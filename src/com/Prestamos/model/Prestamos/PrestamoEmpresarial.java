package com.Prestamos.model.Prestamos;

import com.Prestamos.model.Cliente.Cliente;

public class PrestamoEmpresarial extends Prestamo{
    public  PrestamoEmpresarial(Cliente c,double monto, double tazaInteres, int plazoMeses){
        super(c,monto,tazaInteres,plazoMeses);
    }

    @Override
    public double calcularCuotaMensual(){
        double interesesTotales= getMonto()*(getTasaInteres()/100);
        double cuotaMensual=(getMonto()+interesesTotales)/getPlazoMeses();
        if (getMonto()>=50000){
                cuotaMensual = cuotaMensual *0.90;
        }

        return cuotaMensual;
    }

}
