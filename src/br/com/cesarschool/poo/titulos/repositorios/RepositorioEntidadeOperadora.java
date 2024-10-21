package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas.
 *
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */
public class RepositorioEntidadeOperadora {

    File arquivoEntidadeOperadora = new File("src/BDs/EntidadeOperadora.txt");

    public boolean incluir(EntidadeOperadora entidadeOperadora) throws IOException {

        if (buscar(entidadeOperadora.getIdentificador()) != null) {
            return false;
        }

        FileWriter escreverLinha = new FileWriter(arquivoEntidadeOperadora, true);


        String identificadorString = String.valueOf(entidadeOperadora.getIdentificador());
        String saldoAcaoString = String.valueOf(entidadeOperadora.getSaldoAcao());
        String autorizadoAcaoString = String.valueOf(entidadeOperadora.getAutorizadoAcao());
        String SaldoTituloDividaString = String.valueOf(entidadeOperadora.getSaldoTituloDivida());

        String linhaCompleta = identificadorString +
                ";" + entidadeOperadora.getNome() +
                ";" + autorizadoAcaoString +  ";"
                + saldoAcaoString + ";"
                + SaldoTituloDividaString +
                ";\n";

        escreverLinha.write(linhaCompleta);
        escreverLinha.close();

        return true;
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora) throws IOException {

        if (buscar(entidadeOperadora.getIdentificador()) == null){
            return false;
        }

        Scanner SCAN = new Scanner(arquivoEntidadeOperadora);
        List<String> linhasDoArquivo = new ArrayList<>();

        while (SCAN.hasNextLine()){
            linhasDoArquivo.add(SCAN.nextLine());

        }
        FileWriter escreverLinha = new FileWriter(arquivoEntidadeOperadora);

        for (String linha : linhasDoArquivo){

            String[] arrayLinha = linha.split(";");

            long identificadorArray = Long.parseLong(arrayLinha[0]);
            boolean autorizadoAcaoArray = Boolean.parseBoolean(arrayLinha[2]);
            double saldoAcaoArray = Double.parseDouble(arrayLinha[3]);
            double saldoTituloDividaArray = Double.parseDouble(arrayLinha[4]);

            if (entidadeOperadora.getIdentificador() != identificadorArray){
                EntidadeOperadora e1 = new EntidadeOperadora(identificadorArray, arrayLinha[1], autorizadoAcaoArray);
                e1.creditarSaldoAcao(saldoAcaoArray);
                e1.creditarSaldoTituloDivida(saldoTituloDividaArray);
                incluir(e1);

            }
            else {
                incluir(entidadeOperadora);

            }

        }

        return true;
    }

    public boolean excluir(long identificador) throws IOException {

        if (buscar(identificador) == null){
            return false;
        }

        Scanner SCAN = new Scanner(arquivoEntidadeOperadora);
        List<String> linhasDoArquivo = new ArrayList<>();

        while (SCAN.hasNextLine()){
            linhasDoArquivo.add(SCAN.nextLine());

        }
        FileWriter escreverLinha = new FileWriter(arquivoEntidadeOperadora);

        for (String linha : linhasDoArquivo){

            String[] arrayLinha = linha.split(";");

            long identificadorArray = Long.parseLong(arrayLinha[0]);
            boolean autorizadoAcaoArray = Boolean.parseBoolean(arrayLinha[2]);
            double saldoAcaoArray = Double.parseDouble(arrayLinha[3]);
            double saldoTituloDividaArray = Double.parseDouble(arrayLinha[4]);

            if (identificador != identificadorArray){
                EntidadeOperadora e1 = new EntidadeOperadora(identificadorArray, arrayLinha[1], autorizadoAcaoArray);
                e1.creditarSaldoAcao(saldoAcaoArray);
                e1.creditarSaldoTituloDivida(saldoTituloDividaArray);
                incluir(e1);
            }

        }

        return true;
    }

    public EntidadeOperadora buscar(long identificador) throws FileNotFoundException {

        Scanner scan = new Scanner(arquivoEntidadeOperadora);
        while (scan.hasNextLine()) {

            String[] arrayLinha = scan.nextLine().split(";");

            long identificadorArray = Long.parseLong(arrayLinha[0]);
            boolean autorizadoAcaoArray = Boolean.parseBoolean(arrayLinha[2]);
            double saldoAcaoArray = Double.parseDouble(arrayLinha[3]);
            double saldoTituloDividaArray = Double.parseDouble(arrayLinha[4]);


            if (identificador == identificadorArray) {
                scan.close();
                EntidadeOperadora e1 = new EntidadeOperadora(identificadorArray, arrayLinha[1], autorizadoAcaoArray);
                e1.creditarSaldoAcao(saldoAcaoArray);
                e1.creditarSaldoTituloDivida(saldoTituloDividaArray);
                return e1;
            }
        }
        scan.close();
        return null;
    }

    public List<EntidadeOperadora> buscarTodos () throws FileNotFoundException {
        Scanner scan = new Scanner(arquivoEntidadeOperadora);
        List<EntidadeOperadora> listaEntidades = new ArrayList<>();
        EntidadeOperadora e1;
        while (scan.hasNextLine()) {

            String[] arrayLinha = scan.nextLine().split(";");

            long identificadorArray = Long.parseLong(arrayLinha[0]);
            boolean autorizadoAcaoArray = Boolean.parseBoolean(arrayLinha[2]);
            double saldoAcaoArray = Double.parseDouble(arrayLinha[3]);
            double saldoTituloDividaArray = Double.parseDouble(arrayLinha[4]);



            e1 = new EntidadeOperadora(identificadorArray, arrayLinha[1], autorizadoAcaoArray);
            e1.creditarSaldoAcao(saldoAcaoArray);
            e1.creditarSaldoTituloDivida(saldoTituloDividaArray);
            listaEntidades.add(e1);

        }
        scan.close();
                return listaEntidades;
    }
}
