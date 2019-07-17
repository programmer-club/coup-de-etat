package bots;

import action.ActionAssassinated;
import action.ActionContest;
import action.ActionPlay;
import action.ActionRobbed;
import card.Card;

public class RoyaltyBot extends BaseBot {
    public RoyaltyBot(Integer coins) {
        super(coins);
    }

    @Override
    public ActionPlay playCard(Card[] hand, int i) {
        if (this.getCoins() >= 7) return ActionPlay.Coup;
        return ActionPlay.PlayDuke;
    }

    @Override
    public ActionContest shouldContest(Card opponentClaims, Card[] yourHand) {
        return ActionContest.Allow;
    }

    @Override
    public ActionAssassinated whenAssassinated(Card opponentsAssassin, Card[] yourHand) {
        return ActionAssassinated.Allow;
    }

    @Override
    public ActionRobbed whenRobbed(Card opponentsAssassin, Card[] yourHand) {
        return ActionRobbed.Allow;
    }

    @Override
    public Card flipCard(Card[] yourHand) {
        return yourHand[0];
    }
}
