/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.prim;

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
            
            No atual, anterior;
            atual = anterior = this.primeiro;
            
            while(atual != null && atual.vertice.chave < novo.vertice.chave){
                anterior = atual;
                atual = atual.prox;
            }
            
            if(atual == primeiro){ //Primeiro
                
                novo.prox = this.primeiro;
                this.primeiro = novo;
            }
                
            else if(atual == null){ //Ultimo
                
                anterior.prox = novo;
                this.ultimo = novo;
            }
            
            else{ //No meio
                
                anterior.prox = novo;
                novo.prox = atual;
            }
            
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
    
    
    public boolean estaNaFila(int valor){
        
        No aux = this.primeiro;
        while(aux != null){
            
            if(aux.vertice.posicao == valor) return(true);
            
            aux = aux.prox;
        }
        
        return(false);
    }

    public void atualizaLista(Vertice v){
        
        No atual, anterior;
        atual = anterior = this.primeiro;
        
        while(atual != null && atual.vertice.posicao != v.getPosicao()){
            anterior = atual;
            atual = atual.prox;
        }
        
        if(atual == this.primeiro){
            
            this.primeiro = this.primeiro.prox;
        }
        else if(atual.prox == null){
            
            this.ultimo = anterior;
            this.ultimo.prox = null;
        }
        else{
        
            anterior.prox = atual.prox;
        }
        this.tamanhoFila--;
        
        this.inserirNo(v);
        
    }
}
