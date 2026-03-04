package com.Prestamos.model.Prestamos;

import com.Prestamos.model.Cliente.Cliente;
import com.Prestamos.model.Cliente.PersonaJuridica;

import java.time.LocalDate;

public abstract class Prestamo {
    private static int contadorID;
    private int idPrestamo;
    private Cliente clientePrestamo;
    private double monto;
    private double tasaInteres;
    private int plazoMeses;
    private  boolean estado;
    private LocalDate fechaEmision;

    public Prestamo(Cliente c, double monto, double tazaInteres, int plazoMeses){
        this.idPrestamo= ++contadorID;
        this.clientePrestamo= c;
        this.monto= monto;
        this.tasaInteres = tazaInteres;
        this.plazoMeses= plazoMeses;
        this.estado=true;
        this.fechaEmision= LocalDate.now();
    }

    public abstract double calcularCuotaMensual();

    public Cliente getClientePrestamo(){
        return  clientePrestamo;
    }

    public LocalDate getFechaVencimiento() {
        return this.fechaEmision.plusMonths(this.plazoMeses);
    }

    public void mostrarPrestamo(){
        String documento="";
        String titular="";
        String tipoCredito = "";

        String estadoHabilitado= estado? "ACTIVO":"PENDIENTE";

        if ((getClientePrestamo().getDni().length()==8)) {
            documento =String.format ("%-14s","DNI");
            titular = String.format("%-14s","Nombre");
            tipoCredito= "Personal";
        }else if(getClientePrestamo().getDni().length()==11){
            if (getClientePrestamo().getDni().startsWith("20")){
                documento= String.format("%-14s","RUC (Empresa)");
                titular=String.format("%-14s","Razón Social");
                tipoCredito="Empresarial";
            }
        }else {
            documento= String.format("%-14s","Ruc");
            titular=String.format("%-14s","Contribuyente");
            tipoCredito= "Personal";
        }

        System.out.printf("""
                *************************
                ID             : %d
                Monto          : S/%,.2f
                Intereses      : %.0f%%
                Plazo          : %d Meses.
                Fecha Emision  : %s
                *************************
                Informacion del Titular
                *************************
                Credito        : %s
                %s : %s
                %s : %s
                CUOTA          : S/%,.2f Mensual. 
                ESTADO         : %s
                *************************
                """,this.idPrestamo, this.monto,this.tasaInteres,this.plazoMeses,this.fechaEmision,
                tipoCredito,
                documento,
                getClientePrestamo().getDni(),
                titular,
                getClientePrestamo().getNombre(),
                calcularCuotaMensual(),
                estadoHabilitado);


    }



    public int getIdPrestamo() {
        return idPrestamo;
    }


    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
