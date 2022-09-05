import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login extends JDialog{
    private JTextField correo;
    private JPasswordField contra;

    private JButton administrador;
    private JButton cajero;
    private JButton bodeguero;
    private JPanel loginPanel;
    public Admi admi;
    public login (JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(1280,720));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);

        administrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=correo.getText();
                String password=String.valueOf(contra.getPassword());
                System.out.println("boton ok");
                admi=getAuthenticationAdmi(email,password);

                if (admi!=null){
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(
                            login.this,"email o password incorrectos",
                            "intente nuevamente",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            }
        });

    }


    private Admi getAuthenticationAdmi(String email, String password){
        Admi admi =null;

        final String DB_URL="jdbc:mysql://localhost/productos?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";

        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="SELECT * FROM admi WHERE emA=? AND contraA=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            System.out.println("conexion ok");
            ResultSet resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                admi=new Admi();
                admi.NOMBRE=resultSet.getString("NOMBRE");
                admi.EMAIL=resultSet.getString("EMAIL");
                admi.CELULAR=resultSet.getString("CELULAR");
                admi.DIRECCION=resultSet.getString("DIRECCION");
                admi.CONTRASEÑA=resultSet.getString("CONTRASEÑA");
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            System.out.println("error de...");
            e.printStackTrace();
        }

        return admi;
    }


    public static void main(String[] args) {
        login Login=new login(null);
        Admi user =Login.admi;

        if(user!=null){
            System.out.println("Autenticacion correcta:"+user.NOMBRE);
            System.out.println("email: "+user.EMAIL);
            System.out.println("celular: "+user.CELULAR);
            System.out.println("direccion: "+user.DIRECCION);
            System.out.println("clave: "+user.CONTRASEÑA);
        }
        else{
            System.out.println("Autenticacion fallida");
        }
    }
}