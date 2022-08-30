import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Administrador extends JFrame{
    private JButton bodegueroButton;
    private JButton cajeroButton;
    private JButton usuariosButton;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Administrador");
    }

    public Administrador() {
        setTitle("Bienvenido al sistema de Administrador");
        setContentPane(mainPanel);
        setMinimumSize(new Dimension(1280, 720));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        conectar1();
        bodegueroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {bodeguero(); }
        });

        cajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    Connection con;
    PreparedStatement pst;
    public void conectar1(){

        final String DB_URL="jdbc:mysql://%@/farmacia?serverTimezone=UTC";
        final String USERNAME="cualquiera";
        final String PASSWORD="";

        try{

            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            System.out.println("La conexion ha sido exitosa");


        }
        catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }

    }

    public void bodeguero() {
        String producto,id,stock, precio;
        producto = productoTF.getText();
        id = idTF.getText();
        stock = stockTF.getText();
        precio = precioTF.getText();


        final String DB_URL="jdbc:mysql://%@/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "insert into registro_prod(producto,id,stock,precio) values (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, producto);
            pst.setString(2, id);
            pst.setString(3, stock);
            pst.setString(4, precio);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro Exitoso");

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo registrar");

        }
    }
}
