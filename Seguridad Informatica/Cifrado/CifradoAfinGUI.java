package Cifrado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Interfaz gráfica para el cifrado y descifrado afín con módulo 27 (minúsculas + ñ).
 * Diseñada en dos columnas (Panel de Control lateral + Área de Trabajo) para diferenciarse
 * del diseño básico tradicional, manteniendo todos los componentes de Java Swing estándar.
 */
public class CifradoAfinGUI extends JFrame {

    // Paleta de colores: Negro carbón, Blanco limpio y tonos pasteles suaves
    private static final Color COLOR_NEGRO_ELEGANTE = new Color(24, 24, 27);
    private static final Color COLOR_FONDO_GENERAL  = new Color(248, 250, 252);
    private static final Color COLOR_BLANCO_TARJETA = new Color(255, 255, 255);
    private static final Color COLOR_BORDE_SUAVE    = new Color(226, 232, 240);

    // Tonos pasteles para botones, recuadros y estados
    private static final Color PASTEL_LAVANDA       = new Color(237, 233, 254);
    private static final Color PASTEL_LAVANDA_FONDO = new Color(250, 248, 255);
    private static final Color PASTEL_MENTA         = new Color(209, 250, 229);
    private static final Color PASTEL_ROSA          = new Color(255, 228, 230);
    private static final Color PASTEL_AZUL_INFO     = new Color(241, 245, 249);

    private static final Color TEXTO_OSCURO         = new Color(30, 41, 59);
    private static final Color TEXTO_GRIS           = new Color(100, 116, 139);

    private final JTextArea areaEntrada;
    private final JTextArea areaResultado;
    private final JTextField campoA;
    private final JTextField campoB;
    private final JLabel etiquetaEstado;
    private final JLabel etiquetaInfoMatematica;
    private final JCheckBox checkSoloMinusculas;

