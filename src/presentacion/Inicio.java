package presentacion;

import java.awt.HeadlessException;
import java.io.File;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import logica.entidades.*;

import logica.util.*;

/**
 *
 * @author Tyncho
 * @version 1.0
 */
public class Inicio extends javax.swing.JFrame {

    private File[] archivosALeer;
    private Palabra palabraSeleccionada;
    public static boolean NUEVOS_ARCHIVOS = false;
    public static boolean PROCESAMIENTO_INICIADO = false;
    public static boolean MOSTRAR_DETALLE = false;

    /**
     * Creates new form Principal
     */
    public Inicio() {
        initComponents();
        configurarSeleccionadorArchivos();
        archivosALeer = null;
        pbProcesandoArchivos.setVisible(false);
    }

    public File[] getArchivosALeer() {
        return archivosALeer;
    }

    public void setArchivosALeer(File[] file) {
        archivosALeer = file;
    }

    /**
     * Actualiza la tabla de archivos para ser procesados según la cola de
     * archivos que se pasa como parametro. Y setea la bandera NUEVOS_ARCHIVOS
     * en false, de esta forma indica que no hay más archivos para mostrar
     *
     * @param colaArchivos
     */
    public void mostrarArchivosAProcesar(SimpleList<Archivo> colaArchivos) {
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[]{"Nombre", "Path", ""});
        Iterator<Archivo> it = colaArchivos.iterator();
        Archivo archivo = null;
        while (it.hasNext()) {
            archivo = it.next();
            dtm.addRow(new Object[]{archivo.getNombre(), archivo.getRuta()});
        }
        tblArchivosAProcesar.setModel(dtm);
        Inicio.NUEVOS_ARCHIVOS = false;
    }

    /**
     * Muestra las palabras y su frecuencia total (cantidad de apariciones en
     * todos los archivos) enviadas por parametro en la tabla
     *
     * @param palabras
     */
    public void mostrarPalabras(SimpleList<Palabra> palabras) {
        if (palabras != null) {
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(new String[]{"Palabra", "Frecuencia"});
            Iterator<Palabra> it = palabras.iterator();
            Palabra palabra = null;
            while (it.hasNext()) {
                palabra = it.next();
                dtm.addRow(new Object[]{palabra.getDescripcion(), palabra.getCantidad()});
            }
            tblArchivosAProcesar.setModel(dtm);
            Inicio.PROCESAMIENTO_INICIADO = false;
        }
    }

    /**
     * configura la extensión de arvhivos que se visualizan en el selecionador
     * de archivos (file chooser).
     */
    private void configurarSeleccionadorArchivos() {
        FileFilter ff = new FileNameExtensionFilter("txt", "TXT");
        fcSeleccionadorArchivos.setFileFilter(ff);
        fcSeleccionadorArchivos.setMultiSelectionEnabled(true);
        ocutarSeleccionadorArchivos();
    }

    /**
     * Esconde el Selecionador de archivos (FileChooser).
     */
    private void ocutarSeleccionadorArchivos() {
        fcSeleccionadorArchivos.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionarArchivos = new java.awt.Button();
        btnProcesar = new java.awt.Button();
        lblArchivosAProcesar = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVocabulario = new javax.swing.JTable();
        lblBuscar = new java.awt.Label();
        txtBuscar = new java.awt.TextField();
        lblVocabulario = new java.awt.Label();
        fcSeleccionadorArchivos = new javax.swing.JFileChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblArchivosAProcesar = new javax.swing.JTable();
        pbProcesandoArchivos = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TSB Vocabulario");
        setName("jfPrincipal"); // NOI18N

        btnSeleccionarArchivos.setLabel("Seleccionar");
        btnSeleccionarArchivos.setName("btnSeleccionarArchivos"); // NOI18N
        btnSeleccionarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarArchivosActionPerformed(evt);
            }
        });

        btnProcesar.setLabel("Procesar");
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
            }
        });

        lblArchivosAProcesar.setName(""); // NOI18N
        lblArchivosAProcesar.setText("Archivos a procesar");

        tblVocabulario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Palabra", "Frecuencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVocabulario.setEditingRow(0);
        tblVocabulario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVocabularioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVocabulario);

        lblBuscar.setText("Buscar");

        txtBuscar.setText("textField1");

        lblVocabulario.setName(""); // NOI18N
        lblVocabulario.setText("Vocabulario");

        fcSeleccionadorArchivos.setAcceptAllFileFilterUsed(false);
        fcSeleccionadorArchivos.setCurrentDirectory(new java.io.File("E:\\TSB\\tsb_vocabulario\\res"));

        tblArchivosAProcesar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Path"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblArchivosAProcesar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblVocabulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(641, 641, 641))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblArchivosAProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(244, 244, 244))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pbProcesandoArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSeleccionarArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(fcSeleccionadorArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblArchivosAProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVocabulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnSeleccionarArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pbProcesandoArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fcSeleccionadorArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarArchivosActionPerformed

        fcSeleccionadorArchivos.setVisible(true);
        //[623, 397]        
        try {
            int option = fcSeleccionadorArchivos.showOpenDialog(this);
            switch (option) {
                case JFileChooser.APPROVE_OPTION:
                    ocutarSeleccionadorArchivos();
                    archivosALeer = fcSeleccionadorArchivos.getSelectedFiles();
                    Inicio.NUEVOS_ARCHIVOS = true;
                    //System.out.println(archivosALeer[0].getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    ocutarSeleccionadorArchivos();
                    break;
                case JFileChooser.ERROR_OPTION:
                    System.out.println(this.getState());
                    ocutarSeleccionadorArchivos();
                    break;
                default:
                    break;
            }
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSeleccionarArchivosActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
        Inicio.PROCESAMIENTO_INICIADO = true;
        pbProcesandoArchivos.setVisible(true);
    }//GEN-LAST:event_btnProcesarActionPerformed

    private void tblVocabularioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVocabularioMouseClicked
        if (evt.getClickCount() == 2) {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            int column = target.getSelectedColumn();            
            Inicio.MOSTRAR_DETALLE = true;
        }
    }//GEN-LAST:event_tblVocabularioMouseClicked

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnProcesar;
    private java.awt.Button btnSeleccionarArchivos;
    private javax.swing.JFileChooser fcSeleccionadorArchivos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.Label lblArchivosAProcesar;
    private java.awt.Label lblBuscar;
    private java.awt.Label lblVocabulario;
    private javax.swing.JProgressBar pbProcesandoArchivos;
    private javax.swing.JTable tblArchivosAProcesar;
    private javax.swing.JTable tblVocabulario;
    private java.awt.TextField txtBuscar;
    // End of variables declaration//GEN-END:variables

}
