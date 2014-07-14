package tsdbms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

class Borlistbookfrm extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Object a[][]; 
	Object colname[]={"书名","读者","读者号","借书时间","还书时间","备注","是否归还"};
	JLabel l_readername,l_bookname;
	JTextField t_readername,t_bookname;
	JTable table;
	Container container;
	JButton b_enter,b_back;
	JProgressBar p_bar;
	JPanel pan1,pan2,pan3;
	String a1,a2;
	
	NetConn sql;
	Statement sqll;
	ResultSet rs;
	
	Borlistbookfrm()
	{
		super("借书列表");
		l_bookname=new JLabel("查图书：");
		l_readername=new JLabel("查读者：");
		t_bookname=new JTextField("",10);
		t_readername=new JTextField("",10);
		a=new Object[30][7];
		table=new JTable(a,colname);
		setSize(300,300);setVisible(true);
		b_enter=new JButton("确定");
		b_back=new JButton("返回");
		b_enter.addActionListener(this);
		b_back.addActionListener(this);
		pan1=new JPanel();pan2=new JPanel();pan3=new JPanel();
		pan1.add(l_bookname);pan1.add(t_bookname);
		pan1.add(l_readername);pan1.add(t_readername);pan1.add(b_enter);pan1.add(b_back);
		
		p_bar=new JProgressBar(JProgressBar.VERTICAL,0,50);
		p_bar.setStringPainted(true);
		container=getContentPane();
		container.add(pan1,BorderLayout.SOUTH);
		container.add(new JScrollPane(table),BorderLayout.CENTER);
		container.add(p_bar,BorderLayout.WEST);
		
		sql=new NetConn();
		setBounds(230,150,650,350);
	}
	public void  actionPerformed(ActionEvent eb)
	{
		if(eb.getSource()==b_back)
		{
			dispose();
		}
		if(eb.getSource()==b_enter)
		{
			try
			{
				int i=0;
				sqll=sql.connect();
				a1="'"+"%"+t_bookname.getText().trim()+"%"+"'";
				a2="'"+"%"+t_readername.getText().trim()+"%"+"'";
				while(i<10)
				{
					a[i][0]=a[i][1]=a[i][2]=a[i][3]=a[i][4]=a[i][5]=a[i][6]="";
					i++;					                             
				}
				i=0;
				String temp="select * from bookbrowse where bookname like "+a1+" and readername like "+a2;
				rs=sqll.executeQuery(temp);
				while(rs.next())
				{
					a[i][0]=rs.getString(2);a[i][1]=rs.getString(3);a[i][2]=rs.getString(4);a[i][3]=rs.getDate(5);a[i][4]=rs.getDate(6);
					a[i][5]=rs.getString(7);a[i][6]=rs.getString(8);
					i++;
					p_bar.setValue(i);p_bar.setString("查询了"+i+"条记录");
				}
			}
			catch(SQLException ed)
			{
				
			}
		}
	}
}