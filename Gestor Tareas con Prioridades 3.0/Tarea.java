// --- CLASE TAREA: El molde que define cómo es cada tarea ---
class Tarea {
    // Ponemos 'private' para que los datos estén "bajo llave" y protegidos
    private String descripcion;
    private String fechaLimite;
    private int prioridad;
    private boolean terminado; 

    // CONSTRUCTOR: Se activa cuando pones "new Tarea(...)". Rellena los datos al nacer.
    public Tarea(String descripcion, String fechaLimite, int prioridad) {
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        // Usamos el 'Setter' aquí para que ya desde el principio se compruebe el 1-5
        this.setPrioridad(prioridad);
        this.terminado = false; 
    }

    // GETTERS: Son las "ventanillas" para LEER los datos privados
    public String getDescripcion(){ return descripcion; }
    public String getFechaLimite() { return fechaLimite; }
    public int getPrioridad() { return prioridad; }

    // SETTER DE PRIORIDAD: La "ventanilla" para CAMBIAR el dato con seguridad
    public void setPrioridad(int priorid) {
        // REGLA: Si meten algo que no sea 1-5, le ponemos 1 por defecto
        if(priorid >= 1 && priorid <= 5){ 
            this.prioridad = priorid;
        } else { 
            this.prioridad = 1; 
        }
    }

    // IS y SET para el estado TERMINADO (preguntar y mandar)
    public boolean isTerminado() { return terminado; }
    public void setTerminado(boolean terminad) { this.terminado = terminad; }
}
