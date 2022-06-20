package sudokusolver;
import java.util.Iterator;

public class ConjuntoA<T> implements ConjuntoADT<T>{
    private int cardinalidad;
    private T[] conjunto;
    private final int MAX = 20;

    public ConjuntoA(){
        conjunto = (T[]) new Object[MAX];
        cardinalidad = 0;
    }

    public ConjuntoA(int max){
        conjunto = (T[]) new Object[max];
        cardinalidad = 0;
    }

    @Override
    public boolean agrega(T dato) {
        boolean respuesta;

        respuesta = !contiene(dato);
        if(respuesta){
            if(cardinalidad == conjunto.length){
                expande();
            }

            conjunto[cardinalidad] = dato;
            cardinalidad ++;


        }
        return respuesta;
    }

    private void expande(){
        T[] nuevo = (T[]) new Object[conjunto.length * 2];

        if(conjunto.length == 0) // el caso donde el conjunto esta vacio y la longitud es cero
            nuevo = (T[]) new Object[2];


        for(int i =0; i< cardinalidad; i++)
            nuevo[i] = conjunto[i];

        conjunto = nuevo;
    }


    @Override
    public boolean contiene(T dato) {
        return contiene(dato, 0) >= 0;
    }

    private int contiene(T dato, int pos){
        if(pos == cardinalidad)
            return -1; // estado base de fracaso
        else
        if(conjunto[pos].equals(dato)) // estado de éxito
            return pos;
        else
            return contiene(dato, pos+1); // recursivo de que sigo avanzando en mi busqueda
    }

    @Override
    public boolean estaVacio() {
        return cardinalidad == 0;
    }

    @Override
    public ConjuntoADT<T> union(ConjuntoADT<T> otro) {
        ConjuntoA<T> union = new ConjuntoA(cardinalidad);

        for(int i =0; i< cardinalidad; i++) // puede ser con este o con su mismo iterador o tambien puede ser con el agrega
            union.conjunto[i] = this.conjunto[i];

        union.cardinalidad = this.cardinalidad;

        Iterator<T> iterador = otro.iterator();

        while(iterador.hasNext())
            union.agrega(iterador.next());


        return union;
    }

    private void interseccion(Iterator <T> iterador, ConjuntoADT otro, ConjuntoA inter, T dato) {

        if (otro.contiene(dato)) {
            inter.conjunto[inter.cardinalidad] = dato;
            inter.cardinalidad+=1;
        }

        if (iterador.hasNext()) {
            interseccion(iterador, otro, inter, iterador.next());
        }

    }

    public ConjuntoADT<T> interseccion(ConjuntoADT<T> otro) {
        ConjuntoA<T> inter;

        if (this.cardinalidad < otro.getCardinalidad()) {
            inter = new ConjuntoA(this.cardinalidad);

            T dato = null;

            Iterator it = this.iterator();

            if(it.hasNext()) {
                dato = (T) it.next();
            }

            interseccion(it, otro, inter, dato);


        }
        else {
            inter = new ConjuntoA(otro.getCardinalidad());

            T dato = null;
            Iterator it = otro.iterator();
            if(it.hasNext()) {
                dato = (T) it.next();
            }

            interseccion(it, this, inter, dato);

        }




        return inter;
    }


    private void recorreIzquierda(int pos, int index){
        if(pos < index){
            conjunto[pos] = conjunto[pos +1];
            recorreIzquierda(pos++, index);
        }
    }

    public T remove(T eliminar) {
        T eliminado = null;
        int pos = contiene(eliminar, 0);
        if (pos>= 0) {
            T dato;
            dato = conjunto[pos];
            conjunto[pos] = conjunto[cardinalidad-1];
            cardinalidad -=1;
            eliminado = dato;
        }
        else
            throw new RuntimeException("no puedo eliminar un dato que no tengo");

        return eliminado;
    }

    public ConjuntoADT<T> diferencia(ConjuntoADT<T> otro) { // A\B
        ConjuntoA<T> dif = new ConjuntoA(this.cardinalidad);

        diferencia(0, otro, dif);

        return dif;
    }

    private void diferencia(int pos, ConjuntoADT<T> otro, ConjuntoA<T> conjDif) {
        if(pos < cardinalidad){
            if(!otro.contiene(this.conjunto[pos])) {
                conjDif.conjunto[conjDif.cardinalidad] = this.conjunto[pos];
                conjDif.cardinalidad++;
            }

            diferencia(pos+1, otro, conjDif);

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IteradorArreglo(conjunto, cardinalidad);
    }

    @Override
    public int getCardinalidad() {
        return cardinalidad;
    }

    public String toString() {
        StringBuilder bobthebuilder = new StringBuilder();

        for (int i = 0; i < cardinalidad; i++) {
            // if(conjunto[i] != null)
            bobthebuilder.append(conjunto[i]).append("\n");
        }
        return bobthebuilder.toString();
    }

    public boolean equals(Object otro) {
        boolean resp = false;
        ConjuntoA a;
        if (otro != null && otro instanceof ConjuntoA) {
            a = (ConjuntoA) otro;

            if(cardinalidad == a.cardinalidad) {
                Iterator itotro = a.iterator();

                if(itotro.hasNext())
                    resp = equals(itotro, 0, iterator().next());
                else
                    resp = true; // caso donde ambos conjuntos son vacios
            }


        }


        return resp;
    }

    private boolean equals(Iterator itotro, int pos, T dato) {
        if(pos < cardinalidad)
            if(conjunto[pos].equals(dato)){
                if(itotro.hasNext())
                    return equals(itotro, 0, (T) itotro.next());
                else
                    return true;
            }
            else
                return equals(itotro, pos+1, dato);
        else
            return false;

    }


}

