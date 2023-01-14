package replayrecord;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class RecordsCreator {
    //this class should change the string into a list of objects records
    private String recordList;
    ArrayList<String> listOfRecords;
    ArrayList<Record>listOfRecordObjects;

    public void setRecordList(String recordList) {
        this.recordList = recordList;
    }

    public void splitToRecords(){
        for (int i=0; i<recordList.length();i++){
            StringTokenizer mainMsg= new StringTokenizer(recordList, "/");
            while(mainMsg.hasMoreTokens()){
                listOfRecords.add(mainMsg.nextToken());
            }
        }
    }

    public void toRecordObjects(){
        for (int i=0; i<listOfRecords.size();i++){
            Record rec = new Record();
            for (String j :listOfRecords){
            StringTokenizer subMsg = new StringTokenizer(listOfRecords.get(i), ",");
            while(subMsg.hasMoreTokens()) {
                System.out.println(subMsg.nextToken());

            }
            }
        }
    }

}
