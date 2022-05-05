public abstract class Product {
    // Variables are protected so that they may be accessible inside subclasses
    protected double price;
    protected int stockQuantity, soldQuantity;
    protected String colour, brand;
    private int numInCart, indexInStock;
    private boolean isInVirtualStock;

    /*
     This class is the parent class of every item in the store.
     It contains the share attributes and behaviours of each item.
     */

    /**
     * Constructor for a product that does not have colour and brand attributes
     * @param Price The product's price
     * @param StockQuantity The quantity of the product available in stock.
     */
    public Product(double Price, int StockQuantity)
    {
        price = Price;
        stockQuantity = StockQuantity;
        soldQuantity = 0;
        colour = "noColour";
        brand = "noBrand";
        numInCart = 0;
        indexInStock = 0;
        isInVirtualStock = true;
    }

    /**
     * Class constructor for a Product that initiates brand and colour attributes.
     * @param Price Product price.
     * @param StockQuantity The quantity of the product available.
     * @param color The product's colour.
     * @param Brand The product's brand.
     */
    public Product (double Price,int StockQuantity, String color, String Brand)
    {
        price = Price;
        stockQuantity = StockQuantity;
        soldQuantity = 0;
        colour = color;
        brand = Brand;
        numInCart = 0;
        indexInStock = 0;
        isInVirtualStock = true;
    }


    /**
     * Sell the provided amount of the product object.
     * @param amount The number of items to be sold.
     * @return revenue - The price of each item times the amount sold.
     */
    public double sellUnits (int amount)
    {
        double revenue = 0;
        if (stockQuantity >= amount && amount > 0)
        {
            stockQuantity -= amount;
            soldQuantity += amount;
            revenue = amount * price;
        }
        else
            revenue = -1;

        return revenue;
    }

    // Return stock & sold quantity
    public int getStockQuantity()
    {
        return stockQuantity;
    }
    public int getSoldQuantity() {return soldQuantity;}

    /**
     *
     * @return whether or not an item is in the virtual stock (i.e., the number of items shown in
     * stock minus the number of items in cart).
     */
    public boolean isInVirtualStock(){return isInVirtualStock;}

    /**
     *
     * @param b Sets whether the product is in stock or not.
     */
    public void setInVirtualStock(boolean b){ isInVirtualStock = b;}


    /**
     *
     * @return the number of items to display in stock. This number is calculated by subtracting the # number of items that the user added to the cart
     * from the number of items in the stock for that product. This number is different from the stock quantity attribute
     * because the stock quantity is only updated after a product is sold.
     */
    public int getVirtualStock(){return stockQuantity - numInCart;}
    public double getPrice(){return price;}

    /**
     * Sets the number of items in cart to 0.
     */
    public void removeFromCart(){numInCart = 0;}
    public int getNumInCart(){return numInCart;}

    public void decreaseNumInCart(){numInCart -= 1;}
    public void increaseNumInCart() {numInCart += 1;}

    public int getIndexInStock(){return indexInStock;}
    public void setIndexInStock(int i) {indexInStock = i;}

    // Each subclass needs to implement its own version of the toString method
    public  abstract String toString();
    // Return the number of items in cart for the cart listView
    public  String getCartString(){ return numInCart + " x " + toString(); }


}
