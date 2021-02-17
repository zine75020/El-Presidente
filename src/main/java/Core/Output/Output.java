package Core.Output;

import Core.Isle.Isle;
import Core.ScenarioParsers.Scenario;

import java.util.List;

public interface Output {

    StringBuilder difficultyMenu();

    String welcome();

    String startGame();

    String endGame();

    String impossibleGame();

    StringBuilder gameInformations(Isle isle);

    String valueError();

    String valueOfMenuError();

    StringBuilder scenarioMenu(List<Scenario> allScenarios);

    StringBuilder printScore(Isle isle);

    StringBuilder endOfYearInfo();

    StringBuilder endOfYearMenu(Isle isle);

    StringBuilder bribeMenu(Isle isle);

    StringBuilder foodMarksAsk(Isle isle);
}
