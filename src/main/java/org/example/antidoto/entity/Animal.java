
package org.example.antidoto.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.*;

@Entity
@Table(name = "TBL_ANIMAL")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANIMAL")
    private Long id;

    @NotBlank(message = "O nome popular é obrigatório")
    @Size(max = 100, message = "O nome popular deve ter no máximo 100 caracteres")
    @Column(name = "NOME_POPULAR", nullable = false)
    private String nomePopular;

    @Size(max = 150, message = "O nome científico deve ter no máximo 150 caracteres")
    @Column(name = "NOME_CIENTIFICO")
    private String nomeCientifico;

    @NotBlank(message = "A categoria é obrigatória")
    @Pattern(
            regexp = "SERPENTE|ESCORPIAO|ARANHA|OUTRO",
            message = "A categoria deve ser SERPENTE, ESCORPIAO, ARANHA ou OUTRO"
    )
    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
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
