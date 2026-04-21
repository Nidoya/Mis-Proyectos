// --- CLASE TAREA: Define la estructura de cada objeto tarea ---
class Tarea {
    // ATRIBUTOS PRIVADOS: Solo accesibles dentro de esta clase (Encapsulamiento)
    private String descripcion;
    private String fechaLimite;
    private int prioridad;
    private boolean terminado; 

    // CONSTRUCTOR: Inicializa la tarea con los datos que nos da el usuario
    public Tarea(String descripcion, String fechaLimite, int prioridad) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        // Usamos el Setter para validar la prioridad desde el primer segundo
        this.setPrioridad(prioridad);
        this.terminado = false; // Por defecto, nace sin terminar
    }

    // GETTERS: Ventanillas de salida para poder leer los datos desde fuera
    public String getDescripcion(){ return descripcion; }
    public String getFechaLimite() { return fechaLimite; }
    public int getPrioridad() { return prioridad; }

    // SETTER DE PRIORIDAD: Filtro de seguridad para que el dato siempre sea correcto
    public void setPrioridad(int priorid) {
        // Si el número es válido (1-5), se guarda. Si no, se fuerza a 1.
        if(priorid >= 1 && priorid <= 5){ 
            this.prioridad = priorid;
        } else { 
            this.prioridad = 1; 
        }
    }

    // MÉTODOS PARA EL ESTADO: 'is' para consultar y 'set' para cambiarlo
    public boolean isTerminado() { return terminado; }
    public void setTerminado(boolean terminad) { this.terminado = terminad; }
}