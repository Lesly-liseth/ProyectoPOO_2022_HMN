import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JButton agregar;
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


        String[] titulo = new String[]{"ID", "PRODUCTO", "DESCRIPCION", "CANTIDAD", "PRECIO", "SUBTOTAL"};
        model.setColumnIdentifiers(titulo);
        tabla.setModel(model);



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
        /*String id,nombre,descripcion, precio, stock;
        id = textid.getText();
        nombre = textnombre.getText();
        descripcion = textdescripcion.getText();
        precio = textprecio.getText();
        stock = textStock.getText();

        final String DB_URL="jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "insert into registro_prod(nombre,descripcion,precio,stock) values (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,descripcion);
            pst.setString(3,precio);
            pst.setString(4,stock);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null,"Registro Exitoso");

            stmt.close();
            conn.close();
        }
        catch (SQLException ex){

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"No se pudo registrar");

        }

    }*/


}

    public static void main(String[] args) {
        cajero1 cajeros = new cajero1();
    }
}
