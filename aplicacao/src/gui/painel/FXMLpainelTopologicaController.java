/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.painel;


import codificacoes.buscaProfundidade.BuscaProfundidade;
import codificacoes.buscaProfundidade.Pilha;
import codificacoes.representacaoComputacional.Adjacencia;
import desenho.Grafo;
import gui.FXMLPrincipalController;
import java.net.URL;
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
public class FXMLpainelTopologicaController implements Initializable {

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
        
        
        buttonExecutar.setOnAction(event -> {
            
            realizado = true;
            int valor = Integer.parseInt(verticeInicial.getText());
            
            BuscaProfundidade busca = new BuscaProfundidade(lista);
            busca.DFS(lista, valor);
            
            Pilha pilha = busca.pilhaTopologica;
            
            FXMLPrincipalController.painelD.getChildren().clear();
            desenho.Desenho.desenharTopologico(pilha);
            
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
            
            try{
                valor = Integer.parseInt(verticeInicial.getText());
            }
            catch(NumberFormatException e){
                
                buttonExecutar.setDisable(true);
                return;
            }
            
            if(valor >= 0 && valor < lista.getNumeroVertices()){
                
                buttonExecutar.setDisable(false);
            }
            else{
                buttonExecutar.setDisable(true);
            }
        });

    }

}
