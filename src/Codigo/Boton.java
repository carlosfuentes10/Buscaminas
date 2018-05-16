package Codigo;

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

    public int geti() {
        return i;
    }

    public int getj() {
        return j;
    }
    
    
}
