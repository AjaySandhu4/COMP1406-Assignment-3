public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartQuantity;

    public Product(double price, int stockQuantity){
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.soldQuantity = 0;
        this.cartQuantity = 0;
    }

    public int getStockQuantity(){ return this.stockQuantity; }
    public int getSoldQuantity(){ return this.soldQuantity; }
    public int getCartQuantity(){ return this.cartQuantity; }
    public double getPrice(){ return this.price; }
    public void setStockQuantity(int amount){ this.stockQuantity = amount; }

    public void changeCartBy(int amount){
        cartQuantity += amount;
    }

    public double sellUnits(int amount){
            //Updating inventory
            soldQuantity += amount;

            return amount*price;
    }

    public String toString(){
        return "("+price+" dollars each)";
    }
}
