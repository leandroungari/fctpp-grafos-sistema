/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.transposicao.Transposicao;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelTransposicaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonExecutar;

    @FXML
    private Button buttonFechar;

    private Adjacencia lista;
    private Grafo grafo;
    private boolean realizado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();
        realizado = false;
        buttonExecutar.setOnAction(event -> {
            realizado = true;
            FXMLPrincipalController.painelD.getChildren().clear();
            Adjacencia a = Transposicao.RealizarTransposicao(lista);
            Grafo grafoTransposto = new Grafo(FXMLPrincipalController.painelD, a.getNumeroVertices(), a);
            grafoTransposto.desenhar();

        });

        buttonFechar.setOnAction(event -> {

            FXMLPrincipalController.limparPainelPropriedades();
            
            if (realizado) {
                
                Pane painelDesenho = FXMLPrincipalController.painelD;
                painelDesenho.getChildren().clear();
                grafo.desenhar();
            }

        });

    }

}
