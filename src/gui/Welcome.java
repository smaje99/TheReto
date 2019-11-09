package gui;
import arduino.Arduino;
import tools.Constrains;
import tools.Events;
import tools.Paneles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Welcome extends JPanel {
    private JLabel press;
    private JLabel conexion;
    private Arduino arduino;
    public Welcome(){
        setLayout(new GridBagLayout());
        init();
        this.arduino = Arduino.getConexion(this);
        this.arduino.conectarArduino();
    }
    private void init(){
        JTextArea theReto = new JTextArea("The\nReto!");
        theReto.setFont(new Font(Font.MONOSPACED, Font.BOLD + Font.ITALIC, 120));
        theReto.setOpaque(false);
        theReto.setEditable(false);
        theReto.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (arduino.isConexion()) Events.show(Paneles.LEVES);
            }
        });
        press = new JLabel("Presione cualquier tecla para continuar", SwingConstants.CENTER);
        press.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
        JLabel descripcion = new JLabel("Lee un color y mecaniza otro", SwingConstants.RIGHT);
        descripcion.setFont(new Font(Font.DIALOG, Font.PLAIN + Font.ITALIC, 20));
        conexion = new JLabel("", SwingConstants.LEFT);
        conexion.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        Constrains.addComp(theReto, this, new Rectangle(0, 0, 1, 1), 0.2, 0,
                new Insets(20, 15, 0, 30), GridBagConstraints.NORTHWEST, GridBagConstraints.NONE);
        Constrains.addCompX(descripcion, this, new Rectangle(0, 1, 1, 1), 0.1,
                new Insets(0, 30, 10, 8), GridBagConstraints.WEST, GridBagConstraints.BOTH);
        Constrains.addCompX(conexion, this, new Rectangle(0, 2, 1, 1), 1,
                new Insets(95, 20, 1, 50), GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL);
        Constrains.addComp(press, this, new Rectangle(0, 3, 1, 1), 0, 0,
                new Insets(30, 20, 1, 20), GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
    }
    public JLabel getPress() {
        return press;
    }
    public JLabel getConexion() {
        return conexion;
    }
}