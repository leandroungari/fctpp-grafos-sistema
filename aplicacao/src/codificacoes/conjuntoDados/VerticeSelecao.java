/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.conjuntoDados;

/**
 *
 * @author BRUNO
 */
public class VerticeSelecao implements Comparable<VerticeSelecao> {
    
    private int numero;
    private String nome;
    private int saldoDeGols;
    private int pontos;
    private boolean classificada;
    private double publicoTotal;
    private double mediaDePublico;
    private String cor;
    private String faseDaCompeticao;
    private String posicao;
    private int numeroDeJogos;
    
    /**
     * Está classe representaria uma seleção
     */
    
    public VerticeSelecao(){
        
        this.nome = "n/a";
        this.pontos = 0;
        this.saldoDeGols = 0;
        this.classificada = false;
        this.publicoTotal = 0.0;
        this.mediaDePublico = 0.0;
        this.faseDaCompeticao = "Fase de grupos";
        this.posicao = "Eliminado";
        this.numeroDeJogos = 0;
    }
    
    public VerticeSelecao(int i){
        
        this.numero = i;
        this.nome = "n/a";
        this.pontos = 0;
        this.saldoDeGols = 0;
        this.classificada = false;
        this.publicoTotal = 0.0;
        this.mediaDePublico = 0.0;
        this.faseDaCompeticao = "Fase de grupos";
        this.posicao = "Eliminado";
        this.numeroDeJogos = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSaldoDeGols() {
        return saldoDeGols;
    }

    public void setSaldoDeGols(int saldoDeGols) {
        this.saldoDeGols += saldoDeGols;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos += pontos;
    }

    public boolean isClassificada() {
        return classificada;
    }

    public void setClassificada(boolean classificada) {
        this.classificada = classificada;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPublicoTotal() {
        return publicoTotal;
    }

    public void setPublicoTotal(double publicoTotal) {
        this.publicoTotal += publicoTotal;
    }
    
    public double getMediaDePublico() {
        return mediaDePublico;
    }

    public void setMediaDePublico(double mediaDePublico) {
        this.mediaDePublico = mediaDePublico;
    }

    public String getFaseDaCompeticao() {
        return faseDaCompeticao;
    }

    public void setFaseDaCompeticao(String faseDaCompeticao) {
        this.faseDaCompeticao = faseDaCompeticao;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getNumeroDeJogos() {
        return numeroDeJogos;
    }

    public void setNumeroDeJogos(int numeroDeJogos) {
        this.numeroDeJogos = numeroDeJogos;
    }
    
    public void mostrarSelecao(int finalidade){
        
        if(finalidade == 2){
            System.out.println(this.numero + "  | " + this.faseDaCompeticao + " | " + this.posicao + " | " + this.numeroDeJogos);
        }
        else if(finalidade == 3){
            System.out.println(this.numero + "  | " + this.publicoTotal + "  | " + this.mediaDePublico + " | ");
        }
        else{
            System.out.println("Nº | Pts | SG | Classificado | Publico Total | Media Publico |");
            System.out.println(this.numero + "  | " + this.pontos + "   | " + this.saldoDeGols + "  |    " + this.classificada + "     |      " + this.getPublicoTotal() + "    |      " + this.getMediaDePublico() + "    |");
        }
        
    }
    
    @Override
    public int compareTo(VerticeSelecao o) {
        
        if(this.getPontos() > o.getPontos()) return(1);
        else if(this.getPontos() < o.getPontos()) return(-1);
        else if(this.getPontos() == o.getPontos()){
            
            if(this.getSaldoDeGols() > o.getSaldoDeGols()) return(1);
            else if(this.getSaldoDeGols() < o.getSaldoDeGols()) return(-1);
            else return(0);
        }
        else return(0);
    }
}
