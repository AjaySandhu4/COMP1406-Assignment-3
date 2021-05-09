public class Fridge extends Appliance{ //Fridges are appliances...
    private double cubicFeet;
    private boolean hasFreezer;

    public Fridge(double price, int quantity, int wattage, String color, String brand, double cubicFeet, boolean freezer){
        super(price, quantity, wattage, color, brand);
        this.cubicFeet = cubicFeet;
        this.hasFreezer = freezer;
    }

    public String toString(){
        String freezeString;
        if(hasFreezer){
            freezeString = "with Freezer ";
        }
        else{
            freezeString = "";
        }
        return cubicFeet+" cu. ft. "+this.getBrand()+" Fridge "+freezeString+super.toString();
    }

}
