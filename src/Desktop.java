public class Desktop extends Computer {
    private String profile;

    /**
     * Creates a desktop object and initializes its attributes
     * @param price The product's price
     * @param quantity The stock quantity
     * @param cpuSpeed
     * @param Ram The amount of RAM in GB
     * @param SSD Whether the hard drive is an SSD (true) or an HDD (false)
     * @param Storage The size of the hard drive in GB
     * @param profile The size of the desktop case
     */
    public Desktop(double price, int quantity, double cpuSpeed, int Ram, boolean SSD,
                   int Storage, String profile)
    {
       super(price, quantity, cpuSpeed, Ram, SSD, Storage);
       this.profile = profile;

    }

    public String toString()
    {
        String stringRep = profile + " Desktop PC";
        stringRep += super.toString();
        return stringRep;
    }



}
