/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.buscaLargura.BuscaLargura;
import codificacoes.buscaLargura.Vertice;
import codificacoes.kruskal.Kruskal;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.representacaoComputacional.ListaAdjacencia;
import codificacoes.representacaoComputacional.RepresentacaoComputacional;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelBuscaLarguraController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonExecutar;

    @FXML
    private Button buttonFechar;

    @FXML
    private TextField verticeInicial;

    private Adjacencia lista;
    private Grafo grafo;
    private boolean realizado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();
        realizado = false;
        buttonExecutar.setDisable(true);

        //tetrminar a busca em largura, tem que reestruturar o grafo !!!!
        buttonExecutar.setOnAction(event -> {

            int valor = Integer.parseInt(verticeInicial.getText());
            
            for(Aresta a: grafo.getEdges()){
                a.getForma().setStroke(Color.BLACK);
            }

            Vertice vertice = new Vertice();
            vertice.setPosicao(valor);

            realizado = true;
            BuscaLargura busca = new BuscaLargura(lista);
            busca.BFS(lista, vertice);

            Queue<Integer> fila = new LinkedList();
            Integer pai;
            fila.add(valor);
            Queue<Integer> resultado = new LinkedList<>();

            while (!fila.isEmpty()) {

                pai = fila.poll();
                //grafo.getVertice(pai).selecionarVertice();
                for (Vertice vert : busca.vertices) {

                    if (vert != null && vert.getPredecessor() != null && vert.getPredecessor().getPosicao() == pai) {

                        //grafo.getAresta(pai, vert.getPosicao()).selecionarAresta();
                        //nova.adicionaAresta(pai, vert.getPosicao(), lista.getPeso(pai, vert.getPosicao()));
                        resultado.add(pai);
                        resultado.add(vert.getPosicao());
                        fila.add(vert.getPosicao());
                    }
                }
            }
            int x, y;

            System.out.println(resultado);

            System.out.println(grafo.getEdges());
            while (!resultado.isEmpty()) {
                x = resultado.poll();
                y = resultado.poll();

                for (Aresta a : grafo.getEdges()) {

                    if (!lista.isTipoGrafo()) {
                        System.out.println("passou");
                        if (x == a.getOrigem() && y == a.getDestino() || y == a.getOrigem() && x == a.getDestino()) {
                            a.getForma().setStroke(Color.RED);
                            break;
                        }
                    } else if (x == a.getOrigem() && y == a.getDestino()) {
                        a.getForma().setStroke(Color.RED);
                        break;
                    }

                }
            }

        });

        buttonFechar.setOnAction(event -> {

            FXMLPrincipalController.limparPainelPropriedades();

            if (realizado) {

                Pane painelDesenho = FXMLPrincipalController.painelD;
                painelDesenho.getChildren().clear();
                grafo.desenhar();
            }

        });

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

    }

}
