package card;

import action.ActionPlay;

public enum Card {
    Assassin,
    Ambassador,
    Captain,
    Contessa,
    Duke;

    public static Card fromPlay(ActionPlay p1) {
        switch(p1) {
            case PlayAmbassador:
                return Ambassador;
            case PlayAssassin:
                return Assassin;
            case PlayDuke:
                return Duke;
            case PlayCaptain:
                return Captain;
            default:
                return null;
        }
    }
}
