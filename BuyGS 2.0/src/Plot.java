/**
 * Created by Florian on 03.06.14.
 */
public class Plot {
    private String plotType = null;
    private String plotCost = null;
    private String permission = null;
    private String alias = null;

    public Plot(String plotType, String alias, String plotCost, String permission){
        this.plotType = plotType;
        this.plotCost = plotCost;
        this.permission = permission;
        this.alias = alias;
    }

    public String getPlotType(){
        return plotType;
    }

    public int getPlotCost(){
        return Integer.valueOf(plotCost);
    }

    public String getPermission(){
        return permission;
    }

    public String getAlias(){
        return this.alias;
    }

    public String toString(){
        return this.plotType + "; " + this.plotCost + "; " + this.permission + "; " + this.alias;
    }
}
