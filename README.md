# TDA-Examen-Kit-Robotica

Prueba practica integradora de Estructura de Datos (Java, Visual Studio Code) - Grupo 3: Sistema de control de kits de robotica educativa.

## Equipo

| Integrante | Usuario GitHub | Responsabilidad | Estructura / modulo | Estado |
|---|---|---|---|---|
| Chico Yunda Juan Carlos (lider) | juanchicoyunda1234 | Liderazgo, integracion final, casos de prueba, README | Orquestacion (`negocio.SistemaKits`), `app.CasosPrueba` | Completo |
| Torosina Armendariz Jeremy | WinoSpop | Documentacion y diagramas | Diagrama de clases, seccion de estructuras del README | Completo |
| Altamirano Segovia Jullisa | jullisaaltamirano2017-boop | Documentacion e informe | Casos de prueba documentados (`docs/CASOS-DE-PRUEBA.md`), evidencia de ejecucion | Completo |
| Romo Nunez Joseph | wayusa25-cmyk | Backend - Modelo | Paquete `modelo` (clases de datos y nodos) | Completo |
| Llamuca Abrajan Andres | llamucaandres161 | Backend - Negocio | Paquete `estructuras` (las 6 estructuras) | Completo |
| Tuza Quinatoa Noemi | edithtuza15-collab | Frontend - Integracion | Paquete `app` (`Main.java`, menu de consola) | Completo |

Cada integrante tiene registrado su usuario de GitHub en esta tabla y refleja en sus propios commits el modulo que tiene asignado.

## Caso asignado

**Grupo 3 - Sistema de control de kits de robotica educativa.** El laboratorio presta kits de robotica (Arduino, ESP32, Micro:bit, etc.) a equipos de estudiantes para practicas. Cada kit tiene piezas sensibles que deben controlarse en el prestamo y, sobre todo, en la devolucion.

**Regla diferenciadora:** si en la devolucion faltan piezas respecto de las registradas para ese kit, el kit **no puede volver al estado Disponible**; pasa a **Mantenimiento** hasta que se reponen las piezas faltantes.

**Datos minimos cargados al iniciar** (`negocio/DatosPrueba.java`): KIT001 (Arduino, 42 piezas, Disponible), KIT002 (ESP32, 38 piezas, Prestado a Equipo Alpha), KIT003 (Micro:bit, 40 piezas, Disponible).

## Arquitectura del proyecto

```
src/
  modelo/       Clases de datos: KitRobotica, Equipo, Prestamo, SolicitudPrestamo,
                EventoHistorial, OperacionCritica, EstadoKit, y los nodos propios
                (NodoPrestamo, NodoHistorial, NodoSolicitud, NodoTurno, NodoOperacion)
  estructuras/  Las 6 estructuras exigidas, cada una en su propia clase
  negocio/      SistemaKits (orquesta las 6 estructuras) y DatosPrueba (carga inicial)
  app/          Main (menu de consola) y CasosPrueba (demostracion automatica)
```

## Estructuras utilizadas y por que se eligio cada una

| Estructura | Uso en el sistema | Justificacion |
|---|---|---|
| **Lista secuencial** (`InventarioKits`, arreglo con `tope`/`capacidad`) | Inventario general de kits | El inventario se recorre y se busca por codigo constantemente; un arreglo con indice directo hace la busqueda y el listado simples, y el tamano del laboratorio es acotado y conocido de antemano. |
| **Lista simplemente enlazada** (`ListaPrestamos`, nodos `NodoPrestamo`) | Prestamos activos por equipo | Los prestamos activos crecen y se eliminan constantemente (cada devolucion saca uno); una lista enlazada evita mover elementos como en un arreglo y no necesita un tamano fijo. |
| **Lista doblemente enlazada** (`HistorialMovimientos`, nodos `NodoHistorial`) | Historial de prestamos, devoluciones, danos y faltantes | El historial se muestra tanto en orden cronologico como del mas reciente al mas antiguo (para revisar rapido lo ultimo que paso); los enlaces `anterior`/`siguiente` permiten recorrer en ambos sentidos sin duplicar datos. |
| **Cola** (`ColaSolicitudes`, nodos `NodoSolicitud`) | Solicitudes en espera cuando no hay kits completos disponibles | El orden de atencion debe respetar FIFO: el primer equipo que pidio un kit y no lo obtuvo debe ser el primero en recibirlo cuando se libere uno. |
| **Pila** (`PilaOperaciones`, nodos `NodoOperacion`) | Revertir la ultima operacion critica (un prestamo o una devolucion) | Deshacer siempre afecta a la accion mas reciente (comportamiento LIFO); la pila guarda el estado anterior del kit y el prestamo involucrado para poder restaurarlo exactamente. |
| **Lista circular** (`TurnosMesaEnsamblaje`, nodos `NodoTurno`) | Turnos de la mesa de ensamblaje compartida | Los equipos rotan indefinidamente sobre el mismo recurso compartido; una lista circular representa naturalmente ese ciclo sin necesitar reiniciar el recorrido. |

Ninguna estructura dinamica usa `LinkedList`, `Stack`, `Queue`, `ArrayDeque` ni otra coleccion de `java.util`; todas se implementan con nodos propios. El unico uso de `java.util` en todo el proyecto es `Scanner`, para leer la entrada del usuario.

El diagrama de clases completo esta en [`docs/DIAGRAMA-CLASES.md`](docs/DIAGRAMA-CLASES.md).

## Compilacion y ejecucion

Desde la terminal integrada de Visual Studio Code, dentro de la carpeta `src`:

```bash
cd src
javac modelo/*.java estructuras/*.java negocio/*.java app/*.java -d ../bin
java -cp ../bin app.Main
```

## Menu principal

1. Inventario de kits (registrar, buscar, mostrar, modificar estado, eliminar con validacion)
2. Prestamos por equipo (solicitar prestamo, ver prestamos activos)
3. Cola de solicitudes en espera (atender siguiente, listar)
4. Devoluciones (aplica la regla diferenciadora de piezas faltantes)
5. Mantenimiento (ver kits en mantenimiento, reponer piezas)
6. Historial de movimientos (cronologico o mas reciente primero)
7. Turnos de la mesa de ensamblaje (registrar equipo, avanzar turno, eliminar actual, mostrar ronda)
8. Deshacer ultima operacion critica
9. Ejecutar casos de prueba automaticos
10. Salir

## Casos de prueba

La opcion 9 del menu ejecuta 8 casos funcionales automaticos y deterministas que cubren las seis estructuras, la regla diferenciadora y el deshacer. El detalle de cada caso, con el resultado esperado, esta en [`docs/CASOS-DE-PRUEBA.md`](docs/CASOS-DE-PRUEBA.md).

## Evidencia de colaboracion

El historial de commits de este repositorio debe mostrar aportes identificables de los 6 integrantes, con al menos 3 commits significativos por persona, distribuidos segun el modulo que cada quien tiene asignado en la tabla de arriba. Los mensajes de commit describen el aporte funcional (por ejemplo "Implementa cola de solicitudes", "Agrega eliminacion de kit con validacion"), nunca mensajes genericos como "cambio" o "avance".
