


import java.io.File;
import java.util.LinkedList;
import java.util.List;

import game.HighScoresTable;
import game.ScoreInfo;

/**
 *
 * @author hadar
 *
 */
public class TestTable {

    /**
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        List<ScoreInfo> list = new LinkedList<ScoreInfo>();

        ScoreInfo player1 = new ScoreInfo("Avi", 200);
        ScoreInfo player2 = new ScoreInfo("Yosi", 400);
        ScoreInfo player3 = new ScoreInfo("Tal", 130);
        ScoreInfo player4 = new ScoreInfo("Shay", 200);
        ScoreInfo player5 = new ScoreInfo("Chen", 20);
        ScoreInfo player6 = new ScoreInfo("David", 250);
        ScoreInfo player7 = new ScoreInfo("Yuval", 500);
        ScoreInfo player8 = new ScoreInfo("Ori", 2000);

        HighScoresTable table = new HighScoresTable(5);

        table.add(player1);
        table.add(player2);
        table.add(player3);
        table.add(player4);
        table.add(player5);
        table.add(player6);
        table.add(player7);
        table.add(player8);

        File file = new File("scores.txt");

//        try {
//            table.save(file);
//        } catch (Exception e) {
//        }

//      try {
//          table.load(file);
//      } catch (Exception e) {
//      }

        try {
            table = HighScoresTable.loadFromFile(file);
        } catch (Exception e) {
        }

      list = table.getHighScores();

        for (int i = 0; i < table.size(); i++) {
            if (list.get(i) != null) {
                System.out.println(list.get(i).getName() + " " + list.get(i).getScore());
            }
        }
    }
}
