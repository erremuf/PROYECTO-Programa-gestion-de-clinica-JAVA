/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.clinicaAepi.forms;

import static java.awt.event.KeyEvent.VK_ENTER;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * Esta clase crea y guarda en la base de datos los nuevos pacientes de la clínica.
 * Además, registra en su fichero correspondiente (aciertos.txt o errores.txt), si se ha logrado
 * efectuar la operación, indicando el nombre el nuevo paciente y la fecha y hora en la que se 
 * realizó dicho ingreso en la base de datos.
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 * 
 */
public class Insertar extends javax.swing.JFrame {
    
    // Variables de la clase
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet;
    LocalDateTime registro = LocalDateTime.now().withNano(0);
    LocalDate fecha;
    LocalTime hora;

    /**
     * Constructor por defecto de la clase. Muestra los componentes del JFrame al ejecutar el programa.
     */
    public Insertar() {
        initComponents();
    }
    //Cierre del constructor

    /**
     * Método que permite insertar los pacientes en la base de datos "pacientes".
     * Se conecta a la base de datos e inserta en la tabla "datos" las columnas.
     * Posteriormente recoge los valores de los JTextField, los transforma a mayúsculas y los asigna a su columna
     * para introducirlos en la bbdd.
     * Si todo va correctamente, guarda en el fichero "aciertos.txt" el nombre, la hora y la fecha
     * de la inclusión del nuevo paciente en la BBDD. Si se produce un error, guardará la misma información pero en el fichero "errores.txt", avisando que
     * la información no ha podido ser insertada correctamente.
     * Si el DNI del paciente que quieres ingresar ya está registrado te devolvera un IOException.
     * Por último, vuelve a dejar los campos de los JTextField y JComboBox vacíos.
     * @see BufferedWriter
     * @see LocalDateTime
     * @see LocalDate
     * @see LocalTime 
     */
    public void insertarPaciente() {
        try {
            Class.forName("org.sqlite.JDBC");
            // indicamos PROTOCOLO (jdbc) : FABRICANTE (sqlite) : y RUTA a bbdd
            conexion = DriverManager.getConnection("jdbc:sqlite:pacientes.sqlite");
            // Introducimos en la tabla las columnas
            statement = conexion.prepareStatement("INSERT INTO datos(nombre, apellidos, dni, direccion, telefono, especialidad) VALUES (?,?,?,?,?,?)");
            // Introducimos los valores
            statement.setString(1, txtNombre.getText().toUpperCase());
            statement.setString(2, txtApellido.getText().toUpperCase());
            statement.setString(3, txtDni.getText().toUpperCase());
            statement.setString(4, txtDireccion.getText().toUpperCase());
            statement.setString(5, txtTelefono.getText().toUpperCase());
            statement.setString(6, cboEspecialista.getSelectedItem().toString());
            // Insertamos informacion en bbdd
            statement.executeUpdate();
            // Hacemos log y guardamos info en aciertos.txt
            try ( BufferedWriter ficheroAciertos = new BufferedWriter(new FileWriter("aciertos.txt", true))) {
                ficheroAciertos.write("El paciente " + txtNombre.getText() + " " + txtApellido.getText() + ", se ha insertado correctamente el día " + fecha.from(registro) + " a las " + hora.from(registro));
                ficheroAciertos.newLine();
            } catch (IOException ex) {
                Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Mostramos pantalla que avisa del registro
            JOptionPane.showMessageDialog(rootPane, "¡Registro insertado correctamente!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
            // Refrescamos JTextField y JCombbobox para que vuelva a estar vacío.
            txtNombre.setText("");
            txtApellido.setText("");
            txtDni.setText("");
            txtTelefono.setText("");
            txtDireccion.setText("");
            cboEspecialista.setSelectedIndex(0);
            // Se cierra la conexión.
            conexion.close();

        } catch (SQLException ex) {
            // Mostramos pantalla que avisa del NO registro
            JOptionPane.showMessageDialog(rootPane, "Registro no insertado, posiblemente ya exista el DNI en nuestra base de datos, por favor, compruebe sus datos.", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            // Hacemos log y guardamos info en errores.txt
            try ( BufferedWriter ficheroErrores = new BufferedWriter(new FileWriter("errores.txt"))) {
                ficheroErrores.write("El paciente " + txtNombre.getText() + " " + txtApellido.getText() + ", no se ha insertado correctamente el día " + fecha.from(registro) + " a las " + hora.from(registro));
                ficheroErrores.newLine();
            } catch (IOException ex1) {
                Logger.getLogger(Insertar.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la aplicación", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        }              
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiNombre = new javax.swing.JLabel();
        etiApellido = new javax.swing.JLabel();
        etiDni = new javax.swing.JLabel();
        etiDireccion = new javax.swing.JLabel();
        etiTelefono = new javax.swing.JLabel();
        etiEspecialista = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        cboEspecialista = new javax.swing.JComboBox<>();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        etiNombre.setText("Nombre");

        etiApellido.setText("Apellido");

        etiDni.setText("DNI");

        etiDireccion.setText("Dirección");

        etiTelefono.setText("Teléfono");

        etiEspecialista.setText("Especialista");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtApellidoKeyPressed(evt);
            }
        });

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniKeyPressed(evt);
            }
        });

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDireccionKeyPressed(evt);
            }
        });

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyPressed(evt);
            }
        });

        cboEspecialista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Seleciona-", "Alergología", "Anatomía Patológica", "Anestesiología y Reanimación", "Angiología y Cirugía Vascular", "Aparato Digestivo", "Cardiología", "Cirugía Cardiovascular", "Cirugía General y del Aparato Digestivo", "Cirugía Oral y Maxilofacial", "Cirugía Ortopédica y Traumatología", "Cirugía Pediátrica", "Cirugía Plástica, Estética y Reparadora", "Cirugía Torácica", "Dermatología Médico-Quirúrgica y Venereología", "Endocrinología y Nutrición", "Farmacología Clínica", "Geriatría", "Hematología y Hemoterapia", "Inmunología", "Medicina del Trabajo", "Medicina Familiar y Comunitaria", "Medicina Física y Rehabilitación", "Medicina Intensiva", "Medicina Interna", "Medicina Nuclear", "Medicina Preventiva y Salud Pública", "Nefrología", "Neumología", "Neurocirugía", "Neurofisiología Clínica", "Neurología", "Obstetricia y Ginecología", "Oftalmología", "Oncología Médica", "Oncología Radioterápica", "Otorrinolaringología", "Pediatría y sus Áreas Específicas", "Psiquiatría", "Radiodiagnóstico", "Reumatología", "Urología" }));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiTelefono)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(etiNombre)
                                    .addComponent(etiDni)
                                    .addComponent(etiApellido)
                                    .addComponent(etiDireccion)
                                    .addComponent(etiEspecialista))
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDni, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefono)
                                    .addComponent(txtDireccion))))))
                .addGap(130, 130, 130))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiNombre))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiApellido))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiDni))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiDireccion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiTelefono))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboEspecialista, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiEspecialista))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Evento ActionPerformed que que ejecuta el método insertarPaciente().
     * Necesita que todos los campos del formulario estén rellenos para ejecutar el método, para ello comprueba
     * que la longitud  de los JTextField no sea 0 y el JComboBox no tenga seleccionado el index 0. Si esto
     * ocurre, lanza un JOptionPane avisando de que es necesario rellenar todos los campos.
     * Si todos los campos estan rellenos, al clicar el botón ejecuta el método para insertar.
     * @see Trabaja con el método insertarPaciente().
     */
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (txtNombre.getText().length() == 0 || txtApellido.getText().length() == 0
                || txtDni.getText().length() == 0 || txtTelefono.getText().length() == 0
                || txtDireccion.getText().length() == 0 || cboEspecialista.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "¡Por favor, rellene todos los campos!", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
        } else {
            insertarPaciente();
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    /**
     * Método para que el programa no se cierre al cancelar y cierre simplemente la ventana actual.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Evento KeyPressed que ejecuta el evento del botón aceptar a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de aceptar
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
     * Evento KeyPressed que ejecuta el evento del botón aceptar a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void txtApellidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_txtApellidoKeyPressed

    /**
     * Evento KeyPressed que ejecuta el evento del botón aceptar a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void txtDniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_txtDniKeyPressed

    /**
     * Evento KeyPressed que ejecuta el evento del botón aceptar a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de aceptar
     * (btnAceptar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void txtDireccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_txtDireccionKeyPressed

    private void txtTelefonoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyPressed
        if (evt.getKeyCode() == VK_ENTER) {
            btnAceptar.doClick();
        }
    }//GEN-LAST:event_txtTelefonoKeyPressed

    /**
     * Evento KeyPressed que ejecuta el evento del botón aceptar a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de aceptar
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
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Insertar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Insertar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cboEspecialista;
    private javax.swing.JLabel etiApellido;
    private javax.swing.JLabel etiDireccion;
    private javax.swing.JLabel etiDni;
    private javax.swing.JLabel etiEspecialista;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiTelefono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
