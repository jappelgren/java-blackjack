import java.util.*;

public class Game {
    List<Card> deck = new Deck().shuffleDeck();
    List<Card> discardPile = new ArrayList<>();

    List<Card> dealerHand = new ArrayList<>();
    List<Card> playerHand = new ArrayList<>();

    public void initialDeal() {
        discardHand(playerHand);
        discardHand(dealerHand);
        for(int i = 0; i <= 3; i++) {
            checkDeckHasCards();
            if(i % 2 != 0) {
                hit(dealerHand);
            } else {
                hit(playerHand);
            }
        }

        if(blackJackDetector(playerHand) && blackJackDetector(dealerHand)) {
            printHand(dealerHand);
            printHand(playerHand);
            System.out.println("Player and Dealer both have blackjack, which means no one wins... boo");
            gameInterface(false);
        } else if(blackJackDetector(playerHand)) {
            printHand(dealerHand);
            printHand(playerHand);
            System.out.println("Player has blackjack! Congrats you're a power winner!");
            gameInterface(false);
        }else if(blackJackDetector(dealerHand)) {
            printHand(dealerHand);
            printHand(playerHand);
            System.out.println("Dealer has blackjack, you are a power loser.");
            gameInterface(false);
        }
    }

    private void checkDeckHasCards() {
        if(deck.size() == 0) {
            for (Card card : discardPile) {
                deck.add(card);
            }
            Collections.shuffle(deck);
        }
    }

    private void discardHand(List<Card> hand) {
        for (Card card : hand) {
            discardPile.add(card);
        }
        hand.clear();
    }

    public void hit(List<Card> player) {
        player.add(deck.get(0));
        deck.remove(0);
    }

    public int scorer(List<Card> handToScore) {
        int score = 0;
        List<Card> player = new ArrayList<>();
        player.addAll(handToScore);

        for (int i = 0; i < player.size(); i++) {
            if(player.get(i).getName().equals("Ace") && i != player.size() - 1){
                Card tempCard = player.get(i);
                player.remove(i);
                player.add(tempCard);
            }
        }
        for (Card card : player) {
            if(card.getName() == "Ace" && (score + 11) > 21) {
                score += 1;
            } else {
                score += card.getValue();
            }
        }
        return score;
    }

    private void winDetector() {
        int playerScore = scorer(playerHand);
        int dealerScore = scorer(dealerHand);

        if(playerScore == dealerScore) {
            System.out.println("Push, Dealer and player both have " + playerScore);
        }else if(playerScore > 21) {
            System.out.println("Dealer wins");
        }else if(dealerScore > 21) {
            System.out.println("Player wins");
        }else if(playerScore > dealerScore) {
            System.out.println("Player wins");
            System.out.println("Player has " + playerScore + ". Dealer has " + dealerScore);
        }else {
            System.out.println("Dealer wins");
            System.out.println("Player has " + playerScore + ". Dealer has " + dealerScore);
        }
    }

    private void bustDetector(List<Card> player) {
        String playerName = playerDetector(player);

        if(scorer(player) > 21 && playerName == "Player") {
            System.out.println(playerName + " has busted!");
            winDetector();
            gameInterface(false);
        } else if(scorer(player) > 21 && playerName == "Dealer") {
            System.out.println(playerName + " has busted!");
            winDetector();
            gameInterface(false);
        } else if(playerName == "Player") {
            playerTurn();
        } else if(playerName == "Dealer") {
            dealerTurn();
        }
    }

    private boolean blackJackDetector(List<Card> player) {
        if(player.size() == 2 && scorer(player) == 21) {
            return true;
        }
        return false;
    }

    private String playerDetector(List<Card> player) {
        if(player == dealerHand) {
            return "Dealer";
        } else {
            return "Player";
        }
    }

    public void dealerTurn() {
        int playerScore = scorer(playerHand);
        int dealerScore = scorer(dealerHand);

        if(dealerScore < 17 && playerScore > dealerScore){
            hit(dealerHand);
            printHand(dealerHand);
            bustDetector(dealerHand);
        } else {
            System.out.println("Dealer stands at: " + scorer(dealerHand));
            winDetector();
            gameInterface(false);
        }

    }

    private void playerTurn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to (h)it or (s)tand");
        String hitOrStand = sc.nextLine();
        hitOrStand.toLowerCase();

        if(hitOrStand.charAt(0) == 'h') {
            hit(playerHand);
            System.out.println("Player draws a card");
            printHand(playerHand);
            bustDetector(playerHand);

        } else if(hitOrStand.charAt(0) == 's') {
            System.out.println("Player stands at: " + scorer(playerHand));
            printHand(dealerHand);
            dealerTurn();
        }
    }

    private void printHand(List<Card> player) {
        String playerName = playerDetector(player);

        System.out.println("-------------");
        System.out.println(playerName + " Score: " + scorer(player));
        System.out.println(CardVisualizer.visualizedCard(player, false));
        for (Card card : player) {
            System.out.println(card.getName() + " of " + card.getSuit());
        }
    }


    public void gameInterface(boolean firstGame) {
        if(firstGame) {
            System.out.println("Hello, welcome to Justin's power blackjack.  Would you like to play a hand? (y)es, (n)o");
        } else {
            System.out.println("Would you like to play another hand? (y)es, (n)o");
        }

        Scanner sc = new Scanner(System.in);
        String playHand = sc.nextLine();
        playHand = playHand.toLowerCase();

        if(playHand.charAt(0) == 'y') {
            initialDeal();

            System.out.println("Dealer is showing a:");
            System.out.println(CardVisualizer.visualizedCard(dealerHand, true));
            System.out.println(dealerHand.get(1).getName() + " of " + dealerHand.get(1).getSuit());
            printHand(playerHand);
            playerTurn();

        } else if(playHand.charAt(0) == 'n') {
            System.out.println("See ya dog!");
        } else {
            System.out.println(playHand + " is neither yes or no, try again.");
            gameInterface(firstGame);
        }
    }


}
