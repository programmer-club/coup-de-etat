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
    public ActionPlay playCard(int i) {
        if (this.getCoins() >= 7) return ActionPlay.Coup;
        return ActionPlay.PlayDuke;
    }

    @Override
    public ActionContest shouldContest(Card opponentClaims) {
        return ActionContest.Allow;
    }

    @Override
    public ActionAssassinated whenAssassinated(Card opponentsAssassin) {
        return ActionAssassinated.Allow;
    }

    @Override
    public ActionRobbed whenRobbed(Card opponentsAssassin) {
        return ActionRobbed.Allow;
    }

    @Override
    public int flipCard() {
        return 0;
    }

    @Override
    public Card[] chooseCards(Card[] seenCardsOnDeck) {
        return this.hand;
    }
}
