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
public class No {
    
    public Vertice vertice;
    public No prox;
    
    public No(Vertice vertice){
        
        this.vertice = vertice;
        this.prox = null;
    }
}
