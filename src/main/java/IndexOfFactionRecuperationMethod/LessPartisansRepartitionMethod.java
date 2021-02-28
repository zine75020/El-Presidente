package IndexOfFactionRecuperationMethod;

import Core.Faction.Faction;
import Core.Isle.Isle;

import java.util.List;

public class LessPartisansRepartitionMethod implements IndexOfFactionRecuperationMethod {


    @Override
    public int getIndexOfFactionByMethod(Isle isle) {

        List<Faction> factionList = isle.getFactionList();
        int i;
        int indexSmallestFaction = 9999;
        for(i = 0; i<isle.getFactionList().size();i++){
            if(factionList.get(i).getNbSupporters()< indexSmallestFaction){
                indexSmallestFaction = i;
            }
        }
        return i;
    }
}
