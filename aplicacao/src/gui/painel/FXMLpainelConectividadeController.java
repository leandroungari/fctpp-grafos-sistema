/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.coloracao.Coloracao;
import codificacoes.conectividade.Conectividade;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.transposicao.Transposicao;
import desenho.Grafo;
import desenho.RainbowScale;
import gui.FXMLPrincipalController;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelConectividadeController implements Initializable {

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

            Conectividade c = new Conectividade(lista);
            c.algoritmoConectividade(lista);

            Grafo novo = new Grafo(FXMLPrincipalController.painelD, lista.getNumeroVertices(), lista);

            ArrayList<ArrayList<desenho.Vertice>> blocos = new ArrayList<>();
            ArrayList<desenho.Vertice> interno;

            for (codificacoes.conectividade.Vertice v : c.vertices) {
                blocos.add(new ArrayList<>());
            }

            for (codificacoes.conectividade.Vertice v : c.vertices) {

                interno = blocos.get(v.getConjuntoConexo());
                interno.add(novo.getVertice(v.getPosicao()));
            }

            ArrayList<desenho.Vertice> ca;

            int cb = 0;
            do {

                ca = blocos.get(cb);
                if (ca.isEmpty()) {
                    blocos.remove(cb);
                } else {
                    cb++;
                }
            } while (cb < blocos.size());

            RainbowScale cS = new RainbowScale();
            java.awt.Color awt;

            int r, g, b, passo;

            javafx.scene.paint.Color fxColor;

            for (ArrayList<desenho.Vertice> ap : blocos) {

                Line a;
                Circle circle;
                passo = 255 / (blocos.size()+1);
                awt = cS.getColor((blocos.indexOf(ap) + 1) * passo);
                r = awt.getRed();
                g = awt.getGreen();
                b = awt.getBlue();

                fxColor = javafx.scene.paint.Color.rgb(r, g, b);

                for (desenho.Vertice v : ap) {

                    for (desenho.Vertice h : ap) {

                        if (v.getID() == h.getID()) {
                            circle = new Circle(v.getLayoutX(), v.getLayoutY(), 30, fxColor);
                            circle.toBack();

                            FXMLPrincipalController.painelD.getChildren().add(circle);
                        }
                        else{
                            
                            a = new Line(v.getLayoutX(), v.getLayoutY(), h.getLayoutX(), h.getLayoutY());
                            
                            a.setStroke(fxColor);
                            a.toBack();
                            a.setStrokeWidth(30);
                            FXMLPrincipalController.painelD.getChildren().add(a);
                        }
                        
                    }
                }
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
