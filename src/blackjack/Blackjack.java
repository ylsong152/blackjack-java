package blackjack;

import java.util.*;

public class Blackjack {
    private Deck deck;
    private Dealer dealer;
    private Player player;
    Scanner scanner = new Scanner(System.in);

    public void playGame() {
        String input; 
        do {
            startGame();
            System.out.println("Do you want to start another game? (y/n)");

            while (true) { 
                input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("y") || input.equals("n")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                }
            }
        } while (input.equals("y"));

        System.out.println("Thank you for playing!");
        scanner.close();
    }
    
    private void startGame() {
        System.out.println("Starting a new game...");
        deck = new Deck();
        deck.shuffleDeck();

        dealer = new Dealer();
        player = new Player();

        dealInitial();
        playerTurn();
        dealerTurn();
    } 

    // If more players, can place all players into a list and loop through
    private void dealInitial() {
        player.addToHand(deck.drawCard());
        dealer.addToHand(deck.drawCard());
        player.addToHand(deck.drawCard());
        dealer.addToHand(deck.drawCard());
    }

    private void evaluateHand(List<Card> hand) {
        System.out.println("Your hand: " + player.getHand() + "\nTotal value: " + player.getValue(hand));
    }

    private void playerTurn() {
        evaluateHand(player.getHand());
        if (player.isBlackjack(player.getHand())) {
            handleBlackJack();
        } else {
            handlePlayerAction();
        }     
    }

    private void handleBlackJack() {
        System.out.println("BOOM! BLACKJACK!!! YOU WON!!\n");
        System.out.println("Do you want to start another game? (y/n)");
        playGame();        
    }

    private void handlePlayerAction() {
        while (true) {
            Integer playerHandValue = player.getValue(player.getHand());
            if (playerHandValue < 16) {
                System.out.println("Insufficient value (" + playerHandValue + "), type 'h' to draw a card or 's' to stand.");
            } else if (playerHandValue >= 16 && playerHandValue <= 21) {
                System.out.println("Do you want to hit or stand? (h/s)");
            }
    
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "h":
                    player.addToHand(deck.drawCard());
                    System.out.println("Drawing another card...");
                    evaluateHand(player.getHand());
    
                    if (player.getValue(player.getHand()) > 21) {
                        System.out.println("BUSTTT!! GAME OVER!");
                        return;
                    }
                    break;
                case "s":
                    System.out.println("You chose to stand with " + playerHandValue + ".");
                    return;
                default:
                    System.out.println("Invalid input. Please enter 'h' to hit, or 's' to stand.");
                    break;
            }
        }
    }

    // COMPARE PLAYER AND DEALER HAND TO DETERMINE WINNER
    private void dealerTurn() {

        if (player.getValue(player.getHand()) > 21) {
            return;
        } else {
            System.out.println("Dealer's Hand: " + dealer.getHand() + "\nValue: " + dealer.getValue(dealer.getHand()));
            if (dealer.isBlackjack(dealer.getHand())) {
                System.out.println("Dealer has the Blackjack, you lost!");
                return;
            } 

            while (dealer.getValue(dealer.getHand()) < 17) {
                System.out.println("Dealer currently has " + dealer.getValue(dealer.getHand()) + " and hits");
                dealer.addToHand(deck.drawCard());
                System.out.println("Dealer's hand is now: " + dealer.getHand() + "\nTotal value: " + dealer.getValue(dealer.getHand()));
            }

            if (dealer.getValue(dealer.getHand()) > 21) {
                System.out.println("Dealer BUST! You won!");
            }

            System.out.println("Dealer stands with a total of " + dealer.getValue(dealer.getHand()));
            compareHands();
        }      
    }

    private void compareHands() {
        Integer playerValue = player.getValue(player.getHand());
        Integer dealerValue = dealer.getValue(dealer.getHand());

        System.out.println("Player's final hand: " + player.getHand() + " (value: " + playerValue + ")");
        System.out.println("Dealer's final hand: " + dealer.getHand() + " (value: " + dealerValue + ")");

        if (playerValue > dealerValue) {
            System.out.println("Congratulations! You won!");
        } else if (playerValue < dealerValue) {
            System.out.println("Dealer wins. Better luck next time!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    public static void main(String[] args) {
        Blackjack newGame = new Blackjack();
        newGame.playGame();
    }        
}
