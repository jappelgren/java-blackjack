import java.util.ArrayList;
import java.util.List;

public class CardVisualizer {

    public static String visualizedCard(List<Card> originalHand, boolean onlySecondCard) {
        List<Card> hand = new ArrayList<>();
        for(Card card : originalHand) {
            hand.add(card);
        }

        if(onlySecondCard) {
            hand.remove(0) ;
        }

        String cardVisualized = "";

        for(Card card : hand) {
            String row1 = " ⌈--------⌉";
            if(card == hand.get(hand.size() - 1)) {
                cardVisualized += row1 + "\n";
            } else {
                cardVisualized += row1;
            }

        }

        for (Card card : hand) {
            String row2 = "";
            if(card.getName().length() > 2) {
                row2 = " | " + card.getName().charAt(0) + "      |";
            } else if(card.getName().length() == 2) {
                row2 = " | " + card.getName() + "     |";
            } else {
                row2 = " | " + card.getName() + "      |";
            }

            if(card == hand.get(hand.size() - 1)) {
                cardVisualized += row2 + "\n";
            } else {
                cardVisualized += row2;
            }
        }

        for (Card card : hand) {
            char suitSymbol = suitDetector(card);

            String row3 = " | " + suitSymbol + "      |";

            if (card == hand.get(hand.size() - 1)) {
                cardVisualized += row3 + "\n";
            } else {
                cardVisualized += row3;
            }
        }

        for (Card card : hand) {
            String row4 = " |        |";
            if(card == hand.get(hand.size() - 1)) {
                cardVisualized += row4 + "\n";
            } else {
                cardVisualized += row4;
            }
        }

        for (Card card : hand) {
            char suitSymbol = suitDetector(card);

            String row5 = " |      " + suitSymbol + " |";

            if(card == hand.get(hand.size() - 1)) {
                cardVisualized += row5 + "\n";
            } else {
                cardVisualized += row5;
            }
        }

        for (Card card : hand) {
            String row6 = "";
            if(card.getName().length() > 2) {
                row6 = " |      " + card.getName().charAt(0) + " |";
            } else if(card.getName().length() == 2) {
                row6 = " |     " + card.getName() + " |";
            } else {
                row6 = " |      " + card.getName() + " |";
            }

            if(card == hand.get(hand.size() - 1)) {
                cardVisualized += row6 + "\n";
            } else {
                cardVisualized += row6;
            }
        }

        for (Card card : hand) {
            String row7 = " ⌊________⌋";
            cardVisualized += row7;
        }
        return cardVisualized;
    }

    private static char suitDetector(Card card) {
        char suitSymbol = ' ';

        switch (card.getSuit()) {
            case DIAMONDS:
                suitSymbol = '♦';
                break;
            case SPADES:
                suitSymbol = '♠';
                break;
            case CLUBS:
                suitSymbol = '♣';
                break;
            case HEARTS:
                suitSymbol = '♥';
                break;
            default:
                suitSymbol = ' ';
                break;
        }
        return suitSymbol;
    }

    private static void lineBreaker(Card card, List<Card> hand, String row, String cardVisualized) {
        if(card == hand.get(hand.size() - 1)) {
            cardVisualized += row + "\n";
        }else {
            cardVisualized += row;
        }
    }

}
