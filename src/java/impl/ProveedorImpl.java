/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import cnx.ConexionMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Proveedor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author JOSHUA
 */
public class ProveedorImpl{


    public boolean insertar(Proveedor proveedor) {
        String sql = "INSERT INTO proveedor (id_empleado, nombre_empresa, ruc, telefono, email, direccion, servicios_ofrecidos, calificacion, disponibilidad) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, proveedor.getIdEmpleado());
            ps.setString(2, proveedor.getNombreEmpresa());
            ps.setString(3, proveedor.getRuc());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getEmail());
            ps.setString(6, proveedor.getDireccion());
            ps.setString(7, proveedor.getServiciosOfrecidos());
            ps.setDouble(8, proveedor.getCalificacion());
            ps.setString(9, proveedor.getDisponibilidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("⚠️ Error al insertar proveedor: " + e.getMessage());
            return false;
        }
    }

 
    public boolean actualizar(Proveedor proveedor) {
        String sql = "UPDATE proveedor SET id_empleado=?, nombre_empresa=?, ruc=?, telefono=?, email=?, direccion=?, servicios_ofrecidos=?, calificacion=?, disponibilidad=? "
                   + "WHERE id_proveedor=?";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, proveedor.getIdEmpleado());
            ps.setString(2, proveedor.getNombreEmpresa());
            ps.setString(3, proveedor.getRuc());
            ps.setString(4, proveedor.getTelefono());
            ps.setString(5, proveedor.getEmail());
            ps.setString(6, proveedor.getDireccion());
            ps.setString(7, proveedor.getServiciosOfrecidos());
            ps.setDouble(8, proveedor.getCalificacion());
            ps.setString(9, proveedor.getDisponibilidad());
            ps.setInt(10, proveedor.getIdProveedor());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("⚠️ Error al actualizar proveedor: " + e.getMessage());
            return false;
        }
    }

  
    public boolean eliminar(int idProveedor) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor=?";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("⚠️ Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

    public Proveedor obtenerPorId(int idProveedor) {
        String sql = "SELECT * FROM proveedor WHERE id_proveedor=?";
        Proveedor proveedor = null;
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = mapearProveedor(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("⚠️ Error al obtener proveedor por ID: " + e.getMessage());
        }
        return proveedor;
    }

  
    public List<Proveedor> listarTodos() {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor ORDER BY fecha_registro DESC";
        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearProveedor(rs));
            }

        } catch (SQLException e) {
            System.out.println("⚠️ Error al listar proveedores: " + e.getMessage());
        }
        return lista;
    }

    // Método auxiliar para mapear un ResultSet a un objeto Proveedor
    private Proveedor mapearProveedor(ResultSet rs) throws SQLException {
        Proveedor p = new Proveedor();
        p.setIdProveedor(rs.getInt("id_proveedor"));
        p.setIdEmpleado(rs.getInt("id_empleado"));
        p.setNombreEmpresa(rs.getString("nombre_empresa"));
        p.setRuc(rs.getString("ruc"));
        p.setTelefono(rs.getString("telefono"));
        p.setEmail(rs.getString("email"));
        p.setDireccion(rs.getString("direccion"));
        p.setServiciosOfrecidos(rs.getString("servicios_ofrecidos"));
        p.setCalificacion(rs.getDouble("calificacion"));
        p.setDisponibilidad(rs.getString("disponibilidad"));
        p.setFechaRegistro(rs.getTimestamp("fecha_registro"));
        return p;
    }
    
     public Proveedor getNombreEmpresaById(int idProveedor) {
        Proveedor proveedor = null;
        String sql = "SELECT nombre_empresa FROM proveedor WHERE id_proveedor = ?";

        try (Connection con = ConexionMysql.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor();
                    proveedor.setIdProveedor(idProveedor);
                    proveedor.setNombreEmpresa(rs.getString("nombre_empresa"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedor;
    }
    
    
    
    
}
