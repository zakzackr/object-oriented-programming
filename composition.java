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

// PersonはName/BMIを持っている
class Person{
    // Nameオブジェクトを参照。コンポジションの一部
    private Name name;
    // BMIオブジェクトを参照。コンポジションの一部
    private BMI bmi;
    private int age;
    // PersonクラスはWalletクラスを持つ（集約）
    private Wallet wallet;  
    private Address address;

    // NameとBMIの新しいインスタンスが作られ、コンポジションが形成される
    public Person(String firstName, String lastName, double heightM, double weightKg, int age, Address address){
        this.name = new Name(firstName, lastName);
        this.bmi = new BMI(heightM, weightKg);
        this.age = age;

        // 集約関係のため、Personオブジェクトが削除された場合でも、Wallet/Addressオブジェクトはメモリ上に独立して存在することができる
        this.wallet = new Wallet();
        this.address = address;
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
        System.out.println("Name - " + name);
        System.out.println("age - " + age);
        System.out.println("height and weight - " + bmi);
        System.out.println("Current Money - " + getCash());
        System.out.println("Address - " + address);
        System.out.println();
    }
}

// Person objが存在する限り、その人のName objも一緒に存在する(コンポジッション)
// Person objが削除されると、Name objも削除される
class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}

// Person objが存在する限り、その人のBMI objも一緒に存在する(コンポジッション)
// Person objが削除されると、BMI objも削除される
class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    public double getValue(){
        return weightKg/(heightM*heightM);
    }

    public String toString(){
        return this.heightM + " meters, " + this.weightKg + "kg, BMI:" + this.getValue();
    }
}

// Person objが削除されても、他のPerson objがその住所に住む（集約）
class Address{
    private String city;
    private String town;

    public Address(String city, String town){
        this.city = city;
        this.town = town;
    }

    public String toString(){
        return this.city + ", " + this.town;
    }
}

class Main{
    public static void main(String[] args){
        Address house = new Address("Seattle", "North Seattle");
        Person kai = new Person("Kai", "Ito", 1.83, 75, 28, house);
        Person gabriel = new Person("Gabriel", "Tanaka", 1.89, 85, 27, house);
        Person jack = new Person("Jack", "Tada", 1.71, 58, 30, house);

        kai.receiveBill(100, 100);

        kai.printState();
        gabriel.printState();
        jack.printState();

        Wallet kaiWallet = kai.dropWallet();

        kai = null;
        gabriel = null;
        jack.dropWallet();
        // kai objが削除されても、kaiWallet objは独立して存在することができる（PersonとWalletは集約関係にあるから）
        // しかし、kai objの一部であったName/BMI　objにはアクセスできない(PersonとName/BMIはコンポジッション関係にあるから)
        jack.addWallet(kaiWallet);
        jack.printState();
    }
}
