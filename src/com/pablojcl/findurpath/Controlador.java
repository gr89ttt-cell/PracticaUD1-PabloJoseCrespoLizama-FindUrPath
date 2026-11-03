package com.pablojcl.findurpath;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener {
    private Vista vista;
    private Modelo modelo;
    private File ultimaRutaExportada;
    public static LinkedList<Ruta> lista;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
        asociarListeners(this);
        addWindowListener(this);
    }

    private boolean comprovarCamposVaciosNew() {
        if (vista.textFieldNamePath.getText().isEmpty() ||
                vista.textFieldLength.getText().isEmpty() ||
                vista.textFieldReferencia.getText().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    private boolean comprovarCamposVaciosDel() {
        if (vista.textFieldDelPath.getText().isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    private void limpiarCampos() {
        vista.textFieldNamePath.setText(null);
        vista.textFieldLength.setText(null);
        vista.textFieldReferencia.setText(null);
        vista.textFieldDelPath.setText(null);
    }

    private void asociarListeners(ActionListener listener){
        vista.itemGuardar.addActionListener(listener);
        vista.itemAbrir.addActionListener(listener);
        vista.btnSent.addActionListener(listener);
        vista.btnDelPath.addActionListener(listener);
        vista.btnGR.addActionListener(listener);
        vista.btnPR.addActionListener(listener);
        vista.btnSL.addActionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Nuevo":
                if (comprovarCamposVaciosNew()) {
                    Utilities.mensajeError("Los siguientes campos no puede estar vacios: \n" +
                            "Matricula \n Marca \n Modelo\n Fecha matriculación"
                            +vista.textAreaSR.getText());
                    break;
                }
                if (modelo.existeRuta(vista.textFieldNamePath.getText())) {
                    Utilities.mensajeError("Ya existe un vehiculo con esa matricula \n" +
                            vista.textFieldNamePath.getText());
                    break;
                }
                modelo.newRuta(vista.textFieldNamePath.getText(),Integer.parseInt(vista.textFieldLength.getText()),vista.textFieldReferencia.getText(),vista.checkBoxBTT.isSelected());
                limpiarCampos();
                Utilities.mensajeError("Ruta creada con exito"
                        +vista.textAreaSR.getText());
                break;
            case "Importar":
                JFileChooser selectorFichero = Utilities.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                    Utilities.mensajeError("Importado con exito"
                            +vista.textAreaSR.getText());
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Utilities.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int opcion2 = selectorFichero2.showSaveDialog(null);
                if (opcion2==JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;
            case "GR":
                vista.textAreaSR.setText("kms");
                break;
            case "PR":
                vista.textAreaSR.setText("N Plazas");
                break;
            case "SL":
                vista.textAreaSR.setText("Sl");
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int resp = Utilities.mensajeConfirmacion("¿Desea cerrar la ventana?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

    }
}
