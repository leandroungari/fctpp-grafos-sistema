/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.prim;

import codificacoes.representacaoComputacional.Adjacencia;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author BRUNO
 */
public class Prim {
    
    public Vertice resultados[];
    
    public Prim(Adjacencia adj){
        
        this.resultados = new Vertice[adj.getNumeroVertices()];
        
        for(int i = 0; i < this.resultados.length; i++){
            
            this.resultados[i] = new Vertice();
        }
    }
    
    public void AGM_Prim(int r, Adjacencia adj){
        
        int i;
        for(i = 0; i < this.resultados.length; i++){
            
            resultados[i].setPosicao(i);
            resultados[i].setChave(Integer.MAX_VALUE);
            resultados[i].setPI(null);
        }
        
        resultados[r].setChave(0);
        
        Fila fila = new Fila();
        
        for(i = 0; i < this.resultados.length; i++){
            
            fila.inserirNo(this.resultados[i]);
        }
        
        Vertice aux = null;
        while(!fila.estaVazia()){
            
            aux = fila.remover();
            
            adj.inicializaIterator(aux.getPosicao());
            while(adj.hasNext(aux.getPosicao())){
                
                i = adj.next(aux.getPosicao());
                
                if( (fila.estaNaFila(i)) && (adj.getPeso(aux.getPosicao(), i) < this.resultados[i].getChave()) ){
                    
                    resultados[i].setChave(adj.getPeso(aux.getPosicao(), i));
                    resultados[i].PI = aux; 
                    fila.atualizaLista(resultados[i]);
                }
            }//Fim do while interno
            
        }//Fim do while externo
        
    }//Fim do metodo
    
    public String mostrarResultado(){
   
        String s = "\nRESULTADOS\n\n| V | key | PI |\n";
        int soma = 0;
        
        for(int i = 0; i < this.resultados.length; i++){
            
            s += this.resultados[i].mostrarDados();
            soma += this.resultados[i].getChave();
            
        }
        
        return s + "\nAMG Custo: " + soma;
    }
}
