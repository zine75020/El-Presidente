package IndexOfFactionRecuperationMethod;

import Core.Faction.Faction;
import Core.Isle.Isle;

import java.util.List;

public class LessPartisansRepartitionMethod implements IndexOfFactionRecuperationMethod {

    @Override
    public int getIndexOfFactionByMethod(Isle isle) {

        List<Faction> factionList = isle.getFactionList();
        int indexSmallestFaction = 0;
        for (int i = 1; i < isle.getFactionList().size(); i++) {
            if (factionList.get(indexSmallestFaction).getNbSupporters() == 0 || (factionList.get(i).getNbSupporters() < factionList.get(indexSmallestFaction).getNbSupporters()
                    && factionList.get(i).getNbSupporters() > 0)) {
                indexSmallestFaction = i;
            }
        }
        return indexSmallestFaction;
    }
}
