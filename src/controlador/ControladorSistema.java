/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.awt.*;
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
    ArrayList <VerPelicula> listaCalificaciones;

    public ControladorSistema() {
    }
    
    public ControladorSistema (JFNotFlix frmPrincipal, JFRegistroUsuario frmUsuario, JFCalificar frmCalificar) {
        this.frmPrincipal = frmPrincipal;
        this.frmUsuario = frmUsuario;
        this.frmCalificar = frmCalificar;
        this.listaUsuarios = new ArrayList <>();
        this.listaPeliculas = new ArrayList <> ();
        this.listaCalificaciones = new ArrayList <> ();
        
        this.frmUsuario.btnRegistrarse.addActionListener(this);
        this.frmPrincipal.btnFrmRegistrarse.addActionListener(this);
        this.frmPrincipal.btnBuscarGenero.addActionListener(this);
        this.frmPrincipal.btnIniciarSesion.addActionListener(this);
        this.frmPrincipal.btnRecomendar.addActionListener(this);
        this.frmCalificar.btnEnviarCalificacion.addActionListener(this);
        this.frmPrincipal.btnBuscarDirector.addActionListener(this);
        this.frmPrincipal.btnRecomCalificaciones.addActionListener(this);
        this.frmPrincipal.btnMejorCalif.addActionListener(this);
        this.frmPrincipal.btnBuscarTitulo.addActionListener(this);
        
        this.frmPrincipal.panelPeliculas.setLayout(new GridLayout(0, 5, 10, 10));
        crearPeliculas ();
        crearBotonesPeliculas ();
        llenarComboGeneros ();
        llenarComboDirectores ();
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
        
        if (e.getSource()==this.frmPrincipal.btnIniciarSesion){
            iniciarSesion ();
        }
        
        if (e.getSource()==this.frmPrincipal.btnRecomendar){
            actualizarBotones ();
        }
        
        if (e.getSource()==this.frmCalificar.btnEnviarCalificacion){
            enviarCalificacion ();
        }
        
        if (e.getSource()==this.frmPrincipal.btnBuscarDirector){
            buscarDirector ();
        }
        
        if (e.getSource()==this.frmPrincipal.btnRecomCalificaciones){
            recomendacionesCalificaciones ();
        }
        
        if (e.getSource()==this.frmPrincipal.btnMejorCalif){
            mejorCalif ();
        }
        
        if (e.getSource()==this.frmPrincipal.btnBuscarTitulo){
            buscarTitulo ();
        }
    }
    
    private void llenarComboDirectores() {
        this.frmPrincipal.cmbDirectores.removeAllItems();
        ArrayList<String> directores = new ArrayList<>();
        
        this.frmPrincipal.cmbDirectores.addItem("TODOS");
        
        for (Pelicula p : listaPeliculas) {
            if (!directores.contains(p.getDirector())){
                directores.add(p.getDirector());
                this.frmPrincipal.cmbDirectores.addItem(p.getDirector());
            }
        }
    }
    
    private void llenarComboGeneros (){
        for (Genero g : Genero.values()) {
            this.frmPrincipal.cmbGeneros.addItem(g.toString());
        }
    }
    
    public void registrarUsuario (){
        
        String nom = this.frmUsuario.txtNom.getText();
        String ced = this.frmUsuario.txtCedula.getText();
        String correo = this.frmUsuario.txtCorreo.getText();
        String usuario = this.frmUsuario.txtUsuario.getText();
        String contra = this.frmUsuario.txtContraseña.getText();
        String contra2 = this.frmUsuario.txtVerContraseña.getText();
        
        for (Usuario u: listaUsuarios){
            if(u.getNomUsuario().equals(usuario)){
                JOptionPane.showMessageDialog(null, "NOMBRE DE USUARIO YA REGISTRADO");
                return;
            }
        }
        
        if (nom.isEmpty()||ced.isEmpty()||correo.isEmpty()||contra.isEmpty()||contra2.isEmpty()||usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "DIGITE LOS DATOS COMPLETOS PARA REALIZAR EL REGISTRO");
            reiniciarCamposUsuario();
        }
        else if (contra.equals(contra2)!=true){
            JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS NO COINCIDEN, VERIFIQUE E INTENTE DE NUEVO");
        }
        else if (usuario.equals("Usuario")){
            JOptionPane.showMessageDialog(null, "NOMBRE DE USUARIO INVALIDO");
        }
        else {
            Usuario u = new Usuario (listaUsuarios.size()+"", nom, usuario, ced, correo, contra, listaGeneros());
            listaUsuarios.add(u);
            reiniciarCamposUsuario();
            JOptionPane.showMessageDialog(null, "SE REGISTRÓ CORRECTAMENTE AL USUARIO: "+usuario);
            System.out.println(listaUsuarios.getLast().toString());
            this.frmUsuario.dispose();
        }
    }
    
    public void iniciarSesion (){
        if (listaUsuarios.isEmpty()){
            JOptionPane.showMessageDialog(null, "REGISTRESE ANTES DE INICIAR SESION");
            this.frmUsuario.setVisible(true);
            this.frmUsuario.setLocationRelativeTo(null);
            return;
        }
        
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario:");
        String contraseña = JOptionPane.showInputDialog("Ingrese su contraseña:");
        
        if (usuario.isEmpty()||contraseña.isEmpty()){
            JOptionPane.showMessageDialog(null, "POR FAVOR DIGITE LOS DATOS COMPLETOS E INTENTE DE NUEVO");
        }
        
        else {
            for (Usuario u: listaUsuarios){
                if (u.getNomUsuario().equals(usuario)){
                    if (u.getContraseña().equals(contraseña)){
                        this.frmPrincipal.lblUsuario.setText(usuario);
                    }
                }
            }
            actualizarBotones();
        }
    }
    
    public void buscarDirector (){
        String director = this.frmPrincipal.cmbDirectores.getSelectedItem().toString();
        this.frmPrincipal.panelPeliculas.removeAll();
        
        if (director.equals("TODOS")){
            crearBotonesPeliculas();
            return;
        }
        
        for (Pelicula p: listaPeliculas){
            if (p.getDirector().equals(director)){
                this.frmPrincipal.panelPeliculas.add(crearBoton(p));
            }
        }
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public JButton crearBoton(Pelicula p){
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource(p.getPoster()));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(150, 225, Image.SCALE_SMOOTH);

        JButton btn = new JButton(new ImageIcon(imagenEscalada));

        btn.addActionListener(e -> calificarPelicula(p.getTitulo()));
        btn.setPreferredSize(new Dimension(160,235));
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    return btn;
    }
    
    public void recomendacionesCalificaciones (){
        Usuario usu = buscarUsuario (this.frmPrincipal.lblUsuario.getText());
        this.frmPrincipal.panelPeliculas.removeAll();
        
        if (usu==null){
            JOptionPane.showMessageDialog(null, "POR FAVOR INICIE SESIÓN PRIMERO");
            iniciarSesion();
            return;
        }
        for (Pelicula p: listaPeliculas){
            for (VerPelicula v: listaCalificaciones){
                if (v.getCalificacion()>=4){
                    if (p.getGenero()==v.getPelicula().getGenero()){
                        this.frmPrincipal.panelPeliculas.add(crearBoton(p));
                    }
                }
            }
        }
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public void mejorCalif (){
        ArrayList <Pelicula> copiaPeliculas = new ArrayList <>();
        this.frmPrincipal.panelPeliculas.removeAll();
        for (Pelicula p: listaPeliculas){
            copiaPeliculas.add(p);
        }
        copiaPeliculas.sort((p1, p2) -> Double.compare(p2.getCalificacionProm(),p1.getCalificacionProm()));
        for (Pelicula p: copiaPeliculas){
            this.frmPrincipal.panelPeliculas.add(crearBoton(p));
        }
    }
    
    public void actualizarBotones (){
        Usuario usu = buscarUsuario (this.frmPrincipal.lblUsuario.getText());
        
        if (usu==null){
            JOptionPane.showMessageDialog(null, "POR FAVOR INICIE SESIÓN PRIMERO");
            iniciarSesion();
            return;
        }
        
        this.frmPrincipal.panelPeliculas.removeAll();
        for (Genero g: usu.getPreferencias()){
            for (Pelicula p: listaPeliculas){
                if (p.getGenero().equals(g)){
                    this.frmPrincipal.panelPeliculas.add(crearBoton(p));
                }
            }
        }
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    private void crearBotonesPeliculas (){
        this.frmPrincipal.panelPeliculas.removeAll();
        this.frmPrincipal.panelPeliculas.setLayout(new GridLayout(0, 5, 10, 10));
        
        for (Pelicula p: listaPeliculas){
            this.frmPrincipal.panelPeliculas.add(crearBoton(p));
        }
        
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public void buscarGenero (){
        String genero = this.frmPrincipal.cmbGeneros.getSelectedItem().toString();
        this.frmPrincipal.panelPeliculas.removeAll();
        
        if (genero.equals("TODOS")){
            crearBotonesPeliculas();
            return;
        }
        
        for (Pelicula p: listaPeliculas){
            if (p.getGenero() == Genero.valueOf(genero)){
                this.frmPrincipal.panelPeliculas.add(crearBoton(p));
            }
        }
        
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public void buscarTitulo (){
        String titulo = this.frmPrincipal.txtBusTitulo.getText();
        this.frmPrincipal.panelPeliculas.removeAll();
        
        if (titulo.isEmpty()){
            JOptionPane.showMessageDialog(null, "DIGITE EL NOMBRE DE LA PELICULA A BUSCAR");
            return;
        }
        
        if (buscarPelicula(titulo)==null){
            JOptionPane.showMessageDialog(null, "NO SE ENCONTRÓ LA PELICULA INGRESADA");
            return;
        }
        
        for (Pelicula p: listaPeliculas){
            if (p.getTitulo().equalsIgnoreCase(titulo)){
                this.frmPrincipal.panelPeliculas.add(crearBoton(p));
            }
        }
        
        this.frmPrincipal.panelPeliculas.revalidate();
        this.frmPrincipal.panelPeliculas.repaint();
    }
    
    public void calificarPelicula (String nomPelicula){
        
        if (buscarUsuario(this.frmPrincipal.lblUsuario.getText())==null){
            JOptionPane.showMessageDialog(null, "USUARIO NO ENCONTRADO, INICIE SESION ANTES DE CALIFICAR");
            iniciarSesion(); 
            return;
        }
        
        this.frmCalificar.setVisible(true);
        this.frmCalificar.setLocationRelativeTo(null);
        this.frmCalificar.lblPelicula1.setText(nomPelicula);
    }
    
    public void enviarCalificacion (){
        String comentario = this.frmCalificar.txtComentario.getText();
        
        Usuario u = buscarUsuario (this.frmPrincipal.lblUsuario.getText());
        Pelicula p = buscarPelicula (this.frmCalificar.lblPelicula1.getText());
        
        try {
            Double var = Double.valueOf(this.frmCalificar.txtCalificacion.getText());
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "EL VALOR DE LA CALIFICACION DEBE SER DE 1 A 5");
            reiniciarCamposCalificar();
            return;
        }
        
        try {
            Fecha fe = this.crearFecha(this.frmCalificar.txtFecha.getText());
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "DIGITE LA FECHA EN EL FORMATO DD/MM/YYYY");
            reiniciarCamposCalificar();
            return;
        }
        
        Double calificacion = Double.valueOf(this.frmCalificar.txtCalificacion.getText());
        Fecha fechaV = this.crearFecha(this.frmCalificar.txtFecha.getText());
        
        if (this.frmCalificar.txtCalificacion.getText().isBlank()||comentario.isBlank()||this.frmCalificar.txtFecha.getText().isBlank()){
            JOptionPane.showMessageDialog(null, "PORFAVOR LLENE TODOS LOS CAMPOS");
        }
        
        if (u==null){
                JOptionPane.showMessageDialog(null, "INICIE SESION ANTES DE CALIFICAR");
            return;
        }
        
        else {
            //(Usuario usuario, Pelicula pelicula, Fecha fechaVista, double calificacion, String comentario) 
            VerPelicula ver = new VerPelicula (u, p, fechaV, calificacion, comentario);
            listaCalificaciones.add(ver);
            actualizarCalificacion (p, calificacion);
            this.frmCalificar.dispose();
            JOptionPane.showMessageDialog(null, "SE REGISTRÓ LA CALIFICACION PARA LA PELICULA: "+this.frmCalificar.lblPelicula1.getText());
        }
        reiniciarCamposCalificar();
    }
    
    public void actualizarCalificacion (Pelicula pe, Double calificacion){
        Double total = 0.0;
        Double cant = 0.0;
        for (VerPelicula v: listaCalificaciones){
            if (v.getPelicula().getTitulo().equals(pe.getTitulo())){
                total += v.getCalificacion();
                cant += 1;
            }
        }
        Double calificacionProm = total/cant;
        pe.setCalificacionProm(calificacionProm);
    }
    
    public Fecha crearFecha (String fechaa){
        
        String [] fechas = fechaa.split("/");
        int [] fecha = new int [fechas.length];
        
        for (int i = 0; i<3; i++){
            fecha [i] = Integer.parseInt(fechas [i]);
        }
        
        Fecha f = new Fecha (fecha[0], fecha[1], fecha[2]);
        
        return f;
    }
    
    public Usuario buscarUsuario (String usuario){
        for (Usuario u: listaUsuarios){
            if (u.getNomUsuario().equals(usuario)){
                return u;
            }
        }
        return null;
    }
    
    public Pelicula buscarPelicula (String nomPelicula){
            for (Pelicula p: listaPeliculas){
                if (p.getTitulo().equalsIgnoreCase(nomPelicula)){
                    return p;
                }
            }
        return null;
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
    
    public void reiniciarCamposUsuario (){
        this.frmUsuario.txtNom.setText("");
        this.frmUsuario.txtCedula.setText("");
        this.frmUsuario.txtCorreo.setText("");
        this.frmUsuario.txtUsuario.setText("");
        this.frmUsuario.txtContraseña.setText("");
        this.frmUsuario.txtVerContraseña.setText("");
        this.frmUsuario.chkAccion.setSelected(false);
        this.frmUsuario.chkCienciaFiccion.setSelected(false);
        this.frmUsuario.chkComedia.setSelected(false);
        this.frmUsuario.chkDrama.setSelected(false);
        this.frmUsuario.chkRomance.setSelected(false);
        this.frmUsuario.chkSuspenso.setSelected(false);
        this.frmUsuario.chkTerror.setSelected(false);
    }
    
    public void reiniciarCamposCalificar (){
        this.frmCalificar.txtCalificacion.setText("");
        this.frmCalificar.txtComentario.setText("");
        this.frmCalificar.txtFecha.setText("");
    }
    
    private void crearPeliculas (){
        //(String titulo, Genero genero, double duracion, int año, String director, double calificacionProm, String sinopsis, String poster)
        listaPeliculas.add(new Pelicula("Scary movie", Genero.TERRROR, 2, 2000, "Cris Evans", 0, "Una pelicula de miedo","/Imagenes/ScaryMovie.png"));
        listaPeliculas.add(new Pelicula("Titanic", Genero.ROMANCE, 3.2, 1997, "James Cameron", 0, "Historia de amor en el famoso transatlantico.","/Imagenes/Titanic.jpg"));
        listaPeliculas.add(new Pelicula("Vengadores: Endgame", Genero.ACCION, 3.0, 2019, "Anthony Russo", 0, "Los heroes enfrentan a Thanos.","/Imagenes/VengadoresEndGame.jpg"));
        listaPeliculas.add(new Pelicula("El caballero de la noche", Genero.ACCION, 2.5, 2008, "Christopher Nolan", 0, "Batman lucha contra el Joker.","/Imagenes/CaballeroNoche.png"));
        listaPeliculas.add(new Pelicula("Forrest Gump", Genero.DRAMA, 2.3, 1994, "Robert Zemeckis", 0, "La extraordinaria vida de Forrest.","/Imagenes/ForrestGump.jpg"));
        listaPeliculas.add(new Pelicula("Interestelar", Genero.CIENCIAFICCION, 2.8, 2014, "Christopher Nolan", 0, "Viaje espacial para salvar a la humanidad.","/Imagenes/Interestelar.jpg"));
        listaPeliculas.add(new Pelicula("El conjuro", Genero.TERRROR, 1.9, 2013, "James Wan", 0, "Una familia es aterrorizada por una entidad maligna.","/Imagenes/ElConjuro.jpg"));
        listaPeliculas.add(new Pelicula("Diario de una pasion", Genero.ROMANCE, 2.0, 2004, "Nick Cassavetes", 0, "Una historia de amor inolvidable.","/Imagenes/DiarioPasion.jpg"));
        listaPeliculas.add(new Pelicula("Supercool", Genero.COMEDIA, 1.9, 2007, "Greg Mottola", 0, "Dos amigos viven una noche caotica.","/Imagenes/Supercool.jpg"));
        listaPeliculas.add(new Pelicula("El origen", Genero.CIENCIAFICCION, 2.4, 2010, "Christopher Nolan", 0, "Un experto invade los sueños.","/Imagenes/Origen.jpg"));
        listaPeliculas.add(new Pelicula("Guason", Genero.DRAMA, 2.0, 2019, "Todd Phillips", 0, "El origen del villano mas famoso de Gotham.","/Imagenes/Guason.jpg"));
        listaPeliculas.add(new Pelicula("Parasitos", Genero.SUSPENSO, 2.1, 2019, "Bong Joon-ho", 0, "Dos familias de clases sociales opuestas se cruzan.","/Imagenes/Parasitos.png"));
        listaPeliculas.add(new Pelicula("Mision imposible: Repercusion", Genero.ACCION, 2.3, 2018, "Christopher McQuarrie", 0, "Ethan Hunt intenta detener una amenaza global.","/Imagenes/MisionImposible.jpg"));
        listaPeliculas.add(new Pelicula("¿Que paso ayer?", Genero.COMEDIA, 1.8, 2009, "Todd Phillips", 0, "Una despedida de soltero fuera de control.","/Imagenes/QuePasoAyer.jpg"));
        listaPeliculas.add(new Pelicula("Un lugar en silencio", Genero.TERRROR, 1.7, 2018, "John Krasinski", 0, "Una familia debe vivir sin hacer ruido.","/Imagenes/UnLugarSilencio.jpg"));
        listaPeliculas.add(new Pelicula("La La Land", Genero.ROMANCE, 2.1, 2016, "Damien Chazelle", 0, "Dos artistas persiguen sus sueños.","/Imagenes/Lalaland.png"));
        listaPeliculas.add(new Pelicula("Matrix", Genero.CIENCIAFICCION, 2.3, 1999, "Lana Wachowski", 0, "Un hombre descubre la verdad sobre su realidad.","/Imagenes/Matrix.jpg"));
        listaPeliculas.add(new Pelicula("La isla siniestra", Genero.SUSPENSO, 2.3, 2010, "Martin Scorsese", 0, "Un detective investiga una desaparicion.","/Imagenes/LaIslaSiniestra.jpg"));
        listaPeliculas.add(new Pelicula("En busca de la felicidad", Genero.DRAMA, 2.0, 2006, "Gabriele Muccino", 0, "Un padre lucha por darle un mejor futuro a su hijo.","/Imagenes/BuscaFelicidad.jpg"));
        listaPeliculas.add(new Pelicula("Deadpool", Genero.COMEDIA, 1.8, 2016, "Tim Miller", 0, "Un antiheroe sarcastico busca venganza.","/Imagenes/Deadpool.jpg"));
    }
}
