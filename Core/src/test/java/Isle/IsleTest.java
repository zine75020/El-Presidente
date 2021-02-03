package Isle;

import Agriculture.Agriculture;
import Faction.Faction;
import Industry.Industry;
import Enum.Season;
import Enum.DifficultyChoice;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsleTest {

    Isle isle = new Isle(new Industry(10), new Agriculture(20),
            100, DifficultyChoice.normal, 30);
    Isle isle2 = new Isle(new Industry(10), new Agriculture(20),
            0, DifficultyChoice.normal, 30);

    @Test
    public void should_have_8_factions() {
        assertEquals(8, isle.getFactionList().size());
    }

    @Test
    public void should_increase_tresorery() {
        isle.increaseTresorery(100);

        assertEquals(200, isle.getTresorery());
    }

    @Test
    public void should_decrease_tresorery() {
        isle.decreaseTresorery(50);

        assertEquals(50, isle.getTresorery());
    }

    @Test
    public void should_decrease_tresorery_and_limit_at_0() {
        isle.decreaseTresorery(120);

        assertEquals(0, isle.getTresorery());
    }

    @Test
    public void should_not_decrease_tresorery_because_its_at_0() {
        isle2.decreaseTresorery(20);

        assertEquals(0, isle2.getTresorery());
    }

    @Test
    public void should_increase_food_units() {
        isle.increaseFoodUnits(100);

        assertEquals(100, isle.getFoodUnits());
    }

    @Test
    public void should_decrease_food_units() {
        isle.increaseFoodUnits(50);
        isle.decreaseFoodUnits(10);

        assertEquals(40, isle.getFoodUnits());
    }

    @Test
    public void should_decrease_food_units_and_limit_at_0() {
        isle.increaseFoodUnits(20);
        isle.decreaseFoodUnits(40);

        assertEquals(0, isle.getFoodUnits());
    }

    @Test
    public void should_not_decrease_food_units_because_its_at_0() {
        isle2.decreaseFoodUnits(20);

        assertEquals(0, isle2.getFoodUnits());
    }

    @Test
    public void should_go_to_next_season() {
        assertEquals(Season.SPRING, isle.getSeason());
        isle.nextSeason();
        assertEquals(Season.SUMMER, isle.getSeason());
        isle.nextSeason();
        assertEquals(Season.AUTUMN, isle.getSeason());
        isle.nextSeason();
        assertEquals(Season.WINTER, isle.getSeason());
        isle.nextSeason();
        assertEquals(Season.SPRING, isle.getSeason());
    }

    @Test
    public void should_go_to_next_turn() {
        isle.nextTurn();

        assertEquals(1, isle.getTurn());
    }

    //TODO
    @Test
    public void should_set_score() {

    }

    @Test
    public void should_generate_global_satisfaction_percentage() {
        int percentage = 10;
        for (Faction faction : isle.getFactionList()) {
            faction.increasePercentageApproval(percentage);
            percentage += 10;
        }

        assertEquals(47, isle.generateGlobalSatisfactionPercentage());
    }

    @Test
    public void should_make_bribe() {
        isle.getFactionList().get(3).increaseNbSupporters(10);
        isle.increaseTresorery(150);
        assertTrue(isle.bribe(3));
        assertEquals(100, isle.getTresorery());
        assertEquals(10, isle.getFactionList().get(3).getPercentageApproval());
        assertEquals(85, isle.getFactionList().get(7).getPercentageApproval());
    }

    @Test
    public void should_not_make_bribe() {
        isle.getFactionList().get(3).increaseNbSupporters(10);
        assertFalse(isle.bribe(3));
    }

    @Test
    public void should_make_food_mart() {
        assertEquals(5, isle.foodMart(5));
        assertEquals(60, isle.getTresorery());
    }

    @Test
    public void should_make_max_food_mart() {
        assertEquals(12, isle.foodMart(15));
        assertEquals(4, isle.getTresorery());
    }

    @Test
    public void should_not_make_food_mart() {
        isle.decreaseTresorery(100);
        assertEquals(0, isle.foodMart(5));
    }

    @Test
    public void should_make_end_of_year_review() {
        isle.increaseFoodUnits(150);
        for(Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(25);
        }
        isle.endOfYearReview();
        assertEquals(200, isle.generateNumberTotalOfSupporters());
        assertEquals(150, isle.getFoodUnits());
        assertEquals(100, isle.getFactionList().get(7).getPercentageApproval());
    }

    @Test
    public void should_make_end_of_year_review_with_not_enough() {
        for(Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(26);
        }
        isle.endOfYearReview();
        assertEquals(200, isle.generateNumberTotalOfSupporters());
        assertEquals(0, isle.getFoodUnits());
        assertEquals(84, isle.getFactionList().get(7).getPercentageApproval());
    }

    @Test
    public void should_make_end_of_year_review_with_surplus() {
        for(Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(18);
        }
        isle.endOfYearReview();
        assertEquals(224, isle.getFoodUnits());
        assertEquals(100, isle.getFactionList().get(7).getPercentageApproval());
        assertTrue((isle.generateNumberTotalOfSupporters() > 144 && isle.generateNumberTotalOfSupporters() < 158));
    }

    @Test
    public void should_get_random_index_of_faction() {
        assertTrue((isle.getRandomIndexOfFaction() >= 0 && isle.getRandomIndexOfFaction() <= 7));
    }

    @Test
    public void should_generate_number_total_of_supporters() {
        for(Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(10);
        }
        assertEquals(80, isle.generateNumberTotalOfSupporters());
    }

    @Test
    public void should_set_trigger_coup() {
        for(Faction faction : isle.getFactionList()) {
            faction.increasePercentageApproval(70);
        }
        assertFalse(isle.triggerCoup());
    }

    @Test
    public void should_not_set_trigger_coup() {
        assertTrue(isle.triggerCoup());
    }

}