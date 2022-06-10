package lib;

public interface Command<T>{
    public T undo(T lSquare);
    public T redo(T lSquare);

    public void add(T lSquare);

}