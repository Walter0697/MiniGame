import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.Random;

public class StartGame
{
   private JFrame frame;
   private JPanel panel;
   private int n;
   
   private JButton[][] button;
   private int[][] sit;
   private int SizeOfButton;
   private int Size = 500;
   
   private ImageIcon on = new ImageIcon("lighton.png");
   private ImageIcon off = new ImageIcon("lightoff.png");
   
   public StartGame(int num)
   {
      n = num;
      frame = new JFrame();
      panel = new JPanel();
      
      frame.setTitle("Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(Size, Size);
      
      panel.setLayout(new GridLayout(num, num));
      
      SizeOfButton = (int)(Size / num );
      
      button = new JButton[num][num];
      sit = new int[num][num];
      Random randomNumbers = new Random();
   
      for (int i = 0; i < button.length; i++)
      {
         for (int j = 0; j < button.length; j++)
         {
            sit[i][j] = randomNumbers.nextInt(2);
            button[i][j] = new JButton();
            button[i][j].setPreferredSize(new Dimension(SizeOfButton, SizeOfButton));
            button[i][j].addActionListener(new ButtonListener());
            
            panel.add(button[i][j]);
         }
      }
      CheckUpdate();
      
      frame.add(panel);
      frame.setVisible(true);
   }
   
   public void CheckUpdate()
   {
      for (int i = 0; i < button.length; i++)
      {
         for (int j = 0; j < button.length; j++)
         { 
            if (sit[i][j] == 0)
            {
               button[i][j].setBackground(Color.BLACK);
            }
            else if (sit[i][j] == 1)
            {
               button[i][j].setBackground(Color.WHITE);
            }
         }
      }
   }
   
   public class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         for (int i = 0; i < button.length; i++)
         {
            for (int j = 0; j < button.length; j++)
            {
               if (e.getSource() == button[i][j])
               {
                  ButtonClicked(i, j);
               }
            }
         }
      }
   }
   
   public void ButtonClicked(int i, int j)
   {
      sit[i][j] = ChangeColor(sit[i][j]);
      if (i == 0)
      {
         if (j == 0)
         {
            sit[i+1][j] = ChangeColor(sit[i+1][j]);
            sit[i][j+1] = ChangeColor(sit[i][j+1]);
         }
         else if (j == n-1)
         {
            sit[i+1][j] = ChangeColor(sit[i+1][j]);
            sit[i][j-1] = ChangeColor(sit[i][j-1]);
         }
         else 
         {
            sit[i+1][j] = ChangeColor(sit[i+1][j]);
            sit[i][j+1] = ChangeColor(sit[i][j+1]);
            sit[i][j-1] = ChangeColor(sit[i][j-1]);
         }
      }
      else if (j == 0)
      {
         if (i == n-1)
         {
            sit[i-1][j] = ChangeColor(sit[i-1][j]);
            sit[i][j+1] = ChangeColor(sit[i][j+1]);
         }
         else 
         {
            sit[i-1][j] = ChangeColor(sit[i-1][j]);
            sit[i+1][j] = ChangeColor(sit[i+1][j]);
            sit[i][j+1] = ChangeColor(sit[i][j+1]);
         }
      }
      else if (i == n-1)
      {
         if (j == n-1)
         {
            sit[i-1][j] = ChangeColor(sit[i-1][j]);
            sit[i][j-1] = ChangeColor(sit[i][j-1]);
         }
         else
         {
            sit[i-1][j] = ChangeColor(sit[i-1][j]);
            sit[i][j-1] = ChangeColor(sit[i][j-1]);
            sit[i][j+1] = ChangeColor(sit[i][j+1]);
         }
      }
      else if (j == n-1)
      {
         sit[i+1][j] = ChangeColor(sit[i+1][j]);
         sit[i-1][j] = ChangeColor(sit[i-1][j]);
         sit[i][j-1] = ChangeColor(sit[i][j-1]);
      }
      else
      {
         sit[i+1][j] = ChangeColor(sit[i+1][j]);
         sit[i-1][j] = ChangeColor(sit[i-1][j]);
         sit[i][j+1] = ChangeColor(sit[i][j+1]);
         sit[i][j-1] = ChangeColor(sit[i][j-1]);
      }
      CheckUpdate();
      CheckWin();
   }
   
   public int ChangeColor(int number)
   {
      int output = -1;
         if (number == 0)
         {
            output = 1;
         }
         else if (number == 1)
         {
            output = 0;
         }
      return output;
   }
   
   public void CheckWin()
   {
      boolean isWhite = true;
      boolean isBlack = true;
      
      for (int i = 0; i < sit.length; i++)
      {
         for (int j = 0; j < sit.length; j++)
         {
            if (sit[i][j] == 0)
            {
               isWhite = false;
            }
            if (sit[i][j] == 1)
            {
               isBlack = false;
            }
         }
      }
      
      if (isBlack)
      {
         JOptionPane.showMessageDialog(null, "You got all Black!");
      }
      else if (isWhite)
      {
         JOptionPane.showMessageDialog(null, "You got all White!");
      }
   }
}