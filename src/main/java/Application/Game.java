package Application;

import Core.Agriculture.Agriculture;
import Core.Enum.FactionType;
import Core.Enum.Season;
import Core.EventParsers.Choice;
import Core.EventParsers.Effect;
import Core.EventParsers.Event;
import Core.Faction.Faction;
import Core.Industry.Industry;
import Core.Isle.Isle;
import JsonScenarioRepository.JsonScenarioRepository;
import ConsoleOutput.ConsoleOutput;
import ConsoleInput.ConsoleInput;
import Core.ScenarioParsers.Scenario;

import Core.Enum.DifficultyChoice;

import java.util.*;

public class Game {

    public static final String SCENARIO_FILE_PATH = "src/main/Json/Scenario/scenarios.json";

    public static Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {

        //TODO gérer la suite du jeu lorsqu'il y a des erreurs

        ConsoleOutput consoleOutput = new ConsoleOutput();

        //affichage bienvenue
        System.out.println(consoleOutput.welcome());

        //affichage menu + choix difficulté
        int selectedDifficulty = difficultyTreatment();
        //récupération de la difficulté (enum) selon la difficulté (int)
        DifficultyChoice difficultyChoice = getDifficultyChoiceByDifficulty(selectedDifficulty);

        //récupération de la satisfaction minimale qui sera appliquée au jeu selon la difficulté choisie
        int minimumSatisfaction = getMinimumSatisfactionByDifficulty(difficultyChoice);

        //affichage menu scénario (+ bac à sable)
        /*
         * TODO gérer toutes les exceptions d'existence de clé (au cas où un fichier json serait mal formé)
         *  mieux vaut se retrouver avec une information en moins qu'avec un programme qui plante
         */
        Scenario selectedScenario = scenarioTreatment(selectedDifficulty);

        //initialisation de l'île
        Isle isle = new Isle(new Industry(selectedScenario.getGameStartParameters().getIndustryPercentage()),
                new Agriculture(selectedScenario.getGameStartParameters().getAgriculturePercentage()),
                selectedScenario.getGameStartParameters().getTreasury(),
                difficultyChoice, selectedScenario.getGameStartParameters().getFoodUnits(), minimumSatisfaction, selectedScenario.getGameStartParameters().getFactions());

        System.out.println(consoleOutput.startGame());
        //méthode de déroulement de la partie
        gameExecution(isle, selectedScenario);
    }

    private static void gameExecution(Isle isle, Scenario selectedScenario) {
        ConsoleOutput consoleOutput = new ConsoleOutput();

        List<Event> neutralEvents = new ArrayList<>(selectedScenario.getNeutralEvents());

        //traitement de chaque tout tant qu'un coup d'état n'est pas déclenché
        do {
            isle.nextTurn();
            System.out.println(consoleOutput.gameInformations(isle));

            //traitement scénario
            if (selectedScenario.getScenarioEvents().size() != 0) {
                scenarioEventTreatment(selectedScenario, isle);
            }
            //traitement bac à sable + fin scénario
            else if (selectedScenario.getNeutralEvents().size() != 0) {
                sandboxEventTreatment(selectedScenario, isle, neutralEvents);
            }
            //aucun événement
            else {
                break;
            }

            //si c'est la fin d'année il faut faire le traitement de fin d'année
            if (isle.getSeason() == Season.WINTER) {
                endOfYearTreatment(isle);
            }

            //on passe à la saison suivante en fin de tour
            isle.nextSeason();

        } while (!isle.triggerCoup());

        if(isle.triggerCoup()){
            System.out.println(consoleOutput.endGame());
            System.out.println(consoleOutput.printScore(isle));
        }
        else {
            System.out.println(consoleOutput.impossibleGame());
        }
    }

