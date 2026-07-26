import java.util.*;
/**
 * BoundedStack is a stack with a fixed capacity. It can hold a limited number of elements, and once the capacity is reached, no more elements can be added until some are removed.
 */
public class BoundedStack{

    private List<String> elements ; // private final String[] elements ;
    private final int capacity;

    //AF(elements,capacity)
    //RI
    //-
    //-

    /**
     * Constructs a BoundedStack with the specified capacity.
     *
     * @param capacity the maximum number of elements that can be stored in the stack
     */
    public BoundedStack(int capacity) {
        this.elements = new ArrayList<>();
        this.capacity = capacity;
    }

    /**
     * 
     * @param s
     */
    public void push(String s){



    }
}