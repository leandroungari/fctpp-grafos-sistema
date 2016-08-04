/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.dijkstra.Dijkstra;
import codificacoes.dijkstra.Vertice;
import codificacoes.representacaoComputacional.Adjacencia;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelCaminhoMinimoController implements Initializable {

    @FXML
    private TextField verticeInicial;

    @FXML
    private Button buttonExecutar;

    @FXML
    private Button buttonCancelar;

    private Adjacencia lista;
    private Grafo grafo;

    
    private boolean realizado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();
        realizado = false;
        buttonExecutar.setDisable(true);

        verticeInicial.setOnKeyReleased(event -> {

            int valor = 0;

            try {
                valor = Integer.parseInt(verticeInicial.getText());
            } catch (NumberFormatException e) {

                buttonExecutar.setDisable(true);
                return;
            }

            if (valor >= 0 && valor < lista.getNumeroVertices()) {

                buttonExecutar.setDisable(false);
            } else {
                buttonExecutar.setDisable(true);
            }
        });

        buttonExecutar.setOnAction(event -> {
            
            realizado = true;
            
            for (Aresta aresta : grafo.getEdges()) {
                aresta.desselecionarAresta();
            }

            for (desenho.Vertice vertice : grafo.getVertex()) {
                vertice.desselecionarVertice();
            }

            int valor = Integer.parseInt(verticeInicial.getText());

            Vertice a = new Vertice();
            a.setPosicao(valor);

            Dijkstra d = new Dijkstra(lista);
            d.AlgoritmoDijsktra(lista, a);

            Queue<Integer> fila = new LinkedList();
            Integer pai;
            fila.add(valor);

            while (!fila.isEmpty()) {

                pai = fila.poll();
                grafo.getVertice(pai).selecionarVertice();
                for (Vertice vert : d.resultados) {

                    if (vert != null && vert.getPI() != null && vert.getPI().getPosicao() == pai) {

                        grafo.getAresta(pai, vert.getPosicao()).selecionarAresta();
                        fila.add(vert.getPosicao());
                    }
                }

            }

        });

        buttonCancelar.setOnAction(event -> {



            FXMLPrincipalController.limparPainelPropriedades();

            if (realizado) {

                Pane painelDesenho = FXMLPrincipalController.painelD;
                painelDesenho.getChildren().clear();
                grafo.desenhar();
            }

        });

    }

}
