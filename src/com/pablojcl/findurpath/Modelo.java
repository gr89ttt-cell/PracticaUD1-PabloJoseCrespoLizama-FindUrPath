package com.pablojcl.findurpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Modelo {

    public String cargarText(File fichero) throws FileNotFoundException {
        Scanner lector = new Scanner(fichero);
        StringBuilder textoLeido = new StringBuilder();

        while (lector.hasNextLine()){
            textoLeido.append(lector.nextLine());
        }
        lector.close();
        return textoLeido.toString();
    }

    public void guardarText(File fichero, String texto) throws FileNotFoundException {
        PrintWriter escritor = new PrintWriter(fichero);
        escritor.println(texto);
        escritor.close();
    }
}
