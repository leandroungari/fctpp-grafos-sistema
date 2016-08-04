/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 * @date   Maio 07,2016
 */
public class Principal extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent parent = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));

        Scene scene = new Scene(parent);
        scene.getStylesheets().addAll("gui/style/principal.css");
        this.stage = primaryStage;
        
        stage.getIcons().add(new Image("gui/images/icon.png"));
        
        stage.setScene(scene);
        stage.setTitle("Trabalho de Grafos II");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private Stage stage;
}
