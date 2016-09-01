/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.coloracao;

import codificacoes.representacaoComputacional.Adjacencia;
import desenho.RainbowScale;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author BRUNO
 */
public class Coloracao {
    
    public Vertice vertices[];
    public int tempo;
    public ArrayList<Integer> colors;   
    
    public Coloracao(Adjacencia adj){
        
        this.vertices = new Vertice[adj.getNumeroVertices()];
        this.colors = new ArrayList();
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i] = new Vertice();
            this.vertices[i].setPosicao(i);
            this.vertices[i].setCor(-1);
            this.vertices[i].setVisitado(false);
        }
    }

    
    public void AlgoritmoColoracao(Adjacencia adj){
        
        //Obs a inilicialização das estruturas inclusive da estrutura de cores é realizada no construtor da clase.
        
        //O vertice inicial é o vertice com o maior grau do grafo.
        int verticeInicial = this.retornaVerticeDeMaiorGrau(adj);
        System.out.println("Vertice maior grau: " + verticeInicial);
        this.coloreVertice(adj, verticeInicial);
        
    }
    
    public void coloreVertice(Adjacencia adj, int verticeInicial){
        
        int vizinho;
        this.vertices[verticeInicial].setVisitado(true);
        
        if(this.vertices[verticeInicial].getCor() == -1){
            
            /* 
             * Busca uma já presente na estrutura de cores, se não for apropriada cria uma nova cor adiciona
             * na estrutura de cores e depois adionar esta cor no vertice em questão 
             */
            this.vertices[verticeInicial].setCor(this.buscarCorApropriada(adj, verticeInicial));
        }
        
        adj.inicializaIterator(verticeInicial);
        while(adj.hasNext(verticeInicial)){

            vizinho = adj.next(verticeInicial);
            if(!this.vertices[vizinho].isVisitado()) this.coloreVertice(adj, vizinho);
            
        }//Fim while
        
    }
    
    public int retornaVerticeDeMaiorGrau(Adjacencia adj){
        
        int pos, numVertices, somaAtual, somaVerticeInicial, verticeInicial;
        boolean verif;
        
        pos = somaAtual = somaVerticeInicial = verticeInicial = 0;
        numVertices = adj.getNumeroVertices();
        verif = true;
        
        adj.inicializaIterator(pos);
        while(pos < numVertices){
            
            while(verif = adj.hasNext(pos)){
                
                adj.next(pos);
                somaAtual++;
            }
            
            if(somaAtual > somaVerticeInicial){
                
                verticeInicial = pos;
                somaVerticeInicial = somaAtual;
            }
            
            pos++;
            if(!verif && pos < numVertices){
                adj.inicializaIterator(pos);
                somaAtual = 0;
            }
        }
        
        return(verticeInicial);
        
    }
    
    public int buscarCorApropriada(Adjacencia adj, int verticeInicial){
        System.out.println(verticeInicial);
        int i, cont, salto, cor = -1;
        boolean verif = true;
        
        salto = 255 / adj.getNumeroVertices();
        
        //Se não estiver vazia
        //cont = this.colors.size();
        cont = 0;
        //adj.inicializaIterator(verticeInicial);
        for(int tc : this.colors){
            
            cor = tc;
            //this.vertices[verticeInicial].setCor(tc);
            adj.inicializaIterator(verticeInicial);
            while(verif = adj.hasNext(verticeInicial)){
                
                i = adj.next(verticeInicial);
                
                if(this.vertices[i].getCor() != -1){
                if(this.vertices[i].getCor() == cor) break;
                
                }
            }//Fim do while
            cont++;
            if(verticeInicial == 3) System.out.println("cont = " + cont + "size = " + this.colors.size());
            if(!verif && cont < this.colors.size()) return(tc); //CorBuscada que já está presente na estrutura de cores.
            
        }//Fim do for
        
        //Se ele sair do for então nenhuma das cores da estrutura é apropriada, logo devemos criar outra cor.
        if(cont == this.colors.size()) {
            this.colors.add(cont*salto);
            return(cont*salto);
        }
        return(cor);
        
    }
    
    public void exibirVertices(){
        
        System.out.println("\n\nVertices:\n");
        
        for(int i = 0; i < this.vertices.length; i++){
            
            this.vertices[i].mostarVertice();
        }
    }

}
