package lib;

 import model.ListSquare;

public interface Command{

    public ListSquare undo(ListSquare lSquare);
    public ListSquare redo(ListSquare lSquare);

    public void add(ListSquare lSquare);

}