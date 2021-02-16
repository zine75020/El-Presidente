package Core.Industry;

import Core.Activity.Activity;

public class Industry extends Activity {

    public Industry(int dedicatedPercentage) {
        super(dedicatedPercentage);
    }

    //METHODS

    /**
     * génération de 10$ pour chaque pourcentage d'industrie sur l'île
     *
     * @return
     */
    public int generateMoney() {
        return 10 * this.dedicatedPercentage;
    }

}
