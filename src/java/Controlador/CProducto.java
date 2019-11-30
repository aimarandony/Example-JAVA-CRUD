package Controlador;

import Modelo.Producto;
import ModeloDAO.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//El "name" es el nombre de nuestro archivo .java o de nuestra clase, es lo mismo 
//"urlPatterns" es el nombre de nuestro servlet, por este nombre vamos a poder acceder a él
@WebServlet(name = "CProducto", urlPatterns = {"/ServletProducto"})
public class CProducto extends HttpServlet {

    //Instanciamos nuestra clase ProductoDAO, ya que haremos uso de sus métodos.
    ProductoDAO dao = new ProductoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //Mediante el request.getParameter obtendremos el metodo a realizar
            String accion = request.getParameter("op");

            //Comparamos el parametro obtenido y nos dirigimos al metodo correspondiente
            switch (accion) {
                case "listar":
                    Listar(request, response);
                    break;
                case "listarUno":
                    ListarID(request, response);
                    break;
                case "insertar":
                    Insertar(request, response);
                    break;
                case "actualizar":
                    Actualizar(request, response);
                    break;
                case "eliminar":
                    Eliminar(request, response);
                    break;
            }
        } finally {
            out.close();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void Listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Asignamos un atributo,de esta forma cuando vayamos a la vista lo obtendremos
        //En este caso pasamos el método listar de nuestra clase ProductoDAO
        request.setAttribute("listaProductos", dao.Listar());

        //Indicamos el nombre de nuestro jsp al que queremos dirigirnos.
        request.getRequestDispatcher("/listar.jsp").forward(request, response);
    }

    private void ListarID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtenemos el paremetro id, mandado desde listar.jsp en el botón editar
        int id_prod = Integer.parseInt(request.getParameter("id"));
        
        //Asignamos un atributo,de esta forma cuando vayamos a la vista lo obtendremos
        //En este caso pasamos el método listarUno de nuestra clase ProductoDAO
        request.setAttribute("producto", dao.ListarUno(id_prod));
        
        //Indicamos el nombre de nuestro jsp al que queremos dirigirnos.
        request.getRequestDispatcher("/editar.jsp").forward(request, response);
    }

    private void Insertar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtenemos los parametros obtenidos desde nuestro formulario
        //estos parametros se encuentran en nuestros "<input/>" , en el atributo "name="
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));

        //Mandamos los datos obtenidos a nuestro objeto
        Producto producto = new Producto();
        producto.setNom_prod(nombre);
        producto.setPrecio_prod(precio);

        //Guardamos la respuesta de nuestro método, que nos devuelve un true o un false.
        boolean resp = dao.Insertar(producto);

        //Condición: Sí "resp" es igual true, entonces se dirige a la vista listar.jsp
        //de lo contrario se dirige a la vista agregar.jsp
        if (resp) {
            request.getRequestDispatcher("/ServletProducto?op=listar").forward(request, response);
        } else {
            request.getRequestDispatcher("/agregar.jsp").forward(request, response);
        }
    }

    private void Actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtenemos los parametros obtenidos desde nuestro formulario
        //estos parametros se encuentran en nuestros "<input/>" , en el atributo "name="
        int id_prod = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double precio = Double.parseDouble(request.getParameter("precio"));
        
        //Mandamos los datos obtenidos a nuestro objeto
        Producto producto = new Producto();
        producto.setId_prod(id_prod);
        producto.setNom_prod(nombre);
        producto.setPrecio_prod(precio);

        //Guardamos la respuesta de nuestro método, que nos devuelve un true o un false.
        boolean resp = dao.Actualizar(producto);

        //Condición: Sí "resp" es igual true, entonces se dirige a la vista listar.jsp
        //de lo contrario se dirige a la vista editar.jsp
        if (resp) {
            request.getRequestDispatcher("/ServletProducto?op=listar").forward(request, response);
        } else {
            request.getRequestDispatcher("/editar.jsp").forward(request, response);
        }
    }

    private void Eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtenemos el paremetro id, mandado desde listar.jsp en el botón editar
        int id_prod = Integer.parseInt(request.getParameter("id"));

        //Llamamos al método Eliminar
        dao.Eliminar(id_prod);

        //Nos dirigimos a la vista listar.jsp
        request.getRequestDispatcher("/ServletProducto?op=listar").forward(request, response);

    }

}
