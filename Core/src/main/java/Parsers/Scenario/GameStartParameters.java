package Parsers.Scenario;

import Faction.Faction;

import java.util.List;

public class GameStartParameters {

    private int agriculturePercentage;
    private int industryPercentage;
    private int treasury;
    private int foodUnits;
    private List<Faction> factions;

    public GameStartParameters(int agriculturePercentage, int industryPercentage,
                               int treasury, int foodUnits, List<Faction> factions) {
        this.agriculturePercentage = agriculturePercentage;
        this.industryPercentage = industryPercentage;
        this.treasury = treasury;
        this.foodUnits = foodUnits;
        this.factions = factions;
    }

    public int getAgriculturePercentage() {
        return agriculturePercentage;
    }

    public void setAgriculturePercentage(int agriculturePercentage) {
        this.agriculturePercentage = agriculturePercentage;
    }

    public int getIndustryPercentage() {
        return industryPercentage;
    }

    public void setIndustryPercentage(int industryPercentage) {
        this.industryPercentage = industryPercentage;
    }

    public int getTreasury() {
        return treasury;
    }

    public void setTreasury(int treasury) {
        this.treasury = treasury;
    }

    public int getFoodUnits() {
        return foodUnits;
    }

    public void setFoodUnits(int foodUnits) {
        this.foodUnits = foodUnits;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }
}
