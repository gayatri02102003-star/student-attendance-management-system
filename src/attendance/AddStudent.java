package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddStudent extends JFrame implements ActionListener {
    JTextField tfRoll, tfName;
    JComboBox cbCourse;
    JButton add, back;

    AddStudent(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ADD NEW STUDENT");
        heading.setBounds(0,0,900,50);
        heading.setBackground(new Color(0,102,204));
        heading.setForeground(Color.WHITE);
        heading.setOpaque(true);
        heading.setFont(new Font("Segoe UI",Font.BOLD,20));
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        JLabel lblRoll = new JLabel("Roll No:");
        lblRoll.setBounds(200,100,100,30);
        lblRoll.setFont(new Font("Segoe UI",Font.PLAIN,16));
        add(lblRoll);
        tfRoll = new JTextField();
        tfRoll.setBounds(350,100,250,30);
        add(tfRoll);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(200,150,100,30);
        lblName.setFont(new Font("Segoe UI",Font.PLAIN,16));
        add(lblName);
        tfName = new JTextField();
        tfName.setBounds(350,150,250,30);
        add(tfName);

        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(200,200,100,30);
        lblCourse.setFont(new Font("Segoe UI",Font.PLAIN,16));
        add(lblCourse);
        String courses[] = {"BCA","BBA","BCS","MCA","MBA","BCom"};
        cbCourse = new JComboBox(courses);
        cbCourse.setBounds(350,200,250,30);
        cbCourse.setBackground(Color.WHITE);
        add(cbCourse);

        add = new JButton("ADD");
        add.setBounds(250,300,120,35);
        add.setBackground(new Color(0,102,204));
        add.setForeground(Color.WHITE);
        add.addActionListener(this);
        add(add);

        back = new JButton("BACK");
        back.setBounds(400,300,120,35);
        back.addActionListener(this);
        add(back);

        setSize(900,600);
        setLocation(250,50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){
            String roll = tfRoll.getText();
            String name = tfName.getText();
            String course = (String)cbCourse.getSelectedItem();
            
            if(roll.equals("") || name.equals("")){
                JOptionPane.showMessageDialog(null,"Roll No and Name required!");
                return;
            }
            
            try{
                Conn c = new Conn();
                String q = "insert into student values('"+roll+"','"+name+"','"+course+"')";
                c.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Student Added Successfully!");
                setVisible(false);
                new Students();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        } else {
            setVisible(false);
            new Students();
        }
    }
    public static void main(String[] args){ new AddStudent(); }
}