package com.praticasolucoes.emdiasapiv2.domain.fluxocaixa;

import java.io.Serializable;
import java.util.Objects;

public class LinhaFluxoCaixaMesDTO implements Serializable,Comparable<LinhaFluxoCaixaMesDTO> {
    private String id;
    private String descricao;
    private String coluna1x;
    private String coluna2x;
    private String coluna3x;
    private String coluna4x;
    private String coluna5x;
    private String coluna6x;
    private String coluna7x;
    private String coluna8x;
    private String coluna9x;
    private String coluna10x;
    private String coluna11x;
    private String coluna12x;
    private String coluna13x;
    private String coluna14x;
    private String coluna15x;
    private String coluna16x;
    private String coluna17x;
    private String coluna18x;
    private String coluna19x;
    private String coluna20x;
    private String coluna21x;
    private String coluna22x;
    private String coluna23x;
    private String coluna24x;
    private String coluna25x;
    private String coluna26x;
    private String coluna27x;
    private String coluna28x;
    private String coluna29x;
    private String coluna30x;
    private String coluna31x;
    private String coluna32x;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinhaFluxoCaixaMesDTO(String id , String descricao) {
        this.descricao = descricao;
        this.id = id ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinhaFluxoCaixaMesDTO that = (LinhaFluxoCaixaMesDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int compareTo( LinhaFluxoCaixaMesDTO o) {
        return this.getId().compareTo(o.getId());
    }


    public String getColuna1x() {
        return coluna1x;
    }

    public void setColuna1x(String coluna1x) {
        this.coluna1x = coluna1x;
    }

    public String getColuna2x() {
        return coluna2x;
    }

    public void setColuna2x(String coluna2x) {
        this.coluna2x = coluna2x;
    }

    public String getColuna3x() {
        return coluna3x;
    }

    public void setColuna3x(String coluna3x) {
        this.coluna3x = coluna3x;
    }

    public String getColuna4x() {
        return coluna4x;
    }

    public void setColuna4x(String coluna4x) {
        this.coluna4x = coluna4x;
    }

    public String getColuna5x() {
        return coluna5x;
    }

    public void setColuna5x(String coluna5x) {
        this.coluna5x = coluna5x;
    }

    public String getColuna6x() {
        return coluna6x;
    }

    public void setColuna6x(String coluna6x) {
        this.coluna6x = coluna6x;
    }

    public String getColuna7x() {
        return coluna7x;
    }

    public void setColuna7x(String coluna7x) {
        this.coluna7x = coluna7x;
    }

    public String getColuna8x() {
        return coluna8x;
    }

    public void setColuna8x(String coluna8x) {
        this.coluna8x = coluna8x;
    }

    public String getColuna9x() {
        return coluna9x;
    }

    public void setColuna9x(String coluna9x) {
        this.coluna9x = coluna9x;
    }

    public String getColuna10x() {
        return coluna10x;
    }

    public void setColuna10x(String coluna10x) {
        this.coluna10x = coluna10x;
    }

    public String getColuna11x() {
        return coluna11x;
    }

    public void setColuna11x(String coluna11x) {
        this.coluna11x = coluna11x;
    }

    public String getColuna12x() {
        return coluna12x;
    }

    public void setColuna12x(String coluna12x) {
        this.coluna12x = coluna12x;
    }

    public String getColuna13x() {
        return coluna13x;
    }

    public void setColuna13x(String coluna13x) {
        this.coluna13x = coluna13x;
    }

    public String getColuna14x() {
        return coluna14x;
    }

    public void setColuna14x(String coluna14x) {
        this.coluna14x = coluna14x;
    }

    public String getColuna15x() {
        return coluna15x;
    }

    public void setColuna15x(String coluna15x) {
        this.coluna15x = coluna15x;
    }

    public String getColuna16x() {
        return coluna16x;
    }

    public void setColuna16x(String coluna16x) {
        this.coluna16x = coluna16x;
    }

    public String getColuna17x() {
        return coluna17x;
    }

    public void setColuna17x(String coluna17x) {
        this.coluna17x = coluna17x;
    }

    public String getColuna18x() {
        return coluna18x;
    }

    public void setColuna18x(String coluna18x) {
        this.coluna18x = coluna18x;
    }

    public String getColuna19x() {
        return coluna19x;
    }

    public void setColuna19x(String coluna19x) {
        this.coluna19x = coluna19x;
    }

    public String getColuna20x() {
        return coluna20x;
    }

    public void setColuna20x(String coluna20x) {
        this.coluna20x = coluna20x;
    }

    public String getColuna21x() {
        return coluna21x;
    }

    public void setColuna21x(String coluna21x) {
        this.coluna21x = coluna21x;
    }

    public String getColuna22x() {
        return coluna22x;
    }

    public void setColuna22x(String coluna22x) {
        this.coluna22x = coluna22x;
    }

    public String getColuna23x() {
        return coluna23x;
    }

    public void setColuna23x(String coluna23x) {
        this.coluna23x = coluna23x;
    }

    public String getColuna24x() {
        return coluna24x;
    }

    public void setColuna24x(String coluna24x) {
        this.coluna24x = coluna24x;
    }

    public String getColuna25x() {
        return coluna25x;
    }

    public void setColuna25x(String coluna25x) {
        this.coluna25x = coluna25x;
    }

    public String getColuna26x() {
        return coluna26x;
    }

    public void setColuna26x(String coluna26x) {
        this.coluna26x = coluna26x;
    }

    public String getColuna27x() {
        return coluna27x;
    }

    public void setColuna27x(String coluna27x) {
        this.coluna27x = coluna27x;
    }

    public String getColuna28x() {
        return coluna28x;
    }

    public void setColuna28x(String coluna28x) {
        this.coluna28x = coluna28x;
    }

    public String getColuna29x() {
        return coluna29x;
    }

    public void setColuna29x(String coluna29x) {
        this.coluna29x = coluna29x;
    }

    public String getColuna30x() {
        return coluna30x;
    }

    public void setColuna30x(String coluna30x) {
        this.coluna30x = coluna30x;
    }

    public String getColuna31x() {
        return coluna31x;
    }

    public void setColuna31x(String coluna31x) {
        this.coluna31x = coluna31x;
    }

    public String getColuna32x() {
        return coluna32x;
    }

    public void setColuna32x(String coluna32x) {
        this.coluna32x = coluna32x;
    }
}
