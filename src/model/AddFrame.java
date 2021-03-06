package model;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import model.MyFrame;
public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel label;
	private JTextField textField_1;
	private JLabel label_1;
	private JTextField textField_2;
	private JLabel label_2;
	private JTextField textField_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AddFrame frame = new AddFrame();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddFrame(MyFrame frame) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이     름 :");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 10, 121, 36);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(152, 10, 320, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel("전화번호 :");
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setBounds(12, 56, 121, 36);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(152, 56, 320, 36);
		contentPane.add(textField_1);
		
		label_1 = new JLabel("이 메 일  :");
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setBounds(12, 102, 121, 36);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(152, 102, 320, 36);
		contentPane.add(textField_2);
		
		label_2 = new JLabel("그 룹 명  :");
		label_2.setFont(new Font("굴림", Font.BOLD, 20));
		label_2.setBounds(12, 148, 121, 36);
		contentPane.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(152, 148, 320, 36);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("연락처등록");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField.getText();
					String phone = textField_1.getText();
					String group = textField_3.getText();
					
					if (name.length() > 5 || group.length() > 5)
						throw new MyException1("이름과 그룹명은 5자 이내여야 합니다");
					else if (!phone.matches("^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$"))
						throw new MyException2("전화번호를 다시 입력해주세요");
					else if (name.equals("") || phone.equals(""))
						throw new MyException3("이름과 전화번호를 입력해주세요");
					else if (name.matches(".*[0-9].*"))
						throw new MyException4("이름에 숫자가 포함되었습니다");
					else {
						if(group.equals(""))
							group = "미지정";
				        
				        frame.AddTable(name, phone, group);
						dispose();
					}
					
				} catch (MyException1 e1) {
					JOptionPane.showMessageDialog(contentPane, e1.getMessage());
				} catch (MyException2 e2) {
					JOptionPane.showMessageDialog(contentPane, e2.getMessage());
				} catch (MyException3 e3) {
					JOptionPane.showMessageDialog(contentPane, e3.getMessage());
				} catch (MyException4 e4) {
					JOptionPane.showMessageDialog(contentPane, e4.getMessage());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 25));
		btnNewButton.setBounds(12, 308, 460, 36);
		contentPane.add(btnNewButton);
	}
}

class MyException1 extends Exception {
	public MyException1(String e) {
		super(e);
	}
}

class MyException2 extends Exception{
	public MyException2(String e) {
		super(e);
	}
}

class MyException3 extends Exception{
	public MyException3(String e) {
		super(e);
	}
}

class MyException4 extends Exception{
	public MyException4(String e) {
		super(e);
	}
}