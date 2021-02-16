package Core.Input;

import java.util.List;

public interface Input {

    int verifyDifficultyChoice(String input);

    Integer verifyScenarioChoice(String input, List<Integer> allIds);
}
