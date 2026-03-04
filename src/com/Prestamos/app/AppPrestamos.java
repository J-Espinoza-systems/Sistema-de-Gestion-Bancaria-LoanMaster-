package com.Prestamos.app;

import com.Prestamos.model.Cliente.Cliente;
import com.Prestamos.model.Cliente.PersonaJuridica;
import com.Prestamos.model.Cliente.PersonaNatural;
import com.Prestamos.model.Prestamos.Prestamo;
import com.Prestamos.model.Prestamos.PrestamoEmpresarial;
import com.Prestamos.model.Prestamos.PrestamoPersonal;
import com.Prestamos.service.GestionPrestamos;

import java.util.Scanner;

public class AppPrestamos {
    public static void main(String[] args) {
        var s= new Scanner(System.in);

        Cliente c1 = new PersonaNatural("60549521", "Jordy Felipe", "Espinoza", "jordy@mail.com", 15000.0);
        Cliente c2 = new PersonaJuridica("20605495213", "Sandy S.A.C", "sandy@mail.com", "Sandy Maria", 2000000);
        Cliente c3 = new PersonaNatural("12345678", "Carlos", "Pérez", "carlos@mail.com", 5000.0);
        Cliente c4 = new PersonaJuridica("20123456789", "Tech Solutions Peru", "contacto@tech.pe", "Luis Arrieta", 5000000);


        Prestamo p1 = new PrestamoPersonal(c1, 5000.0, 15.0, 12);
        Prestamo p2 = new PrestamoEmpresarial(c2, 500000.0, 10.0, 24);
        Prestamo p3 = new PrestamoPersonal(c3, 2500.0, 20.0, 6);
        Prestamo p4 = new PrestamoEmpresarial(c4, 1100000.0, 8.0, 48);

        GestionPrestamos LoanMaster = new GestionPrestamos();
        LoanMaster.RegistrarPrestamo(p1);
        LoanMaster.RegistrarPrestamo(p2);
        LoanMaster.RegistrarPrestamo(p3);
        LoanMaster.RegistrarPrestamo(p4);


        boolean salir = false;
        do {
            try{
            System.out.println("""
                    ====================================================
                              SISTEMA BANCARIO LOANMASTER
                    ====================================================
                    1. Registrar Nuevo Préstamo
                    2. Mostrar Préstamos (General)
                    3. Listar Préstamos Personales
                    4. Listar Préstamos Empresariales
                    5. Buscar Préstamo por DNI/RUC
                    6. Anular un Préstamo (Baja)
                    7. Reactivar un Préstamo (Alta)
                    8. Ver Resumen Financiero Consolidado
                    0. Salir del Sistema
                    ====================================================
                    """);
            System.out.println("Seleccione una opción: ");
            int opcion = Integer.parseInt(s.nextLine());

            switch (opcion) {
                case 1 -> {
                    Cliente cliente= null;
                    try {
                        System.out.println("""
                                   Selección Cliente
                                1- Cliente Existente.
                                2- Cliente Nuevo.
                                """);
                        int elejirCliente = Integer.parseInt(s.nextLine());
                        if (elejirCliente == 1) {
                            System.out.println("Ingresar DNI/RUC del Cliente existente:");
                            String documento = s.nextLine();
                            cliente = LoanMaster.obtenerCliente(documento);
                        } else if (elejirCliente == 2) {
                            System.out.println("""
                                      Tipo de Cliente
                                    1- Persona Natural.
                                    2- Persona Juridica.
                                    """);
                            int tipoCliente = Integer.parseInt(s.nextLine());
                            System.out.print("Nombre/Razón Social: ");
                            String nom = s.nextLine();
                            System.out.print("DNI/RUC: ");
                            String doc = s.nextLine();
                            System.out.print("Correo:");
                            String correo = s.nextLine();
                            System.out.print("Sueldo Neto/Utilidad Anual:");
                            double ingresos = Double.parseDouble(s.nextLine());

                            if (tipoCliente == 1) {
                                System.out.print("Apellido: ");
                                String apellido = s.nextLine();
                                cliente = new PersonaNatural(doc, nom, apellido, correo, ingresos);
                            } else if (tipoCliente == 2) {
                                System.out.print("Representante Legal: ");
                                String repLegal = s.nextLine();
                                cliente = new PersonaJuridica(doc, nom, correo, repLegal, ingresos);
                            } else {
                                System.err.println("Opcion invalida, elija 1 o 2");
                            }

                        } else {
                            System.err.println("Opcion invalida, elija 1 o 2.");
                        }

                        if (cliente != null) {
                            Prestamo nuevop = null;
                            System.out.print("Monto :");
                            Double monto = Double.parseDouble(s.nextLine());
                            System.out.print("Taza Interes: ");
                            Double interes = Double.parseDouble(s.nextLine());
                            System.out.print("Plazo Meses: ");
                            int plazo = Integer.parseInt(s.nextLine());
                            if (cliente instanceof PersonaJuridica) {
                                nuevop = new PrestamoEmpresarial(cliente, monto, interes, plazo);
                            } else {
                                nuevop = new PrestamoPersonal(cliente, monto, interes, plazo);
                            }
                            LoanMaster.RegistrarPrestamo(nuevop);
                        }
                    }catch (NumberFormatException e){
                        System.err.println("Error en el registro: Se esperaba un valor numérico (monto, tasa o plazo).");
                    }

                }
                case 2 ->{
                    LoanMaster.MostrarPrestamo();
                }
                case 3 -> {
                    LoanMaster.mostrarPrestamoPersonal();
                }
                case 4->{
                    LoanMaster.mostrarPrestamoEmpresarial();
                }
                case 5->{
                    System.out.println("Ingresar DNI/RUC para busqueda: ");
                    String documento= s.nextLine();
                    LoanMaster.buscarPorDocumento(documento);
                }
                case 6->{
                    try {
                        System.out.println("Ingresar ID del Prestamo a Deshabilitar: ");
                        int idPrestamo = Integer.parseInt(s.nextLine());
                        LoanMaster.desactivarPrestamo(idPrestamo);
                    }catch (NumberFormatException e){
                        System.err.println("Error: El ID debe ser un número entero.");
                    }
                }
                case 7 ->{
                    try {
                    System.out.println("Ingresar ID del Prestamo Habilitar: ");
                    int idPrestamo= Integer.parseInt(s.nextLine());
                    LoanMaster.activarPrestamo(idPrestamo);
                }catch (NumberFormatException e){
                        System.err.println("Error: El ID debe ser un número entero.");
                    }
                }

                case 8->{
                    LoanMaster.resumenFinanciero();
                }
                case 0->{
                    salir=true;
                    System.out.printf("""
                Saliendo del sistema...
                Sistema LoanMaster te espera pronto!!!:)
                """);
                }
            }
        }catch (NumberFormatException e){
            System.err.println("Error: Por favor, seleccione una opción numérica del menú.");
        }

        }while (!salir) ;



    }



}
