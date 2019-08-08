package isi.died.tp.dominio;

public class Liquido extends Insumo {
	
	private float densidad;
	
	public Liquido() {
		super.setUnidadDeMedida(Unidad.LITRO);
	}
	
	public float getDensidad() {
		return densidad;
	}

	public void setDensidad(float densidad) {
		this.densidad = densidad;
	}

	public float calcularPeso(int litrosSolicitados) {
		return densidad*(litrosSolicitados/1000);
	}
	
	
}
