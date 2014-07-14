package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
class BorMsgbookfrm extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_msg,l_user,l_uuser,l_bookname,l_data,l_beizhu;
	JTextField t_user,t_uuser,t_bookname,t_data,t_beizhu;
	JButton b_enter,b_clear,b_update,b_back;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7;
    String a2,a3,a4,a5;
	
	NetConn sql;
	Statement sqll;
	ResultSet rs;
	
	BorMsgbookfrm()
	{
		super("续借窗口");
		l_msg      =new JLabel("续借窗口    ");
		l_user     =new JLabel("借阅者：");
		l_uuser    =new JLabel("用  户 ： ");
		l_bookname =new JLabel("书  名：");
		l_data     =new JLabel("日  期：");
		l_beizhu   =new JLabel("备  注：");
		t_user     =new JTextField("",10);
		t_uuser    =new JTextField("",4);
		t_bookname =new JTextField("",10);
		t_data     =new JTextField("",10);
		t_beizhu   =new JTextField("",10);
		b_enter    =new JButton("确定");
		b_clear    =new JButton("清除");
		b_update   =new JButton("更新");
		b_back     =new JButton("返回");
		b_enter.addActionListener(this);
		b_clear.addActionListener(this);
		b_update.addActionListener(this);
		b_back.addActionListener(this);
		pan1=new JPanel();pan2=new JPanel();pan3=new JPanel();pan4=new JPanel();pan5=new JPanel();pan6=new JPanel();pan7=new JPanel();
		pan1.add(l_msg);pan2.add(l_user);pan2.add(t_user);pan3.add(l_bookname);pan3.add(t_bookname);
		pan4.add(l_data);pan4.add(t_data);pan5.add(l_beizhu);pan5.add(t_beizhu);pan6.add(b_enter);pan6.add(b_clear);pan6.add(b_update);pan6.add(b_back);
		pan7.add(l_uuser);pan7.add(t_uuser);pan7.add(b_enter);
		setLayout(new GridLayout(7,1));
		add(pan1);add(pan7);add(pan2);add(pan3);add(pan4);add(pan5);add(pan6);
		sql=new NetConn();
		setBounds(400,100,400,500);
		
		
	}
	public void actionPerformed(ActionEvent ed)
	{
		if(ed.getSource()==b_back)
		{
			this.dispose();
		}
		else if(ed.getSource()==b_clear)
		{
			t_user.setText("");t_beizhu.setText("");t_bookname.setText("");t_data.setText("");
		}
		else if(ed.getSource()==b_enter)
	    {
	    	
	    	try
		       {
		          sqll=sql.connect();
		    	  rs=sqll.executeQuery("SELECT * FROM bookbrowse where studentname="+"'"+t_uuser.getText()+"'");
			      while(rs.next())
			       {
			    	  t_user.setText(rs.getString(2));
			    	  t_bookname.setText(rs.getString(3));
			    	  t_data.setText(rs.getString(5));
			    	  t_beizhu.setText(rs.getString(6));
			    	  l_msg.setText("查询用户信息成功");
			       }
			
		        }
		       catch(SQLException e2)
		        {}
		       
	    }
		else if(ed.getSource()==b_update)
		{
			try
			{
				sqll=sql.connect();
				a2="'"+t_beizhu.getText().trim()+"'";
				a3="'"+t_user.getText().trim()+"'";
				a4="'"+t_bookname.getText().trim()+"'";
				a5="'"+t_data.getText().trim()+"'";
				String tem="UPDATE bookbrowse SET studentname="+a3+",bookname="+a4+",borrowdata="+a5+",beizhu="+a2+" WHERE studentname="+a3;
				sqll.executeUpdate(tem);
				l_msg.setText("续借成功");
			}
			catch(SQLException dd)
			{
				
			}
		}
		
	}
}