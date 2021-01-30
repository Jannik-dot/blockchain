package blockchain.block;

import blockchain.Blockchain;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * This class was created to separate the creation of blocks from the miner class. It gets information about the needed
 * block parameters from the blockchain and brute forces a block with the right amount of preceding 0s.
 * The hash of the block contains information about its predecessor, its id, its timestamp and a random number used for
 * the brute force.
 */
public class BlockFactory extends Block implements Serializable {
    private Block prevBlock;
    private int iterations;

    public BlockFactory() {
        this.prevBlock = new Block();
    }
    @Override
    public Block create(String preceding0s) {

        Block block = new Block();
        Blockchain blockchain = Blockchain.getInstance();
        if (blockchain.getLength() > 0) {
            prevBlock = blockchain.getLastBlock();
        }
        if (!(iterations == 0)) {
            block.setHashPrevious(prevBlock.getHashNow());
            block.setId(prevBlock.getId() + 1);
        }

        int magicNumber = new Random().nextInt();
        if (!preceding0s.isEmpty()) {
            try {
                while (!block.getHashNow().startsWith(preceding0s)) {
                    String information = block.getHashPrevious()
                            + block.getId()
                            + block.getTimeStamp()
                            + magicNumber;
                    if (iterations > 1 && !blockchain.getMessages().isEmpty()) {
                        information += blockchain.getMessages().stream().toString();
                    }
                    block.setHashNow(SHA256.applySha256(information));
                    magicNumber = new Random().nextInt();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            String information = block.getHashPrevious() + block.getId() + block.getTimeStamp() + magicNumber;
            block.setHashNow(SHA256.applySha256(information));
            magicNumber = new Random().nextInt();
        }

        block.setMagicNumber(magicNumber);

        iterations++;
        Date now = new Date();
        block.setGenerationTime(((int) (now.getTime() - block.getCreationTime()) / 1000));
        return block;
    }
}
