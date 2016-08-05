/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.representacaoComputacional.Adjacencia;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelAplicacaoPercursoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonExecutar;

    @FXML
    private Button buttonCancelar;

    private Adjacencia lista;
    private Grafo grafo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();

        buttonExecutar.setOnAction(event -> {

            
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXMLpainelAplicacaoPercursoTabela.fxml"));
                
            } catch (IOException ex) {

                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage = new Stage();
            stage.setTitle("Tabela de seleções");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.getIcons().add(new Image("gui/images/icon.png"));

            stage.initStyle(StageStyle.DECORATED);
            stage.show();

        });

        buttonCancelar.setOnAction(event -> {

            FXMLPrincipalController.limparPainelPropriedades();
            FXMLPrincipalController.painelD.getChildren().clear();
        });

    }

}
