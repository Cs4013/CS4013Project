package com.cs4013.Misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
   private String filename = "";

   /**
    * This class takes any file path @param filename and does various operations
    * @method readCSV reads a CSV file and stores the data in a 2d Arraylist
    * @method readLines reads the file line by line and  stores the data in an Arraylist
    * */
   public FileManager(String filename){
       this.filename = filename;
   }

   /**
    * @method readCsv we create an array list and call the instance of the array list then we check if it exists
    * we read the file
    * */

   public ArrayList<ArrayList<String>> readCsv() throws IOException {
       ArrayList<ArrayList<String>> data= new ArrayList<>();
       File file = new File("./src/com/cs4013/Misc/"+filename);

       if(file.exists()){

           Scanner fileReader= new Scanner(file);

           while(fileReader.hasNext()){

               ArrayList<String> temp = new ArrayList<>();
               String line = fileReader.nextLine();
               for(String s : line.split(",")){
                   temp.add(s);
               }
               data.add(temp);
           }
           fileReader.close();
       }else{
           TerminalLogger.logError("File not found: "+filename);
       }

       return data;
   }
    public ArrayList<String> readLine() throws IOException {
        ArrayList<String> data= new ArrayList<>();
        File file = new File("./src/com/cs4013/Misc/"+filename);
        if(file.exists()){

            Scanner fileReader= new Scanner(file);

            while(fileReader.hasNext()){
                //apple
                //orange
                //banana
                String line = fileReader.nextLine();
                data.add(line);
            }
            fileReader.close();
        }else{
            TerminalLogger.logError("File not found: "+filename);
        }

        return data;
    }
}