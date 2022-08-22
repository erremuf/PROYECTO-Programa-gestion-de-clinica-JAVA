/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.clinicaAepi;

import com.clinicaAepi.clases.Tarea;
import com.clinicaAepi.forms.AciertosLogs;
import com.clinicaAepi.forms.AciertosLogs;
import com.clinicaAepi.forms.Editar;
import com.clinicaAepi.forms.Insertar;
import com.clinicaAepi.forms.Visualizar;
import com.clinicaAepi.forms.Eliminar;
import com.clinicaAepi.forms.ErroresLogs;
import com.clinicaAepi.forms.ContadorRegistros;
import com.clinicaAepi.forms.Editar;
import com.clinicaAepi.forms.Eliminar;
import com.clinicaAepi.forms.ErroresLogs;
import com.clinicaAepi.forms.Insertar;
import com.clinicaAepi.forms.Visualizar;


/**
 * Esta es clase principal de nuestro programa.
 * Se encarga de facilitarnos un menu enlazado a todas nuestras demás clases.
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 */
public class Inicio extends javax.swing.JFrame {
    
    // Instanciamos el objeto Tarea que se encargará de iniciar el método tarea() para hacer la tarea repetitiva.
    Tarea tarea = new Tarea();
    
    /**
     * Constructor por defecto de la clase. Muestra los componentes del JFrame al ejecutar el programa
     * la tarea TimerTask.
     */
    public Inicio() {
        initComponents();
        tarea.tarea();
    }
    // Cierre de constructor


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        menuMenu = new javax.swing.JMenu();
        menuItemInsertar = new javax.swing.JMenuItem();
        menuItemVisualizar = new javax.swing.JMenuItem();
        menuItemEditar = new javax.swing.JMenuItem();
        menuItemEliminar = new javax.swing.JMenuItem();
        menuLogs = new javax.swing.JMenu();
        menuItemAciertos = new javax.swing.JMenuItem();
        menuItemErrores = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuMenu.setText("Menu");

        menuItemInsertar.setText("Insertar");
        menuItemInsertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemInsertarActionPerformed(evt);
            }
        });
        menuMenu.add(menuItemInsertar);

        menuItemVisualizar.setText("Visualizar");
        menuItemVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemVisualizarActionPerformed(evt);
            }
        });
        menuMenu.add(menuItemVisualizar);

        menuItemEditar.setText("Editar");
        menuItemEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEditarActionPerformed(evt);
            }
        });
        menuMenu.add(menuItemEditar);

        menuItemEliminar.setText("Eliminar");
        menuItemEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemEliminarActionPerformed(evt);
            }
        });
        menuMenu.add(menuItemEliminar);

        menuLogs.setText("Logs");

        menuItemAciertos.setText("Aciertos");
        menuItemAciertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemAciertosActionPerformed(evt);
            }
        });
        menuLogs.add(menuItemAciertos);

        menuItemErrores.setText("Errores");
        menuItemErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemErroresActionPerformed(evt);
            }
        });
        menuLogs.add(menuItemErrores);

        menuMenu.add(menuLogs);

        jMenuBar1.add(menuMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento ActionPerformed que nos permite navegar hasta la clase Insertar.
     * Instancia un objeto de la clase Insertar y le pasa a su método setVisible un true como parámetro.
     * @see Trabaja con la clase Insertar y su método setVisible().
     */
    private void menuItemInsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemInsertarActionPerformed
        Insertar insertar = new Insertar();
        insertar.setVisible(true);
    }//GEN-LAST:event_menuItemInsertarActionPerformed

    /**
     * Evento ActionPerformed que nos permite navegar hasta la clase Visualizar.
     * Instancia un objeto de la clase Visualizar y le pasa a su método setVisible un true como parámetro.
     * @see Trabaja con la clase Visualizar y su método setVisible().
     */
    private void menuItemVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemVisualizarActionPerformed
        Visualizar visualizar = new Visualizar();
        visualizar.setVisible(true);
    }//GEN-LAST:event_menuItemVisualizarActionPerformed

    /**
     * Evento ActionPerformed que nos permite navegar hasta la clase Editar.
     * Instancia un objeto de la clase Editar y le pasa a su método setVisible un true como parámetro.
     * @see Trabaja con la clase Editar y su método setVisible().
     */
    private void menuItemEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEditarActionPerformed
        Editar editar = new Editar();
        editar.setVisible(true);
    }//GEN-LAST:event_menuItemEditarActionPerformed

    /**
     * Evento ActionPerformed que nos permite navegar hasta la clase Eliminar.
     * Instancia un objeto de la clase Eliminar y le pasa a su método setVisible un true como parámetro.
     * @see Trabaja con la clase Eliminar y su método setVisible().
     */
    private void menuItemEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemEliminarActionPerformed
        Eliminar eliminar = new Eliminar();
        eliminar.setVisible(true);
    }//GEN-LAST:event_menuItemEliminarActionPerformed

    /**
     * Evento ActionPerformed que nos permite navegar hasta la clase Aciertos.
     * Instancia un objeto de la clase Aciertos y le pasa a su método setVisible un true como parámetro.
     * @see Trabaja con la clase Aciertos y su método setVisible().
     */
    private void menuItemAciertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemAciertosActionPerformed
        AciertosLogs aciertos = new AciertosLogs();
        aciertos.setVisible(true);
    }//GEN-LAST:event_menuItemAciertosActionPerformed

    /**
     * Evento ActionPerformed que nos permite navegar hasta la clase Errores.
     * Instancia un objeto de la clase Errores y le pasa a su método setVisible un true como parámetro.
     * @see Trabaja con la clase Errores y su método setVisible().
     */
    private void menuItemErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemErroresActionPerformed
        ErroresLogs errores = new ErroresLogs();
        errores.setVisible(true);
    }//GEN-LAST:event_menuItemErroresActionPerformed


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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuItemAciertos;
    private javax.swing.JMenuItem menuItemEditar;
    private javax.swing.JMenuItem menuItemEliminar;
    private javax.swing.JMenuItem menuItemErrores;
    private javax.swing.JMenuItem menuItemInsertar;
    private javax.swing.JMenuItem menuItemVisualizar;
    private javax.swing.JMenu menuLogs;
    private javax.swing.JMenu menuMenu;
    // End of variables declaration//GEN-END:variables
}
