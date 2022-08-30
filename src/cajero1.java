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
    private JButton finalizar;
    private JButton eliminar;
    private JButton total;
    private JTextField cantidad;
    private JButton agregar;
    private JTextField resultado;
    private JButton LIMPIARButton;

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



        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar_registo();
            }
        });

        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
                agregar();
                producto.setText("");
                cantidad.setText("");

            }
        });


        finalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock();
            }
        });
        total.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total();
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

        String produ = producto.getText(); //variable que contendra el nombre del producto a agregar
        String buscar = ""; //inicializando variable

        if(!"".equals(produ)) //evaluación del campo producto mientras sea diferente de vacio
        {
            buscar = "WHERE nombre = '" + produ + "'";

        }

        try {


            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = null;

            String sql = "SELECT id,nombre,descripcion,precio,cantidad, precio*cantidad  FROM registro_prod  "+buscar;
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery(sql);


            ResultSetMetaData datos = rs.getMetaData();
            int colum = datos.getColumnCount();

            while (rs.next()) {

                Object[] filas = new Object[colum];
                for (int i = 0; i < colum; i++) {
                    filas[i] = rs.getObject(i+1);

                }
                model.addRow(filas);

            }

            stmt.close();
            conn.close();


        }
        catch (SQLException ex){

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error de conección");

        }

    }

    public void actualizar(){

        final String DB_URL="jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";

        String produ = producto.getText(); //variable que contendra el nombre del producto a agregar
        String buscar = ""; //inicializando variable

        String cant = cantidad.getText();

        if(!"".equals(produ)) //evaluación del campo producto mientras sea diferente de vacio
        {
            buscar = "WHERE nombre = '" + produ + "'";
        }

        try {


            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE registro_prod SET cantidad =?"+buscar ;
            pst = conn.prepareStatement(sql);

            pst.setString(1,cant);

            pst.executeUpdate();
            stmt.close();
            conn.close();


        }
        catch (SQLException ex){

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Ingrese la cantidad del producto");

        }


    }

    public void stock(){

        final String DB_URL="jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";

        String produ = producto.getText(); //variable que contendra el nombre del producto a agregar
        String buscar = ""; //inicializando variable


        if(!"".equals(produ)) //evaluación del campo producto mientras sea diferente de vacio
        {
            buscar = "WHERE nombre = '" + produ + "'";
        }

        try {


            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "UPDATE registro_prod SET stock = (stock - cantidad)"+buscar ;
            pst = conn.prepareStatement(sql);

            pst.executeUpdate(sql);
            stmt.close();
            conn.close();
            JOptionPane.showMessageDialog(null,"GRACIAS POR SU COMPRA");

        }
        catch (SQLException ex){

            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Stock");

        }

    }

    public void total(){

        double acum = 0;
        double tot = 0;
        int filas = tabla.getRowCount();

        for (int i = 0; i < filas; i++) {
            acum = Double.parseDouble(tabla.getValueAt(i, 5).toString());
            tot+=acum;
        }
        resultado.setText(""+tot);

    }

    public void eliminar_registo(){
        int fila = tabla.getSelectedRow();
        model.removeRow(fila);
    }


    public static void main(String[] args) {
        cajero1 cajeros = new cajero1();
    }
}
