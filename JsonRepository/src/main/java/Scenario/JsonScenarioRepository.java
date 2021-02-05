package Scenario;

import Parsers.Scenario.Scenario;
import Parsers.Scenario.ScenarioRepository;

import java.util.List;

public class JsonScenarioRepository implements ScenarioRepository {

    private String path;

    public JsonScenarioRepository(String path) {
        this.path = path;
    }

    @Override
    public List<Scenario> getAllScenarios() {

        return null;
    }
}