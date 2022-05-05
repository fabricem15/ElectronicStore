import java.util.ArrayList;
import java.util.List;
public class ElectronicStore {

    private String name;
    private final int MAX_PRODUCTS = 10;
    private double revenue, cartValue;
    private Product[] product, storeStock;
    private int numSales, tempIndex, productCount = 0;
    public float avgPerSale;
    private List<Product> inCart, soldOut;
    private int[] indexInCart = new int[10];

    public ElectronicStore(String Name)
    {
        name = Name;
        revenue = 0;
        product = new Product[MAX_PRODUCTS];
        storeStock = new Product[MAX_PRODUCTS]; // this array stores all the products that were added to the cart. No product gets removed from this array.
        avgPerSale = 0.0f;
        numSales = 0;
        cartValue = 0;
        inCart = new ArrayList<>();

        // The ArrayList below stores products that no longer appear in the stock list. It is here to ensure that products that were removed from the stock
        // are sold when the user completes the sale.
        soldOut = new ArrayList<>();
        tempIndex = MAX_PRODUCTS;
    }

    /**
     * Add Product p to the list of item in the store if possible.
     * @param p A Product object
     * @return successful  a boolean that returns true if the store is not at full capacity, otherwise returns false.
     */
    public boolean addProduct(Product p)
    {
        if (productCount < MAX_PRODUCTS && p!= null)
        {
            product[productCount] = p;
            storeStock[productCount] = p;
            productCount++;
            return true;
        }
        return false;
    }


    /**
     * Sell product(s).
     * @param item  an int representing the item to sell.
     * @param amount  an int representing the amount of products the user wants to sell.
     * @return successful - a boolean that returns true if the item could be sold successfully, otherwise returns false.
     */

    public boolean sellProducts(int item, int amount)
    {
        // If a product is null or the amount is invalid
        if (item > 0 && item < MAX_PRODUCTS  && product[item] == null || amount < 0)
        {
            return false;
        }

        // If the product at index 'item' was initialized but the amount exceeds the stock quantity for it
        else if ( item > 0 && item < MAX_PRODUCTS &&  amount > product[item].getStockQuantity())
        {
           return false;
        }

        // If an item was removed from the list of products
        else if (item >= MAX_PRODUCTS)
        {
            revenue += soldOut.get(item - MAX_PRODUCTS).sellUnits(amount);
            return true;
        }

        // If everything else is correct, perform the sale
        else
        {
           revenue += product[item].sellUnits(amount);
           return true;
        }

    }


    /**
     *
     * @return The three most popular items, sorted in decreasing order by the number of products sold.
     */
    public String[] getMostPopItems()
    {
        Product temp;
        Product[] newList = storeStock.clone(); // make a copy of the stock array

        if (newList.length >  0)
        {
            for (int i = 0; i < storeStock.length; i++)
            {
                for (int k = i + 1; k < storeStock.length; k++)
                {
                    if (newList[k]!= null) {
                        if (newList[k].getSoldQuantity() >= newList[i].getSoldQuantity()) {
                            temp = newList[i];
                            newList[i] = newList[k];
                            newList[k] = temp;
                        }
                    } } }
        }

        return new String[]{newList[0].toString(),newList[1].toString(),newList[2].toString()};
    }

    /**
     * Add the product to the cart.
     * @param p The product's index in stock.
     */
    public void addToCart(int p)
    {
        if (p >= 0 && p < MAX_PRODUCTS)
        {
            // Add quantity in cart
            product[p].increaseNumInCart();

            // Increase cart value
            cartValue += product[p].getPrice();

            // Record where in the stock the product appears
            product[p].setIndexInStock(p);


            // If a product was not in cart, add it to the cart list
            if (product[p].getNumInCart() == 1) {
                inCart.add(product[p]);
                indexInCart[p] = inCart.size() - 1; // Each item in cart must map to an index in stock
            }

            // If a product had previously been added to the cart, increase its quantity in cart
            else if (product[p].getNumInCart() > 1) {
                inCart.set(indexInCart[p], product[p]); // Reflect changes in the product's cart quantity
            }

            // Remove a product from the stock view if the number in the virtual stock goes to 0
            if (product[p].getVirtualStock() == 0)
            {
                // Store the product in a temporary location since it has been removed from the stock array
                inCart.get(indexInCart[p]).setIndexInStock(tempIndex);
                inCart.get(indexInCart[p]).setInVirtualStock(false);

                soldOut.add(product[p]); // Add the product to a temporary location
                tempIndex++; // Increase the index

                // Remove the product from the array
                for (int i = p; i < productCount-1; i++) {
                    product[i] = product[i+1];

                }
                // Decrease the size
                productCount--;
            }
        }
    }

    /**
     * Proceed to sell every product that is in cart.
      */
    public void completeSale()
    {
        // Sell every product in the cart
        for (int i = 0; i  < inCart.size(); i++)
        {
            sellProducts(inCart.get(i).getIndexInStock(), inCart.get(i).getNumInCart());
            // Make sure that the product is no longer reflected as being 'in cart'
            inCart.get(i).removeFromCart();
        }

        // Reflect changes in the store
        numSales++;
        avgPerSale = (float) revenue / numSales;
        cartValue = 0;

        // Clear the cart
        inCart.clear();
    }
    /**
     * Removes the Product corresponding to the index provided from the cart.
     * @param p - The index of the product in the cart.
     */
    public void removeFromCart(int p)
    {
        cartValue-= inCart.get(p).getPrice();
        inCart.get(p).decreaseNumInCart();

            // If all the item of that product were removed from the stock list, add it back to the stock list
           if (!inCart.get(p).isInVirtualStock()) {

               inCart.get(p).setInVirtualStock(true);
               product[productCount] = inCart.get(p);
               productCount++;
           }

           // If there are no more items in cart remove it from the cart list
           if (inCart.get(p).getNumInCart() == 0)
            inCart.remove(p);
    }

    /**
     *
     * @return an ArrayList of strings representing each item in stock.
     */
    public ArrayList<String> getItemsInCart(){
        ArrayList<String> s = new ArrayList<>();

        for (int i = 0; i < inCart.size(); i++){
            s.add(inCart.get(i).getCartString());
        }
        return s;
    }


    /**
     *
     * @return An ArrayList of string representations of every item in stock
     */
    public ArrayList<String> getItemsInStock()
    {
        ArrayList <String> actualProducts = new ArrayList<>();

        for (int i = 0 ; i < productCount; i++) {
            // Only add products that still appear in stock
            if (product[i].getVirtualStock() > 0)
                actualProducts.add(product[i].toString());
        }

        return actualProducts;
    }

    // Get methods
    public String getName(){return name;}
    public double getRevenue() { return revenue; }
    public double getCartValue() {return cartValue;}
    public int getNumSales(){return numSales;}
    public float getAvgPerSale(){return avgPerSale;}


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
