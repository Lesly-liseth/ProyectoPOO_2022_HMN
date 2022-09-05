import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Administrador extends JFrame {
    private JButton bodegueroButton;
    private JButton cajeroButton;
    private JButton usuariosButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Administrador");
        Administrador administrador = new Administrador();
    }

    public Administrador() {
        setTitle("SISTEMA ADMINISTRADOR");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(720, 480));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);

        bodegueroButton.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                Bodeguero bodeguero = new Bodeguero();
                bodeguero.setVisible(true);
                dispose();
            }

        });

        cajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cajero1 cajero = new cajero1();
                cajero.setVisible(true);
                dispose();
            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admi Admi = new admi();
                Admi.setVisible(true);
                dispose();
            }
        });
    }
}





