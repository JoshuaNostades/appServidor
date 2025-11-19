/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import impl.EmpleadoImpl;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Empleado;

/**
 *
 * @author JOSHUA
 */
@WebService(serviceName = "ServicioEmpleado")
public class ServicioEmpleado {

    /**
     * Web service operation
     */
    
    EmpleadoImpl empleadoDAO = new EmpleadoImpl();
    
    @WebMethod(operationName = "login")
    public boolean login(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        return empleadoDAO.login(email, password)!= null;
    }

   
    
     @WebMethod(operationName = "registrarEmpleado")
    public boolean registrarEmpleado(
            @WebParam(name = "idTipo") int idTipo,
            @WebParam(name = "dni") String dni,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "estado") String estado
    ) {
        Empleado e = new Empleado();
        e.setId_tipo(idTipo);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setEmail(email);
        e.setPassword(password);
        e.setTelefono(telefono);
        e.setEstado(estado != null ? estado : "ACTIVO");

        return empleadoDAO.registrarEmpleado(e);
    }
    
     @WebMethod(operationName = "obtenerNombreApellido")
    public String obtenerNombreApellido(@WebParam(name = "idEmpleado") int idEmpleado) {
        Empleado empleado = empleadoDAO.getNombreApellidoById(idEmpleado);
        if (empleado != null) {
            return empleado.getNombre() + " " + empleado.getApellido();
        } else {
            return null; // o lanzar excepción si prefieres
        }
    }

    @WebMethod(operationName = "listarEmpleados")
    public Empleado[] listarEmpleados() {
        List<Empleado> lista = empleadoDAO.listarEmpleados();
        return lista.toArray(new Empleado[0]);
    }

    @WebMethod(operationName = "obtenerEmpleadoPorId")
    public Empleado obtenerEmpleadoPorId(@WebParam(name = "idEmpleado") int idEmpleado) {
        return empleadoDAO.obtenerPorId(idEmpleado);
    }
    
    @WebMethod(operationName = "obtenerEmpleadoPorEmail")
    public Empleado obtenerEmpleadoPorEmail(@WebParam(name = "email") String email) {
        return empleadoDAO.obtenerPorEmail(email);
    }

    @WebMethod(operationName = "actualizarEmpleado")
    public boolean actualizarEmpleado(
            @WebParam(name = "idEmpleado") int idEmpleado,
            @WebParam(name = "idTipo") int idTipo,
            @WebParam(name = "dni") String dni,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "estado") String estado
    ) {
        Empleado e = new Empleado();
        e.setId_empleado(idEmpleado);
        e.setId_tipo(idTipo);
        e.setDni(dni);
        e.setNombre(nombre);
        e.setApellido(apellido);
        e.setEmail(email);
        e.setPassword(password);
        e.setTelefono(telefono);
        e.setEstado(estado);

        return empleadoDAO.actualizarEmpleado(e);
    }

    @WebMethod(operationName = "eliminarEmpleado")
    public boolean eliminarEmpleado(@WebParam(name = "idEmpleado") int idEmpleado) {
        return empleadoDAO.eliminarEmpleado(idEmpleado);
    }
    
    
    
    
    
}
