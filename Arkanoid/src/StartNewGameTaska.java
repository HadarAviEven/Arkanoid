

import animation.Animation;
import animation.AnimationRunner;
import game.Task;

/**
 *
 * @author hadar
 *
 */
public class StartNewGameTaska implements Task<Void> {
    private AnimationRunner runner;
    private Animation gameAnimation;

    /**
     * constructor.
     *
     * @param runner the given runner
     * @param highScoresAnimation the given high scores
     */
    public StartNewGameTaska(AnimationRunner runner, Animation gameAnimation) {
       this.runner = runner;
       this.gameAnimation = gameAnimation;
    }

    /**
     * @return null
     */
    public Void run() {
       this.runner.run(this.gameAnimation);
       return null;
    }
 }