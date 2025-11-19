/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package impl;

import cnx.ConexionMysql;
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
    
        
        String sql = "INSERT INTO empleado (id_tipo, dni, nombre, apellido, email, password, telefono, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empleado.getId_tipo());
            ps.setString(2, empleado.getDni());
            ps.setString(3, empleado.getNombre());
            ps.setString(4, empleado.getApellido());
            ps.setString(5, empleado.getEmail());
            ps.setString(6, empleado.getPassword());
            ps.setString(7, empleado.getTelefono());
            ps.setString(8, empleado.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    
    }
    
    public Empleado getNombreApellidoById(int idEmpleado) {
        Empleado empleado = null;
        String sql = "SELECT nombre, apellido FROM empleado WHERE id_empleado = ?";

        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpleado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setId_empleado(idEmpleado);
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setApellido(rs.getString("apellido"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleado;
    }
    

  
    public List<Empleado> listarEmpleados() {
    
        
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleado";
        try (Connection con = ConexionMysql.getConnection();
                Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId_empleado(rs.getInt("id_empleado"));
                e.setId_tipo(rs.getInt("id_tipo"));
                e.setDni(rs.getString("dni"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setEmail(rs.getString("email"));
                e.setPassword(rs.getString("password"));
                e.setTelefono(rs.getString("telefono"));
                e.setEstado(rs.getString("estado"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

 
    public Empleado obtenerPorId(int id_empleado) {
    
        
        String sql = "SELECT * FROM empleado WHERE id_empleado=?";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_empleado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado e = new Empleado();
                    e.setId_empleado(rs.getInt("id_empleado"));
                    e.setId_tipo(rs.getInt("id_tipo"));
                    e.setDni(rs.getString("dni"));
                    e.setNombre(rs.getString("nombre"));
                    e.setApellido(rs.getString("apellido"));
                    e.setEmail(rs.getString("email"));
                    e.setPassword(rs.getString("password"));
                    e.setTelefono(rs.getString("telefono"));
                    e.setEstado(rs.getString("estado"));
                    return e;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public Empleado obtenerPorEmail(String email) {
        Empleado emp = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "SELECT * FROM empleado WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                emp = mapearEmpleado(rs);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al obtener empleado por email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, con);
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


    
    

   
    public boolean actualizarEmpleado(Empleado empleado) {
    
        
        String sql = "UPDATE empleado SET id_tipo=?, dni=?, nombre=?, apellido=?, email=?, password=?, telefono=?, estado=? "
                   + "WHERE id_empleado=?";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empleado.getId_empleado());
            ps.setString(2, empleado.getDni());
            ps.setString(3, empleado.getNombre());
            ps.setString(4, empleado.getApellido());
            ps.setString(5, empleado.getEmail());
            ps.setString(6, empleado.getPassword());
            ps.setString(7, empleado.getTelefono());
            ps.setString(8, empleado.getEstado());
            ps.setInt(9, empleado.getId_empleado());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    
    }


    public Empleado login(String email, String password) {
        Empleado emp = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Obtener conexión desde tu clase de conexión
            con = ConexionMysql.getConnection();

            if (con == null) {
                System.out.println("⚠️ No se pudo establecer conexión con la base de datos.");
                return null;
            }

            String sql = "SELECT * FROM empleado WHERE email = ? AND password = ? AND estado = 'ACTIVO'";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Empleado();
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

                System.out.println("✅ Login exitoso para: " + emp.getNombre() + " " + emp.getApellido());
            } else {
                System.out.println("❌ Credenciales inválidas o empleado inactivo.");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error en login(): " + e.getMessage());
            e.printStackTrace();
        } finally {
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
            } catch (Exception ex) {
                System.out.println("⚠️ Error al cerrar recursos: " + ex.getMessage());
            }
        }

        return emp;
    }

  
    public boolean eliminarEmpleado(int id_empleado) {
    
    
         String sql = "DELETE FROM empleado WHERE id_empleado=?";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_empleado);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    

}
