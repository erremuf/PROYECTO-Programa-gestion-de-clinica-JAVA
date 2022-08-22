/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinicaAepi.clases;

import com.clinicaAepi.forms.ContadorRegistros;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * Esta clase crea una tarea que se repite cada cierto tiempo.
 * Se encarga de crear una tarea TimerTask que trabaja con la clase ContadorRegistros y su método mostrarContador().
 * @author Ricardo Murillo
 * @version 04/22/2022/A
 * @see ContadorRegistros
 */
public class Tarea extends ContadorRegistros {

    /**
     * Método que crea una tarea con la clase Timer y TimerTask. 
     * El objeto TimerTaks sobreescribe su método run() que instancia on objeto de tipo ContadorRegistros, cambia
     * su vissibilidad a true y llama a su método mostrarContador(). Basicamente, muestra el JFrame ContadorRegistros con
     * el contador actualizado cada cierto tiempo.
     * @see Timer
     * @see TimerTask
     * @see ContadorRegistros
     * 
     */
    public void tarea() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ContadorRegistros contador = new ContadorRegistros();
                contador.setVisible(true);
                contador.mostrarContador();
            }
        };
        
        timer.scheduleAtFixedRate(task, 5000, 10000);       
    }
}
