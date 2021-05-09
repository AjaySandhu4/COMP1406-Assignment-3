public abstract class Appliance extends Product{ //Appliances are products...
    private int wattage;
    private String color;
    private String brand;

    public Appliance(double price, int stockQuantity, int wattage, String color, String brand){
        super(price, stockQuantity);
        this.wattage = wattage;
        this.color = color;
        this.brand = brand;
    }

    public String getBrand() { return brand; }

    public String toString(){
        return "("+color+", "+wattage+" watts) "+super.toString();
    }
}
