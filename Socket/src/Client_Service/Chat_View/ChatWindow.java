package Client_Service.Chat_View;

import Client_Service.*;
import Interface.*;
import org.jcp.xml.dsig.internal.dom.DOMTransform;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import FriendList.*;
import javax.swing.*;

public class ChatWindow extends JFrame implements ActionListener,MouseListener{
	private Interface data;

	JPanel jpl_all,jpl_left,jpl_right;

	//Right好友列表
	JPanel jpl2,jpl1;
	JLabel jb,jb2;
	JScrollPane jsp,jsp2;
	JButton jbt1,jbt2;
	JButton jbt_addfriend;
	JPanel jpl_myfriend;

	//Left对话
	JTextArea jta;
	JTextArea jtf;
	JButton jbt_send,jbt_file;
	JPanel jpl;
	JScrollPane jsp_text,jsp_send;
	
	public ChatWindow(Interface Int) {
		// TODO Auto-generated constructor stub

		this.data = Int;

		//总体布局
		jpl_all=new JPanel(new GridLayout(1,2));
		jpl_all.setLayout(null);
		jpl_left=new JPanel(new GridLayout(2,1));
		jpl_left.setLayout(null);
		jpl_left.setBounds(0, 0, 500, 500);
		jpl_right=new JPanel(new GridLayout(4,1));
		jpl_right.setLayout(null);
		jpl_right.setBounds(500, 0, 200, 500);

//Left
		jta = new JTextArea();
		jta.setBounds(0, 27, 500, 360);
		jta.setBackground(new Color(245,245,235));

		jsp_text = new JScrollPane(jta);    //添加滚动条
		jsp_text.setBounds(0, 27, 500, 360);    //设置 JScrollPane 宽100,高200
		jsp_text.setBackground(new Color(245,245,235));

		jtf = new JTextArea();
		jtf.setBounds(0, 13, 400, 60);
		jtf.setBackground(new Color(245,245,235));

		jsp_send = new JScrollPane(jtf);    //添加滚动条
		jsp_send.setBounds(0, 13, 400, 60);    //设置 JScrollPane 宽100,高200
		jsp_send.setBackground(new Color(245,245,235));

		jbt_send = new JButton("Send");
		jbt_send.setBounds(410, 30, 70, 30);
		jbt_send.addActionListener(this);
		jpl = new JPanel(new GridLayout(1,2));
		jpl.setLayout(null);
		jpl.setBounds(0, 380, 500, 700);
		jpl.setBackground(Color.white);

		jpl.add(jsp_send);
		jpl.add(jbt_send);
		jpl_left.add(jsp_text);
		jpl_left.add(jpl);
		jpl_left.setBackground(Color.white);

		//Right
		//显示好友列表1
		jpl1=new JPanel(new GridLayout(Friend.List.size(),1,4,4));//长，宽，间隔（假设50个好友）

		JLabel []jbls=new JLabel[Friend.List.size()];


		int i = 0;
		for (String Key : Friend.List.keySet()){
			jbls[i] = new JLabel(Key);
			jbls[i].addMouseListener(this);
			jpl1.add(jbls[i]);
			i++;
		}

		jpl1.setBackground(new Color(245,245,235));

		jb=new JLabel("群内好友",JLabel.LEFT);
		Font font = new Font("黑体", Font.PLAIN, 15);//创建1个字体实例
		jb.setFont(font);//设置JLabel的字体
		jb.setForeground(Color.GRAY);//设置文字的颜色
		jb.setBounds(5, 0, 180, 30);

		jsp=new JScrollPane(jpl1);
		jsp.setBounds(5, 30, 180, 180);

		//显示好友列表2
		ArrayList<String> FL = new ArrayList<String>();
		FL = FriendList.ReadFriendList();
		int j = 0;
		jpl2=new JPanel(new GridLayout(FL.size(),1,4,4));//长，宽，间隔（假设50个好友）
		//监听每一个用户按钮
		JLabel []jbls2=new JLabel[FL.size()];
		for (String IP : FL){
			jbls2[j]=new JLabel(IP);

			jbls2[j].addMouseListener(this);
			jpl2.add(jbls2[j]);
			j++;
		}
		jpl2.setBackground(new Color(245,245,235));

		jpl_myfriend=new JPanel(new GridLayout(1, 2));
		jpl_myfriend.setBounds(5, 217, 175, 30);
		jb2=new JLabel("我的好友",JLabel.LEFT);
		jb2.setFont(font);//设置JLabel的字体
		jb2.setForeground(Color.GRAY);//设置文字的颜色

		jbt_addfriend=new JButton("好友管理");
		jbt_addfriend.addActionListener(this);

		jsp2=new JScrollPane(jpl2);
		jsp2.setBounds(5, 250, 180, 180);


		jpl_myfriend.add(jb2);
		jpl_myfriend.add(jbt_addfriend);
		jpl_myfriend.setBackground(Color.white);

		jpl_right.add(jb);
		jpl_right.add(jsp);
		jpl_right.add(jpl_myfriend);
		jpl_right.add(jsp2);
		jpl_right.setBackground(Color.white);

		jpl_all.add(jpl_left);
		jpl_all.add(jpl_right);//ClientList

		this.setTitle(Int.getUsername()+" 你已加入群聊");
		this.add(jpl_all,"Center");
		this.setSize(700, 500);
		this.setVisible(true);

		JOptionPane.showMessageDialog(this,"欢迎进入群聊\n"
				+ "您可在群内随意发言，也可以在右侧列表选择私聊对象\n\n"+"双击在线用户即可进入私聊\n"+"点击“添加好友”按钮可实现添加好友功能");

		Refresh();
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
			Int.setUsername(this.data.getUsername());
			Int.setMessage(dateString+"  "+this.data.getUsername()+" 说 : "+jtf.getText());
			//Int.setMessage(jtf.getText());
			jtf.setText("");


			Int.setSc(this.data.getSc());


			new MyClient_Send(Int).Send();
		}

