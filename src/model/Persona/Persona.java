package model.Persona;
import model.Enum.EGenero;
import model.Enum.EGrupoSanguineo;
import model.Enum.Eestado;
import model.Otros.Apercibimiento;
import model.interfaces.I_toJson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**Clase abstracta padre que engloba a Cliente y Personal. */

public abstract class Persona implements Serializable, I_toJson {
    private String nombre;
    private String dni;
    private EGenero genero;
    private String telefono;
    private String domicilio;
    private String email;

    private ArrayList<Apercibimiento> listaApercibimientos;
    private Eestado estado;
    private EGrupoSanguineo grupo_sanguineo;
    private String contacto_emergencia;
    private String obra_social;
    private LocalDate fecha_nacimiento;
    private String comentario;

    /**Constructor que contiene todos los atributos de una persona.*/
    public Persona(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email, Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,LocalDate fecha_nacimiento,String comentario) {
        this.nombre = nombre;
        this.dni = dni;
        this.genero = genero;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.email = email;
        listaApercibimientos = new ArrayList<>();
        this.estado = estado;
        this.grupo_sanguineo = grupo_sanguineo;
        this.contacto_emergencia = contacto_emergencia;
        this.obra_social = obra_social;
        this.fecha_nacimiento = fecha_nacimiento;
        this.comentario=comentario;
    }
    /**Constructor vacio de la clase.*/
    public Persona ()
    {
        nombre=" ";
        dni=" ";
        genero = EGenero.MASCULINO;/
        telefono=" ";
        domicilio = "";
        email = "";/
        listaApercibimientos=new ArrayList<>();
        estado=Eestado.ACTIVO;
        grupo_sanguineo=EGrupoSanguineo.O_NEGATIVO;
        contacto_emergencia=" ";
        obra_social=" ";
        fecha_nacimiento=null;
        comentario=" ";
    }

    /**Constructor para utilizar en el metodo fromJson
     * @see Cliente#fromJson(JSONObject) para mas detalle de su implementacion.
     * @param jo JSONObject con la informacion de la persona.
     * @throws JSONException si el tipo de dato obtenido en el metodo get no coincide con el solicitado.*/
    public Persona(JSONObject jo) throws JSONException {
        nombre = jo.getString("Nombre");
        dni = jo.getString("DNI");
        telefono = jo.getString("Telefono");
        domicilio = jo.getString("Domicilio");
        genero = EGenero.valueOf(jo.getString("Genero"));
        email = jo.getString("Email");
       // estado = (Eestado) jo.get("Estado");
        estado = Eestado.valueOf(jo.getString("Estado")) ;
        //grupo_sanguineo = (EGrupoSanguineo)
        grupo_sanguineo = EGrupoSanguineo.valueOf(jo.getString("Grupo Sanguineo"));
        contacto_emergencia = jo.getString("Contacto de Emergencia");
        obra_social = jo.getString("Obra Social");
        //fecha_nacimiento = (LocalDate) jo.get("Fecha de Nacimiento");

        String fecha_string = jo.getString("Fecha de Nacimiento");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = LocalDate.parse(fecha_string, formatter);

        fecha_nacimiento = fecha;

        comentario = jo.getString("Comentario");
        listaApercibimientos = new ArrayList<>();
        JSONArray ja = null;
        ja = jo.getJSONArray("Apercibimientos");
        for(int i=0; i< ja.length();i++){
            Apercibimiento apercibimiento = new Apercibimiento();
            apercibimiento = apercibimiento.fromJson(ja.getJSONObject(i));
            listaApercibimientos.add(apercibimiento);
        }
    }
    public String getComentario() {
        return comentario;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDni() {
        return dni;
    }
    public String getTelefono() {
        return telefono;
    }
    public Eestado getEstado() {
        return estado;
    }
    public EGrupoSanguineo getGrupo_sanguineo() {
        return grupo_sanguineo;
    }
    public String getContacto_emergencia() {
        return contacto_emergencia;
    }
    public String getObra_social() {
        return obra_social;
    }
    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public String getDomicilio() {
        return domicilio;
    }
    public EGenero getGenero() {
        return genero;
    }
    public String getEmail() {
        return email;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }
    private void setDni(String dni) {
        this.dni = dni;
    }
    private void setGenero(EGenero genero) {
        this.genero = genero;
    }
    private void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    private void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    private void setEmail(String email) {
        this.email = email;
    }
    public void setEstado(Eestado estado) {
        this.estado = estado;
    }
    private void setGrupo_sanguineo(EGrupoSanguineo grupo_sanguineo) {
        this.grupo_sanguineo = grupo_sanguineo;
    }
    private void setContacto_emergencia(String contacto_emergencia) {
        this.contacto_emergencia = contacto_emergencia;
    }
    private void setObra_social(String obra_social) {
        this.obra_social = obra_social;
    }
    private void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    private void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**Permite la modificacion de los datos de la clase persona.*/
    public void modificar(String nombre, String dni, EGenero genero, String telefono, String domicilio,String email,
    Eestado estado, EGrupoSanguineo grupo_sanguineo, String contacto_emergencia, String obra_social,
    LocalDate fecha_nacimiento,String comentario){

        setNombre(nombre);
        setDni(dni);
        setGenero(genero);
        setTelefono(telefono);
        setDomicilio(domicilio);
        setEmail(email);
        setEstado(estado);
        setGrupo_sanguineo(grupo_sanguineo);
        setContacto_emergencia(contacto_emergencia);
        setObra_social(obra_social);
        setFecha_nacimiento(fecha_nacimiento);
        setComentario(comentario);

    }


    /**Devuelve la informacion de los datos en formato String.
     * @return Informacion del objecto en formato String.*/
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", genero='" + genero +
                ", telefono='" + telefono + '\'' + ", domicilio='" + domicilio + ", email='" + email +
                ", listaApercibimientos=" + listaApercibimientos.toString() +
                ", estado=" + estado +
                ", grupo_sanguineo=" + grupo_sanguineo +
                ", contacto_emergencia='" + contacto_emergencia + '\'' +
                ", obra_social='" + obra_social + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                ", comentario='" + comentario + '\'' +
                '}';
    }


