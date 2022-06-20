package model;

import java.util.Comparator;

public class OrdenaCirculoDistrito implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Persona p1 = (Persona) o1;
        Persona p2 = (Persona) o2;
        if(p1.getCirculo() < p2.getCirculo()) return -1;
        if(p1.getCirculo() > p2.getCirculo()) return 1;

        if(p1.getCirculo() == p2.getCirculo()) {
            if(p1.getDistrito() < p2.getDistrito()) {
                return -1;
            }
            if(p1.getDistrito() > p2.getDistrito()) {
                return 1;
            }
            return 1;
        }
        return 0;
    }
}
