package cardgameslib.utilities;

import java.util.*;

/**
 * Class to hold info about a card deck
 *
 * @author Ryan Bickham
 *
 */
public class Deck {

    private static final List<Character> cardValues = Arrays.asList('2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A');
    private static final List<Character> suitValues = Arrays.asList('C', 'D', 'S', 'H');

    private List<Integer> deck;
    private List<Integer> dealtCards;

    /**
     * Constructor of Deck for a standard 52 card deck
     */
    public Deck() {
        deck = new ArrayList<>();
        dealtCards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            deck.add(i);
        }
    }

    /**
     * Constructor for deck with a custom deck
     *
     * @param customDeck
     */
    public Deck(List<Integer> customDeck) {
        for (int card : customDeck) {
            if (card < 0 || card > 51) {
                throw new IllegalArgumentException("One or more cards in the requested deck is invalid");
            }
        }
        deck = new ArrayList<>(customDeck);
        dealtCards = new ArrayList<>();
    }

    /**
     * Adds another set of a standard 52 card deck to the current deck for games
     * using multiple decks
     */
    public void addDeck() {
        for (int i = 0; i < 52; i++) {
            deck.add(i);
        }
    }

    /**
     * Function that prints out all cards in the deck
     */
    public void print() {
        for (int card : deck) {
            System.out.println(cardToString(card));
        }
    }

    /**
     * Getter to obtain top card in the deck
     *
     * @return String
     */
    public String getTopCard() {
        return cardToString(deck.get(0));
    }

    public int getSize() {
        return deck.size();
    }
    
    /**
     * Function to shuffle the deck
     */
    public void shuffle() {
        Random random = new Random();
        int i, j, temp;

        for (i = deck.size() - 1; i > 0; i--) {
            j = random.nextInt(i + 1);
            temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    /**
     * Function that collects cards from game back to the deck
     */
    public void collectCards() {
        // Add the dealt cards back to the deck.
        for (int i = 0; i < dealtCards.size(); i++) {
            deck.add(dealtCards.get(i));
        }
        dealtCards = new ArrayList<>();   // Re-initialize dealtCards list.
    }

    /**
     * Function to deal a single card
     *
     * @return int
     */
    public int dealCard() {
        int card = deck.get(0);
        dealtCards.add(card);
        deck.remove(0);
        return card;
    }

    /**
     * Function to handle dealing multiple cards at a time
     *
     * @param numCards int value holding number of cards to be dealth
     * @return List<Integer>
     */
    public List<Integer> dealCards(int numCards) {
        List<Integer> cards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            dealtCards.add(deck.get(0));
            cards.add(deck.get(0));
            deck.remove(0);
        }
        return cards;
    }

    /**
     * Function to deal a specific card from the deck
     *
     * @param card int value holding which card is to be dealt
     */
    public void dealSpecific(int card) {
        if (validCard(card) && inDeck(card)) {
            deck.remove(new Integer(card));
            dealtCards.add(card);
        }
    }

    /**
     * Function to determine whether or not a card is valid in the current deck
     *
     * @param card int value of card to be checked
     * @return boolean
     */
    public boolean validCard(int card) {
        if (card < 0 || card > 51) {
            throw new IllegalArgumentException(String.format("%d does not match a valid card", card));
        }
        return true;
    }

    /**
     * Function to see if a card is in the deck or not
     *
     * @param card int value of card to checked for
     * @return boolean
     */
    public boolean inDeck(int card) {
        if (!deck.contains(card)) {
            throw new IllegalArgumentException(String.format("%s was already dealt or is not a member of this deck.", cardToString(card)));
        }
        return true;
    }

    /**
     * Function to get a card from a string value
     *
     * @param card String containing the card
     * @return int
     */
    public static int cardFromString(String card) {
        return 13 * suitValues.indexOf(card.charAt(1)) + cardValues.indexOf(card.charAt(0));
    }

    /**
     * Function to turn a integer value representation of a card to a String
     * representation
     *
     * @param card int value holding card to be represented as a String
     * @return String
     */
    public static String cardToString(int card) {
        return String.format("%c%c", cardValues.get(card % 13), suitValues.get(card / 13));
    }
}
