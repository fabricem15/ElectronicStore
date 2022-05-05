import javafx.application.Application;

public abstract class Appliance extends Product{

    protected int wattage; // Variable is protected so that it may be accessible from subclasses

    /*
        This class is the parent class of every appliance in the store (i.e., Fridge and Toaster Oven)
        and a subclass of the Product class.
        It contains the shared attributes of each appliance, namely the wattage, brand, and colour attribute
        and a similar output format.
     */


    /**
     * Creates a new Appliance object
     * @param price The product's price
     * @param quantity The quantity available in stock
     * @param Wattage The appliance's wattage
     * @param colour The product's colour
     * @param brand The product's brand
     */
    public Appliance(double price, int quantity, int Wattage, String colour, String brand)
    {
        super(price, quantity, colour, brand);
        wattage = Wattage;
    }


    public String toString()
    {
        return " (" + colour + ", " + wattage + " watts)";
    }
}
