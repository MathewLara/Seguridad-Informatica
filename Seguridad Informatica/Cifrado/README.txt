1. DESCRIPCIÓN DEL PROYECTO
Aplicación de escritorio desarrollada en Java que implementa de forma 
manual el cifrado y descifrado afín utilizando aritmética modular (Módulo 27, 
trabajando exclusivamente con letras minúsculas e incorporando la letra 'ñ'). 
El programa incluye validaciones estrictas, cálculo de MCD (Algoritmo de 
Euclides), inverso modular por búsqueda acotada y normalización de residuos 
negativos, todo sin utilizar librerías criptográficas externas.

2. REQUISITOS DEL ENTORNO
- Java Development Kit (JDK): Versión 17 o superior instalada en el sistema.
- Variables de entorno de Java configuradas (javac y java accesibles desde la terminal).
- No requiere conexión a internet ni dependencias externas.

3. ESTRUCTURA DEL PROYECTO
El código fuente está organizado dentro de la carpeta del paquete "Cifrado":
- CifradoAfin.java     -> Lógica matemática, validaciones, MCD, inverso y fórmulas.
- CifradoAfinGUI.java  -> Interfaz gráfica con Java Swing y componentes visuales.

4. INSTRUCCIONES PARA ABRIR, COMPILAR Y EJECUTAR DESDE LA TERMINAL

Sigue estos pasos exactos para compilar y ejecutar el programa mediante la línea 
de comandos (CMD en Windows, PowerShell o Terminal en Linux/macOS):

Paso 1: Abrir la terminal en la carpeta del proyecto
- En Windows: Abre la carpeta principal de tu proyecto en el Explorador de archivos. 
  Haz clic en la barra de direcciones superior, escribe `cmd` y presiona Enter. 
  Esto abrirá la consola ya ubicada en la ruta correcta.
- En VS Code / IDE: Abre la carpeta raíz del proyecto y en el menú superior 
  selecciona Terminal -> New Terminal.

Paso 2: Verificar la ubicación actual
Asegúrate de estar parado en la carpeta raíz (la carpeta contenedora que 
contiene a la carpeta llamada "Cifrado"). Puedes comprobarlo ejecutando:
  - En Windows (CMD): `dir` (debe aparecer la carpeta "Cifrado").
  - En Linux/macOS: `ls`

Paso 3: Compilar los archivos fuente
Como los archivos pertenecen al paquete "Cifrado", debes compilar ambos 
especificando la ruta del paquete desde la raíz con el siguiente comando:
  javac Cifrado/CifradoAfin.java Cifrado/CifradoAfinGUI.java

Paso 4: Ejecutar la aplicación
Una vez compilado correctamente (lo que generará los archivos `.class` dentro 
de la carpeta Cifrado), ejecuta la clase principal llamando al paquete completo:
  java Cifrado.CifradoAfinGUI

5. ACLARACIÓN DE TRABAJO INDIVIDUAL
Desarrollo realizado de manera estrictamente individual (un solo integrante), 
cumpliendo a cabalidad con la implementación manual, el módulo 27 y el diseño 
de interfaz solicitados en la práctica experimental.