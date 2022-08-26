import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JButton agregar;
    private JButton FINALIZARButton;
    private JButton eliminar;
    private JButton TOTALButton;
    private JTextField cantidad;
    private JButton agregarButton;

    DefaultTableModel model = new DefaultTableModel();

    public cajero1() {
        setTitle("FACTURACIÓN");
        setSize(720, 500);
        setContentPane(panel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        String[] titulo = new String[]{"ID", "PRODUCTO", "DESCRIPCION", "CANTIDAD", "PRECIO", "SUBTOTAL"};
        model.setColumnIdentifiers(titulo);
        tabla.setModel(model);

        //funcion agregar a la tabla
    }


    public static void main(String[] args) {
        cajero1 cajeros = new cajero1();
    }
}
