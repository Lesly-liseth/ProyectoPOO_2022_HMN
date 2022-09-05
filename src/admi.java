import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.sql.*;

public class admi extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem m1,m2;
    private JTextField IDTF;
    private JTextField emailTF;
    private JButton editarbtn;
    private JButton buscarButton;
    private JButton eliminarButton;
    private JPanel mainPanel;
    private JTable table1;
    private JButton agregarButton;
    private JTextField passTF;
    private JTextField rolTF;
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

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscar();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        editarbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizar();
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                añadir();
            }
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
        String email,password, rol= null;
        email = emailTF.getText();
        password = passTF.getText();
        rol = rolTF.getText();


        System.out.println(email);
        System.out.println(password);
        System.out.println(rol);


        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "insert into registro_prod(email, password, rol)values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, rol);


            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Ingreso de Registro Realizado");
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
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
                String email, password, rol;
                email = rs.getString(2);
                password = rs.getString(3);
                rol = rs.getString(4);

                System.out.println();
                emailTF.setText(email);
                passTF.setText(password);
                rolTF.setText(rol);

            } else {
                //textMensaje.setText("no se encuentra el producto");
                JOptionPane.showMessageDialog(null, "No se encuentra el producto");
                eliminar();
            }
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("SQL incorrecto");

        }
    }
    public void actualizar() {
        String id, email, password, rol;
        id = IDTF.getText();
        email = emailTF.getText();
        password = passTF.getText();
        rol = rolTF.getText();


        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";


        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "update registro_prod set email=?,password=?,rol=? where id=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            pst.setString(3, rol);
            pst.setString(4, id);
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



