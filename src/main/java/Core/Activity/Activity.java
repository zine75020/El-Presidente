package Core.Activity;

public abstract class Activity {

    protected int dedicatedPercentage;

    public Activity(int dedicatedPercentage) {
        this.dedicatedPercentage = dedicatedPercentage;
    }

    //GETTERS AND SETTERS

    public int getDedicatedPercentage() {
        return this.dedicatedPercentage;
    }

    public void setDedicatedPercentage(int dedicatedPercentage) {
        this.dedicatedPercentage = dedicatedPercentage;
    }

    //METHODS

    /**
     * augmente le pourcentage du nombre passé en paramètres et limite à 100
     *
     * @param nbAdd
     */
    public void increaseDedicatedPercentage(int nbAdd) {
        this.dedicatedPercentage += nbAdd;
        if(this.dedicatedPercentage > 100) {
            this.dedicatedPercentage = 100;
        }
    }

    /**
     * diminue le pourcentage du nombre passé en paramètres et limite à 0
     *
     * @param nbRemove
     */
    public void decreaseDedicatedPercentage(int nbRemove) {
        this.dedicatedPercentage -= nbRemove;
        if(this.dedicatedPercentage < 0) {
            this.dedicatedPercentage = 0;
        }
    }
}
