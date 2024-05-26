/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba;

import controllers.ProductoJpaController;
import controllers.TpvJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import models.Controladora;
import models.Tpv;

/**
 *
 * @author aiman
 */
public class Main {

    private static final TpvJpaController tpv = new TpvJpaController();
    private static final ProductoJpaController prod = new ProductoJpaController();

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {
        Date fechaHora = parsearDate("2024-05-24T00:00:00");
        Tpv t1 = new Tpv("Marruecos", fechaHora, "1234");
        tpv.create(t1);
        tpv.destroy(6);
        tpv.create(t1);
        // NonexistentEntityException
        System.out.println(prod.findProductoEntities());
        
    }

    public static void mostrarTpv() {
        System.out.println("--------- Listado de Vehículos -------------");
        tpv.findTpvEntities().forEach(System.out::println);
        System.out.println("--------------------------------------------");

    }
    private static Date parsearDate(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaParseada = null;
        try {
            fechaParseada = formato.parse(fecha);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "La fecha introducida no es correcta");
        }
        return fechaParseada;
    }
}
