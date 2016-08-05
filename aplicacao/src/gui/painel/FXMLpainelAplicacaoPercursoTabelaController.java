/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.representacaoComputacional.AdjacenciaMultipla;
import codificacoes.representacaoComputacional.Arquivo;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelAplicacaoPercursoTabelaController implements Initializable {

    @FXML
    private TableView tabela;

    @FXML
    private TableColumn codigo;

    @FXML
    private TableColumn selecao;


    private Adjacencia lista;
    private Grafo grafo;

    private ObservableList<LinhaTabelaDados> conteudo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();

        tabela.setEditable(true);

        codigo = (TableColumn) tabela.getColumns().get(0);
        selecao = (TableColumn) tabela.getColumns().get(1);

        codigo.setStyle("-fx-alignment: CENTER-RIGHT;");

        codigo.setCellValueFactory(new PropertyValueFactory<LinhaTabelaDados, Integer>("code"));
        selecao.setCellValueFactory(new PropertyValueFactory<LinhaTabelaDados, String>("nome"));

        conteudo = FXCollections.observableArrayList(
                new LinhaTabelaDados(0, "Brasil"),
                new LinhaTabelaDados(1, "Camarões"),
                new LinhaTabelaDados(2, "Croácia"),
                new LinhaTabelaDados(3, "México"),
                new LinhaTabelaDados(4, "Austrália"),
                new LinhaTabelaDados(5, "Chile"),
                new LinhaTabelaDados(6, "Espanha"),
                new LinhaTabelaDados(7, "Países Baixos"),
                new LinhaTabelaDados(8, "Colômbia"),
                new LinhaTabelaDados(9, "Costa do Marfim"),
                new LinhaTabelaDados(10, "Grécia"),
                new LinhaTabelaDados(11, "Japão"),
                new LinhaTabelaDados(12, "Costa Rica"),
                new LinhaTabelaDados(13, "Inglaterra"),
                new LinhaTabelaDados(14, "Itália"),
                new LinhaTabelaDados(15, "Uruguai"),
                new LinhaTabelaDados(16, "Equador"),
                new LinhaTabelaDados(17, "França"),
                new LinhaTabelaDados(18, "Honduras"),
                new LinhaTabelaDados(19, "Suíça"),
                new LinhaTabelaDados(20, "Argentina"),
                new LinhaTabelaDados(21, "Bósnia e Herzegovina"),
                new LinhaTabelaDados(22, "Irã"),
                new LinhaTabelaDados(23, "Nigéria"),
                new LinhaTabelaDados(24, "Alemanha"),
                new LinhaTabelaDados(25, "Estados Unidos"),
                new LinhaTabelaDados(26, "Gana"),
                new LinhaTabelaDados(27, "Portugal"),
                new LinhaTabelaDados(28, "Argélia"),
                new LinhaTabelaDados(29, "Bélgica"),
                new LinhaTabelaDados(30, "Coréia do Sul"),
                new LinhaTabelaDados(31, "Rússia")
        );

        tabela.setItems(conteudo);

        tabela.getSelectionModel().selectedItemProperty().addListener(event -> {

            LinhaTabelaDados rowData = (LinhaTabelaDados) tabela.getSelectionModel().selectedItemProperty().get();
            int codigo = rowData.getCode();
            //Grafo do percurso
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/percursoSelecoes/percurso.txt");

            AdjacenciaMultipla listaTemp = Arquivo.leituraArquivoMultiplo(file.getAbsolutePath(), false);

            //listaTemp.exibir();
            //lista.exibir();
            //Grafo das distancias
            for (Aresta a : grafo.getEdges()) {
                a.getForma().setStroke(Color.BLACK);
                a.getForma().setStrokeWidth(2);
            }

            Queue<Integer> fila = new LinkedList<>();
            int[] repetidos;
            int limite = listaTemp.getNumeroVertices();

            for (int i = 0; i < limite; i++) {

                for (int j = 0; j < limite; j++) {

                    if (listaTemp.verificaAdjacencia(i, j)) {

                        repetidos = listaTemp.getPeso(i, j);

                        for (int a : repetidos) {

                            if (a == codigo) {

                                fila.add(i);
                                fila.add(j);
                            }
                        }

                    }
                }

            }

            int i, j;
            while (!fila.isEmpty()) {

                i = fila.poll();
                j = fila.poll();

                for (Aresta a : grafo.getEdges()) {

                    if (a.getOrigem() == i && a.getDestino() == j) {
                        a.getForma().setStroke(Color.RED);
                        a.getForma().setStrokeWidth(4);
                        a.getForma().toFront();
                        a.getOrigemVertice().toFront();
                        a.getOrigemVertice().numero.toFront();
                        a.getDestinoVertice().toFront();
                        a.getDestinoVertice().numero.toFront();
                        if(a.getLabelPeso() != null) a.getLabelPeso().toFront();
                        if(a.getLabelPeso() != null) a.getLabelPeso().setStroke(Color.RED);
                        a.a.setStroke(Color.RED);
                        a.b.setStroke(Color.RED);
                    }
                }
            }

        });

    }

}
