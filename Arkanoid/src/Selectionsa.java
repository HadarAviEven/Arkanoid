

import java.util.HashMap;
import java.util.Map;

import animation.Animation;

/**
 *
 * @author hadar
 *
 */
public class Selectionsa {
    private Map<String, Animation> selections;
    private Animation startNewGameTask;
    private Animation showHiScoresTask;
    private Animation quitTask;

    /**
     *
     */
    public Selectionsa(Animation startNewGameTask, Animation showHiScoresTask, Animation quitTask) {
        this.selections = new HashMap<String, Animation>();
        this.startNewGameTask = startNewGameTask;
        this.showHiScoresTask = showHiScoresTask;
        this.quitTask = quitTask;


//        vehicles.put("s", 5);
//        vehicles.put("h", 3);
//        vehicles.put("q", 4);
//        vehicles.put("Ford", 10);
//        System.out.println("Total vehicles: " + vehicles.size());
//        for(String key: vehicles.keySet()) {
//            System.out.println(key + " - " + vehicles.get(key));
//        }
//        System.out.println();
//        String searchKey = "Audi";
//        if(vehicles.containsKey(searchKey))
//            System.out.println("Found total " + vehicles.get(searchKey) + " "
//                    + searchKey + " cars!\n");
//        vehicles.clear();
//        System.out.println("After clear operation, size: " + vehicles.size());
    }

    /**
     *
     */
    public void add() {

    }
}