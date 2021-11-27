package com.cs4013.Misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
   private String filename = "";

   public FileManager(String filename){
       this.filename = filename;
   }

   public ArrayList<ArrayList<String>> readCsv() throws IOException {
       ArrayList<ArrayList<String>> data= new ArrayList<>();

       File file = new File(filename);

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
       }

       return data;
   }
    public ArrayList<String> readLine() throws IOException {
        ArrayList<String> data= new ArrayList<>();

        File file = new File(filename);

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
        }

        return data;
    }
}