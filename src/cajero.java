import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cajero extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTable table1;
    private JTextField textField7;
    private JTextField textField8;
    private JButton buscarButton;
    private JTextField textField9;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JPanel cajero;

    DefaultTableModel tabla = new DefaultTableModel();

    String primera[] = {"ID", "PRODUCTO", "DESCRIPCIÓN","CANTIDAD","PRECIO","SUBTOTAL"};
    public cajero() {

        setTitle("cajero");
        setContentPane(cajero);
        setMinimumSize(new Dimension(640,480));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setVisible(true);
        tabla.setColumnIdentifiers(primera);
        
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }



    public static void main(String[] args) {
        cajero cajeroo = new cajero();
        JTable table = new JTable();
    }
}
