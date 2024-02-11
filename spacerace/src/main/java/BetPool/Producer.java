package BetPool;

/**
 * used to add money to the betting pool
 * @author anton byström
 */
public class Producer extends BaseThread{
    int value;

    /**
     * value to be added to the betting pool
     * @param value value
     */
    public Producer(int value) {
        this.value = value;
    }

    /**
     * increases the betting bool with value
     */
    @Override
    public void run() {
        while (!this.threadAlive){
            synchronized (this) {
                this.instance.addBet(value);
            }
            sleep();
            this.killThread();
        }
    }
}
