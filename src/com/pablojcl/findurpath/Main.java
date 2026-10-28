package com.pablojcl.findurpath;

public class Main {
    public static void main(String[] args)   {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(vista, modelo);
    }
}
