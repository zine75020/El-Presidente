package Agriculture;

import Activity.Activity;

public class Agriculture extends Activity {

    public Agriculture(int dedicatedPercentage) {
        super(dedicatedPercentage);
    }

    //METHODS

    /**
     * génération de 40 unités par pourcentage de l'agriculture sur l'île
     *
     * @return
     */
    public int generateFoodUnits() {
        return 40 * this.dedicatedPercentage;
    }
}
