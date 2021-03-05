package IndexOfFactionRecuperationMethod;

import Core.Faction.Faction;
import Core.Isle.Isle;

import java.util.List;

public class MostPartisansRepartitionMethod implements IndexOfFactionRecuperationMethod{

    @Override
    public int getIndexOfFactionByMethod(Isle isle) {
        List<Faction> factionList = isle.getFactionList();

        int indexBiggestFaction = 0;

        for (int i = 1; i < isle.getFactionList().size(); i++) {
            if (factionList.get(i).getNbSupporters() > factionList.get(indexBiggestFaction).getNbSupporters()) {
                indexBiggestFaction = i;
            }
        }
        return indexBiggestFaction;
    }
}
