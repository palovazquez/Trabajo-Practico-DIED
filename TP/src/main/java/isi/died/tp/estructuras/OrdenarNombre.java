package isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.Comparator;

import isi.died.tp.dominio.Insumo;

public class OrdenarNombre implements Comparator<Insumo> {
    public int valor;
    public OrdenarNombre(int i){
        valor = i;
    }
    public int compare(Insumo I1, Insumo I2){
        return(valor*(I1.getDescripcion().compareTo(I2.getDescripcion())));
    }
}
