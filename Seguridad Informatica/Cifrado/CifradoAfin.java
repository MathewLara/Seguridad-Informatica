package Cifrado;

/**
 * Clase encargada de toda la lógica matemática del Cifrado Afín.
 * La hicimos nosotros mismos desde cero, sin usar librerías criptográficas
 * ni métodos como BigInteger, aplicando módulo 27 (solo minúsculas e incluyendo la ñ).
 */
public class CifradoAfin {

    // Definimos nuestro propio alfabeto de 27 letras solo con minúsculas y metiendo la 'ñ' en la posición 14.
    // La cuestión de usar este String es que en el código ASCII la 'ñ' está separada de las demás letras,
    // entonces así controlamos nosotros mismos las posiciones exactas del 0 al 26.
    public static final String ALFABETO = "abcdefghijklmnñopqrstuvwxyz";
    public static final int MODULO = 27;

    /**
     * Método hecho a mano para buscar en qué posición (de 0 a 26) está cada letra.
     * Recorremos el alfabeto letra por letra con un for normal. Si el carácter no está
     * (por ejemplo, si es espacio, número, signo o mayúscula), devolvemos -1 para no tocarlo.
     */
    public static int obtenerPosicion(char c) {
        for (int i = 0; i < ALFABETO.length(); i++) {
            if (ALFABETO.charAt(i) == c) {
                return i; // Retornamos la posición numérica de la letra (M o C)
            }
        }
        return -1; // No está en nuestro alfabeto de minúsculas, así que luego se copia igual
    }

    /**
     * Método propio para verificar si el usuario metió letras mayúsculas en el texto.
     * Lo revisamos carácter por carácter sin expresiones regulares externas.
     */
    public static boolean contieneMayusculas(String texto) {
        String mayusculasConTilde = "ÁÉÍÓÚÜÑ";
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if ((c >= 'A' && c <= 'Z') || mayusculasConTilde.indexOf(c) != -1) {
                return true; // Encontramos al menos una mayúscula
            }
        }
        return false;
    }

    /**
     * Calculamos el Máximo Común Divisor (MCD) nosotros mismos con el algoritmo de Euclides.
     * Esto es clave porque para poder descifrar necesitamos que MCD(a, 27) sea igual a 1 (coprimos).
     */
    public static int mcd(int a, int b) {
        if (a < 0) a = -a;
        if (b < 0) b = -b;

        while (b != 0) {
            int temporal = b;
            b = a % b;
            a = temporal;
        }
        return a;
    }

    /**
     * Función para normalizar los residuos negativos en módulo 27.
     * En Java el operador % puede darnos un número negativo al restar (C - b) en el descifrado,
     * entonces en base a la fórmula le sumamos el módulo y volvemos a sacar % 27 para que siempre quede entre 0 y 26.
     */
    public static int mod27(int n) {
        return ((n % MODULO) + MODULO) % MODULO;
    }

    /**
     * Buscamos el inverso modular de 'a' probando candidatos uno por uno del 1 al 26,
     * tal como lo hacemos a mano. La idea es encontrar qué número multiplicado por 'a'
     * nos deja residuo 1 al dividirlo para 27.
     */
    public static int inversoModular(int a) {
        int aNormalizado = mod27(a);
        for (int candidato = 1; candidato < MODULO; candidato++) {
            if ((aNormalizado * candidato) % MODULO == 1) {
                return candidato; // Para a=5 en mod 27 nos devuelve 11
            }
        }
        return -1; // No tiene inverso modular
    }

    /**
     * Validamos las claves antes de hacer cualquier cálculo.
     * En módulo 27, 'a' debe ir de 1 a 26 y ser coprimo con 27 (no ser múltiplo de 3),
     * y 'b' debe estar en el rango de 0 a 26.
     */
    public static String validarClaves(int a, int b) {
        if (a < 1 || a >= MODULO) {
            return "El valor de 'a' debe estar entre 1 y " + (MODULO - 1) + ".";
        }
        if (b < 0 || b >= MODULO) {
            return "El valor de 'b' debe estar entre 0 y " + (MODULO - 1) + ".";
        }
        if (mcd(a, MODULO) != 1) {
            return "La clave 'a' (" + a + ") no es válida porque MCD(" + a + ", " + MODULO + ") = "
                    + mcd(a, MODULO) + " (debe ser 1 para tener inverso modular).";
        }
        return null;
    }

    /**
     * Proceso de Cifrado aplicando la fórmula: C = (a * M + b) mod 27.
     */
    public static String cifrar(String texto, int a, int b) {
        String error = validarClaves(a, b);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {
            char caracterActual = texto.charAt(i);
            int m = obtenerPosicion(caracterActual);

            if (m != -1) {
                int c = mod27(a * m + b);
                resultado.append(ALFABETO.charAt(c));
            } else {
                resultado.append(caracterActual);
            }
        }
        return resultado.toString();
    }

    /**
     * Proceso de Descifrado aplicando la fórmula: M = (a^-1 * (C - b)) mod 27.
     */
    public static String descifrar(String textoCifrado, int a, int b) {
        String error = validarClaves(a, b);
        if (error != null) {
            throw new IllegalArgumentException(error);
        }

        int aInverso = inversoModular(a);
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < textoCifrado.length(); i++) {
            char caracterActual = textoCifrado.charAt(i);
            int c = obtenerPosicion(caracterActual);

            if (c != -1) {
                int m = mod27(aInverso * (c - b));
                resultado.append(ALFABETO.charAt(m));
            } else {
                resultado.append(caracterActual);
            }
        }
        return resultado.toString();
    }
}