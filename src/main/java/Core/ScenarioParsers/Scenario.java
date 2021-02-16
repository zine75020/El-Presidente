package Core.ScenarioParsers;

import Core.EventParsers.Event;

import java.util.List;

public class Scenario {

    private int id;
    private String name;
    private String story;
    private GameStartParameters gameStartParameters;
    private List<Event> scenarioEvents;
    private List<Event> neutralEvents;

    public Scenario(int id, String name, String story) {
        this.id = id;
        this.name = name;
        this.story = story;
    }

    public Scenario(int id, String name, String story, GameStartParameters gameStartParameters,
                    List<Event> scenarioEvents, List<Event> neutralEvents) {
        this.id = id;
        this.name = name;
        this.story = story;
        this.gameStartParameters = gameStartParameters;
        this.scenarioEvents = scenarioEvents;
        this.neutralEvents = neutralEvents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public GameStartParameters getGameStartParameters() {
        return gameStartParameters;
    }

    public void setGameStartParameters(GameStartParameters gameStartParameters) {
        this.gameStartParameters = gameStartParameters;
    }

    public List<Event> getScenarioEvents() {
        return scenarioEvents;
    }

    public void setScenarioEvents(List<Event> scenarioEvents) {
        this.scenarioEvents = scenarioEvents;
    }

    public List<Event> getNeutralEvents() {
        return neutralEvents;
    }

    public void setNeutralEvents(List<Event> neutralEvents) {
        this.neutralEvents = neutralEvents;
    }
}
