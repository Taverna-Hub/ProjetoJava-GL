package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;
import java.time.LocalDate;
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

    File arquivoAcao = new File("ProjetoJava-GL/src/BDs/Acao.txt");

    public boolean incluirEntidadeOperadora(Acao acao) throws IOException {

        if (buscarEntidadeOperaradora(acao.getIdentificador()) != null) {
            return false;
        }

        Scanner scan = new Scanner(arquivoAcao);
        FileWriter escreverLinha = new FileWriter(arquivoAcao, true);


        String identificadorString = String.valueOf(acao.getIdentificador());
        String dataString = String.valueOf(acao.getDataDeValidade());
        String valorString = String.valueOf(acao.getValorUnitario());

        String linhaCompleta = identificadorString + ";" + acao.getNome() + ";" + dataString + ";" + valorString + ";\n";

        escreverLinha.write(linhaCompleta);
        scan.close();
        escreverLinha.close();

        return true;
    }

    public boolean alterarEntidadeOperadora(Acao acao) throws IOException {

        if (buscarEntidadeOperaradora(acao.getIdentificador()) == null){
            return false;
        }

        Scanner SCAN = new Scanner(arquivoAcao);
        List<String> linhasDoArquivo = new ArrayList<>();

        while (SCAN.hasNextLine()){
            linhasDoArquivo.add(SCAN.nextLine());

        }
        FileWriter escreverLinha = new FileWriter(arquivoAcao);

        for (String linha : linhasDoArquivo){

            String[] arrayLinha = linha.split(";");

            int identificadorArray = Integer.parseInt(arrayLinha[0]);
            LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
            double valorUnitarioArray = Double.parseDouble(arrayLinha[3]);
            if (acao.getIdentificador() != identificadorArray){
                incluirEntidadeOperadora(new Acao(identificadorArray, arrayLinha[1], dataArray, valorUnitarioArray));

            }
            else {
                incluirEntidadeOperadora(acao);

            }

        }

        return true;
    }

    public boolean excluirEntidadeOperadora(int identificador) throws IOException {

        if (buscarEntidadeOperaradora(identificador) == null){
            return false;
        }

        Scanner SCAN = new Scanner(arquivoAcao);
        List<String> linhasDoArquivo = new ArrayList<>();

        while (SCAN.hasNextLine()){
            linhasDoArquivo.add(SCAN.nextLine());

        }
        FileWriter escreverLinha = new FileWriter(arquivoAcao);

        for (String linha : linhasDoArquivo){

            String[] arrayLinha = linha.split(";");

            int identificadorArray = Integer.parseInt(arrayLinha[0]);
            LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
            double valorUnitarioArray = Double.parseDouble(arrayLinha[3]);
            if (identificador != identificadorArray){
                incluirEntidadeOperadora(new Acao(identificadorArray, arrayLinha[1], dataArray, valorUnitarioArray));

            }

        }

        return true;
    }

    public Acao buscarEntidadeOperaradora(int identificador) throws FileNotFoundException {

        Scanner scan = new Scanner(arquivoAcao);
        while (scan.hasNextLine()) {

            String[] arrayLinha = scan.nextLine().split(";");

            int identificadorArray = Integer.parseInt(arrayLinha[0]);
            LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
            double valorUnitarioArray = Double.parseDouble(arrayLinha[3]);

            if (identificador == identificadorArray) {
                scan.close();
                return new Acao(identificadorArray, arrayLinha[1], dataArray, valorUnitarioArray);
            }
        }
        scan.close();
        return null;
    }
}
