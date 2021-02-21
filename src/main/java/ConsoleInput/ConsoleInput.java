package ConsoleInput;

import Application.Game;
import Core.Input.Input;
import Core.ScenarioParsers.Scenario;

import java.util.List;

public class ConsoleInput implements Input {

    @Override
    public int verifyDifficultyChoice(String input) {
        try {
            int difficultyChoice = Integer.parseInt(input);
            //vérification que la valeur saisie est bien entre 1 et 3
            if(difficultyChoice >= 1 && difficultyChoice <= 3) {
                return difficultyChoice;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public int verifyScenarioChoice(String input, List<Integer> allIds) {
        try {
            int scenarioChoice = Integer.parseInt(input);
            //vérification que la valeur saisie est bien dans la liste
            if(allIds.contains(scenarioChoice)) {
                return scenarioChoice;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public int verifyEndOfYearChoice(String input, List<Integer> endOfYearChoices) {
        try {
            int endOfYearChoice = Integer.parseInt(input);
            //vérification que la valeur saisie est bien dans la liste
            if(endOfYearChoices.contains(endOfYearChoice)) {
                return endOfYearChoice;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public int verifyBribeChoice(String input, List<Integer> bribeChoices) {
        try {
            int bribeChoice = Integer.parseInt(input);
            //vérification que la valeur saisie est bien dans la liste
            if(bribeChoices.contains(bribeChoice)) {
                return bribeChoice;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public int verifyFoodMarksChoice(String input) {
        try {
            int foodMarksChoice = Integer.parseInt(input);
            //vérification que la valeur saisie est bien supérieur à 0
            if(foodMarksChoice > 0) {
                return foodMarksChoice;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    @Override
    public int verifyChoice(String choiceInput,int size) {
        try {
            int choice = Integer.parseInt(choiceInput);
            //vérification que la valeur saisie est bien correcte
            if(choice >= 1 && choice <= size) {
                return choice;
            }
            else {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
