package model.Personal;

import excepciones.ActividadRepetida;
import excepciones.ClienteExistenteException;
import model.ActivYrutina.Actividad;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.Otros.Factura;
import model.Otros.Gimnasio;
import model.Persona.Cliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Usuario extends Personal{
    private String usuario;
    private String contrasenia;
    public Usuario()
    {
        usuario=" ";
        contrasenia=" ";
    }

    public Usuario(String nombre, String dni, String telefono, ArrayList<Apercibimiento> listaApercibimientos, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social, boolean alta_medica, LocalDate fecha_nacimiento, String comentario, String CUIL, String usuario, String contrasenia) {
        super(nombre, dni, telefono, listaApercibimientos, estado, grupo_sanguineo, contacto_emergencia, obra_social, alta_medica, fecha_nacimiento, comentario, CUIL);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Usuario(String user, String contra) {
        usuario=user;
        contrasenia=contra;
    }

    public boolean verificarContraseniaYusuario(String user, String contra)
    {
        return (usuario.equals(user) && contrasenia.equals(contra));
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario personal = (Usuario) o;
        return Objects.equals(usuario, personal.usuario) && Objects.equals(contrasenia, personal.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, contrasenia);
    }

    public boolean cambiarContrasenia(String viejaContrasenia, String nuevaContrasenia)
    {
        boolean rta=false;
        if (viejaContrasenia.equals(contrasenia))
        {
            contrasenia=nuevaContrasenia;
            rta=true;
        }
        return rta;
    }


    public double calcularCuota (HashSet<Actividad> setActividades) {
        double cuota = 0;
        int cantidad_actividades = 0;

        Iterator<Actividad> it = setActividades.iterator();

        while (it.hasNext()) {
            cantidad_actividades++;
            cuota += it.next().getPrecio_mensual();
        }

        if (cantidad_actividades == 1) {
            cuota = cuota * 0.95;
        } else if (cantidad_actividades == 2) {
            cuota = cuota * 0.90;
        } else if (cantidad_actividades >= 3) {
            cuota = cuota * 0.8;
        }
        return cuota;
    }

    public Factura cobrarCuota(Cliente cliente){
        LocalDate mes=null;
        LocalDate anio=null;

        double cuota = calcularCuota(cliente.getHashDeActividades());
        String dato_cliente = cliente.getNombre() + "\n" + cliente.getDni() + "\n" + cliente.getTelefono();
        Factura factura = new Factura(mes.getMonthValue(),anio.getYear(), dato_cliente, LocalDate.now(), cuota);
        cliente.agregarFactura(factura);

        return factura;
    }

    public boolean agregarCliente(Gimnasio gimnasio,Cliente cliente) throws ClienteExistenteException {
        boolean rta= gimnasio.agregarCliente(cliente);
        return rta;
    }

    public boolean agregarActividad(Actividad actividad,Gimnasio gimnasio) throws ActividadRepetida {
        boolean rta=gimnasio.agregarActividad(actividad);
        return rta;
    }

}