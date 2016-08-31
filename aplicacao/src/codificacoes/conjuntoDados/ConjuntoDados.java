/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.conjuntoDados;

import codificacoes.representacaoComputacional.Adjacencia;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author BRUNO
 */
public class ConjuntoDados {
    
    public VerticeSelecao [] selecoes;
    public int quantSelecoes;
    public Adjacencia adj;
    
    /** 
     * Nesta classe a ideia seria utilizar os algoritmos já implementados
     * nas codificações anteriores para processar os:
     * resultados dos jogos, media de publicos, distancia percorida, entre outros.
     **/
    public ConjuntoDados(Adjacencia adj){
    
        this.adj = adj;
        this.quantSelecoes = adj.getNumeroVertices();
        this.selecoes = new VerticeSelecao[this.quantSelecoes];
        
        for(int i = 0; i < this.quantSelecoes; i++){
            
            this.selecoes[i] = new VerticeSelecao(i);
        }
    }
    
    public void calcularClassificadosDeUmGrupo(){
        
        CDBuscaProfundidade cdb = new CDBuscaProfundidade(this.adj, this.selecoes);
        cdb.DFS(this.adj, 0, 0);
        Arrays.sort(cdb.vertices, Collections.reverseOrder());
        cdb.vertices[0].setClassificada(true);
        cdb.vertices[1].setClassificada(true);
        cdb.resultadosDaBusca(0);
    }
    
    public void calcularClassificadosDeTodosGrupos(){
        
        CDBuscaProfundidade cdb = new CDBuscaProfundidade(this.adj, this.selecoes);
        cdb.DFS(this.adj, 0, 0);
        
        //Arrays.sort(cdb.vertices, 0, 4, Collections.reverseOrder());
        //cdb.vertices[0].setClassificada(true);
        //cdb.vertices[1].setClassificada(true);
        
        int inicio = 0, fim = 4;
        while(fim <= 32){
            Arrays.sort(cdb.vertices, inicio, fim, Collections.reverseOrder());
            cdb.vertices[inicio].setClassificada(true);
            cdb.vertices[inicio + 1].setClassificada(true);
            inicio = fim;
            fim += 4;
        }
        
        cdb.resultadosDaBusca(0);
    }
    
    public void calcularCampeao(){
        
        CDBuscaProfundidade cdb = new CDBuscaProfundidade(this.adj, this.selecoes);
        cdb.DFS(this.adj, 0, 2);
        
        int finalista[] = new int[2];
        int cont = 0;
        for(int i = 0; i < adj.getNumeroVertices(); i++){
            
            if(cdb.vertices[i].getFaseDaCompeticao().equalsIgnoreCase("Final")) {
                finalista[cont] = i;
                cont++;
                if(cont == 2) break;
            }
        }
        
        cont = adj.getPeso(finalista[0], finalista[1]);
        if(cont == 1){
            cdb.vertices[finalista[0]].setPosicao("Campeão");
            cdb.vertices[finalista[1]].setPosicao("Vice-Campeão");
        }
        else {
            cdb.vertices[finalista[1]].setPosicao("Campeão");
            cdb.vertices[finalista[0]].setPosicao("Vice-Campeão");
        }
        
        cdb.resultadosDaBusca(2);
    }
    
    public VerticeSelecao[] calcularSelecaoMaiorMediaPublicoFaseGrupos(){
        
        CDBuscaProfundidade cdb = new CDBuscaProfundidade(this.adj, this.selecoes);
        cdb.DFS(this.adj, 0, 1);
        
        for(int i = 0; i < cdb.vertices.length; i++){
            
            cdb.vertices[i].setMediaDePublico(cdb.vertices[i].getPublicoTotal()/3.0);
        }
        
        cdb.vertices = CDOrdenacao.quickSort(cdb.vertices, 0, 31);
        
        cdb.resultadosDaBusca(3);
        
        return cdb.vertices;
    }
    
    public void calcularMediaPublicoDeUmGrupo(){
        
        CDBuscaProfundidade cdb = new CDBuscaProfundidade(this.adj, this.selecoes);
        cdb.DFS(this.adj, 0, 1);
        
        for(int i = 0; i < cdb.vertices.length; i++){
            
            cdb.vertices[i].setMediaDePublico(cdb.vertices[i].getPublicoTotal()/3.0);
        }
        
        CDOrdenacao.quickSort(cdb.vertices, 0, 3);
        
        cdb.resultadosDaBusca(1);
    }
    
}
