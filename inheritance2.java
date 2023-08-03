import java.util.Date;
import java.text.SimpleDateFormat;

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

    public double getWeightKg(){
        return this.weightKg;
    }

    public String toString(){
        return heightM + " meters, " + weightKg + "kg, BMI:" + getValue();
    } 
}

class Animal{
    protected String species;
    protected BMI bmi;
    protected double lifeSpanDays;
    protected String biologicalSex;
    protected Date spawnTime;
    protected Date deathTime;  // デフォルトではnull
    protected int hungerPercent = 100;
    protected int sleepPercent = 100;

    public Animal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex){
        this.species = species;
        this.bmi = new BMI(heightM, weightKg); // AnimalとBMIはコンポジッションの関係: Animal objが削除されると、BMI objも削除される
        this.lifeSpanDays = lifeSpanDays;
        this.biologicalSex = biologicalSex;
        this.spawnTime = new Date();
    }

    public void eat(){
        if (!isAlive()) return;
        hungerPercent = 0;
    }

    public void move(){
        if(!this.isAlive()) return;
        System.out.println("This animal just moved...");
    }

    public void setAsHungry(){
        if (!isAlive()) return;
        hungerPercent = 100;
    }

    public boolean isHungry(){
        return hungerPercent >= 70;
    }

    public void sleep(){
        if (!isAlive()) return;
        sleepPercent = 0;
    }

    public void setAsSleepy(){
        if (!isAlive()) return;
        sleepPercent = 100;
    }

    public boolean isSleepy(){
        return sleepPercent >= 70; 
    }

    public void die(){
        sleepPercent = 0;
        hungerPercent = 0;
        deathTime = new Date();
    }

    public String toString(){
        return species + " " + bmi + " lives " + lifeSpanDays + " days/" + "gender:" + biologicalSex + "." + status();
    }

    public String status(){
        return species + " status:" + " Hunger - " + hungerPercent + "%, " + "sleepiness:"+ sleepPercent + "%" + ", Alive - " + isAlive() + ". First created at " + dateCreated() + ", " + dateDied();
    }

    public boolean isAlive(){
        return deathTime == null;
    }

    public String dateCreated(){
        return new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").format(spawnTime);
    }

    public String dateDied(){
        if (deathTime == null) return species + " is alive!!";
        return "Died at " + new SimpleDateFormat("yyyy/mm/dd HH:mm:ss").format(deathTime);
    }
}


class Mammal extends Animal{
    protected double furLengthCm;
    protected String furType;
    protected int toothCounter;
    protected double bodyTemperatureC;
    protected double avgBodyTemperatureC;
    protected boolean mammaryGland = false;
    protected boolean sweatGland = true;
    protected boolean isPregnant = false;

    public Mammal(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double furLengthCm, String furType, double avgBodyTemperatureC){
        // Animalから継承したメンバ変数の初期状態を設定
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        // Mammalのメンバ変数の初期状態を設定
        this.furLengthCm = furLengthCm;
        this.furType = furType;
        this.mammaryGland = (biologicalSex == "female");
        this.avgBodyTemperatureC = avgBodyTemperatureC;
        this.bodyTemperatureC = avgBodyTemperatureC;
    }

    // Mammal独自のメソッド
    public void sweat(){
        if (!isAlive()) return;
        if (sweatGland) System.out.println("Sweating...");
        bodyTemperatureC -= 3;
        System.out.println("Body temperature is now " + bodyTemperatureC + "C");

        System.out.println();
    }

    public void produceMilk(){
        if (!isAlive()) return;
        if (isPregnant() && mammaryGland) System.out.println("Producing milk...");
        else System.out.println("Cannot produce milk");

        System.out.println();
    }

    public void mate(Mammal mammal){
        if (!isAlive()) return;
        if (species != mammal.species) return;
        if (biologicalSex == "male" && mammal.biologicalSex == "female") mammal.fertalize();
        else if (biologicalSex == "female" && mammal.biologicalSex == "male") fertalize();
    }

