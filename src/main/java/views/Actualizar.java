/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import controllers.DetalleTicketJpaController;
import controllers.ProductoJpaController;
import controllers.TipoProductoJpaController;
import controllers.exceptions.NonexistentEntityException;
import java.awt.Color;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.DetalleTicket;
import models.Producto;
import models.TipoProducto;

/**
 *
 * @author aiman
 */
public class Actualizar extends javax.swing.JDialog {

    private Crud padre;
    private Producto producto;

    /**
     * Creates new form Actualizar
     */
    public Actualizar(Crud parent, boolean modal) {
        super(parent, modal);
        this.padre = parent;
        initComponents();
        mostrarDatosEditar();
        padre.cargarDatosJTable();
    }

    private void mostrarDatosEditar() {
        ProductoJpaController prod = new ProductoJpaController();
        try {
            // Obtengo el id de la persona seleccionada
            // Para ello, obtengo la fila seleccionada y luego el id de esa fila
            int fila = filaSeleccionadaJTable(padre.getJTable());

            // Verifica que una fila esté seleccionada
            if (fila == -1) {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione un producto primero.");
                this.dispose();
                //// Verifica que la tabla tiene al menos una columna
            } else if (padre.getJTable().getColumnCount() == 0) {
                JOptionPane.showMessageDialog(null, "La tabla no tiene columnas");
                this.dispose();
            } else {
                // El id del producto es el valor de la columna cero de esa fila
                int idProducto;
                try {
                    idProducto = (Integer) padre.getJTable().getValueAt(fila, 0);
                } catch (ClassCastException e) {
                    idProducto = Integer.parseInt(padre.getJTable().getValueAt(fila, 0).toString());
                }

                // Guarda el producto seleccionado
                this.producto = prod.findProducto(idProducto);

                // Llena los campos del formulario con los datos del producto
                jTextField1.setText(this.producto.getIdProducto().toString());
                jTextField1.setEditable(false);
                jTextField1.setBackground(Color.GRAY);
                jComboBox1.setSelectedItem(this.producto.getIva());
                jTextField4.setText(this.producto.getStock().toString());
                jTextField3.setText(this.producto.getPrecio().toString());
                jTextField5.setText(this.producto.getDescripcion());
                jComboBox2.setSelectedItem(this.producto.getCodTipoProducto().getNomCat());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Error se ha salido del array.");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al intentar mostrar los datos para editar: " + e.getMessage());
        }
    }

    private int filaSeleccionadaJTable(JTable jTable1) {
        int fila = jTable1.getSelectedRow();
        return fila;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID");

        jTextField1.setText("jTextField1");

        jLabel2.setText("IVA");

        jLabel3.setText("Precio");

        jTextField3.setText("jTextField3");

        jLabel4.setText("Stock");

        jTextField4.setText("jTextField4");

        jLabel5.setText("Descripcion");

        jTextField5.setText("jTextField5");

        jLabel6.setText("Tipo Producto");

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "21" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMIDAS", "BEBIDAS", "POSTRES"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(70, 70, 70))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(205, 205, 205))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        boolean prodEncontrado = false;
        DetalleTicketJpaController dt = new DetalleTicketJpaController();
        List<DetalleTicket> det = dt.findDetalleTicketEntities();
        ProductoJpaController prod = new ProductoJpaController();
        TipoProductoJpaController tipoProd = new TipoProductoJpaController();
        String tipo = jComboBox2.getSelectedItem().toString();
        TipoProducto tProd = tipoProd.getTipoProductoPorNombre(tipo);
        String iva = (String) jComboBox1.getSelectedItem().toString();
        BigDecimal precio = new BigDecimal(jTextField3.getText());
        padre.getListaProductos();
        
        int stock = 0;

        try {
            stock = Integer.parseInt(jTextField4.getText());
            precio = new BigDecimal(jTextField3.getText()).abs();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Introduce un número válido para los campos");
            return;
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "No se ha introducido ningún dato");
            return; 
        }Math.abs(stock);
        Producto p1 = new Producto(Integer.parseInt(jTextField1.getText()), iva, precio, Math.abs(stock), jTextField5.getText(), tProd);
        for (DetalleTicket d : det) {
            if (d.getIdProducto().getIdProducto() == p1.getIdProducto()) {
                JOptionPane.showMessageDialog(null, "Este producto se encuentra en un ticket");
                prodEncontrado = true;
                break; // Salimos del bucle si el producto se encuentra en un ticket
            }
        }
        if (!prodEncontrado) {
            if (jTextField5.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Rellena la descripción");
            } else {
                try {
                    prod.edit(p1);

//        Producto p1 = new Producto(iva, BigDecimal.ZERO, ABORT, descripcion, codTipoProducto)
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Actualizar.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Se ha actualizado correctamente");
                this.dispose();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
