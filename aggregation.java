// association > aggregation > composition
// 集約(aggregation)は、Person classがその状態の一部としてWallet classを含む形の関連を示す
// Person オブジェクトは Wallet の所有者で、Person は Wallet を集約し、Wallet は Person オブジェクトによって集約される
// しかし、Personオブジェクトがメモリから削除されても、Walletはメモリ上に独立して存在することができる

// コンポジション(composition): 集約の特殊なタイプで、集約されるオブジェクト(Walletオブジェクト)が集約するオブジェクト(Personオブジェクト)に対して依存性を持つ
// コンポジションでは、所有者が完全な支配権を持つため、集約先のオブジェクトはそれ自体で存在することはできない
// e.g. PersonとWallet classの間にcompositionが適用されている場合は、Personオブジェクトが削除されるとWalletオブジェクトも削除される

class Wallet{
    private int bill1;
    private int bill5;
    private int bill10;
    private int bill20;
    private int bill50;
    private int bill100;

    public Wallet(){}

    public int getTotalMoney(){
        return 1*bill1 + 5*bill5 + 10*bill10 + 20*bill20 + 50*bill50 + 100*bill100;
    }

    public int insertBill(int bill, int amount){
        switch(bill){
            case 1:
                bill1 += amount;
                break;
            case 5:
                bill5 += amount;
                break;
            case 10:
                bill10 += amount;
                break;
            case 20:
                bill20 += amount;
                break;
            case 50:
                bill50 += amount;
                break;
            case 100:
                bill100 += amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }
}

class Person{
    private String firstName;
    private String lastName;
    private int age;
    // PersonクラスはWalletクラスを持つ（集約）
    private Wallet wallet;  

    public Person(String firstName, String lastName, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

        // Personオブジェクトが作成されるときに、新たなWalletオブジェクトも作成される
        // 集約関係のため、Personオブジェクトが削除された場合でも、Walletオブジェクトはメモリ上に独立して存在することができる
        this.wallet = new Wallet();
    }

    public int getCash(){
        if (wallet == null) return 0;
        return wallet.getTotalMoney();
    }

    public int receiveBill(int bill, int amount){
        if (wallet == null) wallet = new Wallet();
        return wallet.insertBill(bill, amount);
    }

    public Wallet dropWallet(){
        Wallet dropped = wallet;
        wallet = null;
        return dropped;
    }

    public void addWallet(Wallet wallet){
        if (this.wallet == null) this.wallet = wallet;
    }

    public void printState(){
        System.out.println("name: " + firstName + " " + lastName);
        System.out.println("total money: " + getCash());
    }
}

class Main{
    public static void main(String[] args){
        Person scott = new Person("Scott", "Aoki", 30);
        scott.receiveBill(100, 10);
        scott.receiveBill(10, 5);
        scott.printState();

        // scottが財布を落とし、その後scottを削除
        // ここでscottWalletとscottの関連性は無くなるが、scottWalletのみ存在し続ける
        Wallet scottWallet = scott.dropWallet();
        scott.printState(); 
        scott = null;

        // 新たなPersonオブジェクトを作成
        Person tom = new Person("Tom", "Aida", 35);
        tom.dropWallet();
        // scottWalletを新しいtomオブジェクトのwalletに設定
        // scottWalletオブジェクトは新たにtomオブジェクトと関連付けられる（PersonとWalletの集約関係）
        tom.addWallet(scottWallet);
        tom.printState();
    }
}
