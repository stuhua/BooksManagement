package tsdbms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class Readme extends JFrame implements ActionListener
{
	JLabel  l_ver,l_lib;
  Readme()
  {
	  super("使用帮助");
	  final Font myfont=new Font("楷体_GB2312",Font.BOLD,20);
	  l_ver=new JLabel("                       三二零图书管理系统1.0正式版");
	  l_lib=new JLabel("三二零图书管理系统");
	  l_lib.setFont(myfont);
	  add(l_lib);
	  add(l_ver);
	  setBounds(350,150,300,300);
  }
  public void actionPerformed(final ActionEvent e)
  {
	  
  }
}
