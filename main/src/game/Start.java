package game;

import bots.RoyaltyBot;

public class Start {
    public static void main(String[] args) {
        // TODO: bots from args
        Game game = new Game();
        game.start(RoyaltyBot.class, RoyaltyBot.class);
    }
}
