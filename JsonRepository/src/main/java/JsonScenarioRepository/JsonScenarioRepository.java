package JsonScenarioRepository;

import EventParsers.Event;
import Faction.Faction;
import ScenarioParsers.GameStartParameters;
import ScenarioParsers.Scenario;
import ScenarioParsers.ScenarioRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Enum.FactionType;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonScenarioRepository implements ScenarioRepository {

    /**
     * retourne la liste des scénarios
     * @return
     */
    @Override
    public List<Scenario> getAllScenarios(String path) {
        List<Scenario> allScenarios = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);
            allScenarios = parseScenariosForMenu(myobject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allScenarios;
    }

    @Override
    public List<Integer> getAllScenariosIds(String path) {
        List<Integer> allIds = new ArrayList<>();
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);
            allIds = parseScenariosForIds(myobject);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allIds;
    }

    @Override
    public Scenario getScenarioById(String path, Integer selectedScenarioId, Integer difficulty) {
        Scenario scenario = null;
        try (FileReader reader = new FileReader(path)) {
            JsonObject myobject = (JsonObject) new JsonParser().parse(reader);
            JsonArray scenarios = (JsonArray) myobject.get("scenarios");
            JsonObject selectedScenario = null;
            for (JsonElement scenar : scenarios) {
                JsonObject sc = scenar.getAsJsonObject();
                if (sc.get("id").getAsInt() == selectedScenarioId) {
                    selectedScenario = sc;
                }
            }
            String name = selectedScenario.get("name").getAsString();
            String story = selectedScenario.get("story").getAsString();
            GameStartParameters gameStartParameters = this.getGameStartParameters(selectedScenario, difficulty);
            //TODO
            List<Event> scenarioEvents = new ArrayList<>();
            List<Event> neutralEvents = new ArrayList<>();

            scenario = new Scenario(selectedScenarioId, name, story, gameStartParameters, scenarioEvents, neutralEvents);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scenario;
    }

    @Override
    public GameStartParameters getGameStartParameters(JsonObject selectedScenario, Integer difficulty) {
        String difficultyKey = this.getDifficultyKey(difficulty);
        selectedScenario = (JsonObject) selectedScenario.get("gameStartParameters");
        selectedScenario = (JsonObject) selectedScenario.get(difficultyKey);

        int agriculturePercentage = selectedScenario.get("agriculturePercentage").getAsInt();
        int industryPercentage = selectedScenario.get("industryPercentage").getAsInt();
        int treasury = selectedScenario.get("treasury").getAsInt();
        int foodUnits = selectedScenario.get("foodUnits").getAsInt();
        List<Faction> factions = this.getFactions(selectedScenario);

        return new GameStartParameters(agriculturePercentage, industryPercentage, treasury, foodUnits, factions);
    }

    @Override
    public List<Faction> getFactions(JsonObject selectedScenario) {
        List<Faction> factions = new ArrayList<>();
        selectedScenario = (JsonObject) selectedScenario.get("factions");
        JsonObject faction = (JsonObject) selectedScenario.get("CAPITALISTS");
        factions.add(new Faction(FactionType.CAPITALISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("COMMUNISTS");
        factions.add(new Faction(FactionType.COMMUNISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("LIBERALS");
        factions.add(new Faction(FactionType.LIBERALS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("RELIGIOUS");
        factions.add(new Faction(FactionType.RELIGIOUS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("MILITARISTS");
        factions.add(new Faction(FactionType.MILITARIES,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("ECOLOGISTS");
        factions.add(new Faction(FactionType.ECOLOGISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("LOYALISTS");
        factions.add(new Faction(FactionType.LOYALISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        faction = (JsonObject) selectedScenario.get("NATIONALISTS");
        factions.add(new Faction(FactionType.NATIONALISTS,
                faction.get("numberOfPartisans").getAsInt(),
                faction.get("satisfactionPercentage").getAsInt()));
        return factions;
    }

    @Override
    public String getDifficultyKey(Integer difficulty) {
        String difficultyKey;
        switch (difficulty) {
            case 1:
                difficultyKey = "EASY";
                break;
            case 2:
                difficultyKey = "NORMAL";
                break;
            case 3:
                difficultyKey = "DIFFICULT";
                break;
            default:
                difficultyKey = "NORMAL";
        }
        return difficultyKey;
    }

    private List<Scenario> parseScenariosForMenu(JsonObject myobject) {
        List<Scenario> allScenarios = new ArrayList<>();
        JsonArray scenarios = (JsonArray) myobject.get("scenarios");
        for (JsonElement scenario : scenarios) {
            JsonObject sc = scenario.getAsJsonObject();
            allScenarios.add(new Scenario(sc.get("id").getAsInt(), sc.get("name").getAsString(), sc.get("story").getAsString()));
        }
        return allScenarios;
    }

    private List<Integer> parseScenariosForIds(JsonObject myobject) {
        List<Integer> allIds = new ArrayList<>();
        JsonArray scenarios = (JsonArray) myobject.get("scenarios");
        for (JsonElement scenario : scenarios) {
            JsonObject sc = scenario.getAsJsonObject();
            allIds.add(sc.get("id").getAsInt());
        }
        return allIds;
    }
}