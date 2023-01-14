package replayrecord;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class RecordsCreator {
    //this class should change the string into a list of objects records
    private String recordList;
    ArrayList<String> listOfRecords;
    ArrayList<String> record;

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

    public List<String> getRecord(int index){
            StringTokenizer subMsg = new StringTokenizer(listOfRecords.get(index), "-");
            while(subMsg.hasMoreTokens()) {
               record.add(subMsg.nextToken());
            }
            return record;
    }



}
