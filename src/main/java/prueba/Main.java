/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import controllers.DetalleTicketJpaController;
import java.util.List;
import models.DetalleTicket;

/**
 *
 * @author aiman
 */
public class Main {
    public static void main(String[] args) {
        DetalleTicketJpaController dt = new DetalleTicketJpaController();
        
        List<DetalleTicket> lista = dt.findDetalleTicketEntities();
        for(DetalleTicket det : lista ){
            System.out.println(det.toString());
        }
    }
}