    private static void scenarioEventTreatment(Scenario selectedScenario, Isle isle) {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        Event currentEvent;
        boolean isRelatedEvent = false;
        //si l'event a un name c'est qu'il n'est pas encore passé
        if (!selectedScenario.getScenarioEvents().get(0).getName().equals("")) {
            //récupération du premier event
            currentEvent = selectedScenario.getScenarioEvents().get(0);
        }
        //sinon il faut récupérer le related event
        else {
            isRelatedEvent = true;
            currentEvent = selectedScenario.getScenarioEvents().get(0).getChoices().get(0).getRelatedEvents().get(0);
        }
        System.out.println("-- " + currentEvent.getName());

        int i = 1;
        for (Choice choice : currentEvent.getChoices()) {
            //affichage des différents choix
            printChoices(choice, i);
            i += 1;
        }

        int selectedChoiceId;
        do {
            //récupération du choix de l'utilisateur
            String choiceInput = clavier.next();
            //vérification que la valeur saisie est valide
            selectedChoiceId = consoleInput.verifyChoice(choiceInput, currentEvent.getChoices().size());
            //si la valeur n'est pas valide on affiche une erreur et on reboucle
            if (selectedChoiceId == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (selectedChoiceId == -1);

        Choice currentChoice = currentEvent.getChoices().get(selectedChoiceId - 1);

        applicationEffects(isle, currentChoice);

        if (currentChoice.getRelatedEvents().size() > 0) {
            currentEvent.setName("");
            int y;
            for (y = 0; y < currentEvent.getChoices().size(); y++) {
                if (currentEvent.getChoices().get(y) != currentChoice) {
                    currentEvent.getChoices().remove(currentEvent.getChoices().get(y));
                    y = 0;
                }
            }
        } else {
            if(isRelatedEvent) {
                selectedScenario.getScenarioEvents().get(0).getChoices().get(0).getRelatedEvents().remove(currentEvent);
                if(selectedScenario.getScenarioEvents().get(0).getChoices().get(0).getRelatedEvents().size() == 0) {
                    selectedScenario.getScenarioEvents().remove(0);
                }
            }
            else {
                selectedScenario.getScenarioEvents().remove(currentEvent);
            }
        }
    }

    private static void sandboxEventTreatment(Scenario selectedScenario, Isle isle, List<Event> neutralEvents) {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        Event currentEvent;
        //TODO Gérer relatedEvents événement neutres (problématique aléatoire) (possibilité plusieurs relatedEvents d'affilée)
        int randomIndex = (int) Math.round(Math.random() * (neutralEvents.size() - 1));
        currentEvent = neutralEvents.get(randomIndex);
        System.out.println("-- " + currentEvent.getName());
        int i = 1;
        for (Choice choice : currentEvent.getChoices()) {
            printChoices(choice, i);
            i += 1;
        }
        int selectedChoiceId;
        do {
            //récupération du choix de l'utilisateur
            String choiceInput = clavier.next();
            //vérification que la valeur saisie est valide
            selectedChoiceId = consoleInput.verifyChoice(choiceInput, currentEvent.getChoices().size());
            //si la valeur n'est pas valide on affiche une erreur et on reboucle
            if (selectedChoiceId == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (selectedChoiceId == -1);

        Choice currentChoice = currentEvent.getChoices().get(selectedChoiceId - 1);

        applicationEffects(isle, currentChoice);

        neutralEvents.remove(currentEvent);
        if (neutralEvents.size() == 0) {
            neutralEvents = selectedScenario.getNeutralEvents();
        }
    }

    private static void applicationEffects(Isle isle, Choice choice) {
        for (Effect effect : choice.getEffects()) {
            //effets sur les factions
            for (Map.Entry actionOnFaction : effect.getActionsOnFaction().entrySet()) {
                //increase si positif
                if ((int) actionOnFaction.getValue() > 0)
                    isle.getFactionByFactionType((FactionType) actionOnFaction.getKey()).
                            increasePercentageApproval((int) actionOnFaction.getValue());
                //decrease si positif
                else
                    isle.getFactionByFactionType((FactionType) actionOnFaction.getKey()).
                            increasePercentageApproval((int) actionOnFaction.getValue());
            }
            //effets sur les facteurs
            for (Map.Entry actionOnFactor : effect.getActionsOnFactor().entrySet()) {
                switch ((String) actionOnFactor.getKey()) {
                    case "INDUSTRY":
                        //increase si positif
                        if ((int) actionOnFactor.getValue() > 0)
                            isle.increaseIndustry((int) actionOnFactor.getValue());
                        //decrease si positif
                        else
                            isle.decreaseIndustry((int) actionOnFactor.getValue());
                        break;
                    case "AGRICULTURE":
                        //increase si positif
                        if ((int) actionOnFactor.getValue() > 0)
                            isle.increaseAgriculture((int) actionOnFactor.getValue());
                        //decrease si positif
                        else
                            isle.decreaseAgriculture((int) actionOnFactor.getValue());
                        break;
                    case "TREASURY":
                        //increase si positif
                        if ((int) actionOnFactor.getValue() > 0)
                            isle.increaseTreasury((int) actionOnFactor.getValue());
                        //decrease si positif
                        else
                            isle.decreaseTreasury((int) actionOnFactor.getValue());
                        break;
                }
            }
            //effets sur les partisans
            if (effect.getPartisans() != 0) {
                //increase si positif
                if (effect.getPartisans() > 0)
                    isle.increasePartisans(effect.getPartisans());
                //decrease si positif
                else
                    isle.decreasePartisans(effect.getPartisans());
            }
        }
    }

    private static void printChoices(Choice choice, int i) {
        //affichage intitulé du choix
        System.out.println(i + " : " + choice.getChoice());
        //affichage des effets
        System.out.println("      Effets :");
        for (Effect effect : choice.getEffects()) {
            //effets sur les factions
            for (Map.Entry actionOnFaction : effect.getActionsOnFaction().entrySet()) {
                if ((int) actionOnFaction.getValue() > 0)
                    System.out.println("          " + actionOnFaction.getKey() + " : +" + actionOnFaction.getValue() + "%");
                else
                    System.out.println("          " + actionOnFaction.getKey() + " : " + actionOnFaction.getValue() + "%");
            }
            //effets sur les facteurs
            for (Map.Entry actionOnFactor : effect.getActionsOnFactor().entrySet()) {
                if ((int) actionOnFactor.getValue() > 0)
                    System.out.println("          " + actionOnFactor.getKey() + " : +" + actionOnFactor.getValue() + "%");
                else
                    System.out.println("          " + actionOnFactor.getKey() + " : " + actionOnFactor.getValue() + "%");
            }
            //effets sur les partisans
            if (effect.getPartisans() != 0)
                System.out.println("          PARTISANS : " + effect.getPartisans() + "\n");
        }
    }

    private static void endOfYearTreatment(Isle isle) {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        int endOfYearChoice;
        System.out.println(consoleOutput.endOfYearInfo());
        //on propose le menu de fin d'année jusqu'à ce que l'utilisateur fasse le bilan de fin d'année
        do {
            endOfYearChoice = -1;
            do {
                //affichage du menu de fin d'année
                System.out.println(consoleOutput.endOfYearMenu(isle));
                //récupération des id des choix disponibles
                List<Integer> endOfYearChoices = new ArrayList<>();
                if (isle.bribeIsPossible()) endOfYearChoices.add(1);
                if (isle.foodMartIsPossible()) endOfYearChoices.add(2);
                endOfYearChoices.add(3);
                //récupération du choix de l'utilisateur
                String input = clavier.next();
                //vérification que la valeur saisie est valide
                endOfYearChoice = consoleInput.verifyEndOfYearChoice(input, endOfYearChoices);
                //si la valeur n'est pas valide on affiche une erreur et on reboucle
                if (endOfYearChoice == -1) {
                    System.out.println(consoleOutput.valueOfMenuError());
                }
            } while (endOfYearChoice < 0);

            //une fois que la valeur est bonne on effectue la suite selon le choix qui a été fait
            switch (endOfYearChoice) {
                case 1:
                    int bribeChoice;
                    do {
                        //affichage du menu de pot de vin
                        System.out.println(consoleOutput.bribeMenu(isle));

                        //récupération des id des choix disponibles
                        List<Integer> bribeChoices = new ArrayList<>();
                        int i = 1;
                        for (Faction faction : isle.getFactionList()) {
                            //ajout de l'id si le pot de vin est possible pour cette faction
                            if (isle.bribeIsPossible(faction)) bribeChoices.add(i);
                            i += 1;
                        }

                        //récupération du choix de l'utilisateur
                        String input = clavier.next();
                        //vérification que la valeur saisie est valide
                        bribeChoice = consoleInput.verifyBribeChoice(input, bribeChoices);
                        //si la valeur n'est pas valide on affiche une erreur et on reboucle
                        if (bribeChoice == -1) {
                            System.out.println(consoleOutput.valueOfMenuError());
                        }
                    } while (bribeChoice < 0);
                    //TODO traitement pot de vin
                    break;
                case 2:
                    int foodMarksChoice;
                    do {
                        //affichage du menu de marché alimentaire
                        System.out.println(consoleOutput.foodMarksAsk(isle));
                        //récupération du choix de l'utilisateur
                        String input = clavier.next();
                        //vérification que la valeur saisie est valide
                        foodMarksChoice = consoleInput.verifyFoodMarksChoice(input);
                        //si la valeur n'est pas valide on affiche une erreur et on reboucle
                        if (foodMarksChoice == -1) {
                            System.out.println(consoleOutput.valueError());
                        }
                    } while (foodMarksChoice < 0);
                    //TODO traitement marché alimentaire
                    break;
                case 3:
                    //TODO traitement bilan de fin d'année
                    break;
            }
        } while (endOfYearChoice != 3);
    }

    private static int difficultyTreatment() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        int difficulty;
        do {
            //affichage du menu de choix de difficulté
            System.out.println(consoleOutput.difficultyMenu());
            //récupération du choix de l'utilisateur
            String input = clavier.next();
            //vérification que la valeur saisie est valide
            difficulty = consoleInput.verifyDifficultyChoice(input);
            //si la valeur n'est pas valide on affiche une erreur et on reboucle
            if (difficulty == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (difficulty < 0);
        return difficulty;
    }

    private static int getMinimumSatisfactionByDifficulty(DifficultyChoice difficulty) {
        int minimumSatisfaction;
        //récupération du pourcentage de satisfaction minimale selon la difficulté choisie pour la partie
        switch (difficulty) {
            case easy:
                minimumSatisfaction = 10;
                break;
            case hard:
                minimumSatisfaction = 50;
                break;
            default:
                minimumSatisfaction = 30;
        }
        return minimumSatisfaction;
    }

    private static DifficultyChoice getDifficultyChoiceByDifficulty(int selectedDifficulty) {
        DifficultyChoice difficultyChoice;
        //récupération de la valeur de l'enum correspondant à la difficulté choisie
        switch (selectedDifficulty) {
            case 1:
                difficultyChoice = DifficultyChoice.easy;
                break;
            case 3:
                difficultyChoice = DifficultyChoice.hard;
                break;
            default:
                difficultyChoice = DifficultyChoice.normal;
        }
        return difficultyChoice;
    }

    private static Scenario scenarioTreatment(int difficulty) {
        int selectedScenarioId;
        JsonScenarioRepository jsonScenarioRepository = new JsonScenarioRepository();

        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        do {
            //affichage du menu des scénarios en les récupérant depuis le fichier json contenant les scénarios
            System.out.println(consoleOutput.scenarioMenu(jsonScenarioRepository.getAllScenarios(SCENARIO_FILE_PATH)));
            //récupération du choix de l'utilisateur
            String input = clavier.next();
            //vérification que la valeur saisie est valide (contenue dans la liste les ids de scénarios récupérée depuis le fichier json)
            selectedScenarioId = consoleInput.verifyScenarioChoice(input, jsonScenarioRepository.getAllScenariosIds(SCENARIO_FILE_PATH));
            //si la valeur n'est pas valide on affiche une erreur et on reboucle
            if (selectedScenarioId == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (selectedScenarioId == -1);

        //parser le scenario choisi
        return jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, selectedScenarioId, difficulty);
    }

}
