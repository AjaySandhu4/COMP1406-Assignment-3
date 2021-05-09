public abstract class Computer extends Product{ //Computers are products...
    private double cpuSpeed;
    private int ram;
    private boolean ssd;
    private int storage;

    public Computer(double price, int stockQuantity, double cpuSpeed, int ram, boolean ssd, int storage){
        super(price, stockQuantity);
        this.cpuSpeed = cpuSpeed;
        this.ram = ram;
        this.ssd = ssd;
        this.storage =  storage;
    }

    public String toString(){
        String drive;
        if(ssd){
            drive = "SSD";
        }
        else{
            drive = "HDD";
        }
        return "with "+cpuSpeed+"ghz CPU, "+ram+"GB RAM, "+storage+"GB "+drive+" drive. "+super.toString();
    }
}
