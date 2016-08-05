/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import codificacoes.conjuntoDados.ConjuntoDados;
import codificacoes.conjuntoDados.VerticeSelecao;
import codificacoes.representacaoComputacional.*;
import desenho.Grafo;
import desenho.Vertice;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

/**
 *
 * @author Leandro Ungari <leandroungari@gmail.com>
 */
public class FXMLPrincipalController implements Initializable {

    @FXML
    private Label label;

    ////////////
    @FXML
    private Pane painelDesenho;

    @FXML
    private Pane painelPropriedades;

    @FXML
    private MenuItem novoArquivo;

    @FXML
    private MenuItem sairArquivo;

    @FXML
    private MenuItem fecharArquivo;

    @FXML
    private MenuItem exportarArquivo;

    //////////////
    @FXML
    private MenuItem caminhominimo;

    @FXML
    private MenuItem componentesConexas;

    @FXML
    private MenuItem coloracao;

    @FXML
    private MenuItem conectividade;

    @FXML
    private MenuItem transposicao;

    @FXML
    private MenuItem ordemTopologica;

    @FXML
    private MenuItem largura;

    @FXML
    private MenuItem arvoreminima;

    @FXML
    private MenuItem sobre;

    /*Aplicação*/
    @FXML
    private MenuItem aplicacaoGeral;

    @FXML
    private MenuItem aplicacaoGrupos;

    @FXML
    private MenuItem aplicacaoOitavas;

    @FXML
    private MenuItem aplicacaoQuartas;

    @FXML
    private MenuItem aplicacaoSemi;

    @FXML
    private MenuItem aplicacaoFinal;

    @FXML
    private MenuItem aplicacaoMedia;

    @FXML
    private MenuItem aplicacaoPercurso;

    @FXML
    private MenuItem aplicacaoDistancia;

    @FXML
    private MenuItem aplicacaoSelecoes;

    @FXML
    private MenuItem aplicacaoSedes;

    //////////////
    public static Adjacencia lista;
    public static Grafo grafo;
    public static Pane painel;
    public static Pane painelD;

    public static File arquivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /*painelDesenho.setOnMousePressed((MouseEvent event) -> {
         Shape s = Desenho.desenharVertice(painelDesenho, event.getX(), event.getY());
                     
         });*/
        fecharArquivo.setDisable(true);
        exportarArquivo.setDisable(true);
        painel = painelPropriedades;
        painelD = painelDesenho;

        novoArquivo.setOnAction(event -> {

            FileChooser janela = new FileChooser();
            ExtensionFilter extFilter = new ExtensionFilter("Arquivo de texto (*.txt)", "*.txt");
            janela.getExtensionFilters().add(extFilter);

            arquivo = janela.showOpenDialog(null);
            if (arquivo != null) {

                lista = Arquivo.leituraArquivo(arquivo.getAbsolutePath(), false);
                painelDesenho.getChildren().clear();
                grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
                grafo.desenhar();

                fecharArquivo.setDisable(false);
                exportarArquivo.setDisable(false);

            }

        });

        sairArquivo.setOnAction(event -> {

            System.exit(0);
        });

        fecharArquivo.setOnAction(event -> {

            painelPropriedades.getChildren().clear();
            painelDesenho.getChildren().clear();
            fecharArquivo.setDisable(true);
        });

