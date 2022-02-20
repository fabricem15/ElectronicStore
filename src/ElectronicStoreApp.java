import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;

    public void start(Stage primaryStage) throws Exception {

        // Initialize view and model
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);


        // Handle mouse events
        view.getStockView().setOnMousePressed(mouseEvent -> handleStockListSelection());
        view.getCartView().setOnMousePressed(mouseEvent -> handleCartListSelection());

        // Add items to Cart
        view.getAddButton().setOnAction(e-> handleAddButton());

        // Remove items from cart
        view.getRemoveButton().setOnAction(e-> handleRemoveButton());

        // Complete Sale
        view.getSellButton().setOnAction(e-> handleCompleteSale());

        // Reset the store
        view.getResetButton().setOnAction(e-> handleResetStore());


        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene (view, 800,400));
        primaryStage.setTitle("Electronic Store Application - " + model.getName());
        primaryStage.show();
    }

    // Add private methods only meant to be called by this class

    private void handleAddButton(){
        int item = view.getStockView().getSelectionModel().getSelectedIndex();
        model.addToCart(item);
        view.update(model);
    }
    private void handleRemoveButton(){
        int item = view.getCartView().getSelectionModel().getSelectedIndex();
        model.removeFromCart(item);
        view.update(model);
    }

    private void handleCompleteSale(){
        model.completeSale();
        view.update(model);
    }


    private void handleStockListSelection(){
        // Remove focus from cartView
        view.getCartView().getSelectionModel().clearSelection();
        view.update(model);
    }

    private void handleCartListSelection(){
        // Remove focus from stockView
        view.getStockView().getSelectionModel().clearSelection();
        view.update(model);
    }

    private void handleResetStore(){
        // Initialize state of the model
        model = ElectronicStore.createStore();
        view.getStockView().getSelectionModel().clearSelection();
        view.getCartView().getSelectionModel().clearSelection();
        view.update(model);
    }
}
