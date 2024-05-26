/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo_tabla;

import controllers.ProductoJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Producto;

/**
 *
 * @author aiman
 */
public class ListaProducto {

    private ArrayList<Producto> productos;
    private controllers.ProductoJpaController prod;

    public ListaProducto() throws IOException {
        this.productos = new ArrayList<>();
        this.prod = new ProductoJpaController();

        if (this.prod != null) {
            try {
                List<Producto> productoEntities = prod.findProductoEntities();
                if (productoEntities != null) {
                    this.productos = new ArrayList<>(productoEntities);
                } else {
                    throw new IOException("Error al obtener productos: lista nula");
                }
            } catch (Exception e) {
                throw new IOException("Error al obtener productos: " + e.getMessage(), e);
            }
        } else {
            throw new IOException("ProductoJpaController no está inicializado correctamente.");
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public Producto getProducto(int id) {
        System.out.println("id buscado " + id);
        // Habría que controlar que si el id no es valido, hay excepción
        return productos.stream()
                .filter(p -> p.getIdProducto() == id)
                .findFirst()
                .get();
    }
}
