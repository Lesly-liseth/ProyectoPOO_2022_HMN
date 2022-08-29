import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bodeguero extends JFrame{
    private JButton ingresarButton;
    private JButton verButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JTextField productoTF;
    private JTextField idTF;
    private JTextField stockTF;
    private JTextField precioTF;
    private JPanel mainPanel;

    DefaultTableModel model = new DefaultTableModel();
    String producto,id,stock, precio;

    public Bodeguero() {
        //conectar();
        setTitle("MODIFICACION DE PRODUCTOS");
        setSize(720, 500);
        setContentPane(mainPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {ingresar();}
        });
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {eliminar();}
        });
    }

    Connection con;
    PreparedStatement pst;
    public void ingresar() {

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
            String sql = "insert into registro_prod(nombre,descripcion,precio,stock) values (?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, producto);
            pst.setString(2, id);
            pst.setString(3, precio);
            pst.setString(4, stock);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro Exitoso");

            stmt.close();
            conn.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo registrar");
        }
    }

    public void eliminar(){

        final String DB_URL="jdbc:mysql://%@/farmacia?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";

        String borrarid = idTF.getText();
        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "delete from registro_prod where id=?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1,borrarid);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro Borrado");
            stmt.close();
            conn.close();
        }
        catch (SQLException ex){

            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }
}
