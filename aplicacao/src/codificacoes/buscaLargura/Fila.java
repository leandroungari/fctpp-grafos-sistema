/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.buscaLargura;

/**
 *
 * @author BRUNO
 */
public class Fila {
    
    public No primeiro;
    public No ultimo;
    public int tamanhoFila;
    
    public Fila(){
        
        this.primeiro = null;
        this.ultimo = null;
        this.tamanhoFila = 0;
        
    }
    
    public int getTamanhoFila(){
        
        return(this.tamanhoFila);
    }
    
    public boolean estaVazia(){
        
        if(this.getTamanhoFila() == 0) return(true);
        return(false);
    }
    
    public void inserirNo(Vertice vertice){
        
        No novo = new No(vertice);
        
        if(this.estaVazia() == true){
            
            this.primeiro = this.ultimo = novo;
        }
        else{
            
            this.ultimo.prox = novo;
            this.ultimo = novo;
        }
        
        this.tamanhoFila++;
    }
    
    
    public Vertice remover(){
        
        if(this.getTamanhoFila() != 0){
            
            Vertice vertice = this.primeiro.vertice;
            this.primeiro = primeiro.prox;
            this.tamanhoFila--;
            return(vertice);
        }
        else return(null);
    }
    
    
    public void exibir(){
        
        No temp = this.primeiro;
        String valores = "";
        int cont = 1;
        
        if(this.estaVazia() == false){
            
            while(cont <= this.getTamanhoFila()){
                valores += " " + Integer.toString(temp.vertice.getPosicao()) + " | ";
                temp = temp.prox;
                cont++;
            }
            
            System.out.print(valores);
        }
        
    }
}
