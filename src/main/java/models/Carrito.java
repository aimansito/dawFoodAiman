/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author aiman
 */
public class Carrito {
    private Map<Producto,Integer> carro ;

    public Carrito(Map<Producto, Integer> carro) {
        this.carro = new TreeMap<>();
    }

    public Carrito() {
    }

    public Map<Producto,Integer> getCarro() {
        return carro;
    }

    public void setCarro(Map<Producto,Integer> carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Carrito{");
        sb.append("carro=").append(carro);
        sb.append("Producto: ").append(this.carro.keySet());
        sb.append("Cantidad: ").append(this.carro.values());
        sb.append('}');
        return sb.toString();
    }
}
