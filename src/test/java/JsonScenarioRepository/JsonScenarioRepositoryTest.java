package JsonScenarioRepository;

import Core.ScenarioParsers.Scenario;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonScenarioRepositoryTest {

    JsonScenarioRepository jsonScenarioRepository = new JsonScenarioRepository();
    public static final String SCENARIO_FILE_PATH = "src/main/Json/Scenario/Scenarios.json";

    @Test
    public void should_get_all_scenarios() {
        assertTrue(jsonScenarioRepository.getAllScenarios(SCENARIO_FILE_PATH).size() > 0);
        assertEquals(jsonScenarioRepository.getAllScenarios("").size(), 0);
    }

    @Test
    public void should_get_all_scenarios_ids() {
        assertTrue(jsonScenarioRepository.getAllScenariosIds(SCENARIO_FILE_PATH).size() > 0);
        assertEquals(jsonScenarioRepository.getAllScenariosIds("").size(), 0);
    }

    @Test
    public void should_get_scenario_by_id_1_and_difficulty_1_() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 1, 1);
        assertEquals("Attack on Titans", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(50, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(45, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(800, scenario.getGameStartParameters().getTreasury());
        assertEquals(600, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_1_and_difficulty_2_() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 1, 2);
        assertEquals("Attack on Titans", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(40, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(35, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(700, scenario.getGameStartParameters().getTreasury());
        assertEquals(500, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_1_and_difficulty_3_() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 1, 3);
        assertEquals("Attack on Titans", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(30, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(25, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(600, scenario.getGameStartParameters().getTreasury());
        assertEquals(400, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_2_and_difficulty_1() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 2, 1);
        assertEquals("Cold War: The USA version", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(35, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(45, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(1100, scenario.getGameStartParameters().getTreasury());
        assertEquals(1100, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_2_and_difficulty_2() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 2, 2);
        assertEquals("Cold War: The USA version", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(25, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(35, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(1000, scenario.getGameStartParameters().getTreasury());
        assertEquals(1000, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());

    }

    @Test
    public void should_get_scenario_by_id_2_and_difficulty_3() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 2, 3);
        assertEquals("Cold War: The USA version", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(15, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(25, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(900, scenario.getGameStartParameters().getTreasury());
        assertEquals(900, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_3_and_difficulty_1() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 3, 1);
        assertEquals("Cold War: The USSR version", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(65, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(35, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(900, scenario.getGameStartParameters().getTreasury());
        assertEquals(1100, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_3_and_difficulty_2() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 3, 2);
        assertEquals("Cold War: The USSR version", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(45, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(25, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(800, scenario.getGameStartParameters().getTreasury());
        assertEquals(1000, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_3_and_difficulty_3() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 3, 3);
        assertEquals("Cold War: The USSR version", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(35, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(20, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(700, scenario.getGameStartParameters().getTreasury());
        assertEquals(900, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_4_and_difficulty_1() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 4, 1);
        assertEquals("The 100", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(20, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(45, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(800, scenario.getGameStartParameters().getTreasury());
        assertEquals(1100, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_4_and_difficulty_2() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 4, 2);
        assertEquals("The 100", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(10, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(40, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(700, scenario.getGameStartParameters().getTreasury());
        assertEquals(1000, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_4_and_difficulty_3() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 4, 3);
        assertEquals("The 100", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(8, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(30, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(600, scenario.getGameStartParameters().getTreasury());
        assertEquals(900, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_5_and_difficulty_1() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 5, 1);
        assertEquals("Jojo's Bizarre Adventure : Diamond is Unbreakable", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(70, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(30, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(400, scenario.getGameStartParameters().getTreasury());
        assertEquals(700, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_5_and_difficulty_2() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 5, 2);
        assertEquals("Jojo's Bizarre Adventure : Diamond is Unbreakable", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(50, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(15, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(200, scenario.getGameStartParameters().getTreasury());
        assertEquals(600, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_5_and_difficulty_3() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 5, 3);
        assertEquals("Jojo's Bizarre Adventure : Diamond is Unbreakable", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(30, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(5, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(100, scenario.getGameStartParameters().getTreasury());
        assertEquals(500, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_6_and_difficulty_1() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 6, 1);
        assertEquals("Shadow War", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(70, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(30, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(1000, scenario.getGameStartParameters().getTreasury());
        assertEquals(700, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_6_and_difficulty_2() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 6, 2);
        assertEquals("Shadow War", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(60, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(25, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(800, scenario.getGameStartParameters().getTreasury());
        assertEquals(600, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_6_and_difficulty_3() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 6, 3);
        assertEquals("Shadow War", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(50, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(20, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(600, scenario.getGameStartParameters().getTreasury());
        assertEquals(400, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_7_and_difficulty_1() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 7, 1);
        assertEquals("Bac à sable", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(20, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(20, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(300, scenario.getGameStartParameters().getTreasury());
        assertEquals(300, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_7_and_difficulty_2() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 7, 2);
        assertEquals("Bac à sable", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(15, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(15, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(200, scenario.getGameStartParameters().getTreasury());
        assertEquals(200, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_scenario_by_id_7_and_difficulty_3() {
        Scenario scenario = jsonScenarioRepository.getScenarioByIdAndDifficulty(SCENARIO_FILE_PATH, 7, 3);
        assertEquals("Bac à sable", scenario.getName());
        assertNotNull(scenario.getStory());
        //game start parameters
        assertEquals(10, scenario.getGameStartParameters().getAgriculturePercentage());
        assertEquals(10, scenario.getGameStartParameters().getIndustryPercentage());
        assertEquals(150, scenario.getGameStartParameters().getTreasury());
        assertEquals(150, scenario.getGameStartParameters().getFoodUnits());
        //factions
        assertEquals(8, scenario.getGameStartParameters().getFactions().size());
    }

    @Test
    public void should_get_difficulty_key() {
        assertEquals("EASY", jsonScenarioRepository.getDifficultyKey(1));
        assertEquals("NORMAL", jsonScenarioRepository.getDifficultyKey(2));
        assertEquals("DIFFICULT", jsonScenarioRepository.getDifficultyKey(3));
    }

}