		//监听jbt_addfriend按钮
		if(e.getSource()==jbt_addfriend) {
			Object[] options ={ "添加好友", "删除好友" };
			int flag = JOptionPane.showOptionDialog(null, "请选择管理方式：", "好友管理",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (flag == 0){
				String IP = String.valueOf(JOptionPane.showInputDialog(null,"请输入好友IP：\n","添加好友",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入"));
				FriendList.WriteFriendList(IP, this);
			} else {
				String IP = String.valueOf(JOptionPane.showInputDialog(null,"请输入好友IP：\n","删除好友",JOptionPane.PLAIN_MESSAGE,null,null,"在这输入"));
				FriendList.DeleteFriendList(IP, this);
			}
		}
		
	}

	public void EchoMessage(String Message){
		this.jta.append(Message);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//响应双击 
		if(e.getClickCount()==2) {

			String name = new String();
			name = ((JLabel)e.getSource()).getText();
			Interface data = new Interface();

			/*if (e.getSource() == jpl1){

				String IP = new String();
				IP = Friend.List.get(name);
				data.setIP(IP);
			} else {
				data.setIP(name);
			}*/

			String[] type = name.split("\\.");

			if (type.length == 4){
				data.setIP(name);
			} else {
				String IP = new String();
				IP = Friend.List.get(name);
				data.setIP(IP);
			}

			new MyClient(data, false).SocketClient();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)e.getSource();
		j1.setForeground(Color.gray);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel j1=(JLabel)e.getSource();
		j1.setForeground(Color.black);
	}


	public void Refresh() {

		jpl_right.remove(jsp);
		jpl1=new JPanel(new GridLayout(Friend.List.size(),1,4,4));//长，宽，间隔（假设50个好友）

		JLabel []jbls=new JLabel[Friend.List.size()];
		int i = 0;
		for (String key : Friend.List.keySet()){
			jbls[i]=new JLabel(key);
			//监听每一个用户按钮
			jbls[i].addMouseListener(this);
			jpl1.add(jbls[i]);
			i++;
		}
		jpl1.setBackground(new Color(245,245,235));
		jsp=new JScrollPane(jpl1);
		jsp.setBounds(5, 30, 180, 180);

		jpl_right.add(jsp);
		jpl_right.updateUI();

	}

	//点击叉号关闭
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			System.exit(0);
		}

	}

	public void Refresh_myfriend() {

		jpl_right.remove(jsp2);
		ArrayList<String> FL = new ArrayList<String>();
		FL = FriendList.ReadFriendList();
		int j = 0;
		jpl2=new JPanel(new GridLayout(FL.size(),1,4,4));//长，宽，间隔（假设50个好友）

		JLabel []jbls2=new JLabel[FL.size()];
		for (String IP : FL){
			jbls2[j]=new JLabel(IP);
			//监听每一个用户按钮
			jbls2[j].addMouseListener(this);
			jpl2.add(jbls2[j]);
			j++;
		}
		jpl2.setBackground(new Color(245,245,235));
		jsp2=new JScrollPane(jpl2);
		jsp2.setBounds(5, 250, 180, 180);

		jpl_right.add(jsp2);
		jpl_right.updateUI();

	}
}
