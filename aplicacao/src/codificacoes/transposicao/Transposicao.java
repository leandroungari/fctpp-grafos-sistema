/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codificacoes.transposicao;

import codificacoes.representacaoComputacional.Adjacencia;
import codificacoes.representacaoComputacional.Arquivo;
import codificacoes.representacaoComputacional.ListaAdjacencia;
import codificacoes.representacaoComputacional.MatrizAdjacencia;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author BRUNO
 */
public class Transposicao {
    
    public static Adjacencia RealizarTransposicao(Adjacencia adjOriginal){
        
        int i, pos, numVertices;
        boolean verif;
        
        numVertices = adjOriginal.getNumeroVertices();
        
        ListaAdjacencia la = new ListaAdjacencia(numVertices);
        Adjacencia adjTransposta = new Adjacencia(numVertices, la, adjOriginal.isTipoGrafo());
        
        pos = 0;
        adjOriginal.inicializaIterator(pos);
        verif = true;
        while(pos < numVertices){
            
            while(verif = adjOriginal.hasNext(pos)){
            
                i = adjOriginal.next(pos);
                adjTransposta.adicionaAresta(i, pos, adjOriginal.getPeso(pos, i));
            
            }
            
            pos++;
            if(!verif && pos < numVertices){
                adjOriginal.inicializaIterator(pos);
            }
        }
        
        
        return(adjTransposta);
        
    }
}
