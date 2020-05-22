
package com.emergentes.modelo;

import java.sql.Date;

public class Blog {
     private int id;
    private String fecha;
    private String titulo;
    private String contenido;
    private String autor;
      
    public Blog() {
        this.id = 0;
        this.fecha =null;
        this.titulo ="";
        this.contenido = "";
        this.autor = "";
    }

    public String getAutor() {
        return autor;
    }

    
    
    
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
 
    
    
}
