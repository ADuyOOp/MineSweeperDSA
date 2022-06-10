package view;
import control.*;
import model.*;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;

 
public class ControlPanel extends JPanel{
  private JLabel lbNumSquareClosed;
  private JLabel lbNotify;
  private JButton btnRestart;

  //use for UNDO
  private JButton btnUndo;

  //use for REDO
  private JButton btnRedo;

  private ManageSquare manageSquare;
  private int numRows, numCols, numMines;
  
  public ControlPanel(ManageSquare manageSquare) {
    this.manageSquare = manageSquare;
    ListSquare listSquare = this.manageSquare.getListSquare();
    numRows = listSquare.getRows();
    numCols = listSquare.getCols();
    numMines = listSquare.getMines();

    initComp();
    addComp();
    addEvent();

    //use for UNDO/REDO
    addDoEvent();
  } 
  
  public void initComp() {
    setLayout(null);
  } 
  
  public void addComp() {
    Font font = new Font("VNI", Font.PLAIN, 12);
 
    lbNumSquareClosed = new JLabel();
    lbNumSquareClosed.setFont(font);
    lbNumSquareClosed.setText("Number cell closed: " + numRows * numCols);
    lbNumSquareClosed.setBounds(10, 10, 150, 40);
    add(lbNumSquareClosed);
 
    lbNotify = new JLabel();
    lbNotify.setFont(font);
    lbNotify.setBounds(200, 10, 100, 40);
    lbNotify.setText("");
    add(lbNotify);

    //use for UNDO
    btnUndo = new JButton();
    btnUndo.setFont(font);
    btnUndo.setText("Undo");
    btnUndo.setBounds(350, 10, 100, 40);
    add(btnUndo);

    //use for REDO
    btnRedo = new JButton();
    btnRedo.setFont(font);
    btnRedo.setText("Redo");
    btnRedo.setBounds(470, 10, 100, 40);
    add(btnRedo);
 
 
    btnRestart = new JButton();
    btnRestart.setFont(font);    
    btnRestart.setText("New Game");
    btnRestart.setBounds(590, 10, 100, 40);
    add(btnRestart);    
  } 
  
  public void addEvent() {
    btnRestart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        manageSquare.restart();
        lbNumSquareClosed.setText("Number cell closed: " + numRows * numCols);
        lbNotify.setText("");
      }
    });
  }  

  //use for UNDO/REDO
  private void addDoEvent() {
    btnUndo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        manageSquare.undo();
        lbNotify.setText("");
      }
    });

    btnRedo.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        manageSquare.redo();
        lbNotify.setText("");
      }
    });
  }
 
  public void updateStatus(int numSquareClosed) {
    lbNumSquareClosed.setText("Number cell closed: " + numSquareClosed);
    if (numSquareClosed == this.numMines) {
      lbNotify.setText("YOU WIN");
      lbNotify.setForeground(Color.blue);
    } else if (numSquareClosed == 0) {
      lbNotify.setText("YOU LOST");
      lbNotify.setForeground(Color.red);
    }
  }

  public JButton getBtnUndo(){
    return this.btnUndo;
  }
  public JButton getBtnRedo(){
    return this.btnRedo;
  }
}