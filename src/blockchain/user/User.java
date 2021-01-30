package blockchain.user;

import blockchain.Blockchain;
import java.util.Random;

/**
 * This class simulates a user which uses the blockchain to "communicate" with other users.
 */
public class User {

    public void chat() {
        Blockchain blockchain = Blockchain.getInstance();

        String[] userName = {"Tom", "Mary", "Simon", "Herbert", "Günther", "Lambertz", "Joel"};

        String[] messages = {"Hallo", "Mir geht's gut!", "Wie geht es dir?", "Ich kaufe gerade GME stock!", "Was machst du so?"};

        String sendMessage = "\n" + userName[new Random().nextInt(userName.length)] + ": " +
                messages[new Random().nextInt(messages.length)];

        if (blockchain.getLength() >= 1) {
            blockchain.acceptMessage(sendMessage);
        }
    }


}
