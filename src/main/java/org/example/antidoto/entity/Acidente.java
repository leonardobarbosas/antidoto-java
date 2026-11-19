package org.example.antidoto.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_ACIDENTE")
public class Acidente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACIDENTE")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANIMAL")
    private Animal animal;

    @Column(name = "DATA_ACIDENTE", nullable = false)
    private LocalDateTime dataAcidente;

    @Column(
            name = "DATA_REGISTRO",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private LocalDateTime dataRegistro;

    @Column(name = "LATITUDE", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "LONGITUDE", precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(name = "DESCRICAO", length = 1000)
    private String descricao;

    @Column(name = "STATUS", nullable = false, length = 30)
    private String status = "REGISTRADO";

    public Acidente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public LocalDateTime getDataAcidente() {
        return dataAcidente;
    }

    public void setDataAcidente(LocalDateTime dataAcidente) {
        this.dataAcidente = dataAcidente;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
