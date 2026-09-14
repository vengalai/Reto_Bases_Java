# Reto Integrador — Sistema de Gestión de un Torneo Deportivo

## Integrantes

Santiago Morantes Salcedo

## Descripción

Aplicación de consola desarrollada en Java para administrar un torneo deportivo. Permite crear el torneo, registrar equipos, jugadores y entrenadores, programar partidos, registrar resultados, consultar la tabla de posiciones, buscar jugadores y generar un reporte final.

El proyecto aplica operadores, estructuras de decisión y repetitivas, cadenas y conversiones, programación orientada a objetos, encapsulamiento, herencia, polimorfismo, arreglos, colecciones y relaciones entre clases.

## Estructura del proyecto

```text
Reto_Torneo-main/
├── pom.xml
├── README.md
├── IMG/
│   ├── Diagrama_Torneo.png
│   ├── crear_torneo.png
│   ├── registrar_equipo.png
│   ├── registrar_jugador.png
│   ├── reporte_final_torneo.png
│   ├── tabla_posiciones.png
│   └── validacion_equipo_inexistente.png
└── src/main/java/com/mycompany/reto_torneo/
    ├── Reto_Torneo.java
    ├── Persona.java
    ├── Jugador.java
    ├── Entrenador.java
    ├── Posicion.java
    ├── Equipo.java
    ├── Partido.java
    ├── Torneo.java
    └── PosicionEquipo.java
```

## Requisitos para ejecutar

- Java 17 o superior.
- Maven instalado y agregado al PATH.

El proyecto está configurado para compilar con Java 17.

## Ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/usuario/repositorio.git
cd repositorio
```

2. Compilar:

```bash
mvn clean compile
```

3. Ejecutar:

```bash
mvn exec:java
```

También se puede ejecutar `Reto_Torneo.java` directamente desde NetBeans, IntelliJ IDEA o VS Code como proyecto Maven.

## Menú del sistema

1. Crear torneo.
2. Registrar equipo.
3. Registrar jugador o entrenador.
4. Programar partido.
5. Registrar resultado.
6. Mostrar tabla de posiciones.
7. Buscar jugador.
8. Generar reporte y salir.

## Diagrama de clases

![Diagrama de clases](IMG/Diagrama_Torneo.png)

## Relaciones entre clases

| Clase A | Clase B | Tipo | Multiplicidad | Implementación |
|---|---|---|---|---|
| `Torneo` | `Equipo` | Composición | 1 a 0..* | `Torneo` mantiene la colección de equipos que administra. |
| `Torneo` | `Partido` | Composición | 1 a 0..* | `Torneo` mantiene la colección de partidos programados. |
| `Equipo` | `Jugador` | Agregación | 0..1 a 0..* | `Equipo` contiene una `List<Jugador>`. El jugador es un objeto independiente. |
| `Equipo` | `Entrenador` | Asociación bidireccional | 1 a 0..* | `Equipo` conoce al entrenador y `Entrenador` mantiene una lista de equipos. |
| `Jugador` | `Posicion` | Asociación unidireccional | * a 1 | `Jugador` conoce su `Posicion`; `Posicion` no mantiene jugadores. |
| `Partido` | `Equipo` | Asociación | 1 partido a 2 equipos | `Partido` mantiene `equipoLocal` y `equipoVisitante`. |

## Puntos técnicos aplicados

### Operadores y decisiones

- `if`, `else if` y `switch` para validaciones y menú.
- Cálculo de 3 puntos por victoria, 1 por empate y 0 por derrota.
- Validación de nombres, edades, camiseta, experiencia y goles.

### Estructuras repetitivas

- `do-while` para mantener el menú activo hasta la opción 8.
- `while` para solicitar nuevamente datos inválidos.
- `for` y `for-each` para recorrer equipos, jugadores y partidos.
- `break` al encontrar un partido.
- `continue` para omitir datos inválidos y repetir la solicitud.

### Cadenas y conversiones

- `equalsIgnoreCase()` para buscar equipos y jugadores sin distinguir mayúsculas y minúsculas.
- `trim()` para limpiar entradas.
- `Integer.parseInt()` mediante `convertirEntero()` para convertir datos de `Scanner` a `int` con control de errores.
- `Double.parseDouble()` mediante `convertirDouble()` como utilidad para conversiones decimales.
- `StringBuilder` para generar el reporte final.

### POO, encapsulamiento y validaciones

- `Persona` es una clase abstracta.
- `Jugador` y `Entrenador` heredan de `Persona`.
- `mostrarRol()` está sobrescrito con `@Override` en ambas subclases.
- Los atributos están encapsulados mediante `private`.
- Los atributos modificables cuentan con setters que validan los datos antes de asignarlos.
- `Equipo.esNombreValido(String nombre)` es un método estático de validación.
- Las colecciones se exponen como listas no modificables para evitar cambios externos directos.

### Colecciones y arreglos

- `ArrayList` para equipos, jugadores, entrenadores y partidos.
- `List<T>` para trabajar con colecciones genéricas.
- Arreglo `String[]` para las opciones del menú.
- Lista de objetos `PosicionEquipo` para construir y ordenar la tabla de posiciones.

## Validaciones implementadas

El programa rechaza, entre otros casos:

- Nombres vacíos.
- Equipos repetidos.
- Edades fuera del rango 1 a 100.
- Números de camiseta fuera del rango 1 a 99.
- Años de experiencia negativos o mayores a 80.
- Goles negativos.
- Partidos con equipos inexistentes.
- Partidos donde el mismo equipo sea local y visitante.
- Partidos registrados con equipos que no pertenecen al torneo.
- Entradas que no pueden convertirse a entero.

## Tabla de posiciones

La tabla muestra:

- Equipo.
- Partidos jugados.
- Puntos.

Los equipos se ordenan de mayor a menor puntaje. En caso de empate, se ordenan alfabéticamente por nombre.

![Tabla de posiciones](IMG/tabla_posiciones.png)

## Evidencias de ejecución

### Menú y creación del torneo

![Creación del torneo](IMG/crear_torneo.png)

### Registro exitoso de un equipo

![Registro de equipo](IMG/registrar_equipo.png)

### Registro exitoso de un jugador

![Registro de jugador](IMG/registrar_jugador.png)

### Validación de dato inválido

![Validación](IMG/validacion_equipo_inexistente.png)

### Reporte final

![Reporte final](IMG/reporte_final_torneo.png)

## Prueba funcional realizada

Se verificó el flujo principal con dos equipos, jugadores, un entrenador, un partido y un resultado. También se probó el rechazo de un nombre de equipo vacío y de goles negativos.



