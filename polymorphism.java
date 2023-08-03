import java.util.Date;
import java.text.SimpleDateFormat;

class BMI{
    private double heightM;
    private double weightKg;

    public BMI(double heightM, double weightKg){
        this.heightM = heightM;
        this.weightKg = weightKg;
    }

    public double getBMI(){
        return weightKg / (heightM * heightM);
    }
}

class Animal{
    protected String species;
    protected BMI bmi;
    protected double lifeSpanDays;
    protected String biologicalSex;
    protected int hungerPercent = 100;
    protected Date spawnTime;  // コンストラクタが呼ばれる時間
    protected Date deathTime;  // default = null

    public Animal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        this.species = species;
        this.bmi = new BMI(heightM, weightKg);  // コンポジッション
        this.lifeSpanDays = lifeSpanDays;
        this.biologicalSex = biologicalSex;
        this.spawnTime = new Date();
    }

    public void eat(){
        if (!isAlive()) return;
        System.out.println("This animal is eating...");
        hungerPercent = 0;
    }

    public boolean isHungry(){
        return hungerPercent >= 60;
    }

    public void setAsHungry(){
        if (!isAlive()) return;
        hungerPercent = 100;
    }

    public void die(){
        System.out.println("This animal has just died...");
        deathTime = new Date();
    }

    public boolean isAlive(){
        return deathTime == null;
    }

    public String toString(){
        System.out.println("This method is called from the animal class");
        return "species: " + species + ", BMI: " + bmi.getBMI() + ", lives for " + lifeSpanDays + ", gender: " + biologicalSex + ", birthDate: " + dateCreated() + ", " + dateDeleted();
    }

    public String dateCreated(){
        return new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").format(spawnTime);
    }

    public String dateDeleted(){
        if (deathTime == null) return "This animal is still alive!!";
        return "This animal died at " + new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").format(deathTime);
    }
}

class Mammal extends Animal{
    protected double furLengthCm;   
    protected String furType;
    protected boolean mammaryGland = false;
    protected boolean isPregnant = false;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        this.furLengthCm = furLengthCm;
        this.furType = furType;
        this.mammaryGland = (biologicalSex == "female");
    }

    public void eat(){
        super.eat();
        System.out.println("This mammal is eating...");
    }

    public void fertalize(){
        if (!isAlive()) return;
        isPregnant = true;
    }

    public void mate(Mammal mammal){
        if (!isAlive()) return;
        if (species != mammal.species) return;

        if (biologicalSex == "female" && mammal.biologicalSex == "male") fertalize();
        else if (biologicalSex == "male" && mammal.biologicalSex == "female") mammal.fertalize();
    }

    public boolean isPregnant(){
        return isPregnant;
    }

    public String toString(){
        return super.toString() + " This mammal is " + (isPregnant()? "pregnant!!": "not pregnant.") ;
    }
}

class Person extends Mammal{
    private Name name;
    private String country;
    private String city;


    public Person(double heightM, double weightKg, String biologicalSex, String firstName, String lastName, String country, String city){
        super("human", heightM, weightKg, 36000, biologicalSex, 0, "Human");

        this.name = new Name(firstName, lastName);  // コンポジッション
        this.country = country;
        this.city = city;
    }

    public void eat(){
        hungerPercent = 0;
        System.out.println(name.getName() + " is eating...");
    }

    public String toString(){
        return super.toString() + personInformation();
    }

    public String personInformation(){
        return name.getName() +  "is from " + city + ", " + country;
    }

    public void sing(){
        System.out.println("hahaha~~~");
    }
}

class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }
}


class Main{
    // Animalクラスを継承しているオブジェクトであれば、どんな種類のものでも渡すことができる
    // それはそのクラスがAnimalの全てのメンバ変数とメソッドを持つことが保証されているから
    public static void animalPolymorphism(Animal animal){
        System.out.println(animal);
        animal.eat();
        animal.die();
        System.out.println(animal);

        System.out.println();

        // 全ての動物が歌うわけではなく、人間のみが歌う。Animalのサブクラスが持つメソッドは実行できない
        // animal.sing();
    }

    public static void main(String[] args){
        Person personM = new Person(1.81, 75, "male", "Nick", "Davis", "USA", "New York");
        Mammal personF = new Mammal("human", 1.6, 49, 36000, "female", 0, "Human");

        Animal horse = new Animal("horse", 1.75, 200, 5000, "female");
        Mammal whale = new Mammal("whale", 20, 2000, 4000, "male", 5, "whale");

        animalPolymorphism(personM);
        animalPolymorphism(horse);
        animalPolymorphism(whale);

        System.out.println("Checking whether methods works intentionally...");
        System.out.println(personF);
        personF.mate(personM);
        System.out.println(personF);
    }
}
