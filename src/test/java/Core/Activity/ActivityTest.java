package Core.Activity;

import Core.Agriculture.Agriculture;
import Core.Industry.Industry;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ActivityTest {

    Agriculture agriculture = new Agriculture(10);
    Agriculture agriculture2 = new Agriculture(0);
    Industry industry = new Industry(20);
    Industry industry2 = new Industry(0);

    @Test
    public void should_increase_percentage_of_agriculture() {
        agriculture.increaseDedicatedPercentage(10);

        assertEquals(20, agriculture.getDedicatedPercentage());
    }

    @Test
    public void should_increase_percentage_of_agriculture_and_limit_at_100() {
        agriculture.increaseDedicatedPercentage(100);

        assertEquals(100, agriculture.getDedicatedPercentage());
    }

    @Test
    public void should_increase_percentage_of_industry() {
        industry.increaseDedicatedPercentage(20);

        assertEquals(40, industry.getDedicatedPercentage());
    }

    @Test
    public void should_increase_percentage_of_industry_and_limit_at_100() {
        industry.increaseDedicatedPercentage(90);

        assertEquals(100, industry.getDedicatedPercentage());
    }

    @Test
    public void should_decrease_percentage_of_agriculture() {
        agriculture.decreaseDedicatedPercentage(5);

        assertEquals(5, agriculture.getDedicatedPercentage());
    }

    @Test
    public void should_not_decrease_percentage_of_agriculture_because_its_at_0() {
        agriculture2.decreaseDedicatedPercentage(10);

        assertEquals(0, agriculture2.getDedicatedPercentage());
    }

    @Test
    public void should_decrease_percentage_of_agriculture_and_limit_at_0() {
        agriculture.decreaseDedicatedPercentage(30);

        assertEquals(0, agriculture.getDedicatedPercentage());
    }

    @Test
    public void should_decrease_percentage_of_industry() {
        industry.decreaseDedicatedPercentage(10);

        assertEquals(10, industry.getDedicatedPercentage());
    }

    @Test
    public void should_decrease_percentage_of_industry_and_limit_at_0() {
        industry.decreaseDedicatedPercentage(40);

        assertEquals(0, industry.getDedicatedPercentage());
    }

    @Test
    public void should_not_decrease_percentage_of_industry_because_its_at_0() {
        industry2.decreaseDedicatedPercentage(15);

        assertEquals(0, industry2.getDedicatedPercentage());
    }

    @Test
    public void should_generate_money() {
        assertEquals(200, industry.generateMoney());
    }

    @Test
    public void should_generate_food_units() {
        assertEquals(400, agriculture.generateFoodUnits());
    }

}
