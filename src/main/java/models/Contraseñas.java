/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author aiman
 */
public class Contraseñas {
     public static String generarContraseña(){
        String randomLetra = RandomStringUtils.randomAlphabetic(1).toLowerCase();
        String randomLetraMayus = RandomStringUtils.randomAlphabetic(1).toUpperCase();
        String signosRandom = RandomStringUtils.random(1,"#$%&()*+-.:,;>=@");
        String numRandom = RandomStringUtils.randomNumeric(3);
        String randomContraseña = randomLetra+randomLetraMayus+signosRandom+numRandom;
        return randomContraseña;
    }
}