    public CifradoAfinGUI() {
        setTitle("Cifrado Afín | Módulo 27 (Minúsculas + ñ)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 580);
        setMinimumSize(new Dimension(760, 520));
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO_GENERAL);
        setLayout(new BorderLayout(14, 14));

        // 1. ENCABEZADO SUPERIOR 
        JPanel panelEncabezado = new JPanel(new BorderLayout());
        panelEncabezado.setBackground(COLOR_NEGRO_ELEGANTE);
        panelEncabezado.setBorder(new EmptyBorder(14, 22, 14, 22));

        JLabel lblTitulo = new JLabel("Cifrado y Descifrado Afín");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 19));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSubtitulo = new JLabel("Implementación manual sin librerías  •  Aritmética Modular (Módulo 27: a-z + ñ)");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSubtitulo.setForeground(PASTEL_LAVANDA);

        JPanel textosHeader = new JPanel(new GridLayout(2, 1, 0, 2));
        textosHeader.setOpaque(false);
        textosHeader.add(lblTitulo);
        textosHeader.add(lblSubtitulo);
        panelEncabezado.add(textosHeader, BorderLayout.WEST);

        // Etiqueta tipo insignia a la derecha del encabezado
        JLabel badgeModulo = new JLabel("  MÓDULO 27  ");
        badgeModulo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badgeModulo.setForeground(COLOR_NEGRO_ELEGANTE);
        badgeModulo.setBackground(PASTEL_MENTA);
        badgeModulo.setOpaque(true);
        badgeModulo.setBorder(new EmptyBorder(4, 8, 4, 8));
        JPanel contenedorBadge = new JPanel(new GridBagLayout());
        contenedorBadge.setOpaque(false);
        contenedorBadge.add(badgeModulo);
        panelEncabezado.add(contenedorBadge, BorderLayout.EAST);

        add(panelEncabezado, BorderLayout.NORTH);

        // Contenedor principal con márgenes limpios
        JPanel contenedorCentral = new JPanel(new BorderLayout(14, 14));
        contenedorCentral.setOpaque(false);
        contenedorCentral.setBorder(new EmptyBorder(0, 16, 0, 16));

        // 2. PANEL LATERAL IZQUIERDO: Claves, Resumen Matemático y Botones
        JPanel panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setBackground(COLOR_BLANCO_TARJETA);
        panelLateral.setPreferredSize(new Dimension(255, 0));
        panelLateral.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE_SUAVE, 1, true),
                new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel lblSeccionClaves = new JLabel("PARÁMETROS DE CLAVE");
        lblSeccionClaves.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSeccionClaves.setForeground(TEXTO_OSCURO);
        lblSeccionClaves.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(lblSeccionClaves);
        panelLateral.add(Box.createVerticalStrut(10));

        // Campo para la clave 'a'
        JLabel lblA = new JLabel("Clave 'a' (1 a 26, coprimo con 27):");
        lblA.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblA.setForeground(TEXTO_GRIS);
        lblA.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(lblA);
        panelLateral.add(Box.createVerticalStrut(4));

        campoA = crearCampoClave("5", PASTEL_LAVANDA_FONDO);
        panelLateral.add(campoA);
        panelLateral.add(Box.createVerticalStrut(10));

        // Campo para la clave 'b'
        JLabel lblB = new JLabel("Clave 'b' (Desplazamiento 0 a 26):");
        lblB.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblB.setForeground(TEXTO_GRIS);
        lblB.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(lblB);
        panelLateral.add(Box.createVerticalStrut(4));

        campoB = crearCampoClave("8", PASTEL_LAVANDA_FONDO);
        panelLateral.add(campoB);
        panelLateral.add(Box.createVerticalStrut(12));

        // Casilla de validación de solo minúsculas
        checkSoloMinusculas = new JCheckBox("Validar solo minúsculas", true);
        checkSoloMinusculas.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        checkSoloMinusculas.setForeground(TEXTO_OSCURO);
        checkSoloMinusculas.setBackground(COLOR_BLANCO_TARJETA);
        checkSoloMinusculas.setFocusPainted(false);
        checkSoloMinusculas.setAlignmentX(Component.LEFT_ALIGNMENT);
        checkSoloMinusculas.setToolTipText("Si está activo, rechaza textos que contengan letras mayúsculas.");
        panelLateral.add(checkSoloMinusculas);
        panelLateral.add(Box.createVerticalStrut(12));

        // Tarjeta informativa de la fórmula actual y el inverso modular
        etiquetaInfoMatematica = new JLabel(
                "<html><div style='padding:4px;'>"
                + "<b>Fórmulas (Mód 27):</b><br>"
                + "C = (a·M + b) mod 27<br>"
                + "M = a⁻¹·(C - b) mod 27<br>"
                + "<span style='color:#4F46E5;'>Para a=5 ➔ a⁻¹ = 11</span>"
                + "</div></html>"
        );
        etiquetaInfoMatematica.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        etiquetaInfoMatematica.setForeground(TEXTO_OSCURO);
        etiquetaInfoMatematica.setBackground(PASTEL_AZUL_INFO);
        etiquetaInfoMatematica.setOpaque(true);
        etiquetaInfoMatematica.setBorder(new LineBorder(COLOR_BORDE_SUAVE, 1, true));
        etiquetaInfoMatematica.setMaximumSize(new Dimension(Integer.MAX_VALUE, 82));
        etiquetaInfoMatematica.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(etiquetaInfoMatematica);
        panelLateral.add(Box.createVerticalStrut(16));

        // Sección de los 4 botones estilizados
        JLabel lblAcciones = new JLabel("OPERACIONES");
        lblAcciones.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblAcciones.setForeground(TEXTO_OSCURO);
        lblAcciones.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelLateral.add(lblAcciones);
        panelLateral.add(Box.createVerticalStrut(8));

        JButton btnCifrar = crearBotonEstilizado("Cifrar", COLOR_NEGRO_ELEGANTE, Color.WHITE);
        JButton btnDescifrar = crearBotonEstilizado("Descifrar", PASTEL_LAVANDA, COLOR_NEGRO_ELEGANTE);
        JButton btnUsarResultado = crearBotonEstilizado("Usar resultado como entrada", PASTEL_MENTA, COLOR_NEGRO_ELEGANTE);
        JButton btnLimpiar = crearBotonEstilizado("Limpiar", PASTEL_ROSA, COLOR_NEGRO_ELEGANTE);

        panelLateral.add(btnCifrar);
        panelLateral.add(Box.createVerticalStrut(8));
        panelLateral.add(btnDescifrar);
        panelLateral.add(Box.createVerticalStrut(8));
        panelLateral.add(btnUsarResultado);
        panelLateral.add(Box.createVerticalStrut(8));
        panelLateral.add(btnLimpiar);

        contenedorCentral.add(panelLateral, BorderLayout.WEST);

        // 3. PANEL DERECHO: Alfabeto de referencia + Entrada y Resultado
        JPanel panelTrabajo = new JPanel(new BorderLayout(10, 10));
        panelTrabajo.setOpaque(false);

        // Cinta superior con las posiciones del alfabeto módulo 27
        JLabel lblAlfabeto = new JLabel(
                "  Alfabeto (0-26):  a:0  b:1  c:2  d:3  e:4  f:5  g:6  h:7  i:8  j:9  k:10  l:11  m:12  n:13  [ñ:14]  o:15  p:16  q:17  r:18  s:19  t:20  u:21  v:22  w:23  x:24  y:25  z:26"
        );
        lblAlfabeto.setFont(new Font("Consolas", Font.PLAIN, 11));
        lblAlfabeto.setForeground(TEXTO_OSCURO);
        lblAlfabeto.setBackground(PASTEL_LAVANDA);
        lblAlfabeto.setOpaque(true);
        lblAlfabeto.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(221, 214, 254), 1, true),
                new EmptyBorder(6, 8, 6, 8)
        ));
        panelTrabajo.add(lblAlfabeto, BorderLayout.NORTH);

        // Cajas de Entrada y Resultado en tarjetas limpias
        JPanel panelCajas = new JPanel(new GridLayout(2, 1, 12, 12));
        panelCajas.setOpaque(false);

        areaEntrada = new JTextArea(6, 35);
        areaEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaEntrada.setForeground(COLOR_NEGRO_ELEGANTE);
        areaEntrada.setBackground(COLOR_BLANCO_TARJETA);
        areaEntrada.setLineWrap(true);
        areaEntrada.setWrapStyleWord(true);
        areaEntrada.setBorder(new EmptyBorder(8, 10, 8, 10));

        areaResultado = new JTextArea(6, 35);
        areaResultado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        areaResultado.setForeground(COLOR_NEGRO_ELEGANTE);
        areaResultado.setBackground(PASTEL_LAVANDA_FONDO);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setEditable(false); // Solo lectura
        areaResultado.setBorder(new EmptyBorder(8, 10, 8, 10));

        panelCajas.add(crearTarjetaTexto("TEXTO DE ENTRADA (Minúsculas a-z y ñ)", areaEntrada, COLOR_BLANCO_TARJETA));
        panelCajas.add(crearTarjetaTexto("RESULTADO DE LA OPERACIÓN (Solo lectura)", areaResultado, PASTEL_LAVANDA_FONDO));

        panelTrabajo.add(panelCajas, BorderLayout.CENTER);
        contenedorCentral.add(panelTrabajo, BorderLayout.CENTER);

        add(contenedorCentral, BorderLayout.CENTER);

        // 4. BARRA DE ESTADO INFERIOR
        JPanel panelEstado = new JPanel(new BorderLayout());
        panelEstado.setBorder(new EmptyBorder(0, 16, 12, 16));
        panelEstado.setOpaque(false);

        etiquetaEstado = new JLabel("  ●  Sistema listo. Claves iniciales cargadas: a = 5, b = 8 (Inverso a⁻¹ = 11 en módulo 27).");
        etiquetaEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        etiquetaEstado.setForeground(TEXTO_OSCURO);
        etiquetaEstado.setBackground(PASTEL_AZUL_INFO);
        etiquetaEstado.setOpaque(true);
        etiquetaEstado.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE_SUAVE, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        panelEstado.add(etiquetaEstado, BorderLayout.CENTER);
        add(panelEstado, BorderLayout.SOUTH);

        // 5. EVENTOS DE LOS BOTONES
        btnCifrar.addActionListener(e -> ejecutarOperacion(true));
        btnDescifrar.addActionListener(e -> ejecutarOperacion(false));

        btnUsarResultado.addActionListener(e -> {
            String resultadoActual = areaResultado.getText();
            if (resultadoActual.isEmpty()) {
                mostrarError("Todavía no hay ningún resultado generado para copiar a la entrada.");
                return;
            }
            areaEntrada.setText(resultadoActual);
            areaResultado.setText("");
            actualizarEstado("Se pasó el resultado al área de entrada para comprobar la operación inversa.", PASTEL_MENTA, new Color(6, 78, 59));
        });

        btnLimpiar.addActionListener(e -> {
            areaEntrada.setText("");
            areaResultado.setText("");
            actualizarEstado("Áreas de texto limpiadas correctamente. Se conservan las claves actuales.", PASTEL_AZUL_INFO, TEXTO_OSCURO);
        });
    }

    /**
     * Crea un campo de texto estilizado para las claves numéricas
     */
    private JTextField crearCampoClave(String valorInicial, Color colorFondo) {
        JTextField campo = new JTextField(valorInicial);
        campo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        campo.setForeground(COLOR_NEGRO_ELEGANTE);
        campo.setBackground(colorFondo);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE_SUAVE, 1, true),
                new EmptyBorder(4, 10, 4, 10)
        ));
        return campo;
    }

    /**
     * Envuelve cada JTextArea en una tarjeta blanca con encabezado propio y JScrollPane
     */
    private JPanel crearTarjetaTexto(String titulo, JTextArea area, Color colorFondo) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setBackground(COLOR_BLANCO_TARJETA);
        tarjeta.setBorder(new LineBorder(COLOR_BORDE_SUAVE, 1, true));

        JLabel lblEncabezado = new JLabel("   " + titulo);
        lblEncabezado.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEncabezado.setForeground(TEXTO_GRIS);
        lblEncabezado.setBackground(COLOR_BLANCO_TARJETA);
        lblEncabezado.setOpaque(true);
        lblEncabezado.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, COLOR_BORDE_SUAVE));
        lblEncabezado.setPreferredSize(new Dimension(0, 28));

        JScrollPane scroll = new JScrollPane(area);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getViewport().setBackground(colorFondo);

        tarjeta.add(lblEncabezado, BorderLayout.NORTH);
        tarjeta.add(scroll, BorderLayout.CENTER);
        return tarjeta;
    }

    /**
     * Crea botones con bordes redondeados y colores pasteles o negros sin usar librerías externas     
     * */
    private JButton crearBotonEstilizado(String texto, Color fondo, Color colorTexto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(fondo.darker());
                } else if (getModel().isRollover()) {
                    // Efecto sutil al pasar el mouse
                    g2.setColor(fondo.equals(COLOR_NEGRO_ELEGANTE) ? new Color(45, 45, 50) : fondo.darker());
                } else {
                    g2.setColor(fondo);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        boton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        return boton;
    }

    /**
     * Método que valida las entradas y ejecuta el cifrado o descifrado
     */
    private void ejecutarOperacion(boolean esCifrado) {
        // Limpiamos el resultado anterior para no mostrar datos viejos si algo falla
        areaResultado.setText("");

        String texto = areaEntrada.getText();
        if (texto.trim().isEmpty()) {
            mostrarError("El área de entrada no puede estar vacía. Por favor, ingresa un mensaje.");
            return;
        }

        // Validación opcional de solo minúsculas
        if (checkSoloMinusculas.isSelected() && CifradoAfin.contieneMayusculas(texto)) {
            mostrarError("El texto contiene letras mayúsculas. Recuerda que trabajamos únicamente con minúsculas (a-z y ñ).");
            return;
        }

        String textoA = campoA.getText().trim();
        String textoB = campoB.getText().trim();

        if (textoA.isEmpty() || textoB.isEmpty()) {
            mostrarError("Ambos campos de clave ('a' y 'b') son obligatorios.");
            return;
        }

        int a, b;
        try {
            a = Integer.parseInt(textoA);
            b = Integer.parseInt(textoB);
        } catch (NumberFormatException ex) {
            mostrarError("Las claves 'a' y 'b' deben ser números enteros válidos (sin letras ni decimales).");
            return;
        }

        try {
            String salida;
            int inv = CifradoAfin.inversoModular(a);

            // Actualizamos el cuadro matemático con los valores actuales
            etiquetaInfoMatematica.setText(
                    "<html><div style='padding:4px;'>"
                    + "<b>Fórmulas (Mód 27):</b><br>"
                    + "C = (" + a + "·M + " + b + ") mod 27<br>"
                    + "M = " + inv + "·(C - " + b + ") mod 27<br>"
                    + "<span style='color:#4F46E5;'>MCD(" + a + ",27)=1 ➔ a⁻¹ = " + inv + "</span>"
                    + "</div></html>"
            );

            if (esCifrado) {
                salida = CifradoAfin.cifrar(texto, a, b);
                actualizarEstado("Cifrado exitoso con a = " + a + ", b = " + b + " (Módulo 27).", PASTEL_MENTA, new Color(6, 78, 59));
            } else {
                salida = CifradoAfin.descifrar(texto, a, b);
                actualizarEstado("Descifrado exitoso usando inverso modular a⁻¹ = " + inv + " (Módulo 27).", PASTEL_LAVANDA, new Color(49, 46, 129));
            }
            areaResultado.setText(salida);
        } catch (IllegalArgumentException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void actualizarEstado(String mensaje, Color fondoPastel, Color colorTexto) {
        etiquetaEstado.setBackground(fondoPastel);
        etiquetaEstado.setForeground(colorTexto);
        etiquetaEstado.setText("  ●  " + mensaje);
    }

    private void mostrarError(String mensaje) {
        actualizarEstado("Error de validación: " + mensaje, PASTEL_ROSA, new Color(136, 19, 55));
        JOptionPane.showMessageDialog(this, mensaje, "Validación de Seguridad", JOptionPane.WARNING_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CifradoAfinGUI().setVisible(true);
        });
    }
}