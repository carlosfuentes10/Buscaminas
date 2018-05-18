package Codigo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javafx.scene.control.Alert;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jp
 */
public class ventanaBuscaminas extends javax.swing.JFrame {

    int filas = 15;
    int columnas = 20;
    int numeroMinas =50;
    Icon mina = new ImageIcon(getClass().getResource("/imagenes/buscaminas.png"));
    Icon bandera = new ImageIcon(getClass().getResource("/imagenes/bandera.jpg"));

    Boton[][] arrayBotones = new Boton[filas][columnas];

    /**
     * Creates new form ventanaBuscaminas
     */
    public ventanaBuscaminas() {
        initComponents();
        setSize(580, 470);
        jDialog1.setSize(530,310);
        jDialog1.setLocationRelativeTo(null);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new GridLayout(filas, columnas));
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Boton boton = new Boton(i, j);
                getContentPane().add(boton);
                arrayBotones[i][j] = boton;
                boton.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent evt) {
                        botonPulsado(evt);
                    }
                });

            }
        }
        ponMinas(30);
        cuentaMinas();
         

    }

    private void botonPulsado(MouseEvent e) {
        
        Boton miBoton = (Boton) e.getComponent();
        if (e.getButton() == MouseEvent.BUTTON3 && miBoton.isEnabled()) {
            miBoton.setIcon(bandera);
        

        }
        if (e.getButton() == MouseEvent.BUTTON1 && miBoton.getText().equals("")) {
            if (miBoton.getMina() == 1) {
                miBoton.setIcon(mina);
                miBoton.setOpaque(true);
                miBoton.setBackground(Color.red);
                Perdiste();
//         JOptionPane.showMessageDialog(null, "GAME OVER"),JOptionPane;
                jDialog1.setVisible(true);
               
            } else if (miBoton.getNumeroMinasAlrededor() == 0) {
                miBoton.setFocusPainted(false);
                expandir(miBoton);
                
            } else {
                miBoton.setImagen(miBoton.getNumeroMinasAlrededor());
                miBoton.setEnabled(true);
               
               
                

            }

        }
    }

    private void expandir(Boton boton) {
        if (boton.getNumeroMinasAlrededor() == 0) {
            boton.setEnabled(false);
            for (int k = -1; k < 2; k++) {
                for (int m = -1; m < 2; m++) {
                    if ((boton.getI() + k >= 0) && (boton.getJ() + m >= 0) && (boton.getI() + k < filas) && (boton.getJ() + m < columnas)) {
                        if (arrayBotones[boton.getI() + k][boton.getJ() + m].isEnabled()) {
                            if (arrayBotones[boton.getI() + k][boton.getJ() + m].getNumeroMinasAlrededor() == 0) {
                                arrayBotones[boton.getI() + k][boton.getJ() + m].setEnabled(false);
                                expandir(arrayBotones[boton.getI() + k][boton.getJ() + m]);

                            }else
                            {arrayBotones[boton.getI() + k][boton.getJ() + m].setEnabled(true);
                             arrayBotones[boton.getI() + k][boton.getJ() + m]
                             .setImagen(arrayBotones[boton.getI() + k][boton.getJ() + m].getNumeroMinasAlrededor());
                                
                            }
                        }
                    }
                }
            }

        }
    }
    
private void Perdiste(){
       
        for(int i=0; i<filas; i++){
            for(int j=0;j<columnas; j++){
                
                if(arrayBotones[i][j].getMina() == 0){
                    if(arrayBotones[i][j].getNumeroMinasAlrededor()!=0){
                  arrayBotones[i][j].setImagen(arrayBotones[i][j].getNumeroMinasAlrededor());
                
                    }
                }
                else {
                   arrayBotones[i][j].setIcon(mina);
                 
                }

               arrayBotones[i][j].setEnabled(true);
                
                
            
                
            }
        }

}
    
        
    
 



    

    private void ponMinas(int numeroMinas) {
        Random r = new Random();
        for (int i = 0; i < numeroMinas; i++) {
            int f = r.nextInt(filas);
            int c = r.nextInt(columnas);
            //TODO hay q hacer una version q chequee si en las casillas seleccionadas
            //ya hay uan mina, pq en ese caso tiene que buscar otra
            arrayBotones[f][c].setMina(1);

        }
    }

    //cuenta minas es un metodo q para cada boton calcula el numero de minas que hay alrededor
    private void cuentaMinas() {
        //TODO falta por hacert q pille las de los extremos 
        int minas = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                for (int k = -1; k < 2; k++) {
                    for (int m = -1; m < 2; m++) {
                        if ((i + k >= 0) && (j + m >= 0) && (i + k < filas) && (j + m < columnas)) {
                            minas = minas + arrayBotones[i + k][j + m].getMina();
                        }
                    }
                }

                arrayBotones[i][j].setNumeroMinasAlrededor(minas);

                //TODO comentar la siguiente parte para que no aparezcan los numeros al iniciar la partida
//                if (arrayBotones[i][j].isEnabled()) {
//                    if (arrayBotones[i][j].getMina() == 0) {
//                        arrayBotones[i][j].setText(String.valueOf(minas));
//                    }
//                }
                minas = 0;
                
//                if(arrayBotones[i][j].getMina() == 1 ){
//                    arrayBotones[i][j].setText("");
//                }
//                if(arrayBotones[i][j].getNumeroMinasAlrededor() == 0){
//                    arrayBotones[i][j].setEnabled(false);
//                }
            }
        }

    }
    
    private void Reiniciar(){
        ventanaBuscaminas reset = new ventanaBuscaminas();
        reset.setVisible(true);
        dispose();
    }
    
    private void Close(){
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jDialog1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Sigue al Cono @conoufv");
        jDialog1.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 140, -1));

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setText("REINICIAR");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jDialog1.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/game.png"))); // NOI18N
        jDialog1.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 629, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        Reiniciar();
        
    }//GEN-LAST:event_jButton1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaBuscaminas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaBuscaminas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
