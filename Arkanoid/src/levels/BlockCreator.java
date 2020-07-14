package levels;

import sprites.Block;

/**
 *
 * @author hadar
 *
 */
public interface BlockCreator {

    /**
     * @param xpos the value of x
     * @param ypos the value of y
     * @return a new block
     */
    Block create(int xpos, int ypos);
 }
