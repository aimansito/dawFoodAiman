/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import controllers.ProductoJpaController;
import controllers.TipoProductoJpaController;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo_tabla.ListaProducto;
import models.Carrito;
import models.Producto;
import models.TipoProducto;

/**
 *
 * @author aiman
 */
public class Comprar extends javax.swing.JDialog {

    private Escalar escalar = new Escalar();
    private VentanaPrincipal padre;
    private modelo_tabla.ListaProducto listaProducto;
    private Map<Producto, Integer> carritoMap;
    private Carrito carrito;
    private String mensaje;

    /**
     * Creates new form Comprar
     */
    public Comprar(VentanaPrincipal parent, boolean modal) throws IOException {
        super(parent, modal);
        padre = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        this.listaProducto = new ListaProducto();
        escalar.escalarLabel(jLabel1, "/images/fondo2.png");
        carritoMap = new TreeMap<>();
        this.carrito = new Carrito(carritoMap);
        cargarDatosJTable();
        this.mensaje = "";
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Arial Nova", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(225, 166, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Menú Tacos Aiman");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(225, 166, 51)));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 200, 40));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Arial Nova", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(225, 166, 51));
        jButton1.setText("COMIDAS");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 166, 51)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, 40));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Arial Nova", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(225, 166, 51));
        jButton2.setText("BEBIDAS");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 166, 51)));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, 110, 40));

        jButton3.setBackground(new java.awt.Color(0, 0, 0));
        jButton3.setFont(new java.awt.Font("Arial Nova", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(225, 166, 51));
        jButton3.setText("POSTRES");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 166, 51)));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 110, 40));

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setFont(new java.awt.Font("Arial Nova", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 460, 70, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "IVA", "Precio", "Stock","Descripción","Tipo Producto"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 460, 300));

        jButton5.setBackground(new java.awt.Color(0, 0, 0));
        jButton5.setFont(new java.awt.Font("Arial Nova", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(225, 166, 51));
        jButton5.setText("AÑADIR AL CARRITO");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 166, 51)));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 100, 40));

        jButton6.setBackground(new java.awt.Color(0, 0, 0));
        jButton6.setFont(new java.awt.Font("Arial Nova", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(225, 166, 51));
        jButton6.setText("CARRITO");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(225, 166, 51)));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 100, 40));

        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ProductoJpaController prod = new ProductoJpaController();
        List<Producto> prodList = prod.findProductoEntities();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        for (Producto producto : prodList) {
            if (producto.getCodTipoProducto().getNomCat().equalsIgnoreCase("BEBIDAS")) {
                Object[] fila = new Object[6];
                fila[0] = producto.getIdProducto();
                fila[1] = producto.getIva();
                fila[2] = producto.getPrecio();
                fila[3] = producto.getStock();
                fila[4] = producto.getDescripcion();
                fila[5] = producto.getCodTipoProducto().getNomCat();
                modelo.addRow(fila);
            }
        }
        jTable1.setModel(modelo);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ProductoJpaController prod = new ProductoJpaController();
        List<Producto> prodList = prod.findProductoEntities();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        for (Producto producto : prodList) {
            if (producto.getCodTipoProducto().getNomCat().equalsIgnoreCase("COMIDAS")) {
                Object[] fila = new Object[6];
                fila[0] = producto.getIdProducto();
                fila[1] = producto.getIva();
                fila[2] = producto.getPrecio();
                fila[3] = producto.getStock();
                fila[4] = producto.getDescripcion();
                fila[5] = producto.getCodTipoProducto().getNomCat();
                modelo.addRow(fila);
            }
        }
        jTable1.setModel(modelo);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ProductoJpaController prod = new ProductoJpaController();
        List<Producto> prodList = prod.findProductoEntities();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);
        for (Producto producto : prodList) {
            if (producto.getCodTipoProducto().getNomCat().equalsIgnoreCase("POSTRES")) {
                Object[] fila = new Object[6];
                fila[0] = producto.getIdProducto();
                fila[1] = producto.getIva();
                fila[2] = producto.getPrecio();
                fila[3] = producto.getStock();
                fila[4] = producto.getDescripcion();
                fila[5] = producto.getCodTipoProducto().getNomCat();
                modelo.addRow(fila);
            }
        }
        jTable1.setModel(modelo);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        ProductoJpaController prod = new ProductoJpaController();
        TipoProductoJpaController tipo = new TipoProductoJpaController();
        try {
            // Obtengo el id del producto seleccionado
            int fila = filaSeleccionadaJTable(this.jTable1);

            // Verifica que una fila esté seleccionada
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto primero.");
                return;
            }

            // Verifica que la tabla tiene al menos una columna
            if (this.jTable1.getColumnCount() == 0) {
                JOptionPane.showMessageDialog(null, "La tabla no tiene columnas.");
                return;
            }

            // El id del producto es el valor de la columna cero de esa fila
            int idProducto = 0;
            String iva = "";
            BigDecimal precio = BigDecimal.ZERO;
            int stock = 0;
            String descripcion = "";
            TipoProducto tipoProd = null;

            try {
                idProducto = Integer.parseInt(this.jTable1.getValueAt(fila, 0).toString());
                iva = this.jTable1.getValueAt(fila, 1).toString();
                precio = new BigDecimal(this.jTable1.getValueAt(fila, 2).toString());
                stock = Integer.parseInt(this.jTable1.getValueAt(fila, 3).toString());
                descripcion = this.jTable1.getValueAt(fila, 4).toString();

                String codigoTipoProducto = this.jTable1.getValueAt(fila, 5).toString();
                if (codigoTipoProducto.equalsIgnoreCase("COMIDAS")) {
                    tipoProd = tipo.findTipoProducto(1);
                } else if (codigoTipoProducto.equalsIgnoreCase("BEBIDAS")) {
                    tipoProd = tipo.findTipoProducto(2);
                }
            } catch (ClassCastException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error al convertir los valores de la tabla: " + e.getMessage());
                return;
            }

            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Introduce la cantidad que desea de: " + prod.findProducto(idProducto).getDescripcion()));
            Producto p = new Producto(idProducto, iva, precio, stock, descripcion, tipoProd);

            if (cantidad > stock) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock");