    /**Compara personas por su numero de DNI.
     * @return True si coinciden. False si no lo hacen.*/
    @Override
    public boolean equals(Object o) {
       boolean rta= false;
        if(o!=null){
        if(o instanceof Persona aux){
            if(aux.getDni().equals(getDni())){
                rta = true;
                }
            }
       }
    return rta;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    /**Permite crear un JSONObject a partir de los datos de una persona.
     * @return El JSONObject con los datos de la misma.
     * @throws JSONException si la clave ingresada en el metodo put es nula.*/
    @Override
    public JSONObject toJsonObj() throws JSONException {
        JSONObject jsonObject = new JSONObject();

            jsonObject.put("Nombre", getNombre());
            jsonObject.put("DNI", getDni());
            jsonObject.put("Genero", getGenero().name());
            jsonObject.put("Telefono", getTelefono());
            jsonObject.put("Email", getEmail());
            jsonObject.put("Estado", getEstado().name()); 
            jsonObject.put("Grupo Sanguineo", getGrupo_sanguineo().name());
            jsonObject.put("Contacto de Emergencia", getContacto_emergencia());
            jsonObject.put("Obra Social", getObra_social());
            jsonObject.put("Fecha de Nacimiento", getFecha_nacimiento().toString());
            jsonObject.put("Comentario", getComentario());
            jsonObject.put("Domicilio", getDomicilio());

           JSONArray jsonArray = new JSONArray();

           for(int i=0; i<listaApercibimientos.size();i++){
                JSONObject aux = listaApercibimientos.get(i).toJsonObj();
                jsonArray.put(i, aux);
           }
           jsonObject.put("Apercibimientos", jsonArray);
        return jsonObject;
    }

    /**Devuelve la cantidad de de apercibimientos en la lista de una persona.
     * @return Entero que indica cuantos apercibimientos tiene una persona en su lista de apercibimientos.*/
    public int getCantidadApercibimientos(){
        return listaApercibimientos.size();
    }

    /** Permite mostrar en una cadena de texto la totalidad de apercibimientos de una persona.
     @return Una cadena de texto que describe los apercibimientos.*/

    public String DescripcionApercibimientos(){
        String rta = "";
        for(Apercibimiento apercibimiento: listaApercibimientos){
            rta += apercibimiento.getDescripcion() + "\n";}
        return rta;}


    /**Agrega un apercibimiento a la lista de apercibimientos de la persona invocada.
     * @param descripcion Descripcion del apercibimiento.
     * @param fecha Fecha del dia en que se realiza el apercibimiento.*/
    public void agregarApercibimiento(String descripcion, LocalDate fecha){
        Apercibimiento apercibimiento = new Apercibimiento(descripcion, fecha);
        listaApercibimientos.add(apercibimiento);
    }

}

