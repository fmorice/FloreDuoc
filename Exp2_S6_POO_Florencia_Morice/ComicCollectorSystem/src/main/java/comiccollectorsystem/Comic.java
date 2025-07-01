package comiccollectorsystem;

import java.util.Objects;

public class Comic {
    private String codigo;
    private String titulo;
    private String autor;
    private boolean prestado = false;

    public Comic(String codigo, String titulo, String autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean estaPrestado() {
        return prestado;
    }

    // Setters
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // Métodos para préstamo
    public void prestar() {
        this.prestado = true;
    }

    public void devolver() {
        this.prestado = false;
    }

    // equals y hashCode basados en título + autor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comic)) return false;
        Comic comic = (Comic) o;
        return titulo.equalsIgnoreCase(comic.titulo) &&
               autor.equalsIgnoreCase(comic.autor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo.toLowerCase(), autor.toLowerCase());
    }

    @Override
    public String toString() {
        String estado = prestado ? "📕 Prestado" : "📗 Disponible";
        return "[" + codigo + "] " + titulo + " - " + autor + " (" + estado + ")";
    }
}