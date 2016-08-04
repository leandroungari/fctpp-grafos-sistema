/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.representacaoComputacional.Adjacencia;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelAplicacaoTabelaSedesController implements Initializable {

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
                new LinhaTabelaDados(0, "Porto Alegre - RS"),
                new LinhaTabelaDados(1, "Curitiba - PR"),
                new LinhaTabelaDados(2, "São Paulo - SP"),
                new LinhaTabelaDados(3, "Rio de Janeiro - RJ"),
                new LinhaTabelaDados(4, "Belo Horizonte - MG"),
                new LinhaTabelaDados(5, "Salvador - BA"),
                new LinhaTabelaDados(6, "Recife - PE"),
                new LinhaTabelaDados(7, "Natal - RN"),
                new LinhaTabelaDados(8, "Brasília - DF"),
                new LinhaTabelaDados(9, "Fortaleza - CE"),
                new LinhaTabelaDados(10, "Cuiabá - MT"),
                new LinhaTabelaDados(11, "Manaus - AM")
        );

        tabela.setItems(conteudo);

    }

}
