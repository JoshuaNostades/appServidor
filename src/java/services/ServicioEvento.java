/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import impl.EventoImpl;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Evento;

/**
 *
 * @author JOSHUA
 */
@WebService(serviceName = "ServicioEvento")
public class ServicioEvento {

    EventoImpl dao = new EventoImpl();

    @WebMethod(operationName = "listarEventos")
    public List<Evento> listarEventos() {
        return dao.listar();
    }

    @WebMethod(operationName = "obtenerEventoPorId")
    public Evento obtenerEventoPorId(@WebParam(name = "id_evento") int id) {
        return dao.buscar(id);
    }

    @WebMethod(operationName = "agregarEvento")
    public int agregarEvento(
            @WebParam(name = "id_empleado") int id_empleado,
            @WebParam(name = "id_proveedor") int id_proveedor,
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "categoria") String categoria,
            @WebParam(name = "fecha_evento") String fecha_evento,
            @WebParam(name = "lugar") String lugar,
            @WebParam(name = "estado") String estado,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "imagen") String imagen) {

        Evento e = new Evento();
        e.setId_empleado(id_empleado);
        e.setId_proveedor(id_proveedor);
        e.setTitulo(titulo);
        e.setCategoria(categoria);
        e.setFecha_evento(fecha_evento);
        e.setLugar(lugar);
        e.setEstado(estado);
        e.setDescripcion(descripcion);
        e.setImagen(imagen);

        return dao.agregar(e);
    }

    @WebMethod(operationName = "actualizarEvento")
    public int actualizarEvento(
            @WebParam(name = "id_evento") int id_evento,
            @WebParam(name = "id_empleado") int id_empleado,
            @WebParam(name = "id_proveedor") int id_proveedor,
            @WebParam(name = "titulo") String titulo,
            @WebParam(name = "categoria") String categoria,
            @WebParam(name = "fecha_evento") String fecha_evento,
            @WebParam(name = "lugar") String lugar,
            @WebParam(name = "estado") String estado,
            @WebParam(name = "descripcion") String descripcion,
            @WebParam(name = "imagen") String imagen) {

        Evento e = new Evento();
        e.setId_evento(id_evento);
        e.setId_empleado(id_empleado);
        e.setId_proveedor(id_proveedor);
        e.setTitulo(titulo);
        e.setCategoria(categoria);
        e.setFecha_evento(fecha_evento);
        e.setLugar(lugar);
        e.setEstado(estado);
        e.setDescripcion(descripcion);
        e.setImagen(imagen);

        return dao.actualizar(e);
    }

    @WebMethod(operationName = "eliminarEvento")
    public int eliminarEvento(@WebParam(name = "id_evento") int id_evento) {
        return dao.eliminar(id_evento);
    }
}
