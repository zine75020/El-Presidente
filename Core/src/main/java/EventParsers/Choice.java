package EventParsers;

import java.util.List;
import java.util.Map;

public class Choice {

    private String choice;
    private List<Effect> effects;
    private List<Event> relatedEvents;

    public Choice(String choice, List<Effect> effects, List<Event> relatedEvents) {
        this.choice = choice;
        this.effects = effects;
        this.relatedEvents = relatedEvents;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Event> getRelatedEvents() {
        return relatedEvents;
    }

    public void setRelatedEvents(List<Event> relatedEvents) {
        this.relatedEvents = relatedEvents;
    }
}
