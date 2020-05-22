/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.modelo.Blog;
import com.emergentes.utiles.ConexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet(name = "MainControlador", urlPatterns = {"/MainControlador"})
public class MainControlador extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String op;
        op=(request.getParameter("op")!= null) ? request.getParameter("op") : "list";
        ArrayList<Blog> lista= new ArrayList<>();
        ConexionBD canal = new ConexionBD();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;
        if (op.equals("list")) {
            try {
                String sql= "SELECT * FROM blog";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                   Blog lib = new Blog();
                   lib.setId(rs.getInt("id"));
                   lib.setFecha(rs.getString("fecha"));
                   lib.setTitulo(rs.getString("titulo"));
                   lib.setContenido(rs.getString("contenido"));
                   lib.setAutor(rs.getString("autor"));
                   lista.add(lib);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("blog.jsp").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Error en SQL:"+e.getMessage());
            } finally{
                canal.desconectar();
            }
        }
        if (op.equals("nuevo")) {
           Blog l = new Blog();
           String autor=request.getParameter("autor");
           l.setAutor(autor);
            request.setAttribute("libro", l);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
         if (op.equals("editar")) {
            int id= Integer.parseInt(request.getParameter("id"));
            try {
                String sql ="select * from blog where id= ?";
                ps= conn.prepareStatement(sql);
                ps.setInt(1, id);
                
                 rs = ps.executeQuery();
                 Blog li = new Blog();
                 while (rs.next()) {
                   Blog lib = new Blog();
                   li.setId(rs.getInt("id"));
                   li.setFecha(rs.getString("fecha"));
                   li.setTitulo(rs.getString("titulo"));
                   li.setContenido(rs.getString("contenido"));
                   li.setAutor(rs.getString("autor"));
                   
                }
                 request.setAttribute("libro", li);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Error en SQL : "+ e.getMessage());
            } 
        }
    
        if (op.equals("eliminar")) {
            int id= Integer.parseInt(request.getParameter("id"));
            try {
                String sql ="delete from blog where id= ?";
                ps= conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                
            } catch (SQLException e) {
                System.out.println("Error en SQL : "+ e.getMessage());
            } finally {
                canal.desconectar();
            }
            response.sendRedirect("MainControlador");
            
        }
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
       String fecha = request.getParameter("fecha");
       String titulo = request.getParameter("titulo");
       String contenido = request.getParameter("contenido");
       String autor = request.getParameter("autor");
       Blog l = new  Blog();
       l.setId(id);
       l.setFecha(fecha);
       l.setTitulo(titulo);
       l.setContenido(contenido);
       l.setAutor(autor);
       
       ConexionBD canal = new ConexionBD();
       Connection conn = canal.conectar();
       PreparedStatement ps;
       ResultSet rs;
        if (id==0) {
            try {
                String sql = "insert into blog (fecha,titulo,contenido,autor) values(?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getFecha());
                ps.setString(2, l.getTitulo());
                ps.setString(3, l.getContenido());
                ps.setString(4, l.getAutor());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error en SQL:" +e.getMessage());
            } finally {
              canal.desconectar();
            }
            
        }else{
             try {
                String sql = "update blog set  fecha= ?,titulo=? , contenido= ?,autor=? where id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, l.getFecha());
                ps.setString(2, l.getTitulo());
                ps.setString(3, l.getContenido());
                ps.setString(4, l.getAutor());
                ps.setInt(5, l.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error en SQL:" +e.getMessage());
            } finally {
              canal.desconectar();
            }
        }
        response.sendRedirect("MainControlador");
    }
 
}
