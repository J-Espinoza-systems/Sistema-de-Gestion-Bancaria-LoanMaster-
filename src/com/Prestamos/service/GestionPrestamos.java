package com.Prestamos.service;

import com.Prestamos.model.Cliente.Cliente;
import com.Prestamos.model.Prestamos.Prestamo;
import com.Prestamos.model.Prestamos.PrestamoEmpresarial;
import com.Prestamos.model.Prestamos.PrestamoPersonal;

import java.util.ArrayList;

public class GestionPrestamos {
    private ArrayList<Prestamo> listarPrestamos;

    public GestionPrestamos(){
        this.listarPrestamos=new ArrayList<>();

    }

    private boolean validarPrestamo(String dni){
        int deudaActiva= 0;
        for (Prestamo p: listarPrestamos){
            if (p.getClientePrestamo().getDni().equals(dni)){
                if (p.isEstado()) {
                    deudaActiva++;
                }
            }
            if (deudaActiva>=2){
                System.err.println("Cliente excede el número de Deudas activas");
                return true;
            }
        }
        System.out.println("Cliente califica para Prestamo");
        return false;
    }

    private boolean validarMonto(Prestamo p) {
        if (p instanceof PrestamoEmpresarial && p.getMonto() < 5000) {
            System.err.println("Monto insuficiente para Crédito Empresarial (Mínimo S/ 5,000)");
            return false;
        } else if (p instanceof PrestamoPersonal && p.getMonto() < 500) {
            System.err.println("Monto insuficiente para Crédito Personal(Minimo S/500)");
            return false;
        } else if (p instanceof PrestamoPersonal && p.getMonto()>1000000){
            System.err.println("Monto excede para ser un Crédito Personal(maximo S/1,000,000)");
            return false;
        }else {
            System.out.println("Monto verificado");
            return true;
        }

    }

    public void RegistrarPrestamo(Prestamo p){
        if (validarPrestamo(p.getClientePrestamo().getDni())){
            return;
        }
        if (!validarMonto(p)) {
            return;
        }
      for (Prestamo pres: listarPrestamos){
          if (pres.getIdPrestamo()==p.getIdPrestamo()){
              System.out.println("Prestamo ya existe en el sistema");
              return;
          }
      }

          this.listarPrestamos.add(p);
        System.out.println("Prestamo registrado con exito!!!");
    }

    public void MostrarPrestamo(){
        if (listarPrestamos.isEmpty()){
            System.out.println("No hay ningun Prestamo registado.");
            return;
        }
        System.out.println("=====LISTA PRESTAMOS=====");
        for (Prestamo p: listarPrestamos){
            if (p.isEstado()){
            p.mostrarPrestamo();
            System.out.println("");
            }
        }
        System.out.println("=========================");
    }

    public void mostrarPrestamoPersonal(){
        if (listarPrestamos.isEmpty()){
            System.err.println("No hay ningun Prestamo registado.");
            return;
        }
        boolean existePrestamo=false;
        System.out.println("=====PRESTAMOS PERSONALES=====");
        for (Prestamo p: listarPrestamos){
            if (p instanceof PrestamoPersonal && p.isEstado()){
                existePrestamo=true;
                    p.mostrarPrestamo();
                    System.out.println("");

            }
        }
        if (!existePrestamo){
            System.err.println("No hay ningun prestamo personal Registrado");
        }
        System.out.println("=========================");
    }

    public void mostrarPrestamoEmpresarial(){
        if (listarPrestamos.isEmpty()){
            System.err.println("No hay ningun Prestamo Empresarial registado.");
            return;
        }
        boolean existePrestamo=false;
        System.out.println("=====PRESTAMOS EMPRESARIAL=====");
        for (Prestamo p: listarPrestamos){
            if (p instanceof PrestamoEmpresarial && p.isEstado()){
                    p.mostrarPrestamo();
                    System.out.println("");
                    existePrestamo=true;

            }
        }
        if (!existePrestamo){
            System.err.println("No hay prestamos Empresariales registrados en el Sistema");
        }
        System.out.println("=========================");
    }

