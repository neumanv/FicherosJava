package Actividad;

import java.util.Comparator;

public class Comparador implements Comparator<Persona> {

    @Override
    public int compare(Persona o1, Persona o2) {

        int num;

        num = o1.nombre.compareTo(o2.nombre);

        return num;
    }
}
