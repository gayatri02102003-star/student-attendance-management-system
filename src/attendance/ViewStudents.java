package attendance;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewStudents extends JFrame {
    ViewStudents(){
        setTitle("View Students");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel h = new JLabel("ALL STUDENTS LIST");
        h.setBounds(0,0,900,50);
        h.setBackground(new Color(0,102,204));
        h.setForeground(Color.WHITE);
        h.setOpaque(true);
        h.setFont(new Font("Segoe UI",Font.BOLD,20));
        h.setHorizontalAlignment(JLabel.CENTER);
        add(h);

        // MySQL madhun data ghenar
        String data[][] = new String[100][3];
        String col[] = {"Roll No","Name","Course"};
        int i=0;
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()){
                data[i][0]=rs.getString("rollno");
                data[i][1]=rs.getString("name");
                data[i][2]=rs.getString("course");
                i++;
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        JTable table = new JTable(data, col);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20,70,850,400);
        add(sp);

        JButton back = new JButton("BACK");
        back.setBounds(350,480,150,30);
        back.setBackground(new Color(0,102,204));
        back.setForeground(Color.WHITE);
        back.addActionListener(e->{
            setVisible(false);
            new Students();
        });
        add(back);

        setSize(900,600);
        setLocation(250,50);
        setVisible(true);
    }
    public static void main(String[] args){ new ViewStudents(); }
}