package controllers;

import models.Tpv;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author aiman
 */
public class ControladoraPersistencia {
    TpvJpaController tpv1 = new TpvJpaController();

    public void crearTPV(Tpv tpv) {
        tpv1.create(tpv);
    }
}
