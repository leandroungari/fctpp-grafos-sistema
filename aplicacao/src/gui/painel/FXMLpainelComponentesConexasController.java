/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.buscaProfundidade.BuscaProfundidade;
import codificacoes.representacaoComputacional.Adjacencia;
import desenho.Aresta;
import desenho.Grafo;
import desenho.RainbowScale;
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
public class FXMLpainelComponentesConexasController implements Initializable {

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

            Grafo a = new Grafo(FXMLPrincipalController.painelD, lista.getNumeroVertices(), lista);
            FXMLPrincipalController.painelD.getChildren().clear();

            realizado = true;
            BuscaProfundidade busca = new BuscaProfundidade(lista);
            busca.DFS(lista, 0);
            javafx.scene.paint.Color fxColor;
            RainbowScale cS = new RainbowScale();
            int r, g, b, colorStep = 255/lista.getNumeroVertices();

            for (int i = 0; i < lista.getNumeroVertices(); i++) {

                java.awt.Color awt = cS.getColor(busca.vertices[i].getComponenteConexa()* colorStep);
                r = awt.getRed();
                g = awt.getGreen();
                b = awt.getBlue();

                fxColor = javafx.scene.paint.Color.rgb(r, g, b);
                a.getVertice(i).setFill(fxColor);
            }
            
            for(Aresta aresta: a.getEdges()){
                
                java.awt.Color awt = cS.getColor(busca.vertices[aresta.getOrigem()].getComponenteConexa()* colorStep);
                r = awt.getRed();
                g = awt.getGreen();
                b = awt.getBlue();

                fxColor = javafx.scene.paint.Color.rgb(r, g, b);
                aresta.getForma().setFill(fxColor);
                aresta.a.setStroke(fxColor);
                aresta.b.setStroke(fxColor);
            }
            
            a.desenhar();
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
