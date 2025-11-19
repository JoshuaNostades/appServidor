/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author JOSHUA
 */
public class Proveedor {

    private int idProveedor;
    private int idEmpleado;
    private String nombreEmpresa;
    private String ruc;
    private String telefono;
    private String email;
    private String direccion;
    private String serviciosOfrecidos;
    private double calificacion;
    private String disponibilidad; // "DISPONIBLE" o "NO DISPONIBLE"
    private java.util.Date fechaRegistro;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, int idEmpleado, String nombreEmpresa, String ruc, String telefono, String email, String direccion, String serviciosOfrecidos, double calificacion, String disponibilidad, Timestamp fechaRegistro) {
        this.idProveedor = idProveedor;
        this.idEmpleado = idEmpleado;
        this.nombreEmpresa = nombreEmpresa;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.calificacion = calificacion;
        this.disponibilidad = disponibilidad;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getServiciosOfrecidos() {
        return serviciosOfrecidos;
    }

    public void setServiciosOfrecidos(String serviciosOfrecidos) {
        this.serviciosOfrecidos = serviciosOfrecidos;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public java.util.Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Proveedor(int idEmpleado, String nombreEmpresa, String ruc, String telefono, String email, String direccion, String serviciosOfrecidos, double calificacion, String disponibilidad, Timestamp fechaRegistro) {
        this.idEmpleado = idEmpleado;
        this.nombreEmpresa = nombreEmpresa;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.serviciosOfrecidos = serviciosOfrecidos;
        this.calificacion = calificacion;
        this.disponibilidad = disponibilidad;
        this.fechaRegistro = fechaRegistro;
    }
    
    

}
