

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
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
    private JButton generar;

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
        generar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generar_nota();
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

    public void generar_nota(){

        try {
            //CREACIÓN DE DOCUMENTO
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("src/pdf/nuevo.pdf"));
            doc.open();
            //DATOS FARMACIA


            String nom = "FARMACIA EL AHORRO";
            String ruc = "1726895160001";
            String fact = "NOTA DE VENTA";
            String num = "022618620/0992626788";
            String em = "farmaAhorro12@gmail.com";

            Paragraph titulo = new Paragraph(nom + "\n" + ruc + "\n" + fact + "\n" + num + "\n" + em + "\n");
            titulo.setAlignment(1);
            doc.add(titulo);
            doc.add(Chunk.NEWLINE);

            PdfPTable tabcli = new PdfPTable(6);
            tabcli.setWidthPercentage(100);
            tabcli.getDefaultCell().setBorder(0);
            PdfPCell ced = new PdfPCell(new Phrase("CÉDULA "));
            PdfPCell nomb = new PdfPCell(new Phrase("NOMBRE "));
            PdfPCell dir = new PdfPCell(new Phrase("DIRECCIÓN "));
            PdfPCell corr = new PdfPCell(new Phrase("CORREO "));
            PdfPCell tel = new PdfPCell(new Phrase("TELEFONO "));
            PdfPCell fec = new PdfPCell(new Phrase("FECHA "));

            ced.setBorder(0);
            nomb.setBorder(0);
            dir.setBorder(0);
            corr.setBorder(0);
            tel.setBorder(0);
            fec.setBorder(0);

            tabcli.addCell(ced);
            tabcli.addCell(nomb);
            tabcli.addCell(dir);
            tabcli.addCell(corr);
            tabcli.addCell(tel);
            tabcli.addCell(fec);

            tabcli.addCell(cedula.getText());
            tabcli.addCell(nombre.getText());
            tabcli.addCell(direccion.getText());
            tabcli.addCell(correo.getText());
            tabcli.addCell(telefono.getText());
            tabcli.addCell(fecha.getText());

            doc.add(tabcli);

            doc.add(Chunk.NEWLINE);

            PdfPTable tab = new PdfPTable(6);
            tab.setWidthPercentage(100);
            PdfPCell id1 = new PdfPCell(new Phrase("Id"));
            id1.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell prod = new PdfPCell(new Phrase("Producto"));
            prod.setBackgroundColor(BaseColor.YELLOW);
            PdfPCell desc = new PdfPCell(new Phrase("Descripcion"));
            desc.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell prec = new PdfPCell(new Phrase("Precio"));
            prec.setBackgroundColor(BaseColor.YELLOW);
            PdfPCell cant = new PdfPCell(new Phrase("Cantidad"));
            cant.setBackgroundColor(BaseColor.CYAN.brighter());
            PdfPCell subt = new PdfPCell(new Phrase("Subtotal"));
            subt.setBackgroundColor(BaseColor.YELLOW);


            tab.addCell(id1);
            tab.addCell(prod);
            tab.addCell(desc);
            tab.addCell(prec);
            tab.addCell(cant);
            tab.addCell(subt);

            for (int row = 0; row < tabla.getRowCount(); row++) {
                for (int column = 0; column < tabla.getColumnCount(); column++) {
                    tab.addCell(tabla.getValueAt(row, column).toString());
                }
            }

            doc.add(tab);

            doc.add(Chunk.NEWLINE);
            doc.add(Chunk.NEWLINE);

            //total factura
            PdfPTable tabtot= new PdfPTable(1);
            tabtot.setWidthPercentage(15);


            PdfPCell tot = new PdfPCell(new Phrase("TOTAL"));
            tot.setBackgroundColor(BaseColor.RED);
            tot.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
            tot.setBorder(0);
            tabtot.addCell(tot);
            tabtot.addCell("$"+resultado.getText());


            doc.add(tabtot);
            doc.close();


        }catch (Exception ex){

        }
    }



    public static void main(String[] args) {
        cajero1 cajeros = new cajero1();
    }
}
