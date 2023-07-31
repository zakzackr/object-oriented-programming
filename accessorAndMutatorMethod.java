class Battery7v{
    private String manufacturer;
    private String model;
    private static final double VOLTAGE = 7.2;  // デフォルト値の代入
    private static final String TYPE = "Lithium-Ion";
    private static int manufacturedCount;
    private double ampHours;
    private double weightKg;
    private double[] dimensionMm;

    public Battery7v(String manufacturer, String model, double ampHours, double weightKg, double wMm, double hMm, double dMm){
        this.manufacturer = manufacturer;
        this.model = model;
        this.ampHours = ampHours;
        this.weightKg = weightKg;
        this.dimensionMm = new double[]{wMm, hMm, dMm};
        this.manufacturedCount++;  // 電池が作成されるたび（コンストラクタが実行されるたび）+1
    }

    // getter/setterを作成
    public String getManufacturer(){
        return this.manufacturer;
    }

    public String getModel(){
        return this.model;
    }

    public double getAmpHours(){
        return this.ampHours;
    }

    public void setAmpHours(double ampHours){
        this.ampHours = ampHours;
    }

    public double getWeightKg(){
        return this.weightKg;
    }

    public void setWeightKg(double weightKg){
        this.weightKg = weightKg;
    }

    public double[] getDimensionMm(){
        return this.dimensionMm;
    }

    public void setDimensionMn(double wMm, double hMm, double dMm){
        this.dimensionMm = new double[]{wMm, hMm, dMm};
    }

    public int getManufacturerCount(){
        return this.manufacturedCount;
    }

    public String toString(){
        return this.manufacturer + " " + this.model + " " + Battery7v.TYPE + " Battery: " + this.getPowerCapacity() + "Wh (" + Battery7v.VOLTAGE + "V/" + this.ampHours + "Ah) - " + this.dimensionMm[0] + "(W)x" + this.dimensionMm[1] + "(H)x" + this.dimensionMm[2] + "(D) " + this.getVolume() + " volume " + this.weightKg + "kg";
    }

    public double getVolume(){
        return this.dimensionMm[0] * this.dimensionMm[1] * dimensionMm[2];
    }

    public double getPowerCapacity(){
        return Battery7v.VOLTAGE * this.ampHours;
    }
}
// manufacturedCountはprivateで定義されているため、クラス外からアクセスできない
// class ExternalModule{
//     public static void dangerousMethod(){
//         Battery7v.manufacturedCount += 1000;
//     }
// }

class Main{
    public static void main(String[] args){
        Battery7v zlD72 = new Battery7v("MT-Dell Tech", "ZL-D72", 9.9, 1.18, 38, 80, 70);
        Battery7v zlD50 = new Battery7v("MT-Dell Tech", "ZL-D50", 6.6, 0.9, 28, 50, 65);
        Battery7v zlD40 = new Battery7v("MT-Dell Tech", "ZL-D40", 5.3, 1.18, 38, 80, 70);

        System.out.println(zlD40.getManufacturerCount());
        System.out.println(zlD72.getManufacturerCount());

        System.out.println(zlD40.getDimensionMm()[0]);
        zlD40.setDimensionMn(100, 100, 100);
        System.out.println(zlD40.toString());

        // ExternalModule.dangerousMethod();
        // zlD72.manufacturer = "company-A";
        System.out.println(zlD72.getManufacturer());
    }
}
