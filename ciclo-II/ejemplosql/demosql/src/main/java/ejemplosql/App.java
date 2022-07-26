package ejemplosql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import org.sqlite.JDBC;

public class App 
{    
    static String url = "jdbc:sqlite:C:\\Java\\hr.db";
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        consultaEmpleados();  
        System.out.println("consulta empleados ---:  ");
        consultaempleado(101);

    }

    public static void consultaEmpleados(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conexion = DriverManager.getConnection(url);
            if (conexion != null) {
                System.out.println("Conectado");
            }
            double salariot = 15000.0;
            String sql="select * from employees where salary>" + salariot;
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString("last_name");
                Double salario = rs.getDouble("Salary");
                System.out.println(id+"\t" + nombre + "\t" + apellido + "\t" + salario);
            } 
            rs.close();
            stm.close();
            conexion.close();
        }
        catch (Exception e){
            System.out.println("Errorx: " + e.getMessage());
        }
    }
    //prepared Statement

    public static void consultaempleado(int id_empleado) {
        try{
            String sql = "select * from employees where employee_id=?";
            Connection conexion = DriverManager.getConnection(url);
            PreparedStatement stm = conexion.prepareStatement(sql);
            stm.setInt(1, 101);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString("last_name");
                Double salario = rs.getDouble("Salary");
                System.out.println(id+'\t'+nombre+'\t'+apellido+'\t'+salario);
            }
            rs.close();
            stm.close();
            conexion.close();
        }
        catch(Exception e){
            System.out.println("Errorx: " + e.getMessage());
        }        
    }
}
