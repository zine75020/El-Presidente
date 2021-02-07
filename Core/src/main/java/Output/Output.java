package Output;

import ScenarioParsers.Scenario;

import java.util.List;

public interface Output {
    StringBuilder difficultyMenu();
    String welcome();
    String valueOfMenuError();
    StringBuilder scenarioMenu(List<Scenario> allScenarios);
}
