/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes;

import codificacoes.buscaProfundidade.BuscaProfundidade;
import codificacoes.buscaProfundidade.Pilha;
import codificacoes.buscaProfundidade.Vertice;
import codificacoes.coloracao.Coloracao;
import codificacoes.conectividade.Conectividade;
import codificacoes.conjuntoDados.ConjuntoDados;
import codificacoes.representacaoComputacional.Arquivo;
import java.util.ArrayList;
import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.transposicao.Transposicao;


/**
 *
 * @author BRUNO
 */
public class teste {
    
    public static void main(String [] args){
        
        Adjacencia adj = Arquivo.leituraArquivo("C:/Users/BRUNO/Documents/Ciência da Computação - UNESP/3º Ano/1º Semestre/Teoria dos Grafos/Trabalho/2º Bimestre/fctpp-grafos-sistema/grafos/grafosExemplos/coloracao2.txt", false);
        //adj.getRepresentacao().exibir();
        
        /*
        //Ordem Topologica
        BuscaProfundidade bp = new BuscaProfundidade(adj);
        bp.DFS(adj, 3);
        bp.resultadosDaBusca();
        System.out.println("");
        bp.pilhaTopologica.exibir();
        System.out.println("");
        
        ArrayList<Vertice> topologica = bp.getSOrdemTopologica();
        for(Vertice v : topologica){
            System.out.print(v.getPosicao() + " -> ");
        }
        */
        
        
        /*
        //Transposição
        Adjacencia adjTransposta = Transposicao.RealizarTransposicao(adj);
        
        System.out.println("original");
        adj.exibir();
        System.out.println("transposta");
        adjTransposta.exibir();
        */
        
        
        Coloracao c = new Coloracao(adj);
        c.AlgoritmoColoracao(adj);
        c.exibirVertices();
        
        
        /*Conectividade c = new Conectividade(adj);
        c.algoritmoConectividade(adj);
        c.mostrarVertices();*/
        
        //ConjuntoDados cd = new ConjuntoDados(adj);
        //cd.calcularClassificadosDeUmGrupo();
        //cd.calcularMediaPublicoDeUmGrupo();
        //cd.calcularCampeao();
        //cd.calcularClassificadosDeTodosGrupos();
        //cd.calcularSelecaoMaiorMediaPublicoFaseGrupos();
        
    }
}
