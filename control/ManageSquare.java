package control;
import model.*;
import view.*;

public class ManageSquare {
  //use for UNDO
   int bufferSquareClosed;
   ListSquare bufferSquare;

   ListSquare listSquare;
   BoardPanel boardPanel;
   ControlPanel controlPanel;

  public ManageSquare(){
     listSquare = new ListSquare();

     //use for UNDO
     bufferSquare = new ListSquare();
  }	

  public ListSquare getListSquare(){
    return this.listSquare;
  }

  public void setBoardPanel(BoardPanel boardPanel){
    this.boardPanel = boardPanel;
  }

  public void setControlPanel(ControlPanel controlPanel){
    this.controlPanel = controlPanel;
  }

  //use for UNDO
  private void backupList(){
    int rows =  listSquare.getRows();
    int cols =  listSquare.getCols();
    for(int i=0; i<rows; i++)
      for (int j=0; j<cols; j++){
        
        Square lSquare = listSquare.getSquare(i, j);
        Square bSquare = bufferSquare.getSquare(i, j);
        bSquare.assignment(lSquare);


      }          
  }
  //use for UNDO
  private void restoreList(){
    int rows =  listSquare.getRows();
    int cols =  listSquare.getCols();
    for(int i=0; i<rows; i++)
      for (int j=0; j<cols; j++){
        Square square = bufferSquare.getSquare(i, j);
        listSquare.getSquare(i, j).assignment(square);
      }          
  }
   
  //from MainFrame
  public void play(int x, int y) {
    //use for UNDO
    this.backupList();
    bufferSquareClosed  = boardPanel.getNumSquareClosed();

    boolean conti = listSquare.play(x, y);   
    if (!conti) { listSquare.showAllSquares(); }
    boardPanel.updateBoard(); 
      
    // cập nhật số ô chưa mở vào controlPanel
    int numSquareClosed = boardPanel.getNumSquareClosed();
    controlPanel.updateStatus(numSquareClosed);
  }
  
  //from MainFrame
  public void target(int x, int y) {
    //use for UNDO
    this.backupList();

    listSquare.target(x, y);

    boardPanel.updateBoard();
  }
  
  //from MainFrame
  public void restart() {
    listSquare = new ListSquare();
    bufferSquare = new ListSquare();

    boardPanel.updateBoard();
  }  

  // methods called from updateBoard()
  public boolean itSquareOpen(int i, int j){
    return this.listSquare.SquareOpen(i,j);
  }

  public boolean itSquareTarget(int i, int j){
    return this.listSquare.SquareTarget(i,j);
  }

  public boolean itSquareMine(int i, int j){
    return this.listSquare.SquareMine(i,j);
  }

  public int itArroundMines(int i, int j){
    return this.listSquare.ArroundMines(i,j);
  }
  

  //use for UNDO
  public void undo(){
    this.restoreList();
    boardPanel.setNumSquareClosed(this.bufferSquareClosed); 
    boardPanel.updateBoard();

    controlPanel.updateStatus(this.bufferSquareClosed);
  }
}
