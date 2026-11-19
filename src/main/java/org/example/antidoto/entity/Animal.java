
package org.example.antidoto.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_ANIMAL")
public class Animal {

    @Id
    @Column(name = "ID_ANIMAL")
    private Long id;

    @Column(name = "NOME_POPULAR", nullable = false)
    private String nomePopular;

    @Column(name = "NOME_CIENTIFICO")
    private String nomeCientifico;

    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Column(name = "DESCRICAO")
    private String descricao;

    public Animal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
