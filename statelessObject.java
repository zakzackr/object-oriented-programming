class Math{
    public static final double PI = 3.14;

    public static double circleSurfaceArea(double x){
        return x * x * PI;
    }

    public static double circumference(double x){
        return 2 * x * PI;
    }

    public static double boxVolume(double x){
        return x * x * x;
    }
}

class Main{
    public static void main(String[] args){
        System.out.println(Math.PI);
        System.out.println(Math.circleSurfaceArea(2));
        System.out.println(Math.circumference(2));
        System.out.println(Math.boxVolume(2));
    }
}
