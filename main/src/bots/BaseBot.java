package bots;

import action.ActionPlay;
import action.ActionContest;
import action.ActionAssassinated;
import action.ActionRobbed;
import card.Card;

public abstract class BaseBot {
    private int coins;

    public BaseBot(Integer coins) {
        this.coins = coins;
    }

    public int getCoins() {
        return this.coins;
    }

    // If a bot calls this, it is disqualified
    public void addCoins(int coins) {
        this.coins += coins;
    }

    public abstract ActionPlay playCard(Card[] hand);
    public abstract ActionContest shouldContest(Card opponentClaims, Card[] yourHand);
    public abstract ActionAssassinated whenAssassinated(Card opponentsAssassin, Card[] yourHand);
    public abstract ActionRobbed whenRobbed(Card opponentsAssassin, Card[] yourHand);

    public abstract Card flipCard(Card[] yourHand);
}
