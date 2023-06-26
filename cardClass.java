class Card{
    public String suit;
    public String value;
    public String intValue;

    public Card(String suit, String value, String intValue){
        this.suit = suit;
        this.value = value;
        this.intValue = intValue;
    }

    public String toString(){
        return this.suit + this.value + "(" + this.intValue + ")";
    }
}

class MyClass{
    public static void printCardArray(Card[] cardArr){
        for (Card card: cardArr) System.out.println(card.toString());
    }

    public static void main(String[] args){
        Card[] cards = {new Card("♣", "A", "1"), new Card("♦", "K", "13"), new Card("♥", "Q", "12"), new Card("♠", "J", "11")};
        printCardArray(cards);
    }
}
