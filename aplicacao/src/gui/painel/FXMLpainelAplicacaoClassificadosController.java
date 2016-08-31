/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.kruskal.Kruskal;
import codificacoes.representacaoComputacional.Adjacencia;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelAplicacaoClassificadosController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private Button buttonCancelar;

    private Adjacencia lista;
    private Grafo grafo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();


        buttonCancelar.setOnAction(event -> {

            FXMLPrincipalController.limparPainelPropriedades();
            FXMLPrincipalController.painelD.getChildren().clear();
        });

    }

}
