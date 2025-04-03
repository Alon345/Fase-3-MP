# Videojuego de Luchas Fantásticas - Fase de Codificación

## Descripción
Este proyecto es el videojuego de luchas de criaturas fantásticas desarrollado en el marco de la asignatura *Metodología de la Programación* para el curso 2023/2024. La aplicación permite a los usuarios (administrador y jugador) gestionar personajes, desafíos y combates, integrando funcionalidades de persistencia y validación en cada combate.  
[!WARNING] Es fundamental que la aplicación cumpla todos los requisitos especificados en el enunciado, independientemente de la interfaz de usuario (sea consola o GUI).

## Tabla de Contenidos
- [Descripción](#descripción)
- [Características](#características)
- [Requisitos del Sistema](#requisitos-del-sistema)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución](#ejecución)
- [Pruebas](#pruebas)
- [Documentación y Diagramas](#documentación-y-diagramas)
- [Contribución](#contribución)
- [Equipo](#equipo)
- [Licencia](#licencia)

## Características
- **Gestión de Personajes:** Creación y modificación de personajes (Vampiros, Licántropos y Cazadores) con atributos específicos, armas, armaduras, esbirros, debilidades y fortalezas.
- **Sistema de Desafíos:** Permite a los usuarios desafiarse entre ellos, gestionando apuestas de oro y validando combates según las reglas definidas.
- **Combate por Rondas:** Combates basados en rondas con cálculo de potencial de ataque y defensa, integrando modificadores y efectos de habilidades especiales.
- **Persistencia:** Registro de combates, usuarios y configuraciones para asegurar la continuidad de la aplicación entre ejecuciones.

[!NOTE] Revisa cada uno de los requisitos detallados en el enunciado para garantizar que la implementación cubre todos los casos de uso y validaciones necesarias.

## Requisitos del Sistema
- **Java JDK 21**
- **IDE recomendado:** IntelliJ IDEA
- **Sistema de Control de Versiones:** GitHub (todos los miembros del equipo deberán ser colaboradores del repositorio)
- **Base de Datos:** (si aplica, según la implementación de persistencia)
- **Otras Herramientas:**  
  - Trello para la gestión de tareas  
  - Teams para la comunicación interna  
  - PlantText para la elaboración de diagramas UML  
  - Microsoft Word para la documentación complementaria

[!WARNING] Verifica la instalación y configuración de todas las herramientas antes de iniciar el desarrollo para evitar problemas futuros.

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
    - Asegúrate de tener Java JDK 21 instalado.
    - Configura las variables de entorno necesarias y, en caso de usar base de datos, ajusta la configuración en el archivo `config.properties` (o el archivo que hayas definido).

[!WARNING] Cualquier error en la configuración del entorno puede impedir la compilación y ejecución del proyecto.

## Ejecución
- **Compilación:**
    ```bash
    mvn clean install
    ```
- **Ejecución de la aplicación:**
    ```bash
    java -jar target/nombre-del-proyecto.jar
    ```

[!IMPORTANT] Antes de ejecutar, asegúrate de que todos los tests unitarios se hayan completado correctamente y de que el código compile sin errores.

## Pruebas
- Se han desarrollado pruebas unitarias para verificar la funcionalidad de:
  - Gestión de personajes y atributos.
  - Lógica de desafíos y apuestas.
  - Cálculo de combates y registro de resultados.
- Para ejecutar las pruebas:
    ```bash
    mvn test
    ```
[!NOTE] Revisa los informes de pruebas para confirmar que todos los casos críticos se comportan según lo esperado.

## Documentación y Diagramas
- **Documentación:**  
  - *Documento de Codificación:* Incluye la descripción de la estructura del código, patrones de diseño y explicaciones de las decisiones técnicas.
- **Diagramas UML:**  
  - Diagrama de clases, actividad, secuencia, estados y casos de uso se encuentran en la carpeta `/diagrams`.  
  [!WARNING] Asegúrate de que los diagramas estén actualizados y reflejen el estado actual de la implementación.

## Contribución
- Se recomienda seguir las buenas prácticas de Git: trabajar en ramas para cada nueva funcionalidad y realizar *pull requests* para revisión.
- Antes de realizar un *commit*, ejecuta las pruebas unitarias y valida la compilación del proyecto.
- Utiliza comentarios claros y documenta cualquier cambio significativo en el código.

[!NOTE] Si tienes dudas sobre las convenciones de codificación o el flujo de trabajo, consulta al Jefe de Desarrollo.

## Equipo
- **Rubén Ruiz Martín:** Analista Funcional  
- **Victor Hugo Oliveira Petroceli:** QA  
- **Raúl Tejada Merinero:** Analista Programador  
- **Ramón Nieto Villegas:** Ingeniero de Desarrollo  
- **Alonso Gutiérrez Sánchez:** Jefe de Desarrollo

[!IMPORTANT] Todos los integrantes deben estar al tanto de las actualizaciones y cambios, y participar activamente en las revisiones de código.

## Licencia
Este proyecto se distribuye bajo la [Licencia XYZ](./LICENSE).

## Contacto
Para cualquier duda o sugerencia, contacta a:
- **Alonso Gutiérrez Sánchez (Jefe de Desarrollo):** [correo@ejemplo.com](mailto:correo@ejemplo.com)
