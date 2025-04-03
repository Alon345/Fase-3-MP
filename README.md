# Videojuego de Luchas Fantásticas - Fase de Codificación

## Descripción
Bienvenido al repositorio del videojuego de luchas de criaturas fantásticas, desarrollado para la asignatura *Metodología de la Programación* (curso 2024/2025). Este juego permite a los usuarios (administrador y jugador) gestionar personajes y participar en combates dinámicos basados en rondas, donde se tienen en cuenta atributos, habilidades especiales, equipamiento, y modificadores de debilidades y fortalezas. Además, el sistema registra la persistencia de combates y configuraciones para garantizar la continuidad de la experiencia de juego.

[!WARNING] Asegúrate de revisar la documentación técnica y los diagramas para comprender la implementación completa de la lógica de combate y la gestión de personajes.

## Tabla de Contenidos
- [Descripción](#descripción)
- [Características](#características)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución](#ejecución)
- [Pruebas](#pruebas)
- [Documentación y Diagramas](#documentación-y-diagramas)
- [Equipo](#equipo)
- [Contacto](#contacto)

## Características
- **Gestión Integral de Personajes:**  
  Permite la creación y modificación de personajes de tres tipos (Vampiros, Licántropos y Cazadores), cada uno con atributos únicos, armas, armaduras, esbirros, y habilidades especiales.  
  - *Vampiros:* Utilizan sangre para activar sus disciplinas.  
  - *Licántropos:* Incrementan su fuerza mediante la rabia acumulada.  
  - *Cazadores:* Dependen de sus puntos de voluntad que decrecen en combate.
  
- **Sistema de Desafíos y Combates:**  
  Los usuarios pueden desafiar a otros mediante apuestas de oro, activando combates por rondas. Cada ronda evalúa el potencial de ataque y defensa basado en:
  - Habilidades especiales (Disciplinas, Dones y Talentos).
  - Modificadores de equipo activo (armas y armaduras).
  - Fortalezas y debilidades específicas de cada personaje.
  
- **Persistencia de Datos:**  
  Todos los combates, junto con la información de usuarios y configuraciones, se almacenan para su consulta y análisis posterior.

[!IMPORTANT] Es crucial que cada combate siga las reglas establecidas: se calculan los puntos de éxito mediante lanzamientos aleatorios y se aplican modificadores según el estado de los personajes.

## Instalación y Configuración
1. **Clona el Repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/tu-repositorio.git
    ```
2. **Accede al Directorio del Proyecto:**
    ```bash
    cd tu-repositorio
    ```
3. **Configura el Entorno:**
    - Verifica que tienes instalado **Java JDK 21**.
    - Configura las variables de entorno necesarias. Si utilizas una base de datos, revisa y ajusta el archivo `config.properties` o el que hayas definido.

[!WARNING] Una configuración incorrecta puede impedir que el juego se compile o ejecute correctamente.

## Ejecución
- **Ejecución de la Aplicación:**  
  Ejecuta el método `MAIN` desde tu IDE o compila y ejecuta el JAR generado:
    ```bash
    java -jar target/nombre-del-proyecto.jar
    ```
  
  Al iniciar, se mostrará un menú interactivo que permitirá:
  - Registrar y gestionar personajes.
  - Realizar desafíos entre usuarios.
  - Configurar el equipo (armas y armaduras) y activar habilidades especiales.

[!NOTE] Sigue las instrucciones en pantalla para navegar por el juego y experimentar con sus diferentes funcionalidades.

## Pruebas
- Se han desarrollado pruebas unitarias para asegurar el correcto funcionamiento en:
  - Creación y gestión de personajes.
  - Lógica de desafíos, apuestas y combates.
  - Registro y persistencia de datos.
  
  Para ejecutar las pruebas:
    ```bash
    mvn test
    ```

[!WARNING] No ignores los resultados de las pruebas, ya que ayudan a identificar posibles errores en la lógica de combate y en la gestión de datos.

## Documentación y Diagramas
- **Documento de Codificación:**  
  Incluye descripciones de la estructura del código, patrones de diseño y decisiones técnicas relevantes.
- **Diagramas UML:**  
  Encuentra los diagramas de clases, actividades, secuencia, estados y casos de uso en la carpeta `/diagrams`.

[!NOTE] Consulta estos documentos para obtener una visión completa de la arquitectura del juego y para facilitar futuras mejoras o mantenimientos.

## Equipo
- **Rubén Ruiz Martín:** Analista Funcional  
- **Victor Hugo Oliveira Petroceli:** QA  
- **Raúl Tejada Merinero:** Analista Programador  
- **Ramón Nieto Villegas:** Ingeniero de Desarrollo  
- **Alonso Gutiérrez Sánchez:** Jefe de Desarrollo

## Contacto
Para cualquier duda o sugerencia, contacta a:
- **Alonso Gutiérrez Sánchez (Jefe de Desarrollo):** [a.gutierrez.2023@alumnos.urjc.es](mailto:a.gutierrez.2023@alumnos.urjc.es)
