package com.pablojcl.findurpath;

import javax.swing.*;

public class Vista {

    private JFrame frame;
    JMenuItem itemGuardar;
    JMenuItem itemAbrir;
    private JPanel panelTop;
    private JPanel panelBot;
    private JLabel Title;
    private JPanel panelAddPath;
    private JLabel JLabelAddPanel;
    private JTextField textFieldNamePath;
    private JTextField textFieldLength;
    private JCheckBox checkBoxBTT;
    private JPanel mainPanel;
    private JButton btnPR;
    private JButton btnGR;
    private JButton btnSL;
    private JCheckBox checkBoxBTTSearch;
    private JTextArea textAreaSR;
    private JButton btnSent;
    private JPanel panelSearch;
    private JPanel panelDeletePath;
    private JLabel JLabelDeletePath;
    private JTextField textFieldDelPath;
    private JButton btnDelPath;

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

