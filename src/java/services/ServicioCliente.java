/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import impl.ClienteImpl;

import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Cliente;

/**
 *
 * @author JOSHUA
 */
@WebService(serviceName = "ServicioCliente")
public class ServicioCliente {

    public final ClienteImpl clienteDAO = new ClienteImpl();

    @WebMethod(operationName = "registrarCliente")
    public boolean registrarCliente(
            @WebParam(name = "dni") String dni,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "direccion") String direccion
    ) {
        Cliente c = new Cliente();
        c.setDni(dni);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setEmail(email);
        c.setPassword(password);
        c.setTelefono(telefono);
        c.setDireccion(direccion);
        c.setVerificado(false); // Por defecto
        c.setIdTipo(2);          // 👈 IMPORTANTE: tipo Cliente

        return clienteDAO.insertar(c);
    }

    ClienteImpl dao = new ClienteImpl();

    @WebMethod(operationName = "loginCliente")
    public boolean loginCliente(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        return dao.login(email, password) != null;
    }

    @WebMethod(operationName = "listarClientes")
    public Cliente[] listarClientes() {
        List<Cliente> lista = dao.listarTodos();
        return lista.toArray(new Cliente[0]);
    }

    @WebMethod(operationName = "actualizarCliente")
    public boolean actualizarCliente(
            @WebParam(name = "idCliente") int idCliente,
            @WebParam(name = "idTipo") int idTipo,
            @WebParam(name = "dni") String dni,
            @WebParam(name = "nombre") String nombre,
            @WebParam(name = "apellido") String apellido,
            @WebParam(name = "email") String email,
            @WebParam(name = "password") String password,
            @WebParam(name = "telefono") String telefono,
            @WebParam(name = "direccion") String direccion,
            @WebParam(name = "verificado") boolean verificado
    ) {
        Cliente c = new Cliente();
        c.setIdCliente(idCliente);
        c.setIdTipo(idTipo);
        c.setDni(dni);
        c.setNombre(nombre);
        c.setApellido(apellido);
        c.setEmail(email);
        c.setPassword(password);
        c.setTelefono(telefono);
        c.setDireccion(direccion);
        c.setVerificado(verificado);

        return dao.actualizar(c);
    }

    @WebMethod(operationName = "verificarCliente")
    public boolean verificarCliente(@WebParam(name = "idCliente") int idCliente) {
        try {
            return dao.verificarCuenta(idCliente);
        } catch (Exception e) {
            System.out.println("⚠️ Error en el servicio verificarCliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @WebMethod(operationName = "obtenerClientePorEmail")
    public Cliente obtenerClientePorEmail(@WebParam(name = "email") String email) {

        return dao.obtenerPorEmail(email);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "eliminarCliente")
    public boolean eliminarCliente(@WebParam(name = "idCliente") int idCliente) {
        try {
            return dao.eliminar(idCliente);
        } catch (Exception e) {
            System.out.println("⚠️ Error en el servicio eliminarCliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
