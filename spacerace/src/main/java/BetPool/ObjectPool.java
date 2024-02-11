package BetPool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * creates random players
 * @author anton byström
 */
public class ObjectPool {
    BetPoolManager betPoolManager = new BetPoolManager();
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final int players = new Random().nextInt(10)+1; // random players

    /**
     * creates a producer that add a value between 100 and 1000 from the betPool
     */
    Runnable producers = () -> {
        try {
            betPoolManager.createProducer(new Random().nextInt((10)+1) * 100);
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * creates a consumer that removes a value between 500 and 5000 from the betPool
     */
    Runnable consumer = () -> {
        try {
            betPoolManager.createConsumer(new Random().nextInt((10)+1) * 500);
            TimeUnit.MILLISECONDS.sleep(300);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * creates a random amount of producers
     */
    public void executeProducers () {
        for (int i=0; i<players; i++) {
            executorService.execute(producers);
        }
    }

    /**
     * creates a random amount of consumers
     */
    public void executeConsumers () {
        if (players <= 5){ // If it´s less than 5 random players only half of them will win
            for (int i=0; i<players/2; i++) {
                executorService.execute(consumer);
            }
        }
        else { // and if it´s more than 5 random players 1/3 of the players will win
            for (int i=0; i<players/3; i++) {
                executorService.execute(consumer);
            }
        }
    }

    /**
     * shuts down the executorService when the player stops the game
     */
    public void killExecutor() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}