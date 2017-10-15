
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexion {
    Connection canalBD=null; 
    public static String servidorbd="localhost";
    public static String userbd="root";
    public static String clavebd="";
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            canalBD=DriverManager.getConnection("jdbc:mysql://"+servidorbd+"/bdkonkagrow",userbd,clavebd);  
            System.out.println("Conexion establecida");
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "error de conexion" + e.getMessage());                  
        } 
       return canalBD;    
    }
    public void querySimple(String SQL)
    {
        try
        {
            Statement st = conectar().createStatement();
            st.executeUpdate(SQL); 
            conectar().close();
        }
        catch (SQLException e)        {
            JOptionPane.showMessageDialog(null,"Datos NO Ingresados \n ERROR : " + e.getMessage());
        }
    }
    public String[] select(String sql)
    {        
        int j = 0;
        String[] datos = new String[j];
         try
        {
            Statement st = conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                j++;                               
            }
            while(rs.next()){
                for (int i = 0; i> j; i++){
                    datos[i] =rs.getString(i+1);
                }
            }          
            conectar().close();            
        }         
        catch (SQLException e)        {
            JOptionPane.showMessageDialog(null,"Datos NO Ingresados \n ERROR : " + e.getMessage());
        }
    return datos;         
    }
}
