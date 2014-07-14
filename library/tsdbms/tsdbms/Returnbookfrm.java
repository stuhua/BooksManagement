package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
class Returnbookfrm extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_msg,l_user,l_bookname,l_data,l_beizhu;
	JTextField t_user,t_bookname,t_data,t_beizhu;
	JButton b_enter,b_clear,b_back;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6;
	String temp1,slist;
	
	Date d;
	NetConn sql;
	Statement sqll;
	ResultSet rs,rs1,rs2;
	
	@SuppressWarnings("deprecation")
	Returnbookfrm()
	{
		super("还书");
		l_msg      =new JLabel("还书    ");
		l_user     =new JLabel("还书者：");
		l_bookname =new JLabel("书  名：");
		l_data     =new JLabel("日  期：");
		l_beizhu   =new JLabel("备  注：");
		t_user     =new JTextField("",12);
		t_bookname =new JTextField("",12);
		t_data     =new JTextField("",12);
		d=new Date();
		t_data.setText(d.toLocaleString());
		t_data.setEditable(false);
		t_beizhu   =new JTextField("",12);
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
		setBounds(400,100,400,500);
		
		sql=new NetConn();
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
			l_msg.setText("还书");
		}
		else if(ed.getSource()==b_enter)
		{
			try
			{
				sqll=sql.connect();
				String temp="SELECT * FROM books where bookname="+"'"+t_bookname.getText()+"'";
				rs = sqll.executeQuery(temp);
				
				if(rs.next())
				{
					String a1="'"+t_user.getText()+"'";
					String a2="'"+t_bookname.getText()+"'";
					String a3="'"+t_data.getText()+"'";
					String a4="'"+t_beizhu.getText()+"'";
					String a5="'"+"Yes"+"'";
					temp="SELECT * FROM bookbrowse where bookname="+a2+" and readername="+a1+" and isreturn='no'";
					rs1=sqll.executeQuery(temp);
					if(rs1.next())
					{
						String ss="'"+rs1.getString(4)+"'";
						//l_msg.setText(ss);
						
						// String temp2="SELECT * FROM readerinfo where readernum="+ss;
						// rs2=sqll.executeQuery(temp2);
						//rs2=sqll.executeQuery(temp1);
						//slist=rs2.getString(2);
						//l_msg.setText(slist);
						if(ss.equals("'1001'")) slist="xiaohua";
						else if(ss.equals("'1002'")) slist="xiaogang";
						else slist="xiaolv";
						
						temp="update bookbrowse set returndata ="+a3+", isreturn ="+a5+", beizhu ="+a4+
						" where bookname ="+a2+" and "+"readername="+a1;
						sqll.executeUpdate(temp);
						
						temp="update books set canborrow=canborrow+1 where bookname="+a2;
						sqll.executeUpdate(temp);
						
						temp="update "+slist+" set isreturn="+a5+",returndate="+a3+",beizhu="+a4+" where borrowbook="+a2;
						sqll.execute(temp);
						l_msg.setText("还书成功.");
					}
					else 
					{
						l_msg.setText("未借此书!请查证.");
					}
				}
				else 
				{
					l_msg.setText("馆中未有该书,请查证! ");
				}
			}
			catch(SQLException eg)
			{}
		}
	}
}