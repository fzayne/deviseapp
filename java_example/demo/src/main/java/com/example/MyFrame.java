package com.example;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.example.main.type_conversion;




public class MyFrame extends JFrame{
    JTextField textField;
    JRadioButton btn1 ;
    JRadioButton btn2;
    MyFrame() throws SQLException, ParseException{
        
        
        JTabbedPane tabbedPane = new JTabbedPane();
        
        //panel.setPreferredSize(new Dimension(250,250));
        
        //ConvertisseurMoannai cm=new ConvertisseurMoannai();
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane.addTab("Insert conversion", insertPanel());
        tabbedPane.addTab("ConversionMonnai", conversionpanel());
        tabbedPane.addTab("AficheHistorique", hist_panel());
        tabbedPane.addTab("Affiche-cours", affi_pannel());
        this.setSize(500,500);
        
        this.add(tabbedPane);
        this.setVisible(true);
    }
    JPanel panel;
    private JPanel conversionpanel() throws SQLException{
         panel=new JPanel();
        panel.setBackground(Color.lightGray);
        Timer timer =new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                panel.removeAll();
                ConvertisseurMoannai cm=new ConvertisseurMoannai();
                try {
                    panel.add(cm.ConversionMonnai());
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
        timer.start();
        
        
        return panel;
    }
   
    JTextField fromdate;
    JTextField todate;
    JPanel affichehistorique;
    private JPanel hist_panel() throws SQLException{
         affichehistorique =new JPanel();
        affichehistorique.setBackground(Color.lightGray);
        affichehistorique.setVisible(true);
        
        JLabel label1=new JLabel("From");
        fromdate=new JTextField();
        todate=new JTextField();
        fromdate.setPreferredSize(new Dimension(150,50));
        fromdate.setFont(new Font(null,Font.PLAIN,25));
        todate.setPreferredSize(new Dimension(150,50));
        todate.setFont(new Font(null,Font.PLAIN,25));
        JLabel label2=new JLabel("To");
        JButton button=new JButton("ENTER");
       // button.setPreferredSize(new Dimension(50,50));;
        affichehistorique.add(label1);
        affichehistorique.add(fromdate);
        affichehistorique.add(label2);
        affichehistorique.add(todate);
        affichehistorique.add(button);
        
        
        affichehistorique.add(new JScrollPane());
        //scpane.setVisible(false);
         
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                 GetDate date =new GetDate();
                 affichehistorique.remove(5);
                 try {
                    date.setDate(fromdate.getText(), todate.getText());
                } catch (ParseException e1) {
                    
                    e1.printStackTrace();
                }
                ConvertisseurMoannai cm=new ConvertisseurMoannai();
                try {
                    affichehistorique.add(cm.Affiche_Historique(date.firstDate, date.secondDate));
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
               affichehistorique.revalidate();
               affichehistorique.repaint();
               
             //   try {
             //      // scpane=cm.Affiche_Historique(date.firstDate, date.secondDate);
             //    
             //   } catch (SQLException e1) {
             //      
             //       e1.printStackTrace();
             //   }
             //   
               // scpane.setVisible(true);
            }
            
            
        });
       // GetDate date =new GetDate();
       // ConvertisseurMoannai cm=new ConvertisseurMoannai();
       // try {
       //     date.setDate("2023-02-26", "2023-03-03");
       // } catch (ParseException e) {
       //     // TODO: handle exception
       // }
        
        
        
               
              
    
            
        return affichehistorique;
    }
    JPanel affichcours =new JPanel();
    JTextField period;
    private JPanel affi_pannel() throws SQLException{
        
        affichcours.setBackground(Color.lightGray);
        JLabel label=new JLabel("The last ");
        label.setFont(new Font(null,Font.PLAIN,25));
        JLabel label2=new JLabel("Months");
        label2.setFont(new Font(null,Font.PLAIN,25));
         period=new JTextField();
        JButton enterbutton=new JButton("Enter");
        period.setPreferredSize(new Dimension(50,50));
        period.setFont(new Font(null,Font.PLAIN,25));
        affichcours.add(label);
        affichcours.add(period);
        affichcours.add(label2);
        affichcours.add(enterbutton);
        ConvertisseurMoannai cm=new ConvertisseurMoannai();
        affichcours.add(cm.Affiche_Cours(1));
        enterbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                affichcours.remove(4);
                
               ConvertisseurMoannai cm=new ConvertisseurMoannai();
               try {
                
                affichcours.add(cm.Affiche_Cours(Double.parseDouble(period.getText())));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
            affichcours.revalidate();
            affichcours.repaint();
            }
            
        });
        return affichcours;
    }


    private JPanel insertPanel(){
        
        JButton button;
        GridBagConstraints gbc =new GridBagConstraints();
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label=new JLabel("montant");
        JLabel label_cours=new JLabel();
        JLabel label_result=new JLabel();
        label.setFont(new Font(null,Font.PLAIN,35));
        
        btn1 = new JRadioButton("Euro-dinar");
        btn2 = new JRadioButton("dinar-Euro");
        ButtonGroup group=new ButtonGroup();
        
        group.add(btn1);
        group.add(btn2);
        
        btn1.setBackground(Color.lightGray);
        btn2.setBackground(Color.lightGray);
        panel.setBorder(new EmptyBorder(10,50,10,50));
        panel.setBackground(Color.lightGray);
        panel.setVisible(true);
        gbc.anchor=GridBagConstraints.CENTER;
        textField=new JTextField();
        //textField.setBounds(200,100,100,50);
        button=new JButton("Insert");
        //button.setBounds(200, 250, 100, 50);
        textField.setPreferredSize(new Dimension(100,50));
        textField.setFont(new Font(null,Font.PLAIN,35));
        gbc.insets=new Insets(50, 0, 0, 10);

        gbc.gridy=0;
        
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                double n=Double.parseDouble(textField.getText());
                type_conversion type=null;
                if(btn1.isSelected())
                type=type_conversion.euro_dinar;
                else if(btn2.isSelected())
                type=type_conversion.dinar_euro;
                new BDQueries();
                BDQueries.InsertIntoDB(n, type);
               
            }
            
        });
        panel.add(label,gbc);
        gbc.gridy++;
        panel.add(textField,gbc);
        gbc.gridy++;
        panel.add(btn1,gbc);
        
        panel.add(btn2,gbc);
        gbc.gridy++;
        //panel.add(label,gbc);
        //gbc.gridy++;
        panel.add(button,gbc);
        return panel;
    }
   
    
}

class GetDate{
    Date firstDate;
    Date secondDate;
    public void setDate(String fdate,String sdate) throws ParseException{
        java.util.Date d1= new SimpleDateFormat("yyyy-MM-dd").parse(fdate);
        java.util.Date d2= new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
        this.firstDate=new Date(d1.getTime());
        this.secondDate=new Date(d2.getTime());
    }
}


