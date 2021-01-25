package Activity;

public abstract class Activity {

    protected int dedicatedPercentage;

    public Activity(int dedicatedPercentage) {
        this.dedicatedPercentage = dedicatedPercentage;
    }

    public int getDedicatedPercentage() {
        return this.dedicatedPercentage;
    }

    public void setDedicatedPercentage(int dedicatedPercentage) {
        this.dedicatedPercentage = dedicatedPercentage;
    }

    public void increaseDedicatedPercentage(int nbAdd) {
        this.dedicatedPercentage += nbAdd;
        if(this.dedicatedPercentage > 100) {
            this.dedicatedPercentage = 100;
        }
    }

    public void decreaseDedicatedPercentage(int nbRemove) {
        this.dedicatedPercentage -= nbRemove;
        if(this.dedicatedPercentage < 0) {
            this.dedicatedPercentage = 0;
        }
    }
}
