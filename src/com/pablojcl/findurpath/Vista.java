package com.pablojcl.findurpath;

import javax.swing.*;

public class Vista {

    public JFrame frame;
    JMenuItem itemGuardar;
    JMenuItem itemAbrir;
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
        frame = new JFrame("EditorMVC");
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
        itemGuardar = new JMenuItem("Guardar");
        itemAbrir = new JMenuItem("Abrir");

        itemGuardar.setActionCommand("guardar");
        itemAbrir.setActionCommand("abrir");

        menu.add(itemAbrir);
        menu.add(itemGuardar);
        barra.add(menu);
        frame.setJMenuBar(barra);
    }
}

