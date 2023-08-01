// association > aggregation (weak Association) > composition (strong Association)
// Wallet, AddressはあるPersonオブジェクトが削除されても、他のPersonオブジェクトによって所有されることができる（集約）
// 集約: Personが消滅されてもWalletが他のスコープに存在し続けることができる場合
// 集約の場合は別の他の場所からオブジェクトを作成し、親オブジェクトにメソッドかコンストラクタで渡します

// しかし、あるPersonが削除されると、その人特有のBMIやNameは、他のPersonオブジェクトによって所有されることはできない(Composition)
// Composition: Personが消滅されるとBMI, Nameが他のスコープに存在し続けることができない場合
// Compositionの多くの場合、コンストラクタのところでその新しいオブジェクトが作成され、private変数にします

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
