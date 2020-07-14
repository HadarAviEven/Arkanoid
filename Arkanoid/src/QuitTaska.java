

import animation.Animation;
import animation.AnimationRunner;
import game.Task;

/**
 *
 * @author hadar
 *
 */
public class QuitTaska implements Task<Void> {
    private AnimationRunner runner;
    private Animation endScreenAnimation;

    /**
     * constructor.
     *
     * @param runner the given runner
     * @param highScoresAnimation the given high scores
     */
    public QuitTaska(AnimationRunner runner, Animation endScreenAnimation) {
       this.runner = runner;
       this.endScreenAnimation = endScreenAnimation;
    }

    /**
     * @return null
     */
    public Void run() {
       this.runner.run(this.endScreenAnimation);
       return null;
    }
 }
