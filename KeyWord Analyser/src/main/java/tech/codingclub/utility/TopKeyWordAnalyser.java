package tech.codingclub.utility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;

public class TopKeyWordAnalyser implements Runnable {

    private final String filePath;

    public TopKeyWordAnalyser(String filePath){
        this.filePath=filePath;
    }
    @Override
    public void run() {
        ArrayList<String> keyWordsFiledata=FileUtility.readFile(filePath);
        HashMap<String,Integer> keyWordCounter=new HashMap<>();
        for(String row:keyWordsFiledata){
            String[] keywords=row.split(" ");
            for(String keyword:keywords){
               String str= keyword.toLowerCase();
               if(!keyWordCounter.containsKey(str)){
                   keyWordCounter.put(str,1);
               }
               else{
                   int val=keyWordCounter.get(str);
                   keyWordCounter.put(str,val+1);
               }
            }
        }
        ArrayList<keywordCount> keywordCountArrayList=new ArrayList<>();
        for(String keyword: keyWordCounter.keySet()){
            keywordCountArrayList.add(new keywordCount(keyword,keyWordCounter.get(keyword)));
        }
        Collections.sort(keywordCountArrayList, new Comparator<keywordCount>() {
            @Override
            public int compare(keywordCount o1, keywordCount o2) {
                return o2.count-o1.count;
            }
        });
//        for(keywordCount keywords:keywordCountArrayList){
//            System.out.println(keywords.keyword+" "+keywords.count);
//        }
        String json=new Gson().toJson(keywordCountArrayList);
        System.out.println(json);

        String covertjson="{\"keyword\":\"tava\",\"count\":3}";

        keywordCount keywordCount=new Gson().fromJson(covertjson, keywordCount.class);
        System.out.println("converted to json  "+keywordCount.keyword+" : "+keywordCount.count);

        String covertjsonArray="[{'keyword':'tava','count':3},{'keyword':'word2','count':2}]";
        ArrayList<keywordCount> convertedArray=new Gson().fromJson(covertjsonArray,new TypeToken<ArrayList<keywordCount>>(){}
                .getType());
        System.out.println("ArrayList from Gson");
        for(keywordCount keywordCount1:convertedArray){
            System.out.println(keywordCount1.keyword+" : "+keywordCount1.count);
        }

    }

    public static void main(String[] args) {
        TaskManager taskManager=new TaskManager(1);
        taskManager.waittillqueueisfreeandadd(new TopKeyWordAnalyser("E:\\KeyWord Analyser\\data\\practice\\filecreate-file.txt"));
    }
}
