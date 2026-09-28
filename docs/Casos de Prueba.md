# Casos de prueba

Estos 8 casos se ejecutan automaticamente con la opcion **9** del menu principal (`Ejecutar casos de prueba automaticos`). Cubren las seis estructuras exigidas, la regla diferenciadora del grupo y la operacion de deshacer. Para las capturas de Visual Studio Code, basta con correr el programa, elegir la opcion 9 y capturar la salida completa; tambien se puede repetir cada caso a mano usando el menu correspondiente.

| # | Caso | Estructura(s) que demuestra | Resultado esperado |
|---|---|---|---|
| 1 | Prestamo exitoso con kit disponible (Equipo Delta) | Lista secuencial (busqueda de disponible) + lista simple (insercion) + pila (se apila la operacion) | Equipo Delta recibe **KIT001**; aparece en prestamos activos. |
| 2 | Solicitud enviada a la cola por falta de kits completos (Equipo Zeta) | Cola (encolar) | Equipo Epsilon recibe **KIT003**; Equipo Zeta queda en cola porque los 3 kits estan prestados. |
| 3 | Deshacer la ultima operacion critica | Pila (desapilar, LIFO) | Se revierte el prestamo de Equipo Epsilon; **KIT003** vuelve a `DISPONIBLE`. |
| 4 | Devolucion completa y atencion automatica de la cola | Lista simple (eliminar) + lista secuencial (modificar estado) + cola (desencolar) | **KIT001** vuelve a `DISPONIBLE`; Equipo Zeta sale de la cola y recibe **KIT001**. |
