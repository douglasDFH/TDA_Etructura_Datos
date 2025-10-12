/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;
import negocio.clsPila;
import negocio.clsCola;
import negocio.clsListaSimple;
import negocio.clsListaDoble;
import java.awt.Graphics;

import negocio.clsNodo;
import negocio.clsNodoDoble;
/**
 *
 * @author dell
 */
public class frmprincipal extends javax.swing.JFrame {

    /**
     * Creates new form frmprincipal
     */
    private final clsPila objPila;
    private final clsCola objCola;
    private Graphics objPintor;
    private clsListaSimple objListaSimple;
    private clsListaDoble objListaDoble;
    // (Los controles específicos por pestaña no se usan; usamos los botones y txtDato del form)
    public frmprincipal() {
        initComponents();
        objPila = new clsPila();
        objCola = new clsCola();
        objListaSimple = new clsListaSimple();
        objListaDoble = new clsListaDoble();
        objPintor = getGraphics();
        // Configurar event listener para el tabbedPane del .form
        if (tabbedPane != null) {
            tabbedPane.addChangeListener(e -> {
                int selectedIndex = tabbedPane.getSelectedIndex();
                adaptarControlesSegunPestaña(selectedIndex);
                drawSelected();
            });
            tabbedPane.setSelectedIndex(0);
            adaptarControlesSegunPestaña(0); // Configurar interfaz inicial
        }
        // Forzar repaint inicial
        this.repaint();
    }






    private void adaptarControlesSegunPestaña(int selectedIndex) {
        // selectedIndex: 0=Pila, 1=Cola, 2=Lista Simple, 3=Lista Doble
        
        // Controles básicos siempre visibles
        dato.setVisible(true);
        txtDato.setVisible(true);
        jButton1.setVisible(true); // Insertar
        jButton2.setVisible(true); // Eliminar
        
        if (selectedIndex == 0) { // Pestaña Pila
            // Solo controles básicos para Pila
            lblPosicion.setVisible(false);
            txtPosicion.setVisible(false);
            btnInsertarOrdenado.setVisible(false);
            btnInsertarDerecha.setVisible(false);
            btnEliminarDerecha.setVisible(false);
            btnOrdenarAsc.setVisible(false);
            btnOrdenarRef.setVisible(false);
            btnSumarElementos.setVisible(false);
            jButtonBuscar.setVisible(true);
            jButtonVaciar.setVisible(true);
            jButton3.setVisible(false); // Ocultar "Mostrar Pila" cuando ya estamos en Pila
            // Ocultar botones específicos de Lista Doble
            btnInsertarIzquierda.setVisible(false);
            btnEliminarIzquierda.setVisible(false);
            btnRecorridoForward.setVisible(false);
            btnRecorridoBackward.setVisible(false);
            
        } else if (selectedIndex == 1) { // Pestaña Cola
            // Solo controles básicos para Cola
            lblPosicion.setVisible(false);
            txtPosicion.setVisible(false);
            btnInsertarOrdenado.setVisible(false);
            btnInsertarDerecha.setVisible(false);
            btnEliminarDerecha.setVisible(false);
            btnOrdenarAsc.setVisible(false);
            btnOrdenarRef.setVisible(false);
            btnSumarElementos.setVisible(false);
            jButtonBuscar.setVisible(true);
            jButtonVaciar.setVisible(true);
            jButton3.setVisible(true); // Mostrar información de cola
            // Ocultar botones específicos de Lista Doble
            btnInsertarIzquierda.setVisible(false);
            btnEliminarIzquierda.setVisible(false);
            btnRecorridoForward.setVisible(false);
            btnRecorridoBackward.setVisible(false);
            
        } else if (selectedIndex == 2) { // Pestaña Lista Simple
            // Todos los controles avanzados visibles
            lblPosicion.setVisible(true);
            txtPosicion.setVisible(true);
            btnInsertarOrdenado.setVisible(true);
            btnInsertarDerecha.setVisible(true);
            btnEliminarDerecha.setVisible(true);
            btnOrdenarAsc.setVisible(true);
            btnOrdenarRef.setVisible(true);
            btnSumarElementos.setVisible(true);
            jButtonBuscar.setVisible(true);
            jButtonVaciar.setVisible(true);
            jButton3.setVisible(false);
            // Ocultar botones específicos de Lista Doble
            btnInsertarIzquierda.setVisible(false);
            btnEliminarIzquierda.setVisible(false);
            btnRecorridoForward.setVisible(false);
            btnRecorridoBackward.setVisible(false);
            
        } else if (selectedIndex == 3) { // Pestaña Lista Doble
            // Todos los controles para Lista Doble (incluyendo posición y botones específicos)
            lblPosicion.setVisible(true);
            txtPosicion.setVisible(true);
            btnInsertarOrdenado.setVisible(true);
            btnInsertarDerecha.setVisible(true);
            btnEliminarDerecha.setVisible(true);
            btnOrdenarAsc.setVisible(true);
            btnOrdenarRef.setVisible(false); // No implementado para Lista Doble
            btnSumarElementos.setVisible(true);
            jButtonBuscar.setVisible(true);
            jButtonVaciar.setVisible(true);
            jButton3.setVisible(false);
            // Botones específicos de Lista Doble
            btnInsertarIzquierda.setVisible(true);
            btnEliminarIzquierda.setVisible(true);
            btnRecorridoForward.setVisible(true);
            btnRecorridoBackward.setVisible(true);
        }
        
        this.revalidate();
        this.repaint();
    }

