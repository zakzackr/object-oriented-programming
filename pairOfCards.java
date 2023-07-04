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

    public Deck(){
        this.deck = Deck.generateDeck();
    }

    public static ArrayList<Card> generateDeck(){
        ArrayList<Card> newDeck = new ArrayList<>();
        String[] suits = new String[]{"♣", "♦", "♥", "♠"};
        String[] values = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] intValues = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        for (int i = 0; i< suits.length; i++){
            for (int j = 0; j < values.length; j++){
                newDeck.add(new Card(suits[i], values[j], intValues[j]));
            }
        }

        return newDeck;
    }

    public void printDeck(){
        System.out.println("printing the deck...");
        for (int i = 0; i < this.deck.size(); i++){
            System.out.println(this.deck.get(i).getCardString());
        }
    }

    public void shuffleDeck(){
        for (int i = this.deck.size() - 1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));

            Card temp = this.deck.get(i);
            this.deck.set(i, this.deck.get(j));
            this.deck.set(j, temp);
        }
    }

    public Card draw(){
        return this.deck.remove(this.deck.size() - 1);
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

// 名前空間
class Dealer{

    // ディーラーはカードをシャッフルしプレイヤーにカードを配る。
    public static ArrayList<ArrayList<Card>> startGame(Table table){
        Deck deck = new Deck();
        deck.shuffleDeck();

        ArrayList<ArrayList<Card>> playerCards = new ArrayList<>();

        for (int i = 0; i < table.amountOfPlayers; i++){
            ArrayList<Card> playerHand = new ArrayList<>();
            
            for (int j = 0; j < initialCards(table.gameMode); j++){
                playerHand.add(deck.draw());
            }
            playerCards.add(playerHand);
        }

        return playerCards;
    }

    // ゲームモードによって配るカードの枚数を返すメソッド
    public static int initialCards(String gameMode){
        if (gameMode == "poker") return 5;
        else if (gameMode == "21") return 2;
        else if (gameMode == "Pair of Cards") return 5;
        else return 0;
    }

    // テーブルの状態を表示するメソッド
    public static void printTableInformation(ArrayList<ArrayList<Card>> playerCards, Table table){
        System.out.println("Amount of players is: " + table.amountOfPlayers + " Game mode: " + table.gameMode);

        for (int i = 0; i < playerCards.size(); i++){
            System.out.println("Player " + (i + 1) + " hand is: ");
            for (int j = 0; j < playerCards.get(i).size(); j++){
                System.out.println(playerCards.get(i).get(j).getCardString());
            }
            System.out.println();
        }
    }

    // PairOfCardsの勝敗を決める関数
    public static String winnerPairOfCards(ArrayList<ArrayList<Card>> playerCards, Table table){
        // カードの強さ
        final int[] cardPower = new int[]{1, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        //数字だけの配列を作る。
        int[] numbers1 = HelperFunction.generateNumArr(playerCards.get(0));
        int[] numbers2 = HelperFunction.generateNumArr(playerCards.get(1));
        // 2人のプレイヤーの保持するカードをハッシュマップにする。
        Map<Integer, Integer> hashmap1 = HelperFunction.createHashmap(cardPower, numbers1);
        Map<Integer, Integer> hashmap2 = HelperFunction.createHashmap(cardPower, numbers2);
    
        String winner = "draw...";
        int maxCount = 0;

        for (int i = 0; i < cardPower.length; i++){
            if (hashmap1.get(cardPower[i]) > maxCount || hashmap2.get(cardPower[i]) > maxCount){
                if (hashmap1.get(cardPower[i]) > hashmap2.get(cardPower[i])){
                    winner = "player 1";
                    maxCount = hashmap1.get(cardPower[i]);
                }
                else if (hashmap2.get(cardPower[i]) > hashmap1.get(cardPower[i])){
                    winner = "player 2";
                    maxCount = hashmap2.get(cardPower[i]);
                }
            }
        }

        System.out.println("The winner of this game is ");
        return winner;
    }

    public static String checkWinner(ArrayList<ArrayList<Card>> playerCards, Table table){
        if (table.gameMode == "Pair of Cards") return winnerPairOfCards(playerCards, table);
        return "no game...";
    }
}

class HelperFunction{
    // 数字だけの配列を作る。
    public static int[] generateNumArr(ArrayList<Card> playerHand){
        int[] intArr = new int[playerHand.size()];

        for (int i = 0; i < playerHand.size(); i++){
            intArr[i] = playerHand.get(i).intValue;
        }

        return intArr;
    }

    // Hashmapを作成する。
    public static Map<Integer, Integer> createHashmap(int[] cardPower, int[] intArr){
        Map<Integer, Integer> hashmap = new HashMap<>();

        for (int i = 0; i < cardPower.length; i++){
            hashmap.put(cardPower[i], 0);
        }

        for (int i = 0; i < intArr.length; i++){
            hashmap.put(intArr[i], hashmap.getOrDefault(intArr[i], 0) + 1);
        }

        return hashmap;
    }
}

class Main{
    public static void main(String[] args){
        // Blackjack
        Table table1 = new Table(2, "21");
        ArrayList<ArrayList<Card>> game1 = Dealer.startGame(table1);
        Dealer.printTableInformation(game1, table1);
        System.out.println(Dealer.checkWinner(game1, table1));

        // Pair of Cards
        Table table2 = new Table(2, "Pair of Cards");
        ArrayList<ArrayList<Card>> game2 = Dealer.startGame(table2);
        Dealer.printTableInformation(game2, table2);
        System.out.println(Dealer.checkWinner(game2, table2));
    }
}
