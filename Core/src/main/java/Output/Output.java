package Output;

import Isle.Isle;
import ScenarioParsers.Scenario;

import java.util.List;

public interface Output {

    StringBuilder difficultyMenu();
    String welcome();
    String startGame();
    String endGame();
    StringBuilder gameInformations(Isle isle);
    String valueOfMenuError();
    StringBuilder scenarioMenu(List<Scenario> allScenarios);
    StringBuilder printScore(Isle isle);
}
