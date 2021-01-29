package Faction;

import org.junit.Test;
import Enum.FactionType;

import static org.junit.jupiter.api.Assertions.*;

public class FactionTest {

    Faction faction = new Faction(FactionType.COMMUNISTS, 15, 80);
    Faction faction2 = new Faction(FactionType.COMMUNISTS, 0, 100);
    Faction faction3 = new Faction(FactionType.COMMUNISTS, 15, 0);

    @Test
    public void should_increase_nb_supporters() {
        faction.increaseNbSupporters(10);

        assertEquals(25, faction.getNbSupporters());
    }

    @Test
    public void should_decrease_nb_supporters() {
        faction.decreaseNbSupporters(10);

        assertEquals(5, faction.getNbSupporters());
    }

    @Test
    public void should_decrease_nb_supporters_and_limit_at_0() {
        faction.decreaseNbSupporters(25);

        assertEquals(0, faction.getNbSupporters());
    }

    @Test
    public void should_not_decrease_nb_supporters_because_its_at_0() {
        faction2.decreaseNbSupporters(15);

        assertEquals(0, faction2.getNbSupporters());
    }

    @Test
    public void should_increase_percentage_approval() {
        faction.increasePercentageApproval(10);

        assertEquals(90, faction.getPercentageApproval());
    }

    @Test
    public void should_increase_percentage_approval_and_limit_at_100() {
        faction.increasePercentageApproval(50);

        assertEquals(100, faction.getPercentageApproval());
    }

    @Test
    public void should_not_increase_percentage_approval_because_its_at_100() {
        faction2.increasePercentageApproval(15);

        assertEquals(100, faction2.getPercentageApproval());
    }

    @Test
    public void should_decrease_percentage_approval() {
        faction.decreasePercentageApproval(20);

        assertEquals(60, faction.getPercentageApproval());
    }

    @Test
    public void should_decrease_percentage_approval_and_limit_at_0() {
        faction.decreasePercentageApproval(100);

        assertEquals(0, faction.getPercentageApproval());
    }

    @Test
    public void should_not_decrease_percentage_approval_because_its_at_0() {
        faction3.decreasePercentageApproval(10);

        assertEquals(0, faction3.getPercentageApproval());
    }

    @Test
    public void should_is_percentage_approval_at_0() {
        faction.decreasePercentageApproval(80);

        assertTrue(faction.isPercentageApprovalAt0());
    }

    @Test
    public void should_is_percentage_approval_at_0_when_it_could_be_negative() {
        faction.decreasePercentageApproval(100);

        assertTrue(faction.isPercentageApprovalAt0());
    }

    @Test
    public void should_not_is_percentage_approval_at_0() {
        faction.decreasePercentageApproval(50);

        assertFalse(faction.isPercentageApprovalAt0());
    }

}
