package attendance;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewTeachers extends JFrame {
    ViewTeachers(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel h=new JLabel("ALL TEACHERS LIST");
        h.setBounds(0,0,900,50);
        h.setBackground(new Color(0,102,204));
        h.setForeground(Color.WHITE);
        h.setOpaque(true);
        h.setFont(new Font("Segoe UI",Font.BOLD,20));
        h.setHorizontalAlignment(JLabel.CENTER);
        add(h);

        String data[][]=new String[100][3];
        String col[]={"Teacher ID","Name","Department"};
        int i=0;
        try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("select * from teacher");
            while(rs.next()){
                data[i][0]=rs.getString(1); // id
                data[i][1]=rs.getString(2); // name
                data[i][2]=rs.getString(3); // dept
                i++;
            }
        }catch(Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }

        JTable table=new JTable(data,col);
        JScrollPane sp=new JScrollPane(table);
        sp.setBounds(20,70,850,400);
        add(sp);

        JButton back=new JButton("BACK");
        back.setBounds(350,480,150,35);
        back.setBackground(new Color(0,102,204));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Segoe UI",Font.BOLD,14));
        back.addActionListener(e->{ setVisible(false); new Teachers(); });
        add(back);

        setSize(900,600);
        setLocation(250,50);
        setVisible(true);
    }
    public static void main(String[] args){
        new ViewTeachers();
    }
}