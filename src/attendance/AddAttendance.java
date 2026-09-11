package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddAttendance extends JFrame implements ActionListener {
    JTextField tfRoll, tfDate;
    JComboBox cbStatus;
    JButton add, back;
    
    AddAttendance(){
        getContentPane().setBackground(Color.WHITE); setLayout(null);

        JLabel h=new JLabel("ADD ATTENDANCE"); h.setBounds(0,0,900,50);
        h.setBackground(new Color(0,102,153)); h.setForeground(Color.WHITE); h.setOpaque(true);
        h.setFont(new Font("Segoe UI",Font.BOLD,20)); h.setHorizontalAlignment(JLabel.CENTER); add(h);

        JLabel l1=new JLabel("Student Roll No:"); l1.setBounds(200,100,150,30); l1.setFont(new Font("Segoe UI",Font.BOLD,14)); add(l1);
        // AATA DROPDOWN NAHI - DIRECT TYPE KARAYCHA
        tfRoll=new JTextField(); tfRoll.setBounds(350,100,250,30); tfRoll.setToolTipText("Ex: 101"); add(tfRoll);

        JLabel l2=new JLabel("Date:"); l2.setBounds(200,150,100,30); l2.setFont(new Font("Segoe UI",Font.BOLD,14)); add(l2);
        tfDate=new JTextField("2026-09-11"); tfDate.setBounds(350,150,250,30); add(tfDate);

        JLabel l3=new JLabel("Status:"); l3.setBounds(200,200,100,30); l3.setFont(new Font("Segoe UI",Font.BOLD,14)); add(l3);
        cbStatus=new JComboBox(new String[]{"Present","Absent"}); cbStatus.setBounds(350,200,250,30); add(cbStatus);

        add=new JButton("MARK ATTENDANCE"); add.setBounds(200,300,200,40); add.addActionListener(this); add(add);
        back=new JButton("BACK"); back.setBounds(420,300,180,40); back.addActionListener(this); add(back);

        setSize(900,500); setLocation(250,100); setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){
            if(tfRoll.getText().equals("")){ JOptionPane.showMessageDialog(null,"Roll No Taka!"); return; }
            try{ Conn c=new Conn(); 
                c.s.executeUpdate("insert into attendance values('"+tfRoll.getText()+"','"+tfDate.getText()+"','"+cbStatus.getSelectedItem()+"')");
                JOptionPane.showMessageDialog(null,"Attendance Marked for Roll: "+tfRoll.getText());
                setVisible(false); new Home();
            }catch(Exception e){ JOptionPane.showMessageDialog(null,e); }
        } else { setVisible(false); new Home(); }
    }
    public static void main(String[] a){ new AddAttendance(); }
}