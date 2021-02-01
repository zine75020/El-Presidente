package Industry;

import Activity.Activity;

public class Industry extends Activity {

    public Industry(int dedicatedPercentage) {
        super(dedicatedPercentage);
    }

    public int generateFoodUnits() {
        return 40 * this.dedicatedPercentage;
    }

}
