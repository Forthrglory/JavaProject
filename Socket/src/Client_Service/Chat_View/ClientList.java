package Client_Service.Chat_View;

import Interface.Interface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ClientList extends JFrame implements ActionListener,MouseListener{
	//
	JPanel jpl1,jpl2,jpl3;
	JLabel jb;
	JScrollPane jsp;
	JButton jbt1,jbt2;
	
	//
	JPanel jpl21,jpl22,jpl23;
	JLabel jb2;
	JScrollPane jsp2;
	JButton jbt21,jbt22;

	CardLayout c1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientList list=new ClientList();
	}
	
	public ClientList() {
		// TODO Auto-generated constructor stub
		//��ʾ�����б�
		jpl1=new JPanel(new BorderLayout());//���岼�ֹ���
		
		jpl2=new JPanel(new GridLayout(50,1,4,4));//���������������50�����ѣ�
		jpl3=new JPanel(new GridLayout(2,1));
		JLabel []jbls=new JLabel[50];
		for(int i=0;i<jbls.length;i++) {
			jbls[i]=new JLabel(i+"wow");
			//����ÿһ���û���ť
			jbls[i].addMouseListener(this);
			jpl2.add(jbls[i]);
		}
		
		jb=new JLabel("�ҵĺ���",JLabel.CENTER);
		jbt2=new JButton("İ����");
		jbt2.addActionListener(this);//�����˰�ť
		jbt1=new JButton("��Ӻ���");
		
		jpl3.add(jbt2);
		jpl3.add(jbt1);
		
		jsp=new JScrollPane(jpl2);
		jpl1.add(jb,"North");
		jpl1.add(jsp,"Center");
		jpl1.add(jpl3,"South");
		
		//��ʾİ�����б�
		jpl21=new JPanel(new BorderLayout());//���岼�ֹ���
		
		jpl22=new JPanel(new GridLayout(20,1,4,4));//���������������50�����ѣ�
		jpl23=new JPanel(new GridLayout(2,1));
		JLabel []jbls2=new JLabel[20];
		for(int i=0;i<jbls2.length;i++) {
			jbls2[i]=new JLabel(i+"hoh");
			//����ÿһ���û���ť
			jbls2[i].addMouseListener(this);
			jpl22.add(jbls2[i]);
		}
		
		jb2=new JLabel("İ����",JLabel.CENTER);
		jbt22=new JButton("�ҵĺ���");
		jbt22.addActionListener(this);//�����˰�ť
		jbt21=new JButton("��Ӻ���");
		
		jpl23.add(jbt22);
		jpl23.add(jbt21);
		
		jsp2=new JScrollPane(jpl22);
		jpl21.add(jb2,"North");
		jpl21.add(jsp2,"Center");
		jpl21.add(jpl23,"South");
		
		this.setTitle("�����б�");
		c1=new CardLayout();
		this.setLayout(c1);;
		this.add(jpl1,"1");
		this.add(jpl21,"2");
		this.setSize(270,500);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//������jbt2��İ���ˣ���ť ��תҳ��
		if(e.getSource()==jbt2) {
			c1.show(this.getContentPane(), "2");
		}
		//������jbt22���ҵĺ��ѣ���ť ��תҳ��
		if(e.getSource()==jbt22) {
			c1.show(this.getContentPane(), "1");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��Ӧ˫�� 
		if(e.getClickCount()==2) {
			Interface data = new Interface();
			data.setUsername(((JLabel)e.getSource()).getText());
			//System.out.println(num);





			//?????服务器维护一个HashMap表
			new ChatWindow(data);
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

}
