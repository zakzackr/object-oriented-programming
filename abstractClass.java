import java.util.Date;
import java.text.SimpleDateFormat;

abstract class Shape2D{
    protected double scale = 1;
    protected String borderColor = "black";
    protected String backgroundColor = "white";
    protected Date createdTime;

    public Shape2D(){
        this.createdTime = new Date();
    }

    public double getScale(){
        return this.scale;
    }

    public void setScale(double scale){
        this.scale = scale;
    }

    public String getBorderColor(){
        return this.borderColor;
    }

    public void setBorderColor(String borderColor){
        this.borderColor = borderColor;
    }

    public String getBackgroundColor(){
        return this.backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor){
        this.backgroundColor = backgroundColor;
    }

    public String getDateCreated(){
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(createdTime);
    }

    public abstract String getDescription();
    public abstract double getArea();
    public abstract double getPerimeter();

    public String toString(){
        return getDescription() + " Created at " + getDateCreated();
    }
}  

class Square extends Shape2D{
    protected double l;

    public Square(double l){
        super();
        this.l = l;
    }

    public String getDescription(){
        return "This is a square!!";
    }

    public double getArea(){
        return l * l;
    }

    public double getPerimeter(){
        return 4 * l;
    }
}

class Rectangle extends Shape2D{
    protected double l;
    protected double h;

    public Rectangle(double l, double h){
        super();
        this.l = l;
        this.h = h;
    }

    public String getDescription(){
        return "This is a rectangle!!";
    }

    public double getArea(){
        return l * h;
    }

    public double getPerimeter(){
        return 2 * l + 2 * h;
    }
}

class Circle extends Shape2D{
    // protected static final PI = 3.14;
    protected double r;

    public Circle(double r){
        super();
        this.r = r;
    }

    public String getDescription(){
        return "This is a circle!!";
    }

    public double getArea(){
        return r * r * Math.PI;
    } 

    public double getPerimeter(){
        return 2 * r * Math.PI;
    }
}

class Main{
    public static void printShape(Shape2D shape){
        System.out.println(shape);
        System.out.println("Area: " + shape.getArea() + ", Perimeter: " + shape.getPerimeter());
    }

    public static void main(String[] args){
        Square square = new Square(2);
        Rectangle rectangle = new Rectangle(2, 4);
        Circle circle = new Circle(2);

        printShape(square);
        printShape(rectangle);
        printShape(circle);
    }
}
