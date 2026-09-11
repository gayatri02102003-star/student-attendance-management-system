package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Teachers extends JFrame implements ActionListener {
    JButton add, view, edit, back;
    Teachers(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel head = new JLabel("TEACHER MANAGEMENT");
        head.setBounds(0,0,800,60); head.setBackground(new Color(0,102,0));
        head.setForeground(Color.WHITE); head.setOpaque(true);
        head.setFont(new Font("Segoe UI",Font.BOLD,22)); head.setHorizontalAlignment(JLabel.CENTER);
        add(head);

        add = new JButton("ADD NEW TEACHER"); add.setBounds(250,100,300,50);
        add.setFont(new Font("Segoe UI",Font.BOLD,14)); add.addActionListener(this); add(add);

        view = new JButton("VIEW TEACHERS"); view.setBounds(250,180,300,50);
        view.setFont(new Font("Segoe UI",Font.BOLD,14)); view.addActionListener(this); add(view);

        // EDIT BUTTON - AATA NORMAL COLOR
        edit = new JButton("EDIT / DELETE TEACHER"); edit.setBounds(250,260,300,50);
        edit.setFont(new Font("Segoe UI",Font.BOLD,14)); 
        edit.addActionListener(this); add(edit);

        back = new JButton("BACK"); back.setBounds(250,340,300,50);
        back.addActionListener(this); add(back);

        setSize(800,500); setLocation(300,100); setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){ setVisible(false); new AddTeacher(); }
        else if(ae.getSource()==view){ setVisible(false); new ViewTeachers(); }
        else if(ae.getSource()==edit){ setVisible(false); new EditTeacher(); }
        else { setVisible(false); new Home(); }
    }
    public static void main(String[] a){ new Teachers(); }
}