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
    
    // Sistema de análisis gráfico
    private java.util.ArrayList<OperacionAnalisis> historialOperaciones;
    private class OperacionAnalisis {
        String estructura;      // "PILA", "COLA", "LISTA_SIMPLE", "LISTA_DOBLE"
        String operacion;       // "INSERTAR", "ELIMINAR", "MOVER_PUNTERO", "VACIAR"
        String valor;           // valor insertado/eliminado o posición del puntero
        String estadoAntes;     // representación del estado antes de la operación
        String estadoDespues;   // representación del estado después de la operación
        String descripcion;     // descripción textual de lo que pasó
        
        public OperacionAnalisis(String estructura, String operacion, String valor, 
                               String antes, String despues) {
            this.estructura = estructura;
            this.operacion = operacion;
            this.valor = valor != null ? valor : "";
            this.estadoAntes = antes;
            this.estadoDespues = despues;
            this.descripcion = "";
        }
        
        public OperacionAnalisis(String estructura, String operacion, String valor, 
                               String antes, String despues, String desc) {
            this.estructura = estructura;
            this.operacion = operacion;
            this.valor = valor != null ? valor : "";
            this.estadoAntes = antes;
            this.estadoDespues = despues;
            this.descripcion = desc;
        }
    }
    // (Los controles específicos por pestaña no se usan; usamos los botones y txtDato del form)
    public frmprincipal() {
        initComponents();
        objPila = new clsPila();
        objCola = new clsCola();
        objListaSimple = new clsListaSimple();
        objListaDoble = new clsListaDoble();
        objPintor = getGraphics();
        
        // Inicializar sistema de análisis
        historialOperaciones = new java.util.ArrayList<>();
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
        // selectedIndex: 0=Pila, 1=Cola, 2=Lista Simple, 3=Lista Doble esta son las pestañas segun la necesidad 
        
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
        
        // Actualizar información del puntero según la pestaña seleccionada
        switch(idx){
            case 0: 
                graficarPila(objPila.getCima()); 
                lblInfoPuntero.setText("Puntero: " + objPila.obtenerInfoPuntero());
                break; // Pila
            case 1: 
                graficarCola(objCola.getPrimero()); 
                lblInfoPuntero.setText("Puntero: " + objCola.obtenerInfoPuntero());
                break; // Cola
            case 2: 
                graficarListaSimple(); 
                lblInfoPuntero.setText("Puntero: " + (objListaSimple.getPunteroActual() != null ? objListaSimple.getPunteroActual().getDato() : "Nulo"));
                break; // Lista Simple
            case 3: 
                graficarListaDoble(); 
                lblInfoPuntero.setText("Puntero: " + (objListaDoble.getPunteroActual() != null ? objListaDoble.getPunteroActual().getDato() : "Nulo"));
                break; // Lista Doble
        }
        repaint(); // Asegurar actualización visual
    }

    public void graficarCola(clsNodo primero){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
        //objPintor.drawString("COLA", 60, 70);
        objPintor.drawString("(FIFO - Primero en entrar, primero en salir)", 60, 85);
        int j = 0;
        clsNodo n = primero;
        clsNodo puntero = objCola.getPunteroActual();
        while(n != null){
            // Determinar si este nodo es donde está el puntero
            boolean esPuntero = (n == puntero);
            
            // Dibujar nodo normal
            objPintor.drawRect(80 + j*60, 105, 40, 20);
            objPintor.drawString(""+n.getDato(), 85 + j*60, 120);
            
            // Dibujar indicador del puntero si está activado y es el nodo correcto
            if (esPuntero) {
                dibujarIndicadorPuntero(80 + j*60, 105);
            }
            
            if(n.getRef() != null) {
                // Dibujar flecha hacia el siguiente
                objPintor.drawString("->", 80 + j*60 + 45, 120);
            }
            n = n.getRef();
            j++;
        }
        if(primero == null) {
            objPintor.drawString("Cola vacía", 80, 125);
        }
    }

    public void graficarListaSimple(){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
       // objPintor.drawString("LISTA SIMPLE", 60, 70);
        objPintor.drawString("(Cabeza -> ... -> null)", 60, 85);
        int j = 0;
        clsNodo n = objListaSimple == null ? null : objListaSimple.getCabeza();
        clsNodo puntero = objListaSimple.getPunteroActual();
        while(n != null){
            // Determinar si este nodo es donde está el puntero
            boolean esPuntero = (n == puntero);
            
            // Dibujar nodo normal
            objPintor.drawRect(80 + j*60, 105, 40, 20);
            objPintor.drawString(""+n.getDato(), 85 + j*60, 120);
            
            // Dibujar indicador del puntero si está activado y es el nodo correcto
            if (esPuntero) {
                dibujarIndicadorPuntero(80 + j*60, 105);
            }
            
            if(n.getRef() != null) {
                objPintor.drawString("->", 80 + j*60 + 45, 120);
            }
            n = n.getRef();
            j++;
        }
        if(objListaSimple.getCabeza() == null) {
            objPintor.drawString("Lista Simple vacía", 80, 125);
        }
    }

    public void graficarListaDoble(){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
        //objPintor.drawString("LISTA DOBLE", 60, 70);
        objPintor.drawString("(null <- cabeza <-> ... <-> cola -> null)", 60, 85);
        clsNodoDoble cur = objListaDoble == null ? null : objListaDoble.getCabeza();
        clsNodoDoble puntero = objListaDoble.getPunteroActual();
        int j = 0;
        while(cur != null){
            // Determinar si este nodo es donde está el puntero
            boolean esPuntero = (cur == puntero);
            
            // Dibujar nodo normal
            objPintor.drawRect(80 + j*70, 105, 40, 20);
            objPintor.drawString(""+cur.getDato(), 85 + j*70, 120);
            
            // Dibujar indicador del puntero si está activado y es el nodo correcto
            if (esPuntero) {
                dibujarIndicadorPuntero(80 + j*70, 105);
            }
            
            if(cur.getNext() != null) {
                objPintor.drawString("<->", 80 + j*70 + 45, 120);
            }
            cur = cur.getNext();
            j++;
        }
        if(objListaDoble.getCabeza() == null) {
            objPintor.drawString("Lista Doble vacía", 80, 125);
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
        // Botones de navegación del puntero
        btnPunteroInicio = new javax.swing.JButton();
        btnPunteroSiguiente = new javax.swing.JButton();
        btnPunteroAnterior = new javax.swing.JButton();
        btnPunteroFinal = new javax.swing.JButton();
        btnTogglePuntero = new javax.swing.JButton();
        btnAnalisis = new javax.swing.JButton();
        lblInfoPuntero = new javax.swing.JLabel();
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

        tabbedPane.addTab("Pila", new javax.swing.JLabel(""));
        tabbedPane.addTab("Cola", new javax.swing.JLabel(""));
        tabbedPane.addTab("Lista Simple", new javax.swing.JLabel(""));
        tabbedPane.addTab("Lista Doble", new javax.swing.JLabel(""));

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
        btnInsertarDerecha.setToolTipText("Insertar a la derecha de la posición del puntero actual");
        btnInsertarDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarDerechaActionPerformed(evt);
            }
        });

        btnEliminarDerecha.setText("Elim. Derecha");
        btnEliminarDerecha.setToolTipText("Eliminar el nodo a la derecha de la posición del puntero actual");
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
        btnInsertarIzquierda.setToolTipText("Insertar a la izquierda de la posición del puntero actual");
        btnInsertarIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertarIzquierdaActionPerformed(evt);
            }
        });

        btnEliminarIzquierda.setText("Elim. Izq.");
        btnEliminarIzquierda.setToolTipText("Eliminar el nodo a la izquierda de la posición del puntero actual");
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

        // Configuración de botones de navegación del puntero
        btnPunteroInicio.setText("⟦⤶⟧");
        btnPunteroInicio.setToolTipText("Ir al inicio");
        btnPunteroInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPunteroInicioActionPerformed(evt);
            }
        });

        btnPunteroAnterior.setText("◀");
        btnPunteroAnterior.setToolTipText("Anterior");
        btnPunteroAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPunteroAnteriorActionPerformed(evt);
            }
        });

        btnPunteroSiguiente.setText("▶");
        btnPunteroSiguiente.setToolTipText("Siguiente");
        btnPunteroSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPunteroSiguienteActionPerformed(evt);
            }
        });

        btnPunteroFinal.setText("⟦⤷⟧");
        btnPunteroFinal.setToolTipText("Ir al final");
        btnPunteroFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPunteroFinalActionPerformed(evt);
            }
        });

        btnTogglePuntero.setText("⬛");
        btnTogglePuntero.setToolTipText("Mostrar/Ocultar indicador visual del puntero");
        btnTogglePuntero.setBackground(java.awt.Color.BLUE);
        btnTogglePuntero.setForeground(java.awt.Color.WHITE);
        btnTogglePuntero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogglePunteroActionPerformed(evt);
            }
        });

        btnAnalisis.setText("📊 Análisis");
        btnAnalisis.setToolTipText("Ver análisis paso a paso de las operaciones");
        btnAnalisis.setBackground(java.awt.Color.GREEN);
        btnAnalisis.setForeground(java.awt.Color.WHITE);
        btnAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalisisActionPerformed(evt);
            }
        });

        lblInfoPuntero.setText("Puntero: Inicio");
        lblInfoPuntero.setForeground(java.awt.Color.BLUE);

        lblPosicion.setText("Posición (solo para inserción ordenada):");

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPunteroInicio)
                        .addGap(5, 5, 5)
                        .addComponent(btnPunteroAnterior)
                        .addGap(5, 5, 5)
                        .addComponent(btnTogglePuntero)
                        .addGap(5, 5, 5)
                        .addComponent(btnPunteroSiguiente)
                        .addGap(5, 5, 5)
                        .addComponent(btnPunteroFinal)
                        .addGap(15, 15, 15)
                        .addComponent(lblInfoPuntero)
                        .addGap(20, 20, 20)
                        .addComponent(btnAnalisis))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPunteroInicio)
                    .addComponent(btnPunteroAnterior)
                    .addComponent(btnTogglePuntero)
                    .addComponent(btnPunteroSiguiente)
                    .addComponent(btnPunteroFinal)
                    .addComponent(lblInfoPuntero)
                    .addComponent(btnAnalisis))
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
         
         // Verificar si las estructuras están vacías antes de insertar
         boolean eraVacia = false;
         switch(idx){
             case 0: 
                 eraVacia = objPila.estaVacia();
                 String estadoAntes0 = obtenerEstadoPila();
                 objPila.insert(dato); 
                 String estadoDespues0 = obtenerEstadoPila();
                 registrarOperacion("PILA", "PUSH", String.valueOf(dato), estadoAntes0, estadoDespues0);
                 // Inicializar puntero automáticamente si era la primera inserción
                 if (eraVacia) objPila.moverPunteroInicio();
                 break; // Pila
             case 1: 
                 eraVacia = objCola.estaVacia();
                 String estadoAntes1 = obtenerEstadoCola();
                 objCola.insertar(dato); 
                 String estadoDespues1 = obtenerEstadoCola();
                 registrarOperacion("COLA", "ENQUEUE", String.valueOf(dato), estadoAntes1, estadoDespues1);
                 // Inicializar puntero automáticamente si era la primera inserción
                 if (eraVacia) objCola.moverPunteroInicio();
                 break; // Cola
             case 2: 
                 eraVacia = objListaSimple.estaVacia();
                 String estadoAntes2 = obtenerEstadoListaSimple();
                 objListaSimple.insertarFinal(dato); 
                 String estadoDespues2 = obtenerEstadoListaSimple();
                 registrarOperacion("LISTA_SIMPLE", "INSERTAR_FINAL", String.valueOf(dato), estadoAntes2, estadoDespues2);
                 // Inicializar puntero automáticamente si era la primera inserción
                 if (eraVacia) objListaSimple.moverPunteroInicio();
                 break; // Lista Simple
             case 3: 
                 eraVacia = objListaDoble.estaVacia();
                 String estadoAntes3 = obtenerEstadoListaDoble();
                 objListaDoble.insertarFinal(dato); 
                 String estadoDespues3 = obtenerEstadoListaDoble();
                 registrarOperacion("LISTA_DOBLE", "INSERTAR_FINAL", String.valueOf(dato), estadoAntes3, estadoDespues3);
                 // Inicializar puntero automáticamente si era la primera inserción
                 if (eraVacia) objListaDoble.moverPunteroInicio();
                 break; // Lista Doble
         }
         txtDato.setText(""); // Limpiar campo después de insertar
         drawSelected();
     }catch(Exception ex){ System.out.println("Valor inválido"); }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int idx = tabbedPane.getSelectedIndex();
         int dato = -1;
         switch(idx){
              case 0: 
                  String estadoAntes0 = obtenerEstadoPila();
                  dato = objPila.eliminar(); 
                  String estadoDespues0 = obtenerEstadoPila();
                  if (dato != -1) {
                      registrarOperacion("PILA", "POP", String.valueOf(dato), estadoAntes0, estadoDespues0);
                  }
                  break; // Pila
              case 1: 
                  String estadoAntes1 = obtenerEstadoCola();
                  dato = objCola.eliminar(); 
                  String estadoDespues1 = obtenerEstadoCola();
                  if (dato != -1) {
                      registrarOperacion("COLA", "DEQUEUE", String.valueOf(dato), estadoAntes1, estadoDespues1);
                  }
                  break; // Cola
              case 2: 
                  String estadoAntes2 = obtenerEstadoListaSimple();
                  dato = objListaSimple.eliminarInicio(); 
                  String estadoDespues2 = obtenerEstadoListaSimple();
                  if (dato != -1) {
                      registrarOperacion("LISTA_SIMPLE", "ELIMINAR_INICIO", String.valueOf(dato), estadoAntes2, estadoDespues2);
                  }
                  break; // Lista Simple
              case 3: 
                  String estadoAntes3 = obtenerEstadoListaDoble();
                  dato = objListaDoble.eliminarInicio(); 
                  String estadoDespues3 = obtenerEstadoListaDoble();
                  if (dato != -1) {
                      registrarOperacion("LISTA_DOBLE", "ELIMINAR_INICIO", String.valueOf(dato), estadoAntes3, estadoDespues3);
                  }
                  break; // Lista Doble
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
    
    public void graficarNodoConPuntero(int px, int py, String dato, boolean esPuntero){
        // Guardar color original
        java.awt.Color colorOriginal = objPintor.getColor();
        
        if (esPuntero) {
            // Cambiar a color rojo para resaltar el puntero
            objPintor.setColor(java.awt.Color.RED);
            objPintor.fillRect(px, py, 40, 20);
            objPintor.setColor(java.awt.Color.WHITE);
            objPintor.drawString(dato, px+5, py+15);
            objPintor.setColor(java.awt.Color.RED);
            objPintor.drawRect(px, py, 40, 20);
        } else {
            // Nodo normal
            objPintor.drawRect(px, py, 40, 20);
            objPintor.drawString(dato, px+5, py+15);
        }
        
        // Restaurar color original
        objPintor.setColor(colorOriginal);
    }
    
    public void graficarNodoDobleConPuntero(int px, int py, String dato, boolean esPuntero){
        // Guardar color original
        java.awt.Color colorOriginal = objPintor.getColor();
        
        if (esPuntero) {
            // Cambiar a color azul para resaltar el puntero en lista doble
            objPintor.setColor(java.awt.Color.BLUE);
            objPintor.fillRect(px, py, 40, 20);
            objPintor.setColor(java.awt.Color.WHITE);
            objPintor.drawString(dato, px+5, py+15);
            objPintor.setColor(java.awt.Color.BLUE);
            objPintor.drawRect(px, py, 40, 20);
        } else {
            // Nodo normal
            objPintor.drawRect(px, py, 40, 20);
            objPintor.drawString(dato, px+5, py+15);
        }
        
        // Restaurar color original
        objPintor.setColor(colorOriginal);
    }
    
    /**
     * Dibujar indicador visual del puntero (cuadrado azul superpuesto)
     */
    public void dibujarIndicadorPuntero(int px, int py) {
        if (mostrarPunteroVisual) {
            // Guardar color original
            java.awt.Color colorOriginal = objPintor.getColor();
            
            // Dibujar cuadrado azul semitransparente más grande que el nodo
            objPintor.setColor(new java.awt.Color(0, 0, 255, 100)); // Azul semitransparente
            objPintor.fillRect(px - 3, py - 3, 46, 26); // Ligeramente más grande que el nodo
            
            // Dibujar borde azul sólido
            objPintor.setColor(java.awt.Color.BLUE);
            objPintor.drawRect(px - 3, py - 3, 46, 26);
            objPintor.drawRect(px - 2, py - 2, 44, 24); // Doble borde para mayor visibilidad
            
            // Restaurar color original
            objPintor.setColor(colorOriginal);
        }
    }
    
    public void graficarPila(clsNodo cima){
        if (objPintor == null) objPintor = getGraphics();
        if (objPintor == null) return;
        // Limpiar área de dibujo en la zona de pestañas
        objPintor.clearRect(50, 50, 600, 200);
      //  objPintor.drawString("PILA", 60, 70);
        objPintor.drawString("(LIFO - Último en entrar, primero en salir)", 60, 85);
        objPintor.drawString("CIMA", 150, 100);
        objPintor.drawString("|", 160, 110);
        objPintor.drawString("v", 160, 120);
        int j = 0;
        clsNodo actual = cima;
        clsNodo puntero = objPila.getPunteroActual();
        while(actual != null){
            // Determinar si este nodo es donde está el puntero
            boolean esPuntero = (actual == puntero);
            
            // Dibujar nodo normal
            objPintor.drawRect(140, 125 + j*30, 40, 20);
            objPintor.drawString(""+actual.getDato(), 145, 140 + j*30);
            
            // Dibujar indicador del puntero si está activado y es el nodo correcto
            if (esPuntero) {
                dibujarIndicadorPuntero(140, 125 + j*30);
            }
            
            if(actual.getRef() != null) {
                objPintor.drawString("|", 160, 150 + j*30);
                objPintor.drawString("v", 160, 160 + j*30);
            }
            actual = actual.getRef();
            j++;
        }
        if(cima == null) {
            objPintor.drawString("Pila vacía", 140, 135);
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
    // Botones de navegación del puntero
    private javax.swing.JButton btnPunteroInicio;
    private javax.swing.JButton btnPunteroSiguiente;
    private javax.swing.JButton btnPunteroAnterior;
    private javax.swing.JButton btnPunteroFinal;
    private javax.swing.JButton btnTogglePuntero;
    private javax.swing.JButton btnAnalisis;
    private javax.swing.JLabel lblInfoPuntero;
    // Control de visualización del puntero
    private boolean mostrarPunteroVisual = true;
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
                    
                case 3: // Lista Doble: buscar with posiciones múltiples
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
            case 0: 
                String estadoAntes0 = obtenerEstadoPila();
                objPila.vaciarPila(); 
                String estadoDespues0 = obtenerEstadoPila();
                registrarOperacion("PILA", "VACIAR", "", estadoAntes0, estadoDespues0);
                break; // Pila
            case 1: 
                String estadoAntes1 = obtenerEstadoCola();
                objCola.vaciarCola(); 
                String estadoDespues1 = obtenerEstadoCola();
                registrarOperacion("COLA", "VACIAR", "", estadoAntes1, estadoDespues1);
                break; // Cola
            case 2: 
                String estadoAntes2 = obtenerEstadoListaSimple();
                objListaSimple.vaciar(); 
                String estadoDespues2 = obtenerEstadoListaSimple();
                registrarOperacion("LISTA_SIMPLE", "VACIAR", "", estadoAntes2, estadoDespues2);
                break; // Lista Simple
            case 3: 
                String estadoAntes3 = obtenerEstadoListaDoble();
                objListaDoble.vaciar(); 
                String estadoDespues3 = obtenerEstadoListaDoble();
                registrarOperacion("LISTA_DOBLE", "VACIAR", "", estadoAntes3, estadoDespues3);
                break; // Lista Doble
        }
        drawSelected();
    }

    // ==================== MÉTODOS DE ACCIÓN PARA LISTA SIMPLE ====================
    
    private void insertarOrdenadoAction() {
        try {
            int valor = Integer.parseInt(txtDato.getText());
            int idx = tabbedPane.getSelectedIndex();
            
            switch(idx) {
                case 2: // Lista Simple
                    String estadoAntes2 = obtenerEstadoListaSimple();
                    objListaSimple.insertarOrdenado(valor);
                    String estadoDespues2 = obtenerEstadoListaSimple();
                    registrarOperacion("LISTA_SIMPLE", "INSERTAR_ORDENADO", String.valueOf(valor), estadoAntes2, estadoDespues2);
                    break;
                case 3: // Lista Doble
                    String estadoAntes3 = obtenerEstadoListaDoble();
                    objListaDoble.insertarOrdenado(valor);
                    String estadoDespues3 = obtenerEstadoListaDoble();
                    registrarOperacion("LISTA_DOBLE", "INSERTAR_ORDENADO", String.valueOf(valor), estadoAntes3, estadoDespues3);
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
            int valor = Integer.parseInt(txtDato.getText());
            int idx = tabbedPane.getSelectedIndex();
            boolean exito = false;
            int posicionPuntero = -1;
            
            switch(idx) {
                case 2: // Lista Simple
                    if (objListaSimple.esPunteroNulo()) {
                        javax.swing.JOptionPane.showMessageDialog(this, 
                            "El puntero no está posicionado. Use los botones de navegación para posicionar el puntero primero.",
                            "Puntero no posicionado", 
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    posicionPuntero = objListaSimple.getPosicionPuntero();
                    String estadoAntes2 = obtenerEstadoListaSimple();
                    exito = objListaSimple.insertarDerecha(posicionPuntero, valor);
                    if (exito) {
                        String estadoDespues2 = obtenerEstadoListaSimple();
                        registrarOperacion("LISTA_SIMPLE", "INSERTAR_DERECHA", String.valueOf(valor), estadoAntes2, estadoDespues2);
                    }
                    break;
                case 3: // Lista Doble
                    if (objListaDoble.esPunteroNulo()) {
                        javax.swing.JOptionPane.showMessageDialog(this, 
                            "El puntero no está posicionado. Use los botones de navegación para posicionar el puntero primero.",
                            "Puntero no posicionado", 
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    posicionPuntero = objListaDoble.getPosicionPuntero();
                    String estadoAntes3 = obtenerEstadoListaDoble();
                    exito = objListaDoble.insertarDerecha(posicionPuntero, valor);
                    if (exito) {
                        String estadoDespues3 = obtenerEstadoListaDoble();
                        registrarOperacion("LISTA_DOBLE", "INSERTAR_DERECHA", String.valueOf(valor), estadoAntes3, estadoDespues3);
                    }
                    break;
            }
            
            if (exito) {
                txtDato.setText("");
                System.out.println("Insertado valor " + valor + " a la derecha del puntero (posición " + posicionPuntero + ")");
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Valor " + valor + " insertado a la derecha del puntero\n(Posición: " + posicionPuntero + ")",
                    "Inserción Exitosa", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                drawSelected();
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Ingrese un valor numérico válido",
                "Error de Entrada", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            System.out.println("Error al insertar a la derecha: " + ex.getMessage());
        }
    }
    
    private void eliminarDerechaAction() {
        try {
            int idx = tabbedPane.getSelectedIndex();
            int eliminado = -1;
            int posicionPuntero = -1;
            
            switch(idx) {
                case 2: // Lista Simple
                    if (objListaSimple.esPunteroNulo()) {
                        javax.swing.JOptionPane.showMessageDialog(this, 
                            "El puntero no está posicionado. Use los botones de navegación para posicionar el puntero primero.",
                            "Puntero no posicionado", 
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    posicionPuntero = objListaSimple.getPosicionPuntero();
                    String estadoAntes2 = obtenerEstadoListaSimple();
                    eliminado = objListaSimple.eliminarDerecha(posicionPuntero);
                    if (eliminado != -1) {
                        String estadoDespues2 = obtenerEstadoListaSimple();
                        registrarOperacion("LISTA_SIMPLE", "ELIMINAR_DERECHA", String.valueOf(eliminado), estadoAntes2, estadoDespues2);
                    }
                    break;
                case 3: // Lista Doble
                    if (objListaDoble.esPunteroNulo()) {
                        javax.swing.JOptionPane.showMessageDialog(this, 
                            "El puntero no está posicionado. Use los botones de navegación para posicionar el puntero primero.",
                            "Puntero no posicionado", 
                            javax.swing.JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    posicionPuntero = objListaDoble.getPosicionPuntero();
                    String estadoAntes3 = obtenerEstadoListaDoble();
                    eliminado = objListaDoble.eliminarDerecha(posicionPuntero);
                    if (eliminado != -1) {
                        String estadoDespues3 = obtenerEstadoListaDoble();
                        registrarOperacion("LISTA_DOBLE", "ELIMINAR_DERECHA", String.valueOf(eliminado), estadoAntes3, estadoDespues3);
                    }
                    break;
            }
            
            if (eliminado != -1) {
                System.out.println("Eliminado a la derecha del puntero (posición " + posicionPuntero + "): " + eliminado);
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Eliminado valor " + eliminado + " a la derecha del puntero\n(Posición: " + posicionPuntero + ")",
                    "Eliminación Exitosa", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                drawSelected();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No se pudo eliminar: no hay nodo a la derecha del puntero actual",
                    "No se puede eliminar", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Error al eliminar a la derecha: " + ex.getMessage());
        }
    }
    
    private void ordenarAscendenteAction() {
        int idx = tabbedPane.getSelectedIndex();
        
        switch(idx) {
            case 2: // Lista Simple
                objListaSimple.ordenarAscendente();
                break;
            case 3: // Lista Doble
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
            case 2: // Lista Simple
                suma = objListaSimple.sumarElementos();
                tamaño = objListaSimple.size();
                tipoLista = "Lista Simple";
                break;
            case 3: // Lista Doble
                suma = objListaDoble.sumarElementos();
                tamaño = objListaDoble.size();
                tipoLista = "Lista Doble";
                break;
        }
        
        if (idx == 2 || idx == 3) {
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
            // TDA CONSISTENCIA: Verificar que el valor sea válido
            if (txtDato.getText().trim().isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Debe ingresar un valor numérico.",
                    "Campo Vacío - TDA", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int valor = Integer.parseInt(txtDato.getText());
            
            // TDA CONSISTENCIA: Verificar que la lista no esté vacía
            if (objListaDoble.estaVacia()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "La lista doble está vacía.\nUse el botón 'Insertar' para agregar el primer elemento.",
                    "Lista Vacía - TDA", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // TDA CONSISTENCIA: Verificar que el puntero esté posicionado
            if (objListaDoble.esPunteroNulo()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "El puntero no está posicionado.\nUse los botones de navegación para posicionar el puntero primero.",
                    "Puntero no posicionado - TDA", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int posicionPuntero = objListaDoble.getPosicionPuntero();
            
            String estadoAntes = obtenerEstadoListaDoble();
            boolean exito = objListaDoble.insertarIzquierda(posicionPuntero, valor);
            if (exito) {
                String estadoDespues = obtenerEstadoListaDoble();
                registrarOperacion("LISTA_DOBLE", "INSERTAR_IZQUIERDA", String.valueOf(valor), estadoAntes, estadoDespues);
                txtDato.setText("");
                System.out.println("TDA: Insertado valor " + valor + " a la izquierda del puntero (posición " + posicionPuntero + ")");
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Valor " + valor + " insertado a la izquierda del puntero\n(Posición: " + posicionPuntero + ")\n\nEl puntero ahora está en posición " + (posicionPuntero + 1),
                    "Inserción Exitosa - TDA", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                drawSelected();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No se pudo insertar el valor.\nVerifique la integridad de la estructura.",
                    "Error TDA", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Ingrese un valor numérico entero válido.\nEjemplo: 10, 25, -5, etc.",
                "Error de Entrada - TDA", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            System.out.println("Error al insertar a la izquierda: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error inesperado al insertar: " + ex.getMessage(),
                "Error de Sistema", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarIzquierdaAction() {
        try {
            // TDA CONSISTENCIA: Verificar que la lista no esté vacía
            if (objListaDoble.estaVacia()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "La lista doble está vacía. No hay elementos para eliminar.",
                    "Lista Vacía", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // TDA CONSISTENCIA: Verificar que el puntero esté posicionado
            if (objListaDoble.esPunteroNulo()) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "El puntero no está posicionado. Use los botones de navegación para posicionar el puntero primero.",
                    "Puntero no posicionado", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int posicionPuntero = objListaDoble.getPosicionPuntero();
            
            // TDA CONSISTENCIA: No se puede eliminar a la izquierda de posición 0
            if (posicionPuntero <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "No se puede eliminar a la izquierda del primer elemento (posición 0).\n" +
                    "Principio TDA: No existe elemento anterior al primer nodo.",
                    "Operación Inválida - Principio TDA", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String estadoAntes = obtenerEstadoListaDoble();
            int eliminado = objListaDoble.eliminarIzquierda(posicionPuntero);
            
            if (eliminado != -1) {
                String estadoDespues = obtenerEstadoListaDoble();
                registrarOperacion("LISTA_DOBLE", "ELIMINAR_IZQUIERDA", String.valueOf(eliminado), estadoAntes, estadoDespues);
                System.out.println("TDA: Eliminado a la izquierda del puntero (posición " + posicionPuntero + "): " + eliminado);
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Eliminado valor " + eliminado + " a la izquierda del puntero\n(Posición: " + posicionPuntero + ")",
                    "Eliminación Exitosa", 
                    javax.swing.JOptionPane.INFORMATION_MESSAGE);
                drawSelected();
            } else {
                // Este caso no debería ocurrir con las validaciones previas, pero mantenemos por seguridad
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Error interno: No se pudo realizar la eliminación.\nVerifique la integridad de la estructura.",
                    "Error TDA", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Error al eliminar a la izquierda: " + ex.getMessage());
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Error inesperado al eliminar: " + ex.getMessage(),
                "Error de Sistema", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
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
    
    // Métodos de acción para navegación del puntero
    private void btnPunteroInicioActionPerformed(java.awt.event.ActionEvent evt) {
        int idx = tabbedPane.getSelectedIndex();
        switch(idx) {
            case 0: // Pila
                if (objPila.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La pila está vacía. No hay elementos para navegar.",
                        "Pila Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objPila.moverPunteroInicio();
                lblInfoPuntero.setText("Puntero: " + objPila.obtenerInfoPuntero());
                break;
            case 1: // Cola
                if (objCola.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La cola está vacía. No hay elementos para navegar.",
                        "Cola Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objCola.moverPunteroInicio();
                lblInfoPuntero.setText("Puntero: " + objCola.obtenerInfoPuntero());
                break;
            case 2: // Lista Simple
                if (objListaSimple.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista simple está vacía. No hay elementos para navegar.",
                        "Lista Simple Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String estadoAntes = obtenerEstadoListaSimple();
                objListaSimple.moverPunteroInicio();
                String estadoDespues = obtenerEstadoListaSimple();
                registrarOperacion("LISTA_SIMPLE", "MOVER_PUNTERO_INICIO", "", estadoAntes, estadoDespues);
                lblInfoPuntero.setText("Puntero: " + objListaSimple.obtenerInfoPuntero());
                break;
            case 3: // Lista Doble
                if (objListaDoble.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista doble está vacía. No hay elementos para navegar.",
                        "Lista Doble Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String estadoAntes3 = obtenerEstadoListaDoble();
                objListaDoble.moverPunteroInicio();
                String estadoDespues3 = obtenerEstadoListaDoble();
                registrarOperacion("LISTA_DOBLE", "MOVER_PUNTERO_INICIO", "", estadoAntes3, estadoDespues3);
                lblInfoPuntero.setText("Puntero: " + objListaDoble.obtenerInfoPuntero());
                break;
        }
        drawSelected();
    }
    
    private void btnPunteroSiguienteActionPerformed(java.awt.event.ActionEvent evt) {
        int idx = tabbedPane.getSelectedIndex();
        switch(idx) {
            case 0: // Pila
                if (objPila.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La pila está vacía. No hay elementos para navegar.",
                        "Pila Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!objPila.moverPunteroSiguiente()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Ya está en el último elemento de la pila.\nPrincipio TDA: No hay siguiente elemento.",
                        "Fin de Pila - TDA", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                lblInfoPuntero.setText("Puntero: " + objPila.obtenerInfoPuntero());
                break;
            case 1: // Cola
                if (objCola.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La cola está vacía. No hay elementos para navegar.",
                        "Cola Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!objCola.moverPunteroSiguiente()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Ya está en el último elemento de la cola.\nPrincipio TDA: No hay siguiente elemento.",
                        "Fin de Cola - TDA", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                lblInfoPuntero.setText("Puntero: " + objCola.obtenerInfoPuntero());
                break;
            case 2: // Lista Simple
                if (objListaSimple.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista simple está vacía. No hay elementos para navegar.",
                        "Lista Simple Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String estadoAntes = obtenerEstadoListaSimple();
                if (!objListaSimple.moverPunteroSiguiente()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Ya está en el último elemento de la lista.\nPrincipio TDA: No hay siguiente elemento.",
                        "Fin de Lista Simple - TDA", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String estadoDespues = obtenerEstadoListaSimple();
                    registrarOperacion("LISTA_SIMPLE", "MOVER_PUNTERO_SIGUIENTE", "", estadoAntes, estadoDespues);
                }
                lblInfoPuntero.setText("Puntero: " + objListaSimple.obtenerInfoPuntero());
                break;
            case 3: // Lista Doble
                if (objListaDoble.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista doble está vacía. No hay elementos para navegar.",
                        "Lista Doble Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String estadoAntes4 = obtenerEstadoListaDoble();
                if (!objListaDoble.moverPunteroSiguiente()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Ya está en el último elemento de la lista.\nPrincipio TDA: No hay siguiente elemento.",
                        "Fin de Lista Doble - TDA", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String estadoDespues4 = obtenerEstadoListaDoble();
                    registrarOperacion("LISTA_DOBLE", "MOVER_PUNTERO_SIGUIENTE", "", estadoAntes4, estadoDespues4);
                }
                lblInfoPuntero.setText("Puntero: " + objListaDoble.obtenerInfoPuntero());
                break;
        }
        drawSelected();
    }
    
    private void btnPunteroAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
        int idx = tabbedPane.getSelectedIndex();
        switch(idx) {
            case 0: // Pila - TDA: En pilas no hay concepto de "anterior" lógico
                if (objPila.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La pila está vacía. No hay elementos para navegar.",
                        "Pila Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objPila.moverPunteroAnterior();
                lblInfoPuntero.setText("Puntero: " + objPila.obtenerInfoPuntero());
                break;
            case 1: // Cola - TDA: En colas no hay concepto de "anterior" lógico
                if (objCola.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La cola está vacía. No hay elementos para navegar.",
                        "Cola Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objCola.moverPunteroAnterior();
                lblInfoPuntero.setText("Puntero: " + objCola.obtenerInfoPuntero());
                break;
            case 2: // Lista Simple - TDA: No hay navegación bidireccional
                if (objListaSimple.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista simple está vacía. No hay elementos para navegar.",
                        "Lista Simple Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Lista Simple no soporta navegación hacia atrás.\nPrincipio TDA: Solo enlaces hacia adelante.",
                    "Operación No Soportada - TDA", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                break;
            case 3: // Lista Doble - TDA: Sí soporta navegación bidireccional
                if (objListaDoble.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista doble está vacía. No hay elementos para navegar.",
                        "Lista Doble Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!objListaDoble.moverPunteroAnterior()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Ya está en el primer elemento de la lista.\nPrincipio TDA: No hay elemento anterior.",
                        "Inicio de Lista Doble - TDA", 
                        javax.swing.JOptionPane.INFORMATION_MESSAGE);
                }
                lblInfoPuntero.setText("Puntero: " + objListaDoble.obtenerInfoPuntero());
                break;
        }
        drawSelected();
    }
    
    private void btnPunteroFinalActionPerformed(java.awt.event.ActionEvent evt) {
        int idx = tabbedPane.getSelectedIndex();
        switch(idx) {
            case 0: // Pila
                if (objPila.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La pila está vacía. No hay elementos para navegar.",
                        "Pila Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objPila.moverPunteroFinal();
                lblInfoPuntero.setText("Puntero: " + objPila.obtenerInfoPuntero());
                break;
            case 1: // Cola
                if (objCola.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La cola está vacía. No hay elementos para navegar.",
                        "Cola Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objCola.moverPunteroFinal();
                lblInfoPuntero.setText("Puntero: " + objCola.obtenerInfoPuntero());
                break;
            case 2: // Lista Simple
                if (objListaSimple.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista simple está vacía. No hay elementos para navegar.",
                        "Lista Simple Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objListaSimple.moverPunteroFinal();
                lblInfoPuntero.setText("Puntero: " + objListaSimple.obtenerInfoPuntero());
                break;
            case 3: // Lista Doble
                if (objListaDoble.estaVacia()) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "La lista doble está vacía. No hay elementos para navegar.",
                        "Lista Doble Vacía - TDA", 
                        javax.swing.JOptionPane.WARNING_MESSAGE);
                    return;
                }
                objListaDoble.moverPunteroFinal();
                lblInfoPuntero.setText("Puntero: " + objListaDoble.obtenerInfoPuntero());
                break;
        }
        drawSelected();
    }
    
    private void btnTogglePunteroActionPerformed(java.awt.event.ActionEvent evt) {
        // Cambiar el estado del toggle
        mostrarPunteroVisual = !mostrarPunteroVisual;
        
        // Cambiar la apariencia del botón según el estado
        if (mostrarPunteroVisual) {
            btnTogglePuntero.setBackground(java.awt.Color.BLUE);
            btnTogglePuntero.setForeground(java.awt.Color.WHITE);
            btnTogglePuntero.setText("⬛");
            btnTogglePuntero.setToolTipText("Ocultar indicador visual del puntero");
        } else {
            btnTogglePuntero.setBackground(java.awt.Color.LIGHT_GRAY);
            btnTogglePuntero.setForeground(java.awt.Color.BLACK);
            btnTogglePuntero.setText("⬜");
            btnTogglePuntero.setToolTipText("Mostrar indicador visual del puntero");
        }
        
        // Redibujar para aplicar el cambio
        drawSelected();
    }
    
    private void registrarOperacion(String estructura, String operacion, String valor, String estadoAntes, String estadoDespues) {
        OperacionAnalisis op = new OperacionAnalisis(estructura, operacion, valor, estadoAntes, estadoDespues);
        historialOperaciones.add(op);
    }
    
    private void btnAnalisisActionPerformed(java.awt.event.ActionEvent evt) {
        abrirVentanaAnalisis();
    }
    
    private String obtenerNombreEstructura(int indice) {
        switch(indice) {
            case 0: return "PILA";
            case 1: return "COLA";
            case 2: return "LISTA_SIMPLE";
            case 3: return "LISTA_DOBLE";
            default: return "DESCONOCIDA";
        }
    }
    
    private void abrirVentanaAnalisis() {
        // Obtener la estructura actualmente seleccionada
        int idx = tabbedPane.getSelectedIndex();
        String estructuraActual = obtenerNombreEstructura(idx);
        
        // Filtrar operaciones solo para la estructura actual
        java.util.ArrayList<OperacionAnalisis> operacionesFiltradas = new java.util.ArrayList<>();
        for (OperacionAnalisis op : historialOperaciones) {
            if (op.estructura.equals(estructuraActual)) {
                operacionesFiltradas.add(op);
            }
        }
        
        // Crear ventana modal para el análisis
        javax.swing.JDialog dialogAnalisis = new javax.swing.JDialog(this, 
            "Análisis Paso a Paso - " + estructuraActual, true);
        dialogAnalisis.setSize(900, 700);
        dialogAnalisis.setLocationRelativeTo(this);
        
        // Panel principal con BorderLayout
        javax.swing.JPanel panelPrincipal = new javax.swing.JPanel(new java.awt.BorderLayout());
        
        // Panel superior con controles
        javax.swing.JPanel panelControles = new javax.swing.JPanel();
        javax.swing.JLabel lblPaso = new javax.swing.JLabel("Paso: 0 / " + operacionesFiltradas.size());
        javax.swing.JButton btnAnterior = new javax.swing.JButton("← Anterior");
        javax.swing.JButton btnSiguiente = new javax.swing.JButton("Siguiente →");
        javax.swing.JButton btnReiniciar = new javax.swing.JButton("🔄 Reiniciar");
        
        panelControles.add(btnReiniciar);
        panelControles.add(btnAnterior);
        panelControles.add(lblPaso);
        panelControles.add(btnSiguiente);
        
        // Área de texto para mostrar el estado actual
        javax.swing.JTextArea areaEstado = new javax.swing.JTextArea();
        areaEstado.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        areaEstado.setEditable(false);
        javax.swing.JScrollPane scrollEstado = new javax.swing.JScrollPane(areaEstado);
        
        // Variable para controlar el paso actual
        final int[] pasoActual = {0};
        
        // Panel para visualización gráfica
        javax.swing.JPanel panelVisualizacion = new javax.swing.JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                // Aquí se dibujará la visualización paso a paso
                dibujarVisualizacionAnalisis(g, this.getWidth(), this.getHeight(), pasoActual[0], operacionesFiltradas);
            }
        };
        panelVisualizacion.setPreferredSize(new java.awt.Dimension(400, 300));
        panelVisualizacion.setBackground(java.awt.Color.WHITE);
        panelVisualizacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Visualización Gráfica"));
        
        // Actualizar información del paso
        Runnable actualizarPaso = () -> {
            lblPaso.setText("Paso: " + pasoActual[0] + " / " + operacionesFiltradas.size());
            
            if (operacionesFiltradas.isEmpty()) {
                areaEstado.setText("No hay operaciones registradas para " + estructuraActual + " aún.\n\nRealiza algunas operaciones en esta estructura y luego abre el análisis.");
                btnAnterior.setEnabled(false);
                btnSiguiente.setEnabled(false);
            } else {
                btnAnterior.setEnabled(pasoActual[0] > 0);
                btnSiguiente.setEnabled(pasoActual[0] < operacionesFiltradas.size());
                
                if (pasoActual[0] == 0) {
                    areaEstado.setText("ESTADO INICIAL:\nLa estructura " + estructuraActual + " está vacía.\n\nHaz clic en 'Siguiente' para ver la primera operación.");
                } else if (pasoActual[0] <= operacionesFiltradas.size()) {
                    OperacionAnalisis op = operacionesFiltradas.get(pasoActual[0] - 1);
                    StringBuilder info = new StringBuilder();
                    info.append("OPERACIÓN ").append(pasoActual[0]).append(":\n");
                    info.append("Estructura: ").append(op.estructura).append("\n");
                    info.append("Operación: ").append(op.operacion).append("\n");
                    if (!op.valor.isEmpty()) {
                        info.append("Valor: ").append(op.valor).append("\n");
                    }
                    info.append("\nESTADO ANTES:\n").append(op.estadoAntes).append("\n");
                    info.append("\nESTADO DESPUÉS:\n").append(op.estadoDespues).append("\n");
                    info.append("\n").append(obtenerExplicacionOperacion(op));
                    areaEstado.setText(info.toString());
                }
            }
            panelVisualizacion.repaint();
        };
        
        // Eventos de botones
        btnAnterior.addActionListener(e -> {
            if (pasoActual[0] > 0) {
                pasoActual[0]--;
                actualizarPaso.run();
            }
        });
        
        btnSiguiente.addActionListener(e -> {
            if (pasoActual[0] < operacionesFiltradas.size()) {
                pasoActual[0]++;
                actualizarPaso.run();
            }
        });
        
        btnReiniciar.addActionListener(e -> {
            pasoActual[0] = 0;
            actualizarPaso.run();
        });
        
        // Ensamblar la ventana
        panelPrincipal.add(panelControles, java.awt.BorderLayout.NORTH);
        panelPrincipal.add(scrollEstado, java.awt.BorderLayout.CENTER);
        panelPrincipal.add(panelVisualizacion, java.awt.BorderLayout.EAST);
        
        dialogAnalisis.add(panelPrincipal);
        
        // Mostrar estado inicial
        actualizarPaso.run();
        
        dialogAnalisis.setVisible(true);
    }
    
    /**
     * Dibuja la visualización paso a paso del análisis
     */
    private void dibujarVisualizacionAnalisis(java.awt.Graphics g, int ancho, int alto, int pasoActual, java.util.ArrayList<OperacionAnalisis> operaciones) {
        java.awt.Graphics2D g2d = (java.awt.Graphics2D) g;
        g2d.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Configuración de colores y fuentes
        java.awt.Font fuenteNodo = new java.awt.Font("Arial", java.awt.Font.BOLD, 12);
        g2d.setFont(fuenteNodo);
        
        int inicioX = 30;
        int inicioY = 80;
        int tamanoNodo = 40;
        int espacioEntre = 60;
        
        if (pasoActual == 0) {
            // Estado inicial: Lista vacía
            dibujarEstadoInicial(g2d, inicioX, inicioY, ancho, alto);
        } else if (pasoActual <= operaciones.size()) {
            // Dibujar estado después de cada operación
            OperacionAnalisis op = operaciones.get(pasoActual - 1);
            dibujarEstadoOperacion(g2d, op, inicioX, inicioY, tamanoNodo, espacioEntre, pasoActual, ancho, alto);
        }
    }
    
    /**
     * Dibuja el estado inicial (lista vacía)
     */
    private void dibujarEstadoInicial(java.awt.Graphics2D g2d, int x, int y, int ancho, int alto) {
        // Título
        g2d.setColor(java.awt.Color.BLACK);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        g2d.drawString("ESTADO INICIAL", x, y - 40);
        
        // Indicador de lista vacía
        g2d.setColor(java.awt.Color.GRAY);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
        g2d.drawString("Lista Vacía", x, y);
        
        // Cabeza apuntando a null
        g2d.setColor(java.awt.Color.BLUE);
        g2d.drawString("cabeza", x, y + 20);
        g2d.drawLine(x + 45, y + 15, x + 80, y + 15);
        g2d.drawString("null", x + 85, y + 20);
        
        // Puntero apuntando a null
        g2d.setColor(java.awt.Color.RED);
        g2d.drawString("puntero", x, y + 40);
        g2d.drawLine(x + 50, y + 35, x + 80, y + 35);
        g2d.drawString("null", x + 85, y + 40);
    }
    
    /**
     * Dibuja el estado después de una operación específica
     */
    private void dibujarEstadoOperacion(java.awt.Graphics2D g2d, OperacionAnalisis op, int inicioX, int inicioY, int tamanoNodo, int espacioEntre, int paso, int ancho, int alto) {
        // Título con información de la operación
        g2d.setColor(java.awt.Color.BLACK);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 14));
        g2d.drawString("PASO " + paso + ": " + op.operacion, inicioX, inicioY - 40);
        
        if (!op.valor.isEmpty()) {
            g2d.setColor(java.awt.Color.DARK_GRAY);
            g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 11));
            g2d.drawString("Valor: " + op.valor, inicioX, inicioY - 25);
        }
        
        // Parsear el estado después para obtener los nodos
        String[] nodos = parsearEstadoLista(op.estadoDespues);
        
        if (nodos.length == 0) {
            // Lista quedó vacía después de la operación
            dibujarEstadoInicial(g2d, inicioX, inicioY + 20, ancho, alto);
            return;
        }
        
        // Obtener posición del puntero para resaltarlo
        int posicionPuntero = obtenerPosicionPuntero(op.estadoDespues);
        
        // Determinar si es lista doble
        boolean esListaDoble = op.estructura.equals("LISTA_DOBLE");
        
        // Ajustar espaciado mejorado
        int espacioMejorado = Math.max(80, espacioEntre + 20);
        
        // Dibujar cabeza
        g2d.setColor(java.awt.Color.BLUE);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
        g2d.drawString("cabeza", inicioX, inicioY - 5);
        
        // Dibujar cola para lista doble
        if (esListaDoble && nodos.length > 0) {
            int colaX = inicioX + ((nodos.length - 1) * espacioMejorado);
            g2d.setColor(java.awt.Color.GREEN);
            g2d.drawString("cola", colaX, inicioY + tamanoNodo + 40);
            dibujarFlechaMejorada(g2d, colaX + 20, inicioY + tamanoNodo + 35, colaX + tamanoNodo/2, inicioY + tamanoNodo + 5, "cola");
        }
        
        // Dibujar nodos y enlaces
        for (int i = 0; i < nodos.length; i++) {
            int nodoX = inicioX + (i * espacioMejorado);
            int nodoY = inicioY;
            
            // Determinar si este nodo es donde está el puntero
            boolean esPuntero = (i == posicionPuntero);
            
            // Dibujar enlace desde cabeza al primer nodo
            if (i == 0) {
                g2d.setColor(java.awt.Color.BLUE);
                dibujarFlechaMejorada(g2d, inicioX + 35, inicioY - 10, nodoX + tamanoNodo/2, nodoY, "cabeza");
            }
            
            // Dibujar nodo con resaltado si es donde está el puntero
            dibujarNodoMejorado(g2d, nodoX, nodoY, tamanoNodo, nodos[i], esPuntero, esListaDoble);
            
            // Dibujar enlaces entre nodos
            if (i < nodos.length - 1) {
                int siguienteX = nodoX + espacioMejorado;
                
                if (esListaDoble) {
                    // Lista doble: enlaces bidireccionales
                    // Flecha hacia adelante (arriba)
                    g2d.setColor(java.awt.Color.BLACK);
                    dibujarFlechaMejorada(g2d, nodoX + tamanoNodo, nodoY + tamanoNodo/3, 
                                        siguienteX, nodoY + tamanoNodo/3, "next");
                    
                    // Flecha hacia atrás (abajo)
                    g2d.setColor(java.awt.Color.GRAY);
                    dibujarFlechaMejorada(g2d, siguienteX, nodoY + (2*tamanoNodo)/3, 
                                        nodoX + tamanoNodo, nodoY + (2*tamanoNodo)/3, "prev");
                } else {
                    // Lista simple: solo enlace hacia adelante
                    g2d.setColor(java.awt.Color.BLACK);
                    dibujarFlechaMejorada(g2d, nodoX + tamanoNodo, nodoY + tamanoNodo/2, 
                                        siguienteX, nodoY + tamanoNodo/2, "next");
                }
            } else {
                // Último nodo apunta a null
                g2d.setColor(java.awt.Color.GRAY);
                g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 9));
                int nullX = nodoX + tamanoNodo + 15;
                g2d.drawLine(nodoX + tamanoNodo, nodoY + tamanoNodo/2, nullX, nodoY + tamanoNodo/2);
                g2d.drawString("null", nullX + 5, nodoY + tamanoNodo/2 + 4);
                
                if (esListaDoble) {
                    // En lista doble, también mostrar prev del último nodo
                    g2d.drawString("prev", nodoX + tamanoNodo/4, nodoY + (3*tamanoNodo)/4);
                }
            }
        }
        
        // Dibujar indicador del puntero
        if (nodos.length > 0 && posicionPuntero >= 0 && posicionPuntero < nodos.length) {
            int punteroX = inicioX + (posicionPuntero * espacioMejorado);
            g2d.setColor(java.awt.Color.RED);
            g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
            g2d.drawString("puntero", punteroX, inicioY - 20);
            dibujarFlechaMejorada(g2d, punteroX + 35, inicioY - 15, punteroX + tamanoNodo/2, inicioY, "ptr");
        } else if (nodos.length > 0 && posicionPuntero == -1) {
            // Puntero es null
            g2d.setColor(java.awt.Color.RED);
            g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 11));
            g2d.drawString("puntero -> null", inicioX, inicioY - 20);
        }
        
        // Explicación de la operación con mejor formato
        g2d.setColor(java.awt.Color.DARK_GRAY);
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 10));
        String explicacion = obtenerExplicacionGraficaOperacion(op);
        String[] lineas = explicacion.split("\n");
        int explicacionY = inicioY + tamanoNodo + (esListaDoble ? 70 : 50);
        for (int i = 0; i < lineas.length && i < 4; i++) {
            g2d.drawString(lineas[i], inicioX, explicacionY + (i * 14));
        }
    }
    
    /**
     * Dibuja un nodo mejorado con mejor visualización
     */
    private void dibujarNodoMejorado(java.awt.Graphics2D g2d, int x, int y, int tamano, String valor, boolean esPuntero, boolean esListaDoble) {
        // Color de fondo según si es el puntero
        if (esPuntero) {
            // Nodo resaltado donde está el puntero
            g2d.setColor(new java.awt.Color(255, 255, 0, 200)); // Amarillo translúcido
            g2d.fillRect(x - 2, y - 2, tamano + 4, tamano + 4);
            g2d.setColor(java.awt.Color.RED);
            g2d.setStroke(new java.awt.BasicStroke(3));
            g2d.drawRect(x - 2, y - 2, tamano + 4, tamano + 4);
            g2d.setStroke(new java.awt.BasicStroke(1)); // Reset stroke
        }
        
        // Nodo principal
        g2d.setColor(esPuntero ? new java.awt.Color(255, 255, 200) : java.awt.Color.WHITE);
        g2d.fillRect(x, y, tamano, tamano);
        g2d.setColor(esPuntero ? java.awt.Color.RED : java.awt.Color.BLACK);
        g2d.setStroke(new java.awt.BasicStroke(esPuntero ? 2 : 1));
        g2d.drawRect(x, y, tamano, tamano);
        g2d.setStroke(new java.awt.BasicStroke(1)); // Reset stroke
        
        // Valor del nodo
        g2d.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, esPuntero ? 13 : 12));
        g2d.setColor(esPuntero ? java.awt.Color.RED : java.awt.Color.BLACK);
        java.awt.FontMetrics fm = g2d.getFontMetrics();
        int textX = x + (tamano - fm.stringWidth(valor)) / 2;
        int textY = y + (tamano + fm.getAscent()) / 2;
        g2d.drawString(valor, textX, textY);
        
        // Para lista doble, dividir el nodo para mostrar prev y next
        if (esListaDoble) {
            g2d.setColor(java.awt.Color.LIGHT_GRAY);
            // Línea horizontal para separar prev/data/next
            g2d.drawLine(x, y + tamano/3, x + tamano, y + tamano/3);
            g2d.drawLine(x, y + 2*tamano/3, x + tamano, y + 2*tamano/3);
            
            // Etiquetas pequeñas
            g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 8));
            g2d.setColor(java.awt.Color.GRAY);
            g2d.drawString("prev", x + 2, y + tamano/6 + 3);
            g2d.drawString("next", x + 2, y + 5*tamano/6 + 3);
        }
        
        // Sombra para mejor efecto visual
        if (esPuntero) {
            g2d.setColor(new java.awt.Color(255, 0, 0, 50));
            g2d.fillRect(x + 3, y + 3, tamano, tamano);
        }
    }
    
    /**
     * Dibuja una flecha mejorada con etiqueta
     */
    private void dibujarFlechaMejorada(java.awt.Graphics2D g2d, int x1, int y1, int x2, int y2, String etiqueta) {
        // Línea principal
        g2d.drawLine(x1, y1, x2, y2);
        
        // Calcular la punta de la flecha
        double angulo = Math.atan2(y2 - y1, x2 - x1);
        int longitud = 10;
        double anguloFlecha = Math.PI / 6;
        
        int x3 = (int) (x2 - longitud * Math.cos(angulo - anguloFlecha));
        int y3 = (int) (y2 - longitud * Math.sin(angulo - anguloFlecha));
        int x4 = (int) (x2 - longitud * Math.cos(angulo + anguloFlecha));
        int y4 = (int) (y2 - longitud * Math.sin(angulo + anguloFlecha));
        
        // Dibujar punta de flecha
        g2d.drawLine(x2, y2, x3, y3);
        g2d.drawLine(x2, y2, x4, y4);
        
        // Etiqueta en el medio de la flecha (opcional)
        if (etiqueta != null && !etiqueta.isEmpty() && etiqueta.length() <= 4) {
            g2d.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 8));
            int midX = (x1 + x2) / 2;
            int midY = (y1 + y2) / 2 - 5;
            g2d.drawString(etiqueta, midX - 8, midY);
        }
    }
    
    /**
     * Dibuja una flecha entre dos puntos
     */

    
    /**
     * Obtiene la posición del puntero desde el estado de la estructura
     */
    private int obtenerPosicionPuntero(String estado) {
        if (estado.contains("Puntero: null")) {
            return -1;
        }
        
        // Buscar patrón "Puntero: pos X"
        if (estado.contains("Puntero: pos ")) {
            try {
                int inicio = estado.indexOf("Puntero: pos ") + "Puntero: pos ".length();
                int fin = estado.indexOf(" ", inicio);
                if (fin == -1) fin = estado.length();
                String posStr = estado.substring(inicio, fin);
                return Integer.parseInt(posStr);
            } catch (Exception e) {
                return 0; // Por defecto posición 0
            }
        }
        
        return 0; // Por defecto posición 0
    }
    
    /**
     * Parsea el estado de la lista para obtener los valores de los nodos
     */
    private String[] parsearEstadoLista(String estado) {
        if (estado == null || estado.trim().isEmpty() || estado.contains("Lista vacía") || estado.contains("Estructura vacía")) {
            return new String[0];
        }
        
        // Buscar patrones como "[10] -> [20] -> [30] -> null" o "10, 20, 30"
        if (estado.contains("->")) {
            String[] partes = estado.split("->");
            java.util.ArrayList<String> valores = new java.util.ArrayList<>();
            for (String parte : partes) {
                parte = parte.trim();
                if (parte.startsWith("[") && parte.endsWith("]")) {
                    valores.add(parte.substring(1, parte.length() - 1));
                } else if (!parte.equals("null") && !parte.isEmpty()) {
                    valores.add(parte);
                }
            }
            return valores.toArray(new String[0]);
        } else if (estado.contains(",")) {
            String[] partes = estado.split(",");
            java.util.ArrayList<String> valores = new java.util.ArrayList<>();
            for (String parte : partes) {
                parte = parte.trim();
                if (!parte.isEmpty() && !parte.equals("null")) {
                    valores.add(parte);
                }
            }
            return valores.toArray(new String[0]);
        } else {
            // Un solo valor
            String valor = estado.trim();
            if (!valor.isEmpty() && !valor.equals("null") && !valor.contains("vacía")) {
                return new String[]{valor};
            }
        }
        
        return new String[0];
    }
    
    /**
     * Obtiene explicación gráfica específica para cada operación
     */
    private String obtenerExplicacionGraficaOperacion(OperacionAnalisis op) {
        switch (op.operacion) {
            case "INSERTAR_INICIO":
                return "Se crea nuevo nodo con valor " + op.valor + "\nSe actualiza 'cabeza' para apuntar al nuevo nodo\nEl puntero se posiciona en el nuevo nodo";
            case "INSERTAR_FINAL":
                return "Se crea nuevo nodo con valor " + op.valor + "\nSe conecta al final de la lista\nSe actualizan las referencias";
            case "INSERTAR_DERECHA":
                return "Se crea nuevo nodo con valor " + op.valor + "\nSe inserta a la derecha del puntero\nSe actualizan los enlaces correctamente";
            case "ELIMINAR_INICIO":
                return "Se elimina el primer nodo (valor " + op.valor + ")\nLa 'cabeza' ahora apunta al siguiente nodo\nEl puntero se reposiciona automáticamente";
            case "ELIMINAR_FINAL":
                return "Se elimina el último nodo (valor " + op.valor + ")\nSe actualiza la referencia del penúltimo nodo\nSe verifica la posición del puntero";
            case "MOVER_PUNTERO_INICIO":
                return "El puntero se mueve al primer nodo\nPosición actualizada: Inicio de la lista\nNavegación TDA: Cabeza de la estructura";
            case "MOVER_PUNTERO_SIGUIENTE":
                return "El puntero avanza al siguiente nodo\nNavegación TDA: Recorrido secuencial\nSe sigue el enlace 'next'";
            case "MOVER_PUNTERO_ANTERIOR":
                return "El puntero retrocede al nodo anterior\nNavegación TDA: Solo en listas dobles\nSe sigue el enlace 'prev'";
            case "MOVER_PUNTERO_FINAL":
                return "El puntero se mueve al último nodo\nPosición actualizada: Final de la lista\nNavegación TDA: Cola de la estructura";
            default:
                return "Operación: " + op.operacion + "\nValor procesado: " + op.valor + "\nEstructura actualizada correctamente";
        }
    }

    private String obtenerExplicacionOperacion(OperacionAnalisis op) {
        StringBuilder explicacion = new StringBuilder();
        explicacion.append("EXPLICACIÓN TÉCNICA:\n");
        
        switch (op.operacion.toLowerCase()) {
            case "push":
            case "apilar":
                explicacion.append("1. Se crea un nuevo nodo en memoria\n");
                explicacion.append("2. Se asigna el valor al nodo\n");
                explicacion.append("3. El puntero 'siguiente' del nuevo nodo apunta al nodo que estaba en la cima\n");
                explicacion.append("4. El puntero 'cima' se actualiza para apuntar al nuevo nodo\n");
                explicacion.append("5. El tamaño se incrementa en 1");
                break;
            case "pop":
            case "desapilar":
                explicacion.append("1. Se verifica que la pila no esté vacía\n");
                explicacion.append("2. Se guarda la referencia al nodo de la cima\n");
                explicacion.append("3. El puntero 'cima' se actualiza para apuntar al siguiente nodo\n");
                explicacion.append("4. Se extrae el valor del nodo original\n");
                explicacion.append("5. Se libera la memoria del nodo eliminado\n");
                explicacion.append("6. El tamaño se decrementa en 1");
                break;
            case "enqueue":
            case "encolar":
                explicacion.append("1. Se crea un nuevo nodo en memoria\n");
                explicacion.append("2. Se asigna el valor al nodo\n");
                explicacion.append("3. Si la cola está vacía, tanto 'frente' como 'final' apuntan al nuevo nodo\n");
                explicacion.append("4. Si no está vacía, el 'siguiente' del nodo final apunta al nuevo nodo\n");
                explicacion.append("5. El puntero 'final' se actualiza para apuntar al nuevo nodo\n");
                explicacion.append("6. El tamaño se incrementa en 1");
                break;
            case "dequeue":
            case "desencolar":
                explicacion.append("1. Se verifica que la cola no esté vacía\n");
                explicacion.append("2. Se guarda la referencia al nodo del frente\n");
                explicacion.append("3. El puntero 'frente' se actualiza para apuntar al siguiente nodo\n");
                explicacion.append("4. Si era el último nodo, 'final' se actualiza a null\n");
                explicacion.append("5. Se extrae el valor del nodo original\n");
                explicacion.append("6. Se libera la memoria del nodo eliminado\n");
                explicacion.append("7. El tamaño se decrementa en 1");
                break;
            case "insertar inicio":
                explicacion.append("1. Se crea un nuevo nodo en memoria\n");
                explicacion.append("2. Se asigna el valor al nodo\n");
                explicacion.append("3. El puntero 'siguiente' del nuevo nodo apunta al primer nodo actual\n");
                explicacion.append("4. El puntero 'primero' se actualiza para apuntar al nuevo nodo\n");
                explicacion.append("5. El tamaño se incrementa en 1");
                break;
            case "insertar final":
                explicacion.append("1. Se crea un nuevo nodo en memoria\n");
                explicacion.append("2. Se asigna el valor al nodo\n");
                explicacion.append("3. Se recorre la lista hasta encontrar el último nodo\n");
                explicacion.append("4. El puntero 'siguiente' del último nodo apunta al nuevo nodo\n");
                explicacion.append("5. El tamaño se incrementa en 1");
                break;
            case "eliminar":
                explicacion.append("1. Se busca el nodo que contiene el valor\n");
                explicacion.append("2. Se actualiza el puntero 'siguiente' del nodo anterior\n");
                explicacion.append("3. Se desconecta el nodo a eliminar\n");
                explicacion.append("4. Se libera la memoria del nodo eliminado\n");
                explicacion.append("5. El tamaño se decrementa en 1");
                break;
            default:
                explicacion.append("Operación personalizada: ").append(op.operacion);
        }
        
        return explicacion.toString();
    }
    

    
    // Métodos auxiliares para obtener estado de las estructuras
    private String obtenerEstadoPila() {
        if (objPila.estaVacia()) {
            return "VACÍA";
        }
        StringBuilder sb = new StringBuilder();
        clsNodo temp = objPila.getCima();
        sb.append("PILA [");
        while (temp != null) {
            sb.append(temp.getDato());
            temp = temp.getRef();
            if (temp != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    private String obtenerEstadoCola() {
        if (objCola.estaVacia()) {
            return "VACÍA";
        }
        StringBuilder sb = new StringBuilder();
        clsNodo temp = objCola.getPrimero();
        sb.append("COLA [");
        while (temp != null) {
            sb.append(temp.getDato());
            temp = temp.getRef();
            if (temp != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    private String obtenerEstadoListaSimple() {
        if (objListaSimple.estaVacia()) {
            return "VACÍA - Puntero: null";
        }
        StringBuilder sb = new StringBuilder();
        clsNodo temp = objListaSimple.getCabeza();
        clsNodo puntero = objListaSimple.getPunteroActual();
        sb.append("LISTA_SIMPLE [");
        int posicion = 0;
        int posicionPuntero = -1;
        while (temp != null) {
            if (temp == puntero) {
                posicionPuntero = posicion;
                sb.append("*").append(temp.getDato()).append("*"); // Marcar nodo donde está el puntero
            } else {
                sb.append(temp.getDato());
            }
            temp = temp.getRef();
            if (temp != null) sb.append(", ");
            posicion++;
        }
        sb.append("] - Puntero: ");
        if (puntero != null) {
            sb.append("pos ").append(posicionPuntero).append(" (valor ").append(puntero.getDato()).append(")");
        } else {
            sb.append("null");
        }
        return sb.toString();
    }
    
    private String obtenerEstadoListaDoble() {
        if (objListaDoble.estaVacia()) {
            return "VACÍA - Puntero: null";
        }
        StringBuilder sb = new StringBuilder();
        clsNodoDoble temp = objListaDoble.getCabeza();
        clsNodoDoble puntero = objListaDoble.getPunteroActual();
        sb.append("LISTA_DOBLE [");
        int posicion = 0;
        int posicionPuntero = -1;
        while (temp != null) {
            if (temp == puntero) {
                posicionPuntero = posicion;
                sb.append("*").append(temp.getDato()).append("*"); // Marcar nodo donde está el puntero
            } else {
                sb.append(temp.getDato());
            }
            temp = temp.getNext();
            if (temp != null) sb.append(", ");
            posicion++;
        }
        sb.append("] - Puntero: ");
        if (puntero != null) {
            sb.append("pos ").append(posicionPuntero).append(" (valor ").append(puntero.getDato()).append(")");
        } else {
            sb.append("null");
        }
        return sb.toString();
    }

}
