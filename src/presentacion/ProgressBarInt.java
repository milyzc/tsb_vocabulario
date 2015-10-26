package presentacion;

import ConexionBD.BaseArchivo;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import logica.controladores.Lector;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import logica.entidades.Archivo;

public class ProgressBarInt extends JFrame {

    private final Lector lector;
    private JPanel jContentPane = null;
    private JProgressBar bar = null;
    private JLabel jLabel = null;
    private JLabel lblCompletado = null;
    private JLabel lblFinish = null;

    private MiWorker trabajador;

    /**
     * This method initializes btnStartWorker
     *
     * @return javax.swing.JButton
     */
    public  void procesoWorker() {
        
                        if (trabajador == null) {
                        trabajador = new MiWorker();
                        //Agrego un Listener para el cambio de la propiedad "progress"
                        trabajador.addPropertyChangeListener(new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent evt) {
                                if ("progress".equals(evt.getPropertyName())) {
                                    lblCompletado.setText(evt.getNewValue() + " %");
                                }
                            }
                        });
                    }
                    trabajador.execute();
                }
    

    /**
     * This method initializes bar
     *
     * @return javax.swing.JProgressBar
     */
    private JProgressBar getBar() {
        if (bar == null) {
            bar = new JProgressBar();
            bar.setBounds(new java.awt.Rectangle(10, 58, 276, 31));
        }
        return bar;
    }

    /**
     * This is the default constructor
     */
    public ProgressBarInt(Lector lector) {
        super();
        initialize();
        this.lector = lector;
        //llama al worker
        procesoWorker();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setSize(400, 200);
        this.setContentPane(getJContentPane());
        this.setTitle("Procesar Archivos");
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            lblFinish = new JLabel();
            lblFinish.setBounds(new java.awt.Rectangle(93, 134, 153, 28));
            lblFinish.setText("");
            lblCompletado = new JLabel();
            lblCompletado.setBounds(new java.awt.Rectangle(94, 108, 39, 20));
            lblCompletado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblCompletado.setText("0%");
            jLabel = new JLabel();
            jLabel.setBounds(new java.awt.Rectangle(17, 108, 74, 19));
            jLabel.setText("Completado:");
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getBar(), null);
            jContentPane.add(jLabel, null);
            jContentPane.add(lblCompletado, null);
            jContentPane.add(lblFinish, null);

        }
        return jContentPane;
    }

    /*
     * Esta es mi inner class "MiWorker", si se fijan, estoy
     * instrumentando Generics para decirle a esta clase, que deber�
     * retornar del "doInBackGround" un String, y que los dem�s m�todos
     * no deben retornar nada (void)
     */
    class MiWorker extends SwingWorker<String, Void> {

        @Override
        protected String doInBackground() throws Exception {
            int  escalon = 100 / lector.getColaArchivos().size(), progreso = 0;
            for (Archivo a : lector.getColaArchivos()) {
                if (!BaseArchivo.existeArchivo(a.getRuta())) {
                    lector.procesar_archivos(a);
                    progreso += escalon;
                    bar.setValue(progreso);
                    setProgress(progreso);

                } else {
                    JOptionPane.showMessageDialog(null, "Archivo: " + a.getNombre() + " Fue procesado anteriomente", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            progreso = 100;
            bar.setValue(progreso);
            setProgress(progreso);
            return "Operacion finalizada";
        }

        @Override
        public void done() {
            try {
                lblFinish.setText(get());
                dispose();
                lector.getColaArchivos().clear();
                (new Inicio(lector)).setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
