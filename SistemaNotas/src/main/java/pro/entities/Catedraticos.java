/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pro.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "catedraticos")
@NamedQueries({
    @NamedQuery(name = "Catedraticos.findAll", query = "SELECT c FROM Catedraticos c"),
    @NamedQuery(name = "Catedraticos.findByIdCatedratico", query = "SELECT c FROM Catedraticos c WHERE c.idCatedratico = :idCatedratico"),
    @NamedQuery(name = "Catedraticos.findByEspecialidad", query = "SELECT c FROM Catedraticos c WHERE c.especialidad = :especialidad"),
    @NamedQuery(name = "Catedraticos.findByTelefono", query = "SELECT c FROM Catedraticos c WHERE c.telefono = :telefono")})
public class Catedraticos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_catedratico")
    private Integer idCatedratico;
    @Basic(optional = false)
    @Column(name = "especialidad")
    private String especialidad;
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @OneToOne(optional = false)
    private Usuarios idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCatedratico")
    private List<Asignaturas> asignaturasList;

    public Catedraticos() {
    }

    public Catedraticos(Integer idCatedratico) {
        this.idCatedratico = idCatedratico;
    }

    public Catedraticos(Integer idCatedratico, String especialidad) {
        this.idCatedratico = idCatedratico;
        this.especialidad = especialidad;
    }

    public Integer getIdCatedratico() {
        return idCatedratico;
    }

    public void setIdCatedratico(Integer idCatedratico) {
        this.idCatedratico = idCatedratico;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuarios idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Asignaturas> getAsignaturasList() {
        return asignaturasList;
    }

    public void setAsignaturasList(List<Asignaturas> asignaturasList) {
        this.asignaturasList = asignaturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCatedratico != null ? idCatedratico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catedraticos)) {
            return false;
        }
        Catedraticos other = (Catedraticos) object;
        if ((this.idCatedratico == null && other.idCatedratico != null) || (this.idCatedratico != null && !this.idCatedratico.equals(other.idCatedratico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pro.entities.Catedraticos[ idCatedratico=" + idCatedratico + " ]";
    }
    
}
