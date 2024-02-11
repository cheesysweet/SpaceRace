package BetPool;

/**
 * used to remove money from the betting pool
 * @author anton byström
 */
public class Consumer extends BaseThread{
    int value;

    /**
     * value to be removed from the betting pool
     * @param value value
     */
    public Consumer(int value) {
        this.value = value;
    }

    /**
     * removes value from the betting pool
     */
    @Override
    public void run() {
        while (!this.threadAlive){
            synchronized (this) {
                this.instance.removeBet(value);
            }
            sleep();
            this.killThread();
        }
    }
}
