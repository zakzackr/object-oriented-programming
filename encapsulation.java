import java.util.Calendar;

class Person{
    private String firstName;
    private String lastName;
    private double heightM;
    private double weightKg;
    private int birthYear;

    public Person(String firstName, String lastName, double heightM, double weightKg, int birthYear){
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightM = heightM;
        this.weightKg = weightKg;
        this.birthYear = birthYear;
    }

    private String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public int getAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.birthYear;
    }

    public void changeName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString(){
        return "name: " + this.getFullName() + ", height: " + this.heightM + "m, weight: " + this.weightKg + "kg, age: " + this.getAge();
    }
}

class Main{
    public static void main(String[] args){
        Person Mikel = new Person("Mikel", "Suzuki", 1.81, 71, 1998);
        System.out.println(Mikel);
        System.out.println(Mikel.getAge());

        // private keywordで定義されているためerrorが発生する
        // System.out.println(Mikel.firstName);
        // System.out.println(Mikel.getFullName());

        Mikel.changeName("John", "Suzuki");
        System.out.println(Mikel);
    }
}
