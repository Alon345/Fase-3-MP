# Videojuego de Luchas Fantásticas - Fase de Codificación

## Descripción
Este videojuego de luchas de criaturas fantásticas, desarrollado para la asignatura *Metodología de la Programación* (curso 2024/2025), permite a los usuarios (administradores y jugadores) gestionar sus personajes y enfrentarse en combates llenos de estrategia. La aplicación integra la creación y modificación de personajes, la gestión de desafíos y combates por rondas, y la persistencia de datos, asegurando que cada partida y cada configuración se conserven entre ejecuciones.

Text that is not a quote

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

Text that is not a quote

> **WARNING:** La correcta configuración del entorno es crucial para que el proyecto compile y funcione sin errores.

## Ejecución
- **Ejecución de la aplicación:**  
  Ejecuta el método `main` de la aplicación para iniciar el videojuego. El sistema cargará la configuración y te presentará las opciones disponibles para gestionar tu personaje y desafiar a otros jugadores.

## Pruebas
Se han implementado pruebas unitarias para garantizar el correcto funcionamiento de:
- La gestión de personajes y sus atributos.
- La lógica de desafíos y apuestas.
- El sistema de combate y registro de resultados.

Text that is not a quote

> **NOTE:** Ejecuta las pruebas con el comando habitual en tu entorno de desarrollo para verificar la integridad del código.

## Documentación
La documentación técnica se encuentra integrada en el código y se complementa con un breve *Documento de Codificación* que explica la estructura del sistema, los patrones de diseño aplicados y las principales decisiones técnicas.

## Equipo
- **Rubén Ruiz Martín:** Analista Funcional  
- **Victor Hugo Oliveira Petroceli:** QA  
- **Raúl Tejada Merinero:** Analista Programador  
- **Ramón Nieto Villegas:** Ingeniero de Desarrollo  
- **Alonso Gutiérrez Sánchez:** Jefe de Desarrollo

## Contacto
Para dudas o sugerencias, contacta a:
- **Alonso Gutiérrez Sánchez (Jefe de Desarrollo):** [a.gutierrez.2023@alumnos.urjc.es](mailto:a.gutierrez.2023@alumnos.urjc.es)

