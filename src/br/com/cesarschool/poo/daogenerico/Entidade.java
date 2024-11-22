package br.com.cesarschool.poo.daogenerico;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Entidade implements Serializable {
    private String idUnico;
    private LocalDateTime dataHoraInclusao;
    private LocalDateTime dataHoraUltimaAlteracao;

    public Entidade(String idUnico, LocalDateTime dataHoraInclusao) {
        this.idUnico = idUnico;
        this.dataHoraInclusao = dataHoraInclusao;
    }

    protected Entidade() {
    }

    public String getIdUnico() {
        return idUnico;
    }

    public LocalDateTime getDataHoraInclusao() {
        return dataHoraInclusao;
    }

    public LocalDateTime getDataHoraUltimaAlteracao() {
        return dataHoraUltimaAlteracao;
    }

    public void setDataHoraUltimaAlteracao(LocalDateTime dataHoraUltimaAlteracao) {
        this.dataHoraUltimaAlteracao = dataHoraUltimaAlteracao;
    }
}
