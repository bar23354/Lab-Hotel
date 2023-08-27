/*
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * PROGRAMACION ORIENTADA A OBJETOS
 * ROBERTO BARREDA No. 23354
 */

class Cuarto {
    private int numero;
    private int capacidad;
    private double precioNoche;
    private boolean ocupada;
    private Cliente clienteAsignado;
    private String tipo;

    public Cuarto(int numero, int capacidad, double precioNoche, String tipo) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.ocupada = false;
        this.clienteAsignado = null;
        this.tipo = tipo;
    }

    public double calcularMontoPorCheckout(int numNights) {
        // Calcular costo
        double cost = 0.0;
        switch (tipo) {
            case "ESTANDAR":
                cost = 100.0 * numNights;
                break;
            case "DELUXE":
                cost = 150.0 * numNights;
                break;
            case "SUITE":
                cost = 200.0 * numNights;
                break;
            default:
                System.out.println("Error: Tipo de habitación no válido.");
                break;
        }
    
        if (cost > 0) {
            if (clienteAsignado != null) {
                clienteAsignado.subtractFromBalance(cost);
            }
    
            // Abrir espacio
            liberarCuarto();
    
            return cost;
        } else {
            System.out.println("Error en el cálculo del monto de checkout.");
            return 0.0;
        }
    }    

    //Getters y setters 
    public int getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public Cliente getClienteAsignado() {
        return clienteAsignado;
    }

    public void setClienteAsignado(Cliente cliente) {
        this.clienteAsignado = cliente;
        this.ocupada = true;
    }

    public void liberarCuarto() {
        this.clienteAsignado = null;
        this.ocupada = false;
    }

    public String getTipo() {
        return tipo;
    }
}
