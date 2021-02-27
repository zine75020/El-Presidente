package Core.Isle;

import Core.Agriculture.Agriculture;
import Core.Enum.DifficultyChoice;
import Core.Enum.FactionType;
import Core.Enum.Season;
import Core.Faction.Faction;
import Core.Industry.Industry;
import org.junit.Test;

import static org.junit.Assert.*;


public class IsleTest {

    Isle isle = new Isle(new Industry(10), new Agriculture(20),
            100, DifficultyChoice.normal, 0, 30);
    Isle isle2 = new Isle(new Industry(10), new Agriculture(20),
            0, DifficultyChoice.normal, 0, 30);

    @Test
    public void should_have_8_factions() {
        assertEquals(8, isle.getFactionList().size());
    }

    @Test
    public void should_increase_treasury() {
        isle.increaseTreasury(100);

        assertEquals(200, (int) isle.getTreasury());
    }

    @Test
    public void should_decrease_treasury() {
        isle.decreaseTreasury(50);

        assertEquals(50, (int) isle.getTreasury());
    }

    @Test
    public void should_decrease_treasury_and_limit_at_0() {
        isle.decreaseTreasury(120);

        assertEquals(0, (int) isle.getTreasury());
    }

    @Test
    public void should_not_decrease_treasury_because_its_at_0() {
        isle2.decreaseTreasury(20);

        assertEquals(0, (int) isle2.getTreasury());
    }

    @Test
    public void should_increase_food_units() {
        isle.increaseFoodUnits(100);

        assertEquals(100, (int) isle.getFoodUnits());
    }

    @Test
    public void should_decrease_food_units() {
        isle.increaseFoodUnits(50);
        isle.decreaseFoodUnits(10);

        assertEquals(40, (int) isle.getFoodUnits());
    }

    @Test
    public void should_decrease_food_units_and_limit_at_0() {
        isle.increaseFoodUnits(20);
        isle.decreaseFoodUnits(40);

        assertEquals(0, (int) isle.getFoodUnits());
    }

    @Test
    public void should_not_decrease_food_units_because_its_at_0() {
        isle2.decreaseFoodUnits(20);

        assertEquals(0, (int) isle2.getFoodUnits());
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

        assertEquals(1, (int) isle.getTurn());
    }

    @Test
    public void should_set_score() {
        assertTrue(isle.generateScore() > 0);
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
        isle.increaseTreasury(150);
        assertTrue(isle.bribe(3));
        assertEquals(100, (int) isle.getTreasury());
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
        assertEquals(60, (int) isle.getTreasury());
    }

    @Test
    public void should_make_max_food_mart() {
        assertEquals(12, isle.foodMart(15));
        assertEquals(4, (int) isle.getTreasury());
    }

    @Test
    public void should_not_make_food_mart() {
        isle.decreaseTreasury(100);
        assertEquals(0, isle.foodMart(5));
    }

    @Test
    public void should_make_end_of_year_review() {
        isle.increaseFoodUnits(150);
        for (Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(25);
        }
        isle.endOfYearReview();
        assertEquals(200, isle.generateNumberTotalOfPartisans());
        assertEquals(150, (int) isle.getFoodUnits());
        assertEquals(100, isle.getFactionList().get(7).getPercentageApproval());
    }

    @Test
    public void should_make_end_of_year_review_with_not_enough() {
        for (Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(26);
        }
        isle.endOfYearReview();
        assertEquals(200, isle.generateNumberTotalOfPartisans());
        assertEquals(0, (int) isle.getFoodUnits());
        assertEquals(84, isle.getFactionList().get(7).getPercentageApproval());
    }

    @Test
    public void should_make_end_of_year_review_with_surplus() {
        for (Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(18);
        }
        isle.endOfYearReview();
        assertEquals(224, (int) isle.getFoodUnits());
        assertEquals(100, isle.getFactionList().get(7).getPercentageApproval());
        assertTrue((isle.generateNumberTotalOfPartisans() > 144 && isle.generateNumberTotalOfPartisans() < 158));
    }

    @Test
    public void should_generate_number_total_of_supporters() {
        for (Faction faction : isle.getFactionList()) {
            faction.increaseNbSupporters(10);
        }
        assertEquals(80, isle.generateNumberTotalOfPartisans());
    }

    @Test
    public void should_set_trigger_coup() {
        for (Faction faction : isle.getFactionList()) {
            faction.increasePercentageApproval(70);
        }
        assertFalse(isle.triggerCoup());
    }

    @Test
    public void should_not_set_trigger_coup() {
        assertTrue(isle.triggerCoup());
    }

    @Test
    public void should_bribe_is_possible() {
        isle.increaseTreasury(100);
        for(Faction faction : isle.getFactionList()) {
            faction.setNbSupporters(4);
        }
        assertTrue(isle.bribeIsPossible());
    }

    @Test
    public void should_bribe_is_not_possible() {
        isle.decreaseTreasury(10000);
        for(Faction faction : isle.getFactionList()) {
            faction.setNbSupporters(4);
        }
        assertFalse(isle.bribeIsPossible());
    }

    @Test
    public void should_bribe_is_possible_for_factions() {
        isle.increaseTreasury(100);
        for(Faction faction : isle.getFactionList()) {
            faction.setNbSupporters(4);
        }
        for(Faction faction : isle.getFactionList()) {
            assertTrue(isle.bribeIsPossible(faction));
        }
    }

    @Test
    public void should_bribe_is_not_possible_for_factions() {
        isle.decreaseTreasury(10000);
        for(Faction faction : isle.getFactionList()) {
            faction.setNbSupporters(4);
        }
        for(Faction faction : isle.getFactionList()) {
            assertFalse(isle.bribeIsPossible(faction));
        }
    }

    @Test
    public void should_food_mart_is_possible() {
        assertTrue(isle.foodMartIsPossible());
    }

    @Test
    public void should_food_mart_is_not_possible() {
        isle.decreaseTreasury(10000);
        assertFalse(isle.foodMartIsPossible());
    }

    @Test
    public void should_increase_partisans() {
        isle.increasePartisans(10);
        assertEquals(10, isle.generateNumberTotalOfPartisans());
    }

    @Test
    public void should_decrease_partisans() {
        isle.increasePartisans(10);
        isle.decreasePartisans(8);
        assertEquals(2, isle.generateNumberTotalOfPartisans());
        isle.decreasePartisans(4);
        assertEquals(0, isle.generateNumberTotalOfPartisans());
    }

    @Test
    public void should_get_faction_by_faction_type() {
        assertEquals(isle.getFactionList().get(0).getName(), FactionType.CAPITALISTS);
        assertEquals(isle.getFactionList().get(1).getName(), FactionType.COMMUNISTS);
        assertEquals(isle.getFactionList().get(2).getName(), FactionType.LIBERALS);
        assertEquals(isle.getFactionList().get(3).getName(), FactionType.RELIGIOUS);
        assertEquals(isle.getFactionList().get(4).getName(), FactionType.MILITARISTS);
        assertEquals(isle.getFactionList().get(5).getName(), FactionType.ECOLOGISTS);
        assertEquals(isle.getFactionList().get(6).getName(), FactionType.NATIONALISTS);
        assertEquals(isle.getFactionList().get(7).getName(), FactionType.LOYALISTS);
    }

}
