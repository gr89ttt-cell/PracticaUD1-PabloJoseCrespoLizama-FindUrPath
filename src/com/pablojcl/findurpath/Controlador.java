package com.pablojcl.findurpath;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

public class Controlador implements ActionListener {
    private Vista vista;
    private Modelo modelo;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
        asociarListeners(this);
    }

    private void asociarListeners(ActionListener listener){
        vista.itemAbrir.addActionListener(listener);
        vista.itemGuardar.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String accion = actionEvent.getActionCommand();
/*
        switch (accion){
            case "abrir":
            {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcion = selectorArchivo.showOpenDialog(null);
                if(opcion == JFileChooser.APPROVE_OPTION) {
                    File fichero = selectorArchivo.getSelectedFile();
                    try {
                        vista.textArea.setText(modelo.cargarText(fichero));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
            case "guardar":
            {
                JFileChooser selectorArchivo = new JFileChooser();
                int opcion = selectorArchivo.showSaveDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    File ficheroSeleccionado = selectorArchivo.getSelectedFile();
                    try {
                        modelo.guardarText(ficheroSeleccionado, vista.textArea.getText());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
        */
    }
}
