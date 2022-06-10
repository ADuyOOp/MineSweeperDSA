package lib;

import model.*;
import java.util.Stack;

public class Buffer {
    private Stack<ListSquare> bufferList;


    public Buffer(){
        this.bufferList = new Stack<>();
    }

    public void add(ListSquare lSquare){
        this.bufferList.push(lSquare);
    }

    public ListSquare remove(){
        ListSquare lSquare = null;
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
