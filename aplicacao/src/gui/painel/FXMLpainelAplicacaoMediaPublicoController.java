/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;

import codificacoes.conjuntoDados.ConjuntoDados;
import codificacoes.conjuntoDados.VerticeSelecao;
import codificacoes.kruskal.Kruskal;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.representacaoComputacional.Arquivo;
import desenho.Aresta;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import static gui.FXMLPrincipalController.grafo;
import static gui.FXMLPrincipalController.lista;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLpainelAplicacaoMediaPublicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button buttonExecutar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Text media;

    @FXML
    private ChoiceBox choice;
    
    @FXML
    private Text media1;
    
    @FXML
    private Text media2;
    
    @FXML
    private Text media3;
    
    @FXML
    private Text media4;
    
    @FXML
    private Text media5;

    private Adjacencia lista;
    private Grafo grafo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        lista = FXMLPrincipalController.getLista();
        grafo = FXMLPrincipalController.getGrafo();

        choice.setItems(FXCollections.observableArrayList("Grupo A", "Grupo B", "Grupo C", "Grupo D", "Grupo E", "Grupo F", "Grupo G", "Grupo H"));

        ConjuntoDados c1 = new ConjuntoDados(lista);
        ConjuntoDados c2 = new ConjuntoDados(lista);
        c1.calcularMediaPublicoDeUmGrupo();

        double soma = 0;
        for (VerticeSelecao v : c1.selecoes) {
            soma += v.getMediaDePublico();
        }

        soma /= c1.quantSelecoes;

        media.setText(media.getText() + String.format("%.2f", soma));
        
        VerticeSelecao[] conj = c2.calcularSelecaoMaiorMediaPublicoFaseGrupos();
        
        media1.setText(conj[0].getNumero() + " - " + String.format("%.2f", conj[0].getMediaDePublico()));
        media2.setText(conj[1].getNumero() + " - " + String.format("%.2f", conj[1].getMediaDePublico()));
        media3.setText(conj[2].getNumero() + " - " + String.format("%.2f", conj[2].getMediaDePublico()));
        media4.setText(conj[3].getNumero() + " - " + String.format("%.2f", conj[3].getMediaDePublico()));
        media5.setText(conj[4].getNumero() + " - " + String.format("%.2f", conj[4].getMediaDePublico()));
        
        

        buttonExecutar.setOnAction(event -> {

            String choiceValor = (String) choice.getSelectionModel().getSelectedItem();
            String arquivo = null;
            switch (choiceValor) {

                case "Grupo A":
                    arquivo = "grupoA";
                    break;

                case "Grupo B":
                    arquivo = "grupoB";
                    break;

                case "Grupo C":
                    arquivo = "grupoC";
                    break;

                case "Grupo D":
                    arquivo = "grupoD";
                    break;

                case "Grupo E":
                    arquivo = "grupoE";
                    break;

                case "Grupo F":
                    arquivo = "grupoF";
                    break;

                case "Grupo G":
                    arquivo = "grupoG";
                    break;

                case "Grupo H":
                    arquivo = "grupoH";
                    break;

                default:
                    System.out.println("errado");
                    return;
            }

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/publicoGrupos/" + arquivo + ".txt");
            System.out.println(file.getAbsolutePath());
            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            FXMLPrincipalController.painelD.getChildren().clear();
            grafo = new Grafo(FXMLPrincipalController.painelD, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados grupo = new ConjuntoDados(lista);
            grupo.calcularMediaPublicoDeUmGrupo();

            double somaGrupo = 0;
            for (VerticeSelecao v : grupo.selecoes) {
                somaGrupo += v.getMediaDePublico();
            }

            somaGrupo /= grupo.quantSelecoes;
            

            media.setText("Média " + choiceValor + ": " + String.format("%.2f", somaGrupo));
        });

        buttonCancelar.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.limparPainelPropriedades();
        });

    }

}
