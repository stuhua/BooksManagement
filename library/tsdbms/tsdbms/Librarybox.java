package tsdbms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Librarybox extends JFrame implements ActionListener
{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//JLabel mlabel;
	JMenuBar mainmenu;
	JPanel contentPane;
    BorderLayout borderLayout1 = new BorderLayout();
    JDesktopPane desktop = new javax.swing.JDesktopPane();
	
	static JMenu system,bookse,bookth,bookfo,bookfi,booksex,mi_system_manger;
	JMenuItem mi_system_login,mifi_system_adduser,mifi_system_moduser,mifi_system_deluser,mi_system_exit;
	JMenuItem se_book_addbook,se_book_modifybook,se_book_delbook;
	JMenuItem th_book_borrowmsg,th_book_borrowmsgmodif;
	JMenuItem fo_book_returnmsg,fo_book_returnmsgmodif;
	JMenuItem fi_msglabel_book,fi_msglabel_borrow,fi_msglabel_user,fi_msglabel_reader;
	JMenuItem sex_edition;
	
	Loginfrm frml=new Loginfrm(this,"登录窗口");
	Adduser frma=new Adduser(this,"添加用户");
	Mdiuser frmm=new Mdiuser(this,"修改用户");
	Deluser frmd=new Deluser(this,"删除用户");
	
	Addbookfrm frmab;Mdibookfrm frmmb;Delbookfrm frmdb;Borbookfrm frmbb;BorMsgbookfrm frmbmb;
	Booklistfrm  frmbl;Borlistbookfrm frmblb;Userlistfrm frmul;ReturnMsgbookfrm frmrmb;Returnbookfrm frmrb;
	Readme frmread;ReaderInfoList frmreaderinfo;
	
	Librarybox()
	{
		super("图书管理系统");
		//Font myFont=new Font("楷体_GB2312",Font.BOLD,60);
		//mlabel=new JLabel("    希望图书管理系统");
		//mlabel.setFont(myFont);
		//add(mlabel);
		
		contentPane = (JPanel) getContentPane();
        contentPane.setLayout(borderLayout1);
        contentPane.add(desktop, java.awt.BorderLayout.CENTER);
        
		mainmenu=new JMenuBar();
		system=new JMenu("系统管理");
		mi_system_login=new JMenuItem("用户登录");
		mi_system_manger=new JMenu("用户管理");
		mifi_system_adduser=new JMenuItem("添加用户");
		mifi_system_moduser=new JMenuItem("修改用户");
		mifi_system_deluser=new JMenuItem("删除用户");
		mi_system_manger.add(mifi_system_adduser);
		mi_system_manger.add(mifi_system_moduser);
		mi_system_manger.add(mifi_system_deluser);
		mi_system_exit=new JMenuItem("退出");
		mi_system_login.addActionListener(this);
		mi_system_exit.addActionListener(this);
		mifi_system_adduser.addActionListener(this);
		mifi_system_moduser.addActionListener(this);
		mifi_system_deluser.addActionListener(this);
		system.add(mi_system_login);
		system.add(mi_system_manger);
		system.addSeparator();
		system.add(mi_system_exit);
		mainmenu.add(system);
		
		
		bookse=new JMenu("书籍管理" );
		se_book_addbook=new JMenuItem("添加书籍");
		se_book_modifybook=new JMenuItem("修改书籍");
		se_book_delbook=new JMenuItem("删除书籍");
		se_book_addbook.addActionListener(this);
		se_book_modifybook.addActionListener(this);
		se_book_delbook.addActionListener(this);
		bookse.add(se_book_addbook);
		bookse.add(se_book_modifybook);
		bookse.add(se_book_delbook);
		mainmenu.add(bookse);
		
		
		bookth=new JMenu("借阅管理" );
		th_book_borrowmsg=new JMenuItem("出借图书");
		th_book_borrowmsgmodif=new JMenuItem("续借图书");
		th_book_borrowmsg.addActionListener(this);
		th_book_borrowmsgmodif.addActionListener(this);
		bookth.add(th_book_borrowmsg);
		bookth.addSeparator();
		bookth.add(th_book_borrowmsgmodif);
		mainmenu.add(bookth);
		
		
		bookfo=new JMenu("还书管理");
		fo_book_returnmsg=new JMenuItem("当前还书");
		fo_book_returnmsgmodif=new JMenuItem("超期滞纳金缴纳");
		fo_book_returnmsg.addActionListener(this);
		fo_book_returnmsgmodif.addActionListener(this);
		bookfo.add(fo_book_returnmsg);
		bookfo.addSeparator();
		bookfo.add(fo_book_returnmsgmodif);
		mainmenu.add(bookfo);
		
		
		bookfi=new JMenu("信息查询");
		fi_msglabel_book=new JMenuItem("图书列表");
		fi_msglabel_borrow=new JMenuItem("借阅情况列表");
		fi_msglabel_user=new JMenuItem("用户列表");
		fi_msglabel_reader=new JMenuItem("读者信息查询");
		fi_msglabel_book.addActionListener(this);
		fi_msglabel_borrow.addActionListener(this);
		fi_msglabel_user.addActionListener(this);
		fi_msglabel_reader.addActionListener(this);
		bookfi.add(fi_msglabel_book);
		bookfi.add(fi_msglabel_borrow);
		bookfi.add(fi_msglabel_user);
		bookfi.addSeparator();
		bookfi.add(fi_msglabel_reader);
		mainmenu.add(bookfi);
		
		
		booksex=new JMenu("帮助");
		sex_edition=new JMenuItem("—使用帮助—");
		sex_edition.addActionListener(this);
		booksex.add(sex_edition);
		mainmenu.add(booksex);
		
		
		
		this.setJMenuBar(mainmenu);
		
		setBounds(200,90,800,600);
		this.setVisible(true);
		this.setResizable(false);
        loadBackgroundImage();
		
	}
	protected void loadBackgroundImage(){
        ImageIcon icon = new ImageIcon("desktop.jpg");
        //ImageIcon icon = new ImageIcon(JF_main.class.getResource("main_bg.jpg"));
        JLabel jl = new JLabel(icon);
        jl.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
       desktop.add(jl,new Integer(Integer.MIN_VALUE));
    //  desktop.add(jl,BorderLayout.CENTER);
     
      
}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="退出")
		{
			dispose();
			System.exit(0);
		}
		else if(e.getActionCommand()=="添加用户")
		{
			frma.setVisible(true);
			frma.setResizable(false);
		}
		else if(e.getActionCommand()=="用户登录")
		{
			this.setVisible(false);
			this.dispose();
			frml.setVisible(true);
			frml.setResizable(false);
			
		}
		else if(e.getActionCommand()=="修改用户")
		{
			frmm.setVisible(true);
			frmm.setResizable(false);
		}
		else if(e.getActionCommand()=="删除用户")
		{
			frmd.setVisible(true);
			frmd.setResizable(false);
		}
		else if(e.getActionCommand()=="添加书籍")
		{
		    frmab=new Addbookfrm();
		    frmab.setVisible(true);
		    frmab.setResizable(false);
		}
		else if(e.getActionCommand()=="修改书籍")
		{
			frmmb=new Mdibookfrm();
			frmmb.setVisible(true);
			frmmb.setResizable(false);
		}
		else if(e.getActionCommand()=="删除书籍")
		{
			frmdb=new Delbookfrm();
			frmdb.setVisible(true);
			frmdb.setResizable(false);
		}
		else if(e.getActionCommand()=="出借图书")
		{
			frmbb=new Borbookfrm();
			frmbb.setVisible(true);
			frmbb.setResizable(false);
		}
		else if(e.getActionCommand()=="续借图书")
		{
			frmbmb=new BorMsgbookfrm();
			frmbmb.setVisible(true);
			frmbmb.setResizable(false);
		}
		else if(e.getActionCommand()=="当前还书")
		{
			frmrb=new Returnbookfrm();
			frmrb.setVisible(true);
			frmrb.setResizable(false);
		}
		else if(e.getActionCommand()=="超期滞纳金缴纳")
		{
			frmrmb=new ReturnMsgbookfrm();
			frmrmb.setVisible(true);
			frmrmb.setResizable(false);
		}
		else if(e.getActionCommand()=="图书列表")
		{
			frmbl=new Booklistfrm();
			frmbl.setVisible(true);
		}
		else if(e.getActionCommand()=="借阅情况列表")
		{
			frmblb=new Borlistbookfrm();
			frmblb.setVisible(true);
		}
		else if(e.getActionCommand()=="用户列表")
		{
			frmul=new Userlistfrm();
			frmul.setVisible(true);
		}
		else if(e.getActionCommand()=="读者信息查询")
		{
			frmreaderinfo=new ReaderInfoList();
			frmreaderinfo.setVisible(true);
			frmreaderinfo.setResizable(false);
		}
		else if(e.getActionCommand()=="—使用帮助—")
		{
			frmread=new Readme();
			frmread.setVisible(true);
			frmread.setResizable(false);
		}
	}
}
 


