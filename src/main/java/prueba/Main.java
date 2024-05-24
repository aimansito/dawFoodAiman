/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import controllers.TpvJpaController;
import java.util.Date;
import models.Controladora;
import models.Tpv;

/**
 *
 * @author aiman
 */
public class Main {

    private static final TpvJpaController tpv = new TpvJpaController();

    public static void main(String[] args) {
        Date fechaHora = new Date("2024-05-24T00:00:00");
        Tpv t1 = new Tpv("España", fechaHora, "1234");
    }

    public static void mostrarTpv() {
        System.out.println("--------- Listado de Vehículos -------------");
        tpv.findTpvEntities().forEach(System.out::println);
        System.out.println("--------------------------------------------");

    }

}
