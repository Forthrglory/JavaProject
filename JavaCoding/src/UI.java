import sun.management.BaseOperatingSystemImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public JFrame jf;
    public Container contentPane;
    public Base64Operate bo;

    public UI(){
        this.jf = new JFrame("test");
        this.contentPane = jf.getContentPane();
        this.bo = new Base64Operate();
    }

    public void start(){
        this.jf.setSize(700,1000);

        this.jf.setLocationRelativeTo(null); // 居中

        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jf.setDefaultLookAndFeelDecorated(true);

        this.contentPane.setLayout(null);

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                layout(contentPane);
            }
        });

        jf.setVisible(true);
    }

    public void layout(Container contentPane){

        JTextArea jta_1 = new JTextArea();
        jta_1.setBounds(300, 10, 370, 460);
        jta_1.setFont(new Font("宋体", Font.BOLD, 20));
        jta_1.setLineWrap(true);

        JTextArea jta_2 = new JTextArea();
        jta_2.setBounds(300, 480, 370, 460);
        jta_2.setFont(new Font("宋体", Font.BOLD, 20));
        jta_2.setLineWrap(true);

        JButton button_1 = new JButton("Text->Base64");
        button_1.setBounds(10, 10, 130, 50);
        button_1.setBorder(BorderFactory.createEtchedBorder());
        button_1.setFont(new Font("宋体", Font.BOLD, 16));

        JButton button_2 = new JButton("Base64->Text");
        button_2.setBounds(150, 10, 130, 50);
        button_2.setBorder(BorderFactory.createEtchedBorder());
        button_2.setFont(new Font("宋体", Font.BOLD, 16));

        addActionListener_1(this.bo, button_1, jta_1, jta_2); //添加监听器
        addActionListener_2(this.bo, button_2, jta_1, jta_2);

        contentPane.add(jta_1);
        contentPane.add(jta_2);
        contentPane.add(button_1);
        contentPane.add(button_2);
    }

    private void addActionListener_1 (Base64Operate bo, JButton jb, JTextArea jta_1, JTextArea jta_2){
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = jta_1.getText();
                bo.setStr(str);
                jta_2.setText(bo.StrEncode());
            }
        });
    }

    private void addActionListener_2 (Base64Operate bo, JButton jb, JTextArea jta_1, JTextArea jta_2){
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = jta_1.getText();
                bo.setStr_base64(str);
                jta_2.setText(bo.StrDecode());
            }
        });
    }
}
