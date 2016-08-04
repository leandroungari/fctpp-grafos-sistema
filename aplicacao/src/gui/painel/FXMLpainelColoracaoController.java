/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.bellmanFord.Vertice;
import codificacoes.coloracao.Coloracao;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.transposicao.Transposicao;
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
public class FXMLpainelColoracaoController implements Initializable {

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
            
            Coloracao c = new Coloracao(lista);
            c.AlgoritmoColoracao(lista);
            
            javafx.scene.paint.Color fxColor;
            java.awt.Color awt;
            RainbowScale cS = new RainbowScale();
            int r,g,b;
            
            Grafo novo = new Grafo(FXMLPrincipalController.painelD, lista.getNumeroVertices(), lista);

            for (codificacoes.coloracao.Vertice v : c.vertices) {

                desenho.Vertice vertD = novo.getVertice(v.getPosicao());

                awt = cS.getColor(v.getCor());
                r = awt.getRed();
                g = awt.getGreen();
                b = awt.getBlue();

                fxColor = javafx.scene.paint.Color.rgb(r, g, b);
                vertD.setFill(fxColor);
            }
            
            novo.desenhar();

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
