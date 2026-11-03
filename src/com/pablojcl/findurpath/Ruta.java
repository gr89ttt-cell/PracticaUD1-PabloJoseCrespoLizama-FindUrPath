package com.pablojcl.findurpath;

public abstract class Ruta {
    private String nombre;
    private int kilometros;
    private String referencia;
    private boolean btt;

    public Ruta(String nombre, int kilometros, String referencia, boolean btt) {
        this.nombre = nombre;
        this.kilometros = kilometros;
        this.referencia = referencia;
        this.btt = btt;
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

    @Override
    public String toString() {
        return nombre + '\n' +
                "\n Kilometros:" + kilometros +
                "\n Referencia:'" + referencia +
                "\n Ruta multiactividad:" + btt +
                "\n -----------------------------";
    }
}
