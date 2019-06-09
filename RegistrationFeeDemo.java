//*********************************
//   Programmer: Tyler Barnard
//   CTP 150 - section 875
//   Lab 8
//   
//*********************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

class RegistrationFee extends JFrame
{
   //constants for price of registration and conferences
   private final double GENERAL_FEE = 895;
   private final double STUDENT_FEE = 495;
   private final double OPENING_FEE = 30;
   private final double E_COMMERCE = 295;
   private final double FUTURE_WEB = 295;
   private final double ADVANCED_JAVA = 395;
   private final double NETWORK_SECURITY = 395;
   
   private JPanel mainPanel;        //main panel to hold all panels
   private JPanel titlePanel;       //panel for title
   private JPanel workPanel;        //panel for workshop lists
   private JPanel typePanel;        //panel for general and student registration
   private JPanel keyPanel;         //panel for keynote checkbox
   private JPanel calcPanel;        //panel for calculate, clear, and exit buttons
   
   private JRadioButton general;    //radio button for general registration
   private JRadioButton student;    //radio button for student registration
   private ButtonGroup type;        //button group for radiobuttons
   private JCheckBox keynote;       //checkbox for keynote speech  
   private JList workshopList;      //list for possible workshops
   private JScrollPane scrollPane1; //scroll panel for the list
   private JButton calcButton;      //button for calculate costs
   private JButton clearButton;     //button for clear selection
   private JButton exitButton;      //button for exit program
   
   private String[] workshopChoices = {"Introduction to E-Commerce", "The Future of the Web",
                                       "Advanced Java Programming", "Network Security"};
   
    /**
      Constructor
   */
   
   public RegistrationFee()
   {
      JFrame frame = new JFrame();
      
      // Set this window's title.
      frame.setTitle("Conference Registration System");

      // Set the size of this window.
      frame.setSize(600, 300);
 
      // Specify what happens when the close button is clicked.
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //build the panel
      buildMainPanel();
      
      //add mainPanel to frame
      frame.add(mainPanel);
      
      // Display the window.
      frame.setVisible(true);
      
   }//end constructor

