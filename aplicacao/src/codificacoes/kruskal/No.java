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
public class No {
    
    private int posicao;
    private No prox;
    
    public No(){
        
    }

    public No(int posicao, No prox) {
        this.posicao = posicao;
        this.prox = prox;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }
    
}
