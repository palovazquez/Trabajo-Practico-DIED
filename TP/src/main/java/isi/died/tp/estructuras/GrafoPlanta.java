package isi.died.tp.estructuras;
import java.util.ArrayList;
import java.util.List;
import isi.died.tp.dominio.Insumo;
import isi.died.tp.dominio.Planta;
import isi.died.tp.dominio.Stock;

	public class GrafoPlanta extends Grafo<Planta> {
		public void imprimirDistanciaAdyacentes(Planta inicial) {
			List<Planta> adyacentes = super.getAdyacentes(inicial);
			for(Planta unAdyacente: adyacentes) {
				Arista<Planta> camino = super.buscarArista(GrafoCaminos.grafo.getNodo(inicial), GrafoCaminos.grafo.getNodo(unAdyacente));
				System.out.println("camino de "+inicial.getNombre()+" a "+
				unAdyacente.getNombre()+ " tiene valor de "+ camino.getValor() );
			}
	}
		public Planta buscarPlanta(Planta inicial, Insumo i, Integer saltos) {
	        boolean encontrado=false;
	        List<Planta> listaPlantas;
	        listaPlantas = getAdyacentes(inicial);
	        for(int a=saltos;a>=0;a--) {
	            for (Planta p : listaPlantas) {
	                if (p.necesitaInsumo(i)) return p;
	            }
	            List<Planta> auxLista = new ArrayList<>();
	            for (Planta p : listaPlantas) {
	                auxLista.addAll(getAdyacentes(p));
	            }
	            listaPlantas = auxLista;
	        }

	        return null;

	        }
		public Planta buscarPlanta(Planta inicial, Insumo i) { 
			
			if(inicial.necesitaInsumo(i))
				return inicial;
			else {
				Vertice<Planta> inicial1= new Vertice<Planta> (inicial);
				List<Planta> plantasOrd = this.recorridoAnchura(inicial1);
				for(int c=0;c<plantasOrd.size();c++) {
					if(plantasOrd.get(c).necesitaInsumo(i)) return plantasOrd.get(c);
				}
			}	
			return null;
		}
	 // c
		public Planta buscarPlanta(Insumo i) {
			int max=0;
			Planta planta=null;
			Vertice<Planta> inicial=this.vertices.get(0);
			List<Planta> plantasOrd = this.recorridoAnchura(inicial);
			for(int c=0;c<plantasOrd.size();c++) {
				if(plantasOrd.get(c).necesitaInsumo(i)) {
					ArrayList<Stock> listastocks= plantasOrd.get(c).getStocks();
					for(int cont=0; cont<listastocks.size(); cont++) {
						if(listastocks.get(cont).getInsumo()==i) {
							if(((listastocks.get(cont).getPuntoPedido())-(listastocks.get(cont).getCantidad()))>max){
								max=((listastocks.get(cont).getPuntoPedido())-(listastocks.get(cont).getCantidad()));
								planta= plantasOrd.get(c);
							}
						}
					}
				}
			}
		return planta;	
		
		}
}
