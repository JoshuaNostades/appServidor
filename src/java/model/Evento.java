/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author JOSHUA
 */
public class Evento {
    
     private int id_evento;
    private int id_empleado;
    private Integer id_proveedor;
    private String titulo;
    private String categoria;
    private String fecha_evento;
    private String lugar;
    private String estado;
    private String descripcion;
    private String fecha_creacion;
    private String imagen;
    
   

    public Evento(String titulo, String categoria, String fecha_evento, String lugar, String estado, String descripcion) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.fecha_evento = fecha_evento;
        this.lugar = lugar;
        this.estado = estado;
        this.descripcion = descripcion;
    }
                
                

    public Evento(int id_evento, int id_empleado, Integer id_proveedor, String titulo, String categoria, String fecha_evento, String lugar, String estado, String descripcion, String fecha_creacion, String imagen) {
        this.id_evento = id_evento;
        this.id_empleado = id_empleado;
        this.id_proveedor = id_proveedor;
        this.titulo = titulo;
        this.categoria = categoria;
        this.fecha_evento = fecha_evento;
        this.lugar = lugar;
        this.estado = estado;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.imagen = imagen;
    }

    public Evento() {
    }

    public int getId_evento() {
        return id_evento;
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFecha_evento() {
        return fecha_evento;
    }

    public void setFecha_evento(String fecha_evento) {
        this.fecha_evento = fecha_evento;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
    
}
