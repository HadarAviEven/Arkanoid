
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameEnvironment;
import game.GameFlow;
import levels.LevelInformation;
import sprites.SpriteCollection;

/**
*
* @author hadar
*
*/
public class Ass6Game {

    /**
    *
    * @param args parameters from the command line
    * @throws IOException an exception;
    */
   public static void main(String[] args) throws IOException {
//       args = new String[]{"resources/definitions/level_definitions.txt"};
       GUI gui = new GUI("Arkanoid", 800, 600);
       while (true) {
           KeyboardSensor keyboard = gui.getKeyboardSensor();
           AnimationRunner runner = new AnimationRunner(gui);
           SpriteCollection sprites = new SpriteCollection();
           GameEnvironment environment = new GameEnvironment();
           List<LevelInformation> levels = new ArrayList<LevelInformation>();
           File scoreFile = new File("highscores.txt");
           DialogManager dialog = gui.getDialogManager();
           if (args.length == 0) {
               GameFlow gameFlow = new GameFlow(runner, keyboard, sprites, environment, scoreFile, dialog);
               gameFlow.runLevels(levels);
               gui.close();
           } else {
               List<String> newArgs = new ArrayList<String>();
               for (int i = 0; i < args.length; i++) {
                   newArgs.add(args[i]);
               }
               GameFlow gameFlow = new GameFlow(runner, keyboard, sprites, environment, newArgs, scoreFile, dialog);
               gameFlow.runLevels(levels);
           }
       }
    }
}