    public void fertalize(){
        if (!isAlive()) return;
        isPregnant = true;
    }

    public boolean isPregnant(){
        if (!isAlive()) return false;
        return isPregnant;
    }

    public void bite(){
        if (!isAlive()) return;
        System.out.println(species + " bites with its single lower jaws which has " + (toothCounter == 0? "not": "") + "replaced.");
        System.out.println();
    }

    public void replaceTeeth(){
        if (!isAlive()) return;
        if (toothCounter == 0) toothCounter++;
    }

    public void increaseBodyHeat(double celcius){
        bodyTemperatureC += celcius;
    }

    public void decreaseBodyHeat(double celcius){
        bodyTemperatureC -= celcius;
    }

    public void adjustBodyHeat(){
        bodyTemperatureC = avgBodyTemperatureC;
    }

    public String toString(){
        return super.toString() + "\n" + mammalInformation();
    }

    public String mammalInformation(){
        return "This is a mammal with the following - " + "fur:" + furType + "/teethReplaced:"+ (toothCounter>0) + "/Pregnant:" + isPregnant() + "/Body Temperature:" + bodyTemperatureC;
    }
}

class Person extends Mammal{
    private String firstName;
    private String lastName;
    private String job;
    private double salary;
    
    // super()クラスは親クラス(Mammal class)のコンストラクタへの参照
    public Person(double heightM, double weightKg, String biologicalSex, String firstName, String lastName, String job, double salary){
        super("Human", heightM, weightKg, 36500, biologicalSex, 0, "Human", 36.5);

        this.firstName = firstName;
        this.lastName = lastName;
        this.job = job;
        this.salary = salary;
    }

    public void move(){
        System.out.println(firstName + " " + lastName + " is heading to a different place...");
        hungerPercent -= 10;
    }

    public void eat(){
        super.eat();
        System.out.println(firstName + " " + lastName + " has just had a meal...");
    }

    public void workout(){
        hungerPercent -= 50;
        System.out.println(firstName + " " + lastName + " is craving for food");
    }

    public String toString(){
        return super.toString() + "\n" + personInformation();
    }

    public String personInformation(){
        return "Name :" + firstName + " " + lastName + ". Job: " + job + ". salary: " + salary; 
    }
}

class Horse extends Mammal{
    private String type;
    private String country;
    private int winCounter;  // default = 0

    public Horse(double heightM, double weightKg, String biologicalSex, String type, String country){
        super("Horse", heightM, weightKg, 2000, biologicalSex, 30, "Horse", 39);

        this.type = type;
        this.country = country;
    }

    public boolean isRaceHorse(){
        return type == "race";
    }

    public int getTotalAward(){
        if (!isRaceHorse()){
            System.out.println("This horse is domesticated");
            return getWinCounter();
        } else {
            System.out.println("This horse takes part in competitions!!");
            return getWinCounter() * 1000;
        }
    }

    public int getWinCounter(){
        return this.winCounter;
    }

    public void winRace(){
        winCounter++;
    }

    public String horseInformation(){
        return "This horse is a " + type + " horse from " + country + "." + (type  == "race"? " It has won " + winCounter + " races!!": " It has not won any races as it is domesticated..."); 
    }
}

class Fish extends Animal{
    protected double depthLevelM;
    protected double speedKmH;
    protected String livingPlace;

    public Fish(String species, double heightM, double weightKg, double lifeSpanDays, String biologicalSex, double depthLevelM, double speedKmH, String livingPlace){
        super(species, heightM, weightKg, lifeSpanDays, biologicalSex);

        this.depthLevelM = depthLevelM;
        this.speedKmH = speedKmH;
        this.livingPlace = livingPlace;
    }

    public String toString(){
        return super.toString() + fishInformation();
    }

    public String fishInformation(){
        return "This fish lives in the " + livingPlace + " at the depth of " + depthLevelM + " meters, swims at " + speedKmH + " km/h.";
    }
}

