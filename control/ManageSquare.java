package control;

import lib.*;
import model.*;
import view.*;

public class ManageSquare {

  //use for UNDO
   int bufferSquareClosed;
   
  //use for PATTERN 
   BufferInvoker bInvoker;

   ListSquare listSquare;
   BoardPanel boardPanel;
   ControlPanel controlPanel;

  public ManageSquare(){
     listSquare = new ListSquare();

     //use for PATTERN
     this.bInvoker = new BufferInvoker();

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

  
  //use for UNDO - PATTERN
  private void backupList(){    
    
    ListSquare tempSquare = new ListSquare();
    int rows =  listSquare.getRows();
    int cols =  listSquare.getCols();
    for(int i=0; i<rows; i++)
      for (int j=0; j<cols; j++){
        Square lSquare = listSquare.getSquare(i, j);
        Square tSquare = tempSquare.getSquare(i, j);
        tSquare.assignment(lSquare);        //gan(lSquare,bSquare)
      }   

    //use for PATTERN  
    this.bInvoker.add(tempSquare); 
  }

  //use for UNDO - PATTERN
  private void restoreList(){
    this.listSquare = (ListSquare)this.bInvoker.undo(this.listSquare);
  }
   
  //from MainFrame
  public void play(int x, int y) {
    this.controlPanel.getBtnUndo().setEnabled(true);

    //use for UNDO - PATTERN
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

  //use for PATTERN
  public void redo(){
    this.listSquare = (ListSquare)this.bInvoker.redo(this.listSquare);

    boardPanel.setNumSquareClosed(this.bufferSquareClosed); 
    boardPanel.updateBoard();

    controlPanel.updateStatus(this.bufferSquareClosed);
  }
}
