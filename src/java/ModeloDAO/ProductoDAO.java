package ModeloDAO;

import Config.Conexion;
import Intefaces.IProducto;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//Para implementar los métodos de la interfaz IProducto agregamos despúes del nombre de la clase "implements IProducto"
//si nos marca en rojo, entonces damos click en el foquito y luego en "Implement all abstract methods".
//De esta forma nos implementará todos los métodos que hemos creado en nuestra Interfaz.

//Extendemos de la Clase Conexion para acceder a sus métodos sin tener que instanciarlos.
public class ProductoDAO extends Conexion implements IProducto{

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    @Override
    public List<Producto> Listar() {
        
        try {
            //Indicamos la consulta a realizar a la BD
            String sql = "SELECT * FROM PRODUCTOS";
            
            //Creamos una Lista de Producto, para guardar los datos.
            List<Producto> lista = new ArrayList<>();
            
            //Abrimos la conexión
            con = conectar();
            
            //Preparamos la consulta escrita mas arriba
            ps = con.prepareStatement(sql);
            
            //Como se trata de una consulta, nos va devolver datos, es por eso que usamos el ResultSet para guardarlos.
            rs = ps.executeQuery();
            
            //Con un while recorremos cada fila guardada en el ResultSet con el método "rs.next()"
            while (rs.next()) {
                
                //Instanciamos la clase Producto para poder guardar las filas de cada recorrido del while.
                Producto producto = new Producto(
                        rs.getInt(1), //Indicamos que tipo de dato y que n° de columna de la BD queremos guardar
                        rs.getString(2) ,
                        rs.getDouble(3),
                        rs.getBoolean(4));
                
                //Añadimos la la fila guardada en el objeto producto
                lista.add(producto);
            }
            //Cerramos la conexión
            rs.close();
            ps.close();
            con.close();
            
            //Retornamos la Lista
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }            
    }

