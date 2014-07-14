package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
class Loginfrm extends Dialog implements ActionListener
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	JLabel l_name,l_pass,l_error;
	JTextField t_name;
	JButton b_enter,b_cancle,b_clear;
	JPanel pan1,pan2,pan3,pan4;
	JPasswordField t_pass;
	
	String sname,spass;
	int sunit;
	
	NetConn sql;
	Statement sqll;
	ResultSet rs;
	
	Librarybox lbox;
	
	int until=0;
	
	Loginfrm(Frame f,String s)
	{
		super(f,s);
		l_name=new JLabel("名字：");
		l_pass=new JLabel("密码：");
		l_error=new JLabel("请输入用户名和密码登陆");
		t_name=new JTextField("",10);
		t_pass=new JPasswordField("",10);
		t_pass.setEchoChar('*');
		b_enter=new JButton("确定");
		b_enter.addActionListener(this);
		b_cancle=new JButton("取消");
		b_cancle.addActionListener(this);
		b_clear=new JButton("清除");
		b_clear.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan4=new JPanel();
		pan1.add(l_name);pan1.add(t_name);
		pan2.add(l_pass);pan2.add(t_pass);
		pan3.add(l_error);
		pan4.add(b_enter);pan4.add(b_cancle);pan4.add(b_clear);
		setLayout(new GridLayout(4,1));
		add(pan1);add(pan2);add(pan3);add(pan4);
		
		sql=new NetConn();
		
		setBounds(400,200,300,300);
		setVisible(false);
	}
	@SuppressWarnings({ "deprecation", "static-access" })
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b_enter)
		{
			if(until<=4)
			{
			   String text2 = t_pass.getText();
			String text = text2;
			if(t_name.getText().equals("")||text.equals(""))
			   {	
				     l_error.setText("用户名和密码不能为空");
			   }
			   else
			   {
				   try
				   {
				      sqll=sql.connect();
				      
//				      ---------------------------------------------
				      System.out.println(t_name.getText());
				      System.out.println(l_pass.getText());
				      
				      
			    	  rs=sqll.executeQuery("SELECT * FROM users where username="+"'"+t_name.getText()+"'");
				      while(rs.next())
				       {
				        	sname=rs.getString(2);  spass=rs.getString(3);sunit=Integer.parseInt(rs.getString(4));
				        	
				        			if(text.equals(spass))
				        			{
				        				switch(sunit)
				        				{
				        					case 1:
				        					{
				        						l_error.setText("登陆成功");				        
				        						t_name.setText("");
				        						t_pass.setText("");
				        						lbox=new Librarybox();
				        						lbox.bookfi.setEnabled(true);
				        						lbox.bookse.setEnabled(true);
				        						lbox.bookth.setEnabled(true);
				        						lbox.bookfo.setEnabled(true);
				        						lbox.mi_system_manger.setEnabled(true);
				        						lbox.setVisible(true);
				        						this.dispose();
				        						break;
				        					}
				        					case 2:
				        					{
				        						l_error.setText("登陆成功");				        
				        						t_name.setText("");
				        						t_pass.setText("");
				        						lbox=new Librarybox();
				        						lbox.bookfi.setEnabled(false);
				        						lbox.bookse.setEnabled(false);
				        						lbox.bookth.setEnabled(true);
				        						lbox.bookfo.setEnabled(false);
				        						lbox.mi_system_manger.setEnabled(false);
				        						lbox.setVisible(true);
				        						this.dispose();
				        						break;
				        					}
				        					case 3:
				        					{
				        						l_error.setText("登陆成功");				        
				        						t_name.setText("");
				        						t_pass.setText("");
				        						lbox=new Librarybox();
				        						lbox.bookfi.setEnabled(true);
				        						lbox.bookse.setEnabled(false);
				        						lbox.bookth.setEnabled(false);
				        						lbox.bookfo.setEnabled(true);
				        						lbox.mi_system_manger.setEnabled(false);
				        						lbox.fi_msglabel_user.setEnabled(false);
				        						lbox.setVisible(true);
				        						this.dispose();
				        						break;
				        					}
				        				}
				        			}
				        			else
				        			{
				        				l_error.setText("用户名或密码错误！");
				        				until++;
				        			}
				        		
				        			
				        	
				       }
				
			        }
			       catch(SQLException e2)
			        {}
			       
			   }
			}
			else
			{
				l_error.setText("你已经超出登陆次数");
				t_name.setEnabled(false);
				t_pass.setEnabled(false);
				b_enter.setEnabled(false);
				b_clear.setEnabled(false);
			}
		}
		else if(e.getSource()==b_clear)
		{
			t_name.setText("");
			t_pass.setText("");
			l_error.setText("请输入用户名和密码登陆");
		}
		else if(e.getSource()==b_cancle)
		{
			dispose();
			
		}
	}
	
 }
 