package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JTextField tfUser;
    JPasswordField tfPass;
    JButton login, cancel, forgot;

    Login(){
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // LEFT PANEL
        JPanel left = new JPanel();
        left.setBackground(new Color(20, 35, 70));
        left.setBounds(0,0,350,400);
        left.setLayout(null);
        add(left);

        JLabel l1 = new JLabel("STUDENT");
        l1.setBounds(50,80,250,40); l1.setFont(new Font("Segoe UI",Font.BOLD,30));
        l1.setForeground(Color.WHITE); left.add(l1);
        JLabel l2 = new JLabel("ATTENDANCE");
        l2.setBounds(50,120,250,40); l2.setFont(new Font("Segoe UI",Font.BOLD,30));
        l2.setForeground(Color.WHITE); left.add(l2);
        JLabel l3 = new JLabel("SYSTEM");
        l3.setBounds(50,160,250,40); l3.setFont(new Font("Segoe UI",Font.BOLD,30));
        l3.setForeground(new Color(52,152,219)); left.add(l3);
        
        JLabel l4 = new JLabel("Manage Students & Attendance Easily");
        l4.setBounds(30,220,300,20); l4.setForeground(Color.LIGHT_GRAY); left.add(l4);

        // RIGHT PANEL
        JLabel head = new JLabel("Admin Login");
        head.setBounds(400,30,200,40); head.setFont(new Font("Segoe UI",Font.BOLD,26)); add(head);
        
        JLabel sub = new JLabel("Please enter your credentials to continue");
        sub.setBounds(400,65,300,20); sub.setForeground(Color.GRAY); sub.setFont(new Font("Segoe UI",Font.PLAIN,12)); add(sub);

        JLabel u = new JLabel("USERNAME");
        u.setBounds(400,110,100,20); u.setFont(new Font("Segoe UI",Font.BOLD,11)); add(u);
        
        // ITHE BLANK THEVLA AAHE - AADHICH BHARLELA NAHI
        tfUser = new JTextField();
        tfUser.setBounds(400,135,280,35); 
        tfUser.setToolTipText("Enter username");
        add(tfUser);

        JLabel p = new JLabel("PASSWORD");
        p.setBounds(400,190,100,20); p.setFont(new Font("Segoe UI",Font.BOLD,11)); add(p);
        
        // PASSWORD PAN BLANK
        tfPass = new JPasswordField();
        tfPass.setBounds(400,215,280,35);
        add(tfPass);

        login = new JButton("LOGIN");
        login.setBounds(400,270,140,40); login.setBackground(new Color(13,71,161));
        login.setForeground(Color.WHITE); login.setFont(new Font("Segoe UI",Font.BOLD,13));
        login.addActionListener(this); add(login);

        cancel = new JButton("CANCEL");
        cancel.setBounds(550,270,130,40); cancel.setBackground(new Color(230,230,230));
        cancel.addActionListener(this); add(cancel);

        // FORGOT PASSWORD BUTTON
        forgot = new JButton("Forgot Password ?");
        forgot.setBounds(400,320,280,20); forgot.setBorder(null);
        forgot.setBackground(Color.WHITE); forgot.setForeground(new Color(13,71,161));
        forgot.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgot.addActionListener(this); add(forgot);

        setSize(750,400);
        setLocation(300,150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==login){
            String user = tfUser.getText();
            String pass = new String(tfPass.getPassword());
            
            // Check empty
            if(user.equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(null, "Username & Password Taka!");
                return;
            }

            try{
                Conn c = new Conn();
                String q = "select * from login where username='"+user+"' and password='"+pass+"'";
                ResultSet rs = c.s.executeQuery(q);
                if(rs.next()){
                    setVisible(false);
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!");
                    tfPass.setText(""); // fakt password clear hoil
                }
            }catch(Exception e){
                // Jar DB nasel tar default admin/admin ne chalu hoil
                if(user.equals("admin") && pass.equals("admin")){
                    setVisible(false);
                    new Home();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login! Default is admin / admin");
                }
            }
        } else if(ae.getSource()==cancel){
            setVisible(false);
        } else if(ae.getSource()==forgot){
            // FORGOT PASSWORD LOGIC
            String newPass = JOptionPane.showInputDialog(this, "Enter NEW Password for admin:");
            if(newPass != null && !newPass.equals("")){
                try{
                    Conn c = new Conn();
                    c.s.executeUpdate("update login set password='"+newPass+"' where username='admin'");
                    JOptionPane.showMessageDialog(null, "Password Updated! New Password: "+newPass+"\nAata login kara.");
                    tfUser.setText("admin");
                    tfPass.setText("");
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Default Login: \nUsername: admin\nPassword: admin");
                }
            }
        }
    }
    public static void main(String[] args){ new Login(); }
}