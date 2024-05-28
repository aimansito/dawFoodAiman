/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author aiman
 */
public class Tarjeta {
     // Declaración de atributos
    private String nombreTitular;
    private int numeroTarjeta;
    private LocalDate fechaVencimiento;
    private int CVV;
    private double saldoTarjeta;
    private static Random random = new Random();

    // Constructor con parámetros
    public Tarjeta(String nombreTitular, int numeroTarjeta, LocalDate fechaVencimiento, int CVV, double saldoTarjeta) {
        this.nombreTitular = nombreTitular;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVencimiento = fechaVencimiento;
        this.CVV = CVV;
        this.saldoTarjeta = saldoTarjeta;
    }

    //genera una tarjeta automática 
//    public static Tarjeta generarTarjeta(String nombre) {;
//        int numTarjeta = numTarjetaAleatorio();
//        int numCVV = numCvvAleatorio();
//        double saldo = saldoCuentaAleatorio();
//        Tarjeta t1 = new Tarjeta(nombre, numTarjeta, LocalDate.now().plusYears(new Random().nextInt(6)), numCVV, Math.round(saldo * 100.0) / 100.0);
//        return t1;
//    }

    private static int numTarjetaAleatorio() {
        return new Random().nextInt(1000,10000);
    }

    private static int numCvvAleatorio() {
        int numRandom = 0;
        //return numRandom = Integer.parseInt(RandomStringUtils.randomNumeric(3));
        return new Random().nextInt(1000);
    }

    private static double saldoCuentaAleatorio() {
        double numAleatorio = 0;
        return numAleatorio = random.nextDouble(10, 100);
    }

    // Constructor vacío
    public Tarjeta() {
    }

    
    // Getters y Setters
    public String getNombreTitular() {
        return nombreTitular;
    }

    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public int getCVV() {
        return CVV;
    }

    public double getSaldoTarjeta() {
        return saldoTarjeta;
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarjeta{");
        sb.append("nombre del titular=").append(nombreTitular);
        sb.append(", numero de la tarjeta=").append(numeroTarjeta);
//        sb.append(", fecha de vencimiento=").append(fechaVencimiento.getMonthValue());
//        sb.append("/");
//        sb.append(fechaVencimiento.getYear());
        sb.append("fecha vencimiento=").append(fechaVencimiento);

        sb.append(", CVV=").append(CVV);
        sb.append(", saldo en la tarjeta=").append(saldoTarjeta);
        sb.append('}');
        return sb.toString();
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public void setSaldoTarjeta(double saldoTarjeta) {
        this.saldoTarjeta = saldoTarjeta;
    }
}
