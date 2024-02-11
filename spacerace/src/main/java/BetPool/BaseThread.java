package BetPool;

/**
 * BaseThread used by consumer/producer to add/remove values to the threadPool
 * @author anton Byström
 */
public abstract class BaseThread implements Runnable{
    BetPool instance = BetPool.INSTANCE;
    volatile boolean threadAlive = false;

    /**
     * puts the thread to sleep
     */
    protected void sleep(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Kills the thread
     */
    public void killThread(){
        threadAlive = true;
    }
}
