package Codigo;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Jp
 */
public class Boton extends JButton{
    
    private int mina = 0;
    private int i = 0;
    private int j = 0;
    private int numeroMinasAlrededor = 0;
    
    
    
    
    
    
    public Boton (int _i, int _j){
        i = _i;
        j = _j;
        this.setBorder(null);
        this.setOpaque(false);
       this.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        this.setBorderPainted(false);
        this.setBackground(Color.GRAY);
        this.setFocusPainted(false);
        
    }

    public int getMina() {
        return mina;
    }

    public void setMina(int mina) {
        this.mina = mina;
    }

    public int getNumeroMinasAlrededor() {
        return numeroMinasAlrededor;
    }

    public void setNumeroMinasAlrededor(int numeroMinasAlrededor) {
        this.numeroMinasAlrededor = numeroMinasAlrededor;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    public void setImagen(int num){   
        String nombre = "";
        setEnabled(true);
        switch (num){
            case 0: nombre = "blank"; break;
            case 1: nombre = "number1"; break;
            case 2: nombre = "number2"; break;
            case 3: nombre = "number3"; break;
            case 4: nombre = "number4"; break;
            case 5: nombre = "number5"; break;
            case 6: nombre = "number6"; break;
            case 7: nombre = "number7"; break;
            case 8: nombre = "number8"; break;
            case 9: nombre = "flag"; break;
            case 10: nombre = "exposed"; break;
            
        }
        String ruta = "/imagenes/" + nombre + ".png";
        this.setIcon(new ImageIcon(getClass().getResource(ruta)));
    }
}