    @Override
    public Producto ListarUno(int id_prod) {
        try {
            //Indicamos la consulta a realizar a la BD
            String sql = "SELECT * FROM PRODUCTOS WHERE id_prod = "+id_prod+"";
            
            //Instanciamos la clase Producto para guardar la información que se obtendrá
            Producto producto = null;
            
            //Abrimos la conexión
            con = conectar();
            
            //Preparamos la consulta escrita mas arriba
            ps = con.prepareStatement(sql);
            
            //Como se trata de una consulta, nos va devolver datos, es por eso que usamos el ResultSet para guardarlos.
            rs = ps.executeQuery();
            
            //Con un while recorremos cada fila guardada en el ResultSet con el método "rs.next()",
            //en este caso solo es una, por eso no añadimos una lista.
            if (rs.next()) {                
                //Llenamos los datos en Producto.
                producto = new Producto(
                        rs.getInt(1), //Indicamos que tipo de dato y que n° de columna de la BD queremos guardar
                        rs.getString(2) ,
                        rs.getDouble(3),
                        rs.getBoolean(4));
                
            }
            //Cerramos la conexión
            rs.close();
            ps.close();
            con.close();
            
            //Retornamos la Lista
            return producto;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    
    }

    @Override
    public boolean Insertar(Producto producto) {
        try {
            //Indicamos la sentencia SQL a realizar a la BD
            //Se coloca null, ya que el primer campo es el "id_prod", y este es auto_incrementable.
            //Se coloca default, ya que el cuarto campo es estado, y definimos en la BD que ese campo se
            //guarde por defecto como "true"
            String sql = "INSERT INTO PRODUCTOS VALUES(null, ?, ?, default)";
            
            //Creamos la variable a retornar
            boolean respuesta = false;
            
            //Abrimos la conexión
            con = conectar();
            
            //Preparamos la consulta escrita mas arriba
            ps = con.prepareStatement(sql);
            //Indicamos que al primer parametro de nuestra sentencia SQL, le otorge el nom_prod de nuestra clase Producto.
            ps.setString(1, producto.getNom_prod());
            //Indicamos que al segundo parametro de nuestra sentencia SQL, le otorge el precio_prod de nuestra clase Producto.
            ps.setDouble(2, producto.getPrecio_prod());
            
            //Preguntamos que si al ejecutar esta sentencia SQL, nos devueleve que una fila ha sido afectada,
            //entonces diremos que el registro se ha creado con exito.
            if (ps.executeUpdate() == 1) {                
                respuesta = true; // Indicamos "true" ya que se ha registrado con exito.
            }
            
            //Cerramos la conexión
            ps.close();
            con.close();
            
            //Retornamos la Lista
            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }

    @Override
    public boolean Actualizar(Producto producto) {
        try {
            //Indicamos la sentencia SQL a realizar a la BD
            String sql = "UPDATE PRODUCTOS SET nom_prod = ?, precio_prod = ? WHERE id_prod = ?";
            
            //Creamos la variable a retornar
            boolean respuesta = false;
            
            //Abrimos la conexión
            con = conectar();
            
            //Preparamos la consulta escrita mas arriba
            ps = con.prepareStatement(sql);
            //Indicamos que al primer parametro de nuestra sentencia SQL, le otorge el nom_prod de nuestra clase Producto.
            ps.setString(1, producto.getNom_prod());
            //Indicamos que al segundo parametro de nuestra sentencia SQL, le otorge el precio_prod de nuestra clase Producto.
            ps.setDouble(2, producto.getPrecio_prod());
            //Indicamos que al tercer parametro de nuestra sentencia SQL, le otorge el id_prod de nuestra clase Producto.
            ps.setDouble(3, producto.getId_prod());
            
            //Preguntamos que si al ejecutar esta sentencia SQL, nos devueleve que una fila ha sido afectada,
            //entonces diremos que el registro se ha actualizado con exito.
            if (ps.executeUpdate() == 1) {                
                respuesta = true; // Indicamos "true" ya que se ha actualizado con exito.
            }
            
            //Cerramos la conexión
            ps.close();
            con.close();
            
            //Retornamos la Lista
            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }  
    }

    @Override
    public boolean ActualizarEstado(int id_prod) {
        try {
            //Indicamos la sentencia SQL a realizar a la BD
            String sql = "UPDATE PRODUCTOS SET estado_prod = false WHERE id_prod = "+id_prod+"";
            
            //Creamos la variable a retornar
            boolean respuesta = false;
            
            //Abrimos la conexión
            con = conectar();
            
            //Preparamos la consulta escrita mas arriba
            ps = con.prepareStatement(sql);
            
            //Preguntamos que si al ejecutar esta sentencia SQL, nos devueleve que una fila ha sido afectada,
            //entonces diremos que el registro se ha actualizado con exito.
            if (ps.executeUpdate() == 1) {                
                respuesta = true; // Indicamos "true" ya que se ha actualizado con exito.
            }
            
            //Cerramos la conexión
            ps.close();
            con.close();
            
            //Retornamos la Lista
            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean Eliminar(int id_prod) {
        try {
            //Indicamos la sentencia SQL a realizar a la BD
            String sql = "DELETE FROM PRODUCTOS WHERE id_prod = "+id_prod+"";
            
            //Creamos la variable a retornar
            boolean respuesta = false;
            
            //Abrimos la conexión
            con = conectar();
            
            //Preparamos la consulta escrita mas arriba
            ps = con.prepareStatement(sql);
            
            //Preguntamos que si al ejecutar esta sentencia SQL, nos devueleve que una fila ha sido afectada,
            //entonces diremos que el registro se ha eliminado con exito.
            if (ps.executeUpdate() == 1) {                
                respuesta = true; // Indicamos "true" ya que se ha eliminado con exito.
            }
            
            //Cerramos la conexión
            ps.close();
            con.close();
            
            //Retornamos la Lista
            return respuesta;
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
