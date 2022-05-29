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

  private ManageSquare manageSquare;
  private int numRows, numCols, numMines;
  
  public ControlPanel(ManageSquare manageSquare) {
    this.manageSquare = manageSquare;
    ListSquare listSquare = this.manageSquare.getListSquare();
    numRows = listSquare.getArrSquare().length;
    numCols = listSquare.getArrSquare()[0].length;
    numMines = listSquare.getMines();

    initComp();
    addComp();
    addEvent();
  } 
  
  public void initComp() {
    setLayout(null);
  } 
  
  public void addComp() {
    Font font = new Font("VNI", Font.PLAIN, 20);
 
    lbNumSquareClosed = new JLabel();
    lbNumSquareClosed.setFont(font);
    lbNumSquareClosed.setText("Số ô chưa mở: " + numRows * numCols);
    lbNumSquareClosed.setBounds(10, 10, 250, 40);
    add(lbNumSquareClosed);
 
    lbNotify = new JLabel();
    lbNotify.setFont(font);
    lbNotify.setBounds(270, 10, 200, 40);
    add(lbNotify);
 
    btnRestart = new JButton();
    btnRestart.setFont(font);
    btnRestart.setText("Chơi lại");
    btnRestart.setBounds(490, 10, 200, 40);
    add(btnRestart);
  } 
  
  public void addEvent() {
    btnRestart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        manageSquare.restart();
        lbNumSquareClosed.setText("Số ô chưa mở: " + numRows * numCols);
        lbNotify.setText("");
      }
    });
  }  
 
  public void updateStatus(int numSquareClosed) {
    lbNumSquareClosed.setText("Số ô chưa mở: " + numSquareClosed);
    if (numSquareClosed == this.numMines) {
      lbNotify.setText("THẮNG");
      lbNotify.setForeground(Color.blue);
    } else if (numSquareClosed == 0) {
      lbNotify.setText("THUA");
      lbNotify.setForeground(Color.red);
    }
  }
}