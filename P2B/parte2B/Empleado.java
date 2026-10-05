package parte2B;

public class Empleado {

	
	public enum TipoEmpleado{Vendedor , Encargado};
	
	public float calculoNominaBruta(TipoEmpleado tipo, float ventasMes, float horasExtra) {
        int salarioBase = 0;

        if (tipo == TipoEmpleado.Vendedor) {
            salarioBase = 2000;
        } else if (tipo == TipoEmpleado.Encargado) {
            salarioBase = 2500;
        }else {
        	return -1;
        }

        float prima = 0;
        if (ventasMes >= 1500) {
            prima = 200;
        } else if (ventasMes >= 1000) {
            prima = 100;
        }

        float pagoHorasExtra = horasExtra * 30;

        return salarioBase + prima + pagoHorasExtra;
    }

    public float calculoNominaNeta(float nominaBruta) {
        float retencion = 0;

        if (nominaBruta >= 2500) {
            retencion = 0.18f;
        } else if (nominaBruta >= 2100) {
            retencion = 0.15f;
        } else {
            retencion = 0;
        }

        return (float)(nominaBruta * (1-retencion));
    }
}
