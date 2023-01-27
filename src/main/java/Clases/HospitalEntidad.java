package Clases;

import javax.persistence.*;

@Entity
@Table(name = "hospital", schema = "bd_hospital")
public class HospitalEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codigo")
    private int codigo;
    @Basic
    @Column(name = "nombre")
    private String nombre;
    @Basic
    @Column(name = "localidad")
    private String localidad;
    @Basic
    @Column(name = "telefono")
    private String telefono;
    @Basic
    @Column(name = "numero_camas")
    private Integer numeroCamas;

    public HospitalEntidad() {
    }

    public HospitalEntidad(int codigo, String nombre, String localidad, String telefono, Integer numeroCamas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.localidad = localidad;
        this.telefono = telefono;
        this.numeroCamas = numeroCamas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Integer getNumeroCamas() {
        return numeroCamas;
    }

    public void setNumeroCamas(Integer numeroCamas) {
        this.numeroCamas = numeroCamas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HospitalEntidad that = (HospitalEntidad) o;

        if (codigo != that.codigo) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (localidad != null ? !localidad.equals(that.localidad) : that.localidad != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;
        if (numeroCamas != null ? !numeroCamas.equals(that.numeroCamas) : that.numeroCamas != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (localidad != null ? localidad.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        result = 31 * result + (numeroCamas != null ? numeroCamas.hashCode() : 0);
        return result;
    }
}
