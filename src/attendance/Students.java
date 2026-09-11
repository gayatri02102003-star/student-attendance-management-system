package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Students extends JFrame implements ActionListener {
    JButton add, view, back;
    Students(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("STUDENT MANAGEMENT");
        heading.setBounds(0,0,900,50);
        heading.setBackground(new Color(0,102,204));
        heading.setForeground(Color.WHITE);
        heading.setOpaque(true);
        heading.setFont(new Font("Segoe UI",Font.BOLD,20));
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        add = new JButton("ADD NEW STUDENT");
        add.setBounds(200,100,200,40);
        add.addActionListener(this);
        add(add);

        view = new JButton("VIEW STUDENTS");
        view.setBounds(200,160,200,40);
        view.addActionListener(this);
        add(view);

        back = new JButton("BACK");
        back.setBounds(200,220,200,40);
        back.addActionListener(this);
        add(back);

        setSize(900,600);
        setLocation(250,50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){
            setVisible(false);
            new AddStudent(); // Aata navin premium form ughadel
        } else if(ae.getSource()==view){
            setVisible(false);
            new ViewStudents();
        } else {
            setVisible(false);
            new Home();
        }
    }
    public static void main(String[] args){ new Students(); }
}