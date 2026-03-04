package com.Prestamos.model.Cliente;

import java.util.Objects;

public abstract class Cliente {
    private String Dni;
    private String nombre;
    private String correo;

    public Cliente(String dni, String nombre, String correo){
        this.Dni=dni;
        this.nombre= nombre;
        this.correo= correo;
    }


    public abstract double calcularCapacidadPago();

    public void mostrarCliente(){
        System.out.printf("""
                ID Fiscal (Dni/Ruc): %s
                Nombre/Razón Social: %s
                Correo: %s
                """,this.Dni,this.nombre,this.correo);
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(Dni, cliente.Dni) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Dni);
    }
}
