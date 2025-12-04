/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import cnx.ConexionMysql;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Evento;

/**
 *
 * @author JOSHUA
 */
public class EventoImpl {
    
    
    Connection con;
CallableStatement cs;
ResultSet rs;


    // LISTAR
   public List<Evento> listar() {
    List<Evento> lista = new ArrayList<>();

    try {
        con = ConexionMysql.getConnection();
        cs = con.prepareCall("{CALL sp_evento_listar()}");
        rs = cs.executeQuery();

        while (rs.next()) {
            Evento e = new Evento();
            e.setId_evento(rs.getInt("id_evento"));
            e.setId_empleado(rs.getInt("id_empleado"));
            e.setId_proveedor(rs.getInt("id_proveedor"));
            e.setTitulo(rs.getString("titulo"));
            e.setCategoria(rs.getString("categoria"));
            e.setFecha_evento(rs.getString("fecha_evento"));
            e.setLugar(rs.getString("lugar"));
            e.setEstado(rs.getString("estado"));
            e.setDescripcion(rs.getString("descripcion"));
            e.setFecha_creacion(rs.getString("fecha_creacion"));
            e.setImagen(rs.getString("imagen"));
            lista.add(e);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return lista;
}


    // BUSCAR POR ID
   public Evento buscar(int id) {
    Evento e = null;

    try {
        con = ConexionMysql.getConnection();
        cs = con.prepareCall("{CALL sp_evento_buscar(?)}");
        cs.setInt(1, id);
        rs = cs.executeQuery();

        if (rs.next()) {
            e = new Evento(
                rs.getInt("id_evento"),
                rs.getInt("id_empleado"),
                rs.getInt("id_proveedor"),
                rs.getString("titulo"),
                rs.getString("categoria"),
                rs.getString("fecha_evento"),
                rs.getString("lugar"),
                rs.getString("estado"),
                rs.getString("descripcion"),
                rs.getString("fecha_creacion"),
                rs.getString("imagen")
            );
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return e;
}


    // AGREGAR
    public int agregar(Evento e) {
    try {
        con = ConexionMysql.getConnection();
        cs = con.prepareCall("{CALL sp_evento_agregar(?,?,?,?,?,?,?,?,?)}");

        cs.setInt(1, e.getId_empleado());
        cs.setObject(2, e.getId_proveedor());
        cs.setString(3, e.getTitulo());
        cs.setString(4, e.getCategoria());
        cs.setString(5, e.getFecha_evento());
        cs.setString(6, e.getLugar());
        cs.setString(7, e.getEstado());
        cs.setString(8, e.getDescripcion());
        cs.setString(9, e.getImagen());

        return cs.executeUpdate();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}


    // ACTUALIZAR
    public int actualizar(Evento e) {
    try {
        con = ConexionMysql.getConnection();
        cs = con.prepareCall("{CALL sp_evento_actualizar(?,?,?,?,?,?,?,?,?,?)}");

        cs.setInt(1, e.getId_empleado());
        cs.setObject(2, e.getId_proveedor());
        cs.setString(3, e.getTitulo());
        cs.setString(4, e.getCategoria());
        cs.setString(5, e.getFecha_evento());
        cs.setString(6, e.getLugar());
        cs.setString(7, e.getEstado());
        cs.setString(8, e.getDescripcion());
        cs.setString(9, e.getImagen());
        cs.setInt(10, e.getId_evento());

        return cs.executeUpdate();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}


    // ELIMINAR
    public int eliminar(int id) {
    try {
        con = ConexionMysql.getConnection();
        cs = con.prepareCall("{CALL sp_evento_eliminar(?)}");
        cs.setInt(1, id);
        return cs.executeUpdate();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}

    
}
