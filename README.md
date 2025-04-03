# Videojuego de Luchas Fantásticas - Fase de Codificación

## Descripción
Este videojuego de luchas de criaturas fantásticas, desarrollado para la asignatura *Metodología de la Programación* (curso 2024/2025), permite a los usuarios (administradores y jugadores) gestionar sus personajes y enfrentarse en combates llenos de estrategia. La aplicación integra la creación y modificación de personajes, la gestión de desafíos y combates por rondas, y la persistencia de datos, asegurando que cada partida y cada configuración se conserven entre ejecuciones.

> **WARNING:** Asegúrate de tener configurado correctamente el entorno y las dependencias para evitar problemas durante la ejecución.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Características](#características)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución](#ejecución)
- [Pruebas](#pruebas)
- [Documentación](#documentación)
- [Equipo](#equipo)
- [Contacto](#contacto)

## Características
- **Gestión de Personajes:**  
  Crea y personaliza personajes (Vampiros, Licántropos y Cazadores) con atributos, armas, armaduras, esbirros, debilidades y fortalezas.
- **Sistema de Desafíos:**  
  Los jugadores pueden retarse entre sí, apostando oro y configurando sus equipos antes de cada combate.
- **Combate por Rondas:**  
  Los enfrentamientos se desarrollan en rondas. Cada ronda calcula el potencial de ataque y defensa de los personajes, teniendo en cuenta sus habilidades especiales, el equipo activo y modificadores derivados de sus fortalezas y debilidades.
- **Persistencia:**  
  Toda la información (usuarios, personajes y combates) se guarda de forma persistente, lo que permite continuar la experiencia de juego sin perder datos.

## Mecánica del Juego
En este videojuego, los jugadores controlan personajes de diferentes razas (Vampiros, Licántropos y Cazadores) y se enfrentan en combates por rondas, donde cada personaje tiene estadísticas y habilidades que influyen en su rendimiento. Los jugadores deben gestionar sus recursos, como oro, equipo y habilidades, para poder desafiar a otros y ganar batallas.

Cada combate se divide en rondas, y cada ronda tiene dos fases:
1. **Fase de Ataque:** El personaje intenta infligir daño al oponente utilizando su potencial de ataque y habilidades activas.
2. **Fase de Defensa:** El oponente intenta bloquear o mitigar el daño recibido, basándose en su defensa y habilidades especiales.

El objetivo es reducir la salud del oponente a cero antes de que lo hagan contigo.

## Roles
- **Administrador:**  
  El administrador tiene control total sobre el juego, pudiendo gestionar la base de datos de personajes, configuraciones y usuarios. Además, puede añadir o modificar personajes, armas, armaduras y otras configuraciones esenciales para el correcto funcionamiento del juego.
  
- **Jugador:**  
  Los jugadores crean y gestionan sus propios personajes, participan en desafíos, y configuran sus equipos para participar en combates contra otros jugadores o la inteligencia artificial.

> **NOTE:** Los administradores pueden proporcionar premios en oro o recompensas a los jugadores según los resultados de los combates.

## Sistema de Habilidades y Atributos
Cada personaje tiene un conjunto de habilidades y atributos que afectan su desempeño en combate. Estos son algunos de los elementos clave:

- **Atributos:**
  - **Salud (HP):** Cuánta vida tiene el personaje. Si llega a 0, el personaje pierde el combate.
  - **Ataque:** Determina cuánto daño puede hacer el personaje.
  - **Defensa:** Reduce el daño recibido durante el combate.
  - **Velocidad:** Influye en el orden de las acciones en cada ronda.

- **Habilidades:**
  - **Habilidades Especiales:** Cada raza tiene habilidades únicas que pueden cambiar el curso de una batalla. Por ejemplo, el Vampiro puede regenerar salud, mientras que el Licántropo tiene habilidades de ataque mejoradas durante la luna llena.
  - **Habilidades Pasivas:** Son habilidades que afectan al personaje de manera continua, como resistir venenos o aumentar la probabilidad de crítico.

> **NOTE:** Las habilidades pueden evolucionar o mejorarse a medida que el personaje sube de nivel.

## Instalación y Configuración
1. **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/tu-repositorio.git
    ```
2. **Acceder al directorio del proyecto:**
    ```bash
    cd tu-repositorio
    ```
3. **Configurar el entorno:**
    - Verifica que tienes instalado Java JDK 21.
    - Ajusta las variables de entorno y la configuración (por ejemplo, en `config.properties`) según tus necesidades.

> **WARNING:** La correcta configuración del entorno es crucial para que el proyecto compile y funcione sin errores.

## Ejecución
- **Ejecución de la aplicación:**  
  Ejecuta el método `main` de la aplicación para iniciar el videojuego. El sistema cargará la configuración y te presentará las opciones disponibles para gestionar tu personaje y desafiar a otros jugadores.

## Pruebas
Se han implementado pruebas unitarias para garantizar el correcto funcionamiento de:
- La gestión de personajes y sus atributos.
- La lógica de desafíos y apuestas.
- El sistema de combate y registro de resultados.

## Documentación
La documentación técnica se encuentra integrada en el código.

## FAQ

- **¿Cómo puedo crear un personaje?**
  Para crear un personaje, simplemente selecciona la opción "Crear Personaje" en el menú principal y elige entre las diferentes razas disponibles. Luego, personaliza sus atributos y habilidades según tus preferencias.

- **¿Qué razas de personajes puedo elegir?**  
  Puedes elegir entre Vampiros, Licántropos y Cazadores, cada uno con sus propias habilidades y atributos únicos.

- **¿Cómo se pueden cambiar los atributos de un personaje?**  
  Los atributos se definen al momento de la creación del personaje. No es posible cambiarlos después, pero puedes mejorar las habilidades mediante la experiencia obtenida en combate.

- **¿Cómo puedo desafiar a otro jugador?**  
  Desde el menú principal, selecciona "Desafiar a otro jugador" e ingresa la cantidad de oro que deseas apostar antes del combate.

- **¿Cuántas rondas tiene un combate?**  
  Cada combate se desarrolla en múltiples rondas hasta que uno de los personajes pierde toda su salud.

- **¿Cómo se calculan los daños durante un combate?**  
  Los daños se calculan en función de los atributos de ataque y defensa de los personajes, además de las habilidades y modificadores especiales.

- **¿Puedo mejorar las habilidades de mi personaje?**  
  Sí, las habilidades mejoran automáticamente a medida que subes de nivel ganando combates.

- **¿Qué pasa si pierdo un combate?**  
  Si pierdes un combate, tu personaje pierde oro y parte de su salud, pero puedes seguir jugando y mejorar a tu personaje para la próxima batalla.

- **¿Cómo funciona el sistema de apuestas?**  
  Antes de cada combate, puedes apostar oro. El ganador recibe todo el oro apostado por ambos jugadores.

- **¿Puedo modificar la apariencia de mi personaje?**  
  La apariencia de tu personaje es fija, pero puedes cambiar los atributos y habilidades durante la creación.


- **¿Puedo cambiar las armas de mi personaje?**  
  Sí, puedes equipar a tu personaje con diferentes armas que afectan sus estadísticas de combate.

- **¿Puedo crear más de un personaje?**  
  Sí, puedes crear varios personajes y gestionarlos desde el menú principal.

- **¿Cómo puedo ver los resultados de los combates anteriores?**  
  Los resultados de los combates se guardan automáticamente y puedes acceder a ellos desde la sección "Historial de Combates" en el menú.

- **¿Qué pasa si un combate termina en empate?**  
  En caso de empate, ambos jugadores reciben una parte del oro apostado, y no se registra un ganador.

- **¿El juego tiene algún tipo de multijugador online?**  
  No, el juego actualmente solo permite enfrentamientos locales entre jugadores en la misma sesión.

- **¿Puedo cambiar las habilidades de mi personaje durante el combate?**  
  No, las habilidades de tu personaje se usan en el momento adecuado, pero no puedes modificarlas durante el combate.

- **¿Qué debo hacer si el juego se cierra inesperadamente?**  
  Asegúrate de tener el entorno correctamente configurado y que tu dispositivo cumpla con los requisitos del sistema. Si el problema persiste, consulta el archivo de registro de errores o contacta con el soporte técnico.

--

## Equipo
- **Rubén Ruiz Martín:** Analista Funcional  
- **Victor Hugo Oliveira Petroceli:** QA  
- **Raúl Tejada Merinero:** Analista Programador  
- **Ramón Nieto Villegas:** Ingeniero de Desarrollo  
- **Alonso Gutiérrez Sánchez:** Jefe de Desarrollo

## Contacto
Para dudas o sugerencias, contacta a:
- **Alonso Gutiérrez Sánchez (Jefe de Desarrollo):** [a.gutierrez.2023@alumnos.urjc.es](mailto:a.gutierrez.2023@alumnos.urjc.es)

