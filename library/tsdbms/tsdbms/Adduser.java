package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
class Adduser extends JDialog  implements ActionListener
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_addname,l_fenge,l_name,l_pass,l_enter,l_unit;
	JTextField t_aname;
	Choice c_aunit;
	JButton b_addname,b_cancle;
	JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7;
	JPasswordField t_apass,t_aenter;
	
	NetConn sql;
	Statement sqll;
	ResultSet rs;
	
	Adduser(Frame f,String s)
	{
		super(f,s);
		l_addname=new JLabel("添加用户         ");
		l_fenge=new JLabel("---------------------------------------------------");
		l_name=new JLabel("名字：");
		l_pass=new JLabel("密码：");
		l_enter=new JLabel("确认：");
		l_unit=new JLabel("权限：");
		t_aname=new JTextField("",10);
		t_apass=new JPasswordField("",10);
		t_apass.setEchoChar('*');
		t_aenter=new JPasswordField("",10);
		t_aenter.setEchoChar('*');
		c_aunit=new Choice();
		c_aunit.add("1");
		c_aunit.add("2");
		c_aunit.add("3");
		b_addname=new JButton("添加");
		b_addname.addActionListener(this);
		b_cancle=new JButton("放弃");
		b_cancle.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan4=new JPanel();
		pan5=new JPanel();
		pan6=new JPanel();
		pan7=new JPanel();
		pan1.add(l_addname);pan2.add(l_fenge);
		pan2.add(l_name);pan2.add(t_aname);
		pan3.add(l_pass);pan3.add(t_apass);
		pan4.add(l_enter);pan4.add(t_aenter);
		pan5.add(b_addname);pan5.add(b_cancle);
		pan6.add(l_fenge);
		pan7.add(l_unit);pan7.add(c_aunit);
		
		sql=new NetConn();
		
		setLayout(new GridLayout(7,1));
		add(pan1);add(pan6);add(pan2);add(pan3);add(pan4);add(pan7);add(pan5);
		setBounds(450,150,250,300);
		
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getSource()==b_cancle)
		{
			dispose();
		}
	    else if(e.getSource()==b_addname)
	    {
	    	String text2 = t_aenter.getText();
			String text = text2;
			String text21 = text;
			String text1 = text21;
			String  s=text1.trim();
	    	if(t_apass.getText().trim().equals(s))
	    	{
	    		
	    		try 
	    		{
	    			sqll=sql.connect();
					rs=sqll.executeQuery("SELECT * FROM users where username="+"'"+t_aname.getText()+"'");					
					if(rs.next())
					{
						
					     l_addname.setText("用户已经存在,添加失败！");
	
					}
					else
					   {
						   String s2="'"+t_aname.getText().trim()+"'";
						   String s3="'"+t_apass.getText().trim()+"'";
						   String s4="'"+c_aunit.getSelectedItem().trim()+"'";
						   String temp="INSERT INTO users (username, password, unit) VALUES ("+s2+","+s3+","+s4+")";
						   sqll.executeUpdate(temp);
						   l_addname.setText("用户添加成功");
					   }
				} 
	    		catch (SQLException e1) 
	    		{
					
					e1.printStackTrace();
				}
	    		
	    	}
	    	else
	    	{
	    		l_addname.setText("两次输入的密码不一致！");
	    		t_apass.setText("");
	    		t_aenter.setText("");
	    	}
	    }
	}
	
 }