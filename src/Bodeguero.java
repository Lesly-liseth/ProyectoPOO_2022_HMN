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
        conectar();

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

    Connection con;
    PreparedStatement pst;
    public void conectar(){

        final String DB_URL="jdbc:mysql://localhost/producto?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try{

            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            System.out.println("conexion exitosa");


        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }

    }

    public static void main(String[] args) {
        Bodeguero bodegueros = new Bodeguero();
    }
}
