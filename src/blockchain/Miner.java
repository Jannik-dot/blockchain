package blockchain;


import blockchain.block.BlockFactory;

/**
 * This class simulates a miner which guesses blocks and submits them to the blockchain
 */
public class Miner extends Thread{
    private final Blockchain blockchain = Blockchain.getInstance();

    public Miner () {
    }


    public synchronized void mine(BlockFactory blockFactory) {

        try {
            StringBuilder builder = new StringBuilder();
            builder.append("0".repeat(Math.max(0, blockchain.getN())));
            blockchain.addBlock(blockFactory.create(builder.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
