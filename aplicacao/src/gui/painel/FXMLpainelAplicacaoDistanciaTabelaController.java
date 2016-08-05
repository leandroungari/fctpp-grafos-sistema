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

        File fileA = new File("distancia.txt");
        int posicaoA = fileA.getAbsolutePath().indexOf("distancia.txt");
        String pathA = fileA.getAbsolutePath().substring(0, posicaoA - 10);
        fileA = new File(pathA + "grafos/conjuntoDeDados/grafosDigrafos/percursoSelecoes/percurso.txt");

        AdjacenciaMultipla listaTempA = Arquivo.leituraArquivoMultiplo(fileA.getAbsolutePath(), false);

        Queue<Integer> filaA = new LinkedList<>();
        int[] repetidosA;
        int limiteA = listaTempA.getNumeroVertices();

        int[] total = new int[32];

        for (int i = 0; i < limiteA; i++) {

            for (int j = 0; j < limiteA; j++) {

                if (listaTempA.verificaAdjacencia(i, j)) {

                    repetidosA = listaTempA.getPeso(i, j);

                    for (int a : repetidosA) {

                        filaA.add(i);
                        filaA.add(j);
                        filaA.add(a); 
                    }

                }
            }
        }
        int m, n, local;
        
        while(!filaA.isEmpty()){
            
            m = filaA.poll();
            n = filaA.poll();
            local = filaA.poll();
            
            total[local] += lista.getPeso(m, n);
        }

        conteudo = FXCollections.observableArrayList(
                new LinhaTabela(0, "Brasil", total[0]),
                new LinhaTabela(1, "Camarões", total[1]),
                new LinhaTabela(2, "Croácia", total[2]),
                new LinhaTabela(3, "México", total[3]),
                new LinhaTabela(4, "Austrália", total[4]),
                new LinhaTabela(5, "Chile", total[5]),
                new LinhaTabela(6, "Espanha", total[6]),
                new LinhaTabela(7, "Países Baixos", total[7]),
                new LinhaTabela(8, "Colômbia", total[8]),
                new LinhaTabela(9, "Costa do Marfim", total[9]),
                new LinhaTabela(10, "Grécia", total[10]),
                new LinhaTabela(11, "Japão", total[11]),
                new LinhaTabela(12, "Costa Rica", total[12]),
                new LinhaTabela(13, "Inglaterra", total[13]),
                new LinhaTabela(14, "Itália", total[14]),
                new LinhaTabela(15, "Uruguai", total[15]),
                new LinhaTabela(16, "Equador", total[16]),
                new LinhaTabela(17, "França", total[17]),
                new LinhaTabela(18, "Honduras", total[18]),
                new LinhaTabela(19, "Suíça", total[19]),
                new LinhaTabela(20, "Argentina", total[20]),
                new LinhaTabela(21, "Bósnia e Herzegovina", total[21]),
                new LinhaTabela(22, "Irã", total[22]),
                new LinhaTabela(23, "Nigéria", total[23]),
                new LinhaTabela(24, "Alemanha", total[24]),
                new LinhaTabela(25, "Estados Unidos", total[25]),
                new LinhaTabela(26, "Gana", total[26]),
                new LinhaTabela(27, "Portugal", total[27]),
                new LinhaTabela(28, "Argélia", total[28]),
                new LinhaTabela(29, "Bélgica", total[29]),
                new LinhaTabela(30, "Coréia do Sul", total[30]),
                new LinhaTabela(31, "Rússia", total[31])
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

                    if (a.getOrigem() == i && a.getDestino() == j || a.getOrigem() == j && a.getDestino() == i) {
                        a.getForma().setStroke(Color.RED);
                        a.getForma().setStrokeWidth(4);
                        a.getForma().toFront();
                        a.getOrigemVertice().toFront();
                        a.getOrigemVertice().numero.toFront();
                        a.getDestinoVertice().toFront();
                        a.getDestinoVertice().numero.toFront();
                        if(a.getLabelPeso() != null) a.getLabelPeso().setStroke(Color.RED);
                        if(a.getLabelPeso() != null) a.getLabelPeso().toFront();
                    }
                }
            }

        });

    }

}
