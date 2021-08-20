import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private static List<Card> newDeck() {
        List<Card> deck = new ArrayList<>();
        for(int i = 2; i <= 14; i++) {
            for (Suit currentSuit : Suit.values()) {
                Card newCard = new Card();
                newCard.setSuit(currentSuit);
                newCard.setSequence(i);
                switch (newCard.getSequence()){
                    case 11:
                        newCard.setName("Jack");
                        newCard.setValue(10);
                        break;
                    case 12:
                        newCard.setName("Queen");
                        newCard.setValue(10);
                        break;
                    case 13:
                        newCard.setName("King");
                        newCard.setValue(10);
                        break;
                    case 14:
                        newCard.setName("Ace");
                        newCard.setValue(11);
                        break;
                    default:
                        newCard.setName(Integer.toString(i));
                        newCard.setValue(i);
                        break;
                }
                deck.add(newCard);
            }
        }
        return deck;
    }

    public static List<Card> shuffleDeck() {
        List<Card> deck = new Deck().newDeck();
        Collections.shuffle(deck);
        return deck;
    }


}
