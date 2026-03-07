# 💱 Conversor de Monedas - Java Challenge

¡Bienvenido al Conversor de Monedas! Este proyecto es una aplicación de consola desarrollada en Java que permite realizar conversiones de divisas en tiempo real utilizando una API externa.

## 🚀 Características

* **Conversiones en tiempo real:** Utiliza la [ExchangeRate-API](https://www.exchangerate-api.com/) para obtener las tasas más actualizadas.
* **Soporte para múltiples monedas:** Incluye conversiones entre Dólares (USD), Pesos Argentinos (ARS), Reales (BRL) y Pesos Colombianos (COP).
* **Historial de Consultas:** Registra cada operación realizada con su respectiva fecha y hora.
* **Interfaz de Usuario Robusta:** Validación de entradas para evitar errores de ejecución (letras o números fuera de rango).

## 🛠️ Tecnologías Utilizadas

* **Java JDK 17+** (u otra versión compatible con Java 11+).
* **HttpClient:** Para el consumo de la API REST.
* **Gson (Google):** Para el mapeo y procesamiento de datos JSON.
* **Record de Java:** Para una gestión de modelos de datos limpia y eficiente.

## 📁 Estructura del Proyecto

El código está organizado siguiendo principios de Programación Orientada a Objetos:

- `com.martinf.conversor.principal`: Clase `Main` y lógica del menú interactivo.
- `com.martinf.conversor.conexion`: Lógica de conexión y peticiones HTTP.
- `com.martinf.conversor.modelos`: Definición del `Record` para el mapeo del JSON.

## ⚙️ Configuración

1. Clona este repositorio:
   ```bash
   git clone [https://github.com/malaspina-martin/conversor-monedas-java.git]
2. Importa el proyecto en tu IDE favorito (IntelliJ IDEA recomendado).

3. Asegúrate de agregar el archivo JAR de Gson a las librerías del proyecto.

4. Obtén tu propia API Key en ExchangeRate-API.

5. Reemplaza "TU-API-KEY" en la clase ConsultaAPI por tu clave personal.

##
Desarrollado por Martín - Estudiante de la Universidad Nacional del Sur (UNS) 🇦🇷
