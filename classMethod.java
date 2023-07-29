class Card{
    public String rank;
    public String suit;

    public Card(String rank, String suit){
        this.rank = rank;
        this.suit = suit;
    }

    public String toString(){
        return this.rank + this.suit;
    }
}

class Deck{
    // クラス変数:
    public static final String[] SUITS = {"♠","♡","♢","♣"};
    public static final String[] RANKS = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public Card[] cards;

    public Deck(){
        this.cards = Deck.createDeck();
    }

    // クラスメソッド
    public static Card[] createDeck(){
        int s = Deck.SUITS.length;
        int r = Deck.RANKS.length;
        Card[] cards = new Card[s * r];

        for (int i = 0; i < s; i++){
            for (int j = 0; j < r; j++){
                cards[i*r + j] = new Card(Deck.SUITS[i], Deck.RANKS[j]);
            }
        }

        return cards;
    }

    public void shuffleDeck(){
        for (int i = this.cards.length - 1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card temp = this.cards[i];
            this.cards[i] = this.cards[j];
            this.cards[j] = temp;
        }
    }

    public String toString(){
        return Deck.cardsToString(this.cards);
    }

    // クラスメソッド
    public static String cardsToString(Card[] cards){
        String output = "";
        for (int i = 0; i < cards.length; i++){
            output += cards[i].toString();
        }

        return output;
    }
}

class Main{
    public static void main(String[] args){
        Deck deck1 = new Deck();
        System.out.println(deck1);
        deck1.shuffleDeck();
        System.out.println(deck1);
    }
}
