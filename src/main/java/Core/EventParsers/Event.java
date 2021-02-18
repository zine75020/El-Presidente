package Core.EventParsers;

import java.util.List;
import java.util.Map;

import Core.Enum.Season;

public class Event {

    private String name;
    List<Season> seasons;
    List<Choice> choices;

    public Event (String name, List<Season> seasons, List<Choice> choices) {
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

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
