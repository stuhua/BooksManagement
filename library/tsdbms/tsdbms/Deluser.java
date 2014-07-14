package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
 class Deluser extends JDialog implements ActionListener
 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_name,l_pass,l_error;
	JTextField t_name;
	JButton b_enter,b_cancle;
	JPanel pan1,pan2,pan3,pan4;
	JPasswordField t_pass;
	
	String sname,spass;
	
	NetConn sql;
	Statement sqll;
	ResultSet rs;
	
	int until=1;
	
	Deluser(Frame f,String s)
	{
		super(f,s);
		l_name=new JLabel("名字：");
		l_pass=new JLabel("密码：");
		l_error=new JLabel("请输入用户名和密码删除");
		t_name=new JTextField("",10);
		t_pass=new JPasswordField("",10);
		t_pass.setEchoChar('*');
		b_enter=new JButton("确定");
		b_enter.addActionListener(this);
		b_cancle=new JButton("取消");
		b_cancle.addActionListener(this);
		pan1=new JPanel();
		pan2=new JPanel();
		pan3=new JPanel();
		pan4=new JPanel();
		pan1.add(l_name);pan1.add(t_name);
		pan2.add(l_pass);pan2.add(t_pass);
		pan3.add(l_error);
		pan4.add(b_enter);pan4.add(b_cancle);
		setLayout(new GridLayout(4,1));
		add(pan1);add(pan2);add(pan3);add(pan4);
		
		sql=new NetConn();
		
		setBounds(400,200,300,300);
		setVisible(false);
	}
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b_enter)
		{
			sqll=sql.connect();
			try
			{
			  sqll.executeUpdate("delete from users where username="+"'"+t_name.getText()+"'"+"and password="+"'"+t_pass.getText()+"'");
			  l_error.setText("用户删除成功");
			  
			}
			catch(SQLException e2)
			{}
		}
		else if(e.getSource()==b_cancle)
		{
			dispose();
		}
	 }
	
 }
