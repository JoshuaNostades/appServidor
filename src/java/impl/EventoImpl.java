/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import cnx.ConexionMysql;
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
    PreparedStatement ps;
    ResultSet rs;

    // LISTAR
    public List<Evento> listar() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM evento";

        try {
            con = ConexionMysql.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

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
        String sql = "SELECT * FROM evento WHERE id_evento=?";

        try {
            con = ConexionMysql.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

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
        String sql = "INSERT INTO evento(id_empleado,id_proveedor,titulo,categoria,fecha_evento,lugar,estado,descripcion,imagen) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            con = ConexionMysql.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getId_empleado());
            ps.setObject(2, e.getId_proveedor());
            ps.setString(3, e.getTitulo());
            ps.setString(4, e.getCategoria());
            ps.setString(5, e.getFecha_evento());
            ps.setString(6, e.getLugar());
            ps.setString(7, e.getEstado());
            ps.setString(8, e.getDescripcion());
            ps.setString(9, e.getImagen());
            return ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // ACTUALIZAR
    public int actualizar(Evento e) {
        String sql = "UPDATE evento SET id_empleado=?, id_proveedor=?, titulo=?, categoria=?, fecha_evento=?, lugar=?, estado=?, descripcion=?, imagen=? WHERE id_evento=?";

        try {
            con = ConexionMysql.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, e.getId_empleado());
            ps.setObject(2, e.getId_proveedor());
            ps.setString(3, e.getTitulo());
            ps.setString(4, e.getCategoria());
            ps.setString(5, e.getFecha_evento());
            ps.setString(6, e.getLugar());
            ps.setString(7, e.getEstado());
            ps.setString(8, e.getDescripcion());
            ps.setString(9, e.getImagen());
            ps.setInt(10, e.getId_evento());
            return ps.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    // ELIMINAR
    public int eliminar(int id) {
        String sql = "DELETE FROM evento WHERE id_evento=?";
        try {
            con = ConexionMysql.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
}
