package ConsoleOutput;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleOutputTest {

    ConsoleOutput consoleOutput = new ConsoleOutput();

    @Test
    public void should_display_rules() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();

        String expectedOutput = "Bienvenue sur El Presidente\n";

        assertEquals(expectedOutput, consoleOutput.welcome());
    }

    @Test
    public void should_display_difficulty_menu() {

        String expectedOutput = "Avec quelle difficulté voulez-vous jouer ?\n" +
                "1: Partie facile\n" +
                "2: Partie normale\n" +
                "3: Partie difficile\n";

        assertEquals(expectedOutput, consoleOutput.difficultyMenu().toString());
    }
}
