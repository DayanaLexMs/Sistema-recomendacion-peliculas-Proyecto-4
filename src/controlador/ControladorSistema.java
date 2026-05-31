/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import javax.swing.JOptionPane;
import modelo.*;
import vista.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author USUARIO
 */
public class ControladorSistema implements ActionListener {
    
    JFRegistroUsuario frmUsuario = new JFRegistroUsuario ();
    JFNotFlix frmPrincipal = new JFNotFlix ();
    JFCalificar frmCalificar = new JFCalificar ();
    
    ArrayList <Usuario> listaUsuarios;
    ArrayList <Pelicula> listaPeliculas;

    public ControladorSistema() {
    }
    
    public ControladorSistema (JFNotFlix frmPrincipal, JFRegistroUsuario frmUsuario, JFCalificar frmCalificar) {
        this.frmPrincipal = frmPrincipal;
        this.frmUsuario = frmUsuario;
        this.frmCalificar = frmCalificar;
        this.listaUsuarios = new ArrayList <>();
        this.listaPeliculas = new ArrayList <> ();
        
        this.frmUsuario.btnRegistrarse.addActionListener(this);
        this.frmPrincipal.btnFrmRegistrarse.addActionListener(this);
        this.frmPrincipal.btnBuscarGenero.addActionListener(this);
        
        crearPeliculas ();
        crearBotonesPeliculas ();
        llenarCombo ();
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        
        if (e.getSource()==this.frmUsuario.btnRegistrarse){
            registrarUsuario ();
        }
        
        if (e.getSource()==this.frmPrincipal.btnFrmRegistrarse){
            this.frmUsuario.setVisible(true);
            this.frmUsuario.setLocationRelativeTo(null);
        }
        
        if (e.getSource()==this.frmPrincipal.btnBuscarGenero){
            buscarGenero ();
        }
        
    }
    
    private void llenarCombo (){
        for (Genero g : Genero.values()) {
            frmPrincipal.cmbGeneros.addItem(g.toString());
        }
    }
    
