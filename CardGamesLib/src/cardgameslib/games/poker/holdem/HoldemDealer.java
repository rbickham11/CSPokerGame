package cardgameslib.games.poker.holdem;

import cardgameslib.utilities.*;
import cardgameslib.games.poker.betting.*;

import java.util.*;

public class HoldemDealer {
    private final int MAX_PLAYERS = 10;
    
    private Deck deck;
    private PokerBettingHelper bettingHelper;
    private HoldemWinChecker winChecker;
    
    private List<BettingPlayer> players;
    private List<BettingPlayer> activePlayers;
    
    private List<Integer> board;
    
    private int chipLimit;
    private int bigBlind;
   
    public HoldemDealer(int maxChips, int bb) {
        deck = new Deck();
        players = new ArrayList<>(MAX_PLAYERS);
        bettingHelper = new PokerBettingHelper(activePlayers, bigBlind);
        winChecker = new HoldemWinChecker();
        chipLimit = maxChips;
        bigBlind = bb;

        Random r = new Random();
        Collections.rotate(players, r.nextInt(MAX_PLAYERS));
        
    }
    
    public int getBigBlind() {
    	return bigBlind;
    }
    public void setBigBlind(int bb) {
    	bigBlind = bb;
    }
    public void addPlayer(int id, int seatNum, int startingChips) {
        if(startingChips <= chipLimit) {
            players.add(new BettingPlayer(id, seatNum, startingChips));
            Collections.sort(players);
        } else {
            throw new IllegalArgumentException("Starting chip count exceeds maximum for this table");
        }
    }
    
    public void removePlayer(int id) {
        for(BettingPlayer player : players) {
            if(player.getUserId() == id) {
                players.remove(players.indexOf(player));
                return;
            }
        }
        throw new IllegalArgumentException(String.format("Player with id %d not found on table", id));
    }
    
    public void startHand() {
        Collections.rotate(players, 1);
        activePlayers = new ArrayList<>(players);
        board = new ArrayList<>();
        deck.collectCards();
        bettingHelper = new PokerBettingHelper(activePlayers, bigBlind);
        
        for(BettingPlayer player : players) {
            player.resetHand();
        }
        deck.shuffle();
        System.out.printf("The dealer is Player %d\n", players.get(players.size() - 1).getSeatNumber());
        dealHands();
        bettingHelper.startNewRound(true);
    }
    
    public void dealHands() {
        List<Integer> cards = deck.dealCards(players.size() * 2);
        int i;
        int j = 0;
        
        for(i = 0; i < players.size(); i++) {
            players.get(i).giveCard(cards.get(j));
            players.get(i).giveCard(cards.get(j + players.size()));
            j++;
        }
        
        for(Player player : players) {    //For testing
            System.out.println(String.format("Player %d's hand is %s %s", player.getSeatNumber(), 
                                            Deck.cardToString(player.getHand().get(0)), Deck.cardToString(player.getHand().get(1))));
        }
    }
    
    public void dealFlopToBoard() {
        int card;
        deck.dealCard(); //Burn
        System.out.printf("\nPot Size: %d\n", getPotSize());
        System.out.print("Board: ");
        for(int i = 0; i < 3; i++) {
            card = deck.dealCard();
            board.add(card);
            System.out.print(Deck.cardToString(card) + " ");
        }
        System.out.print("\n");
        bettingHelper.startNewRound(false);
    }
    
    public void dealCardToBoard() {   //For turn and river
        deck.dealCard(); //Burn
        int card = deck.dealCard();
        board.add(card);
        System.out.printf("\nPot Size: %d\n", getPotSize());     
        System.out.print("Board: ");
        for(int boardCard : board) {
            System.out.print(Deck.cardToString(boardCard) + " ");
        }
        System.out.print("\n");
        bettingHelper.startNewRound(false);
    }
    
    public void takeAction(Action action, int chipAmount) {
        bettingHelper.takeAction(action, chipAmount);
    }
    
    public boolean bettingComplete() {
        return bettingHelper.bettingComplete();
    }
    
    public int getCurrentBet() {
        return bettingHelper.getCurrentBet();
    }
    
    public int getPotSize() {
        return bettingHelper.getPotSize();
    }
    
    public BettingPlayer getActivePlayer() {
        return activePlayers.get(0);
    }
    
    public boolean isWinner() {
        return bettingHelper.isWinner();
    }
    
    public void findWinner() {
        winChecker.findWinningHand(activePlayers, board);
        
        List<BettingPlayer> winningPlayers = winChecker.getWinningPlayers();
        String winningRank = winChecker.getWinningRank();
        String a = "";
        if(winningRank.equals("Pair") || winningRank.equals("Straight") || winningRank.equals("Flush") || winningRank.equals("Full House") || winningRank.equals("Straight Flush")) {
            a = "a ";
        }
        for(BettingPlayer player : winningPlayers) {
            System.out.printf("\nThe winner is Player %d with %s%s", player.getSeatNumber(), a, winChecker.getWinningRank());
        }
    }
}
