package Core.ScenarioParsers;

import Core.Faction.Faction;
import com.google.gson.JsonObject;

import java.util.List;

public interface ScenarioRepository {

    List<Scenario> getAllScenarios(String path);

    List<Integer> getAllScenariosIds(String path);

    Scenario getScenarioByIdAndDifficulty(String path, Integer selectedScenarioId, Integer difficulty);

    GameStartParameters getGameStartParameters(JsonObject selectedScenario, Integer difficulty);

    List<Faction> getFactions(JsonObject selectedScenario);

    String getDifficultyKey(Integer difficulty);
}