        componentesConexas.setOnAction(event -> {

            if (lista.isTipoGrafo()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Verifique o tipo do grafo");
                alert.setHeaderText("Atenção, isto é uma mensagem de aviso!");
                alert.setContentText("Este algoritmo é destinado somente para grafos não direcionados.");
                alert.showAndWait();
                return;
            }

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelComponentesConexas.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        coloracao.setOnAction(event -> {

            if (lista.isTipoGrafo()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Verifique o tipo do grafo");
                alert.setHeaderText("Atenção, isto é uma mensagem de aviso!");
                alert.setContentText("Este algoritmo é destinado somente para grafos não direcionados.");
                alert.showAndWait();
                return;
            }

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelColoracao.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        conectividade.setOnAction(event -> {

            if (!lista.isTipoGrafo()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Verifique o tipo do grafo");
                alert.setHeaderText("Atenção, isto é uma mensagem de aviso!");
                alert.setContentText("Este algoritmo é destinado somente para grafos direcionados.");
                alert.showAndWait();
                return;
            }

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelConectividade.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        transposicao.setOnAction(event -> {

            if (!lista.isTipoGrafo()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Verifique o tipo do grafo");
                alert.setHeaderText("Atenção, isto é uma mensagem de aviso!");
                alert.setContentText("Este algoritmo é destinado somente para grafos direcionados.");
                alert.showAndWait();
                return;
            }

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelTransposicao.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        ordemTopologica.setOnAction(event -> {
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelTopologica.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        largura.setOnAction(event -> {

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelBuscaLargura.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        arvoreminima.setOnAction(event -> {

            if (lista.isTipoGrafo()) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Verifique o tipo do grafo");
                alert.setHeaderText("Atenção, isto é uma mensagem de aviso!");
                alert.setContentText("Este algoritmo é destinado somente para grafos não direcionados.");
                alert.showAndWait();
                return;
            }

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelArvoreMinima.fxml")));

                //Prim prim = new Prim(lista);
                //prim.AGM_Prim(0, lista);
            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        caminhominimo.setOnAction(event -> {

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelCaminhoMinimo.fxml")));
            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        sobre.setOnAction(event -> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("FXMLSobre.fxml"));

            } catch (IOException ex) {

                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage = new Stage();
            stage.setTitle("Sobre");
            stage.setScene(new Scene(root, 450, 450));
            //stage.setAlwaysOnTop(true);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);

            stage.getIcons().add(new Image("gui/images/icon.png"));

            stage.initStyle(StageStyle.DECORATED);
            stage.showAndWait();
        });

        exportarArquivo.setOnAction(event -> {

            FileChooser dialog = new FileChooser();

            dialog.setTitle("Salvar grafo");

            FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG Files *.png", "*.png");
            dialog.getExtensionFilters().add(filter);
            File a = dialog.showSaveDialog(null);
            if (a != null) {
                a.setReadOnly();
                WritableImage image = FXMLPrincipalController.painelD.snapshot(new SnapshotParameters(), null);

                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", a);
                } catch (IOException e) {
                    return;
                }

            }
        });

        aplicacaoDistancia.setOnAction(event -> {

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/distanciaSedes/distancia.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoDistancia.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoFinal.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.painel.getChildren().clear();

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/competicaoCompleta/competicaoCompleta.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados conj = new ConjuntoDados(lista);
            conj.calcularCampeao();

            int numero = 0;
            String fase = "";

            for (Vertice a : grafo.getVertex()) {

                numero = a.getID();

                for (VerticeSelecao vert : conj.selecoes) {

                    if (vert.getNumero() == numero) {
                        fase = vert.getFaseDaCompeticao();
                        break;
                    }
                }

                if (fase.equalsIgnoreCase("Final")) {
                    a.setFill(Color.RED);
                } else {
                    a.setFill(Color.BLACK);
                }

            }

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoAlcance.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        aplicacaoGrupos.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.painel.getChildren().clear();

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/competicaoCompleta/competicaoCompleta.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados conj = new ConjuntoDados(lista);
            conj.calcularCampeao();

            int numero = 0;
            String fase = "";

            for (Vertice a : grafo.getVertex()) {

                numero = a.getID();

                for (VerticeSelecao vert : conj.selecoes) {

                    if (vert.getNumero() == numero) {
                        fase = vert.getFaseDaCompeticao();
                        break;
                    }
                }

                if (fase.equalsIgnoreCase("Fase de Grupos")) {
                    a.setFill(Color.BLACK);
                } else {
                    a.setFill(Color.GRAY);
                }

            }

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoAlcance.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoGeral.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.painel.getChildren().clear();

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/competicaoCompleta/competicaoCompleta.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados conj = new ConjuntoDados(lista);
            conj.calcularCampeao();

            int numero = 0;
            String fase = "";

            for (Vertice a : grafo.getVertex()) {

                numero = a.getID();

                for (VerticeSelecao vert : conj.selecoes) {

                    if (vert.getNumero() == numero) {
                        fase = vert.getFaseDaCompeticao();
                        break;
                    }
                }

                switch (fase) {

                    case "Fase de grupos":
                        a.setFill(Color.BLACK);
                        break;

                    case "Oitavas de final":
                        a.setFill(Color.LIGHTBLUE);
                        break;

                    case "Quartas de final":
                        a.setFill(Color.GREEN);
                        break;

                    case "Semi-final":
                        a.setFill(Color.ORANGE);
                        break;

                    case "Final":
                        a.setFill(Color.RED);
                        break;

                    default:

                        System.out.println("deu errado :(");
                        return;
                }
            }

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoAlcance.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Média de público
        aplicacaoMedia.setOnAction(event -> {
            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/publicoGrupos/faseDeGrupos.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoMediaPublico.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoOitavas.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.painel.getChildren().clear();

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/competicaoCompleta/competicaoCompleta.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados conj = new ConjuntoDados(lista);
            conj.calcularCampeao();

            int numero = 0;
            String fase = "";

            for (Vertice a : grafo.getVertex()) {

                numero = a.getID();

                for (VerticeSelecao vert : conj.selecoes) {

                    if (vert.getNumero() == numero) {
                        fase = vert.getFaseDaCompeticao();
                        break;
                    }
                }

                if (fase.equalsIgnoreCase("Oitavas de Final")) {
                    a.setFill(Color.LIGHTBLUE);
                } else {
                    a.setFill(Color.BLACK);
                }

            }

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoAlcance.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoPercurso.setOnAction(event -> {
            
            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/percursoSelecoes/percurso.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoPercurso.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoQuartas.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.painel.getChildren().clear();

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/competicaoCompleta/competicaoCompleta.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados conj = new ConjuntoDados(lista);
            conj.calcularCampeao();

            int numero = 0;
            String fase = "";

            for (Vertice a : grafo.getVertex()) {

                numero = a.getID();

                for (VerticeSelecao vert : conj.selecoes) {

                    if (vert.getNumero() == numero) {
                        fase = vert.getFaseDaCompeticao();
                        break;
                    }
                }

                if (fase.equalsIgnoreCase("Quartas de Final")) {
                    a.setFill(Color.GREEN);
                } else {
                    a.setFill(Color.BLACK);
                }

            }

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoAlcance.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoSemi.setOnAction(event -> {

            FXMLPrincipalController.painelD.getChildren().clear();
            FXMLPrincipalController.painel.getChildren().clear();

            //Mostrando grafo
            File file = new File("distancia.txt");
            int posicao = file.getAbsolutePath().indexOf("distancia.txt");
            String path = file.getAbsolutePath().substring(0, posicao - 10);
            file = new File(path + "grafos/conjuntoDeDados/grafosDigrafos/competicaoCompleta/competicaoCompleta.txt");

            lista = Arquivo.leituraArquivo(file.getAbsolutePath(), false);
            painelDesenho.getChildren().clear();
            grafo = new Grafo(painelDesenho, lista.getNumeroVertices(), lista);
            grafo.desenhar();

            ConjuntoDados conj = new ConjuntoDados(lista);
            conj.calcularCampeao();

            int numero = 0;
            String fase = "";

            for (Vertice a : grafo.getVertex()) {

                numero = a.getID();

                for (VerticeSelecao vert : conj.selecoes) {

                    if (vert.getNumero() == numero) {
                        fase = vert.getFaseDaCompeticao();
                        break;
                    }
                }

                if (fase.equalsIgnoreCase("Semi-Final")) {
                    a.setFill(Color.ORANGE);
                } else {
                    a.setFill(Color.BLACK);
                }

            }

            fecharArquivo.setDisable(false);
            exportarArquivo.setDisable(false);

            //Mostrando barra
            try {

                painelPropriedades.getChildren().setAll((Pane) FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoAlcance.fxml")));

            } catch (IOException ex) {
                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        aplicacaoSelecoes.setOnAction(event -> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoTabelaSelecoes.fxml"));

            } catch (IOException ex) {

                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage = new Stage();
            stage.setTitle("Tabela de seleções");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.getIcons().add(new Image("gui/images/icon.png"));

            stage.initStyle(StageStyle.DECORATED);
            stage.show();

        });

        aplicacaoSedes.setOnAction(event -> {

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("painel/FXMLpainelAplicacaoTabelaSedes.fxml"));

            } catch (IOException ex) {

                Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Stage stage = new Stage();
            stage.setTitle("Tabela de sedes");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);

            stage.getIcons().add(new Image("gui/images/icon.png"));

            stage.initStyle(StageStyle.DECORATED);
            stage.show();

        });

    }

    public static Adjacencia getLista() {

        return lista;
    }

    public static Grafo getGrafo() {

        return grafo;
    }

    public static void limparPainelPropriedades() {

        painel.getChildren().clear();
    }
}
