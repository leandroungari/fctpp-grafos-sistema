/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import jfxtras.labs.scene.control.grid.cell.MediaImageCell;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLSobreController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Image image = new Image(getClass().getResourceAsStream("images/icon.png"), 250, 250, false, false);
       
        
        label.setText("");
        label.setGraphic(new ImageView(image));
        label.setLayoutX(115);
        label.setLayoutY(50);
        
        

    }

}
