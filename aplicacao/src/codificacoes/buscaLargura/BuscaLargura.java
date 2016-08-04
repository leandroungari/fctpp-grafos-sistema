/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.buscaLargura;

import codificacoes.representacaoComputacional.Adjacencia;
import java.util.Queue;

/**
 *
 * @author BRUNO
 */
public class BuscaLargura {
 
    public Vertice vertices[];
    
    public BuscaLargura(Adjacencia adj){
        
        this.vertices = new Vertice[adj.getNumeroVertices()];
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i] = new Vertice();
            this.vertices[i].setPosicao(i);
        }
        
    }
    
    public void BFS(Adjacencia adj, Vertice verticeInicial){
        
        int i;
        for(i = 0; i < this.vertices.length; i++){
            
            if(this.vertices[i].getPosicao() != verticeInicial.getPosicao()){
                
                this.vertices[i].setCor("BRANCA");
                this.vertices[i].setDistancia(0);
                this.vertices[i].setPredecessor(null);
            }
        }
        
        verticeInicial.setCor("CINZA");
        verticeInicial.setDistancia(0);
        verticeInicial.setPredecessor(null);
        
        //Queue<Vertice> fila = new LinkedList();
        Fila fila = new Fila();
        
        this.enfilera(fila, verticeInicial);
        
        //!fila.isEmpty()
        while(!fila.estaVazia()){
            
            Vertice aux = this.desenfilera(fila);
            
            adj.inicializaIterator(aux.getPosicao());
            while(adj.hasNext(aux.getPosicao())){
                
                i = adj.next(aux.getPosicao());
                if(this.vertices[i].getCor().equalsIgnoreCase("BRANCA")){
                    
                    this.vertices[i].setCor("CINZA");
                    this.vertices[i].setDistancia(aux.getDistancia() + 1);
                    this.vertices[i].setPredecessor(aux);
                    
                    this.enfilera(fila, this.vertices[i]);
                }
                
            }
            
            this.vertices[aux.getPosicao()].setCor("PRETO");
            
        }
        
    }
    
    //public void enfilera(Queue<Vertice> fila, Vertice s){
    public void enfilera(Fila fila, Vertice verticeInicial){
        
        //fila.add(s);
        fila.inserirNo(verticeInicial);
    }
    
    //public Vertice desenfilera(Queue<Vertice> fila){
    public Vertice desenfilera(Fila fila){  
        
        //return(fila.poll());
        return(fila.remover());
    }
    
    
    public void mostrarResultados(){
        
        System.out.println("\nResultados\n");
        
        int i;
        
        System.out.println("Cor   |DIS|fIL|");
        for(i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].mostarVertice();
        }
    }
    
    public void mostrarResultados2(){
        
        System.out.println();
        
        for(int i = 0; i < this.vertices.length; i++){
            
            System.out.print(" | " + this.vertices[i].getPosicao());
        }
        System.out.println();
        
        for(int i = 0; i < this.vertices.length; i++){
            
            if(this.vertices[i].getPredecessor() != null)
                System.out.print(" | " + this.vertices[i].getPredecessor().getPosicao());
            else System.out.print(" |-1");
        }
        System.out.println();
        
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
    
    
}
