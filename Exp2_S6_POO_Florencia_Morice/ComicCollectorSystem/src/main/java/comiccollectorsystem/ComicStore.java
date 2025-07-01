package comiccollectorsystem;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ComicStore {

    private TreeSet<Comic> comics = new TreeSet<>(Comparator.comparing(Comic::getTitulo, String.CASE_INSENSITIVE_ORDER));
    private HashMap<String, Usuario> usuarios = new HashMap<>();

    // Agrega un cómic si no está repetido (por título + autor)
    public void agregarComic(Comic c) {
        if (comics.add(c)) {
            System.out.println("✅ Comic agregado: " + c.getTitulo());
        } else {
            System.out.println("⚠️ El comic ya existe (por título y autor).");
        }
    }

    // Registra un usuario si no existe (por ID)
    public void registrarUsuario(Usuario u) {
        String id = u.getId().toLowerCase();
        if (usuarios.containsKey(id)) {
            System.out.println("⚠️ Usuario ya registrado");
        } else {
            usuarios.put(id, u);
            System.out.println("✅ Usuario registrado: " + u.getNombre());
        }
    }

    // Muestra todos los cómics ordenados
    public void mostrarComics() {
        if (comics.isEmpty()) {
            System.out.println("📭 No hay cómics registrados.");
            return;
        }
        System.out.println("\n📚 Lista de Cómics:");
        for (Comic c : comics) {
            System.out.println("• " + c);
        }
    }

    // Muestra todos los usuarios registrados
    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("📭 No hay usuarios registrados.");
            return;
        }
        System.out.println("\n👥 Usuarios registrados:");
        for (Usuario u : usuarios.values()) {
            System.out.println("• " + u);
        }
    }

    // Carga cómics desde un archivo CSV en UTF-8
    public void cargarComicsDesdeCSV(String archivo) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea;
            int contador = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    Comic c = new Comic(datos[0].trim(), datos[1].trim(), datos[2].trim());
                    agregarComic(c);
                    contador++;
                } else {
                    System.out.println("⚠️ Línea con formato incorrecto: " + linea);
                }
            }

            System.out.println("✅ Se cargaron " + contador + " cómics desde el archivo.");

        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo: " + e.getMessage());
        }
    }

    // Guardar usuarios en un archivo de texto en UTF-8
    public void guardarUsuarios(String archivo) {
        try (Writer fw = new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8)) {
            for (Usuario u : usuarios.values()) {
                fw.write(u.toString() + "\n");
            }
            System.out.println("✅ Usuarios guardados en el archivo.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar usuarios: " + e.getMessage());
        }
    }

    // Prestar un cómic por título
    public void prestarComic(String titulo) {
        for (Comic c : comics) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                if (!c.estaPrestado()) {
                    c.prestar();
                    System.out.println("📖 Comic prestado: " + c.getTitulo());
                } else {
                    System.out.println("⚠️ El cómic ya está prestado.");
                }
                return;
            }
        }
        System.out.println("❌ No se encontró un cómic con ese título.");
    }

    // Devolver un cómic por título
    public void devolverComic(String titulo) {
        for (Comic c : comics) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                if (c.estaPrestado()) {
                    c.devolver();
                    System.out.println("✅ Comic devuelto: " + c.getTitulo());
                } else {
                    System.out.println("⚠️ El cómic no estaba prestado.");
                }
                return;
            }
        }
        System.out.println("❌ No se encontró un cómic con ese título.");
    }
}