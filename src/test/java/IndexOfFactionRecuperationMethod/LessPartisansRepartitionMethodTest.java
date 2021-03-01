package IndexOfFactionRecuperationMethod;

import Core.Agriculture.Agriculture;
import Core.Enum.DifficultyChoice;
import Core.Industry.Industry;
import Core.Isle.Isle;
import org.junit.Test;


import static org.junit.Assert.assertTrue;

public class LessPartisansRepartitionMethodTest {

    LessPartisansRepartitionMethod lessPartisansRepartitionMethod = new LessPartisansRepartitionMethod();
    Isle isle = new Isle(new Industry(10), new Agriculture(20), 100
            , DifficultyChoice.normal, 0, 30);

    @Test
    public void should_get_less_index_of_faction() {
        assertTrue((lessPartisansRepartitionMethod.getIndexOfFactionByMethod(isle) > 0));
    }
}