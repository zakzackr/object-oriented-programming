class RGB24Immutable{
    private int red;
    private int green;
    private int blue;

    // コンストラクタを用いてオブジェクトが作成されると、その状態がロックされ変更することができない
    public RGB24Immutable(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public RGB24Immutable(String inputString){
        int l = inputString.length();

        if(l == 6) this.setColorsByHex(inputString);
        else if(l == 24) this.setColorsByBin(inputString);
        else this.setAsBlack();
    }

    // コンストラクタ内からのみ呼び出すことができ、一度設定された色は変更できない
    private void setColorsByHex(String hex){
        if (hex.length() != 6) this.setAsBlack();
        else {
            this.red = Integer.parseInt(hex.substring(0, 2), 16);
            this.green = Integer.parseInt(hex.substring(2, 4), 16);
            this.blue = Integer.parseInt(hex.substring(4), 16);
        }
    }

    private void setColorsByBin(String bin){
        if (bin.length() != 24) this.setAsBlack();
        else {
            this.red = Integer.parseInt(bin.substring(0, 8), 2);
            this.green = Integer.parseInt(bin.substring(8, 16), 2);
            this.blue = Integer.parseInt(bin.substring(16), 2);
        }
    }

    private void setAsBlack(){
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    // getterのみを定義し、setterを定義しないことで一度作成された状態を変更できない
    public String getHex(){
        String output = Integer.toHexString(this.red);
        output += Integer.toHexString(this.green);
        output += Integer.toHexString(this.blue);

        return output;
    }

    public String getBits(){
        String output = Integer.toBinaryString(Integer.parseInt(this.getHex(), 16));
        return output;

        // String output = Integer.toBinaryString(this.red);
        // output += Integer.toBinaryString(this.green);
        // output += Integer.toBinaryString(this.blue);

        // return output;
    }

    public String getColorShade(){
        if (this.red == this.green && this.green == this.blue) return "greyscale";
        String[] colorTable = {"red", "green", "blue"};
        int[] colorValues = {this.red, this.green, this.blue};
        int maxInt = 0;
        int maxVal = this.red;

        for (int i = 1; i < colorValues.length; i++){
            if (colorValues[i] >= maxVal){
                maxVal = colorValues[i];
                maxInt = i;
            }
        }

        return colorTable[maxInt];
    }
}

class Main{
    public static void main(String[] args){
        RGB24Immutable color = new RGB24Immutable(100, 150, 200);
        System.out.println(color.getColorShade());

        RGB24Immutable grey = new RGB24Immutable(100, 100, 100);
        System.out.println(grey.getColorShade());

        RGB24Immutable color2 = new RGB24Immutable("100110011111111100110011");
        System.out.println(color2.getColorShade());
        System.out.println(color2.getBits());

        RGB24Immutable color3 = new RGB24Immutable("ff11cc");
        System.out.println(color3.getColorShade());
        System.out.println(color3.getBits());
        System.out.println(color3.getHex());
    }
}
