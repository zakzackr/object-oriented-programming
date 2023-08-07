interface Audible{
    // 抽象メソッドのみ定義可能
    abstract public void makeNoise();
    abstract public double soundFrequency();
    abstract public double soundLevel();
}

class Name{
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
}

class Person implements Audible{
    private Name name;  // has-a関係
    private int age;

    public Person(String firstName, String lastName, int age){
        this.name = new Name(firstName, lastName);  // Composition
        this.age = age;
    }

    public void makeNoise(){
        System.out.println("Hello World!! I am a human...");
    }

    public double soundFrequency(){
        return age > 20 ? 110: 130;
    }

    public double soundLevel(){
        return age > 20? 60: 65;
    }

    public String toString(){
        return "I am " + name.getFullName() + " and " + age + " years old!!";
    }

    public void sing(){
        System.out.println("Hello Hello~~~!!");
    }
}

class Horse implements Audible{
    private double speedKmh;
    private boolean isRaceHorse;

    public Horse(double speedKmh){
        this.speedKmh = speedKmh;
        this.isRaceHorse = speedKmh > 40;
    }

    public void makeNoise(){
        System.out.println("Neeighh!!");
    }

    public double soundFrequency(){
        return isRaceHorse? 150: 130;
    }

    public double soundLevel(){
        return isRaceHorse? 70: 75;
    }

    public String toString(){
        return "I am a " + (isRaceHorse? "race ": "domesticated ") + "horse that runs at " + speedKmh + " km/h";
    }
}

class Truck implements Audible{
    private double soundFrequency = 165;
    private double soundDecibels = 120;

    public Truck(){};

    public void makeNoise(){
        System.out.println("Beep Beep!!");
    }

    public double soundFrequency(){
        return soundFrequency;
    }

    public double soundLevel(){
        return soundDecibels;
    }

    public String toString(){
        return "I am a truck...";
    }
}

class Main{
    // Audibleを実装している任意のクラスのオブジェクトを取り込む
    public static void personInteractsWithObject(Person p, Audible audibleObj){
        System.out.println(p + "interacts with " + audibleObj);
        System.out.println("The Audible object makes sound at " + audibleObj.soundFrequency() + "Hz and " + audibleObj.soundLevel() + " Db.");
        System.out.println();
    }

    public static void main(String[] args){
        Person p1 = new Person("Luis", "George", 22);
        Horse h1 = new Horse(41.0);
        Audible p2 = new Person("Harry", "Lee", 12);
        Truck t1 = new Truck();

        personInteractsWithObject(p1, h1);
        personInteractsWithObject(p1, p2);
        personInteractsWithObject(p1, t1);
        // personInteractsWithObject(p2, t1); // error

        // p2.sing();  // p2はAudible objとして定義されているから、Person class独自のmethodを実装できない
        if (p2 instanceof Person) ((Person) p2).sing();  // オブジェクトキャスティングを使用して、Person class独自のmethodを実行
    }
}
