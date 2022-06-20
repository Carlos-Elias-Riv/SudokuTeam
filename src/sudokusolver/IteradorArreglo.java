package sudokusolver;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorArreglo<T> implements Iterator<T> {
    private int actual;
    private int total;
    private T[] coleccion;

    public IteradorArreglo(T[] datos, int total) {
        actual = 0;
        coleccion = datos;
        this.total = total;
    }

    @Override
    public boolean hasNext() {
        return actual<total;
    }

    @Override
    public T next() {
        T resp;
        if(hasNext()){
            resp = coleccion[actual];
            actual +=1;
            return resp;
        }
        else
            throw new NoSuchElementException();

    }
}