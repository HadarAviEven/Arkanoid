

import animation.Animation;
import animation.AnimationRunner;
import game.Task;

/**
 *
 * @author hadar
 *
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;

    /**
     * constructor.
     *
     * @param runner the given runner
     * @param highScoresAnimation the given high scores
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation) {
       this.runner = runner;
       this.highScoresAnimation = highScoresAnimation;
    }

    /**
     * @return null
     */
    @Override
    public Void run() {
       this.runner.run(this.highScoresAnimation);
       return null;
    }
 }
