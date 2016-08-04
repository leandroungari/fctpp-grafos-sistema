/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.kruskal;

/**
 *
 * @author BRUNO
 */
public class Lista {

    public No primeiro;
    public No ultimo;
    public int tamanhoLista;
    
    public Lista(){
        
        this.primeiro = null;
        this.ultimo = null;
        this.tamanhoLista = 0;
    }
    
    public int getTamanhoLista(){
        
        return(this.tamanhoLista);
    }
    
    public boolean estaVazia(){
        
        if(this.getTamanhoLista() == 0) return(true);
        return(false);
    }
    
    public void inserirNo(int posicao, No prox){
        
        No no = new No(posicao, null);
        
        if(this.estaVazia() == true){
            
            this.primeiro = this.ultimo = no;
        }
        else{
            
            this.ultimo.setProx(no);
            this.ultimo = no;
        }
        
        this.tamanhoLista++;
    }
    
    public void exibir(){
        
        No temp = this.primeiro;
        String valores = "";
        int cont = 1;
        
        if(this.estaVazia() == false){
            
            while(cont <= this.getTamanhoLista()){
                
                valores += " " + Integer.toString(temp.getPosicao()) + " |";
                temp = temp.getProx();
                cont++;
            }
            
            System.out.print(valores);
        }
        
    }
    
}
