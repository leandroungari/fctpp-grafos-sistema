/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desenho;

import codificacoes.buscaProfundidade.Pilha;
import codificacoes.representacaoComputacional.Adjacencia;
import gui.FXMLPrincipalController;
import static gui.FXMLPrincipalController.lista;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import jfxtras.labs.util.event.MouseControlUtil;

/**
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 * @date May 13,2016
 */
public class Desenho {

    private static double initialX;
    private static double initialY;
    
    public static Grafo novo;

    public static void desenharVertice(Pane pane, Vertice shape, Grafo grafo) {

        MouseControlUtil.makeDraggable(shape, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                shape.corrigirArestas(grafo);
            }
        }, null);

        shape.setOnDragDetected((MouseEvent event) -> {
            initialX = shape.getLayoutX();
            initialY = shape.getLayoutY();
            shape.setLayoutX(initialX);
            shape.setLayoutY(initialY);
            shape.numero.setLayoutX(shape.getLayoutX() - 5);
            shape.numero.setLayoutY(shape.getLayoutY() + 5);
            shape.numero.toFront();
        });

        shape.setOnMouseReleased(me -> {

            Point2D mouse = new Point2D(shape.getLayoutX(), shape.getLayoutY());
            Rectangle2D area = new Rectangle2D(pane.getLayoutX(), pane.getLayoutY(), 685.0, 470.0);
            if (!area.contains(mouse)) {

                shape.setLayoutX(initialX);
                shape.setLayoutY(initialY);
            }

            shape.corrigirArestas(grafo);
        });

        pane.getChildren().add(shape);
        shape.numero.setLayoutX(shape.getLayoutX() - 4);
        shape.numero.setLayoutY(shape.getLayoutY() + 4);
        pane.getChildren().add(shape.numero);
    }

    public static void desenharAresta(Pane pane, Aresta aresta) {

        aresta.getForma().setStroke(Paint.valueOf("#000"));

        pane.getChildren().add(aresta.getForma());
        aresta.getForma().setStrokeWidth(2);

        if (aresta.getPeso() != 0) {
            pane.getChildren().add(aresta.labelPeso);
        }

        if (aresta.isDirected() && !(aresta instanceof Loop)) {
            pane.getChildren().add(aresta.a);
            pane.getChildren().add(aresta.b);
            aresta.a.setStrokeWidth(2);
            aresta.b.setStrokeWidth(2);
        }

    }

    public static void desenharTopologico(Pilha pilha) {

        FXMLPrincipalController.painelD.getChildren().clear();

        ArrayList<codificacoes.buscaProfundidade.Vertice> conjunto = new ArrayList();

        while (!pilha.estaVazia()) {
            conjunto.add(pilha.pop());
        }

        int quantidade = conjunto.size();

        novo = new Grafo(FXMLPrincipalController.painelD, quantidade, FXMLPrincipalController.lista, true, conjunto);
        
        novo.desenhar();

    }
}
