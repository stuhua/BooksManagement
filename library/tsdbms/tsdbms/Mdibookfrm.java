package tsdbms;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;

class Mdibookfrm extends JFrame implements ActionListener
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l_msg,l_idd,l_bookname,l_company,l_author,l_pdata,l_price,l_number,l_beizhu;
	 JTextField t_idd,t_bookname,t_company,t_author,t_pdata,t_price,t_number,t_beizhu;
	 JButton b_clear,b_enter,b_update,b_back;
	 JPanel pan1,pan2,pan3,pan4,pan5,pan6,pan7,pan8,pan9,pan10;
	 
	 NetConn sql;
	 Statement sqll;
	 ResultSet rs;
	 String a1,a2,a3,a4,a5,a6,a7;
	 int a8;
	 
	 Mdibookfrm()
	 {
		 super("修改书籍窗口");
		 l_msg     =new JLabel("修改书籍        ");
		 l_idd     =new JLabel("         输入书籍ID:  ");
		 l_bookname=new JLabel("书名：      ");
		 l_company =new JLabel("出版社：  ");
		 l_author  =new JLabel("作者：      ");
		 l_pdata   =new JLabel("出版日期：");
		 l_price   =new JLabel("价格 ：     ");
		 l_number  =new JLabel("新书数量：");
		 l_beizhu  =new JLabel("备注：      ");
		 t_idd     =new JTextField("",8);
		 t_bookname=new JTextField("",10);
		 t_company=new JTextField("",10);
		 t_author=new JTextField("",10);
		 t_pdata=new JTextField("",10);
		 t_price=new JTextField("",10);
		 t_number=new JTextField("",10);
		 t_beizhu=new JTextField("",10);
		 b_clear=new JButton("清空");
		 b_enter=new JButton("查询");
		 b_update=new JButton("更新");
		 b_back=new JButton("返回");
		 b_clear.addActionListener(this);
		 b_enter.addActionListener(this);
		 b_update.addActionListener(this);
		 b_back.addActionListener(this);
		 pan1=new JPanel();pan2=new JPanel();pan3=new JPanel();pan4=new JPanel();pan5=new JPanel();pan6=new JPanel();pan7=new JPanel();pan8=new JPanel();pan9=new JPanel();pan10=new JPanel();
		 pan1.add(l_msg);pan2.add(l_bookname);pan2.add(t_bookname);pan3.add(l_company);pan3.add(t_company);
		 pan4.add(l_author);pan4.add(t_author);pan5.add(l_pdata);pan5.add(t_pdata);pan6.add(l_price);pan6.add(t_price);
		 pan7.add(l_number);pan7.add(t_number);pan8.add(l_beizhu);pan8.add(t_beizhu);pan9.add(b_clear);pan9.add(b_update);pan9.add(b_back);
		 pan10.add(l_idd);pan10.add(t_idd);pan10.add(b_enter);
		 setLayout(new GridLayout(10,1));
		 add(pan1);add(pan10);add(pan2);add(pan3);add(pan4);add(pan5);add(pan6);add(pan7);add(pan8);add(pan9);
		 
		 sql=new NetConn();
		 
		 setBounds(350,150,400,500);
		 setVisible(true);
		 
	 }
	 public void actionPerformed(ActionEvent ef)
	 {
		 if(ef.getSource()==b_back)
		 {
			 dispose();
		 }
		 else if(ef.getSource()==b_update)
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
					 a1="'"+t_bookname.getText().trim()+"'";
					 a2="'"+t_company.getText().trim()+"'";
					 a3="'"+t_author.getText().trim()+"'";
					 a4="'"+t_pdata.getText().trim()+"'";
					 a5="'"+t_price.getText().trim()+"'";
					 a6="'"+t_number.getText().trim()+"'";
					 a7="'"+t_beizhu.getText().trim()+"'";
					 a8=Integer.parseInt(t_number.getText());
					 String temp="UPDATE books set bookname="+a1+", company="+a2
					 +", author="+a3+", pressdata="+a4+",price="+a5+",conun="+a6
					 +",beizhu="+a7+",canborrow="+a8+" where id='"+t_idd.getText()+"'";
					 sqll.executeUpdate(temp);
					 l_msg.setText("书籍修改成功");
				 }
				 catch(SQLException e3)
				 {}
			 }
		 }
		 else if(ef.getSource()==b_clear)
		 {
			 t_bookname.setText("");
			 t_company.setText("");
			 t_author.setText("");
			 t_pdata.setText("");
			 t_price.setText("");
			 t_number.setText("");
			 t_beizhu.setText("");
			 l_msg     =new JLabel("修改书籍        ");
		 }
		 else if(ef.getSource()==b_enter)
		 {
			 try
			 {
				 sqll=sql.connect();
				 String temp="SELECT * FROM books where id='"+t_idd.getText()+"'";
				 rs=sqll.executeQuery(temp);
				 if(rs.next())
				 {
					 t_bookname.setText(rs.getString(2));
					 t_company.setText(rs.getString(3));
					 t_author.setText(rs.getString(4));
					 t_pdata.setText(String.valueOf(rs.getDate(5)));
					 t_price.setText(String.valueOf(rs.getInt(6)));
					 t_number.setText(String.valueOf(rs.getInt(7)));
					 t_beizhu.setText(rs.getString(8));
					 
					 l_msg.setText("找到书籍");
				 }
				 else
				 {
					 l_msg.setText("没有找到书籍！");
				 }
			 }
			 catch(SQLException e4)
			 {}
		 }
	 }
}