   //method to build each individual panel, and combine them onto one main panel
   private void buildMainPanel()
   {
      //create main panel to hold other panels
      mainPanel = new JPanel();
      
      mainPanel.setLayout(null);
      mainPanel.setSize(600, 300);
      
      //build panels
      buildTitlePanel();
      buildWorkPanel();
      buildTypePanel();
      buildKeyPanel();
      buildCalcPanel();
      
      //Determine how to arrage layout, perhaps manually?   600, 300
      //set bounds for each panel
      titlePanel.setBounds(0, 0, 600, 30);
      workPanel.setBounds(400, 35, 195, 110);
      typePanel.setBounds(0, 35, 395, 60);
      keyPanel.setBounds(50, 90, 195, 30);
      calcPanel.setBounds(5, 210, 600, 50);
      
      //add panels to main panel
      mainPanel.add(titlePanel);
      mainPanel.add(workPanel);      
      mainPanel.add(typePanel);      
      mainPanel.add(keyPanel);      
      mainPanel.add(calcPanel);
      
   }//end buildMainPanel
      
   
   private void buildTitlePanel()
   {
      //create panel for title
      titlePanel = new JPanel();
      
      //create new font for title
      Font f1 = new Font("Serif", Font.BOLD, 20);
      
      //create the label
      JLabel titleLabel = new JLabel("Select Registration Options");
      
      //set the font
      titleLabel.setFont(f1);
      
      //add the label to the panel
      titlePanel.add(titleLabel);
      
      //determine its layout
      titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
   }//end buildTitlePanel
   
   
   private void buildWorkPanel()
   {
      //create panel for workshop choices
      workPanel = new JPanel();
      workPanel.setBorder(new TitledBorder(new EtchedBorder(), "Workshops"));
      
      //create list of choices
      workshopList = new JList(workshopChoices);
      
      //set the selection mode to multiple interval selection
      workshopList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      
      //set the visible rows
      workshopList.setVisibleRowCount(4);
        
      //add the list to a scroll pane
      scrollPane1 = new JScrollPane(workshopList);
      
      //set layout
      workPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      //add list to panel
      workPanel.add(scrollPane1);
      
   }//end buildWorkPanel
   
   
   private void buildTypePanel()
   {
      //create panel for registration type
      typePanel = new JPanel();
      typePanel.setBorder(new TitledBorder(new EtchedBorder(), "Registration Type"));
   
      //create buttons for type of registration
      general = new JRadioButton("General Registration");
      student = new JRadioButton("Student Registration");
         
      //add buttons to a button group
      ButtonGroup type = new ButtonGroup();
      
      type.add(general);
      type.add(student);
      
      typePanel.add(general);
      typePanel.add(student);
      
      typePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      //set a default button selected
      general.setSelected(true);
   }//end buildTypePanel
   
   
   private void buildKeyPanel()
   {
      //create panel for keynote speech option
      keyPanel = new JPanel();
      //create check box
      keynote = new JCheckBox("Dinner and Keynote Speech");
      //set layout
      keyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      //add check box to panel
      keyPanel.add(keynote); 
   
   }//end buildKeyPanel
   
     
   private void buildCalcPanel()
   {
      //create panel for Calculate, CLear, and Exit buttons
      calcPanel = new JPanel();
        
      //create a button with the caption "Calculate Charges"
      JButton calcButton = new JButton("Calculate Charges");
      //create a button with the caption "Clear"
      JButton clearButton = new JButton("Clear");
      //create a button with the caption "Exit"
      JButton exitButton = new JButton("Exit");
      
      //add buttons to calcPanel
      calcPanel.add(calcButton);
      calcPanel.add(clearButton);
      calcPanel.add(exitButton);
      
      //add an action listener to the "Calculate Charges" button
      calcButton.addActionListener(new CalcButtonListener());
      //add an action listener to the "CLear" button
      clearButton.addActionListener(new ClearButtonListener());
      //add an action listener to the "Exit" button
      exitButton.addActionListener(new ExitButtonListener());
      
      calcPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
   }//end buildCalcPanel
   
      
   private class CalcButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         double totalCharge = 0;  //variable to hold the final total
         
         //get the registration type and add the associated fee to totalCharge
         if (general.isSelected())
         { 
            totalCharge = GENERAL_FEE;
         }
         else if (student.isSelected())
         {
            totalCharge = STUDENT_FEE;
         }
         
         //add the opening dinner fee is selected
         if (keynote.isSelected())
         {
            totalCharge += OPENING_FEE;
         }
        
         //add any workshop charges selected to the total charges
         int[] selections = workshopList.getSelectedIndices();
        
         for (int i = 0; i < selections.length; i++)
         {
            switch(selections[i])
            {
               case 0:
                  totalCharge += E_COMMERCE;
                  break;
               case 1:
                  totalCharge += FUTURE_WEB;
                  break;
               case 2:
                  totalCharge += ADVANCED_JAVA;
                  break;
               case 3:
                  totalCharge += NETWORK_SECURITY;
                  break;
            }
         }

         //display the result
         if (workshopList.getSelectedValue() == null)
         {
            JOptionPane.showMessageDialog(null, "Please select type of workshops.");
         }
         else
         {
            JOptionPane.showMessageDialog(null, " Charge is $" + totalCharge);
         }
         
      }
   }//end CalcButtonListener class
   
   
   private class ClearButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         workshopList.clearSelection();
         keynote.setSelected(false);
         general.setSelected(false);
         student.setSelected(false);
         
      }
   }//end ClearButtonListener class
   
   
   private class ExitButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent a)
      {
         System.exit(0);
      }
   }//end ExitButtonListener class
   
}//end RegistrationFee class

public class RegistrationFeeDemo
{
   public static void main(String[] arg)
   {
      RegistrationFee attendee1 = new RegistrationFee();
      
   }//end main()
   
}//end RegistrationFeeDemo
