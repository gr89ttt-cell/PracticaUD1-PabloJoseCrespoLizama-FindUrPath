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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Controlador implements ActionListener, ListSelectionListener, WindowListener {
    private Vista vista;
    private Modelo modelo;
    private File ultimaRutaExportada;

    public Controlador(Vista vista, Modelo modelo){
        this.vista = vista;
        this.modelo = modelo;
        asociarListeners(this);
        addWindowListener(this);
    }

    private boolean comprovarCamposVaciosNew() {
        if (vista.textFieldNamePath.getText().isEmpty() ||
                vista.textFieldLength.getText().isEmpty() ||
                vista.textFieldReferencia.getText().isEmpty() ||
                vista.JDatePicker.getText().isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    private boolean comprovarCamposVaciosDel() {
        return vista.textFieldDelPath.getText().isEmpty();
    }

    private void limpiarCampos() {
        vista.textFieldNamePath.setText(null);
        vista.textFieldLength.setText(null);
        vista.textFieldReferencia.setText(null);
        vista.textFieldDelPath.setText(null);
        vista.JDatePicker.setDate(null);
    }

    private void limpiarSR(){
        vista.textAreaSR.setText(null);
    }

    private void mostrarTodo() {
        limpiarSR();
        for(Ruta ruta: modelo.listaRutas)
            if(vista.checkBoxBTTSearch.isSelected()){
                if(ruta.isBtt()){
                    vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                }
            } else {
                vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
            }
    }

    private void asociarListeners(ActionListener listener){
        vista.itemExport.addActionListener(listener);
        vista.itemImport.addActionListener(listener);
        vista.btnSent.addActionListener(listener);
        vista.btnDelPath.addActionListener(listener);
        vista.btnGR.addActionListener(listener);
        vista.btnPR.addActionListener(listener);
        vista.btnSL.addActionListener(listener);
        vista.especificoRadioButton.addActionListener(listener);
        vista.todoRadioButton.addActionListener(listener);
        vista.actualizarButton.addActionListener(listener);
    }

    private void guardarConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        if (ultimaRutaExportada != null) {
            configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        }
        configuracion.store(new PrintWriter("perifericos.conf"), "Datos configuracion perifericos");
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Añadir":
                if (comprovarCamposVaciosNew()) {
                    Utilities.mensajeError("Los siguientes campos no puede estar vacios: \n" +
                            "Nombre de la Ruta \n Kilometraje \n Referencia");
                    break;
                }
                if (modelo.existeRuta(vista.textFieldNamePath.getText())) {
                    Utilities.mensajeError("Ya existe esta Ruta \n");
                    break;
                }
                modelo.newRuta(vista.textFieldNamePath.getText(),Integer.parseInt(vista.textFieldLength.getText()),
                        vista.textFieldReferencia.getText(),vista.checkBoxBTT.isSelected(),vista.JDatePicker.getDate());
                Utilities.mensajeRecuerdo("Ruta creada con exito");
                limpiarCampos();
                if(vista.todoRadioButton.isSelected()){
                    mostrarTodo();
                }

                break;

            case "import":
                JFileChooser selectorFichero = Utilities.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int importFile = selectorFichero.showOpenDialog(null);
                if (importFile == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (SAXException ex) {
                        ex.printStackTrace();
                    }
                }
                mostrarTodo();
                break;

            case "export":
                JFileChooser selectorFichero2 = Utilities.crearSelectorFichero(ultimaRutaExportada,
                        "Archivos XML","xml");
                int exportFile = selectorFichero2.showSaveDialog(null);
                if (exportFile == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportar(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException ex) {
                        ex.printStackTrace();
                    } catch (TransformerException ex) {
                        ex.printStackTrace();
                    }
                }
                break;

            case "GR":
                if(vista.especificoRadioButton.isSelected()) {
                    limpiarSR();
                    for (Ruta ruta : modelo.listaRutas)
                        if (ruta instanceof GrandRoute) {
                            if (vista.checkBoxBTTSearch.isSelected()) {
                                if (ruta.isBtt()) {
                                    vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                                }
                            } else {
                                vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                            }
                        }
                }
                break;

            case "PR":
                if(vista.especificoRadioButton.isSelected()) {
                    limpiarSR();
                    for (Ruta ruta : modelo.listaRutas)
                        if (ruta instanceof PetiteRoute) {
                            if (vista.checkBoxBTTSearch.isSelected()) {
                                if (ruta.isBtt()) {
                                    vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                                }
                            } else {
                                vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                            }
                        }
                }
                break;

            case "SL":
                if(vista.especificoRadioButton.isSelected()) {
                    limpiarSR();
                    for (Ruta ruta : modelo.listaRutas)
                        if (ruta instanceof SenderoL) {
                            if (vista.checkBoxBTTSearch.isSelected()) {
                                if (ruta.isBtt()) {
                                    vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                                }
                            } else {
                                vista.textAreaSR.setText(vista.textAreaSR.getText() + "\n" + ruta.toString());
                            }
                        }
                }
                break;

            case "Borrar":
                if (comprovarCamposVaciosDel()) {
                    Utilities.mensajeError("El campo \"Nombre de la Ruta\" no puede estar vacio. "
                            + vista.textFieldDelPath.getText());
                    break;
                }
                for(int i = 0; i < modelo.listaRutas.size(); i++)
                    if (modelo.listaRutas.get(i).getNombre().equals(vista.textFieldDelPath.getText()))
                        modelo.listaRutas.remove(i);
                    limpiarCampos();
                    break;

            case "Actualizar":
                if(vista.todoRadioButton.isSelected()) {
                    mostrarTodo();
                }
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        int resp = Utilities.mensajeConfirmacion("¿Desea salir de la aplicación?", "Salir");
        if (resp == JOptionPane.OK_OPTION) {
            try {
                guardarConfiguracion();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
