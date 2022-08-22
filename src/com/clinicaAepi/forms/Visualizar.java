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
import javax.swing.table.DefaultTableModel;
import com.clinicaAepi.interfaces.ILogs;

/**
 *
 * Esta clase muestra la base de datos con toda la información de los pacientes.
 * Además, permite buscar por nombre a los pacientes que tenemos en la base de datos.
 * Te permite escirbir el nombre del paciente tanto en minusculas como en mayusculas
 * y lo mostrará igualmente.
 * Esta clase implementa la intefaz ILogs.
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 * @see ILogs
 * 
 */
public class Visualizar extends javax.swing.JFrame implements ILogs {

    // Variables de la clase.
    private Connection conexion = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet;

    /**
     * Constructor por defecto de la clase. Muestra los componentes del JFrame al ejecutar el programa
     * y ejecuta desde el inicio el método muestraTodos() para que la información se visualice nada mas abrir el JFrame.
     */
    public Visualizar() {
        initComponents();
        mostrarTodos();
    }
    //Cierre del constructor
    
    /**
     * Implementación del método de la Interfaz ILogs. 
     * En esta clase no lo usamos.
     */
    @Override
    public void borrarFichero() {
    }

    /**
     * Implementación del método mostrar() de la Interfaz ILogs que busca en la base de datos por el nombre del paciente. 
     * Este método conecta con la base de datos y selecciona todo lo que coincida con el nombre introducido
     * en el JTextField y guarda los resultados en una variable ResultSet.
     * Crea una tabla DefaultTableModel llamada modelo y le añade las columnas que vamos a mostrar.
     * Posteriormente crea un bucle while que recorre la variable ResultSet y una matriz de objetos en su interior
     * a la que añadirá losresultados obtenidos de la bbdd. En cada iteración del bucle agrega una fila a
     * la tabla de la base de datos. Además, dentro de esta bucle suma 1 en cada iteración a la variable de tipo int,
     * iniciada en 0, si la longitud de la matriz es mayor de 0. Con esto consigue averiguar si hay algun nombre
     * que coincida con el nombre introducido.
     * Por ultimo resetea el JTextField para dejar el campo vacío.
     * @see DefaultTableModel
     * @see Connection
     * @see ResultSet
     * 
     */
    @Override
    public void mostrar() {
        try {
            String nombre = txtBuscarNombre.getText().trim().toUpperCase();

            Class.forName("org.sqlite.JDBC");
            // indicamos PROTOCOLO (jdbc) : FABRICANTE (sqlite) : y RUTA a bbdd
            conexion = DriverManager.getConnection("jdbc:sqlite:pacientes.sqlite");
            // Seleccionamos de nuestra tabla en la BBDD todo donde nombre sea ?
            statement = conexion.prepareStatement("SELECT * FROM datos WHERE nombre=?");
            statement.setString(1, nombre); 
            resultSet = statement.executeQuery();

            // Creamos modelo de tabla por defecto
            DefaultTableModel modelo = new DefaultTableModel();
            // Añadimos las columnas en nuestra tabla modelo que vamos a mostrar en la tabla
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            modelo.addColumn("DNI");
            modelo.addColumn("Dirección");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Especialidad");
            // Le asignamos a nuestra tabla el modelo por defecto que hemos creado
            table.setModel(modelo);

            int contadorRegistros = 0;
            try {
                //Recorro el ResultSet que contiene los resultados.
                while (resultSet.next()) {
                    Object[] resultados = new Object[6];
                    resultados[0] = resultSet.getString(1);
                    resultados[1] = resultSet.getString(2);
                    resultados[2] = resultSet.getString(3);
                    resultados[3] = resultSet.getString(4);
                    resultados[4] = resultSet.getString(5);
                    resultados[5] = resultSet.getString(6);
                    // Agregamos en cada iteracion una fila a tabla
                    modelo.addRow(resultados);
                    if (resultados.length > 0) {
                        contadorRegistros++;
                    }
                }

                if (contadorRegistros <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "No hemos encontrado ningún registro", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
                }
                
                // Refrescamos JTextField para que vuelva a estr vacío.
                txtBuscarNombre.setText("");

                // Se cierra la coneccion.
                conexion.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al seleccionar los registros desde la base de datos", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la aplicación", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Método que muestra todos los registros de pacientes de la bbdd. 
     * Funciona exactamente igual que el método mostrar() pero selecciona todo de la base de datos sin sentencia WHERE.
     * @see mostrar()
     * 
     */
    public void mostrarTodos() {
        try {
            Class.forName("org.sqlite.JDBC");
            // indicamos PROTOCOLO (jdbc) : FABRICANTE (sqlite) : y RUTA a bbdd
            conexion = DriverManager.getConnection("jdbc:sqlite:pacientes.sqlite");
            // Seleccionamos de nuestra tabla en la BBDD todo donde nombre sea ?
            statement = conexion.prepareStatement("SELECT * FROM datos");
            resultSet = statement.executeQuery();
            // Creamos modelo de tabla por defecto
            DefaultTableModel modelo = new DefaultTableModel();
            // Añadimos las columnas en nuestra tabla modelo que vamos a mostrar en la tabla
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellidos");
            modelo.addColumn("DNI");
            modelo.addColumn("Dirección");
            modelo.addColumn("Teléfono");
            modelo.addColumn("Especialidad");
            // Le asignamos a nuestra tabla el modelo por defecto que hemos creado
            table.setModel(modelo);
            int contadorRegistros = 0;
            try {
                //Recorro el ResultSet que contiene los resultados.
                while (resultSet.next()) {
                    Object[] resultados = new Object[6];
                    resultados[0] = resultSet.getString(1);
                    resultados[1] = resultSet.getString(2);
                    resultados[2] = resultSet.getString(3);
                    resultados[3] = resultSet.getString(4);
                    resultados[4] = resultSet.getString(5);
                    resultados[5] = resultSet.getString(6);
                    // Agregamos en cada iteracion una fila a tabla
                    modelo.addRow(resultados);
                    if (resultados.length > 0) {
                        contadorRegistros++;
                    }
                }

                if (contadorRegistros <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "No hemos encontrado ningún registro", "Aviso del sistema", JOptionPane.INFORMATION_MESSAGE);
                }

                // Se cierra la coneccion.
                conexion.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al seleccionar los registros desde la base de datos", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error en la aplicación", "Aviso del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etiNombre = new javax.swing.JLabel();
        txtBuscarNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVerTodos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        etiNombre.setText("Nombre");

        txtBuscarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarNombreActionPerformed(evt);
            }
        });
        txtBuscarNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarNombreKeyPressed(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnVerTodos.setText("Ver todos");
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiNombre)
                        .addGap(29, 29, 29)
                        .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVerTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(130, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etiNombre)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarNombreActionPerformed

    /**
     * Método para que el programa no se cierre al cancelar y cierre simplemente la ventana actual.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Evento ActionPerformed que que ejecuta el método mostrar().
     * Al clickar el botón buscar (btnBuscar) ejecuta el método.
     * @see Trabaja con el método mostrar().
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        mostrar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Evento KeyPressed que ejecuta el evento del botón buscar a través de la pulsación de la tecla Enter.
     * Este evento permite que al pulsar la tecla "Enter", se ejecute el mismo evento que tiene el botón de buscar
     * (btnBuscar) del JFrame a través del método doClick().
     * @see Para comprobar la tecla que se pulsa trabaja con la clase KeyEvent su la constante VK_ENTER.
     * @see El método que ejecuta es el de btnAceptarActionPerformed().
     */
    private void txtBuscarNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarNombreKeyPressed
        if (evt.getKeyCode()== VK_ENTER) {
            btnBuscar.doClick();
        }
    }//GEN-LAST:event_txtBuscarNombreKeyPressed

    /**
     * Evento ActionPerformed que que ejecuta el método mostrarTodos().
     * Al clickar el botón "ver todos" (btnVerTodos) ejecuta el método.
     * @see Trabaja con el método mostrarTodos().
     */
    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        mostrarTodos();
    }//GEN-LAST:event_btnVerTodosActionPerformed

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
            java.util.logging.Logger.getLogger(Visualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Visualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Visualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Visualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Visualizar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtBuscarNombre;
    // End of variables declaration//GEN-END:variables

    
}
