package EventParsers;

import java.util.List;
import java.util.Map;

import Enum.Season;

public class Event {

    private String name;
    Map<Season, Boolean> seasons;
    List<Choice> choices;

    public Event (String name, Map<Season, Boolean> seasons, List<Choice> choices) {
        this.name = name;
        this.seasons = seasons;
        this.choices = choices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Season, Boolean> getSeasons() {
        return seasons;
    }

    public void setSeasons(Map<Season, Boolean> seasons) {
        this.seasons = seasons;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
