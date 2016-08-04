/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.kruskal;

import codificacoes.representacaoComputacional.Adjacencia;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author BRUNO
 */
public class Kruskal {
    
    //public int conjuntos[][];
    public Lista conjuntos[];
    public ArrayList<Aresta> arestas;
    public ArrayList<Aresta> resultados; 
    
    public Kruskal(Adjacencia adj){
        
        /*
        this.conjuntos = new int[adj.getNumeroVertices()][adj.getNumeroVertices()];
        for(int i = 0; i < this.conjuntos.length; i++){
            this.conjuntos[i][i] = 1;
        }
        */
        this.conjuntos = new Lista[adj.getNumeroVertices()];
        
        this.arestas = new ArrayList<Aresta>();
        this.gerarArestas(adj);
        
        this.resultados = new ArrayList<Aresta>();
    }
    
    public void algoritmoKruskal(){
        
        int i;
        this.inicializaConjuntos();
        this.ordenarArestas();
        
        for(Aresta a : this.arestas){
            
            if(!this.estaoNoMesmoConjunto(a.getVerticeInicio(), a.getVerticeFim())){
                
                this.resultados.add(a);
                this.aplicarUniao(a.getVerticeInicio(), a.getVerticeFim());
            }
        }//Fim do for
        
    }//Fim do método
    
    
    public void gerarArestas(Adjacencia adj){
        
        int j;
        for(int i = 0; i < adj.getNumeroVertices(); i++){
            
            adj.inicializaIterator(i);
            while(adj.hasNext(i)){
                
                j = adj.next(i);
                Aresta a = new Aresta(i,j,adj.getPeso(i, j));
                
                this.arestas.add(a);
            }//Fim do while
            
        }//Fim do for
        
    }//Fim do método
    
    public void ordenarArestas(){
        
        Collections.sort(this.arestas);
    }
    
    public void mostrarArestas(){
        
        for(Aresta a : this.arestas){
            
            a.mostrarAresta();
        }
    }
    
    public void inicializaConjuntos(){
        
        for(int i = 0; i < this.conjuntos.length; i++){
            
            this.conjuntos[i] = new Lista();
            this.conjuntos[i].inserirNo(i, null);
        }
    }
    
    public void mostrarConjuntos(){
        
        for(int i = 0; i < this.conjuntos.length; i++){
            
            No aux = this.conjuntos[i].primeiro;
            if(aux == null) System.out.println("null");
            else{
                
                while(aux != null){
                
                    System.out.print(aux.getPosicao() + "-> ");
                    aux = aux.getProx();
                }
                System.out.println();
            }
        
        }
    }
    
    public boolean estaoNoMesmoConjunto(int i, int j){
    
        No aux;
        for(int cont = 0; cont < this.conjuntos.length; cont++){
            
            aux = this.conjuntos[cont].primeiro;
            while(aux != null){
                if(aux.getPosicao() == i) break;
                aux = aux.getProx();
            }
            if(aux != null && aux.getPosicao() == i){
                
                aux = this.conjuntos[cont].primeiro;
                while(aux != null){
                    if(aux.getPosicao() == j) break;
                    aux = aux.getProx();
                }
                if(aux != null && aux.getPosicao() == j) return(true);
            }
        }
        return(false);
    }
    
    
    public void aplicarUniao(int i, int j){
        
        No auxi, auxj;
        int conti = 0, contj = 0;
        for(conti = 0; conti < this.conjuntos.length; conti++){
            
            auxi = this.conjuntos[conti].primeiro;
            while(auxi != null){
                
                if(auxi.getPosicao() == i) break;
                auxi = auxi.getProx();
            }
            if(auxi != null && auxi.getPosicao() == i) break;
        }
        
        for(contj = 0; contj < this.conjuntos.length; contj++){
            
            auxj = this.conjuntos[contj].primeiro;
            while(auxj != null){
                
                if(auxj.getPosicao() == j) break;
                auxj = auxj.getProx();
            }
            if(auxj != null && auxj.getPosicao() == j) break;
        }
       
        
        this.conjuntos[conti].ultimo.setProx(this.conjuntos[contj].primeiro);
        this.conjuntos[conti].ultimo = this.conjuntos[contj].ultimo;
        this.conjuntos[contj].primeiro = null;
        
    }
    
    public String mostrarResultados(){
        
        String s = "";
        int custo = 0;
        for(Aresta a : this.resultados){
            
            s += a.mostrarAresta();
            custo += a.getPeso();
        }
        
        s += "\nAGM Custo: " + custo;
        
        return s;
    }
}
