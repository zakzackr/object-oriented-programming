abstract class Numeric{
    // byte (8bit): -128 ~ 127
    public byte getByte(){
        return (byte)getInteger();
    }

    // short (16bit): -2^15 ~ 2^15 - 1
    public short getShort(){
        return (short)getInteger();
    }

    // long (64bit): -2^63 ~ 2^63 - 1
    public long getLong(){
        return (long)getInteger();
    }

    public char getChar(){
        return (char) getInteger();
    }

    // 具体的な実装は具象クラスで
    public abstract int getInteger();
    public abstract double getDouble();

    public String toString(){
        return getClass().getSimpleName() + " of int value: " + getInteger();
    }
}

class IntegerNumeric extends Numeric{
    private int value;

    public IntegerNumeric(int value){
        this.value = value;
    }

    public int getInteger(){
        return this.value;
    }

    public double getDouble(){
        return this.value + 0.0;
    }
}

class CharNumeric extends Numeric{
    private char c;

    public CharNumeric(char c){
        this.c = c;
    }

    // 暗黙の型変換
    public int getInteger(){
        return this.c;
    }

    public double getDouble(){
        return getInteger() + 0.0;
    }
}

class HexNumeric extends Numeric{
    private String hex;

    public HexNumeric(String hex){
        this.hex = hex;
    }

    public int getInteger(){
        return Integer.parseInt(hex, 16);
    }

    public double getDouble(){
        return getInteger() + 0.0;
    }
}

class OctNumeric extends Numeric{
    private String oct;

    public OctNumeric(String oct){
        this.oct = oct;
    }

    public int getInteger(){
        return Integer.parseInt(oct, 8);
    }

    public double getDouble(){
        return getInteger() + 0.0;
    }
}

class BigDecimalNumeric extends Numeric{
    private String bigNum;

    public BigDecimalNumeric(String bigNum){
        this.bigNum = bigNum;
    }

    public int getInteger(){
        return Integer.parseInt(bigNum);
    }

    public double getDouble(){
        return getInteger() + 0.0;
    }

    public double add(BigDecimalNumeric bigDecNumeric){
        return getDouble() + bigDecNumeric.getDouble();
    }
}

class Main{
    public static void printNumeric(Numeric numeric){
        System.out.println(numeric);
        System.out.println("byte: " + numeric.getByte());
        System.out.println("short: " + numeric.getShort());
        System.out.println("long: " + numeric.getLong());
        System.out.println("double: " + numeric.getDouble());
        System.out.println("char: " + numeric.getChar());
        System.out.println();
    }

    public static void main(String[] args){
        Numeric num1 = new IntegerNumeric(100);
        Numeric num2 = new CharNumeric('g');
        Numeric num3 = new HexNumeric("1234abcd");
        Numeric num4 = new OctNumeric("671");
        Numeric num5 = new BigDecimalNumeric("56789");

        printNumeric(num1);
        printNumeric(num2);
        printNumeric(num3);
        printNumeric(num4);
        printNumeric(num5);
    }
}
