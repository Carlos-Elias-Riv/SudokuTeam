package sudokusolver;

import java.util.Iterator;

public interface ConjuntoADT <T> extends Iterable<T>{
    public boolean agrega(T dato);
    public boolean contiene(T dato);
    public boolean estaVacio();
    public ConjuntoADT<T> union(ConjuntoADT<T> otro);
    public Iterator<T> iterator();
    public String toString();
    public int getCardinalidad();
    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro);
    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro);
}
