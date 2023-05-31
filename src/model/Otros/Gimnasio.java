package model.Otros;

import model.ActivYrutina.Actividad;
import model.Persona.Cliente;
import model.Persona.Instructor;
import model.Personal.Administrador;
import model.Personal.Encargado;
import model.Personal.Personal;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;


public class Gimnasio {
    private String responsable;
    private String direccion;
    private Conjunto<String,Cliente> mapaCliente;
    private Conjunto<String, Instructor> mapaInstructor;
    private ArrayList<Factura>listaFacturas;

    private TreeSet<Actividad>arbolActividades;

    private final String usuario_admin="admin2023";
    private final String usuario_encargado="encargado2023";
    private final String contrasenia_admin="admin123";
    private final String contrasenia_encargado="encargado123";
    public Gimnasio(String responsable, String direccion) {
        this.responsable = responsable;
        this.direccion = direccion;
        mapaCliente = new Conjunto<>();
        mapaInstructor = new Conjunto<>();
        listaFacturas = new ArrayList<>();
        arbolActividades=new TreeSet<>();
    }

    public Gimnasio()
    {
        responsable=" ";
        direccion=" ";
        mapaCliente=new Conjunto<>();
        mapaInstructor=new Conjunto<>();
        listaFacturas=new ArrayList<>();
        arbolActividades=new TreeSet<>();
    }

    public String getResponsable() {
        return responsable;
    }

    public String getDireccion() {
        return direccion;
    }

    public Personal DamePersonal (String usuario, String contrasenia){
        Administrador a=null;
        Encargado e=null;
        Personal p= null;
        if (usuario.equals(usuario_admin) && contrasenia.equals(contrasenia_admin))
        {
            a = new Administrador(usuario,contrasenia);
            p = a;
        }
        else if (usuario.equals(usuario_encargado) && contrasenia.equals(contrasenia_encargado)) {
            e = new Encargado(usuario, contrasenia);
            p = e;
        }
        return p;
    }

    public boolean agregarFactura(Factura factura){
        listaFacturas.add(factura);
        return true;
    }


    @Override
    public String toString() {
        return "Gimnasio{" +
                "responsable='" + responsable + '\'' +
                ", direccion='" + direccion + '\'' +
                ", mapaCliente=" + mapaCliente +
                ", mapaInstructor=" + mapaInstructor +
                ", listaFacturas=" + listaFacturas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gimnasio gimnasio = (Gimnasio) o;
        return Objects.equals(responsable, gimnasio.responsable) && Objects.equals(direccion, gimnasio.direccion) && Objects.equals(mapaCliente, gimnasio.mapaCliente) && Objects.equals(mapaInstructor, gimnasio.mapaInstructor) && Objects.equals(listaFacturas, gimnasio.listaFacturas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responsable, direccion, mapaCliente, mapaInstructor, listaFacturas);
    }
}
