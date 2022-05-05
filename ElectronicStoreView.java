import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class ElectronicStoreView extends FlowPane {
    private ElectronicStore model;
    private ListView <String> stockView, topThreeView, cartView;
    private TextField saleNumbField, revenueField, revPerSaleField;
    private Button resetButton, addButton, removeButton, sellButton;
    private Label cartValue;


    // Return listviews
    public ListView getStockView () { return stockView; }
    public ListView getCartView(){return cartView;}

    // Return buttons
    public Button getResetButton() {return resetButton;}
    public Button getAddButton () {return addButton;}
    public Button getRemoveButton(){return removeButton;}
    public Button getSellButton(){return sellButton;}

    public ElectronicStoreView(ElectronicStore m)
    {
        model = m;

        // A FlowPane containing a few nodes
        FlowPane aPane = new FlowPane();
        aPane.setHgap(5);
        aPane.setVgap(5);
        aPane.setPrefWrapLength(175);

        // Labels
        Label summaryLabel = new Label("Store Summary:");
        summaryLabel.setPrefSize(145, 20);
        summaryLabel.setAlignment(Pos.CENTER);

        Label stockLabel = new Label("Store Stock:");
        stockLabel.setPrefSize(280, 20);
        stockLabel.setAlignment(Pos.CENTER);

        HBox cartBox = new HBox();
        Label cartLabel = new Label("Current Cart ");
        cartLabel.setPrefSize(140, 20);
        cartLabel.setAlignment(Pos.CENTER_RIGHT);

        cartValue = new Label();
        cartValue.setAlignment(Pos.BOTTOM_CENTER);
        cartBox.getChildren().addAll(cartLabel, cartValue);


        Label saleNumbLabel = new Label("# Sales:");
        saleNumbLabel.setPrefSize(55, 20);
        saleNumbLabel.setAlignment(Pos.CENTER_RIGHT);

        Label revenueLabel = new Label("Revenue:");
        revenueLabel.setPrefSize(55, 20);
        revenueLabel.setAlignment(Pos.CENTER_RIGHT);

        Label revPerSaleLabel = new Label ("$ / Sale:");
        revPerSaleLabel.setPrefSize(55, 20);
        revPerSaleLabel.setAlignment(Pos.CENTER_RIGHT);

        Label covetedItemsLabel = new Label("Most Popular Items:");
        covetedItemsLabel.setPrefWidth(145);
        covetedItemsLabel.setAlignment(Pos.CENTER);

        // TextFields
        saleNumbField = new TextField();
        saleNumbField.setEditable(false);
        saleNumbField.setPrefSize(100, 20);
        revenueField = new TextField();
        revenueField.setEditable(false);
        revenueField.setPrefSize(100, 20);
        revPerSaleField = new TextField();
        revPerSaleField.setPrefSize(100, 20);

        setStyle("-fx-background-color: beige");

        aPane.getChildren().addAll(saleNumbLabel, saleNumbField, revenueLabel,
                revenueField, revPerSaleLabel, revPerSaleField);
        // ListViews
        topThreeView = new ListView<String>();
        topThreeView.setPrefSize(125, 182);

        stockView = new ListView<String>();
        stockView.setPrefSize(280, 300);

        cartView = new ListView<String>();
        cartView.setPrefSize(280,300);


        // Buttons
        resetButton = new Button("Reset Store");
        resetButton.setPrefSize(130, 50);
        setMargin(resetButton, new Insets(0, 0, 0, 20));

        addButton = new Button("Add to Cart");
        addButton.setDisable(true);
        addButton.setPrefSize(130, 50);
        setMargin(addButton, new Insets(0, 0, 0, 70));

        removeButton = new Button("Remove from Cart");
        removeButton.setDisable(true);
        removeButton.setPrefSize(140, 50);

        sellButton = new Button("Complete Sale");
        sellButton.setDisable(true);
        sellButton.setPrefSize(140, 50);

        HBox h1 = new HBox();
        h1.getChildren().addAll(removeButton, sellButton);


        setOrientation(Orientation.VERTICAL);
        setVgap(5);
        setHgap(10);
        setPadding(new Insets(10));

        // Add all components to FlowPane
        getChildren().addAll(summaryLabel,aPane,covetedItemsLabel, topThreeView,resetButton,
                stockLabel, stockView,addButton, cartBox, cartView, h1);

        // Update the view upon startup
        update(model);
    }

    /**
     * Refreshes the view based on information from the model.
     */
    public void update(ElectronicStore model)
    {
        // Set text
        saleNumbField.setText(Integer.toString(model.getNumSales()));
        revenueField.setText(String.format("$%,.2f", model.getRevenue()));
        cartValue.setText(String.format("($%.2f)", model.getCartValue()));
        String l = Float.toString(model.getAvgPerSale());
        if (l.equals("0.0"))
            revPerSaleField.setText("N/A");
        else
            revPerSaleField.setText(String.format("$%,.2f", model.getAvgPerSale()));


        // Set ListView items
        stockView.setItems(FXCollections.observableArrayList(model.getItemsInStock()));
        topThreeView.setItems(FXCollections.observableArrayList(model.getMostPopItems()));
        cartView.setItems(FXCollections.observableArrayList(model.getItemsInCart()));


        // Enable CompleteSale button if there is at least an item in cart
        if (!model.getItemsInCart().isEmpty()) {
            getSellButton().setDisable(false);
        }
        else
            getSellButton().setDisable(true);

        // Enable/Disable add and remove buttons
       addButton.setDisable(stockView.getSelectionModel().getSelectedIndex() < 0);
       removeButton.setDisable(cartView.getSelectionModel().getSelectedIndex() < 0);
    }
}
