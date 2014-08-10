package util;

import com.avaje.ebeaninternal.server.transaction.TransactionMap;
import com.mysql.jdbc.Util;

import java.io.File;
import java.sql.*;

/**
 * Created by Florian on 09.08.14.
 */

public class SQLManager {

    private SQLLogger logger;
    private Connection c;
    private Statement stmt;

    public SQLManager(File folder){

        this.logger = new SQLLogger();

        final String DB_FOLDER = folder.getPath() + "/sql";
        final String DB_PATH = folder.getPath() + "/sql/plotsystem.db";

        File dbfolderFile = new File(DB_FOLDER);

        if(!dbfolderFile.exists()){
            dbfolderFile.mkdirs();
        }

        logger.writeLine("DatabaseFolder: " + DB_FOLDER);
        logger.writeLine("DatabaseFolder Exists: " + dbfolderFile.exists());
        logger.writeLine("DatabasePath: " + DB_PATH);

        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
            if(!c.isClosed()){
                logger.writeLine("Connection Established!");
            }
            logger.writeLine("Connection closed: " + c.isClosed());
        }catch (SQLException e){
            e.printStackTrace();
            try{
                Thread.sleep(6000);
            }catch (Exception e3){
                e3.printStackTrace();
            }
        }catch (ClassNotFoundException e2){
            e2.printStackTrace();
            try{
                Thread.sleep(6000);
            }catch (Exception e){
                e2.printStackTrace();
            }
        }
    }

    public void addPlayer(String playerName){
        try{
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + playerName + " " +
                         "(plot1 TEXT , " +
                         " plot2 TEXT);";
            logger.writeLine(sql);
            stmt.executeUpdate(sql);
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String selectPlot(String player, int plotID){
        try{
            stmt = c.createStatement();
            if(Utils.beginsWithNumber(player)){
                player = player.replace("FCCERR_", "");
            }
            String sql = "SELECT " + "plot" + plotID + " FROM " + player + ";";
            logger.writeLine(sql);
            ResultSet set = stmt.executeQuery(sql);
            String plot = set.getString("plot" + plotID);
            set.close();
            stmt.close();
            if(plot.equalsIgnoreCase("")){
                plot = "null";
            }
            logger.writeLine("Plot" + plotID + " from " + player + ": " + plot);
            logger.writeLine("");
            return plot;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void addPlot(String playerName, String plotID){
        if(selectPlot(playerName, 1).equalsIgnoreCase("null")){

        }else if(selectPlot(playerName, 2).equalsIgnoreCase("null")){

        }else{

        }
    }

    public void close(){
        try{
            c.close();
            logger.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
