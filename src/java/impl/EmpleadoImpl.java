/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import cnx.ConexionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;

/**
 *
 * @author JOSHUA
 */
public class EmpleadoImpl extends ConexionMysql  {


    public boolean registrarEmpleado(Empleado empleado) {

    String sql = "{CALL sp_empleado_registrar(?, ?, ?, ?, ?, ?, ?, ?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, empleado.getId_tipo());
        cs.setString(2, empleado.getDni());
        cs.setString(3, empleado.getNombre());
        cs.setString(4, empleado.getApellido());
        cs.setString(5, empleado.getEmail());
        cs.setString(6, empleado.getPassword());
        cs.setString(7, empleado.getTelefono());
        cs.setString(8, empleado.getEstado());

        return cs.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
    public Empleado getNombreApellidoById(int idEmpleado) {

    Empleado emp = null;
    String sql = "{CALL sp_empleado_nombre_apellido(?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, idEmpleado);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                emp = new Empleado();
                emp.setId_empleado(idEmpleado);
                emp.setNombre(rs.getString("nombre"));
                emp.setApellido(rs.getString("apellido"));
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emp;
}

    

  
  public List<Empleado> listarEmpleados() {

    List<Empleado> lista = new ArrayList<>();
    String sql = "{CALL sp_empleado_listar()}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql);
         ResultSet rs = cs.executeQuery()) {

        while (rs.next()) {
            Empleado e = mapearEmpleado(rs);
            lista.add(e);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return lista;
}

 
  public Empleado obtenerPorId(int id_empleado) {

    Empleado emp = null;
    String sql = "{CALL sp_empleado_obtenerPorId(?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, id_empleado);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                emp = mapearEmpleado(rs);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emp;
}

    
    public Empleado obtenerPorEmail(String email) {

    Empleado emp = null;
    String sql = "{CALL sp_empleado_obtenerPorEmail(?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setString(1, email);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                emp = mapearEmpleado(rs);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emp;
}

    
      private void cerrarRecursos(PreparedStatement ps, Connection con) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al cerrar recursos: " + e.getMessage());
        }
    }

   
    
    public boolean actualizarEmpleado(Empleado empleado) {

    String sql = "{CALL sp_empleado_actualizar(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, empleado.getId_empleado());
        cs.setInt(2, empleado.getId_tipo());
        cs.setString(3, empleado.getDni());
        cs.setString(4, empleado.getNombre());
        cs.setString(5, empleado.getApellido());
        cs.setString(6, empleado.getEmail());
        cs.setString(7, empleado.getPassword());
        cs.setString(8, empleado.getTelefono());
        cs.setString(9, empleado.getEstado());

        return cs.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}



    public Empleado login(String email, String password) {

    Empleado emp = null;
    String sql = "{CALL sp_empleado_login(?, ?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setString(1, email);
        cs.setString(2, password);

        try (ResultSet rs = cs.executeQuery()) {
            if (rs.next()) {
                emp = mapearEmpleado(rs);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return emp;
}


  
    public boolean eliminarEmpleado(int id_empleado) {

    String sql = "{CALL sp_empleado_eliminar(?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, id_empleado);

        return cs.executeUpdate() > 0;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
     private void cerrarRecursos(ResultSet rs, PreparedStatement ps, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al cerrar recursos: " + e.getMessage());
        }
    }
    
  private Empleado mapearEmpleado(ResultSet rs) throws SQLException {
    Empleado emp = new Empleado();

    emp.setId_empleado(rs.getInt("id_empleado"));
    emp.setId_tipo(rs.getInt("id_tipo"));
    emp.setDni(rs.getString("dni"));
    emp.setNombre(rs.getString("nombre"));
    emp.setApellido(rs.getString("apellido"));
    emp.setEmail(rs.getString("email"));
    emp.setPassword(rs.getString("password"));
    emp.setTelefono(rs.getString("telefono"));
    emp.setEstado(rs.getString("estado"));
    emp.setFecha_registro(rs.getTimestamp("fecha_registro"));

    return emp;
}


    
    

   
    
    
    

}