    public Cliente obtenerCliente(String dni){
        for (Prestamo p: listarPrestamos){
            if (p.getClientePrestamo().getDni().equals(dni)){
                return p.getClientePrestamo();
            }
        }
        System.err.println("Error: El cliente con DNI/RUC " + dni + " no existe en el sistema.");
        return null;
    }

    public void buscarPorDocumento(String documento){
        boolean encontrado = false;
        for(Prestamo p : listarPrestamos){
            if (documento.trim().equalsIgnoreCase(p.getClientePrestamo().getDni())){
                encontrado=true;
                if (!p.isEstado()){
                    System.out.printf("%nPrestamo de %s encontrado ESTADO: ANULADO%n",p.getClientePrestamo().getDni());
                }else {
                System.out.printf("%nPrestamo de %s encontrado!!%n",p.getClientePrestamo().getDni());
                }
                p.mostrarPrestamo();
            }
        }
        if (!encontrado){
            System.err.printf("""
            No se encontro el prestamo de %s
            """,documento);
        }else {
            System.out.println("Fin de busqueda");
        }
    }

    public void desactivarPrestamo(int id){
        boolean encontrado= false;
        for (Prestamo p: listarPrestamos){
            if (p.getIdPrestamo()==id){
                encontrado=true;
                if (!p.isEstado()){
                    System.out.println("No se puedo deshabilitar el prestamo (Ya esta deshabilitado)");
                    return;
                }
                p.setEstado(false);
                System.out.println("Deshabilitado con exito");
                return;
            }
        }
        if (!encontrado){
            System.err.printf("No se pudo deshabilitar el prestamo (ID %d no encontrado)",id);
        }
    }

    public void activarPrestamo(int id){
        boolean encontrado= false;
        for (Prestamo p: listarPrestamos){
            if (p.getIdPrestamo()==id){
                encontrado=true;
                if (p.isEstado()){
                    System.err.printf("""
                            Este prestamo del Cliente %s %s ya se encuentra Habilitado""",p.getClientePrestamo().getDni(), p.getClientePrestamo().getNombre());
                            return;
                }
                p.setEstado(true);
                System.out.println("Prestamo habilitado con exito");
                return;
            }

        }
        if (!encontrado){
            System.err.printf("No se pudo Habilitar el prestamo (ID %d no encontrado)",id);
        }
    }

    public void resumenFinanciero(){
        int presAnulados=0;
        int presActivos=0;
        double totalGeneral=0;
        double ingresoMensual=0;
        double presPersonal=0;
        double presEmpresarial=0;
        double interesTotal =0;
        for (Prestamo p : listarPrestamos) {
            if (p.isEstado()) {
                presActivos++;
                totalGeneral+=p.getMonto();
                interesTotal +=((p.getMonto() * p.getTasaInteres()) / 100);
                ingresoMensual+= p.calcularCuotaMensual();
                if (p instanceof PrestamoEmpresarial){
                    presEmpresarial+=p.getMonto();
                } else if (p instanceof PrestamoPersonal) {
                    presPersonal+=p.getMonto();
                }
            }else {
                presAnulados++;
            }
        }
        System.out.printf("""
                ====================================================
                          RESUMEN DE CARTERA BANCARIA
                ====================================================
                -------------------------------
                Prestamos Activos     : %d
                Prestamos Anulados    : %d
                -------------------------------
                Capital Total         : S/%,.2f
                Interes Total a cobrar: S/%,.2f
                Ingreso Mensual       : S/%,.2f
                -------------------------------
                Credito Personal      : S/%,.2f
                Credito Empresarial   : S/%,.2f
                -------------------------------
                """,presActivos,presAnulados,totalGeneral,
                interesTotal, ingresoMensual,presPersonal,
                presEmpresarial);
    }

}
