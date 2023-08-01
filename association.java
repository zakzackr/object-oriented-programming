class Wallet{
    private int bill1;
    private int bill5;
    private int bill10;
    private int bill20;
    private int bill50;
    private int bill100;

    public Wallet(){};

    public Wallet(int bill1, int bill5, int bill10, int bill20, int bill50, int bill100){
        this.bill1 = bill1;
        this.bill5 = bill5;
        this.bill10 = bill10;
        this.bill20 = bill20;
        this.bill50 = bill50;
        this.bill100 = bill100;
    }

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


    public int removeBill(int bill, int amount){
        switch(bill){
            case 1:
                if (bill1 <= 0 || bill1 < amount) return 0;
                bill1 -= amount;
                break;
            case 5:
                if (bill5 <= 0 || bill5 < amount) return 0;
                bill5 -= amount;
                break;
            case 10:
                if (bill10 <= 0 || bill10 < amount) return 0;
                bill10 -= amount;
                break;
            case 20:
                if (bill20 <= 0 || bill20 < amount) return 0;
                bill20 -= amount;
                break;
            case 50:
                if (bill50 <= 0 || bill50 < amount) return 0;
                bill50 -= amount;
                break;
            case 100:
                if (bill100 <= 0 || bill100 < amount) return 0;
                bill100 -= amount;
                break;
            default:
                return 0;
        }

        return bill*amount;
    }

    public void printInsideWallet(){
        System.out.println("Inside wallet (bill1: " + bill1 + ", bill5: " + bill5 + ", bill10: " + bill10 + ", bill20: " + bill20 + ", bill50: " + bill50 + ", bill100: " + bill100 + ")");
    }
}

class Person{
    private String firstName;
    private String lastName;
    private int age;
    private double heightM;
    private double weightKg;
    private Wallet wallet;  // unidirectional association
    private static String denomination = "highestFirst";

    public Person(String firstName, String lastName, int age, double heightM, double weightKg){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.wallet = new Wallet();
    }

    public int getCash(){
        if (wallet == null) return 0;
        return wallet.getTotalMoney();
    }

    public void addWallet(Wallet wallet){
        this.wallet = wallet;
    }

    public Wallet dropWallet(){
        Wallet dropped = wallet;
        wallet = null;

        return dropped;
    }

    public int[] getPaid(int money){
        int[] moneyTable = {1, 5, 10, 20, 50, 100};
        int[] addedMoney = allocateMoney(money);
        if (wallet == null) return addedMoney;

        for (int i = moneyTable.length-1; i >= 0; i--){
            this.wallet.insertBill(moneyTable[i], addedMoney[i]);
        }

        return addedMoney;
    }

    public int[] spendMoney(int money){
        int[] moneyTable = {1, 5, 10, 20, 50, 100};
        int[] spentMoney = allocateMoney(money);
        if (wallet == null) return spentMoney;

        for (int i = moneyTable.length-1; i >= 0; i--){
            wallet.removeBill(moneyTable[i], spentMoney[i]);
        }

        return spentMoney;
    }

    // 入力として受け取ったお金をdenominationで指定された方法で割り当てる
    public int[] allocateMoney(int money){
        int[] moneyTable = {1, 5, 10, 20, 50, 100};
        int[] allocatedMoney = new int[moneyTable.length];

        if (this.denomination == "highestFirst"){
            for (int i = moneyTable.length-1; i >= 0; i--){
                int count = money / moneyTable[i];
                if (count > 0){
                    allocatedMoney[i] = count;
                    money %= moneyTable[i];
                    if (money == 0) break;
                }
            }
        } else if (this.denomination == "dollars"){
            allocatedMoney[0] = money;
        } else {
            for (int i = moneyTable.length-3; i >= 0; i--){
                int count = money / moneyTable[i];
                if (count > 0){
                    allocatedMoney[i] = count;
                    money %= moneyTable[i];
                    if (money == 0) break;
                }
            }
        }

        return allocatedMoney;
    }

    public void setDenominationPreference(String denomination){
        this.denomination = denomination;
    }

    public void printState(){
        System.out.println("firstName: " + firstName);
        System.out.println("lastName: " + lastName);
        System.out.println("age: " + age);
        System.out.println("heightM: " + heightM);
        System.out.println("weightKg: " + weightKg);
        System.out.println("current Money: " + (wallet == null? "wallet does not exsist...": wallet.getTotalMoney()));
        if (wallet != null) wallet.printInsideWallet();
    }
}

class Main{
    public static void main(String[] args){
        Person John = new Person("John", "Suzuki", 18, 1.87, 85);
        John.printState();
        John.getPaid(123);
        John.printState();
        John.spendMoney(100);
        John.printState();
        John.dropWallet();
        John.printState();

        System.out.println();
        Person Kelly = new Person("Kelly", "Kato", 21, 1.67, 50);
        Kelly.printState();
        Kelly.setDenominationPreference("dollars");
        Kelly.getPaid(321);
        Kelly.printState();
        Kelly.spendMoney(322);
        Kelly.printState();
        Kelly.spendMoney(125);
        Kelly.printState();

        System.out.println();
        Person Amy = new Person("Amy", "Tanaka", 27, 1.57, 49);
        Amy.setDenominationPreference("twenties");
        Amy.getPaid(1236);
        Amy.printState();
        Amy.spendMoney(1220);
        Amy.printState();
        Amy.addWallet(new Wallet(0, 2, 20, 1, 2, 100));
        Amy.printState();
        Amy.spendMoney(20);
        Amy.printState();
    }
}

