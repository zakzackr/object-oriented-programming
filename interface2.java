interface Fly{
    public abstract void fly();
    public abstract double flightHeight();
    public abstract double flySpeed();
}

class Bird implements Fly{
    private String species;
    private String country;
    private double height;
    private double speed;

    public Bird(String species, String country, double height, double speed){
        this.species = species;
        this.country = country;
        this.height = height;
        this.speed = speed;
    }

    public String toString(){
        return "This " + species + " lives in " + country + "!!";
    }

    public void sing(){
        System.out.println("piyo piyo!!");
    }

    public void fly(){
        System.out.println("This bird is flying...");
    }

    public double flightHeight(){
        return this.height;
    }

    public double flySpeed(){
        return this.speed;
    }
}

class AirPlane implements Fly{
    private String type;
    private int capacity;
    private String destination;
    private double height;
    private double speed;

    public AirPlane(String type, int capacity, String destination, double height, double speed){
        this.type = type;
        this.capacity = capacity;
        this.destination = destination;
        this.height = height;
        this.speed = speed;
    }

    public String toString(){
        return type + " can accomodate " + capacity + " people!!";
    }

    public void fly(){
        System.out.println("This plane is flying to " + destination + "!!");
    }

    public double flightHeight(){
        return this.height;
    }

    public double flySpeed(){
        return this.speed;
    }
}

class Drone implements Fly{
    private String company;
    private int price;
    private int lastingHours;
    private int madeYear;
    private double height;
    private double speed;

    public Drone(String company, int price, int lastingHours, int madeYear, double height, double speed){
        this.company = company;
        this.price = price;
        this.lastingHours = lastingHours;
        this.madeYear = madeYear;
        this.height = height;
        this.speed = speed;
    }

    public String toString(){
        return "This drone was created by " + company + " in " + madeYear + " and it costs " + price + " dollers.";
    }

    public void fly(){
        System.out.println("This drone is flying...");
    }

    public double flightHeight(){
        return this.height;
    }

    public double flySpeed(){
        return this.speed;
    }
}

class Main{
    // fly interfaceを実装する任意のクラスのオブジェクトを受け取る
    public static void flyFunction(Fly flyObj){
        System.out.println(flyObj);
        flyObj.fly();  
        System.out.println(flyObj.flightHeight());
        System.out.println(flyObj.flySpeed());
        System.out.println();
    }

    public static void main(String[] args){
        Fly bird1 = new Bird("Pigeon", "Japan", 50, 25);
        flyFunction(bird1);
        // bird1.sing(); // sing methodはBird class内のmethod
        if (bird1 instanceof Bird) ((Bird) bird1).sing();

        Fly ariPlane1 = new AirPlane("Boeing777", 500, "Seoul", 20000, 1000);
        flyFunction(ariPlane1);

        Fly drone1 = new Drone("drone-X", 2000, 10, 2019, 100, 120);
        flyFunction(drone1);

        Bird bird2 = new Bird("eagle", "China", 200, 130);
        flyFunction(bird2);
        bird2.sing();
    }
}
