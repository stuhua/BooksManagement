package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
class Delbookfrm extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_bookname;
	JTextField t_bookname;
	JButton b_delbook,b_back;
	JPanel pan1,pan2,pan3;
	
	NetConn sql;
	Statement sqll;
	ResultSet rs;
	
	Delbookfrm()
	{
		
		l_bookname=new JLabel("输入你要删除的书籍的名称：");
		t_bookname=new JTextField("",10);
		b_delbook=new JButton("删除");
		b_back=new JButton("返回");
		b_delbook.addActionListener(this);
		b_back.addActionListener(this);
		pan1=new JPanel();pan2=new JPanel();pan3=new JPanel();
	    pan1.add(l_bookname);pan2.add(t_bookname);pan3.add(b_delbook);pan3.add(b_back);
	    setLayout(new GridLayout(3,1));
	    add(pan1);add(pan2);add(pan3);
		setBounds(400,300,300,350);
		
		sql=new NetConn();
		
		
	}
	public void actionPerformed(ActionEvent ed)
	{
		if(ed.getSource()==b_delbook)
		{
			if(t_bookname.getText().equals(""))
			 {
				 l_bookname.setText("书名不能为空！");
				 
			 }
			else 
			{
				try
				{
					sqll=sql.connect();
					String temp="SELECT * FROM books where bookname="+"'"+t_bookname.getText()+"'";
					rs = sqll.executeQuery(temp);
					if(rs.next())
					{
						sqll.executeUpdate("delete from books where bookname="+"'"+t_bookname.getText()+"'");
						l_bookname.setText("书籍删除成功! ");
					}
					else
					{
						l_bookname.setText("没有找到书籍！");
					}
				}
				catch(SQLException e6)
				{}
			}
		}
		else if(ed.getSource()==b_back)
		{
			dispose();
		}
	}
}