/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.representacaoComputacional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author BRUNO
 */
public class ListaAdjacenciaMultipla extends RepresentacaoComputacionalMultipla {
    
    public Lista [] lista;
    //public No posicaoAtual;
    
    public ListaAdjacenciaMultipla(int numVertices){
        
        super(numVertices);
        this.lista = new Lista[numVertices];
        
        this.inicializaListas();
    }
    
    public void inicializaListas(){
        
        for(int i = 0; i < this.lista.length; i++){
            this.lista[i] = new Lista();
        }
    }
    
    
    @Override
    public void adicionaAresta(int i, int j, int peso){
        
          lista[i].inserirNo(j, peso);
    }
      
    @Override
    public boolean verificarAdjacencia(int i, int j){
         
        return lista[i].contem(j);
    }
    
    
    @Override
    public int[] getPeso(int i, int j) {
        
        int quantidade = 0;
        int[] resultado = null;
        No primeiro = null;
        No aux = this.lista[i].primeiro;
        while(aux != null){
            
            if(aux.getValor() == j) {
                
                primeiro = aux;
                
                do{
                    quantidade++;
                    aux = aux.getProx();
                }while(aux != null && aux.getValor() == j);
                
                aux = primeiro;
                resultado = new int[quantidade];
                int cont = 0;
                do{
                    resultado[cont++] = aux.getPeso();
                    aux = aux.getProx();
                }while(aux!= null && aux.getValor() == j);
                
                return resultado;
            }
            
            aux = aux.getProx();
            
        }
        
        return resultado;
    }
  
    @Override
    public void exibir(){
         
        for(int i = 0; i < this.lista.length ;i++){
            
            System.out.print("| " + i + " |-->");
            lista[i].exibir();
            System.out.println();
        }
    }

   
    public boolean hasNext(int pos) { //OK

        if(this.lista[pos].posicaoAtual != null) return(true);
        return(false);
    }

    
    public int next(int pos) { 
        
        No aux = this.lista[pos].posicaoAtual;
        this.lista[pos].posicaoAtual = this.lista[pos].posicaoAtual.getProx();
        return(aux.getValor());
    }
    
    public void inicializaIterator(int pos){
        
        this.lista[pos].posicaoAtual = this.lista[pos].primeiro;
    }

}
