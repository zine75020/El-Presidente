package Core.Faction;
import Core.Enum.FactionType;

public class Faction {

    /**
     * nom de la faction : CAPITALISTS, COMMUNISTS, libéraux,
     * RELIGIOUS, militaristes, écologistes, NATIONALISTS, loyalistes
     */
    private FactionType name;
    private int nbSupporters;
    private int percentageApproval;

    public Faction(FactionType name, int nbSupporters, int percentageApproval) {
        this.name = name;
        this.nbSupporters = nbSupporters;
        this.percentageApproval = percentageApproval;
    }

    //GETTERS AND SETTERS

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

    //METHODS

    /**
     * augmente le nombre de partisants du nombre passé en paramètres
     *
     * @param nbAdd
     */
    public void increaseNbSupporters(int nbAdd) {
        this.nbSupporters += nbAdd;
    }

    /**
     * diminue le nombre de partisants du nombre passé en paramètres et limite à 0
     *
     * @param nbRemove
     */
    public void decreaseNbSupporters(int nbRemove) {
        //si le nombre passé en paramètre est négatif, on le repasse en positif
        if(nbRemove < 0) {
            nbRemove *= -1;
        }
        this.nbSupporters -= nbRemove;
        if(this.nbSupporters <= 0) {
            this.nbSupporters = 0;
            this.percentageApproval = 0;
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

    /**
     * diminue le pourcentage du nombre passé en paramètres et limite à 0
     *
     * @param nbRemove
     */
    public void decreasePercentageApproval(int nbRemove) {
        //si le nombre passé en paramètre est négatif, on le repasse en positif
        if(nbRemove < 0) {
            nbRemove *= -1;
        }
        this.percentageApproval -= nbRemove;
        if(this.percentageApproval < 0) {
            this.percentageApproval = 0;
        }
    }

    /**
     * vérifie si le pourcentage d'approbation est à 0
     * (un pourcentage à 0 ne peut pas réaugmenter)
     *
     * @return
     */
    public boolean isPercentageApprovalAt0() {
        return this.percentageApproval == 0;
    }
}
