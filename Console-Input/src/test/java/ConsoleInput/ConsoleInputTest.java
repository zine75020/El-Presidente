package ConsoleInput;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
