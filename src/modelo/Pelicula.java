/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author USUARIO
 */
public class Pelicula {
    
    private String titulo;
    private Genero genero;
    private double duracion;
    private int año;
    private String director;
    private double calificacionProm;
    private String sinopsis;
    private String poster;

    public Pelicula() {
    }

    public Pelicula(String titulo, Genero genero, double duracion, int año, String director, double calificacionProm, String sinopsis, String poster) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracion = duracion;
        this.año = año;
        this.director = director;
        this.calificacionProm = calificacionProm;
        this.sinopsis = sinopsis;
        this.poster = poster;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getCalificacionProm() {
        return calificacionProm;
    }

    public void setCalificacionProm(double calificacionProm) {
        this.calificacionProm = calificacionProm;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