    public void registrarUsuario (){
        
        String nom = this.frmUsuario.txtNom.getText();
        String ced = this.frmUsuario.txtCedula.getText();
        String correo = this.frmUsuario.txtCorreo.getText();
        String usuario = this.frmUsuario.txtUsuario.getText();
        String contra = this.frmUsuario.txtContraseña.getText();
        String contra2 = this.frmUsuario.txtVerContraseña.getText();
        
        if (nom.isEmpty()||ced.isEmpty()||correo.isEmpty()||contra.isEmpty()||contra2.isEmpty()||usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "DIGITE LOS DATOS COMPLETOS PARA REALIZAR EL REGISTRO");
            reiniciarCamposUsuario();
        }
        else if (contra.equals(contra2)!=true){
            JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN, VERIFIQUE E INTENTE DE NUEVO");
        }
        else {
            Usuario u = new Usuario (listaUsuarios.size()+"", nom, usuario, ced, correo, contra, listaGeneros());
            listaUsuarios.add(u);
            reiniciarCamposUsuario();
            JOptionPane.showMessageDialog(null, "SE REGISTRÓ CORRECTAMENTE AL USUARIO: "+usuario);
            System.out.println(listaUsuarios.getLast().toString());
        }
    }
    
    public ArrayList <Genero> listaGeneros(){
        ArrayList <Genero> aux = new ArrayList <>();
        
        if (this.frmUsuario.chkComedia.isSelected()){
            aux.add(Genero.COMEDIA);
        }
        if (this.frmUsuario.chkAccion.isSelected()){
            aux.add(Genero.ACCION);
        }
        if (this.frmUsuario.chkDrama.isSelected()){
            aux.add(Genero.DRAMA);
        }
        if (this.frmUsuario.chkTerror.isSelected()){
            aux.add(Genero.TERRROR);
        }
        if (this.frmUsuario.chkRomance.isSelected()){
            aux.add(Genero.ROMANCE);
        }
        if (this.frmUsuario.chkSuspenso.isSelected()){
            aux.add(Genero.SUSPENSO);
        }
        if (this.frmUsuario.chkCienciaFiccion.isSelected()){
            aux.add(Genero.CIENCIAFICCION);
        }
        
        return aux;
    }
    
    //public Pelicula(String titulo, Genero genero, double duracion, int año, String director, double calificacionProm, String sinopsis)
    
    private void crearPeliculas (){
        listaPeliculas.add(new Pelicula ("Scary movie", Genero.TERRROR, 2, 2000, "Cris Evans", 0, "Una pelicula de miedo"));
        listaPeliculas.add(new Pelicula("Titanic", Genero.ROMANCE, 3.2, 1997, "James Cameron", 0, "Historia de amor en el famoso transatlantico."));
        listaPeliculas.add(new Pelicula("Vengadores: Endgame", Genero.ACCION, 3.0, 2019, "Anthony Russo", 0, "Los heroes enfrentan a Thanos."));
        listaPeliculas.add(new Pelicula("El caballero de la noche", Genero.ACCION, 2.5, 2008, "Christopher Nolan", 0, "Batman lucha contra el Joker."));
        listaPeliculas.add(new Pelicula("Forrest Gump", Genero.DRAMA, 2.3, 1994, "Robert Zemeckis", 0, "La extraordinaria vida de Forrest."));
        listaPeliculas.add(new Pelicula("Interestelar", Genero.CIENCIAFICCION, 2.8, 2014, "Christopher Nolan", 0, "Viaje espacial para salvar a la humanidad."));
        listaPeliculas.add(new Pelicula("El conjuro", Genero.TERRROR, 1.9, 2013, "James Wan", 0, "Una familia es aterrorizada por una entidad maligna."));
        listaPeliculas.add(new Pelicula("Diario de una pasion", Genero.ROMANCE, 2.0, 2004, "Nick Cassavetes", 0, "Una historia de amor inolvidable."));
        listaPeliculas.add(new Pelicula("Supercool", Genero.COMEDIA, 1.9, 2007, "Greg Mottola", 0, "Dos amigos viven una noche caotica."));
        listaPeliculas.add(new Pelicula("El origen", Genero.CIENCIAFICCION, 2.4, 2010, "Christopher Nolan", 0, "Un experto invade los sueños."));
        listaPeliculas.add(new Pelicula("Guason", Genero.DRAMA, 2.0, 2019, "Todd Phillips", 0, "El origen del villano mas famoso de Gotham."));
        listaPeliculas.add(new Pelicula("Parasitos", Genero.SUSPENSO, 2.1, 2019, "Bong Joon-ho", 0, "Dos familias de clases sociales opuestas se cruzan."));
        listaPeliculas.add(new Pelicula("Mision imposible: Repercusion", Genero.ACCION, 2.3, 2018, "Christopher McQuarrie", 0, "Ethan Hunt intenta detener una amenaza global."));
        listaPeliculas.add(new Pelicula("¿Que paso ayer?", Genero.COMEDIA, 1.8, 2009, "Todd Phillips", 0, "Una despedida de soltero fuera de control."));
        listaPeliculas.add(new Pelicula("Un lugar en silencio", Genero.TERRROR, 1.7, 2018, "John Krasinski", 0, "Una familia debe vivir sin hacer ruido."));
        listaPeliculas.add(new Pelicula("La La Land", Genero.ROMANCE, 2.1, 2016, "Damien Chazelle", 0, "Dos artistas persiguen sus sueños."));
        listaPeliculas.add(new Pelicula("Matrix", Genero.CIENCIAFICCION, 2.3, 1999, "Lana Wachowski", 0, "Un hombre descubre la verdad sobre su realidad."));
        listaPeliculas.add(new Pelicula("La isla siniestra", Genero.SUSPENSO, 2.3, 2010, "Martin Scorsese", 0, "Un detective investiga una desaparicion."));
        listaPeliculas.add(new Pelicula("En busca de la felicidad", Genero.DRAMA, 2.0, 2006, "Gabriele Muccino", 0, "Un padre lucha por darle un mejor futuro a su hijo."));
        listaPeliculas.add(new Pelicula("Deadpool", Genero.COMEDIA, 1.8, 2016, "Tim Miller", 0, "Un antiheroe sarcastico busca venganza."));
    }
    
    private void crearBotonesPeliculas (){
        this.frmPrincipal.panelPeliculas.removeAll();
        JButton [] peliculas = new javax.swing.JButton[listaPeliculas.size()];
        
        for (Pelicula p: listaPeliculas){
            JButton btn = new JButton (p.getTitulo());
            this.frmPrincipal.panelPeliculas.add(btn);
            btn.addActionListener(e -> { calificarPelicula (p.getTitulo());
                });
        }
        
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public void buscarGenero (){
        String genero = this.frmPrincipal.cmbGeneros.getSelectedItem().toString();
        this.frmPrincipal.panelPeliculas.removeAll();
        
        for (Pelicula p: listaPeliculas){
            if (p.getGenero() == Genero.valueOf(genero)){
                JButton btn = new JButton (p.getTitulo());
                this.frmPrincipal.panelPeliculas.add(btn);
                 btn.addActionListener(e -> { calificarPelicula (p.getTitulo());
                    });
            }
        }
        
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public void calificarPelicula (String nomPelicula){
        this.frmCalificar.setVisible(true);
        this.frmCalificar.setLocationRelativeTo(null);
        this.frmCalificar.lblPelicula1.setText(nomPelicula);
    }
    
    public Pelicula buscarPelicula (String nomPelicula){
            for (Pelicula p: listaPeliculas){
                if (p.getTitulo().equals(nomPelicula)){
                    return p;
                }
            }
        return null;
    }
    
    // btn.addActionListener(e -> seleccionarPuesto(num_puesto, numeroPuesto));
    
    public void reiniciarCamposUsuario (){
        this.frmUsuario.txtNom.setText("");
        this.frmUsuario.txtCedula.setText("");
        this.frmUsuario.txtCorreo.setText("");
        this.frmUsuario.txtContraseña.setText("");
        this.frmUsuario.txtVerContraseña.setText("");
    }
    
}
