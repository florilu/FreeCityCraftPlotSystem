package util;

import java.util.ArrayList;

/**
 * Created by Florian on 13.06.14.
 */
public class PlotListsList {

    public ArrayList<String> mainList = new ArrayList<>();

    public String plotType = null;
    public String alias = null;


    public PlotListsList(String plotType, String alias){
        this.plotType = plotType;
        this.alias = alias;
    }

    public String getListType(){
        return this.plotType;
    }

    public void resetList(){
        this.mainList = new ArrayList<>();
    }
}
