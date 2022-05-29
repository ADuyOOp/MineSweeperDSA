package control;
import model.*;
import view.*;

public class ManageSquare {
   ListSquare listSquare;
   BoardPanel boardPanel;
   ControlPanel controlPanel;

  public ManageSquare(){
     listSquare = new ListSquare();
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

   
  //from MainFrame
  public void play(int x, int y) {
    boolean check = listSquare.play(x, y);
    if (!check) { listSquare.showAllSquares(); }
    boardPanel.updateBoard(); 
      
    // cập nhật số ô chưa mở vào controlPanel
    int numSquareClosed = boardPanel.getNumSquareClosed();
    controlPanel.updateStatus(numSquareClosed);
  }
  
  //from MainFrame
  public void target(int x, int y) {
    listSquare.target(x, y);
    boardPanel.updateBoard();
  }
  
  //from MainFrame
  public void restart() {
    listSquare = new ListSquare();
    boardPanel.updateBoard();
  }  
}
