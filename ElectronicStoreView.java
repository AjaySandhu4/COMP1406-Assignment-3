import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

public class ElectronicStoreView extends Pane {
    private TextField salesText, revenueText, avgText;
    private ListView<Product> popularList, stockList;
    private ListView<String> cartList;
    private Button resetButton, addButton, removeButton, completeButton;
    private Label cartLabel;


    public ElectronicStoreView(){

        //Setting up labels
        Label summaryLabel = new Label("Store Summary:");
         summaryLabel.relocate(40, 20);
        Label stockLabel = new Label("Store Stock:");
         stockLabel.relocate(305, 20);
        cartLabel = new Label("Current Cart:");
         cartLabel.relocate(600,20);
        Label salesLabel = new Label("# of Sales:");
         salesLabel.relocate(20,45);
        Label revenueLabel = new Label("  Revenue:");
         revenueLabel.relocate(20,75);
        Label avgLabel = new Label("       $/Sale:");
         avgLabel.relocate(20, 105);
        Label popularLabel = new Label("Most Popular Items:");
         popularLabel.relocate(40,140);

        //Setting up text fields
        salesText = new TextField();
         salesText.relocate(100,40);
         salesText.setPrefSize(90,20);
         salesText.setEditable(false);
        revenueText = new TextField();
         revenueText.relocate(100, 70);
         revenueText.setPrefSize(90,20);
         revenueText.setEditable(false);
        avgText = new TextField();
         avgText.relocate(100,100);
         avgText.setPrefSize(90,20);
         avgText.setEditable(false);

        //Setting up list views
        popularList = new ListView<Product>();
         popularList.relocate(10,160);
         popularList.setPrefSize(180,170);
        stockList = new ListView<Product>();
         stockList.relocate(200,40);
         stockList.setPrefSize(285,290);
        cartList = new ListView<String>();
         cartList.relocate(495, 40);
         cartList.setPrefSize(285,290);

        //Setting up buttons
        resetButton = new Button("Reset Store");
         resetButton.relocate(30,340);
         resetButton.setPrefSize(140, 45);
        addButton = new Button("Add to Cart");
         addButton.relocate(272.5,340);
         addButton.setPrefSize(140,45);
        removeButton = new Button("Remove from Cart");
         removeButton.relocate(495,340);
         removeButton.setPrefSize(140,45);
        completeButton = new Button("Complete Sale");
         completeButton.relocate(640, 340);
         completeButton.setPrefSize(140,45);


         getChildren().addAll(summaryLabel, stockLabel, cartLabel, salesLabel, revenueLabel, avgLabel, popularLabel, salesText, revenueText, avgText, popularList, stockList, cartList, resetButton, addButton, removeButton, completeButton);
         this.setPrefSize(800,400);
    }
    public ListView<Product> getStockList(){ return stockList; }
    public ListView<String> getCartList(){ return cartList; }
    public Button getAddButton(){ return addButton; }
    public Button getRemoveButton(){ return removeButton; }
    public Button getResetButton(){ return resetButton; }
    public Button getCompleteButton(){ return completeButton; }


    public void update(ElectronicStore storeModel){

        //updates list views
        popularList.setItems(FXCollections.observableArrayList(storeModel.getMostPopular()));
        stockList.setItems(FXCollections.observableArrayList(storeModel.getStoreStock()));
        cartList.setItems(FXCollections.observableArrayList(storeModel.getCartStrings()));

        //updates cart value on "Current Cart" label
        cartLabel.setText("Current Cart ($"+String.format("%.2f", storeModel.getCartValue())+"):");

        //updates information in text fields
        salesText.setText(String.format("%d", storeModel.getNumSales()));
        revenueText.setText(String.format("%.2f", (float)storeModel.getRevenue()));
        if(storeModel.getNumSales() == 0) {
            avgText.setText("N/A");
        }
        else{
            avgText.setText(String.format("%.2f", (float)(storeModel.getRevenue() / storeModel.getNumSales())));
        }

        //updating whether a button is disabled or not
        addButton.setDisable(stockList.getSelectionModel().getSelectedIndex() < 0);
        removeButton.setDisable(cartList.getSelectionModel().getSelectedIndex() < 0);
        completeButton.setDisable(storeModel.getCartProducts().length == 0);

    }


}
