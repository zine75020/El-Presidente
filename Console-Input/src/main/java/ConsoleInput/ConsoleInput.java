package ConsoleInput;

import Input.Input;

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
}
