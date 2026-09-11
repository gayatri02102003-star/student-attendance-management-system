package attendance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener{
    JButton b1,b2,b3,b4;
    Home(){
        setSize(1000, 650);
        setLocation(200, 80);
        setLayout(null);
        getContentPane().setBackground(new Color(236,240,245));

        JPanel header = new JPanel();
        header.setBounds(0,0,1000,80);
        header.setBackground(new Color(26,47,80));
        header.setLayout(null);
        add(header);

        JLabel title = new JLabel("STUDENT ATTENDANCE SYSTEM", JLabel.CENTER);
        title.setBounds(0,15,1000,50);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        header.add(title);

        b1 = createButton("STUDENT MANAGEMENT", new Color(5,152,219), 100, 150);
        b2 = createButton("TEACHER MANAGEMENT", new Color(39,174,96), 550, 150);
        b3 = createButton("ADD ATTENDANCE", new Color(142,68,173), 100, 350);
        b4 = createButton("VIEW ATTENDANCE", new Color(231,76,60), 550, 350);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);

        add(b1); add(b2); add(b3); add(b4);

        setVisible(true);
    }

    public JButton createButton(String text, Color c, int x, int y){
        JButton b = new JButton(text);
        b.setBounds(x,y,350,130);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 16));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder());
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){ new Students(); }
        if(ae.getSource()==b2){ new Teachers(); }
        if(ae.getSource()==b3){ new AddAttendance(); }
        if(ae.getSource()==b4){ new ViewAttendance(); }
    }
    public static void main(String[] args){ new Home(); }
}