    // Método eliminado - mantenemos tooltips originales

    @Override
    public void paint(Graphics g){
        super.paint(g);
        // Mantener objPintor para compatibilidad con tu diseño
        objPintor = g;
        drawSelected();
    }

    private void drawSelected(){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        if (tabbedPane == null) return;
        int idx = tabbedPane.getSelectedIndex();
        switch(idx){
            case 0: graficarPila(objPila.getCima()); break; // Pila
            case 1: graficarCola(objCola.getPrimero()); break; // Cola
            case 2: graficarListaSimple(); break; // Lista Simple
            case 3: graficarListaDoble(); break; // Lista Doble
        }
        repaint(); // Asegurar actualización visual
    }

    public void graficarCola(clsNodo primero){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
        objPintor.drawString("COLA (FIFO - Primero en entrar, primero en salir)", 60, 70);
        int j = 0;
        clsNodo n = primero;
        while(n != null){
            graficarNodo(80 + j*60, 90, ""+n.getDato());
            if(n.getRef() != null) {
                // Dibujar flecha hacia el siguiente
                objPintor.drawString("->", 80 + j*60 + 45, 105);
            }
            n = n.getRef();
            j++;
        }
        if(primero == null) {
            objPintor.drawString("Cola vacía", 80, 110);
        }
    }

    public void graficarListaSimple(){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
        objPintor.drawString("LISTA SIMPLE (Cabeza -> ... -> null)", 60, 70);
        int j = 0;
        clsNodo n = objListaSimple == null ? null : objListaSimple.getCabeza();
        while(n != null){
            graficarNodo(80 + j*60, 90, ""+n.getDato());
            if(n.getRef() != null) {
                objPintor.drawString("->", 80 + j*60 + 45, 105);
            }
            n = n.getRef();
            j++;
        }
        if(objListaSimple.getCabeza() == null) {
            objPintor.drawString("Lista Simple vacía", 80, 110);
        }
    }