//            } else if (this.carritoMap.isEmpty()) {
//                StringBuilder nombresYCantidades = new StringBuilder();
//                this.carritoMap.put(p, cantidad);
//                for (Map.Entry<Producto, Integer> entry : this.carritoMap.entrySet()) {
//                    int cantidadProducto = entry.getValue();
//                    Producto producto = entry.getKey();
//                    nombresYCantidades.append("Producto: ").append(producto.getDescripcion())
//                            .append(", Cantidad: ").append(cantidadProducto).append("\n");
//                }
//                System.out.println(nombresYCantidades);
//                this.carrito.setCarro(this.carritoMap);
//                JOptionPane.showMessageDialog(null, "Se ha añadido el producto al carrito");
            } else {
                this.carritoMap.put(p, cantidad);
//                for (Map.Entry<Producto, Integer> entry : this.carritoMap.entrySet()) {
//                    int cantidadProducto = entry.getValue();
//                    Producto producto = entry.getKey();
//                    mensaje += "Producto: " + producto.getDescripcion() + ", Cantidad: " + cantidadProducto + "\n";
//                }
//                System.out.println(mensaje);
            }
//                // Agregar el producto al carritoMap
//                this.carritoMap.put(cantidad, p);

            // Actualizar el carrito
//                carrito.setCarro(carritoMap);
//                //Recorro el map para meter en el stringbuilder los valores
//                StringBuilder nombresYCantidades = new StringBuilder();
//                for (Map.Entry<Integer, Producto> entry : carritoMap.entrySet()) {
//                    int cantidadProducto = entry.getKey();
//                    Producto producto = entry.getValue();
//                    nombresYCantidades.append("Producto: ").append(producto.getDescripcion())
//                            .append(", Cantidad: ").append(cantidadProducto).append("\n");
//                }
//                JOptionPane.showMessageDialog(null, "Producto añadido al carrito: \n" + nombresYCantidades.toString());            
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error: Se ha salido del array.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar mostrar los datos para editar: " + e.getMessage());
            System.out.println(e.getMessage());
        }
        


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        new CarritoV(this, true,this.carritoMap).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    public void cargarDatosJTable() {

        ProductoJpaController prod = new ProductoJpaController();
        List<Producto> prodList = prod.findProductoEntities();
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setRowCount(0);

        if (jButton1.isSelected()) {
            for (Producto producto : prodList) {
                if (producto.getCodTipoProducto().getNomCat().equalsIgnoreCase("BEBIDAS")) {
                    Object[] fila = new Object[6];
                    fila[0] = producto.getIdProducto();
                    fila[1] = producto.getIva();
                    fila[2] = producto.getPrecio();
                    fila[3] = producto.getStock();
                    fila[4] = producto.getDescripcion();
                    fila[5] = producto.getCodTipoProducto().getNomCat();
                    modelo.addRow(fila);
                }
            }
        } else {
            for (Producto producto : prodList) {
                Object[] fila = new Object[6];
                fila[0] = producto.getIdProducto();
                fila[1] = producto.getIva();
                fila[2] = producto.getPrecio();
                fila[3] = producto.getStock();
                fila[4] = producto.getDescripcion();
                fila[5] = producto.getCodTipoProducto().getNomCat();
                modelo.addRow(fila);
            }
        }
        jTable1.setModel(modelo);

    }

    // Método para obtener la lista de personas cargada en el formulario
    // desde el diálogo
    public ListaProducto getListaProductos() {
        return this.listaProducto;
    }

    // Método para obtener el jtable del formulario
    // desde el diálogo
    public JTable getJTable() {
        return this.jTable1;
    }

    private int filaSeleccionadaJTable(JTable jTable1) {
        int fila = jTable1.getSelectedRow();
        return fila;
    }

    public Map<Producto, Integer> getMap() {
        return this.carritoMap;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
