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

// is-a relationship
// a mammal is an animal/ 'an animal is a mammal' is not correct
class Mammal extends Animal{
    private double furLengthCm;
    private String furType;
    private int toothCounter;
    private double bodyTemperatureC;
    private double avgBodyTemperatureC;
    private boolean mammaryGland = false;
    private boolean sweatGland = true;
    private boolean isPregnant = false;

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

    // 親クラスで定義されたmethodをoverride
    public void move(){
        if (!isAlive()) return;
        System.out.println("This mammal is moving...");
        System.out.println();
    }

    public void eat(){
        super.eat();
        bite();
        System.out.println("This mammal is eating...");
    }

    public String toString(){
        return super.toString() + mammalInformation();
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

    public void increaseBodyHeat(int celcius){
        bodyTemperatureC += celcius;
    }

    public void decreaseBodyHeat(int celcius){
        bodyTemperatureC -= celcius;
    }

    public void adjustBodyHeat(){
        bodyTemperatureC = avgBodyTemperatureC;
    }

    public String mammalInformation(){
        return "This is a mammal with the following - " + "fur:" + furType + "/teethReplaced:"+ (toothCounter>0) + "/Pregnant:" + isPregnant() + "/Body Temperature:" + bodyTemperatureC;
    }
}

class Main{
    public static void main(String[] args){
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