    public void graficarListaDoble(){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
        objPintor.drawString("LISTA DOBLE (null <- Head <-> ... <-> Tail -> null)", 60, 70);
        clsNodoDoble cur = objListaDoble == null ? null : objListaDoble.getHead();
        int j = 0;
        while(cur != null){
            graficarNodo(80 + j*70, 90, ""+cur.getDato());
            if(cur.getNext() != null) {
                objPintor.drawString("<->", 80 + j*70 + 45, 105);
            }
            cur = cur.getNext();
            j++;
        }
        if(objListaDoble.getHead() == null) {
            objPintor.drawString("Lista Doble vacía", 80, 110);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        btnEliminarDerecha = new javax.swing.JButton();
        btnInsertarDerecha = new javax.swing.JButton();
        btnInsertarOrdenado = new javax.swing.JButton();
        btnOrdenarAsc = new javax.swing.JButton();
        btnOrdenarRef = new javax.swing.JButton();
        btnSumarElementos = new javax.swing.JButton();
        // Nuevos botones para Lista Doble
        btnInsertarIzquierda = new javax.swing.JButton();
        btnEliminarIzquierda = new javax.swing.JButton();
        btnRecorridoForward = new javax.swing.JButton();
        btnRecorridoBackward = new javax.swing.JButton();
        dato = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonBuscar = new javax.swing.JButton();
        jButtonVaciar = new javax.swing.JButton();
        lblPosicion = new javax.swing.JLabel();
        txtDato = new javax.swing.JTextField();
        txtPosicion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.addTab("Pila", new javax.swing.JLabel("Vista: Pila"));
        tabbedPane.addTab("Cola", new javax.swing.JLabel("Vista: Cola"));
        tabbedPane.addTab("Lista Simple", new javax.swing.JLabel("Vista: Lista Simple"));
        tabbedPane.addTab("Lista Doble", new javax.swing.JLabel("Vista: Lista Doble"));

        jButton1.setText("Insertar");
        jButton1.setToolTipText("Insertar el valor en la estructura seleccionada");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");
        jButton2.setToolTipText("Eliminar (pop/poll/eliminarInicio) en la estructura seleccionada");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        dato.setText("dato");
        txtDato.setToolTipText("Ingrese un número entero aquí");

        jButton3.setText("Mostrar Pila");
        jButton3.setToolTipText("Ir a la pestaña Pila");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.setToolTipText("Buscar valor en la estructura seleccionada");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jButtonVaciar.setText("Vaciar");
        jButtonVaciar.setToolTipText("Vaciar la estructura seleccionada");
        jButtonVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVaciarActionPerformed(evt);
            }
        });

        btnInsertarOrdenado.setText("Ins. Ordenado");
        btnInsertarOrdenado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarOrdenadoActionPerformed(evt);
            }
        });

        btnInsertarDerecha.setText("Ins. Derecha");
        btnInsertarDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarDerechaActionPerformed(evt);
            }
        });

        btnEliminarDerecha.setText("Elim. Derecha");
        btnEliminarDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDerechaActionPerformed(evt);
            }
        });

        btnOrdenarAsc.setText("Ordenar Asc.");
        btnOrdenarAsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarAscActionPerformed(evt);
            }
        });

        btnOrdenarRef.setText("Ordenar Ref.");
        btnOrdenarRef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarRefActionPerformed(evt);
            }
        });

        btnSumarElementos.setText("Sumar");
        btnSumarElementos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSumarElementosActionPerformed(evt);
            }
        });

        // Configuración de botones específicos para Lista Doble
        btnInsertarIzquierda.setText("Ins. Izq.");
        btnInsertarIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarIzquierdaActionPerformed(evt);
            }
        });

        btnEliminarIzquierda.setText("Elim. Izq.");
        btnEliminarIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarIzquierdaActionPerformed(evt);
            }
        });

        btnRecorridoForward.setText("→ Adelante");
        btnRecorridoForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecorridoForwardActionPerformed(evt);
            }
        });

        btnRecorridoBackward.setText("← Atrás");
        btnRecorridoBackward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecorridoBackwardActionPerformed(evt);
            }
        });

        lblPosicion.setText("Posición:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(dato, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblPosicion)
                        .addGap(5, 5, 5)
                        .addComponent(txtPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInsertarOrdenado)
                        .addGap(5, 5, 5)
                        .addComponent(btnInsertarDerecha)
                        .addGap(5, 5, 5)
                        .addComponent(btnEliminarDerecha)
                        .addGap(5, 5, 5)
                        .addComponent(btnOrdenarAsc))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOrdenarRef)
                        .addGap(10, 10, 10)
                        .addComponent(btnSumarElementos)
                        .addGap(20, 20, 20)
                        .addComponent(jButton1)
                        .addGap(10, 10, 10)
                        .addComponent(jButton2)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonBuscar)
                        .addGap(10, 10, 10)
                        .addComponent(jButtonVaciar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInsertarIzquierda)
                        .addGap(10, 10, 10)
                        .addComponent(btnEliminarIzquierda)
                        .addGap(10, 10, 10)
                        .addComponent(btnRecorridoForward)
                        .addGap(10, 10, 10)
                        .addComponent(btnRecorridoBackward))
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jButton3)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dato)
                    .addComponent(txtDato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPosicion)
                    .addComponent(txtPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarOrdenado)
                    .addComponent(btnInsertarDerecha)
                    .addComponent(btnEliminarDerecha)
                    .addComponent(btnOrdenarAsc))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOrdenarRef)
                    .addComponent(btnSumarElementos)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButtonBuscar)
                    .addComponent(jButtonVaciar))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsertarIzquierda)
                    .addComponent(btnEliminarIzquierda)
                    .addComponent(btnRecorridoForward)
                    .addComponent(btnRecorridoBackward))
                .addGap(10, 10, 10)
                .addComponent(jButton3)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try{
         int dato = Integer.parseInt(txtDato.getText());
         int idx = tabbedPane.getSelectedIndex();
         switch(idx){
             case 0: objPila.insert(dato); break; // Pila
             case 1: objCola.insertar(dato); break; // Cola
             case 2: objListaSimple.insertarFinal(dato); break; // Lista Simple
             case 3: objListaDoble.insertarFinal(dato); break; // Lista Doble
         }
         txtDato.setText(""); // Limpiar campo después de insertar
         drawSelected();
     }catch(Exception ex){ System.out.println("Valor inválido"); }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int idx = tabbedPane.getSelectedIndex();
         int dato = -1;
         switch(idx){
              case 0: dato = objPila.eliminar(); break; // Pila
              case 1: dato = objCola.eliminar(); break; // Cola
              case 2: dato = objListaSimple.eliminarInicio(); break; // Lista Simple
              case 3: dato = objListaDoble.eliminarInicio(); break; // Lista Doble
         }
         System.out.println("dato"+dato);
         drawSelected();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int idx = tabbedPane.getSelectedIndex();
        switch(idx){
            case 0: // Pila - Mostrar información en consola
                if(objPila.getCima() != null) {
                    System.out.println("Cima: " + objPila.getCima().getDato());
                } else {
                    System.out.println("Pila vacía");
                }
                break;
            case 1: // Cola - Mostrar información
                if(objCola.getPrimero() != null) {
                    System.out.println("Primero en cola: " + objCola.getPrimero().getDato());
                    System.out.println("Último en cola: " + objCola.getUltimo().getDato());
                } else {
                    System.out.println("Cola vacía");
                }
                break;
            case 2: // Lista Simple - Función especial (recorrer)
                String recorridoLS = objListaSimple.toString();
                System.out.println("Recorrido Lista Simple: " + recorridoLS);
                break;
            case 3: // Lista Doble - Recorrer
                String recorrido = objListaDoble.recorridoForward();
                System.out.println("Recorrido Lista Doble: " + recorrido);
                break;
        }
        drawSelected();
    }//GEN-LAST:event_jButton3ActionPerformed

    public clsPila getPila(){
        return this.objPila;
    }
    
    public void graficarNodo(int px, int py, String dato){
          objPintor.drawRect(px, py, 40 ,20 );
          objPintor.drawString(dato, px+5, py+15);
    }
    
    public void graficarPila(clsNodo cima){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
        objPintor.drawString("PILA (LIFO - Último en entrar, primero en salir)", 60, 70);
        objPintor.drawString("CIMA", 150, 85);
        objPintor.drawString("|", 160, 95);
        objPintor.drawString("v", 160, 105);
        int j = 0;
        clsNodo actual = cima;
        while(actual != null){
            graficarNodo(140, 110 + j*30, ""+actual.getDato());
            if(actual.getRef() != null) {
                objPintor.drawString("|", 160, 135 + j*30);
                objPintor.drawString("v", 160, 145 + j*30);
            }
            actual = actual.getRef();
            j++;
        }
        if(cima == null) {
            objPintor.drawString("Pila vacía", 140, 120);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmprincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmprincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarDerecha;
    private javax.swing.JButton btnInsertarDerecha;
    private javax.swing.JButton btnInsertarOrdenado;
    private javax.swing.JButton btnOrdenarAsc;
    private javax.swing.JButton btnOrdenarRef;
    private javax.swing.JButton btnSumarElementos;
    // Botones específicos para Lista Doble
    private javax.swing.JButton btnInsertarIzquierda;
    private javax.swing.JButton btnEliminarIzquierda;
    private javax.swing.JButton btnRecorridoForward;
    private javax.swing.JButton btnRecorridoBackward;
    private javax.swing.JLabel dato;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonVaciar;
    private javax.swing.JLabel lblPosicion;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTextField txtDato;
    private javax.swing.JTextField txtPosicion;
    // End of variables declaration//GEN-END:variables
    


    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            int val = Integer.parseInt(txtDato.getText());
            int idx = tabbedPane.getSelectedIndex();
            boolean found = false;
            
            switch(idx){
                case 0: // Pila: buscar en toda la pila
                    clsNodo p = objPila.getCima();
                    int posicionPila = 0;
                    java.util.ArrayList<Integer> posicionesPila = new java.util.ArrayList<>();
                    while(p != null){ 
                        if(p.getDato() == val){ 
                            found = true; 
                            posicionesPila.add(posicionPila);
                        } 
                        p = p.getRef(); 
                        posicionPila++;
                    }
                    if(found) {
                        System.out.println("Dato " + val + " encontrado en posición(es): " + posicionesPila);
                    } else {
                        System.out.println("Buscar " + val + " -> false");
                    }
                    break;
                    
                case 1: // Cola: buscar en toda la cola
                    clsNodo pCola = objCola.getPrimero();
                    int posicionCola = 0;
                    java.util.ArrayList<Integer> posicionesCola = new java.util.ArrayList<>();
                    while(pCola != null){ 
                        if(pCola.getDato() == val){ 
                            found = true; 
                            posicionesCola.add(posicionCola);
                        } 
                        pCola = pCola.getRef(); 
                        posicionCola++;
                    }
                    if(found) {
                        System.out.println("Dato " + val + " encontrado en posición(es): " + posicionesCola);
                    } else {
                        System.out.println("Buscar " + val + " -> false");
                    }
                    break;
                    
                case 2: // Lista Simple: buscar con posiciones múltiples
                    int[] posiciones = objListaSimple.buscarTodasLasPosiciones(val);
                    found = posiciones.length > 0;
                    if(found) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("[");
                        for(int i = 0; i < posiciones.length; i++) {
                            sb.append(posiciones[i]);
                            if(i < posiciones.length - 1) sb.append(",");
                        }
                        sb.append("]");
                        System.out.println("Dato " + val + " -> true, posición " + sb.toString());
                    } else {
                        System.out.println("Dato " + val + " -> false");
                    }
                    break;
                    
                case 3: // Lista Doble: buscar con posiciones múltiples
                    int[] posicionesDoble = objListaDoble.buscarTodasLasPosiciones(val);
                    found = posicionesDoble.length > 0;
                    if(found) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("[");
                        for(int i = 0; i < posicionesDoble.length; i++) {
                            sb2.append(posicionesDoble[i]);
                            if(i < posicionesDoble.length - 1) sb2.append(",");
                        }
                        sb2.append("]");
                        System.out.println("Dato " + val + " -> true, posición " + sb2.toString());
                    } else {
                        System.out.println("Dato " + val + " -> false");
                    }
                    break;
            }
            drawSelected(); // Refrescar visualización
        }catch(Exception ex){ System.out.println("Valor inválido"); }
    }

    private void jButtonVaciarActionPerformed(java.awt.event.ActionEvent evt) {
        int idx = tabbedPane.getSelectedIndex();
        switch(idx){
            case 0: objPila.vaciarPila(); break; // Pila
            case 1: objCola.vaciarCola(); break; // Cola
            case 2: objListaSimple.vaciar(); break; // Lista Simple
            case 3: objListaDoble.vaciar(); break; // Lista Doble
        }
        drawSelected();
    }

    // ==================== MÉTODOS DE ACCIÓN PARA LISTA SIMPLE ====================
    
    private void insertarOrdenadoAction() {
        try {
            int valor = Integer.parseInt(txtDato.getText());
            int idx = tabbedPane.getSelectedIndex();
            
            switch(idx) {
                case 1: // Lista Simple
                    objListaSimple.insertarOrdenado(valor);
                    break;
                case 2: // Lista Doble
                    objListaDoble.insertarOrdenado(valor);
                    break;
            }
            
            txtDato.setText("");
            drawSelected();
        } catch (Exception ex) {
            System.out.println("Valor inválido para insertar ordenado");
        }
    }
    
    private void insertarDerechaAction() {
        try {
            int posicion = Integer.parseInt(txtPosicion.getText());
            int valor = Integer.parseInt(txtDato.getText());
            int idx = tabbedPane.getSelectedIndex();
            boolean exito = false;
            
            switch(idx) {
                case 1: // Lista Simple
                    exito = objListaSimple.insertarDerecha(posicion, valor);
                    break;
                case 2: // Lista Doble
                    exito = objListaDoble.insertarDerecha(posicion, valor);
                    break;
            }
            
            if (exito) {
                txtDato.setText("");
                txtPosicion.setText("");
                drawSelected();
            }
        } catch (Exception ex) {
            System.out.println("Valores inválidos para insertar a la derecha");
        }
    }
    
    private void eliminarDerechaAction() {
        try {
            int posicion = Integer.parseInt(txtPosicion.getText());
            int idx = tabbedPane.getSelectedIndex();
            int eliminado = -1;
            
            switch(idx) {
                case 1: // Lista Simple
                    eliminado = objListaSimple.eliminarDerecha(posicion);
                    break;
                case 2: // Lista Doble
                    eliminado = objListaDoble.eliminarDerecha(posicion);
                    break;
            }
            
            if (eliminado != -1) {
                txtPosicion.setText("");
                System.out.println("Eliminado a la derecha de posición " + posicion + ": " + eliminado);
                drawSelected();
            } else {
                System.out.println("No se pudo eliminar: posición inválida o no hay nodo a la derecha");
            }
        } catch (Exception ex) {
            System.out.println("Posición inválida para eliminar a la derecha");
        }
    }
    
    private void ordenarAscendenteAction() {
        int idx = tabbedPane.getSelectedIndex();
        
        switch(idx) {
            case 1: // Lista Simple
                objListaSimple.ordenarAscendente();
                break;
            case 2: // Lista Doble
                objListaDoble.ordenarAscendente();
                break;
        }
        
        drawSelected();
    }
    
    private void ordenarReferenciaAction() {
        objListaSimple.ordenarPorReferencia();
        drawSelected();
    }
    
    private void sumarElementosAction() {
        int idx = tabbedPane.getSelectedIndex();
        int suma = 0;
        int tamaño = 0;
        String tipoLista = "";
        
        switch(idx) {
            case 1: // Lista Simple
                suma = objListaSimple.sumarElementos();
                tamaño = objListaSimple.size();
                tipoLista = "Lista Simple";
                break;
            case 2: // Lista Doble
                suma = objListaDoble.sumarElementos();
                tamaño = objListaDoble.size();
                tipoLista = "Lista Doble";
                break;
        }
        
        if (idx == 1 || idx == 2) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Suma de elementos: " + suma + "\nTamaño de la lista: " + tamaño,
                "Información de " + tipoLista, 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Actualizar solo la visualización
        drawSelected();
    }

    // ==================== MÉTODOS DE EVENTOS PARA ARCHIVO .FORM ====================
    
    private void btnInsertarOrdenadoActionPerformed(java.awt.event.ActionEvent evt) {
        insertarOrdenadoAction();
    }
    
    private void btnInsertarDerechaActionPerformed(java.awt.event.ActionEvent evt) {
        insertarDerechaAction();
    }
    
    private void btnEliminarDerechaActionPerformed(java.awt.event.ActionEvent evt) {
        eliminarDerechaAction();
    }
    
    private void btnOrdenarAscActionPerformed(java.awt.event.ActionEvent evt) {
        ordenarAscendenteAction();
    }
    
    private void btnOrdenarRefActionPerformed(java.awt.event.ActionEvent evt) {
        ordenarReferenciaAction();
    }
    
    private void btnSumarElementosActionPerformed(java.awt.event.ActionEvent evt) {
        sumarElementosAction();
    }
    
    // ==================== MÉTODOS DE EVENTOS PARA LISTA DOBLE ====================
    
    private void btnInsertarIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {
        insertarIzquierdaAction();
    }
    
    private void btnEliminarIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {
        eliminarIzquierdaAction();
    }
    
    private void btnRecorridoForwardActionPerformed(java.awt.event.ActionEvent evt) {
        recorridoForwardAction();
    }
    
    private void btnRecorridoBackwardActionPerformed(java.awt.event.ActionEvent evt) {
        recorridoBackwardAction();
    }

    // ==================== MÉTODOS DE ACCIÓN PARA LISTA DOBLE ====================
    
    private void insertarIzquierdaAction() {
        try {
            int posicion = Integer.parseInt(txtPosicion.getText());
            int valor = Integer.parseInt(txtDato.getText());
            boolean exito = objListaDoble.insertarIzquierda(posicion, valor);
            if (exito) {
                txtDato.setText("");
                txtPosicion.setText("");
                drawSelected();
            }
        } catch (Exception ex) {
            System.out.println("Valores inválidos para insertar a la izquierda");
        }
    }
    
    private void eliminarIzquierdaAction() {
        try {
            int posicion = Integer.parseInt(txtPosicion.getText());
            int eliminado = objListaDoble.eliminarIzquierda(posicion);
            if (eliminado != -1) {
                txtPosicion.setText("");
                System.out.println("Eliminado a la izquierda de posición " + posicion + ": " + eliminado);
                drawSelected();
            } else {
                System.out.println("No se pudo eliminar: posición inválida o no hay nodo a la izquierda");
            }
        } catch (Exception ex) {
            System.out.println("Posición inválida para eliminar a la izquierda");
        }
    }
    
    private void recorridoForwardAction() {
        String recorrido = objListaDoble.recorridoForward();
        System.out.println("Recorrido Forward (Head → Tail): " + recorrido);
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Recorrido Forward (Head → Tail):\n" + recorrido,
            "Lista Doble - Recorrido Directo", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void recorridoBackwardAction() {
        String recorrido = objListaDoble.recorridoBackward();
        System.out.println("Recorrido Backward (Tail ← Head): " + recorrido);
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Recorrido Backward (Tail ← Head):\n" + recorrido,
            "Lista Doble - Recorrido Inverso", 
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    

}
