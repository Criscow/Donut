import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int k;
        double A = 0, B = 0, i, j; // Inicializa las variables de los ángulos y los índices de los bucles
        double[] z = new double[1760]; // Buffer para guardar la profundidad de cada píxel
        char[] b = new char[1760]; // Buffer para guardar los caracteres de salida
        System.out.print("\u001b[2J"); // Limpia la pantalla de la terminal

        for (; ; ) { // Bucle infinito para la animación
            Arrays.fill(b, 0, 1760, ' '); // Llena el buffer de salida con espacios
            Arrays.fill(z, 0, 1760, 0); // Llena el buffer de profundidad con ceros

            for (j = 0; 6.28 > j; j += 0.07) // Bucle para el ángulo theta
                for (i = 0; 6.28 > i; i += 0.02) { // Bucle para el ángulo phi
                    double c = Math.sin(i), // Calcula el seno de phi
                            d = Math.cos(j), // Calcula el coseno de theta
                            e = Math.sin(A), // Calcula el seno de A
                            f = Math.sin(j), // Calcula el seno de theta
                            g = Math.cos(A), // Calcula el coseno de A
                            h = d + 2, // Calcula la distancia desde el observador
                            D = 1 / (c * h * e + f * g + 5), // Calcula el factor de escala de la proyección
                            l = Math.cos(i), // Calcula el coseno de phi
                            m = Math.cos(B), // Calcula el coseno de B
                            n = Math.sin(B), // Calcula el seno de B
                            t = c * h * g - f * e; // Calcula la posición en el eje z
                    int x = (int) (40 + 30 * D * (l * h * m - t * n)), // Calcula la posición en el eje x
                            y = (int) (12 + 15 * D * (l * h * n + t * m)), // Calcula la posición en el eje y
                            o = x + 80 * y, // Calcula el índice en el buffer de salida
                            N = (int) (8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n)); // Calcula la luminancia
                    if (22 > y && y > 0 && x > 0 && 80 > x && D > z[o]) { // Si el píxel está en la pantalla y está más cerca que el píxel anterior
                        z[o] = D; // Actualiza la profundidad del píxel
                        b[o] = new char[]{'.', ',', '-', '~', ':', ';', '=', '!', '*', '#', '$', '@'}[Math.max(N, 0)]; // Actualiza el carácter de salida
                    }
                }
            System.out.print("\u001b[H"); // Mueve el cursor a la esquina superior izquierda
            for (k = 0; 1761 > k; k++) // Bucle para imprimir el buffer de salida
                System.out.print(k % 80 > 0 ? b[k] : 10); // Imprime el carácter de salida o un salto de línea
            A += 0.04; // Incrementa el ángulo A
            B += 0.02; // Incrementa el ángulo B
        }
    }
}