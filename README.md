# Conversor de Monedas – Java (API ExchangeRate)

Este es un proyecto en **Java** que permite convertir distintas monedas en tiempo real utilizando la API gratuita de **ExchangeRate API**.  
El usuario selecciona un tipo de conversión, ingresa un monto y la aplicación devuelve el resultado junto con la tasa aplicada.

---

## Características

- Conversión en tiempo real usando HTTP requests  
- Integración con API ExchangeRate  
- Manejo de errores y validaciones  
- Menú interactivo por consola  
- Código organizado en clases (`Principal`, `ConsultaTasa`, `ResultadoConversion`)  
- Uso de `record` para almacenar el resultado de manera simple y profesional  

---

## Tecnologías usadas

- **Java 17+**
- **HttpClient** (para solicitudes HTTP)
- **Gson** (para procesar JSON)
- **API ExchangeRate**

---

## Estructura del proyecto

```
/src
 ─ Principal.java
 ─ ConsultaTasa.java
 ─ ResultadoConversion.java
```

---

## Configuración de la API

Este proyecto usa la API de:

=> https://www.exchangerate-api.com/

Debes generar tu propia API key.  
En el código, la clave se configura en:

```java
private static final String claveApi = "TU_API_KEY_AQUI";
```

Reemplázala con tu clave!.

---

## ¿Cómo funciona?

1. El usuario selecciona un tipo de cambio.  
2. Ingresa un monto.  
3. La clase `ConsultaTasa` hace la solicitud HTTP.  
4. Se recibe un JSON con la tasa (`conversion_rate`).  
5. Se calcula el monto final.  
6. Se devuelve un `ResultadoConversion`.  
7. Se muestra todo en consola.  

---

## Ejecución

Compilar:

```bash
javac Principal.java
```

Ejecutar:

```bash
java Principal
```

---

## Ejemplo de uso

```
============================================
---------CONVERSOR DE MONEDAS ALURA---------
============================================
1) USD -> ARS
2) ARS -> USD
3) USD -> BRL
4) BRL -> USD
5) USD -> COP
6) COP -> USD
7) Salir
Seleccione una opción: 1
Ingrese el monto a convertir: 10

--- RESULTADO ---
Moneda base: USD
Moneda destino: ARS
Tasa: 1032.50
Monto convertido: 10325.0
-------------------
```

---

## Clases del proyecto

### `ConsultaTasa.java`
Realiza la petición HTTP, obtiene la tasa de conversión y calcula el total.

### `ResultadoConversion.java`
Record que encapsula los datos de la conversión.

### `Principal.java`
Contiene el menú y controla la aplicación.

---

## Manejo de errores

- Opciones inválidas  
- Números mal ingresados  
- Clave API incorrecta  
- Fallos de conexión  
- Monedas no soportadas  

---

## Licencia

Uso libre para fines educativos.
