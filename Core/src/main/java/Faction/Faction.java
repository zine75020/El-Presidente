package Faction;
import Enum.FactionType;

public class Faction {

    /**
     * nom de la faction : capitalistes, communistes, libéraux,
     * religieux, militaristes, écologistes, nationalistes, loyalistes
     */
    private FactionType name;
    private int nbSupporters;
    private int percentageApproval;

    public Faction(FactionType name, int nbSupporters, int percentageApproval) {
        this.name = name;
        this.nbSupporters = nbSupporters;
        this.percentageApproval = percentageApproval;
    }

    public FactionType getName() {
        return this.name;
    }

    public void setName(FactionType name) {
        this.name = name;
    }

    public int getNbSupporters() {
        return this.nbSupporters;
    }

    public void setNbSupporters(int nbSupporters) {
        this.nbSupporters = nbSupporters;
    }

    public int getPercentageApproval() {
        return this.percentageApproval;
    }

    public void setPercentageApproval(int percentageApproval) {
        this.percentageApproval = percentageApproval;
    }

    public void increaseNbSupporters(int nbAdd) {
        this.nbSupporters += nbAdd;
    }

    public void decreaseNbSupporters(int nbRemove) {
        this.nbSupporters -= nbRemove;
        if(this.nbSupporters < 0) {
            this.nbSupporters = 0;
        }
    }

    /**
     * si la pourcentage est à 0, il reste à 0 pour toute la partie
     *
     * @param nbAdd
     */
    public void increasePercentageApproval(int nbAdd) {
        this.percentageApproval += nbAdd;
        if(this.percentageApproval > 100) {
            this.percentageApproval = 100;
        }
    }

    public void decreasePercentageApproval(int nbRemove) {
        this.percentageApproval -= nbRemove;
        if(this.percentageApproval < 0) {
            this.percentageApproval = 0;
        }
    }

    public boolean isPercentageApprovalAt0() {
        return this.percentageApproval == 0;
    }
}
