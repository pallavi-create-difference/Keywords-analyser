package tech.codingclub.utility;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtility {
    public static boolean createFile(String path){
        File file=new File(path);
        boolean createF=false;
        try {

            createF=file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createF;
    }
    public static boolean writeInFile(String content, String path){



        // If file doesn't exists, then create it


        // Write in file

        FileWriter fw = null;
        BufferedWriter bw=null;

        try {
            File file = new File(path);
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            if (!file.exists()) {
                file.createNewFile();
            }


            bw.write(content);
        } catch (IOException e) {
            return false;
        }
        finally {
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        }
        return true;

    }
    public static ArrayList<String> readFile(String fileName){
        ArrayList<String> strings=new ArrayList<>();
        Scanner scanner=null;
        try{
            File file=new File(fileName);
            scanner=new Scanner(file);
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                strings.add(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
                if(scanner!=null){
                    scanner.close();
                }
    }
        return strings;
    }

    public static void main(String[] args) {
        String filePath="C:\\Users\\PALLAVI GUPTA\\IdeaProjects\\Hello\\data\\practice\\file"+"create-file.txt";
      createFile(filePath);
      boolean val=writeInFile("adding to previous file on this device ",filePath);
        System.out.println("write in file :"+val);
    }
}
