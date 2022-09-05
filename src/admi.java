import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;import java.sql.*;

public class admi extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem m1,m2;
    private JTextField IDTF;
    private JTextField nombreTF;
    private JTextField descripciontextf;
    private JTextField preciotext;
    private JTextField CantidadTF;
    private JButton actualizarbtn;
    private JButton LIMPIARButton;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    private JPanel mainPanel;
    private JTable table1;
    private JButton AÑADIRButton;
    private JButton VERButton;
    DefaultTableModel model = new DefaultTableModel();

    public admi() {

        setLayout(null);
        mb = new JMenuBar();
        setJMenuBar(mb);
        menu1 = new JMenu("REGRESAR");
        mb.add(menu1);
        m1 = new JMenuItem("Login");
        menu1.add(m1);
        m2 = new JMenuItem("Panel Administrador");
        menu1.add(m2);
        m1.addActionListener(this);
        m2.addActionListener(this);

        conectar();
        setTitle("ADMINISTRACION");
        setSize(720, 480);
        setContentPane(mainPanel);
        setLocationRelativeTo(null); // aparece la ventana en el centro
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        actualizarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        AÑADIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                añadir();
            }
        });


        LIMPIARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });

        VERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ver();
            }
        });

        table1.addComponentListener(new ComponentAdapter() {
        });
    }

    Connection con;
    PreparedStatement pst;

    public void conectar() {

        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";

        try {

            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            System.out.println("conexion exitosa");


        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");
        }

    }

    public void añadir() {
        String nombre, descripcion, precio, cantidad = null, stock;
        nombre = nombreTF.getText();
        descripcion = descripciontextf.getText();
        precio = preciotext.getText();
        cantidad = CantidadTF.getText();

        System.out.println(nombre);
        System.out.println(descripcion);
        System.out.println(precio);
        System.out.println(cantidad);

        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "insert into registro_prod(nombre, descripcion, precio, cantidad)values(?,?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setString(3, precio);
            pst.setString(4, cantidad);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ingreso de Registro Realizado");
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void limpiar() {
        IDTF.setText("");
        nombreTF.setText("");
        descripciontextf.setText("");
        preciotext.setText("");
        CantidadTF.setText("");

    }

    public void buscar() {
        String id = "0";
        id = IDTF.getText();

        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "select * from registro_prod where id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            //System.out.println(sql);


            ResultSet rs = pst.executeQuery();


            if (rs.next() == true) {
                String nombre, descripcion, precio, cantidad, stock;
                nombre = rs.getString(2);
                descripcion = rs.getString(3);
                precio = rs.getString(4);
                cantidad = rs.getString(5);


                System.out.println();
                nombreTF.setText(nombre);
                descripciontextf.setText(descripcion);
                preciotext.setText(precio);
                CantidadTF.setText(cantidad);


            } else {
                //textMensaje.setText("no se encuentra el producto");
                JOptionPane.showMessageDialog(null, "No se encuentra el producto");
                limpiar();
            }
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void actualizar() {
        String id, nombre, descripcion, precio, cantidad, stock;
        id = IDTF.getText();
        nombre = nombreTF.getText();
        descripcion = descripciontextf.getText();
        precio = preciotext.getText();
        cantidad = CantidadTF.getText();


        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "update registro_prod set nombre=?,descripcion=?,precio=?,cantidad=? where id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, descripcion);
            pst.setString(3, precio);
            pst.setString(4, cantidad);
            pst.setString(5, id);
            //ResultSet rs=pst.executeQuery();
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro actualizado");
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void eliminar() {

        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";
        String borrarid = IDTF.getText();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "delete from registro_prod where id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, borrarid);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro borrado");
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public void ver() {
        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";

        model.addColumn("id");
        model.addColumn("nombre");
        model.addColumn("descripcion");
        model.addColumn("precio");
        model.addColumn("cantidad");
        table1.setModel(model);
        String[] dato = new String[5];

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = pst.executeQuery("SELECT * FROM registro_prod");

            while (rs.next()) {
                dato[0] = rs.getString(1);
                dato[1] = rs.getString(2);
                dato[2] = rs.getString(3);
                dato[3] = rs.getString(4);
                dato[4] = rs.getString(5);
                model.addRow(dato);
            }
            table1.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }

    public static void main(String[] args) {

        admi administrador = new admi();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == m1) {
            login log = new login();
            log.setVisible(true);
            dispose();

        }
        if (e.getSource() == m2) {
            Administrador admi = new Administrador();
            admi.setVisible(true);
            dispose();

        }
    }


}



