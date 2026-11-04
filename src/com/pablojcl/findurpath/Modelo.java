package com.pablojcl.findurpath;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Modelo {

    public ArrayList<Ruta> listaRutas;

    public Modelo() {
        listaRutas= new ArrayList<Ruta>();
    }

    public ArrayList<Ruta> obtenerVehiculos() {
        return listaRutas;
    }

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

    public boolean existeRuta(String nombre) {
        for (Ruta ruta: listaRutas) {
            if (ruta.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void newRuta(String nombre, int kilometros, String referencia, boolean btt, LocalDate fecha_ingreso) {
        if (kilometros < 10){
            listaRutas.add(new SenderoL(nombre,kilometros,referencia, btt, fecha_ingreso));
        } else if (kilometros > 10 && kilometros < 50){
            listaRutas.add(new PetiteRoute(nombre,kilometros,referencia, btt, fecha_ingreso));
        } else {
            listaRutas.add(new GrandRoute(nombre, kilometros, referencia, btt, fecha_ingreso));
        }
    }

    public void exportar(File fichero) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null,"xml",null);

        Element raiz = documento.createElement("Rutas");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoRuta = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Ruta ruta: listaRutas) {
            if (ruta instanceof GrandRoute) {
                nodoRuta = documento.createElement("GR");
            } else if (ruta instanceof PetiteRoute){
                nodoRuta = documento.createElement("PR");
            } else if (ruta instanceof SenderoL) {
                nodoRuta = documento.createElement("SL");
            }
            raiz.appendChild(nodoRuta);

            nodoDatos = documento.createElement("nombre");
            nodoRuta.appendChild(nodoDatos);

            texto = documento.createTextNode(ruta.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("kilometros");
            nodoRuta.appendChild(nodoDatos);

            texto = documento.createTextNode("" + ruta.getKilometros());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("modelo");
            nodoRuta.appendChild(nodoDatos);

            texto = documento.createTextNode(ruta.getReferencia());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("BTT");
            nodoRuta.appendChild(nodoDatos);

            texto = documento.createTextNode(""+ruta.isBtt());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fecha_ingreso");
            nodoRuta.appendChild(nodoDatos);

            texto = documento.createTextNode("" + ruta.getFecha_ingreso());
            nodoDatos.appendChild(texto);
        }

        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source,resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaRutas = new ArrayList<Ruta>();
        Ruta newRuta = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        NodeList listaElementos = documento.getElementsByTagName("*");

        for (int i=0;i<listaElementos.getLength();i++) {
            Element nodoRuta = (Element) listaElementos.item(i);

            //intento fallido ¡~¡
            /*
            newRuta(nodoRuta.getChildNodes().item(0).getTextContent(),
                    Integer.parseInt(nodoRuta.getChildNodes().item(1).getTextContent()),
                    nodoRuta.getChildNodes().item(2).getTextContent(),
                    Boolean.getBoolean(nodoRuta.getChildNodes().item(3).getTextContent()));
            */

            //intento fallido ¡~¡
            /*
            String newRutaNombre = nodoRuta.getChildNodes().item(0).getTextContent();
            int newRutaKilometros = Integer.parseInt(nodoRuta.getChildNodes().item(1).getTextContent());
            String newRutaReferencia = nodoRuta.getChildNodes().item(2).getTextContent();
            boolean newRutaBTT = Boolean.getBoolean(nodoRuta.getChildNodes().item(3).getTextContent());

            newRuta(newRutaNombre,newRutaKilometros,newRutaReferencia,newRutaBTT);
             */

            if (nodoRuta.getTagName().equals("GR")) {
                newRuta = new GrandRoute(nodoRuta.getChildNodes().item(0).getTextContent(),
                        Integer.parseInt(nodoRuta.getChildNodes().item(1).getTextContent()),
                        nodoRuta.getChildNodes().item(2).getTextContent(),
                        Boolean.parseBoolean(nodoRuta.getChildNodes().item(3).getTextContent()),
                        LocalDate.parse(nodoRuta.getChildNodes().item(4).getTextContent()));
                listaRutas.add(newRuta);

            } else if (nodoRuta.getTagName().equals("PR")) {
                    newRuta = new PetiteRoute(nodoRuta.getChildNodes().item(0).getTextContent(),
                            Integer.parseInt(nodoRuta.getChildNodes().item(1).getTextContent()),
                            nodoRuta.getChildNodes().item(2).getTextContent(),
                            Boolean.parseBoolean(nodoRuta.getChildNodes().item(3).getTextContent()),
                            LocalDate.parse(nodoRuta.getChildNodes().item(4).getTextContent()));
                    listaRutas.add(newRuta);

            } else if (nodoRuta.getTagName().equals("SL")) {
                    newRuta = new SenderoL(nodoRuta.getChildNodes().item(0).getTextContent(),
                            Integer.parseInt(nodoRuta.getChildNodes().item(1).getTextContent()),
                            nodoRuta.getChildNodes().item(2).getTextContent(),
                            Boolean.parseBoolean(nodoRuta.getChildNodes().item(3).getTextContent()),
                            LocalDate.parse(nodoRuta.getChildNodes().item(4).getTextContent()));
                    listaRutas.add(newRuta);
                }
            }
        }
}
