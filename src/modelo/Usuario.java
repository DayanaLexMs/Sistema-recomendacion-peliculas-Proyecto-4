/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Usuario {
    
    private String id;
    private String nombre;
    private String nomUsuario;
    private String cedula;
    private String correoElectronico;
    private String contraseña;
    private Fecha fechaRegistro;
    private ArrayList <Genero> preferencias; //lista de preferencias
    private ArrayList <Pelicula> peliculasVistas;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String nomUsuario, String cedula, String correoElectronico, String contraseña, Fecha fechaRegistro, ArrayList<Genero> preferencias, ArrayList<Pelicula> peliculasVistas) {
        this.id = id;
        this.nombre = nombre;
        this.nomUsuario = nomUsuario;
        this.cedula = cedula;
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.fechaRegistro = fechaRegistro;
        this.preferencias = preferencias;
        this.peliculasVistas = peliculasVistas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNomUsuario() {
        return nomUsuario;
    }

    public void setNomUsuario(String nomUsuario) {
        this.nomUsuario = nomUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Fecha getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Fecha fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ArrayList<Genero> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(ArrayList<Genero> preferencias) {
        this.preferencias = preferencias;
    }

    public ArrayList<Pelicula> getPeliculasVistas() {
        return peliculasVistas;
    }

    public void setPeliculasVistas(ArrayList<Pelicula> peliculasVistas) {
        this.peliculasVistas = peliculasVistas;
    }
    
}
