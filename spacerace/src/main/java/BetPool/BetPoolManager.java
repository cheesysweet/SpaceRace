package BetPool;

/**
 * Manager for the betPool used to add or remove bets
 */
public class BetPoolManager {

    /**
     * Creates a producer
     */
    public synchronized void createProducer(int amount){
        Producer producer = new Producer(amount);
        producer.run();
    }

    /**
     * Creates a consumer
     */
    public synchronized void createConsumer(int amount){
        Consumer consumer = new Consumer(amount);
        consumer.run();
    }
}
