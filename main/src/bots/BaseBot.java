package bots;

import action.ActionPlay;
import action.ActionContest;
import action.ActionAssassinated;
import action.ActionRobbed;
import card.Card;

public abstract class BaseBot {
    // These variables must *not* be modified by the bot
    private int coins;
    public Card[] hand;

    public BaseBot(Integer coins) {
        this.coins = coins;
    }

    public void removeCard(int index) {
        if (hand.length == 1) {
            hand = new Card[0];
        } else {
            var new_hand = new Card[1];
            new_hand[0] = hand[1 - index];
            hand = new_hand;
        }
    }

    public int getCoins() {
        return this.coins;
    }

    // If a bot calls this, it is disqualified
    public void addCoins(int coins) {
        this.coins += coins;
    }

    public abstract ActionPlay playCard(int opponentCoins);

    public abstract ActionContest shouldContest(Card opponentClaims);

    public abstract ActionAssassinated whenAssassinated(Card opponentsAssassin);

    public abstract ActionRobbed whenRobbed(Card opponentsAssassin);

    public abstract Card[] chooseCards(Card[] seenCardsOnDeck);

    public abstract int flipCard();
}
