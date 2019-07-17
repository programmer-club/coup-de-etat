package game;

import action.ActionContest;
import action.ActionPlay;
import bots.BaseBot;
import card.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    public ArrayList<Card> createDeck() {
        ArrayList<Card> ca = new ArrayList<>();
        for (Card c : Card.values()) {
            for (int i = 0; i < 3; i++) {
                ca.add(c);
            }
        }

        return ca;
    }

    public void start(Class<? extends BaseBot> bot1, Class<? extends BaseBot> bot2) {
        int winner = 0;


        BaseBot player1 = null, player2 = null;
        try {
            player1 = bot1.getConstructor(Integer.class).newInstance(1);
            player2 = bot2.getConstructor(Integer.class).newInstance(2);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error constructing bots.");
            System.exit(-1);
        }

        int round = 1;
        Card[] hand1, hand2;
        ArrayList<Card> deck = createDeck();
        Collections.shuffle(deck);

        hand1 = new Card[] {
                deck.get(0),
                deck.get(1)
        };

        hand2 = new Card[] {
                deck.get(2),
                deck.get(3)
        };

        deck.remove(0);
        deck.remove(1);
        deck.remove(2);
        deck.remove(3);

        Collections.shuffle(deck);

        System.out.println("Player 1 has: " + Arrays.toString(hand1));
        System.out.println("Player 2 has: " + Arrays.toString(hand2));


        while (winner == 0) {
            ActionPlay p1 = player1.playCard(hand1, player2.getCoins());
            System.out.println("Player 1 attempts " + p1);
            ActionContest p2contests = ActionContest.Allow;

            if (Card.fromPlay(p1) != null) p2contests = player2.shouldContest(Card.fromPlay(p1), hand2);

            // todo: check contest, and then execute action
        }
    }
}
