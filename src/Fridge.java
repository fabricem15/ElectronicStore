public class Fridge extends Appliance{
    private double cubicFeet;
    private boolean hasFreezer;

    /**
     * Creates a Fridge object and initializes variables.
     * @param price Product's price
     * @param quantity Stock quantity
     * @param wattage The wattage rating of the fridge
     * @param color The colour of the fridge
     * @param brand The brand name of the fridge
     * @param cubicFeet The volume in cubic feet
     * @param freezer Whether it has a freezer or not
     */
    public Fridge(double price, int quantity, int wattage, String color,
                  String brand, double cubicFeet, boolean freezer)
    {
        super(price, quantity, wattage, color, brand);
        this.cubicFeet = cubicFeet;
        hasFreezer = freezer;
    }



    public String toString(){
        String stringRep = cubicFeet + " cu. ft. " + brand + " Fridge ";

        if (hasFreezer)
            stringRep += " with freezer ";

        stringRep += super.toString();

        return stringRep;
    }


}
