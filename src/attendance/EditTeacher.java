package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EditTeacher extends JFrame implements ActionListener {
    JTextField tfId, tfName, tfDept;
    JButton search, update, delete, back;
    
    EditTeacher(){
        getContentPane().setBackground(Color.WHITE); setLayout(null);
        
        JLabel h=new JLabel("EDIT / UPDATE TEACHER"); h.setBounds(0,0,900,50);
        h.setBackground(new Color(255,102,0)); h.setForeground(Color.WHITE); h.setOpaque(true);
        h.setFont(new Font("Segoe UI",Font.BOLD,20)); h.setHorizontalAlignment(JLabel.CENTER); add(h);

        JLabel l1=new JLabel("Enter Teacher ID:"); l1.setBounds(150,80,150,30); add(l1);
        tfId=new JTextField(); tfId.setBounds(300,80,200,30); add(tfId);
        search=new JButton("SEARCH"); search.setBounds(520,80,100,30); search.addActionListener(this); add(search);

        JLabel l2=new JLabel("Name:"); l2.setBounds(150,150,100,30); add(l2);
        tfName=new JTextField(); tfName.setBounds(300,150,300,30); add(tfName);

        JLabel l3=new JLabel("Department:"); l3.setBounds(150,200,100,30); add(l3);
        tfDept=new JTextField(); tfDept.setBounds(300,200,300,30); add(tfDept);

        update=new JButton("UPDATE"); update.setBounds(250,300,120,35); update.setBackground(Color.BLUE); update.setForeground(Color.WHITE); update.addActionListener(this); add(update);
        delete=new JButton("DELETE"); delete.setBounds(400,300,120,35); delete.setBackground(Color.RED); delete.setForeground(Color.WHITE); delete.addActionListener(this); add(delete);
        back=new JButton("BACK"); back.setBounds(320,350,120,30); back.addActionListener(this); add(back);

        setSize(900,500); setLocation(250,100); setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        try{
            Conn c=new Conn();
            if(ae.getSource()==search){
                ResultSet rs=c.s.executeQuery("select * from teacher where id='"+tfId.getText()+"'");
                if(rs.next()){ tfName.setText(rs.getString(2)); tfDept.setText(rs.getString(3)); }
                else { JOptionPane.showMessageDialog(null,"ID Not Found!"); }
            } else if(ae.getSource()==update){
                c.s.executeUpdate("update teacher set name='"+tfName.getText()+"', dept='"+tfDept.getText()+"' where id='"+tfId.getText()+"'");
                JOptionPane.showMessageDialog(null,"Teacher Updated!"); setVisible(false); new Teachers();
            } else if(ae.getSource()==delete){
                int conf=JOptionPane.showConfirmDialog(null,"Delete karaycha ka?");
                if(conf==0){ c.s.executeUpdate("delete from teacher where id='"+tfId.getText()+"'");
                JOptionPane.showMessageDialog(null,"Deleted!"); setVisible(false); new Teachers(); }
            } else { setVisible(false); new Teachers(); }
        }catch(Exception e){ JOptionPane.showMessageDialog(null,e); }
    }
    public static void main(String[] a){ new EditTeacher(); }
}