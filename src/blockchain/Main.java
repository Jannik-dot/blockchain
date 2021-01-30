package blockchain;


import blockchain.block.BlockFactory;
import blockchain.user.User;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the main class which looks for a blockchain on the hard drive, and deserializes it if it finds one. Else it will
 * create a blockchain of the size 5 (could be longer but it takes forever to generate). Miners and users are multithreaded
 * and continuously created. As soon as 5 blocks are created the execution shutdowns and the blockchain is serialized.
 */
public class Main {
    public static void main(String[] args) {
        File file = new File("Blockchain\\task\\src\\resources\\blockchain.data");
        Blockchain blockchain;
        
        try {
            if (file.exists()) {
                blockchain = (Blockchain) BlockchainSerialization.deserialize(file.getAbsolutePath());
            } else {
                blockchain = Blockchain.getInstance();
            }
        } catch (IOException | ClassNotFoundException ioException) {
            blockchain = Blockchain.getInstance();
        }

        BlockFactory blockFactory = new BlockFactory();
        ExecutorService executor = Executors.newFixedThreadPool(4);

        while (blockchain.getLength() < 5) {
            executor.submit(() -> new Miner().mine(blockFactory));
            executor.submit(() -> new User().chat());
        }
        executor.shutdownNow();


        try {
            BlockchainSerialization.serialize(blockchain, file.getAbsolutePath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        
    }


}
