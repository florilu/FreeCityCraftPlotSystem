package util;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.domains.Domain;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;
import sun.tools.jar.resources.jar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Florian on 12.06.14.
 */
public class ListManager {

    public ArrayList<PlotListsList> lists = new ArrayList<>();
    private ArrayList<ProtectedRegion> protectedRegions = new ArrayList<>();
    private WorldGuardPlugin worldGuard = null;

    private Logger logger;

    public ListManager(WorldGuardPlugin worldGuard, Logger logger){
        this.worldGuard = worldGuard;
        this.logger = logger;
    }

    public void addListType(String type, String alias){
        lists.add(new PlotListsList(type, alias));
    }

    public void runIdentification(World world){
        protectedRegions = new ArrayList<>();
        for(int i = 0; i < lists.size(); i++){
            lists.get(i).resetList();
        }
        Map<String, ProtectedRegion> regionStringMap = worldGuard.getRegionManager(world).getRegions();
        for(Map.Entry<String, ProtectedRegion> entry : regionStringMap.entrySet()){
            protectedRegions.add(entry.getValue());
        }
        for(int i = 0; i < lists.size(); i++){
            for(int j = 0; j < protectedRegions.size(); j++){
                PlotListsList plotList = lists.get(i);
                ProtectedRegion region = protectedRegions.get(j);
                if(!region.getId().equals("stadt")){
                    if(region.getId().length() - 1 > plotList.alias.length() - 1){
                        if(region.getId().contains(plotList.alias) && !Character.isLetter(region.getId().charAt(plotList.alias.length()))){
                            DefaultDomain domain = region.getOwners();
                            if(domain.size() > 0){
                                plotList.mainList.add("§4" + region.getId() +  "§f, ");
                            }else{
                                plotList.mainList.add("§a" + region.getId() + "§f, ");
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < lists.size(); i++){
            PlotListsList newList = lists.get(i);
            Collections.sort(newList.mainList);
        }
    }

    public ArrayList<PlotListsList> getLists(){
        return this.lists;
    }
}
