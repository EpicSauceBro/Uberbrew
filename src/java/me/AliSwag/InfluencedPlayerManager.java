package me.AliSwag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/**
 * Created by Ali on 30/06/2014.
 */
public class InfluencedPlayerManager {

    public static List<InfluencedPlayer> influencedPlayerList = new ArrayList<InfluencedPlayer>();

    private static Formatter x;

    public static void dump(){
        createDumpFile();
        openFile();
    }

    public static void load(){

    }

    private static void createDumpFile(){
        final Formatter w;

        try{
            w = new Formatter("InfluencedPlayers.dump");
        }catch (Exception e){
            System.out.println("FAILED TO DUMP INFLUENCED PLAYERS!!! NEXT TIME SERVER STARTS IT WILL NOT BE ABLE TO LOAD THEM");
        }
    }

    private static void openFile(){
        try{
            x = new Formatter("InfluencedPlayers.dump");
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND!!!!!!!!");
        }
    }

}
