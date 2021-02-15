package Game;

import Agriculture.Agriculture;
import Industry.Industry;
import Isle.Isle;
import JsonScenarioRepository.JsonScenarioRepository;
import ConsoleOutput.ConsoleOutput;
import ConsoleInput.ConsoleInput;
import ScenarioParsers.Scenario;

import Enum.DifficultyChoice;

import java.util.Scanner;

public class Game {

    //TODO update les chemins après déplacement du fichier json
    public static final String SCENARIO_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Scenario/Scenarios.json";

    public static Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {

        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

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
                difficultyChoice, minimumSatisfaction, selectedScenario.getGameStartParameters().getFactions());

        //TODO déroulement jeu
        /*
         * différencier traitement scénario et bac à sable
         *
         */
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
        Integer selectedScenarioId;
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
