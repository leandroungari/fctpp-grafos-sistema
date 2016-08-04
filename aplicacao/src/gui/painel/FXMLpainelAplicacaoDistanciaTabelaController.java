/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.kruskal.Kruskal;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.representacaoComputacional.Arquivo;
import codificacoes.representacaoComputacional.ListaAdjacencia;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import static gui.FXMLPrincipalController.lista;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelAplicacaoDistanciaTabelaController implements Initializable {

    @FXML
    private TableView tabela;

    @FXML
    private TableColumn codigo;

    @FXML
    private TableColumn selecao;

    @FXML
    private TableColumn distancia;

    private Adjacencia lista;
    private Grafo grafo;

    private ObservableList<LinhaTabela> conteudo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();

        tabela.setEditable(true);

        codigo = (TableColumn) tabela.getColumns().get(0);
        selecao = (TableColumn) tabela.getColumns().get(1);
        distancia = (TableColumn) tabela.getColumns().get(2);

        codigo.setStyle("-fx-alignment: CENTER-RIGHT;");
        distancia.setStyle("-fx-alignment: CENTER-RIGHT;");

        codigo.setCellValueFactory(new PropertyValueFactory<LinhaTabela, Integer>("code"));
        selecao.setCellValueFactory(new PropertyValueFactory<LinhaTabela, String>("nome"));
        distancia.setCellValueFactory(new PropertyValueFactory<LinhaTabela, Integer>("distancia"));

        conteudo = FXCollections.observableArrayList(
                new LinhaTabela(0, "Brasil", 0),
                new LinhaTabela(1, "Camarões", 0),
                new LinhaTabela(2, "Croácia", 0),
                new LinhaTabela(3, "México", 0),
                new LinhaTabela(4, "Austrália", 0),
                new LinhaTabela(5, "Chile", 0),
                new LinhaTabela(6, "Espanha", 0),
                new LinhaTabela(7, "Países Baixos", 0),
                new LinhaTabela(8, "Colômbia", 0),
                new LinhaTabela(9, "Costa do Marfim", 0),
                new LinhaTabela(10, "Grécia", 0),
                new LinhaTabela(11, "Japão", 0),
                new LinhaTabela(12, "Costa Rica", 0),
                new LinhaTabela(13, "Inglaterra", 0),
                new LinhaTabela(14, "Itália", 0),
                new LinhaTabela(15, "Uruguai", 0),
                new LinhaTabela(16, "Equador", 0),
                new LinhaTabela(17, "França", 0),
                new LinhaTabela(18, "Honduras", 0),
                new LinhaTabela(19, "Suíça", 0),
                new LinhaTabela(20, "Argentina", 0),
                new LinhaTabela(21, "Bósnia e Herzegovina", 0),
                new LinhaTabela(22, "Irã", 0),
                new LinhaTabela(23, "Nigéria", 0),
                new LinhaTabela(24, "Alemanha", 0),
                new LinhaTabela(25, "Estados Unidos", 0),
                new LinhaTabela(26, "Gana", 0),
                new LinhaTabela(27, "Portugal", 0),
                new LinhaTabela(28, "Argélia", 0),
                new LinhaTabela(29, "Bélgica", 0),
                new LinhaTabela(30, "Coréia do Sul", 0),
                new LinhaTabela(31, "Rússia", 0)
        );

        tabela.setItems(conteudo);

        tabela.getSelectionModel().selectedItemProperty().addListener(event -> {

            LinhaTabela rowData = (LinhaTabela) tabela.getSelectionModel().selectedItemProperty().get();
            int codigo = rowData.getCode();
            //Grafo do percurso
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/percursoSelecoes/percurso.txt");

            Adjacencia listaTemp = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            
            //listaTemp.exibir();
            //lista.exibir();
            //Grafo das distancias
            for (Aresta a : grafo.getEdges()) {
                a.getForma().setStroke(Color.BLACK);
                a.getForma().setStrokeWidth(2);
            }

            Queue<Integer> fila = new LinkedList<>();

            int limite = listaTemp.getNumeroVertices();

            for (int i = 0; i < limite; i++) {

                for (int j = 0; j < limite; j++) {

                    if (listaTemp.verificaAdjacencia(i, j) && listaTemp.getPeso(i, j) == codigo) {

                        System.out.println(i + " " + j + " " + listaTemp.getPeso(i, j));
                        fila.add(i);
                        fila.add(j);
                    }
                }

            }

            int i, j;
            while (!fila.isEmpty()) {

                i = fila.poll();
                j = fila.poll();

                for (Aresta a : grafo.getEdges()) {
                    
                    
                    if(a.getOrigem() == i && a.getDestino() == j){
                        a.getForma().setStroke(Color.RED);
                        a.getForma().setStrokeWidth(4);
                    }
                }
            }
            
            //tem que alterar a implementação da lista de adjacencia porque está sobrescrevendo elementos
            // e calcular a distancia
        });

    }

}
