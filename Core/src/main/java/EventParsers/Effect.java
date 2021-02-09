package EventParsers;

import java.util.Map;

import Enum.FactionType;

public class Effect {

    private Map<FactionType, Integer> actionsOnFaction;
    private Map<String, Integer> actionsOnFactor;
    private Integer partisans;

    public Effect(Map<FactionType, Integer> actionsOnFaction, Map<String, Integer> actionsOnFactor, Integer partisans) {
        this.actionsOnFaction = actionsOnFaction;
        this.actionsOnFactor = actionsOnFactor;
        this.partisans = partisans;
    }

    public Map<FactionType, Integer> getActionsOnFaction() {
        return actionsOnFaction;
    }

    public void setActionsOnFaction(Map<FactionType, Integer> actionsOnFaction) {
        this.actionsOnFaction = actionsOnFaction;
    }

    public Map<String, Integer> getActionsOnFactor() {
        return actionsOnFactor;
    }

    public void setActionsOnFactor(Map<String, Integer> actionsOnFactor) {
        this.actionsOnFactor = actionsOnFactor;
    }

    public Integer getPartisans() {
        return partisans;
    }

    public void setPartisans(Integer partisans) {
        this.partisans = partisans;
    }
}
