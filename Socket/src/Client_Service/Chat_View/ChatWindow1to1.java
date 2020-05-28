package Client_Service.Chat_View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import Client_Service.MyClient_Send;
import Client_Service.MyClient_SendFile;
import Interface.Interface;

public class ChatWindow1to1 extends JFrame implements ActionListener{

	private Interface data;

	JTextArea jta;//文本域
	JTextArea jtf;//发送文本域
	JButton jbt_send,jbt_file;
	JScrollPane jsp_text,jsp_send;
	JPanel jpl_all,jpl_up,jpl_down;
	JPanel jpl_down_left,jpl_down_right;

	public ChatWindow1to1(Interface Int) {
		// TODO Auto-generated constructor stub
		data = Int;

		jpl_all = new JPanel(new GridLayout(2,1));
		jpl_all.setLayout(null);
		jpl_all.setBackground(Color.white);
		jpl_up=new JPanel();
		jpl_up.setLayout(null);
		jpl_up.setBackground(Color.white);
		jpl_down=new JPanel();
		jpl_down.setLayout(null);
		jpl_down.setBackground(Color.white);

		jpl_down_left=new JPanel();
		jpl_down_left.setLayout(null);
		jpl_down_left.setBackground(Color.white);
		jpl_down_right = new JPanel(new GridLayout(2,1));
		jpl_down_right.setLayout(null);
		jpl_down_right.setBackground(Color.white);

		//上方文本域
		jta = new JTextArea();
		jta.setBounds(0, 0, 400, 200);
		jta.setBackground(new Color(245,245,235));
		jsp_text = new JScrollPane(jta);    //添加滚动条
		jsp_text.setBounds(0, 0, 400, 200);
		jsp_text.setBackground(new Color(245,245,235));

		jpl_up.add(jsp_text);
		jpl_up.setBounds(0, 0, 400, 210);

		//下方发送
		//左侧文本编辑框
		jtf = new JTextArea();
		jtf.setBounds(0, 0, 300, 90);
		jtf.setBackground(new Color(245,245,235));
		jsp_send = new JScrollPane(jtf);    //添加滚动条
		jsp_send.setBounds(0, 0, 300, 90);
		jsp_send.setBackground(new Color(245,245,235));

		jpl_down_left.add(jsp_send);
		jpl_down_left.setBounds(0, 0, 300, 90);

		//右侧按钮框
		jbt_send = new JButton("Send");
		jbt_send.setBounds(10, 10, 80, 30);
		jbt_send.addActionListener(this);
		jbt_file = new JButton("File");
		jbt_file.setBounds(10, 50, 80, 30);
		jbt_file.addActionListener(this);

		jpl_down_right.add(jbt_send);
		jpl_down_right.add(jbt_file);
		jpl_down_right.setBounds(300, 0, 100, 100);

		//down
		jpl_down.add(jpl_down_left);
		jpl_down.add(jpl_down_right);
		jpl_down.setBounds(0, 210, 400, 90);

		jpl_all.add(jpl_up);
		jpl_all.add(jpl_down);

		this.setTitle("正在和" + data.getIP() + "聊天");
		//this.setIconImage(new ImageIcon("").getImage());
		this.add(jpl_all);
		this.setBounds(700, 100, 416, 345);
		this.setVisible(true);

	}


	public void EchoMessage(String Message){
		this.jta.append(Message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Date date=new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);

		//监听Send按钮
		if(e.getSource()==jbt_send) {
			Interface Int = new Interface();//读取发送信息
			Int.setMessage(dateString+"  "+this.data.getIP()+" 说 : "+jtf.getText());
			jta.append(dateString+"  "+this.data.getIP()+" 说 : "+jtf.getText() + "\n");
			//Int.setMessage(jtf.getText());
			jtf.setText("");


			Int.setSc(this.data.getSc());


			new MyClient_Send(Int).Send();
		}
		//监听File按钮
		if(e.getSource()==jbt_file) {
			String path = String.valueOf(JOptionPane.showInputDialog(null,"请输入储存路径：\n","文件传输",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入"));
			new MyClient_SendFile(this.data.getIP(), path).Connet();
		}

	}


}
