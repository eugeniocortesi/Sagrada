package it.polimi.ingsw.LM26;

import it.polimi.ingsw.LM26.model.Cards.Decks;

import static it.polimi.ingsw.LM26.model.Serialization.SingletonDecks.singletonDecks;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");

        Decks decks=singletonDecks();

        /*OnBoardCards onBoardCards = new OnBoardCards(decks.getObjectivePublicCardDeck(), decks.getToolCardDeck());

        System.out.println("card " + onBoardCards.getObjectivePublicCardList().get(0).getId());
        System.out.println(onBoardCards.getObjectivePublicCardList().get(0).getPoints());
        System.out.println(onBoardCards.getObjectivePublicCardList().get(0).getEffect());*/

        //CAMBIA IL II PARAMENTRO DI ONBOARDCARDS IN TOOLCARDINT

    }
}
