/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import cnx.ConexionMysql;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import util.Seguridad;

/**
 *
 * @author JOSHUA
 */
public class ClienteImpl {

   
    public boolean insertar(Cliente cliente) {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "INSERT INTO cliente (id_tipo, dni, nombre, apellido, email, password, telefono, direccion, verificado) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getIdTipo());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getEmail());

            ps.setString(6, (cliente.getPassword()));

            ps.setString(7, cliente.getTelefono());
            ps.setString(8, cliente.getDireccion());
            ps.setBoolean(9, cliente.isVerificado());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("⚠️ Error al insertar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(ps, con);
        }
        return false;

    }


   
    public Cliente obtenerPorId(int idCliente) {

        Cliente cliente = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente = mapearCliente(rs);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al obtener cliente por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, con);
        }
        return cliente;
    }

  
    public List<Cliente> listarTodos() {

        List<Cliente> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "SELECT * FROM cliente";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al listar clientes: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, con);
        }
        return lista;
    }

  
    public boolean actualizar(Cliente cliente) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "UPDATE cliente SET id_tipo=?, dni=?, nombre=?, apellido=?, email=?, password=?, telefono=?, direccion=?, verificado=? "
                    + "WHERE id_cliente=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getIdTipo());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getEmail());
            ps.setString(6, cliente.getPassword());
            ps.setString(7, cliente.getTelefono());
            ps.setString(8, cliente.getDireccion());
            ps.setBoolean(9, cliente.isVerificado());
            ps.setInt(10, cliente.getIdCliente());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("⚠️ Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(ps, con);
        }
        return false;
    }

  
    public boolean eliminar(int idCliente) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("⚠️ Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(ps, con);
        }
        return false;
    }

    
    public Cliente obtenerPorEmail(String email) {
        Cliente cliente = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "SELECT * FROM cliente WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                cliente = mapearCliente(rs);
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error al obtener cliente por email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, con);
        }
        return cliente;
    }

    public Cliente login(String email, String password) {
    Cliente cliente = null;
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = ConexionMysql.getConnection();

        if (con == null) {
            System.out.println("No se pudo establecer conexión con la base de datos.");
            return null;
        }

        String sql = "SELECT * FROM cliente WHERE email = ? AND password = ? AND verificado = TRUE AND id_tipo = 2";
        ps = con.prepareStatement(sql);

        // 🔐 Encriptamos la contraseña ingresada antes de comparar
       

        ps.setString(1, email);
        ps.setString(2, password);
        rs = ps.executeQuery();

        if (rs.next()) {
            cliente = new Cliente();
            cliente.setIdCliente(rs.getInt("id_cliente"));
            cliente.setIdTipo(rs.getInt("id_tipo"));
            cliente.setDni(rs.getString("dni"));
            cliente.setNombre(rs.getString("nombre"));
            cliente.setApellido(rs.getString("apellido"));
            cliente.setEmail(rs.getString("email"));
            cliente.setPassword(rs.getString("password"));
            cliente.setTelefono(rs.getString("telefono"));
            cliente.setDireccion(rs.getString("direccion"));
            cliente.setVerificado(rs.getBoolean("verificado"));

            Timestamp fechaRegistro = rs.getTimestamp("fecha_registro");
            if (fechaRegistro != null) {
                cliente.setFechaRegistro(fechaRegistro);
            }

            System.out.println("Login exitoso: " + cliente.getNombre() + " " + cliente.getApellido());
        } else {
            System.out.println("Credenciales inválidas, usuario no verificado o tipo distinto a 2.");
        }

    } catch (Exception e) {
        System.out.println("⚠️ Error en login(): " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception ex) {
            System.out.println("⚠️ Error al cerrar recursos: " + ex.getMessage());
        }
    }

    return cliente;
}


 
    public boolean verificarCuenta(int idCliente) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "UPDATE cliente SET verificado = TRUE WHERE id_cliente = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCliente);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("⚠️ Error al verificar cuenta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(ps, con);
        }
        return false;
    }

  
    public boolean actualizarVerificado(int idCliente, boolean estado) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "UPDATE cliente SET verificado = ? WHERE id_cliente = ?";
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, estado);
            ps.setInt(2, idCliente);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("⚠️ Error al actualizar verificación: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(ps, con);
        }
        return false;
    }


    public boolean existeEmail(String email) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "SELECT 1 FROM cliente WHERE email = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("⚠️ Error al verificar email: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, con);
        }
        return false;
    }

 
    public boolean existeDni(String dni) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "SELECT 1 FROM cliente WHERE dni = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            System.out.println("⚠️ Error al verificar DNI: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rs, ps, con);
        }
        return false;
    }

    // ========================= MÉTODOS AUXILIARES =========================
    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setIdTipo(rs.getInt("id_tipo"));
        c.setDni(rs.getString("dni"));
        c.setNombre(rs.getString("nombre"));
        c.setApellido(rs.getString("apellido"));
        c.setEmail(rs.getString("email"));
        c.setPassword(rs.getString("password"));
        c.setTelefono(rs.getString("telefono"));
        c.setDireccion(rs.getString("direccion"));
        c.setVerificado(rs.getBoolean("verificado"));
        c.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        return c;
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
}
