package isi.died.tp.estructuras;

import java.util.Comparator;
import isi.died.tp.dominio.Planta;

public class OrdenarPorPageRank implements Comparator<Planta>  {
	
	public OrdenarPorPageRank() {}
	
    public int compare(Planta p1, Planta p2){
        return(p1.getCantidadCaminos()-(p2.getCantidadCaminos()));
    }

}
