public class Laptop extends Computer { //Laptops are computers...
    private double screenSize;

    public Laptop(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage, double screenSize){
        super(price, quantity, cpuSpeed, ram, ssd, storage);
        this.screenSize = screenSize;
    }

    public String toString(){
        return screenSize+" inch Laptop PC "+super.toString();
    }
}
