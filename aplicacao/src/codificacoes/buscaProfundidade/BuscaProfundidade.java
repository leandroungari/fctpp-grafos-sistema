    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.buscaProfundidade;

import codificacoes.representacaoComputacional.Adjacencia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author BRUNO
 */
public class BuscaProfundidade {
    
    public Vertice vertices[];
    public int tempo;
    public Pilha pilhaTopologica;
    
    public BuscaProfundidade(Adjacencia adj){
        
        this.vertices = new Vertice[adj.getNumeroVertices()];
        this.pilhaTopologica = new Pilha();
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i] = new Vertice();
            this.vertices[i].setPosicao(i);
        }
    }

    
    public int DFS(Adjacencia adj, int verticeInicial){
        
        //Inicializando
        int i, componenteConexa = 0;
        
        for(i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].setCor("BRANCO");
        }
        
        this.tempo = 0;
        
        //Precorrendo e verificando
        this.DFSVisita(this.vertices[verticeInicial], adj, ++componenteConexa);
        for(i = 0; i < this.vertices.length; i++){
            
            if(this.vertices[i].getCor().equalsIgnoreCase("BRANCO")){
                this.DFSVisita(this.vertices[i], adj, ++componenteConexa);
            }
        }
        
        return(componenteConexa);
    }
    
    public void DFSVisita(Vertice v, Adjacencia adj, int componenteConexa){
        
        int i;
        
        v.setCor("CINZA");
        this.tempo++;
        v.setTempoInicio(this.tempo);
        v.setComponenteConexa(componenteConexa);
        
        adj.inicializaIterator(v.getPosicao());
        while(adj.hasNext(v.getPosicao())){
            
            i = adj.next(v.getPosicao());
            if(this.vertices[i].getCor().equalsIgnoreCase("BRANCO")){

                this.vertices[i].setPredecessor(v);
                this.DFSVisita(this.vertices[i], adj, componenteConexa);
            }
        }//Fim while
        
        v.setCor("PRETO");
        this.tempo++;
        v.setTempoFim(this.tempo);
        this.pilhaTopologica.push(v);
        
    }
    
    public void resultadosDaBusca(){
        
        System.out.println("\n\nResultados da Busca\n");
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].mostarVertice();
        }
    }
    
    
    public String caminho(int u, int v){
        
        int i = v;
        String s = "";
        
        s = vertices[v].getPosicao() + "";
        
        while(vertices[i].getPredecessor() != null && vertices[i].getPredecessor().getPosicao() != u){
            
            i = vertices[i].getPredecessor().getPosicao();
            s = i + " -> " + s;
        }
        
        if(vertices[i].getPredecessor() == null) return "Não existe caminho";
        else return u + " -> " + s;
    }
    
    public ArrayList<Vertice> getOrdemTopologica(){
        
        ArrayList<Vertice> ordemTopologica = new ArrayList();
        
        while(!this.pilhaTopologica.estaVazia()){
            ordemTopologica.add(this.pilhaTopologica.pop());
        }
        
        return(ordemTopologica);
    }
    
}