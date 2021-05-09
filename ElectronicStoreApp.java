import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ElectronicStoreApp extends Application {
    private ElectronicStore storeModel;

    public ElectronicStoreApp(){
        storeModel = storeModel.createStore();
    }
    public void start(Stage primaryStage){
        Pane storePane = new Pane();

        //Initializing view
        ElectronicStoreView view = new ElectronicStoreView();
        storePane.getChildren().add(view);
        view.update(storeModel);

        //Initializing stage
        primaryStage.setTitle("Electronic Store Application - "+storeModel.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(storePane));
        primaryStage.show();

        //The "Add to Cart" button event handlers
        view.getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                Product p = view.getStockList().getSelectionModel().getSelectedItem();
                storeModel.addtoCart(p);
                view.update(storeModel);
            }
        });
        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                view.update(storeModel);
            }
        });

        //The "Remove from Cart" button event handlers
        view.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                int index = view.getCartList().getSelectionModel().getSelectedIndex();
                Product p = storeModel.getCartProducts()[index];
                storeModel.removeFromCart(p);
                view.update(storeModel);
            }
        });
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent mouseEvent){
                view.update(storeModel);
            }
        });

        //The "Reset Store" button event handler
        view.getResetButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                ElectronicStore resetStore = new ElectronicStore("");
                storeModel = resetStore.createStore();
                view.update(storeModel);
            }
        });

        //The "Complete Sale" button event handler
        view.getCompleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                storeModel.completeSale();
                view.update(storeModel);
            }
        });
    }

    public static void main(String[] args){
        launch(args);
    }

}
