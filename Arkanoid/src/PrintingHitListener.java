

import listeners.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public class PrintingHitListener implements HitListener {
    /**
     * @param beingHit a given block
     * @param hitter a given ball
     */
   @Override
   public void hitEvent(Block beingHit, Ball hitter) {
       System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
   }
}