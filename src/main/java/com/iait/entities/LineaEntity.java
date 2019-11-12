package com.iait.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.iait.converters.SistemaAmortizacionConverter;
import com.iait.enums.SistemaAmortizacionEnum;

@Entity
@Table(name = "lineas")
public class LineaEntity {
    
    @Id @Column(name = "linea_id")
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 200)
    private String nombre;
    
    @Convert(converter = SistemaAmortizacionConverter.class)
    @Column(name = "sistema_amortizacion", nullable = false, length = 1)
    private SistemaAmortizacionEnum sistemaAmortizacion;
    
    @Column(name = "tasa_min", nullable = false)
    private BigDecimal tasaMinima;
    
    @Column(name = "tasa_max", nullable = false)
    private BigDecimal tasaMaxima;
    
    @Column(name = "cuotas_min", nullable = false)
    private Long cuotasMinimas;
    
    @Column(name = "cuotas_max", nullable = false)
    private Long cuotasMaximas;
    
    @Column(name = "capital_min", nullable = false)
    private BigDecimal capitalMinimo;
    
    @Column(name = "capital_max", nullable = false)
    private BigDecimal capitalMaximo;
    
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "usuario_tipo_documento_id", 
                    referencedColumnName = "id_tipodocumento", nullable = false),
            @JoinColumn(name = "usuario_numero_documento", 
                    referencedColumnName = "numero_documento", nullable = false)
    })
    private UsuarioEntity usuario;
    
    public LineaEntity() {}
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public SistemaAmortizacionEnum getSistemaAmortizacion() {
        return sistemaAmortizacion;
    }
    
    public void setSistemaAmortizacion(SistemaAmortizacionEnum sistemaAmortizacion) {
        this.sistemaAmortizacion = sistemaAmortizacion;
    }
    
    public BigDecimal getTasaMinima() {
        return tasaMinima;
    }
    
    public void setTasaMinima(BigDecimal tasaMinima) {
        this.tasaMinima = tasaMinima;
    }
    
    public BigDecimal getTasaMaxima() {
        return tasaMaxima;
    }
    
    public void setTasaMaxima(BigDecimal tasaMaxima) {
        this.tasaMaxima = tasaMaxima;
    }
    
    public Long getCuotasMinimas() {
        return cuotasMinimas;
    }
    
    public void setCuotasMinimas(Long cuotasMinimas) {
        this.cuotasMinimas = cuotasMinimas;
    }
    
    public Long getCuotasMaximas() {
        return cuotasMaximas;
    }
    
    public void setCuotasMaximas(Long cuotasMaximas) {
        this.cuotasMaximas = cuotasMaximas;
    }
    
    public BigDecimal getCapitalMinimo() {
        return capitalMinimo;
    }
    
    public void setCapitalMinimo(BigDecimal capitalMinimo) {
        this.capitalMinimo = capitalMinimo;
    }
    
    public BigDecimal getCapitalMaximo() {
        return capitalMaximo;
    }
    
    public void setCapitalMaximo(BigDecimal capitalMaximo) {
        this.capitalMaximo = capitalMaximo;
    }
    
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
    
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineaEntity)) {
            return false;
        }
        LineaEntity other = (LineaEntity) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "LineaEntity [id=" + id + ", nombre=" + nombre + ", sistemaAmortizacion=" 
                + sistemaAmortizacion + ", fechaAlta=" + fechaAlta + "]";
    }
}
