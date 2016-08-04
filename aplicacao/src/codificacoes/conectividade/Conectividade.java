/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.conectividade;

import codificacoes.buscaProfundidade.BuscaProfundidade;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.transposicao.Transposicao;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author BRUNO
 */
public class Conectividade {
    
    public Vertice [] vertices;
    public int [] ordemVertices;
    public Adjacencia adjTransposta;
    public int tempo;
    
    public Conectividade(Adjacencia adjOriginal){
        
        this.inicializaVertices(adjOriginal);
        this.inicializaOrdemVertices();
    }
    
    public void inicializaVertices(Adjacencia adjOriginal){
        
        this.vertices = null;
        this.vertices = new Vertice[adjOriginal.getNumeroVertices()];
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i] = new Vertice();
            this.vertices[i].setPosicao(i);
            this.vertices[i].setConjuntoConexo(-1);
            this.vertices[i].setVisitado(false);
            this.vertices[i].setPredecessor(null);
        }
    }
    
    public void inicializaOrdemVertices(){

        this.ordemVertices = new int[this.vertices.length];
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.ordemVertices[i] = this.vertices[i].getPosicao();
        }
    }
    
    public void algoritmoConectividade(Adjacencia adjOriginal){
        
        //Passo 1
        this.DFS(adjOriginal, this.vertices[this.ordemVertices[0]].getPosicao());
        this.ordemVertices = this.retornaVerticesOrdenadosDescrecenteTempoFim();
        
        //Passo 2
        this.adjTransposta = this.aplicarTransposicao(adjOriginal);
        
        //Passo 3
        this.inicializaVertices(adjTransposta);
        this.DFS(adjTransposta, this.vertices[this.ordemVertices[0]].getPosicao());
        
    }
    
    //Função para transposição
    public Adjacencia aplicarTransposicao(Adjacencia adjOriginal){
        
        return(Transposicao.RealizarTransposicao(adjOriginal));
    }
    
    //Funções para busca em profundidade
    public int DFS(Adjacencia adj, int verticeInicial){
        
        //Inicializando
        int i, componenteConexa = 0;
        
        for(i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].setCorVisitaBusca("BRANCO");
        }
        
        this.tempo = 0;
        
        //Precorrendo e verificando
        this.DFSVisita(this.vertices[verticeInicial], adj, ++componenteConexa);
        for(i = 0; i < this.vertices.length; i++){
            
            if(this.vertices[this.ordemVertices[i]].getCorVisitaBusca().equalsIgnoreCase("BRANCO")){
                this.DFSVisita(this.vertices[this.ordemVertices[i]], adj, ++componenteConexa);
            }
        }
        
        return(componenteConexa);
    }
    
    public void DFSVisita(Vertice v, Adjacencia adj, int componenteConexa){
        
        int i;
        
        v.setCorVisitaBusca("CINZA");
        this.tempo++;
        v.setTempoInicio(this.tempo);
        v.setConjuntoConexo(componenteConexa);
        
        adj.inicializaIterator(v.getPosicao());
        while(adj.hasNext(v.getPosicao())){
            
            i = adj.next(v.getPosicao());
            if(this.vertices[i].getCorVisitaBusca().equalsIgnoreCase("BRANCO")){

                this.vertices[i].setPredecessor(v);
                this.DFSVisita(this.vertices[i], adj, componenteConexa);
            }
        }//Fim while
        
        v.setCorVisitaBusca("PRETO");
        this.tempo++;
        v.setTempoFim(this.tempo);
        
    }
    
    public int[] retornaVerticesOrdenadosDescrecenteTempoFim(){
        
        Vertice [] v = this.vertices;
        Arrays.sort(v, Collections.reverseOrder());
        
        int matriz [] = new int[v.length];
        
        for(int i = 0; i < v.length; i++){
                            
            matriz[i] = v[i].getPosicao();
            
        }
        
        return(matriz);
    }
    
    public void mostrarOrdem(){
        
        System.out.println();
        for(int i = 0; i < this.ordemVertices.length; i++){
                
            System.out.print(this.ordemVertices[i] + " - ");
        }
        
        System.out.println();
        
    }
    
    public void mostrarVertices(){
        
        System.out.println();
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].exibir();
        }
        System.out.println();
    }

}
