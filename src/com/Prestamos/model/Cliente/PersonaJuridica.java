package com.Prestamos.model.Cliente;

public class PersonaJuridica extends Cliente{
    private String representanteLegal;
    private double utilidadAnual;

    public PersonaJuridica(String dni,String nombre,String correo,String representanteLegal,double utilidadAnual){
        super(dni,nombre,correo);
        this.representanteLegal= representanteLegal;
        this.utilidadAnual= utilidadAnual;
    }
    public double calcularCapacidadPago(){
        return (utilidadAnual / 12) * 0.40;
    }
    @Override
    public void mostrarCliente(){
        super.mostrarCliente();
        System.out.printf("""
                Representante Legal: %s
                Utilidad Anual: %.2f
                """,this.representanteLegal,this.utilidadAnual);
    }


    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public double getUtilidadAnual() {
        return utilidadAnual;
    }

    public void setUtilidadAnual(double utilidadAnual) {
        this.utilidadAnual = utilidadAnual;
    }
}
