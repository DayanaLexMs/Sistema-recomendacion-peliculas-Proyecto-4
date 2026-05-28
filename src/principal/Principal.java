/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;
import vista.*;
import controlador.ControladorSistema;

/**
 *
 * @author USUARIO
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFNotFlix frmPrincipal = new JFNotFlix ();
        JFCalificar frmCalificar = new JFCalificar ();
        JFRegistroUsuario frmUsuario = new JFRegistroUsuario ();
        ControladorSistema cntrl = new ControladorSistema (frmPrincipal, frmUsuario, frmCalificar);
        frmPrincipal.setVisible(true);
        frmPrincipal.setLocationRelativeTo(null);
        
    }
    
}
