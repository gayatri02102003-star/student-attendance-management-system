package attendance;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewAttendance extends JFrame implements ActionListener {
    JTable table;
    JButton search, back, showAll;
    JTextField tfSearch;
    String data[][]=new String[500][3];
    String col[]={"Roll No","Date","Status"};

    ViewAttendance(){
        getContentPane().setBackground(Color.WHITE); setLayout(null);

        JLabel h=new JLabel("VIEW ATTENDANCE"); h.setBounds(0,0,900,50);
        h.setBackground(new Color(0,102,153)); h.setForeground(Color.WHITE); h.setOpaque(true);
        h.setFont(new Font("Segoe UI",Font.BOLD,20)); h.setHorizontalAlignment(JLabel.CENTER); add(h);

        JLabel l=new JLabel("Search by Roll No:"); l.setBounds(50,70,150,25); add(l);
        tfSearch=new JTextField(); tfSearch.setBounds(200,70,200,25); add(tfSearch);

        search=new JButton("SEARCH"); search.setBounds(420,70,100,25); search.addActionListener(this); add(search);
        showAll=new JButton("SHOW ALL"); showAll.setBounds(530,70,100,25); showAll.addActionListener(this); add(showAll);

        loadData("select * from attendance");

        table=new JTable(data,col);
        JScrollPane sp=new JScrollPane(table); sp.setBounds(20,110,850,350); add(sp);

        back=new JButton("BACK"); back.setBounds(400,480,100,30); back.addActionListener(this); add(back);

        setSize(900,550); setLocation(250,50); setVisible(true);
    }

    void loadData(String q){
        // table clear
        for(int i=0;i<500;i++){ data[i][0]=null; data[i][1]=null; data[i][2]=null; }
        int i=0;
        try{
            Conn c=new Conn(); ResultSet rs=c.s.executeQuery(q);
            while(rs.next()){ data[i][0]=rs.getString("roll"); data[i][1]=rs.getString("date"); data[i][2]=rs.getString("status"); i++; }
        }catch(Exception e){
            // jar 'roll' navane error ala tar pahila column gheil
            try{ i=0; Conn c=new Conn(); ResultSet rs=c.s.executeQuery(q);
            while(rs.next()){ data[i][0]=rs.getString(1); data[i][1]=rs.getString(2); data[i][2]=rs.getString(3); i++; }
            }catch(Exception ex){ JOptionPane.showMessageDialog(null,ex); }
        }
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==search){
            if(tfSearch.getText().equals("")){ JOptionPane.showMessageDialog(null,"Roll No Taka!"); return; }
            // ITHE FIX KELA - 'rollno' NAHI TAR 'roll' VAPARLA
            loadData("select * from attendance where roll='"+tfSearch.getText()+"'");
            table.repaint(); setVisible(false); new ViewAttendanceWithData(tfSearch.getText());
        } else if(ae.getSource()==showAll){
            setVisible(false); new ViewAttendance();
        } else {
            setVisible(false); new Home();
        }
    }

    // Search sathi dusra simple frame
    class ViewAttendanceWithData extends JFrame{
        ViewAttendanceWithData(String rollNo){
            setLayout(null);
            String d[][]=new String[100][3]; String c[]={"Roll","Date","Status"};
            int i=0;
            try{ Conn conn=new Conn(); ResultSet rs=conn.s.executeQuery("select * from attendance where roll='"+rollNo+"'");
                while(rs.next()){ d[i][0]=rs.getString(1); d[i][1]=rs.getString(2); d[i][2]=rs.getString(3); i++; }
            }catch(Exception e){}
            JTable t=new JTable(d,c); JScrollPane sp=new JScrollPane(t); sp.setBounds(20,20,550,300); add(sp);
            JButton b=new JButton("BACK"); b.setBounds(200,350,100,30); b.addActionListener(e->{setVisible(false); new ViewAttendance();}); add(b);
            setSize(600,450); setLocation(300,100); setVisible(true);
        }
    }

    public static void main(String[] a){ new ViewAttendance(); }
}