package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
class Borbookfrm extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_msg,l_user,l_bookname,l_data,l_beizhu;
	JTextField t_user,t_bookname,t_data,t_beizhu;
	JButton b_enter,b_clear,b_back;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6;
	
	Date d;
	NetConn sql;
	Statement sqll;
	ResultSet rs,rs1;
	
	@SuppressWarnings("deprecation")
	Borbookfrm()
	{
		super("图书借阅");
		l_msg      =new JLabel("图书借阅    ");
		l_user     =new JLabel("借阅者：");
		l_bookname =new JLabel("书  名：");
		l_data     =new JLabel("日  期：");
		l_beizhu   =new JLabel("备  注：");
		t_user     =new JTextField("",11);
		t_bookname =new JTextField("",11);
		t_data     =new JTextField("",11);
		d=new Date();
		t_data.setText(d.toLocaleString());
		t_data.setEditable(false);
		t_beizhu   =new JTextField("",11);
		b_enter    =new JButton("确定");
		b_clear    =new JButton("清除");
		b_back     =new JButton("返回");
		b_enter.addActionListener(this);
		b_clear.addActionListener(this);
		b_back.addActionListener(this);
		pan1=new JPanel();pan2=new JPanel();pan3=new JPanel();pan4=new JPanel();pan5=new JPanel();pan6=new JPanel();
		pan1.add(l_msg);pan2.add(l_user);pan2.add(t_user);pan3.add(l_bookname);pan3.add(t_bookname);
		pan4.add(l_data);pan4.add(t_data);pan5.add(l_beizhu);pan5.add(t_beizhu);pan6.add(b_enter);pan6.add(b_clear);pan6.add(b_back);
		setLayout(new GridLayout(6,1));
		add(pan1);add(pan2);add(pan3);add(pan4);add(pan5);add(pan6);
		
		sql=new NetConn();
		
		setBounds(400,100,400,500);
		
		
	}
	public void actionPerformed(ActionEvent ed)
	{
		if(ed.getSource()==b_back)
		{
				dispose();
		}
		else if(ed.getSource()==b_clear)
		{
			t_user.setText("");t_beizhu.setText("");t_bookname.setText("");
		}
		else if(ed.getSource()==b_enter)
		{
			try
			{
				 sqll=sql.connect();
				 String temp="SELECT * FROM books where bookname="+"'"+t_bookname.getText()+"'";
				 rs=sqll.executeQuery(temp);
				 if(rs.next())
				 {
					 String b_id="'"+rs.getString(1)+"'";
					 String s2="'"+t_user.getText().trim()+"'";
					 String s3="'"+t_bookname.getText().trim()+"'";
					 String s4="'"+t_data.getText().trim()+"'";
					 String s5="'"+t_beizhu.getText().trim()+"'";
					 String s6="'"+"No"+"'";
					 String temp2="SELECT * FROM readerinfo where name="+s2;
					 rs1=sqll.executeQuery(temp2);
					 
					 
					 if(rs1.next())
					 {
						 String s7="'"+rs1.getString(1)+"'";
						 String s8=rs1.getString(2);
						// l_msg.setText(s3);
						 String temp1="INSERT INTO bookbrowse (bookid, bookname, readername, readernum, borrowdata, beizhu, isreturn) VALUES ("+b_id+","+s3+","+s2+","+s7+","+s4+","+s5+","+s6+")";
						 sqll.executeUpdate(temp1);
						 l_msg.setText(s7);
						 temp="UPDATE books set canborrow=canborrow-1 where bookname="+s3;
						 sqll.executeUpdate(temp);
						 //l_msg.setText(s9);
						 String temp3="INSERT INTO "+s8+" (readername, readernum, borrowbook, borrowdate, isreturn, beizhu) "+
						 		"VALUES ("+s2+","+s7+","+s3+","+s4+","+s6+","+s5+")";
						 sqll.executeUpdate(temp3);
						 l_msg.setText("借书成功! ");
					 }
					 else 
					 {
						 l_msg.setText("读者姓名没有登记,请确认.");
					 }
				 }
				 else
				{
					l_msg.setText("没有找到该书。借书失败！");
				}
			}
			catch(SQLException er)
			{
				System.out.println("借书失败");
			}
		}
	}
}