package Client_Service.Chat_View;

import Client_Service.*;
import Interface.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import javax.swing.*;

public class ClientLogin extends JFrame implements ActionListener{
	
	String Username;
	
	// 得到显示器屏幕的宽高
    public int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    public int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    // 定义窗体的宽高
    public int windowsWedth = 390;
    public int windowsHeight = 220;
	
	//BackGround
	JLabel imag;
	JPanel jpl_bg;
	
	//UP
	JLabel jbl1; //标签
	
	//MID
	JPanel MID_jpl;
	JLabel MID_jb1,MID_jb2,MID_jb3;
	JTextField jtf_name;
	JPasswordField jpf_password;
	JCheckBox MID_jcb1,MID_jcb2;
	
	
	//DOWN
	JPanel jpl;//布局
	JButton jbt1,jbt2;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientLogin clientLogin = new ClientLogin();
		
	}
	
	public ClientLogin() {
			
			//North
		   JLabel imag=new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\back.gif"));
		   imag.setPreferredSize(new Dimension(380,100));
		   //imag.setBounds(0, 0, 0, 0);
		   jpl_bg=new JPanel();
		   jpl_bg.add(imag);
			 
			//MID
			MID_jpl=new JPanel(new GridLayout(1,2));//1行2列
//			MID_jpl.setLayout(null);
//			MID_jpl.setBounds(0, 100, 390, 220);
			//JLabel imag_user=new JLabel(new ImageIcon("Image/Use1r.jpg"));
			//imag_user.setBounds(20, 0, 60, 60);
			//imag_user.setPreferredSize(new Dimension(60,20));
			
			MID_jb1=new JLabel("用户名",JLabel.CENTER);

			jtf_name=new JTextField();
			
			//MID_jpl.add(imag_user);
			MID_jpl.add(MID_jb1);
			MID_jpl.add(jtf_name); 
			
			//South
			jpl=new JPanel();
			jbt1=new JButton("Login");
			//监听
			jbt1.addActionListener(this);
			jbt2=new JButton("Exit");
			jbt2.addActionListener(this);
			
			jpl.add(jbt1);
			jpl.add(jbt2);
			
			this.add(jpl_bg,"North");
			this.add(MID_jpl,"Center");
			this.add(jpl,"South");
			this.setSize(windowsWedth,windowsHeight);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//system.exit()
			this.setBounds((width - windowsWedth) / 2,
	                (height - windowsHeight) / 2, windowsWedth, windowsHeight);
			this.setVisible(true);//可视化
			
			
		}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//点击Login
		if(e.getSource()==jbt1) {
			Interface Int = new Interface();

			Int.setUsername(jtf_name.getText());
			try{
				Int.setIP("127.0.0.1");
			} catch (Exception e1){
				JOptionPane.showMessageDialog(null,"无法获取本机IP！");
			}

			new MyClient_Service().start();//启动监听端口服务
			new MyClient_File().start();//启动监听文件传输端口服务
			this.dispose();

			new MyClient(Int, true).SocketClient();//连接公共聊天室
		}
		
		//点击Exit
		if(e.getSource()==jbt2) {
			this.dispose();
			System.exit(0);
		}
	}


}
