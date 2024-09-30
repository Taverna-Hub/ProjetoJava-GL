package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    private static final String FILE_PATH = "Acao.txt";
    private List<Acao> lerArquivo() throws IOException {

        List<Acao> acoes = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader(FILE_PATH))) {

            String linha;
            while ((linha = leitor.readLine()) != null) {

                String[] dados = linha.split(";");

                int identificador = Integer.parseInt(dados[0]);
                String nome = dados[1];
                LocalDate dataLocal = LocalDate.parse(dados[2]);
                double valorUnitario = Double.parseDouble(dados[3]);

                acoes.add(new Acao(identificador, nome, dataLocal, valorUnitario));
            }
        }
        return acoes;
    }

    public void gravarArquivo(List<Acao> acoes) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Acao acao : acoes) {

                writer.write(acao.getIdentificador() + ";"
                        + acao.getNome() + ";"
                        + acao.getDataDeValidade() + ";"
                        + acao.getValorUnitario() + ";");
                writer.newLine();
            }
        }
    }

    public boolean AlterarAcao(Acao acaoAlterada) throws IOException {

        List<Acao> acoes = lerArquivo();
        boolean encontrou = false;

        for (int i = 0; i < acoes.size(); i++) {
            if (acoes.get(i).getIdentificador() == acaoAlterada.getIdentificador()) {

                acoes.set(i, acaoAlterada);
                encontrou = true;
                break;
            }
        }

        if(encontrou) {
            gravarArquivo(acoes);
            return true;
        }
        return false;
    }

    public boolean excluirAcao(int id) throws IOException {

        List<Acao> acoes = lerArquivo();
        boolean encontrou = false;

        for (int i = 0; i < acoes.size(); i++) {
            if (acoes.get(i).getIdentificador() == id) {

                acoes.remove(i);
                encontrou = true;
                break;
            }
        }

        if(encontrou) {
            gravarArquivo(acoes);
            return true;
        }
        return false;
    }

    public Acao buscarAcao(int id) throws IOException {

        List<Acao> acoes = lerArquivo();
        for (Acao acao : acoes) {

            if (acao.getIdentificador() == id) {
                return acao;
            }
        }
        return null;
    }
}
