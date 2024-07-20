package com.schoolbackend.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private String nombre;

    private String apellido;

    private char genero;

    private Date fecha_registro;

    private Date fecha_nacimiento;
    
    private int estado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "alumno")
    private Set<Pago> pagos;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "alumno")
    private Set<AlumnoMateria> materias;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy =  "alumno")
    private Apoderado apoderado;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private User user;
    
    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)      //el campo esta oculto

    //Extra
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToMany(mappedBy = "alumnos")
    private Set<Aula> aulas;


    public Alumno() {
        pagos = new HashSet<>();
        materias = new HashSet<>();
    }

    public Alumno(String dni, String nombre, String apellido, char genero) {
        this();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
    }

    
    public Alumno(Long id, String dni, String nombre, String apellido, char genero, Date fecha_nacimiento,
            int estado) {
        this();
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado = estado;
    }

    
    


    public Alumno(Long id, String dni, String nombre, String apellido, char genero, Date fecha_registro,
            Date fecha_nacimiento, int estado, User user) {
        this();
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.fecha_registro = fecha_registro;
        this.fecha_nacimiento = fecha_nacimiento;
        this.estado = estado;
        this.user = user;
    }

    @PrePersist
    public void prePersist(){
        estado = 1;
    }


    @Override
    public String toString() {
        return "Alumno [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", genero="
                + genero + ", fecha_registro=" + fecha_registro + ", fecha_nacimiento=" + fecha_nacimiento + ", estado="+estado+"]";
    }



    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    
    public Set<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(Set<Pago> pagos) {
        this.pagos = pagos;
    }

    public void addPago(Pago p){
        this.pagos.add(p);
        p.setAlumno(this);
    }
    public void removePago(Pago p){
        p.setAlumno(null);
        this.pagos.remove(p);
    }



    public Apoderado getApoderado() {
        return apoderado;
    }
    public void setApoderado(Apoderado apoderado) {
        this.apoderado = apoderado;
        apoderado.setAlumno(this);
    }
    public void removeApoderado(Apoderado apoderado) {
        apoderado.setAlumno(null);
        this.apoderado = null;
    }


    
    public Set<AlumnoMateria> getMaterias() {
        return materias;
    }
    public void setMaterias(Set<AlumnoMateria> materias) {
        this.materias = materias;
    }
    public void addMateria(AlumnoMateria am){
        this.materias.add(am);
        am.setAlumno(this);
    }
    public void removeMateria(AlumnoMateria am){
        am.setAlumno(null);
        this.materias.remove(am);
    }


    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }



    public Set<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(Set<Aula> aulas) {
        this.aulas = aulas;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    


    
}
