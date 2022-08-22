/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.clinicaAepi.forms;

import static java.awt.event.KeyEvent.VK_ENTER;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Esta clase permite eliminar en la base de datos el registro/peciente que se desee.
 * Primero busca en la base de datos el nombre y el apellido introducidos, comprueba que haya algún 
 * registro/paciente con esos datos o que no haya más de un paciente con el mismo nombre y apellidos
 * y procede a eliminar el registro. Si hay mas de un registro con el mismo nombre, solicita el DNI.
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 */
public class Eliminar extends javax.swing.JFrame {

    // Variables de la clase
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet;

    /**
     * Constructor por defecto de la clase.
     * Muestra los componentes del JFrame al ejecutar el programa.
     */
    public Eliminar() {
        initComponents();
    }
    // Cierre del constructor

    /**
     * Método que nos permite eliminar TODOS los registros/pacientes de la base de datos. 
     * Se conecta a la base de datos y selecciona todo para pasar a eliminarlo con la sentencia DELETE.
     * @see ResultSet
     * @see Connection
     */
    public void eliminarTodo() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        // indicamos PROTOCOLO (jdbc) : FABRICANTE (sqlite) : y RUTA a bbdd
        conexion = DriverManager.getConnection("jdbc:sqlite:pacientes.sqlite");
        // Eliminamos paciente de la tabla datos donde nombre y apellidos sean =?
        statement = conexion.prepareStatement("DELETE FROM datos");
        statement.executeUpdate();
        // Mostramos pantalla que avisa del re, "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE
        JOptionPane.showMessageDialog(rootPane, "¡Registro eliminado correctamente!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
        // Se cierra la coneccion.
        conexion.close();
    }

    /**
     * Método que nos permite eliminar un registro/paciente de la base de datos. 
     * Se conecta a la base de datos y selecciona todo, a través de la sentencia WHERE, lo que coincida
     * con el nombre y el apellido introducidos. (Los JTextField los tranforma a mayúsculas para hacer la búsqueda)
     * Si no encuentra registros con esos datos te devulve un mensaje por pantalla indicándolo.
     * Si encuentra solo un registro, lo elimina y te lo indica por pantalla.
     * Si encuentra mas de un resultado, te pide que introduzcas el campo DNI (Clave primaria en la base de datos)
     * para asegurarse de eliminar el paciente correcto.
     * Por último refresca los JTextField para que vuelvan a estar vacíos.
     * @see ResultSet
     * @see Connection
     */
    public void eliminarPaciente() {
        try {
            String nombre = txtNombre.getText().trim().toUpperCase();
            String apellidos = txtApellidos.getText().trim().toUpperCase();

            Class.forName("org.sqlite.JDBC");
            // indicamos PROTOCOLO (jdbc) : FABRICANTE (sqlite) : y RUTA a bbdd
            conexion = DriverManager.getConnection("jdbc:sqlite:pacientes.sqlite");
            // Seleccionamos de nuestra tabla en la BBDD lo que coincida con ese nombre y apellidos
            statement = conexion.prepareStatement("SELECT * FROM datos "
                    + "WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'");
            resultSet = statement.executeQuery();

            // Buscamos si hay mas pacientes con el mismo nombre
            int mismoNombre = 0;
            try {
                // Recorro el ResultSet que contiene los resultados.
                while (resultSet.next()) {
                    mismoNombre++;
                }
                // Si no encuentra resultados
                if (mismoNombre <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "No hemos encontrado ningún registro en la base de datos con ese nombre", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);

                    // Si encuentra solo un resultado
                } else if (mismoNombre == 1) {
                    statement = conexion.prepareStatement("DELETE FROM datos WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'");
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "¡Registro eliminado correctamente!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);

                    // Si encuentra más de un resultado
                } else if (mismoNombre > 1) {
                    String dni = JOptionPane.showInputDialog(rootPane, "Hay mas pacientes con ese nombre, por favor introduce el DNI para eliminar el paciente correcto.", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE).toUpperCase();
                    statement = conexion.prepareStatement("DELETE FROM datos WHERE dni = '" + dni + "'");
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "¡Registro eliminado correctamente!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al seleccionar los registros desde la base de datos", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            }

            // Refrescamos JTextField y JCombbobox para que vuelva a estr vacío.
            txtNombre.setText("");
            txtApellidos.setText("");

            // Se cierra la coneccion.
            conexion.close();

        } catch (SQLException ex) {
            // Mostramos pantalla que avisa del NO registroJOptionPane.showMessageDialog(rootPane, "Registro no insertado!", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(rootPane, "Registro no eliminado!", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la aplicación", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        etiApellidos = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminarTodo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        etiNombre.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        etiApellidos.setText("Apellidos");

        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });
        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidosKeyPressed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminarTodo.setText("Vaciar BBDD");
        btnEliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiApellidos)
                    .addComponent(etiNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnEliminarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiApellidos))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(259, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

     /**
     * Evento ActionPerformed que que ejecuta el método eliminarPaciente().
     * Al clickar el botón aceptar (btnAceptar) ejecuta el método. Es necesario que todos los campos estén cumplimentados.
     * Necesita doble confirmación a través de JOptionPane.showMessageDialog().
     * @see Trabaja con el método eliminarPaciente().
     */
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (txtNombre.getText().length() == 0 || txtApellidos.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "¡Por favor, rellene todos los campos!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int alerta = JOptionPane.showConfirmDialog(rootPane, "Va a eliminar un paciente en la base de datos ¿Está seguro?", "Información del sistema", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (alerta == 0) {
                eliminarPaciente();
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * Evento para que el programa no se cierre al cancelar y cierre simplemente la ventana actual.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

     /**
     * Evento ActionPerformed que que ejecuta el método eliminarTodo().
     * Al clickar el botón Vaciar BBDD (btnEliminarTodo) ejecuta el método.
     * Necesita doble confirmación a través de JOptionPane.showMessageDialog().
     * @see Trabaja con el método eliminarTodo().
     */
    private void btnEliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarTodoActionPerformed
        int alerta = JOptionPane.showConfirmDialog(rootPane, "Está a punto de eliminar toda la base de datos. ¿Seguro que es lo que quiere?", "Información del sistema", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        if (alerta == 0) {
            try {
                eliminarTodo();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEliminarTodoActionPerformed

    /**
     * Evento KeyPressed que ejecuta el evento del botón "Aceptar" a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de Aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_txtNombreKeyPressed

    /**
     * Evento KeyPressed que ejecuta el evento del botón "Aceptar" a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de Aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void txtApellidosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidosKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_txtApellidosKeyPressed

    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Eliminar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Eliminar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Eliminar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Eliminar.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eliminar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarTodo;
    private javax.swing.JLabel etiApellidos;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
