package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddTeacher extends JFrame implements ActionListener {
    JTextField tfId, tfName;
    JComboBox cbDept;
    JButton add, back;

    AddTeacher(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("ADD NEW TEACHER");
        heading.setBounds(0,0,900,50);
        heading.setBackground(new Color(0,102,204));
        heading.setForeground(Color.WHITE);
        heading.setOpaque(true);
        heading.setFont(new Font("Segoe UI",Font.BOLD,20));
        heading.setHorizontalAlignment(JLabel.CENTER);
        add(heading);

        JLabel l1 = new JLabel("Teacher ID:");
        l1.setBounds(200,100,100,30); l1.setFont(new Font("Segoe UI",Font.PLAIN,16)); add(l1);
        tfId = new JTextField(); tfId.setBounds(350,100,250,30); add(tfId);

        JLabel l2 = new JLabel("Name:");
        l2.setBounds(200,150,100,30); l2.setFont(new Font("Segoe UI",Font.PLAIN,16)); add(l2);
        tfName = new JTextField(); tfName.setBounds(350,150,250,30); add(tfName);

        JLabel l3 = new JLabel("Department:");
        l3.setBounds(200,200,100,30); l3.setFont(new Font("Segoe UI",Font.PLAIN,16)); add(l3);
        String dept[] = {"Computer","Commerce","Science","Arts","Management"};
        cbDept = new JComboBox(dept); cbDept.setBounds(350,200,250,30); add(cbDept);

        add = new JButton("ADD"); add.setBounds(250,300,120,35);
        add.setBackground(new Color(0,102,204)); add.setForeground(Color.WHITE);
        add.addActionListener(this); add(add);

        back = new JButton("BACK"); back.setBounds(400,300,120,35);
        back.addActionListener(this); add(back);

        setSize(900,600); setLocation(250,50); setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==add){
            String id=tfId.getText(), name=tfName.getText(), dept=(String)cbDept.getSelectedItem();
            if(id.equals("")||name.equals("")){ JOptionPane.showMessageDialog(null,"Fill all!"); return; }
            try{
                Conn c=new Conn();
                c.s.executeUpdate("insert into teacher values('"+id+"','"+name+"','"+dept+"')");
                JOptionPane.showMessageDialog(null,"Teacher Added!");
                setVisible(false); new Teachers();
            }catch(Exception e){ JOptionPane.showMessageDialog(null,e); }
        } else { setVisible(false); new Teachers(); }
    }
    public static void main(String[] args){ new AddTeacher(); }
}