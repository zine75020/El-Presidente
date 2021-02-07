package Game;

import Faction.Faction;
import JsonScenarioRepository.JsonScenarioRepository;
import ConsoleOutput.ConsoleOutput;
import ConsoleInput.ConsoleInput;
import ScenarioParsers.Scenario;

import java.util.Scanner;

public class Game {

    public static final String SCENARIO_FILE_PATH = "C:/Users/alois/Documents/GitHub/El-Presidente/Core/src/main/Json/Scenario/Scenarios.json";

    public static Scanner clavier = new Scanner(System.in);

    public static void main(String[] args) {

        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();
        //affichage bienvenue
        System.out.println(consoleOutput.welcome());

        //affichage menu + choix difficulté
        int selectedDifficulty = difficultyTreatment();

        int minimumSatisfaction = getMinimumSatisfactionByDifficulty(selectedDifficulty);

        //affichage menu scénario (+ bac à sable)
        Scenario selectedScenario = scenarioTreatment(selectedDifficulty);

        //une fois toutes les données nécessaires à l'initialisation de l'île
        //Isle isle = new Isle(..., selectedDifficulty, ...);

    }

    private static int difficultyTreatment() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        int difficulty = -1;
        do {
            System.out.println(consoleOutput.difficultyMenu());
            String input = clavier.next();
            difficulty = consoleInput.verifyDifficultyChoice(input);
            if (difficulty == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (difficulty < 0);
        return difficulty;
    }

    private static int getMinimumSatisfactionByDifficulty(int difficulty) {
        int minimumSatisfaction;
        switch (difficulty) {
            case 1:
                minimumSatisfaction = 10;
                break;
            case 2:
                minimumSatisfaction = 30;
                break;
            case 3:
                minimumSatisfaction = 50;
                break;
            default:
                minimumSatisfaction = 30;
        }
        return minimumSatisfaction;
    }

    private static Scenario scenarioTreatment(int difficulty) {
        Integer selectedScenarioId = -1;
        JsonScenarioRepository jsonScenarioRepository = new JsonScenarioRepository();

        ConsoleOutput consoleOutput = new ConsoleOutput();
        ConsoleInput consoleInput = new ConsoleInput();

        do {
            System.out.println(consoleOutput.scenarioMenu(jsonScenarioRepository.getAllScenarios(SCENARIO_FILE_PATH)));
            String input = clavier.next();
            selectedScenarioId = consoleInput.verifyScenarioChoice(input, jsonScenarioRepository.getAllScenariosIds(SCENARIO_FILE_PATH));
            if (selectedScenarioId == -1) {
                System.out.println(consoleOutput.valueOfMenuError());
            }
        } while (selectedScenarioId == -1);

        //parser le scenario choisi
        return jsonScenarioRepository.getScenarioById(SCENARIO_FILE_PATH, selectedScenarioId, difficulty);
    }

}
