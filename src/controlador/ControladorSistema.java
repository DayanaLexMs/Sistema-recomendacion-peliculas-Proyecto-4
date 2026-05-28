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

/**
 *
 * @author USUARIO
 */
public class ControladorSistema implements ActionListener {
    
    JFRegistroUsuario frmUsuario = new JFRegistroUsuario ();
    JFNotFlix frmPrincipal = new JFNotFlix ();
    JFCalificar frmCalificar = new JFCalificar ();

    public ControladorSistema() {
    }
    
    public ControladorSistema (JFNotFlix frmPrincipal, JFRegistroUsuario frmUsuario, JFCalificar frmCalificar) {
        this.frmPrincipal = frmPrincipal;
        this.frmUsuario = frmUsuario;
        this.frmCalificar = frmCalificar;
        
    }
    
    @Override
    public void actionPerformed (ActionEvent e){
        
        
        
    }
    
}
