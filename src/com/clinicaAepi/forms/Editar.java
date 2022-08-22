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
import javax.swing.JOptionPane;

/**
 * Esta clase permite editar en la base de datos la especialidad de los pacientes.
 * Primero busca en la base de datos el nombre y el apellido introducidos, comprueba que haya algún 
 * registro/paciente con esos datos o que no haya más de un paciente con el mismo nombre y apellidos
 * y modifica su especialidad. Si hay mas de un registro con el mismo nombre, solicita el DNI.
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 */
public class Editar extends javax.swing.JFrame {

    // Variables de clase
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet;

    /**
     * Constructor por defecto de la clase.
     * Muestra los componentes del JFrame al ejecutar el programa.
     */
    public Editar() {
        initComponents();
    }
    //Cierre del constructor

    /**
     * Método que nos permite editar la especialidad de los pacientes y modificarlo en la base de datos. 
     * Se conecta a la base de datos y selecciona todo, a traves de la sentencia WHERE, lo que coincida
     * con el nombre y el apellido introducidos. (Los JTextField los tranforma a mayúsculas para hacer la búsqueda)
     * Si no encuentra registros con esos datos te devulve un mensaje por pantalla.
     * Si encuentra solo uno, lo modifica y te lo muestra por pantalla.
     * Si encuentra mas de un resultado, te pide que introduzcas el campo DNI (Clave primaria en la base de datos)
     * para asegurarse de modificar el paciente correcto.
     * Por último refresca los JTextField para que vuelvan a estar vacíos.
     * @see ResultSet
     * @see Connection
     */
    public void editarPaciente() {
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
                // Hacemos la condición según los resultados que encuentre con el mismo nombre
                // Si no encuentra resultados
                if (mismoNombre <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "No hemos encontrado ningún registro en la base de datos con ese nombre", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
                // Si encuentra solo un resultado
                } else if (mismoNombre == 1) {
                    statement = conexion.prepareStatement("UPDATE datos SET especialidad = ? "
                            + "WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "'");
                    statement.setString(1, cboEspecialista.getSelectedItem().toString());
                    statement.executeUpdate();

                    JOptionPane.showMessageDialog(rootPane, "¡Registro actualizado correctamente!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
                // Si encuentra más de un resultado
                } else if (mismoNombre > 1) {
                    String dni = JOptionPane.showInputDialog(rootPane, "Hay mas pacientes con ese nombre, por favor introduce el DNI para modificar el paciente correcto.", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE).toUpperCase();
                    
                    statement = conexion.prepareStatement("UPDATE datos SET especialidad = ? "
                            + "WHERE dni = '" + dni + "'");
                    statement.setString(1, cboEspecialista.getSelectedItem().toString());
                    statement.executeUpdate();
                    
                    JOptionPane.showMessageDialog(rootPane, "¡Registro actualizado correctamente!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al seleccionar los registros desde la base de datos", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            }

            // Refrescamos JTextField y JCombbobox para que vuelva a estr vacío.
            txtNombre.setText("");
            txtApellidos.setText("");
            cboEspecialista.setSelectedIndex(0);

            // Se cierra la coneccion.
            conexion.close();

        } catch (SQLException ex) {
            // Mostramos pantalla que avisa del NO registroJOptionPane.showMessageDialog(rootPane, "Registro no insertado!", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(rootPane, "Registro no actualizado!", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la aplicación", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiNombre = new javax.swing.JLabel();
        etiApellidos = new javax.swing.JLabel();
        etiEspecialista = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        cboEspecialista = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        etiNombre.setText("Nombre");

        etiApellidos.setText("Apellidos");

        etiEspecialista.setText("Especialista");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidosKeyPressed(evt);
            }
        });

        cboEspecialista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleccione-", "Alergología", "Anatomía Patológica", "Anestesiología y Reanimación", "Angiología y Cirugía Vascular", "Aparato Digestivo", "Cardiología", "Cirugía Cardiovascular", "Cirugía General y del Aparato Digestivo", "Cirugía Oral y Maxilofacial", "Cirugía Ortopédica y Traumatología", "Cirugía Pediátrica", "Cirugía Plástica, Estética y Reparadora", "Cirugía Torácica", "Dermatología Médico-Quirúrgica y Venereología", "Endocrinología y Nutrición", "Farmacología Clínica", "Geriatría", "Hematología y Hemoterapia", "Inmunología", "Medicina del Trabajo", "Medicina Familiar y Comunitaria", "Medicina Física y Rehabilitación", "Medicina Intensiva", "Medicina Interna", "Medicina Nuclear", "Medicina Preventiva y Salud Pública", "Nefrología", "Neumología", "Neurocirugía", "Neurofisiología Clínica", "Neurología", "Obstetricia y Ginecología", "Oftalmología", "Oncología Médica", "Oncología Radioterápica", "Otorrinolaringología", "Pediatría y sus Áreas Específicas", "Psiquiatría", "Radiodiagnóstico", "Reumatología", "Urología" }));
        cboEspecialista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboEspecialistaKeyPressed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiApellidos)
                            .addComponent(etiEspecialista)
                            .addComponent(etiNombre))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre)
                            .addComponent(txtApellidos)
                            .addComponent(cboEspecialista, 0, 439, Short.MAX_VALUE))))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiApellidos))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiEspecialista))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
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
     * Evento ActionPerformed que que ejecuta el método editarPaciente().
     * Al clickar el botón aceptar (btnAceptar) ejecuta el método. Es necesario que todos los campos estén cumplimentados.
     * Necesita doble confirmación a través de JOptionPane.showMessageDialog().
     * @see Trabaja con el método editarPaciente().
     */
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (txtNombre.getText().length() == 0 || txtApellidos.getText().length() == 0 || cboEspecialista.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "¡Por favor, rellene todos los campos!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            int alerta = JOptionPane.showConfirmDialog(rootPane, "Va a modificar la especialidad de un paciente en la base de datos ¿Está seguro?", "Información del sistema", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (alerta == 0) {
                editarPaciente();
            }
        }


    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * Evento KeyPressed que ejecuta el evento del botón "Aceptar" a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de Aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_formKeyPressed

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

    /**
     * Evento KeyPressed que ejecuta el evento del botón "Aceptar" a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de Aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void cboEspecialistaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboEspecialistaKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_cboEspecialistaKeyPressed

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
            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Editar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Editar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cboEspecialista;
    private javax.swing.JLabel etiApellidos;
    private javax.swing.JLabel etiEspecialista;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
