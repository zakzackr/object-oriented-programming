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
    public static final String[] SUITS = {"♠","♡","♢","♣"};
    public static final String[] RANKS = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    public Card[] cards;

    public Deck(){
        this.cards = Deck.createDeck();
    }

    public static Card[] createDeck(){
        int s = SUITS.length;
        int r = RANKS.length;
        Card[] cards = new Card[s * r];

        for (int i = 0; i < s; i++){
            for (int j = 0; j < r; j++){
                cards[i * r + j] = new Card(Deck.RANKS[j], Deck.SUITS[i]);
            }
        }

        return cards;
    }

    // 現在のDeck オブジェクトのカードをin-placeでシャッフルするため、状態が必要となる
    public void shuffleDeck(){
        Card[] cards = this.cards;
        for (int i = cards.length - 1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    // インスタンスメソッドのshuffleDeck()の代わりに、引数にカードの配列を受け取るクラスメソッドを作成
    // 副作用:入力の配列が変更される
    public static Card[] shuffleDeckInPlace(Card[] inputCards){
        for (int i = inputCards.length - 1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card temp = inputCards[i];
            inputCards[i] = inputCards[j];
            inputCards[j] = temp;
        }

        return inputCards;
    }

    // 副作用を避けるため、引数の入れるをディープコピーしてからシャッフルを行う
    public static Card[] shuffleDeckOutOfPlace(Card[] inputCards){
        Card[] copyArr = new Card[inputCards.length];
        for (int i = 0; i < inputCards.length; i++){
            copyArr[i] = inputCards[i];
        }

        for (int i = copyArr.length - 1; i >= 0; i--){
            int j = (int)Math.floor(Math.random() * (i + 1));
            Card temp = copyArr[i];
            copyArr[i] = copyArr[j];
            copyArr[j] = temp;
        }

        return copyArr;
    }

    public String toString(){
        return Deck.cardsToString(this.cards);
    }

    public static String cardsToString(Card[] inputCards){
        String output = "";
        for (Card card: inputCards){
            output += card.toString();
        }

        return output;
    }
}

class Main{
    public static void main(String[] args){
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffleDeck();  // インスタンスメソッド
        System.out.println(deck);

        Card[] card1 = Deck.createDeck();
        System.out.println(Deck.cardsToString(card1));
        System.out.println(Deck.cardsToString(Deck.shuffleDeckInPlace(card1)));
        System.out.println(Deck.cardsToString(card1));  // 入力配列もシャッフルされた

        Card[] card2 = Deck.createDeck();
        System.out.println(Deck.cardsToString(card2));
        System.out.println(Deck.cardsToString(Deck.shuffleDeckOutOfPlace(card2)));
        System.out.println(Deck.cardsToString(card2));  // 入力配列は変更されていない
    }
}
