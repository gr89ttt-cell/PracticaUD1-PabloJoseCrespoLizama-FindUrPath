package com.pablojcl.findurpath;

import javax.swing.*;

public class Vista {

    public JFrame frame;
    JMenuItem itemExport;
    JMenuItem itemImport;
    public JPanel panelTop;
    public JPanel panelBot;
    public JLabel Title;
    public JPanel panelAddPath;
    public JLabel JLabelAddPanel;
    public JTextField textFieldNamePath;
    public JTextField textFieldLength;
    public JCheckBox checkBoxBTT;
    private JPanel mainPanel;
    public JButton btnPR;
    public JButton btnGR;
    public JButton btnSL;
    public JCheckBox checkBoxBTTSearch;
    public JTextArea textAreaSR;
    public JButton btnSent;
    public JPanel panelSearch;
    public JPanel panelDeletePath;
    public JLabel JLabelDeletePath;
    public JTextField textFieldDelPath;
    public JButton btnDelPath;
    public JTextField textFieldReferencia;

    public Vista(){
        frame = new JFrame("FindUrPath - Pablo José Crespo Lizama - MVC");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        crearBarraMenu();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    private void crearBarraMenu(){
        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        itemImport = new JMenuItem("Importar Datos");
        itemExport = new JMenuItem("Exportar Datos");

        itemImport.setActionCommand("import");
        itemExport.setActionCommand("export");

        menu.add(itemImport);
        menu.add(itemExport);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}

