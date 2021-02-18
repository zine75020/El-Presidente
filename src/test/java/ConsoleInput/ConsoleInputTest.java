package ConsoleInput;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConsoleInputTest {

    ConsoleInput consoleInput = new ConsoleInput();

    @Test
    public void should_verify_difficulty_choice() {
        assertEquals(-1, consoleInput.verifyDifficultyChoice("o"));
        assertEquals(1, consoleInput.verifyDifficultyChoice("1"));
        assertEquals(2, consoleInput.verifyDifficultyChoice("2"));
        assertEquals(3, consoleInput.verifyDifficultyChoice("3"));
        assertEquals(-1, consoleInput.verifyDifficultyChoice("4"));
    }

    @Test
    public void should_verify_scenario_choice() {
        List<Integer> choices = new ArrayList<>();
        choices.add(1);
        choices.add(2);
        choices.add(3);

        assertEquals(-1, consoleInput.verifyScenarioChoice("o", choices));
        assertEquals(1, consoleInput.verifyScenarioChoice("1", choices));
        assertEquals(2, consoleInput.verifyScenarioChoice("2", choices));
        assertEquals(3, consoleInput.verifyScenarioChoice("3", choices));
        assertEquals(-1, consoleInput.verifyScenarioChoice("4", choices));
    }

    @Test
    public void should_verifiy_end_of_year_choice() {
        List<Integer> choices = new ArrayList<>();
        choices.add(1);
        choices.add(2);
        choices.add(3);

        assertEquals(-1, consoleInput.verifyEndOfYearChoice("o", choices));
        assertEquals(1, consoleInput.verifyEndOfYearChoice("1", choices));
        assertEquals(2, consoleInput.verifyEndOfYearChoice("2", choices));
        assertEquals(3, consoleInput.verifyEndOfYearChoice("3", choices));
        assertEquals(-1, consoleInput.verifyEndOfYearChoice("4", choices));
    }

    @Test
    public void should_verify_bribe_choice() {
        List<Integer> choices = new ArrayList<>();
        choices.add(1);
        choices.add(2);
        choices.add(3);

        assertEquals(-1, consoleInput.verifyBribeChoice("o", choices));
        assertEquals(1, consoleInput.verifyBribeChoice("1", choices));
        assertEquals(2, consoleInput.verifyBribeChoice("2", choices));
        assertEquals(3, consoleInput.verifyBribeChoice("3", choices));
        assertEquals(-1, consoleInput.verifyBribeChoice("4", choices));
    }

    @Test
    public void should_verify_food_marks_choice() {
        assertEquals(-1, consoleInput.verifyFoodMarksChoice("o"));
        assertEquals(10, consoleInput.verifyFoodMarksChoice("10"));
        assertEquals(256, consoleInput.verifyFoodMarksChoice("256"));
        assertEquals(37, consoleInput.verifyFoodMarksChoice("37"));
        assertEquals(-1, consoleInput.verifyFoodMarksChoice("-6"));
    }

}
