package lib;

import model.*;

public class BufferInvoker implements Command{
    private Buffer buffer, temp;    


    public BufferInvoker(){
        this.buffer = new Buffer();
        this.temp = new Buffer();            
    }    

    public Buffer getBuffer(){
        return this.buffer;
    }

    @Override
    //public ListSquare undo(ListSquare lSquare) {
    public ListSquare undo(ListSquare lSquare) {

        // TODO Auto-generated method stub
        if(!this.buffer.isEmpty()){
            this.temp.add(lSquare);
            lSquare = this.buffer.remove();
        }
        return lSquare;

    }

    @Override
    public ListSquare redo(ListSquare lSquare) {
        // TODO Auto-generated method stub
        if(!this.temp.isEmpty()){
            this.buffer.add(lSquare);
            lSquare = this.temp.remove();
        }
        return lSquare;
    }
    

    @Override
    public void add(ListSquare lSquare) {
        // TODO Auto-generated method stub
        this.buffer.add(lSquare);  
        if(!this.temp.isEmpty()) {
            temp.clear();
        }
    }
}
