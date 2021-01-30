package blockchain.block;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents the block and has lot of fields detailing different aspects of the block.
 */
public class Block implements Serializable {
    private long timeStamp = new Date().getTime();
    private long id = 1;
    private String HashNow = "";
    private String HashPrevious = "0";
    private int magicNumber;
    private int generationTime;
    private long creationTime = new Date().getTime();
    private String miner;



    Block create(String preceding0s) {
        return this;
    }
    //Getters & Setters
    public long getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getHashNow() {
        return HashNow;
    }

    public String getHashPrevious() {
        return HashPrevious;
    }

    public long getGenerationTime() {
        return generationTime;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setHashNow(String hashNow) {
        HashNow = hashNow;
    }

    public void setHashPrevious(String hashPrevious) {
        HashPrevious = hashPrevious;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setGenerationTime(int creationTime) {
        this.generationTime = creationTime;
    }

    public void setMagicNumber(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