class Tuna extends Fish{
    private String type;
    private double value;  // methodで設定

    public Tuna(double heightM, double weightKg, String biologicalSex, String type){
        super("Tuna", heightM, weightKg, 2000, biologicalSex, 100, 80, "sea");

        this.type = type;
    }

    // コンストラクタに引数として渡されてるだけだから、アクセスできないスコープ
    // public double getTunaValue(){
    //     if (weightKg >= 100) this.value = 10000;
    //     else if (weightKg >= 50) this.value = 5000;
    //     else if (weightKg >= 25) this.value = 2000;
    //     else this.value = 500;

    //     return this.value;
    // }

    public double getTunaValue(){
        if (bmi.getWeightKg() >= 100) this.value = 10000;
        else if (bmi.getWeightKg() >= 50) this.value = 5000;
        else if (bmi.getWeightKg() >= 25) this.value = 2000;
        else this.value = 500;

        return this.value;
    }

    public boolean isEdible(){
        return type == "food";
    }

    public String toString(){
        return super.toString() + " This tuna's type is " + type + " and is worth " + getTunaValue() + " dollers!!";
    }
}

class Main{
    public static void main(String[] args){
        Person male = new Person(1.81, 75, "male", "John", "White", "engineer", 100000);
        Person female = new Person(1.7, 52, "female", "Kate", "Suzuki", "doctor", 150000);
        System.out.println("== male ==");
        System.out.println(male);
        System.out.println(male.isHungry());
        System.out.println(male);
        male.increaseBodyHeat(0.5);
        System.out.println(male.mammalInformation());

        System.out.println("== female ==");
        System.out.println(female);
        female.mate(male);
        System.out.println(female);

        System.out.println("== horse ==");
        Horse raceHorse = new Horse(1.8, 180, "male", "race", "Japan");
        System.out.println(raceHorse.isRaceHorse());
        System.out.println(raceHorse.getWinCounter());
        raceHorse.winRace();
        System.out.println(raceHorse.getWinCounter());
        System.out.println(raceHorse.getTotalAward());
        System.out.println(raceHorse.horseInformation());
        System.out.println(raceHorse);
        raceHorse.sweat();
        raceHorse.eat();
        raceHorse.move();
        System.out.println(raceHorse);

        System.out.println("== fish ==");
        Fish tunaAsFish = new Tuna(1, 50, "male", "food");
        // tunaAsFish.isEdible();  // tunaAsFishはFish classのobjとして定義しているからTuna classのmethodにはaccessできない
        System.out.println(tunaAsFish.fishInformation());

        Tuna tuna = new Tuna(2, 120, "female", "food");
        System.out.println(tuna.isEdible());
        System.out.println(tuna);

        System.out.println("== mammal ==");
        Mammal dogF = new Mammal("dog", 0.5, 30, 5000, "female", 1.1, "dog", 38.5);
        Mammal dogM = new Mammal("dog", 0.5, 20, 10000, "male", 0.2, "dog", 38.5);
        Mammal tigerF = new Mammal("Tiger", 0.9, 140, 4745, "female", 1.1, "Tiger Hair", 39.5);
        Mammal tigerM = new Mammal("Tiger", 1.1, 280, 4045, "male", 1.2, "Tiger Hair", 39.0);

        System.out.println(tigerF);
        tigerF.eat();
        tigerF.sleep();
        System.out.println(tigerF);
        tigerF.die();
        System.out.println(tigerF);

        dogF.setAsHungry();
        System.out.println(dogF.status());
        dogF.mate(tigerM);
        System.out.println(dogF);
        System.out.println(dogF.isPregnant());
        dogF.produceMilk();
        dogF.mate(dogM);
        System.out.println(dogF);
        dogF.produceMilk();

        dogF.replaceTeeth();
        dogF.bite();

        System.out.println(dogM.mammalInformation());
    }
}
