package com.Prestamos.model.Prestamos;

import com.Prestamos.model.Cliente.Cliente;


public class PrestamoPersonal extends Prestamo{
    public PrestamoPersonal(Cliente c, double monto, double tazaInteres, int plazoMeses){
        super(c,monto,tazaInteres,plazoMeses);

    }


    @Override
    public double calcularCuotaMensual(){
        double interesesTotales= getMonto()*(getTasaInteres()/100);
        return (getMonto()+interesesTotales)/getPlazoMeses();
    }

}
