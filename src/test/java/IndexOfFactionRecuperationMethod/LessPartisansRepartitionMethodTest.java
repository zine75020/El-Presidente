package IndexOfFactionRecuperationMethod;

import Core.Agriculture.Agriculture;
import Core.Enum.DifficultyChoice;
import Core.Industry.Industry;
import Core.Isle.Isle;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LessPartisansRepartitionMethodTest {

    LessPartisansRepartitionMethod lessPartisansRepartitionMethod = new LessPartisansRepartitionMethod();
    Isle isle = new Isle(new Industry(10), new Agriculture(20), 100
            , DifficultyChoice.normal, 0, 30);

    @Test
    public void should_get_less_index_of_faction_0() {
        isle.getFactionList().get(0).increaseNbSupporters(1);
        isle.getFactionList().get(1).increaseNbSupporters(4);
        isle.getFactionList().get(2).increaseNbSupporters(3);
        isle.getFactionList().get(3).increaseNbSupporters(1);
        assertEquals(0, lessPartisansRepartitionMethod.getIndexOfFactionByMethod(isle));
    }

    @Test
    public void should_get_less_index_of_faction_1() {
        isle.getFactionList().get(0).increaseNbSupporters(0);
        isle.getFactionList().get(1).increaseNbSupporters(1);
        isle.getFactionList().get(2).increaseNbSupporters(3);
        isle.getFactionList().get(3).increaseNbSupporters(1);
        assertEquals(1, lessPartisansRepartitionMethod.getIndexOfFactionByMethod(isle));
    }

    @Test
    public void should_get_less_index_of_faction_3() {
        isle.getFactionList().get(0).increaseNbSupporters(0);
        isle.getFactionList().get(1).increaseNbSupporters(5);
        isle.getFactionList().get(2).increaseNbSupporters(2);
        isle.getFactionList().get(3).increaseNbSupporters(1);
        assertEquals(3, lessPartisansRepartitionMethod.getIndexOfFactionByMethod(isle));
    }
}