/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import impl.ProveedorImpl;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Proveedor;

/**
 *
 * @author JOSHUA
 */
@WebService(serviceName = "ServicioProveedor")
public class ServicioProveedor {

   private final ProveedorImpl proveedorDAO = new ProveedorImpl();

    @WebMethod(operationName = "insertarProveedor")
    public boolean insertarProveedor(
            @WebParam(name = "idEmpleado") int idEmpleado,
            @WebParam(name = "nombreEmpresa") String nombreEmpresa,
            @WebParam(name = "ruc") String ruc,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "email") String email,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "serviciosOfrecidos") String serviciosOfrecidos,
            @WebParam(name = "calificacion") double calificacion,
            @WebParam(name = "disponibilidad") String disponibilidad
    ) {
        Proveedor p = new Proveedor();
        p.setIdEmpleado(idEmpleado);
        p.setNombreEmpresa(nombreEmpresa);
        p.setRuc(ruc);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setDireccion(direccion);
        p.setServiciosOfrecidos(serviciosOfrecidos);
        p.setCalificacion(calificacion);
        p.setDisponibilidad(disponibilidad);
        return proveedorDAO.insertar(p);
    }

    @WebMethod(operationName = "actualizarProveedor")
    public boolean actualizarProveedor(
            @WebParam(name = "idProveedor") int idProveedor,
            @WebParam(name = "idEmpleado") int idEmpleado,
            @WebParam(name = "nombreEmpresa") String nombreEmpresa,
            @WebParam(name = "ruc") String ruc,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "email") String email,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "serviciosOfrecidos") String serviciosOfrecidos,
            @WebParam(name = "calificacion") double calificacion,
            @WebParam(name = "disponibilidad") String disponibilidad
    ) {
        Proveedor p = new Proveedor();
        p.setIdProveedor(idProveedor);
        p.setIdEmpleado(idEmpleado);
        p.setNombreEmpresa(nombreEmpresa);
        p.setRuc(ruc);
        p.setTelefono(telefono);
        p.setEmail(email);
        p.setDireccion(direccion);
        p.setServiciosOfrecidos(serviciosOfrecidos);
        p.setCalificacion(calificacion);
        p.setDisponibilidad(disponibilidad);
        return proveedorDAO.actualizar(p);
    }

    @WebMethod(operationName = "eliminarProveedor")
    public boolean eliminarProveedor(@WebParam(name = "idProveedor") int idProveedor) {
        return proveedorDAO.eliminar(idProveedor);
    }
    
    
     @WebMethod(operationName = "obtenerNombreEmpresa")
    public String obtenerNombreEmpresa(@WebParam(name = "idProveedor") int idProveedor) {
        Proveedor proveedor = proveedorDAO.getNombreEmpresaById(idProveedor);
        if (proveedor != null) {
            return proveedor.getNombreEmpresa();
        } else {
            return null; // o lanzar excepción si prefieres
        }
    }

    @WebMethod(operationName = "obtenerProveedor")
    public Proveedor obtenerProveedor(@WebParam(name = "idProveedor") int idProveedor) {
        return proveedorDAO.obtenerPorId(idProveedor);
    }

    @WebMethod(operationName = "listarProveedores")
    public List<Proveedor> listarProveedores() {
        return proveedorDAO.listarTodos();
    }
    
    
    
    
}
