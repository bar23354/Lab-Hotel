/*
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * PROGRAMACION ORIENTADA A OBJETOS
 * ROBERTO BARREDA No. 23354
 */

class Cliente {
    private String nombreCompleto;
    private String CUI;
    private int visitas;
    private String clasificacion;
    private double balance;

    public Cliente(String nombreCompleto, String CUI) {
        this.nombreCompleto = nombreCompleto;
        this.CUI = CUI;
        this.visitas = 0;
        actualizarClasificacion();
        this.balance = 0.0;
    }

    //Getters y setters 
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getCUI() {
        return CUI;
    }

    public int getVisitas() {
        return visitas;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public double getBalance() {
        return balance;
    }

    public void aumentarVisitas(int cantidad) {
        this.visitas += cantidad;
        actualizarClasificacion();
    }

    public void addToBalance(double amount) {
        balance += amount;
    }

    public void subtractFromBalance(double amount) {
        balance -= amount;
    }

    private void actualizarClasificacion() {
        if (visitas < 5) {
            clasificacion = "Regular";
        } else if (visitas < 10) {
            clasificacion = "Frecuente";
        } else {
            clasificacion = "VIP";
        }
    }
}

