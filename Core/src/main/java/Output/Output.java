package Output;

import Isle.Isle;
import ScenarioParsers.Scenario;

import java.util.List;

public interface Output {

    StringBuilder difficultyMenu();
    String welcome();
    String startGame();
    StringBuilder gameInformations(Isle isle);
    String valueOfMenuError();
    StringBuilder scenarioMenu(List<Scenario> allScenarios);
}
