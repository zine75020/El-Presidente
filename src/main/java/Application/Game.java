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
        Scenario selectedScenario = scenarioTreatment(selectedDifficulty);

        if (selectedScenario != null && selectedScenario.getNeutralEvents().size() > 0) {//initialisation de l'île
            Isle isle = new Isle(new Industry(selectedScenario.getGameStartParameters().getIndustryPercentage()),
                    new Agriculture(selectedScenario.getGameStartParameters().getAgriculturePercentage()),
                    selectedScenario.getGameStartParameters().getTreasury(),
                    difficultyChoice, selectedScenario.getGameStartParameters().getFoodUnits(), minimumSatisfaction, selectedScenario.getGameStartParameters().getFactions());

            System.out.println(consoleOutput.startGame());
            //méthode de déroulement de la partie
            gameExecution(isle, selectedScenario);
        }
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

        if (isle.triggerCoup()) {
            System.out.println(consoleOutput.endGame());
            System.out.println(consoleOutput.printScore(isle));
        } else {
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

        //s'il y a des related events
        if (currentChoice.getRelatedEvents().size() > 0) {
            //on supprime le nom de l'événement pour savoir qu'il est déjà passé
            currentEvent.setName("");
            int y;
            //on supprime tous les autres choix de l'événement pour ne garder que celui avec les related events
            for (y = 0; y < currentEvent.getChoices().size(); y += 1) {
                if (currentEvent.getChoices().get(y) != currentChoice) {
                    currentEvent.getChoices().remove(currentEvent.getChoices().get(y));
                    y = 0;
                }
            }
        } else {
            //si on traite actuellement un related event on le supprime
            if (isRelatedEvent) {
                selectedScenario.getScenarioEvents().get(0).getChoices().get(0).getRelatedEvents().remove(currentEvent);
                //s'il n'y a plus de related events on supprime l'event global
                if (selectedScenario.getScenarioEvents().get(0).getChoices().get(0).getRelatedEvents().size() == 0) {
                    selectedScenario.getScenarioEvents().remove(0);
                    isRelatedEvent = false;
                }
            }
            //sinon on supprime l'événement global
            else {
                selectedScenario.getScenarioEvents().remove(currentEvent);
            }
        }
    }

    private static void sandboxEventTreatment(Scenario selectedScenario, Isle isle, List<Event> neutralEvents) {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        Event currentEvent = null;
        boolean isRelatedEvent = false;
        //si le premier event n'a pas de name c'est qu'il est déjà passé et qu'il a des related events
        if (neutralEvents.get(0).getName().equals("")) {
            isRelatedEvent = true;
            currentEvent = neutralEvents.get(0).getChoices().get(0).getRelatedEvents().get(0);
        }
        //sinon il faut récupérer un événement aléatoire correspondant à la saison
        else {
            boolean seasonIsOk = false;
            do {
                int randomIndex = (int) Math.round(Math.random() * (neutralEvents.size() - 1));
                //vérification correspondance événement récupéré aléatoirement
                if (neutralEvents.get(randomIndex).getSeasons().contains(isle.getSeason())) {
                    currentEvent = neutralEvents.get(randomIndex);
                    seasonIsOk = true;
                }
                //si la saison ne correspondait pas, on cherche avant l'événement
                if (!seasonIsOk) {
                    for (int x = randomIndex; x > 0; x -= 1) {
                        if (neutralEvents.get(x).getSeasons().contains(isle.getSeason())) {
                            currentEvent = neutralEvents.get(x);
                            seasonIsOk = true;
                        }
                    }
                }
                //si la saison ne correspondait toujours avec aucuns, on cherche après l'événement
                if (!seasonIsOk) {
                    for (int x = randomIndex; x < neutralEvents.size(); x += 1) {
                        if (neutralEvents.get(x).getSeasons().contains(isle.getSeason())) {
                            currentEvent = neutralEvents.get(x);
                            seasonIsOk = true;
                        }
                    }
                }
                //s'il n'y a aucun événement qui correspond, on remet tous les événements neutres
                if (!seasonIsOk) {
                    neutralEvents = new ArrayList<>(selectedScenario.getNeutralEvents());
                }
            } while (!seasonIsOk);
        }

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

        //s'il y a des related events
        if (currentChoice.getRelatedEvents().size() > 0) {
            //on supprime le nom de l'événement pour savoir qu'il est déjà passé
            currentEvent.setName("");
            int y;
            //on supprime tous les autres choix de l'événement pour ne garder que celui avec les related events

            for (y = 0; y < currentEvent.getChoices().size(); y += 1) {
                if (currentEvent.getChoices().get(y) != currentChoice) {
                    currentEvent.getChoices().remove(currentEvent.getChoices().get(y));
                    y = -1;
                }
            }
            //on enlève l'événement et on le remet en début de liste
            neutralEvents.remove(currentEvent);
            neutralEvents.add(0, currentEvent);
            System.out.println(neutralEvents.get(0).getChoices());
        } else {
            //si on traite actuellement un related event on le supprime
            if (isRelatedEvent) {
                neutralEvents.get(0).getChoices().get(0).getRelatedEvents().remove(currentEvent);
                //s'il n'y a plus de related events on supprime l'event global
                if (neutralEvents.get(0).getChoices().get(0).getRelatedEvents().size() == 0) {
                    neutralEvents.remove(0);
                    isRelatedEvent = false;
                }
            }
            //sinon on supprime l'événement global
            else {
                neutralEvents.remove(currentEvent);
            }
        }

        //si la liste est vide, on remet tous les événements neutres
        if (neutralEvents.size() == 0) {
            neutralEvents = new ArrayList<>(selectedScenario.getNeutralEvents());
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
                    //decrease si négatif
                else
                    isle.getFactionByFactionType((FactionType) actionOnFaction.getKey()).
                            decreasePercentageApproval((int) actionOnFaction.getValue());
            }
            //effets sur les facteurs
            for (Map.Entry actionOnFactor : effect.getActionsOnFactor().entrySet()) {
                switch ((String) actionOnFactor.getKey()) {
                    case "INDUSTRY":
                        //increase si positif
                        if ((int) actionOnFactor.getValue() > 0)
                            isle.increaseIndustry((int) actionOnFactor.getValue());
                            //decrease si négatif
                        else
                            isle.decreaseIndustry((int) actionOnFactor.getValue());
                        break;
                    case "AGRICULTURE":
                        //increase si positif
                        if ((int) actionOnFactor.getValue() > 0)
                            isle.increaseAgriculture((int) actionOnFactor.getValue());
                            //decrease si négatif
                        else
                            isle.decreaseAgriculture((int) actionOnFactor.getValue());
                        break;
                    case "TREASURY":
                        //increase si positif
                        if ((int) actionOnFactor.getValue() > 0)
                            isle.increaseTreasury((int) actionOnFactor.getValue());
                            //decrease si négatif
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
                    //decrease si négatif
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
                    System.out.println("          " + actionOnFactor.getKey() + " : +" + actionOnFactor.getValue());
                else
                    System.out.println("          " + actionOnFactor.getKey() + " : " + actionOnFactor.getValue());
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
            System.out.println(consoleOutput.gameInformations(isle));
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
                    int treasury = isle.getTreasury();
                    if (isle.bribe(bribeChoice - 1)) {
                        System.out.println(consoleOutput.bribeInfos(isle.getFactionList().get(bribeChoice - 1).getName()));
                        System.out.println(consoleOutput.loyalistsDiminutionSatisfaction(treasury - isle.getTreasury()));
                        System.out.println(consoleOutput.bribeCoast(treasury - isle.getTreasury()));
                    } else {
                        System.out.println(consoleOutput.bribeEchec(isle.getFactionList().get(bribeChoice - 1).getName()));
                    }
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
                    System.out.println(consoleOutput.nbUnitSet(isle.foodMart(foodMarksChoice)));
                    break;
                case 3:
                    HashMap<String, Integer> reviewOfYear = isle.endOfYearReview();
                    System.out.println(consoleOutput.endOfYearReviewInfos(reviewOfYear));
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
        int selectedScenarioId = -1;
        JsonScenarioRepository jsonScenarioRepository = new JsonScenarioRepository();

        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        if(jsonScenarioRepository.getAllScenarios(SCENARIO_FILE_PATH).size() == 0) {
            return null;
        }
        else {
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

}
