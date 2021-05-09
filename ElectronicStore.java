public class ElectronicStore {
    private final int MAX_PRODUCTS = 10;
    private String name;
    private double revenue;
    private Product[] products;
    private int productcount;  //adding variable to keep track of number of products created for this store
    private int numSales;

    public ElectronicStore(String name){
        this.name = name;
        this.revenue = 0;
        this.products = new Product[MAX_PRODUCTS];
        this.productcount = 0;
        this.numSales = 0;
    }
    public String getName(){ return name; }
    public double getRevenue(){ return revenue; }
    public Product[] getProducts(){
        Product[] noNullProducts = new Product[productcount];
        System.arraycopy(this.products, 0, noNullProducts, 0, this.productcount);
        return noNullProducts;
    }
    public int getNumSales() { return numSales; }

    //Method takes array of all products, sorts it based on sold quantity, then returns array of most popular items
    public Product[] getMostPopular(){
        Product[] mostPopular;
        Product[] productsArray = this.getProducts();
        Product tempProduct;

        for(int a=0; a<this.productcount-1; a++){
            for(int b=0; b<this.productcount-1; b++){
                if(productsArray[b+1].getSoldQuantity() > productsArray[b].getSoldQuantity()){
                    tempProduct = productsArray[b];
                    productsArray[b] = productsArray[b+1];
                    productsArray[b+1] = tempProduct;
                }
            }
        }
        if(this.productcount >= 3){
            mostPopular = new Product[3];
            for(int i=0; i<3; i++){
                mostPopular[i] = productsArray[i];
            }
        }
        else{
            mostPopular = new Product[productcount];
            for(int i=0; i<productcount; i++){
                mostPopular[i] = productsArray [i];
            }
        }
        return mostPopular;
    }

    //method returns array of products in the cart
    public Product[] getCartProducts(){
        Product[] cartProducts;
        int numCartProducts = 0;
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].getCartQuantity() > 0){
                numCartProducts++;
            }
        }
        cartProducts = new Product[numCartProducts];
        int cartCounter = 0;
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].getCartQuantity() > 0){
                cartProducts[cartCounter++] = this.getProducts()[i];
            }
        }
       return cartProducts;
    }

    public float getCartValue(){
        float cartValue = 0;
        for(int i=0; i<this.getCartProducts().length; i++){
            cartValue += (float)(this.getCartProducts()[i].getPrice() * this.getCartProducts()[i].getCartQuantity());
        }
        return cartValue;
    }

    //method returns strings that will be visible on the cart list view
    public String[] getCartStrings(){
        String[] cartStrings = new String[this.getCartProducts().length];
        for(int i=0; i<this.getCartProducts().length; i++){
            cartStrings[i] = this.getCartProducts()[i].getCartQuantity() + " x " + this.getCartProducts()[i];
        }
        return cartStrings;
    }

    //method returns array of products which are still available in stock
    public Product[] getStoreStock(){
        Product[] storeStock;
        int stockNum = 0;
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].getStockQuantity() > 0){
                stockNum++;
            }
        }
        storeStock = new Product[stockNum];
        int stockCounter = 0;
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].getStockQuantity() > 0){
                storeStock[stockCounter++] = this.getProducts()[i];
            }
        }
        return storeStock;
    }

    public boolean addProduct(Product p){
        if(productcount<MAX_PRODUCTS){
            products[productcount++] = p;
            return true;
        }
        else{
            return false;
        }
    }
    public void sellProducts(int item, int amount){
            revenue += products[item].sellUnits(amount);
    }
    public void addtoCart(Product p){
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].equals(p)){
                this.getProducts()[i].changeCartBy(1);
                this.getProducts()[i].setStockQuantity(this.getProducts()[i].getStockQuantity() - 1);
                return;
            }
        }
    }
    public void removeFromCart(Product p){
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].equals(p)){
                this.getProducts()[i].changeCartBy(-1);
                this.getProducts()[i].setStockQuantity(this.getProducts()[i].getStockQuantity() + 1);
                return;
            }
        }
    }
    public void completeSale(){
        for(int i=0; i<this.productcount; i++){
            if(this.getProducts()[i].getCartQuantity() > 0){
                this.sellProducts(i, this.getProducts()[i].getCartQuantity());
                this.getProducts()[i].changeCartBy(-this.getProducts()[i].getCartQuantity()); //Clear cart
            }
        }
        this.numSales++;
    }


    public static ElectronicStore createStore(){
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
}
