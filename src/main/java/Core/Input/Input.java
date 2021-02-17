package Core.Input;

import java.util.List;

public interface Input {

    int verifyDifficultyChoice(String input);

    int verifyScenarioChoice(String input, List<Integer> allIds);

    int verifyEndOfYearChoice(String input, List<Integer> endOfYearChoices);

    int verifyBribeChoice(String input, List<Integer> bribeChoices);

    int verifyFoodMarksChoice(String input);

    int verifyChoice(String choiceInput,int size);
}
