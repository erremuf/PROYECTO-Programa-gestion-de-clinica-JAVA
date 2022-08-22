/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.clinicaAepi.forms;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import com.clinicaAepi.interfaces.ILogs;

/**
 *
 * Esta clase muestra el fichero txt que contiene la información de los aciertos generedos al insertar pacientes.
 * Muestra el contenido del fichero "aciertos.txt" y permite borrarlo y dejarlo vacío.
 * Implementa la interfaz ILogs y sus métodos mostrar() y borrarFichero()
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 * @see ILogs
 * 
 */
public class AciertosLogs extends javax.swing.JFrame implements ILogs {

    // Variables de la clase
    File fichero = new File("aciertos.txt");

    /**
     * Constructor por defecto de la clase. Muestra los componentes del JFrame al ejecutar el programa
     * y ejecuta desde el inicio el método sobreescrito mostrar() para que la información se visualice nada mas abrir el JFrame.
     */
    public AciertosLogs() {
        initComponents();
        mostrar();
    }
    
    /**
     * Método implementado de la Interfaz ILogs que borra el contenido del fichero. 
     * Mediante un BufferedWriter y un objeto FileWriter como parametro (Objeto que recibe a su vez el fichero
     * y el parametro false para que borre todo el contenido actual cada vez que escribe), escribe un String vacío
     * y cierra el buffer.
     * @see BufferedWriter
     * @see FileWriter
     */
    @Override
    public void borrarFichero() {
        try ( BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, false))) {
            bw.write(" ");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método implementado de la Interfaz ILogs que lee y muestra el contenido del fichero. 
     * Mediante un BufferedReader y un objeto FileReader como parametro (Objeto que recibe a su vez el fichero en cuestión),
     * guarda a través de un bucle while la información a una lista. El bucle funciona siempre que el método readLine() de BufferedReader
     * no devuelva null.
     * Por último asigna la lista que contiene todo la información leída a nuestro JListt. 
     * @see BufferedReader
     * @see FileReader

     */
    @Override
    public void mostrar() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            DefaultListModel modelo = new DefaultListModel();
            String info;
            while ((info = br.readLine()) != null) {
                modelo.addElement(info);
            }
            lstAciertos.setModel(modelo);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "¡Fichero no encontrado!", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la aplicación", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        lstAciertos = new javax.swing.JList<>();
        btnBorrar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        etiAciertos = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane2.setViewportView(lstAciertos);

        btnBorrar.setText("Borrar fichero");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        etiAciertos.setText("Registro de los pacientes insertados:");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(130, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBorrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(etiAciertos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /**
     * Método para que el programa no se cierre al cancelar y cierre simplemente la ventana actual.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Evento ActionPerformed que que ejecuta el método borrarFichero().
     * Al clickar el botón "borrar fichero" (btnBorrar) ejecuta el método.
     * @see Trabaja con el método borrarFichero().
     */
    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        borrarFichero();
    }//GEN-LAST:event_btnBorrarActionPerformed

    /**
     * Evento ActionPerformed que que ejecuta el método mostrar().
     * Al clickar el botón "Actualizar" (btnActualizar) ejecuta el método. Como la información se muestra desde
     * un inicio, este evento se utiliza bascamente para refrescar el JFrame cuando borras el fichero.
     * @see Trabaja con el método mostrar().
     */
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        mostrar();
    }//GEN-LAST:event_btnActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(AciertosLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AciertosLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AciertosLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AciertosLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AciertosLogs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel etiAciertos;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstAciertos;
    // End of variables declaration//GEN-END:variables
}
