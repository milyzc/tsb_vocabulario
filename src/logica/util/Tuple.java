/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.util;

/**
 *
 * @author MilagrosZea
 * @param X
 * @param Y
 */
public class Tuple<X ,Y> implements Comparable<Tuple>{

    public final X x;
    public final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Tuple t){if(this.y == t.y)return 0;return-1;
}
}
