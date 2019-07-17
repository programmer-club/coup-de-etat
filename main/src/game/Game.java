package game;

import bots.BaseBot;

public class Game {
    public void start(Class<? extends BaseBot> bot1, Class<? extends BaseBot> bot2) {
        try {
            BaseBot player1 = bot1.getConstructor(Integer.class).newInstance(1);
            BaseBot player2 = bot2.getConstructor(Integer.class).newInstance(2);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error constructing bots.");
            System.exit(-1);
        }

        int round = 1;
        // TODO: round loop
    }
}
