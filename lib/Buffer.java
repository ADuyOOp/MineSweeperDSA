package lib;

import java.util.Stack;

public class Buffer {
    private Stack<Object> bufferList;


    public Buffer(){
        this.bufferList = new Stack<>();
    }

    public void add(Object lSquare){
        this.bufferList.push(lSquare);
    }

    public Object remove(){
        Object lSquare = null;
        if(!this.bufferList.isEmpty()){
            lSquare =  this.bufferList.pop();
        }        
        return lSquare;
    }

    public boolean isEmpty(){
        return bufferList.isEmpty();
    }

    public void clear(){
        if(!this.bufferList.isEmpty()){
            bufferList.clear();
        }
    }
}
