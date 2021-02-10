package JsonEventsRepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonEventsRepositoryTest {

    JsonEventsRepository jsonEventsRepository = new JsonEventsRepository();

    @Test
    public void should_get_all_events_by_id_1_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(1, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(1, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(1, 3).size() > 0);
    }

    //TODO activer quand on aura les modifications de Gabriel et Zinédine
    /*@Test
    public void should_get_all_events_by_id_2_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(2, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(2, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(2, 3).size() > 0);
    }

    @Test
    public void should_get_all_events_by_id_3_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(3, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(3, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(3, 3).size() > 0);
    }

    @Test
    public void should_get_all_events_by_id_4_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(4, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(4, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(4, 3).size() > 0);
    }

    @Test
    public void should_get_all_events_by_id_5_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(5, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(5, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(5, 3).size() > 0);
    }

    @Test
    public void should_get_all_events_by_id_6_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(6, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(6, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(6, 3).size() > 0);
    }

    @Test
    public void should_get_all_events_by_id_7_and_difficulty() {
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(7, 1).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(7, 2).size() > 0);
        assertTrue(jsonEventsRepository.getAllEventsByIdAndDifficulty(7, 3).size() > 0);
    }*/

    @Test
    public void should_get_neutrals_events_by_difficulty() {
        assertTrue(jsonEventsRepository.getNeutralEventsByDifficulty(1).size() > 0);
        assertTrue(jsonEventsRepository.getNeutralEventsByDifficulty(2).size() > 0);
        assertTrue(jsonEventsRepository.getNeutralEventsByDifficulty(3).size() > 0);
    }

}
