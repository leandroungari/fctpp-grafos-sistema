/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.buscaProfundidade;

/**
 *
 * @author BRUNO
 */
public class Pilha {
    
    public No primeiro;
    public No ultimo;
    public int tamanhoPilha;
    
    public Pilha(){
        
        this.primeiro = null;
        this.ultimo = null;
        this.tamanhoPilha = 0;
        
    }
    
    public int getTamanhoPilha(){
        
        return(this.tamanhoPilha);
    }
    
    public boolean estaVazia(){
        
        if(this.getTamanhoPilha() == 0) return(true);
        return(false);
    }
    
    public void push(Vertice vertice){
        
        No novo = new No(vertice);
        
        if(this.estaVazia() == true){
            
            this.primeiro = this.ultimo = novo;
        }
        else{
               
            ultimo.prox = novo;
            this.ultimo = novo;
        }

        this.tamanhoPilha++;
    }
    
    
    public Vertice pop(){
        
        if(this.getTamanhoPilha() != 0){
            
            No anterior, atual;
            anterior = atual = this.primeiro;

            while(atual.vertice.getPosicao() != this.ultimo.vertice.getPosicao()){
                anterior = atual;
                atual = atual.prox;
            }
            
            No retorno = this.ultimo;
            this.ultimo = anterior;
            this.ultimo.prox = null;
            this.tamanhoPilha--;
            return(retorno.vertice);
        }
        else return(null);
        
    }
    
    public void exibir(){
        
        No temp = this.primeiro;
        String valores = "";
        int cont = 1;
        
        if(this.estaVazia() == false){
            
            while(cont <= this.getTamanhoPilha()){
                valores += " " + Integer.toString(temp.vertice.getPosicao()) + " | ";
                temp = temp.prox;
                cont++;
            }
            
            System.out.print(valores);
        }
   
    }
    
}
