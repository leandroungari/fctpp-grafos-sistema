/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.coloracao;

import desenho.RainbowScale;
import javafx.scene.text.Text;

/**
 *
 * @author BRUNO
 */
public class Vertice {
    
    private int posicao;
    private boolean visitado;
    private int color;
    
    public Vertice(){
    
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getCor() {
        return this.color;
    }

    public void setCor(int color) {
        this.color = color;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }
    
    public void mostarVertice(){
        
        System.out.print(this.getPosicao() + " | ");
        System.out.println(this.color + " | ");
        
    }
}
