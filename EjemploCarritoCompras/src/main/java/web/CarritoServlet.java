package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        
       
        
        //Creamos o recuperamos el objeto http session
        
        HttpSession sesion = request.getSession();
        
        //Recuperamos la lista de articulos agregados previamente 
        
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");
        
        //Validacion de lista de articulos existe o no
        
        if(articulos == null){
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }
        
         //Procesamos el articulo.
        
        
        String articuloNuevo = request.getParameter("articulo");
        
        //Revisamos articulo nuevo
        
        if(articuloNuevo != null && !articuloNuevo.trim().equals("")){
            articulos.add(articuloNuevo);
        }
        
        try ( //Imprimimos lista de articulso
                PrintWriter out = response.getWriter()) {
            out.print("<h1>Articulos en el carrito:</h1>");
            out.print("<br>");
            
            
            //iteramos los articulso
            
            for(String articulo: articulos){
                out.print("<li>" + articulo + "</li>");
            }
            
            //agregamos un link para regresar
            
            out.print("<a href='/EjemploCarritoCompras'>Volver</a>");
        }
        
    }
}
