import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bodeguero extends JFrame {
    private JButton ingresarButton;
    private JButton verButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTextField textNombre;
    private JTextField textDescripcion;
    private JTextField textPrecio;
    private JTextField textStock;
    private JPanel mainPanel;
    private JButton limpiarButton;
    private JButton buscarButton;

    DefaultTableModel model = new DefaultTableModel();
    String producto, id, stock, precio;

    public Bodeguero() {

        setTitle("MODIFICACION DE PRODUCTOS");
        setSize(720, 500);
        setContentPane(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
