package util;

import com.sun.javafx.tk.quantum.EmbeddedScene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Florian on 09.08.14.
 */
public class SQLLogger {

    private File sqlLog;
    private BufferedWriter writer;

    public SQLLogger(){
        File logFolder = new File("/FCCSQLLogs");
        if(!logFolder.exists()){
            logFolder.mkdir();
        }
        File[] logs = logFolder.listFiles();
        sqlLog = new File(logFolder + "/log" + logs.length + ".txt");
        try{
            this.writer = new BufferedWriter(new FileWriter(sqlLog));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void writeLine(String line){
        try{
            writer.write(line);
            writer.newLine();
            writer.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        if(writer != null){
            try{
                writer.close();
                sqlLog.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
