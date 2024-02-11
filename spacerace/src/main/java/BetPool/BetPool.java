package BetPool;

import Main.Constants;

/**
 * pool of money that can be won
 * @author anton byström
 */
public class BetPool {
    public static final BetPool INSTANCE = new BetPool();
    //private final AtomicInteger betPool = new AtomicInteger(Constants.BET_POOL_SIZE);
    private volatile int betPool = Constants.BET_POOL_SIZE;

    /**
     * returns the amount left in betPool
     * @return betPool
     */
    public int getBetPool () {
        return betPool;
    }
    /**
     * amount to be added to the pool
     * @param amount amount
     */
    public synchronized void addBet(int amount) {
        betPool += amount;
    }

    /**
     * amount to be removed from the pool
     * @param amount amount
     */
    public synchronized void removeBet(int amount) {
        betPool += -amount;
    }
}
