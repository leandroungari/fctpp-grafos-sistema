/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.bellmanFord;

import codificacoes.representacaoComputacional.Adjacencia;
import java.util.ArrayList;

/**
 *
 * @author BRUNO
 */
public class BellmanFord {
    
    public Vertice resultados[];
    public ArrayList<Aresta> arestas;
    
    public BellmanFord(Adjacencia adj){
        
        this.resultados = new Vertice[adj.getNumeroVertices()];
        this.arestas = new ArrayList<Aresta>();
        
        for(int i = 0; i < this.resultados.length; i++){
            
            this.resultados[i] = new Vertice();
            this.resultados[i].setPosicao(i); //Nome do vertice
        }
        
        this.gerarArestas(adj);
    }
    
    public void inicializa(Vertice verticeInicial){
        
        for(int i = 0; i < this.resultados.length; i++){
            
            this.resultados[i].setDistancia(Integer.MAX_VALUE);
            this.resultados[i].setPI(null);
        }
        
        this.resultados[verticeInicial.getPosicao()].setDistancia(0);
    }
    
    public void relaxa(Vertice u, Vertice v, Adjacencia adj){
        
        int distancia; 
        
        /*if(u.getDistancia() == Integer.MAX_VALUE){
            distancia = adj.getPeso(u.getPosicao(), this.resultados[v.getPosicao()].getPosicao());
        }
        else distancia = u.getDistancia() + adj.getPeso(u.getPosicao(), this.resultados[v.getPosicao()].getPosicao());
        */
        if(u.getDistancia() != Integer.MAX_VALUE){
            
            distancia = u.getDistancia() + adj.getPeso(u.getPosicao(), v.getPosicao()/*this.resultados[v.getPosicao()].getPosicao()*/);
            
            if(this.resultados[v.getPosicao()].getDistancia() > (distancia) ){
            
                this.resultados[v.getPosicao()].setDistancia(distancia);
                this.resultados[v.getPosicao()].setPI(u);
            }
        }
        
        
    }
    
    public boolean algoritmoBellmanFord(Adjacencia adj, Vertice verticeInicial){
        
        this.inicializa(verticeInicial);
        
        for(int i = 1; i < this.resultados.length-1; i++){
            
            for(Aresta a : this.arestas){
                
                this.relaxa(this.resultados[a.getVerticeInicio()], this.resultados[a.getVerticeFim()], adj);
            }//Fim for interno
        }//Fim for externo
        
        for(Aresta a : this.arestas){
            
            if(this.resultados[a.getVerticeInicio()].getDistancia() != Integer.MAX_VALUE){
                
                if(this.resultados[a.getVerticeFim()].getDistancia() > this.resultados[a.getVerticeInicio()].getDistancia() + adj.getPeso(a.getVerticeInicio(), a.getVerticeFim())){
                    return(false);
                }  
            }
            
        }
        
        return(true);
    }
    
    public void gerarArestas(Adjacencia adj){
        
        int j;
        for(int i = 0; i < adj.getNumeroVertices(); i++){
            
            adj.inicializaIterator(i);
            while(adj.hasNext(i)){
                
                j = adj.next(i);
                Aresta a = new Aresta(i,j,adj.getPeso(i, j));
                
                this.arestas.add(a);
            }
        }
        
        //this.mostrarArestas();
        
    }
    
    public void mostrarArestas(){
        
        System.out.println("\nArestas:");
        
        for(Aresta a : this.arestas){
            
            a.mostrarAresta();
        }
    }
    
    public void mostrarResultados(){
        
        System.out.println("\n\nResultados:\n");
        
        System.out.print("V ");
        for(int i = 0; i < this.resultados.length; i++){
            
            System.out.print(" | " + this.resultados[i].getPosicao());
        }
        
        System.out.print("\nD ");
        for(int i = 0; i < this.resultados.length; i++){
            
            System.out.print(" | " + this.resultados[i].getDistancia());
        }
        
        System.out.print("\nPI");
        for(int i = 0; i < this.resultados.length; i++){
            
            if(this.resultados[i].getPI() != null) 
                System.out.print(" | " + this.resultados[i].getPI().getPosicao());
            else System.out.print(" | null");
        }
        
        System.out.println();
        
    }
    
    public String printCaminho(Vertice inicio, Vertice fim){
        
        String texto = "";
        if(inicio.getPosicao() == fim.getPosicao()) return(inicio.getPosicao() + "-> ");
        else if(this.resultados[fim.getPosicao()].getPI() == null) texto += "Não existe caminho";
        else{
            texto = printCaminho(inicio, this.resultados[fim.getPosicao()].getPI());
            texto += fim.getPosicao() + "-> ";
        }
        
        return(texto);
    }
}
