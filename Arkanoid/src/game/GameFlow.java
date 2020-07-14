package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import animation.AnimationRunner;
import animation.EndPoints;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.StartGameEnum;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import levels.LevelSpecificationReader;
import sprites.SpriteCollection;

/**
 *
 * @author hadar
 *
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private CounterScore score;
    private CounterLives numberOfLives;
    private String situation;
    private List<String> newArgs;
    private boolean thereAreArgs;
    private HighScoresTable highScores;
    private File scoreFile;
    private int currentScore;
    private DialogManager dialog;
    private SetUpGame setUpGame;

    /**
     * constructor with no args.
     *
     * @param ar
     *            the given animation runner
     * @param ks
     *            the given keyboard sensor
     * @param sprites
     *            the given sprites
     * @param environment
     *            the given environment
     * @param scoreFile
     *            the given file
     * @param dialog
     *            the given dialog
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, SpriteCollection sprites, GameEnvironment environment,
            File scoreFile, DialogManager dialog) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.sprites = sprites;
        this.environment = environment;
        this.score = new CounterScore(0);
        this.numberOfLives = new CounterLives(7);
        this.thereAreArgs = false;
        this.scoreFile = scoreFile;
        this.highScores = HighScoresTable.loadFromFile(scoreFile);
        this.dialog = dialog;
    }

    /**
     * constructor with args.
     *
     * @param ar
     *            the given animation runner
     * @param ks
     *            the given keyboard sensor
     * @param sprites
     *            the given sprites
     * @param environment
     *            the given environment
     * @param newArgs
     *            the given args from the cmd
     * @param scoreFile
     *            the given file
     * @param dialog
     *            the given dialog
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, SpriteCollection sprites, GameEnvironment environment,
            List<String> newArgs, File scoreFile, DialogManager dialog) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.sprites = sprites;
        this.environment = environment;
        this.score = new CounterScore(0);
        this.numberOfLives = new CounterLives(7);
        this.newArgs = newArgs;
        this.thereAreArgs = true;
        this.scoreFile = scoreFile;
        this.highScores = HighScoresTable.loadFromFile(scoreFile);
        this.dialog = dialog;
    }

    /**
     *
     * @param levels
     *            a list of the levels game.
     * @throws IOException
     *             an exception;
     */
    public void runLevels(List<LevelInformation> levels) throws IOException {
        int countLevels = 0;
        this.setUpGame = new SetUpGame(this.animationRunner, this.keyboardSensor, highScores);

        StartGameEnum gameEnum = setUpGame.go();
        if (gameEnum == StartGameEnum.DONT_START) {
            return;
        }

        String definitionsPath = EndPoints.EASY_DEFINITIONS;
        if (gameEnum == StartGameEnum.START_EASY) {
            definitionsPath =  EndPoints.EASY_DEFINITIONS;
        } else {
            definitionsPath =  EndPoints.HARD_DEFINITIONS;
        }

        List<LevelInformation> list = new ArrayList<LevelInformation>();
        // File fileParent = new File("resources");
        // File[] childsOfResources = fileParent.listFiles();
        // File[] deepFiles = childsOfResources[2].listFiles();
        // String levelsFileName = null;
        // for (int j = 0; j < deepFiles.length; j++) {
        // if (deepFiles[j].getName().equals("level_definitions.txt")) {
        // levelsFileName = deepFiles[j].getName();
        // break;
        // }
        // }

//        String path = null;
//        if (this.thereAreArgs) {
//            path = newArgs.get(0);
//        } else {
//            path = "resources/definitions/level_definitions.txt";
//        }
        BufferedReader br = null;
        FileReader fr = null;

        fr = new FileReader(definitionsPath);
        br = new BufferedReader(fr);
//        if (this.thereAreArgs) {
//            path = "resources/";
//        } else {
//            path = "resources/";
//        }
        LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
        list = levelSpecificationReader.fromReader(br);
        br.close();
        fr.close();

        // for (int i = 0; i < list.size(); i++) {
        // levels.add(list.get(i));
        // }
        levels.addAll(list);

        for (LevelInformation levelInfo : levels) {
            countLevels++;
            GameLevel level = new GameLevel(this.sprites, this.environment, levelInfo, this.keyboardSensor,
                    this.animationRunner, this.score, this.numberOfLives);
            level.initialize();
            while (level.thereAreBlocks() && level.thereAreLives()) {
                level.playOneTurn();
            }
            if (!level.thereAreLives()) {
                this.situation = "lose";
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new EndScreen(this.situation, level.getScore())));
                this.currentScore = level.getScore().getValue();
                break;
            }
            if ((countLevels == levels.size())) {
                this.situation = "win";
                this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                        new EndScreen(this.situation, level.getScore())));
                this.currentScore = level.getScore().getValue();
                break;
            }
        }
        if ((this.highScores.getRank(this.currentScore) >= 1)
                && (this.highScores.getRank(this.currentScore) <= this.highScores.size())) {
            String namePlayer = this.dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo player = new ScoreInfo(namePlayer, this.currentScore);
            this.highScores.add(player);
            this.highScores.save(scoreFile);
        }
        this.animationRunner.run(
                new KeyPressStoppableAnimation(this.keyboardSensor, "space", new HighScoresAnimation(this.highScores)));
    }
}