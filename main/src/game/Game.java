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

    public void doAction(BaseBot actionPerformer, BaseBot actionContestor) {
        var player1 = actionPerformer;
        var player2 = actionContestor;

        var hand1 = player1.hand;
        var hand2 = player2.hand;

        ActionPlay p1 = player1.playCard(player2.getCoins());
        System.out.println("Player 1 attempts " + p1);
        ActionContest p2contests = ActionContest.Allow;

        if (Card.fromPlay(p1) != null) p2contests = player2.shouldContest(Card.fromPlay(p1));

        if (p2contests == ActionContest.Contest) {
            if (hand1[0] == Card.fromPlay(p1) || hand1[1] == Card.fromPlay(p1)) {
                player2.removeCard(player2.flipCard());
                // TODO: get new card for player 1
            } else {
                player1.removeCard(player1.flipCard());
            }
        }

        if (p1 == ActionPlay.Income) {
            player1.addCoins(1);
        } else if (p1 == ActionPlay.Coup) {
            player2.removeCard(player2.flipCard());
            // TODO: check coin ct
            player1.addCoins(-7);
        } else {
            switch (Card.fromPlay(p1)) {
                case Duke:
                    player1.addCoins(3);
                    break;
                case Captain:
                    doCaptain(player1, player2);
                case Assassin:
                    doAssassin(player1, player2);
                case Ambassador:
                    doAmbassador(player1);
                default:
                    break;
            }
        }
    }

    private void doAmbassador(BaseBot player1) {
        var onDeck = new Card[] {
                deck.get(0),
                deck.get(1)
        };
        player1.hand = player1.chooseCards(onDeck);

        deck.remove(0);
        deck.remove(1);
        // TODO: reshuffle the 2 cards not chosen
    }

    private void doAssassin(BaseBot player1, BaseBot player2) {

    }

    private void doCaptain(BaseBot player1, BaseBot player2) {

    }

    private ArrayList<Card> deck;

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
        deck = createDeck();
        Collections.shuffle(deck);

        player1.hand = new Card[]{
                deck.get(0),
                deck.get(1)
        };

        player2.hand = new Card[]{
                deck.get(2),
                deck.get(3)
        };

        deck.remove(0);
        deck.remove(1);
        deck.remove(2);
        deck.remove(3);

        Collections.shuffle(deck);

        System.out.println("Player 1 has: " + Arrays.toString(player1.hand));
        System.out.println("Player 2 has: " + Arrays.toString(player2.hand));


        while (winner == 0) {
            doAction(player1, player2);
            doAction(player2, player1);

            if (player1.hand.length == 0) {
                winner = 2;
            } else if(player2.hand.length == 0) {
                winner = 1;
            }

            System.out.printf("Round %d over. Player 1 has %s and %d coins, and player 2 has %s and %d coin.\n",
                    round,
                    Arrays.toString(player1.hand),
                    player1.getCoins(),
                    Arrays.toString(player2.hand),
                    player2.getCoins());
            round++;
        }
    }
}
