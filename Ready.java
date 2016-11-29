import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Ready
{
   private int num;
   
   public Ready()
   {
      num = Integer.parseInt(JOptionPane.showInputDialog("What is the number of the row: "));
      new StartGame(num);
   }
}