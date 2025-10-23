/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "calificaciones")
@NamedQueries({
    @NamedQuery(name = "Calificaciones.findAll", query = "SELECT c FROM Calificaciones c"),
    @NamedQuery(name = "Calificaciones.findByIdCalificacion", query = "SELECT c FROM Calificaciones c WHERE c.idCalificacion = :idCalificacion"),
    @NamedQuery(name = "Calificaciones.findByExamen1", query = "SELECT c FROM Calificaciones c WHERE c.examen1 = :examen1"),
    @NamedQuery(name = "Calificaciones.findByExamen2", query = "SELECT c FROM Calificaciones c WHERE c.examen2 = :examen2"),
    @NamedQuery(name = "Calificaciones.findByExamenFinal", query = "SELECT c FROM Calificaciones c WHERE c.examenFinal = :examenFinal"),
    @NamedQuery(name = "Calificaciones.findByTotal", query = "SELECT c FROM Calificaciones c WHERE c.total = :total"),
    @NamedQuery(name = "Calificaciones.findByFechaRegistro", query = "SELECT c FROM Calificaciones c WHERE c.fechaRegistro = :fechaRegistro")})
public class Calificaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_calificacion")
    private Integer idCalificacion;
    @Column(name = "examen1")
    private Integer examen1;
    @Column(name = "examen2")
    private Integer examen2;
    @Column(name = "examen_final")
    private Integer examenFinal;
    @Column(name = "total", insertable = false, updatable = false)
    private Integer total;
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura")
    @ManyToOne(optional = false)
    private Asignaturas idAsignatura;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante")
    @ManyToOne(optional = false)
    private Estudiantes idEstudiante;

    public Calificaciones() {
    }

    public Calificaciones(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(Integer idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public Integer getExamen1() {
        return examen1;
    }

    public void setExamen1(Integer examen1) {
        this.examen1 = examen1;
    }

    public Integer getExamen2() {
        return examen2;
    }

    public void setExamen2(Integer examen2) {
        this.examen2 = examen2;
    }

    public Integer getExamenFinal() {
        return examenFinal;
    }

    public void setExamenFinal(Integer examenFinal) {
        this.examenFinal = examenFinal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Asignaturas getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignaturas idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Estudiantes getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiantes idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalificacion != null ? idCalificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificaciones)) {
            return false;
        }
        Calificaciones other = (Calificaciones) object;
        if ((this.idCalificacion == null && other.idCalificacion != null) || (this.idCalificacion != null && !this.idCalificacion.equals(other.idCalificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pro.entities.Calificaciones[ idCalificacion=" + idCalificacion + " ]";
    }
    
}
