package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.Fill;
import levels.LevelInformation;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.Ball;
import sprites.Block;
import sprites.Collidable;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 *
 * @author hadar
 *
 */
public class GameLevel implements Animation {
   private AnimationRunner runner;
   private boolean running;
   private SpriteCollection sprites;
   private GameEnvironment environment;
   private CounterBlocks counterBlocks;
   private CounterBalls counterBalls;
   private CounterScore score;
   private CounterLives numberOfLives;
   private BlockRemover blockRemover;
   private BallRemover ballRemover;
   private ScoreTrackingListener scoreTrackingListener;
   private Paddle paddle;
   private KeyboardSensor keyboard;
   private LevelInformation level;
   private int numberOfBlocks;
   private List<Ball> balls;
   private List<Block> blocks;
   private List<Fill> fill;

   /**
    * constructor.
    *
    * @param sprites the given sprites
    * @param environment the given environment
    * @param level the given level
    * @param keyboard the given keyboard
    * @param runner the given runner
    * @param score the given score
    * @param numberOfLives the given numberOfLives
    */
   public GameLevel(SpriteCollection sprites, GameEnvironment environment,
           LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner,
           CounterScore score, CounterLives numberOfLives) {
       this.sprites = sprites;
       this.environment = environment;
       this.level = level;
       this.keyboard = keyboard;
       this.runner = runner;
       this.score = score;
       this.numberOfLives = numberOfLives;
       this.balls = new ArrayList<Ball>();
       this.blocks = this.level.blocks();
       this.numberOfBlocks = blocks.size();
   }

   /**
    *  add the given collidable to the game.
    *
    * @param c a given collidable
    */
   public void addCollidable(Collidable c) {
       this.environment.addCollidable(c);
   }

   /**
    *  add the given sprite to the game.
    *
    * @param s a given sprite
    */
   public void addSprite(Sprite s) {
       this.sprites.addSprite(s);
   }

   /**
    * Initialize a new game.
    */
   public void initialize() {
       sprites.addSprite(this.level.getBackground());
       this.counterBlocks = new CounterBlocks(0);
       this.counterBalls = new CounterBalls(0);
       this.blockRemover = new BlockRemover(this, counterBlocks);
       this.ballRemover = new BallRemover(this, counterBalls);
       this.scoreTrackingListener = new ScoreTrackingListener(score);

       int guiWidth = 800;
       int guiHeight = 600;
       ArrayList<Block> blocksAround = new ArrayList<Block>();

       ScoreIndicator scoreIndicator = new ScoreIndicator(score);
       LivesIndicator livesIndicator = new LivesIndicator(numberOfLives);
       scoreIndicator.addToGame(this);
       livesIndicator.addToGame(this);

       scoreIndicator.addHitListener(scoreTrackingListener);

       int randomNumber = 25;
       Block upperBlock = new Block(new Rectangle(new Point(0, 20), guiWidth, randomNumber),
               Color.gray, "X", this.fill);
       Block rightBlock = new Block(new Rectangle(new Point(guiWidth - randomNumber, 20), randomNumber, guiHeight),
               Color.gray, "X", this.fill);
       Block leftBlock = new Block(new Rectangle(new Point(0, 20), randomNumber, guiHeight),
               Color.gray, "X", this.fill);
       Block deathBlock = new Block(new Rectangle(new Point(0, guiHeight), guiWidth, randomNumber),
               Color.gray, "3", this.fill);

       deathBlock.addHitListener(ballRemover);

       blocksAround.add(upperBlock);
       blocksAround.add(rightBlock);
       blocksAround.add(leftBlock);
       blocksAround.add(deathBlock);

       for (int i = 0; i < blocksAround.size(); i++) {
           blocksAround.get(i).addToGame(this);
       }

       for (int i = 0; i < numberOfBlocks; i++) {
           blocks.get(i).addToGame(this);
           blocks.get(i).addHitListener(blockRemover);
           blocks.get(i).addHitListener(scoreTrackingListener);
           this.counterBlocks.increase(1);
       }
   }

   /**
    * play one turn.
    */
   public void playOneTurn() {
       this.createBallsOnTopOfPaddle();
       this.runner.run(new CountdownAnimation(2000, 3, this.sprites));
       this.running = true;
       this.runner.run(this);
   }

   /**
    *
    * @param c the collidable
    */
   public void removeCollidable(Collidable c) {
       this.environment.removeTheCollidable(c);
   }

   /**
    *
    * @param s the sprite
    */
   public void removeSprite(Sprite s) {
       this.sprites.removeTheSprite(s);
   }

   /**
    * @return if the loop should stop.
    */
   @Override
   public boolean shouldStop() {
       return (!this.running);
   }

   /**
    * @param d the given drawSurface.
    * @param dt number of seconds.
    */
   @Override
   public void doOneFrame(DrawSurface d, double dt) {
       this.sprites.drawAllOn(d);
       this.sprites.notifyAllTimePassed(dt);

       if (this.counterBalls.getValue() == 0) {
           this.numberOfLives.decrease(1);
           this.paddle.removeFromGame(this);
           this.running = false;
       }
       if (!this.thereAreBlocks()) {
           this.score.increase(100);
           this.running = false;
           sprites.removeTheSprite(this.level.getBackground());

           this.removeBallsFromGame();
           this.paddle.removeFromGame(this);

           for (int i = 0; i < numberOfBlocks; i++) {
               blocks.get(i).removeFromGame(this);
           }
       }
       if (this.keyboard.isPressed("p")) {
           this.runner.run(new PauseScreen(this.keyboard));
       }
    }

   /**
    * creates the balls and the paddle.
    */
   public void createBallsOnTopOfPaddle() {
       int guiWidth = 800;
       int guiHeight = 600;

       Rectangle paddleRect = new Rectangle(new Point(guiWidth / 2 - (this.level.paddleWidth() / 2) - 10,
               guiHeight - 35), this.level.paddleWidth(), 20);
       this.paddle = new Paddle(keyboard, paddleRect);
       this.paddle.addToGame(this);

       for (int i = 0; i < this.level.numberOfBalls(); i++) {
           Ball ball = new Ball(392, 480, 5, java.awt.Color.white, this.environment);
           ball.addToGame(this);
           ball.setVelocity(this.level.initialBallVelocities().get(i));
           this.balls.add(ball);
       }
       this.counterBalls.increase(this.level.numberOfBalls());
   }

   /**
    * @return if the game has more blocks.
    */
   public boolean thereAreBlocks() {
       return (this.counterBlocks.getValue() > this.blocks.size() - this.level.numberOfBlocksToRemove());
   }

   /**
    * @return if the game has more lives.
    */
   public boolean thereAreLives() {
       return (this.numberOfLives.getValue() != 0);
   }

   /**
    * @return the score.
    */
   public CounterScore getScore() {
       return this.score;
   }

   /**
    * removes balls from the game.
    */
   public void removeBallsFromGame() {
       if (this.balls != null && !this.balls.isEmpty()) {
           for (Ball ball : balls) {
               ball.removeFromGame(this);
           }
       }
   }

   /**
    * @return the fill.
    */
   public List<Fill> fill() {
       return this.fill;
   }
}