public abstract class Computer extends Product{
    // Variables are protected so that they may be accessible by subclasses
    protected double cpu;
    protected int ram;
    protected int storage;
    protected boolean ssd;


    /*
        This class is the parent class of every computer in the store (i.e., Laptop and Desktop)
        and a subclass of the Product class.

        It contains the shared attributes of each computer, namely the CPU Speed, RAM storage, the size of the
        hard drive and whether it's an SSD or HDD storage.

        All computer objects also share a similar output format.
     */

/**
 * Creates a Computer object and initializes its attributes.
 * @param Price The product's price
 * @param quantity The stock quantity
 * @param cpuSpeed The CPU Speed in Ghz
 * @param Ram The amount of RAM in GB
 * @param SSD Whether the hard drive is an SSD (true) or an HDD (false)
 * @param Storage The size of the hard drive in GB
 **/
    public Computer(double Price, int quantity, double cpuSpeed, int Ram, boolean SSD, int Storage){
        super(Price, quantity);
        cpu = cpuSpeed;
        ram = Ram;
        ssd = SSD;
        storage = Storage;
    }



    public String toString() {
        String stringRep = "";
            if (ssd)
                stringRep += " with " + cpu + "ghz CPU, " + ram + "GB RAM, " + storage + "GB SSD drive.";
            else
                stringRep += " with " + cpu + "ghz CPU, " + ram + "GB RAM, " + storage + "GB HDD drive.";
        return stringRep;
    }

}
