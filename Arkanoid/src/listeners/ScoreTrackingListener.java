package listeners;

import game.CounterScore;
import sprites.Ball;
import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public class ScoreTrackingListener implements HitListener {
   private CounterScore currentScore;

   /**
    * constructor.
    *
    * @param scoreCounter the given score counter
    */
   public ScoreTrackingListener(CounterScore scoreCounter) {
      this.currentScore = scoreCounter;
   }

   /**
    * @param beingHit a given block
    * @param hitter a given ball
    */
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHitPoints() == "X") {
           if (beingHit.beingHitBefore()) {
               this.currentScore.increase(10);
           } else {
               this.currentScore.increase(15);
           }
       } else {
           this.currentScore.increase(5);
       }
   }
}