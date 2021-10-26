package com.praticasolucoes.emdiasbi.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name="CU_FLUXO_CAIXA")
public class CuFluxoCaixa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cu_fluxo_caixa_id_seq" )
    @SequenceGenerator(name="cu_fluxo_caixa_id_seq",sequenceName = "CU_FLUXO_CAIXA_ID_seq",allocationSize = 1)
    @Column(name="ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="ID_ITEM")
    private AtFluxoCaixaItem atFluxoCaixaItem;

    @ManyToOne
    @JoinColumn(name="ID_CONTA")
    private AtConta  atConta;

    @ManyToOne
    @JoinColumn(name="ID_TYPE")
    private AtTipoMovim atTipoMovim;

    @ManyToOne
    @JoinColumn(name="ID_RELATORIO")
    private AtRelatorio atRelatorio;

    @ManyToOne
    @JoinColumn(name="ID_DATA")
    private AtData atData ;

    @Column(name="VALOR")
    private BigDecimal valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AtFluxoCaixaItem getAtFluxoCaixaItem() {
        return atFluxoCaixaItem;
    }

    public void setAtFluxoCaixaItem(AtFluxoCaixaItem atFluxoCaixaItem) {
        this.atFluxoCaixaItem = atFluxoCaixaItem;
    }

    public AtConta getAtConta() {
        return atConta;
    }

    public void setAtConta(AtConta atConta) {
        this.atConta = atConta;
    }

    public AtTipoMovim getAtTipoMovim() {
        return atTipoMovim;
    }

    public void setAtTipoMovim(AtTipoMovim atTipoMovim) {
        this.atTipoMovim = atTipoMovim;
    }

    public AtRelatorio getAtRelatorio() {
        return atRelatorio;
    }

    public void setAtRelatorio(AtRelatorio atRelatorio) {
        this.atRelatorio = atRelatorio;
    }

    public AtData getAtData() {
        return atData;
    }

    public void setAtData(AtData atData) {
        this.atData = atData;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuFluxoCaixa that = (CuFluxoCaixa) o;
        return Objects.equals(id, that.id) && Objects.equals(atFluxoCaixaItem, that.atFluxoCaixaItem) && Objects.equals(atConta, that.atConta) && Objects.equals(atTipoMovim, that.atTipoMovim) && Objects.equals(atRelatorio, that.atRelatorio) && Objects.equals(atData, that.atData) && Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, atFluxoCaixaItem, atConta, atTipoMovim, atRelatorio, atData, valor);
    }
}
