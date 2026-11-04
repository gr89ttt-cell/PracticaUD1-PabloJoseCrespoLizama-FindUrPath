package com.pablojcl.findurpath;

import java.time.LocalDate;

public abstract class Ruta {
    private String nombre;
    private int kilometros;
    private String referencia;
    private boolean btt;
    private LocalDate fecha_ingreso;

    public Ruta(String nombre, int kilometros, String referencia, boolean btt, LocalDate fecha_ingreso) {
        this.nombre = nombre;
        this.kilometros = kilometros;
        this.referencia = referencia;
        this.btt = btt;
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public boolean isBtt() {
        return btt;
    }

    public void setBtt(boolean btt) {
        this.btt = btt;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    @Override
    public String toString() {
        return nombre + " | Ingreso: " + fecha_ingreso +"\n" +
                "\n Kilometros: " + kilometros +
                "\n Referencia: " + referencia +
                "\n Ruta multiactividad: " + btt +
                "\n --------------------------------------";
    }
}
