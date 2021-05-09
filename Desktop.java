public class Desktop extends Computer {
    private String profile;

    public Desktop(double price, int quantity, double cpuSpeed, int ram, boolean ssd, int storage, String profile){
        super(price, quantity, cpuSpeed, ram, ssd, storage);
        this.profile = profile;
    }

    public String toString(){
        return profile+" Desktop PC "+super.toString();
    }
}
