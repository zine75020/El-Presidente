package Core.EventParsers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public interface EventsRepository {
    List<Event> getAllEventsByIdAndDifficulty(Integer selectedScenarioId, Integer difficulty);

    List<Event> getNeutralEventsByDifficulty(Integer difficulty);

    String getPathByIdAndDifficulty(Integer selectedScenarioId, Integer difficulty);

    List<Event> parseEvents(JsonObject myobject);

    List<Event> parseRelatedEvents(JsonObject myobject);

    void parseEvents(JsonArray myobject, List<Event> allEvents);

    List<Choice> getAllChoices(JsonArray choices);

    List<Effect> getAllEffects(JsonArray effects);
}
