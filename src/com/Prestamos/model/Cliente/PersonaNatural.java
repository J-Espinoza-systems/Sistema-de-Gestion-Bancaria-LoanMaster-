package com.Prestamos.model.Cliente;

public class PersonaNatural extends Cliente{
    private String apellido;
    private double sueldoNeto;

    public  PersonaNatural(String dni,String nombre, String apellido, String correo,double sueldoNeto){
        super(dni,nombre,correo);
        this.apellido= apellido;
        this.sueldoNeto= sueldoNeto;
    }
    @Override
    public void mostrarCliente(){
        super.mostrarCliente();
        System.out.printf("""
                Apellido: %s
                Sueldo Neto: %.2f
                """, this.apellido,this.sueldoNeto);
    }

    public double calcularCapacidadPago(){
        return this.sueldoNeto*0.35;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSueldoNeto() {
        return sueldoNeto;
    }

    public void setSueldoNeto(double sueldoNeto) {
        this.sueldoNeto = sueldoNeto;
    }
}
