import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class cajero1 extends JFrame {
    private JPanel panel;
    private JTextField cedula;
    private JTextField direccion;
    private JTextField correo;
    private JTextField nombre;
    private JTextField telefono;
    private JTextField fecha;
    private JTable tabla;
    private JTextField producto;
    private JButton bucar;
    private JButton FINALIZARButton;
    private JButton eliminar;
    private JButton TOTALButton;
    private JTextField cantidad;
    private JButton agregarButton;

    DefaultTableModel model = new DefaultTableModel();

    public cajero1() {
        conectar();

        setTitle("FACTURACIÓN");
        setSize(720, 500);
        setContentPane(panel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        String[] titulo = new String[]{"ID", "PRODUCTO", "DESCRIPCION", "PRECIO", "CANTIDAD", "SUBTOTAL"};
        model.setColumnIdentifiers(titulo);
        tabla.setModel(model);


        bucar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
    }

    Connection con;
    PreparedStatement pst;
    public void conectar(){
        //BASE DE DATOS CON CUENTA DE USUARIO CREADA
        final String DB_URL="jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";

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

    //funcion agregar a la tabla
    public void agregar(){

        final String DB_URL="jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";

        String produ = producto.getText();
        String dato = ""; //en caso que el espacio este vacio


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = null;
            String sql = "SELECT nombre,descripcion,precio,stock FROM registro_prod  ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();

            ResultSetMetaData datos = rs.getMetaData();
            int colum = datos.getColumnCount();

            while (rs.next()) {
                Object[] filas = new Object[colum];
                for (int i = 0; i < colum; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                model.addRow(filas);

            }
        }
        catch (SQLException ex){

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se pudo registrar");

        }

    }




    public static void main(String[] args) {
        cajero1 cajeros = new cajero1();
    }
}
