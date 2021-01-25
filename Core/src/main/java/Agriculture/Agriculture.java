package Agriculture;

import Activity.Activity;

public class Agriculture extends Activity {

    public Agriculture(int dedicatedPercentage) {
        super(dedicatedPercentage);
    }

    public int generateMoney() {
        return 10 * this.dedicatedPercentage;
    }
}
