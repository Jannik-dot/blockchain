package blockchain;

import blockchain.block.Block;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the blockchain itself. It adds blocks if they have the right amount of
 * preceding 0s and their prevHash is the same as the hash of the previous block. The adding of blocks
 * get progressively harder depending on the generation time of the blocks.
 */
public class Blockchain implements Serializable {
    private final List<Block> chain;
    private static final Blockchain instance = new Blockchain();
    private static final long serialVersionUID = 1L;
    private int N = 0;
    private int prevN;
    private List<String> messages;

    private Blockchain() {
        this.chain = new LinkedList<>();
        this.messages = new LinkedList<>();
    }

    public synchronized void addBlock(Block block) {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < N; i++) {
            builder.append("0");
        }
        if (chain.size() >= 1) {
            if (block.getHashNow().startsWith(builder.toString()) &&
                    chain.get(chain.size() - 1).getHashNow().equals(block.getHashPrevious())) {
                prevN = N;
                chain.add(block);
                block.setMiner("miner" + Thread.currentThread().getId());
                if (block.getGenerationTime() >= 60) {
                    N--;
                } else if (block.getGenerationTime() < 15) {
                    N++;
                }
                validate(block);
                messages.clear();
            }
        } else {
            chain.add(block);
            if (block.getGenerationTime() >= 60) {
                N--;
            } else if (block.getGenerationTime() < 15) {
                N++;
            }
            acceptMessage(" no messages");
            validate(block);
            messages.clear();
        }

    }

    public synchronized void acceptMessage(String message) {
        messages.add(message);
    }


    public List<String> getMessages() {
        return messages;
    }

    public static Blockchain getInstance() {
        return instance;
    }

    public synchronized void validate(Block block) {

        System.out.printf("Block:\n" +
                        "Created by %s\n" +
                        "%s gets 100 VC\n" +
                        "Id: %d\n" +
                        "Timestamp: %d\n" +
                        "Magic number: %d\n" +
                        "Hash of the previous block:\n%s\n" +
                        "Hash of the block:\n%s\n", block.getMiner(), block.getMiner(), block.getId(), block.getTimeStamp(),
                block.getMagicNumber(), block.getHashPrevious(), block.getHashNow());

        System.out.print("Block data:");
        messages.stream()
                .forEach(System.out::print);
        System.out.println();
        System.out.printf("Block was generating for %d seconds\n", block.getGenerationTime());
        if (prevN < N) {
            System.out.printf("N was increased to %d", N);
        } else if (prevN > N) {
            System.out.printf("N was decreased to %d", N);
        } else {
            System.out.printf("N stays the same");
        }
        System.out.println("\n");
    }

    public int getN() {
        return N;
    }

    public int getLength() {
        return chain.size();
    }

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }
}


