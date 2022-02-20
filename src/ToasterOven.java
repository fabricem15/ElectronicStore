public class ToasterOven extends Appliance {
   private int width;
   private boolean convection;

    /**
     * Creates a ToasterOven object and initializes variables
     * @param price The price of the Toaster oven
     * @param quantity How many units are available in stock
     * @param wattage The wattage rating of the toaster oven
     * @param Colour The colour of the toaster
     * @param brand The brand name of the toaster
     * @param width The width of the toaster oven
     * @param convection whether the toaster has convection heat or not
     */
    public ToasterOven(double price, int quantity, int wattage, String Colour,
                       String brand, int width, boolean convection)
    {
        super(price, quantity, wattage, Colour, brand);
        this.width = width;
        this.convection = convection;
    }

    public String toString()
    {
        String stringRep =  width + " inch " + brand;

        if (convection)
            stringRep += " with convection ";

        stringRep += super.toString();

        return stringRep;
    }
}
