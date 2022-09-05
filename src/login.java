import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JFrame {

    private JTextField email;
    private JPasswordField password;
    private JButton OK;
    private JButton cancelar;
    private JPanel mainPanel;

    public login() {

        setTitle("Login");
        setSize(720, 480);
        setContentPane(mainPanel);
        setLocationRelativeTo(null); // aparece la ventana en el centro
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        Connection con;
        PreparedStatement pst;

        OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = email.getText();
                String pass = String.valueOf(password.getPassword());

                String sql = "SELECT email,password,rol FROM usuarios WHERE email='"+correo+"' ";
                try {
                    Connection con = conectar_BDD();
                    PreparedStatement ps  = con.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();

                    if(rs.next()) {

                        String em = rs.getString("email");
                        String p = rs.getString("password");
                        String r = rs.getString("rol");

                        if (pass.equals(p)) {
                            if (r.equals("cajero")) {

                                System.out.println("Bienvenido cajero");
                                cajero1 cajero = new cajero1();
                                cajero.setVisible(true);
                                dispose();

                            } else if (r.equals("administrador")) {
                                System.out.println("Bienvenido administrador");
                                Administrador administrador = new Administrador();
                                administrador.setVisible(true);
                                dispose();

                            } else if(r.equals("bodeguero")){
                                System.out.println("Bienvenido bodeguero");
                                Bodeguero bodeguero = new Bodeguero();
                                bodeguero.setVisible(true);
                                dispose();
                            }


                        } else {
                            JOptionPane.showMessageDialog(null, "USUARIO O CONTRASEÑA INCORRECTOS");

                        }
                    }
                }
                catch (SQLException ex){
                    ex.printStackTrace();
                    System.out.println("ERROR DE CONEXIÓN A LA BASE DE DATOS");
                }



            }
        });
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static Connection conectar_BDD() {

        final String DB_URL = "jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME = "pame";
        final String PASSWORD = "1234";

        try {

            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = con.createStatement();
            System.out.println("conexion exitosa");

            return con;


        } catch (SQLException ex) {

            ex.printStackTrace();
            System.out.println("ERROR DE CONEXIÓN A LA BASE DE DATOS");
            return null;
        }


    }

    public static void main(String[] args) {
        login Login = new login();
    }
}
