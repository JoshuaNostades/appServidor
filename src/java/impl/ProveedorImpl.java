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
    String sql = "{CALL sp_proveedor_insertar(?,?,?,?,?,?,?,?,?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, proveedor.getIdEmpleado());
        cs.setString(2, proveedor.getNombreEmpresa());
        cs.setString(3, proveedor.getRuc());
        cs.setString(4, proveedor.getTelefono());
        cs.setString(5, proveedor.getEmail());
        cs.setString(6, proveedor.getDireccion());
        cs.setString(7, proveedor.getServiciosOfrecidos());
        cs.setDouble(8, proveedor.getCalificacion());
        cs.setString(9, proveedor.getDisponibilidad());

        return cs.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("⚠️ Error al insertar proveedor: " + e.getMessage());
        return false;
    }
}


 
   public boolean actualizar(Proveedor proveedor) {
    String sql = "{CALL sp_proveedor_actualizar(?,?,?,?,?,?,?,?,?,?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, proveedor.getIdEmpleado());
        cs.setString(2, proveedor.getNombreEmpresa());
        cs.setString(3, proveedor.getRuc());
        cs.setString(4, proveedor.getTelefono());
        cs.setString(5, proveedor.getEmail());
        cs.setString(6, proveedor.getDireccion());
        cs.setString(7, proveedor.getServiciosOfrecidos());
        cs.setDouble(8, proveedor.getCalificacion());
        cs.setString(9, proveedor.getDisponibilidad());
        cs.setInt(10, proveedor.getIdProveedor());

        return cs.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("⚠️ Error al actualizar proveedor: " + e.getMessage());
        return false;
    }
}


  
    public boolean eliminar(int idProveedor) {
    String sql = "{CALL sp_proveedor_eliminar(?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, idProveedor);
        return cs.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("⚠️ Error al eliminar proveedor: " + e.getMessage());
        return false;
    }
}

   public Proveedor obtenerPorId(int idProveedor) {
    String sql = "{CALL sp_proveedor_obtenerPorId(?)}";
    Proveedor proveedor = null;

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, idProveedor);
        try (ResultSet rs = cs.executeQuery()) {
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
    String sql = "{CALL sp_proveedor_listarTodos()}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql);
         ResultSet rs = cs.executeQuery()) {

        while (rs.next()) {
            lista.add(mapearProveedor(rs));
        }

    } catch (SQLException e) {
        System.out.println("⚠️ Error al listar proveedores: " + e.getMessage());
    }

    return lista;
}


    
    
     public Proveedor getNombreEmpresaById(int idProveedor) {
    Proveedor proveedor = null;
    String sql = "{CALL sp_proveedor_getNombreEmpresa(?)}";

    try (Connection con = ConexionMysql.getConnection();
         CallableStatement cs = con.prepareCall(sql)) {

        cs.setInt(1, idProveedor);

        try (ResultSet rs = cs.executeQuery()) {
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
    
    
}
