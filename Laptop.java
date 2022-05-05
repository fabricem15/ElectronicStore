public class Laptop extends Computer{
    private double screenSize;

    /**
     * Creates a Laptop object and initializes its attributes.
     * @param Price The product's price
     * @param quantity The stock quantity
     * @param cpuSpeed The CPU Speed in Ghz
     * @param Ram The amount of RAM in GB
     * @param SSD Whether the hard drive is an SSD (true) or an HDD (false)
     * @param Storage The size of the hard drive in GB
     **/
    public Laptop(double Price, int quantity, double cpuSpeed, int Ram,
                  boolean SSD, int Storage, double ScreenSize)
    {
        super(Price, quantity, cpuSpeed, Ram, SSD, Storage);
        screenSize = ScreenSize;
    }

    public String toString()
    {
        String stringRep = screenSize + " inch Laptop";
        stringRep +=  super.toString();
        return stringRep;
    }

}
