package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JFrame;

class Addbookfrm extends JFrame implements ActionListener
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_msg,l_bookname,l_company,l_author,l_pdata,l_price,l_number,l_beizhu,l_id;
	 JTextField t_bookname,t_company,t_author,t_pdata,t_price,t_number,t_beizhu,t_id;
	 JButton b_clear,b_add,b_back;
	 JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7,pan8,pan9,pan10;
	 
	 NetConn sql;
	 Statement sqll;
	 
	 String a1,a2,a3,a4,a5,a6,a7,a8,a9;

	 
	 Addbookfrm()
	 {
		 super("添加书籍窗口");
		 l_msg     =new JLabel("添加书籍        ");
		 l_bookname=new JLabel("书　　名:  ");
		 l_company =new JLabel("出版社：   ");
		 l_author  =new JLabel("作    者： ");
		 l_pdata   =new JLabel("出版日期: ");
		 l_id	   =new JLabel("索引号：  ");
		 l_price   =new JLabel("价　　格: ");
		 l_number  =new JLabel("新书数量: ");
		 l_beizhu  =new JLabel("备　　注: ");
		 t_bookname=new JTextField("",10);
		 t_company =new JTextField("",10);
		 t_author  =new JTextField("",10);
		 t_pdata   =new JTextField("",10);
		 t_id	   =new JTextField("",10);
		 t_price   =new JTextField("",10);
		 t_number  =new JTextField("",10);
		 t_beizhu  =new JTextField("",10);
		 b_clear   =new JButton("清空");
		 b_add     =new JButton("添加");
		 b_back    =new JButton("返回");
		 b_clear.addActionListener(this);
		 b_add.addActionListener(this);
		 b_back.addActionListener(this);
		 pan1=new JPanel();pan2=new JPanel();pan3=new JPanel();pan4=new JPanel();pan5=new JPanel();pan6=new JPanel();
		 pan7=new JPanel();pan8=new JPanel();pan9=new JPanel();pan10=new JPanel();
		 pan1.add(l_msg);pan2.add(l_bookname);pan2.add(t_bookname);pan3.add(l_company);pan3.add(t_company);
		 pan4.add(l_author);pan4.add(t_author);pan5.add(l_pdata);pan5.add(t_pdata);
		 pan6.add(l_id);pan6.add(t_id);pan7.add(l_price);pan7.add(t_price);
		 pan8.add(l_number);pan8.add(t_number);pan9.add(l_beizhu);pan9.add(t_beizhu);
		 pan10.add(b_clear);pan10.add(b_add);pan10.add(b_back);
		 setLayout(new GridLayout(10,1));
		 add(pan1);add(pan2);add(pan3);add(pan4);add(pan5);add(pan6);add(pan7);add(pan8);add(pan9);add(pan10);
		 
		 sql=new NetConn();
		 
		 setBounds(350,150,400,500); 
		 
	 }
	 public void actionPerformed(ActionEvent ec)
	 {
		 if(ec.getSource()==b_back)
		 {
			 dispose();
		 }
		 else if(ec.getSource()==b_clear)
		 {
			 t_bookname.setText("");
			 t_company.setText("");
			 t_author.setText("");
			 t_pdata.setText("");
			 t_id.setText("");
			 t_price.setText("");
			 t_number.setText("");
			 t_beizhu.setText("");
			 l_msg.setText("添加书籍");
		 }
		 else if(ec.getSource()==b_add)
		 {
			 if(t_bookname.getText().equals("")||t_company.getText().equals("")||t_author.getText().equals(""))
			 {
				 l_msg.setText("书名，出版社和作者不能为空！");
				 
			 }
			 else
			 {
				try
				 {
					
					 sqll=sql.connect();
					 System.out.println("---------------sqll:"+sqll);
					 a1="'"+t_bookname.getText().trim()+"'";
					 a2="'"+t_company.getText().trim()+"'";
					 a3="'"+t_author.getText().trim()+"'";
					 a4="'"+t_pdata.getText().trim()+"'";
					 a5="'"+t_id.getText().trim()+"'";
					 a6="'"+t_price.getText().trim()+"'";
					 a7="'"+t_number.getText().trim()+"'";
					 a8="'"+t_beizhu.getText().trim()+"'";
					 a9="'"+t_number.getText().trim()+"'";
					 
					
					 String temp="insert into books  (id, bookname, company, author, pressdata, price, conun, beizhu, canborrow)" 
						 +"VALUES ("+a5+","+a1+","+a2+","+a3+","+a4+","+a6+","+a7+","+a8+","+a9+")";
					 sqll.executeUpdate(temp);
					 l_msg.setText("书籍添加成功");
				 }
				 catch(SQLException e3)
				 {System.out.println("添加书籍不成功--------------------");}
			 }
		 }
	 }
	
}