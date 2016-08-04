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
public class Vertice {

    public int posicao;
    public int chave;
    public Vertice PI;

    public Vertice() {

    }

    public Vertice(int posicao, int chave, Vertice PI) {

        this.posicao = posicao;
        this.chave = chave;
        this.PI = PI;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public Vertice getPI() {
        return PI;
    }

    public void setPI(Vertice PI) {
        this.PI = PI;
    }

    public String mostrarDados() {

        if (this.PI == null) {
            if (this.getChave() == Integer.MAX_VALUE) {
                return "| " + this.getPosicao() + " | ∞ | null | \n";
            }
            return "| " + this.getPosicao() + " | " + this.getChave() + " | null | \n";
        } else {
            if (this.getChave() == Integer.MAX_VALUE) {
                return "| " + this.getPosicao() + " | ∞ | " + this.PI.getPosicao() + " | \n";
            }
            return "| " + this.getPosicao() + " | " + this.getChave() + " | " + this.PI.getPosicao() + " | \n";
        }
    }
}
