import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Bodeguero extends JFrame {
    private JButton ingresarButton;
    private JButton table;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTextField textNombre;
    private JTextField textDescripcion;
    private JTextField textPrecio;
    private JTextField textStock;
    private JPanel mainPanel;
    private JButton limpiarButton;
    private JButton buscarButton;
    private JTextField textid;
    private JTable table1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bienvenido al area de Bodeguero");
        frame.setContentPane(new Bodeguero().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    DefaultTableModel model = new DefaultTableModel();

    public Bodeguero() {
        conectar();

        table.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });


        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ingresar();

            }
        });

        table.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ver();
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

    public void ingresar(){
        String nombre, descripcion,precio, id, stock;
        nombre=textNombre.getText();
        descripcion=textDescripcion.getText();
        precio=textPrecio.getText();
        stock=textStock.getText();
        id= textNombre.getText();
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(descripcion);
        System.out.println(precio);
        System.out.println(stock);

        final String DB_URL="jdbc:mysql://localhost/producto?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";


        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="insert into registro_prod(nombre, descripcion, precio, stock)values(?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,descripcion);
            pst.setString(3,precio);
            pst.setString(4,stock);
            //ResultSet resultSet=pst.executeQuery();
            pst.executeUpdate();

            stmt.close();
            conn.close();

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void limpiar(){
        textid.setText("");
        textNombre.setText("");
        textDescripcion.setText("");
        textPrecio.setText("");
        textStock.setText("");
    }

    public void buscar(){
        String id="0";
        id=textid.getText();

        final String DB_URL="jdbc:mysql://localhost/producto?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";


        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="select * from registro_prod where id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,id);
            //System.out.println(sql);


            ResultSet rs=pst.executeQuery();


            if(rs.next()==true){
                String nombre, descripcion, precio, stock;
                nombre=rs.getString(2);
                descripcion=rs.getString(3);
                precio=rs.getString(4);
                stock=rs.getString(6);

                System.out.println();
                textNombre.setText(nombre);
                textDescripcion.setText(descripcion);
                textPrecio.setText(precio);
                textStock.setText(stock);

            }
            else {
                //textMensaje.setText("no se encuentra el producto");
                JOptionPane.showMessageDialog(null,"no se encuentra el producto");
                limpiar();
            }
            stmt.close();
            conn.close();

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void actualizar(){
        String id, nombre, descripcion, precio, stock;
        id=textid.getText();
        nombre=textNombre.getText();
        descripcion=textDescripcion.getText();
        precio=textPrecio.getText();
        stock=textStock.getText();



        final String DB_URL="jdbc:mysql://localhost/producto?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";


        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="update registro_prod set nombre=?, descripcion=?,precio=?,stock=? where id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,nombre);
            pst.setString(2,descripcion);
            pst.setString(3,precio);
            pst.setString(4,stock);
            pst.setString(5,id);
            //ResultSet resultSet=pst.executeQuery();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro actualizado");
            stmt.close();
            conn.close();

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void eliminar(){
        final String DB_URL="jdbc:mysql://localhost/producto?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";
        String borrarid=textid.getText();

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="delete from registro_prod where id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,borrarid);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro borrado");
            stmt.close();
            conn.close();

        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void ver(){
        final String DB_URL="jdbc:mysql://localhost/producto?serverTimezone=UTC";
        final String USERNAME="root";
        final String PASSWORD="";
        
        model.addColumn("id");
        model.addColumn("nombre");
        model.addColumn("descripcion");
        model.addColumn("precio");
        model.addColumn("cantidad");
        model.addColumn("stock");
        table1.setModel(model);
        String[] dato = new String[6];

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            ResultSet rs=pst.executeQuery( "SELECT * FROM registro_prod");

            while (rs.next()) {
                dato[0]=rs.getString(1);
                dato[1]=rs.getString(2);
                dato[2]=rs.getString(3);
                dato[3]=rs.getString(4);
                dato[4]=rs.getString(5);
                dato[5]=rs.getString(6);
                model.addRow(dato);
            }
            table1.setModel(model);
        } catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

}
