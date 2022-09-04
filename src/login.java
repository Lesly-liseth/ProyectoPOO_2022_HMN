import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class login extends JDialog{
    private JTextField emailTF;
    private JPasswordField passwordTF;
    private JButton CANCELButton;
    private JButton OKButton;
    private JPanel loginPanel;
    private JButton administradorButton;
    private JButton cajeroButton;
    private JButton bodegueroButton;
    private JFormattedTextField formattedTextField1;

    public Admi user;
    public login(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(1000,700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        //setVisible(true);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email=emailTF.getText();
                String password=String.valueOf(passwordTF.getPassword());
                System.out.println("boton ok");
                user=getAuthenticationUser(email,password);

                if (user!=null){
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


        CANCELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boton cancel");
                dispose();
            }
        });
        setVisible(true);
    }


    private Admi getAuthenticationUser(String email, String password){
        Admi admi =null;

        final String DB_URL="jdbc:mysql://localhost/admi?serverTimezone=UTC";
        final String USERNAME="pame";
        final String PASSWORD="1234";


        try{
            Connection conn= DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
            Statement stmt= conn.createStatement();
            String sql="SELECT * FROM admi WHERE em_A=? AND con_A=?";
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
        login login=new login(null);
        Admi user =login.user;

        if(user!=null){
            System.out.println("Autenticacion correcta:"+user.NOMBRE);
            System.out.println("email: "+user.EMAIL);
            //System.out.println("celular: "+user.celular);
            System.out.println("direccion: "+user.DIRECCION);
            System.out.println("clave: "+user.CONTRASEÑA);
        }
        else{
            System.out.println("Autenticacion fallida");
        }
    }
}
