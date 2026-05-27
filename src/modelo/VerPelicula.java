/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class VerPelicula {
    
    private Usuario usuario;
    private Pelicula pelicula;
    private Fecha fechaVista;
    private double calificacion;
    private String comentario;

    public VerPelicula() {
    }

    public VerPelicula(Usuario usuario, Pelicula pelicula, Fecha fechaVista, double calificacion, String comentario) {
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.fechaVista = fechaVista;
        this.calificacion = calificacion;
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public Fecha getFechaVista() {
        return fechaVista;
    }

    public void setFechaVista(Fecha fechaVista) {
        this.fechaVista = fechaVista;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
    
}
