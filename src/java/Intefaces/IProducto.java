package Intefaces;

import Modelo.Producto;
import java.util.List;

public interface IProducto {
    
    //Estos son los metodos que usarás para realizar consultas a tu base de datos;
    //si deseas puedes agregar o quitar métodos, es dependiendo a las necesidades del Proeyecto.
    //Recordar que todos los métodos que están acá al ser implementados en una clase,
    //la clase estará obligada a usar todos estos métodos.
    
    //En este caso implementaremos estos métodos en la clase ProductoDAO, del paquete ModeloDAO.
    
    public List<Producto> Listar();
    public Producto ListarUno(int id_prod);
    public boolean Insertar(Producto producto);
    public boolean Actualizar(Producto producto);
    public boolean ActualizarEstado(int id_prod);
    public boolean Eliminar(int id_prod);
}
