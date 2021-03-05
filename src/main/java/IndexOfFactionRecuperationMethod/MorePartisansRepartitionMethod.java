package IndexOfFactionRecuperationMethod;

import Core.Faction.Faction;
import Core.Isle.Isle;

import java.util.List;

public class MorePartisansRepartitionMethod implements IndexOfFactionRecuperationMethod{

    @Override
    public int getIndexOfFactionByMethod(Isle isle) {
        List<Faction> factionList = isle.getFactionList();

        int indexBigestFaction = 0;

        for (int i = 1; i < isle.getFactionList().size(); i++) {
            if (factionList.get(i).getNbSupporters() > factionList.get(indexBigestFaction).getNbSupporters()) {
                indexBigestFaction = i;
            }
        }
        return indexBigestFaction;
    }
}
