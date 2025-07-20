package com.drivequest.modelo;

public abstract class Vehiculo {

    // Atributos comunes para todos los vehículos
    protected String patente;
    protected String marca;
    protected int anio;
    protected int diasArriendo;
    protected double valorDiario;

    // Constructor vacío
    public Vehiculo() {
        // No inicializa atributos, se espera que se usen setters
    }

    // Constructor sobrecargado
    public Vehiculo(String patente, String marca, int anio, int diasArriendo, double valorDiario) {
        this.patente = patente;
        this.marca = marca;
        this.anio = anio;
        this.diasArriendo = diasArriendo;
        this.valorDiario = valorDiario;
    }

    // Getters y Setters
    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    public double getValorDiario() {
        return valorDiario;
    }

    public void setValorDiario(double valorDiario) {
        this.valorDiario = valorDiario;
    }

    // Método abstracto que debe ser implementado por cada subclase
    public abstract void mostrarDatos();
}