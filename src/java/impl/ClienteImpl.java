/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import cnx.ConexionMysql;
import java.security.MessageDigest;
import java.sql.CallableStatement;
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
        CallableStatement ps = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL insertar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
            ps = con.prepareCall(sql);
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
        CallableStatement ps = null;
        ResultSet rs = null;
        try {

            con = ConexionMysql.getConnection();
            String sql = "{CALL sp_cliente_obtener_por_id(?)}";
            ps = con.prepareCall(sql);
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
        CallableStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConexionMysql.getConnection();
            String sql = "{CALL sp_cliente_listar_todos()}";
            ps = con.prepareCall(sql);
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
        CallableStatement cs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL actualizar_cliente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
            cs = con.prepareCall(sql);

            cs.setInt(1, cliente.getIdTipo());
            cs.setString(2, cliente.getDni());
            cs.setString(3, cliente.getNombre());
            cs.setString(4, cliente.getApellido());
            cs.setString(5, cliente.getEmail());
            cs.setString(6, cliente.getPassword());
            cs.setString(7, cliente.getTelefono());
            cs.setString(8, cliente.getDireccion());
            cs.setBoolean(9, cliente.isVerificado());
            cs.setInt(10, cliente.getIdCliente());

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("⚠️ Error al actualizar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(cs, con);
        }
        return false;
    }

    public boolean eliminar(int idCliente) {
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL eliminar_cliente(?) }";
            cs = con.prepareCall(sql);
            cs.setInt(1, idCliente);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("⚠️ Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(cs, con);
        }
        return false;
    }

    public Cliente obtenerPorEmail(String email) {
        Cliente cliente = null;
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL obtener_cliente_por_email(?) }";
            cs = con.prepareCall(sql);
            cs.setString(1, email);

            rs = cs.executeQuery();

            if (rs.next()) {
                cliente = mapearCliente(rs);
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error al obtener cliente por email: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, cs, con);
        }

        return cliente;
    }

    public Cliente login(String email, String password) {
        Cliente cliente = null;
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL login_cliente(?, ?) }";

            cs = con.prepareCall(sql);
            cs.setString(1, email);
            cs.setString(2, password);

            rs = cs.executeQuery();

            if (rs.next()) {
                cliente = mapearCliente(rs);
                System.out.println("Login exitoso: " + cliente.getNombre() + " " + cliente.getApellido());
            } else {
                System.out.println("Credenciales inválidas o usuario no verificado.");
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error en login(): " + e.getMessage());
        } finally {
            cerrarRecursos(rs, cs, con);
        }

        return cliente;
    }

    public boolean verificarCuenta(int idCliente) {
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL verificar_cliente(?) }";
            cs = con.prepareCall(sql);
            cs.setInt(1, idCliente);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("⚠️ Error al verificar cuenta: " + e.getMessage());
        } finally {
            cerrarRecursos(cs, con);
        }
        return false;
    }

    public boolean actualizarVerificado(int idCliente, boolean estado) {
        Connection con = null;
        CallableStatement cs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL actualizar_verificado(?, ?) }";
            cs = con.prepareCall(sql);

            cs.setBoolean(1, estado);
            cs.setInt(2, idCliente);

            return cs.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("⚠️ Error al actualizar verificación: " + e.getMessage());
        } finally {
            cerrarRecursos(cs, con);
        }
        return false;
    }

    public boolean existeEmail(String email) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL existe_email(?) }";
            cs = con.prepareCall(sql);
            cs.setString(1, email);

            rs = cs.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("⚠️ Error al verificar email: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, cs, con);
        }
        return false;
    }

    public boolean existeDni(String dni) {
        Connection con = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            con = ConexionMysql.getConnection();
            String sql = "{ CALL existe_dni(?) }";
            cs = con.prepareCall(sql);
            cs.setString(1, dni);

            rs = cs.executeQuery();
            return rs.next();

        } catch (Exception e) {
            System.out.println("⚠️ Error al verificar DNI: " + e.getMessage());
        } finally {
            cerrarRecursos(rs, cs, con);
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
