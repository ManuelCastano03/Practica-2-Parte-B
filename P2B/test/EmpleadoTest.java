package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import parte2B.Empleado;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmpleadoTest {

    private Empleado empleado;
    private static final float ERR_permitido = 0.01f;

    @BeforeEach
    public void setUp() {
        empleado = new Empleado();
    }

    // =========================================================================
    // CASUÍSTICAS: calculoNominaBruta
    // =========================================================================
    @Nested
    @DisplayName("Pruebas para calculoNominaBruta")
    class PruebasNominaBruta {

        @Test
        @DisplayName("1. Que sea vendedor (salario base 2000)")
        public void testEsVendedor() {
            // Vendedor, 0 ventas (sin prima), 0 horas extra -> 2000
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Vendedor, 0.0f, 0.0f);
            assertEquals(2000.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("2. Que sea encargado (salario base 2500)")
        public void testEsEncargado() {
            // Encargado, 0 ventas (sin prima), 0 horas extra -> 2500
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Encargado, 0.0f, 0.0f);
            assertEquals(2500.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("3. Que el tipo no sea válido / nulo (devuelve -1)")
        public void testTipoInvalidoONulo() {
            float resultado = empleado.calculoNominaBruta(null, 500.0f, 0.0f);
            assertEquals(-1.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("4. Venta al mes menor que 1000 (sin prima)")
        public void testVentasMenor1000() {
            // Vendedor (2000) + ventas 800 (prima 0) + 0 horas -> 2000
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Vendedor, 800.0f, 0.0f);
            assertEquals(2000.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("5. Venta al mes entre 1000 y 1500 (prima 100)")
        public void testVentasEntre1000Y1500() {
            // Vendedor (2000) + ventas 1200 (prima 100) + 0 horas -> 2100
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Vendedor, 1200.0f, 0.0f);
            assertEquals(2100.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("6. Venta al mes mayor o igual que 1500 (prima 200)")
        public void testVentasMayorOIgual1500() {
            // Vendedor (2000) + ventas 1600 (prima 200) + 0 horas -> 2200
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Vendedor, 1600.0f, 0.0f);
            assertEquals(2200.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("7. Venta al mes negativa (no aplica prima)")
        public void testVentasNegativas() {
            // Vendedor (2000) + ventas -100 (prima 0) + 0 horas -> 2000
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Vendedor, -100.0f, 0.0f);
            assertEquals(2000.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("8. Que no tenga horas extra (0 horas = 0€)")
        public void testSinHorasExtra() {
            // Encargado (2500) + 0 ventas (0) + 0 horas -> 2500
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Encargado, 0.0f, 0.0f);
            assertEquals(2500.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("9. Que tenga horas extra (ej. 2 horas * 30€ = 60€)")
        public void testConHorasExtra() {
            // Vendedor (2000) + 0 ventas (0) + 2 horas (60) -> 2060
            float resultado = empleado.calculoNominaBruta(Empleado.TipoEmpleado.Vendedor, 0.0f, 2.0f);
            assertEquals(2060.0f, resultado, ERR_permitido);
        }
    }

    // =========================================================================
    // CASUÍSTICAS: calculoNominaNeta
    // =========================================================================
    @Nested
    @DisplayName("Pruebas para calculoNominaNeta")
    class PruebasNominaNeta {

        @Test
        @DisplayName("10. Nómina bruta menor que 2100 (0% retención)")
        public void testNominaBrutaMenor2100() {
            // 2000 * (1 - 0) = 2000
            float resultado = empleado.calculoNominaNeta(2000.0f);
            assertEquals(2000.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("11. Nómina bruta entre 2100 y 2500 (15% retención)")
        public void testNominaBrutaEntre2100Y2500() {
            // 2200 * (1 - 0.15) = 1870
            float resultado = empleado.calculoNominaNeta(2200.0f);
            assertEquals(1870.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("12. Nómina bruta mayor o igual que 2500 (18% retención)")
        public void testNominaBrutaMayorOIgual2500() {
            // 2600 * (1 - 0.18) = 2132
            float resultado = empleado.calculoNominaNeta(2600.0f);
            assertEquals(2132.0f, resultado, ERR_permitido);
        }

        @Test
        @DisplayName("13. Nómina bruta negativa (entra en tramo < 2100, 0% retención)")
        public void testNominaBrutaNegativa() {
            // -500 * (1 - 0) = -500
            float resultado = empleado.calculoNominaNeta(-500.0f);
            assertEquals(-500.0f, resultado, ERR_permitido);
        }
    }
}