package cardgamesdesktop.fxml;

import cardgamesdesktop.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Andrew Haeger
 */
public class EuchreGUIController implements Initializable, Screens {

    // Seat Configuration
    //      2
    //  1       3
    //      0
    
    // <editor-fold defaultstate="collapsed" desc="GUI Components">
    ScreensController controller;
    String previous;
    
    @FXML
    private Label loggedInHeader;
    
    @FXML
    private AnchorPane player0Image;
    @FXML
    private AnchorPane player0Card1;
    @FXML
    private AnchorPane player0Card2;
    @FXML
    private AnchorPane player0Card3;
    @FXML
    private AnchorPane player0Card4;
    @FXML
    private AnchorPane player0Card5;
    @FXML
    private Label player0Name;
    @FXML
    private AnchorPane player0CardUp;
    @FXML
    private AnchorPane player0CardPlayed;
    
    @FXML
    private AnchorPane player1Image;
    @FXML
    private AnchorPane player1Card1;
    @FXML
    private AnchorPane player1Card2;
    @FXML
    private AnchorPane player1Card3;
    @FXML
    private AnchorPane player1Card4;
    @FXML
    private AnchorPane player1Card5;
    @FXML
    private Label player1Name;
    @FXML
    private AnchorPane player1CardUp;
    @FXML
    private AnchorPane player1CardPlayed;
    
    @FXML
    private AnchorPane player2Image;
    @FXML
    private AnchorPane player2Card1;
    @FXML
    private AnchorPane player2Card2;
    @FXML
    private AnchorPane player2Card3;
    @FXML
    private AnchorPane player2Card4;
    @FXML
    private AnchorPane player2Card5;
    @FXML
    private Label player2Name;
    @FXML
    private AnchorPane player2CardUp;
    @FXML
    private AnchorPane player2CardPlayed;
    
    @FXML
    private AnchorPane player3Image;
    @FXML
    private AnchorPane player3Card1;
    @FXML
    private AnchorPane player3Card2;
    @FXML
    private AnchorPane player3Card3;
    @FXML
    private AnchorPane player3Card4;
    @FXML
    private AnchorPane player3Card5;
    @FXML
    private Label player3Name;
    @FXML
    private AnchorPane player3CardUp;
    @FXML
    private AnchorPane player3CardPlayed;
    
    @FXML
    private AnchorPane currentTrump;
    @FXML
    private Label teamOneName;
    @FXML
    private Label teamOneTricks;
    @FXML
    private Label teamOnePoints;
    @FXML
    private Label teamTwoName;
    @FXML
    private Label teamTwoTricks;
    @FXML
    private Label teamTwoPoints;
    
    @FXML
    private TextArea gameInfo;
    @FXML
    private CheckBox goAlone;
    @FXML
    private RadioButton spades;
    @FXML
    private RadioButton hearts;
    @FXML
    private RadioButton diamonds;
    @FXML
    private RadioButton clubs;
    @FXML
    private Label handInformation;
    @FXML
    private TextArea chatBox;
    @FXML
    private TextField chatMessage;
    // </editor-fold>
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loggedInHeader.setVisible(false);
    }    
    
    @Override
    public void setScreenController(ScreensController controller) {
        this.controller = controller;
    }
    
    @Override
    public void setPreviousScreen(String previous) {
        this.previous = previous;
    }
    
    @FXML
    private void goToTablesScreen() {
        controller.setScreen(DesktopCardGameGUI.tablesScreen);
    }
    
    @FXML
    private void goToLoginScreen() {
        controller.setScreen(DesktopCardGameGUI.loginScreen);
    }
    
    @FXML
    private void showUserStatisticsScreen() {
        controller.setScreen(DesktopCardGameGUI.statisticsScreen);
    }
    
    @FXML
    private void pass() {
        
    }
    
    @FXML
    private void call() {
        
    }
    
    @FXML
    private void sendMessage() {
        
    }
}