# 🏦 Sistema de Gestión Bancaria - LoanMaster

**LoanMaster** es una aplicación de consola robusta desarrollada en **Java**, diseñada para la gestión, validación y control de riesgos en la emisión de préstamos bancarios. El sistema integra reglas de negocio financieras reales para optimizar la toma de decisiones y asegurar la integridad de la cartera crediticia.

---

##  Funcionalidades Principales

* ** Identificación de Clientes:** Soporte dinámico para Personas Naturales (DNI) y Personas Jurídicas (RUC) mediante una jerarquía de clases heredada de la clase `Cliente`.
* ** Control de Riesgo Crediticio:** Validación automática que impide el registro si el cliente ya posee 2 deudas activas, mitigando el riesgo de sobreendeudamiento.
* ** Validación de Montos por Perfil:**
    * **Crédito Personal:** Rango desde S/ 500.00 hasta S/ 1,000,000.00.
    * **Crédito Empresarial:** Monto mínimo de S/ 5,000.00.
* ** Resumen de Cartera:** Generación de reportes consolidados que calculan el Capital Total, Intereses por cobrar e Ingresos Mensuales proyectados.
* ** Gestión de Estados:** Capacidad para anular (desactivar) o reactivar préstamos, actualizando los indicadores financieros en tiempo real.
* ** Resiliencia del Sistema:** Implementación de bloques `try-catch` para asegurar la estabilidad ante entradas de datos inválidas.

---

##  Estructura Técnica y Tecnologías

El software ha sido construido bajo los principios de la **Programación Orientada a Objetos (POO)**, facilitando su escalabilidad hacia frameworks como Spring Boot.

* **Lenguaje:** Java 24
* **Paradigma:** Uso intensivo de Herencia, Polimorfismo y Encapsulamiento.
* **Arquitectura de Capas:**
    * `Model`: Definición de entidades y jerarquía de herencia (`Cliente` y `Prestamo`).
    * `Service`: Gestión de lógica de negocio y validaciones de seguridad.
    * `App`: Controlador de interfaz de usuario y manejo de flujo de datos.

---

##  Cómo ejecutar el proyecto

1. Clona este repositorio:
   ```bash
   git clone [https://github.com/TU_USUARIO/LoanMaster.git](https://github.com/TU_USUARIO/LoanMaster.git)
