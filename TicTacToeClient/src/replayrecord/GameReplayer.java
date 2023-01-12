package replayrecord;

import models.Player;

import java.sql.SQLOutput;
import java.util.Arrays;

public class GameReplayer {
 //awel 7aga agrab l ta2te3 wl donia
    Record rec = new Record();
    Player player;
    static String gameOne = "1,4,5,8,9";
  ///check if array
    public static int[] createArrayMove() {
           return Arrays.stream(gameOne.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
    }

    public static void main(String[] args) {
        for(int i: createArrayMove()){
            System.out.println(i);
        }

    }

}
