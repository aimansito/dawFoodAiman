/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import controllers.ControladoraPersistencia;

/**
 *
 * @author aiman
 */
public class Controladora {
    ControladoraPersistencia c1 = new ControladoraPersistencia();
    
    public void crearTPV(Tpv tpv){
        c1.crearTPV(tpv);
    }
    public void insertarTPV(Tpv tpv){
        c1.insertarTPV(tpv);
    }
}
