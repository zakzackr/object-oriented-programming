import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class Card{
    public String suit;
    public String value;
    public int intValue;

    public Card(String suit, String value, int intValue){
        this.suit = suit;
        this.value = value;
        this.intValue = intValue;
    }

    public String getCardString(){
        return this.suit + this.value + "(" + this.intValue + ")";
    }

}

class Deck{
 
    public ArrayList<Card> deck;
    
    public Deck(Table table){
        this.deck = Deck.generateDeck(table);
    }

    public static ArrayList<Card> generateDeck(Table table){
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        Map<String, Integer> blackJack = new HashMap<>(){
            {
                put("A", 1);
                put("J", 10);
                put("Q", 10);
                put("K", 10);
            }
        };

        for (int i = 0; i < suits.length; i++){
            for (int j = 0; j < values.length; j++){
                if (table.gameMode == "21"){
                    newDeck.add(new Card(suits[i], values[j], (blackJack.get(values[j]) == null)? j + 1: blackJack.get(values[j])));
                } else {
                    newDeck.add(new Card(suits[i], values[j], j + 1));
                }
            }
        }
        
        return newDeck;
    }

    public Card draw(){
        return this.deck.remove(this.deck.size() - 1);
    }

    public void printDeck(){
        System.out.println("Printing cards...");
        for (int i = 0; i < this.deck.size(); i++){
            System.out.println(this.deck.get(i).getCardString());
        }
    }

    public void shuffleDeck() {
        for (int i = this.deck.size() - 1; i >= 0; i--){
            Card temp = this.deck.get(i);
            int j = (int)Math.floor(Math.random() * (i + 1));
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j, temp);
        }
    }
}    

class Table{
    public int amountOfPlayers;
    public String gameMode;

    public Table(int amountOfPlayers, String gameMode){
        this.amountOfPlayers = amountOfPlayers;
        this.gameMode = gameMode;
    }
}

class Dealer{
    public static ArrayList<ArrayList<Card>> startGame(Table table) {
        Deck deck = new Deck(table);
        deck.shuffleDeck();
        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();

        for (int i = 0; i < table.amountOfPlayers; i++){
            ArrayList<Card> playerHand = new ArrayList<>(initialCards(table.gameMode));
            for (int j = 0; j < initialCards(table.gameMode); j++){
                playerHand.add(deck.draw());
            }
            playerCards.add(playerHand);
        }

        return playerCards;
    }


    public static int initialCards(String gameMode) {
        if (gameMode == "poker") return 5;
        if (gameMode == "21") return 2;
        return 0;
    }


    public static void printTableInformation(ArrayList<ArrayList<Card>> playerCards, Table table) {
        System.out.println("Amount of players: " + table.amountOfPlayers +"... Game mode: " + table.gameMode + ". At this table: ");

        for (int i = 0; i < playerCards.size(); i++){
            System.out.println("player " + (i + 1) + " hand is: ");

            for (int j = 0; j < playerCards.get(i).size(); j++){
                System.out.println(playerCards.get(i).get(j).getCardString());
            }
            System.out.println();
        }
    }

    public static int score21Individual(ArrayList<Card> cards) {
        int total = 0;

        for (int i = 0; i < cards.size(); i++){
            total += cards.get(i).intValue;
        }

        return total;
    }

    public static String winnerOf21(ArrayList<ArrayList<Card>> playerCards) {
        int[] points = new int[playerCards.size()];
        Map <Integer, Integer> cache = new HashMap<>();

        for (int i = 0; i < playerCards.size(); i++){
            int point = score21Individual(playerCards.get(i));
            points[i] = point;

            cache.put(point, cache.getOrDefault(point, 0) + 1);
        }

        int winnerIdx = HelperFunctions.maxInArrayIndex(points);
        if (cache.get(points[winnerIdx]) > 1) return "It is a draw...";
        else if (cache.get(points[winnerIdx]) >= 0) return "Player " + (winnerIdx + 1) + " is winner!!";
        else return "no winners";
    } 

    public static String checkWinner(ArrayList<ArrayList<Card>> playerCards, Table table){
        if (table.gameMode == "21") return winnerOf21(playerCards);
        else return "no game";
    }
}

class HelperFunctions {

    public static int maxInArrayIndex(int[] intArr) {
        int maxIdx = 0;
        int maxValue = 0;

        for (int i = 1; i < intArr.length; i++){
            if (intArr[i] > maxValue){
                maxIdx = i;
                maxValue = intArr[i];
            }
        }
        return maxIdx;
    }
}

class Main{
        
    public static void main(String[] args){
        // poker
        Table table1 = new Table(3, "poker");
        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(table1);
        Dealer.printTableInformation(game1, table1);
        System.out.println(Dealer.checkWinner(game1, table1));

        // blackjack
        Table table2 = new Table(4, "21");
        ArrayList<ArrayList<Card>> game2 = Dealer.startGame(table2);
        Dealer.printTableInformation(game2, table2);
        System.out.println(Dealer.checkWinner(game2, table2));
    }
}
