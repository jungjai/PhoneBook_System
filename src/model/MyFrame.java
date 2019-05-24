package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;

public class MyFrame extends JFrame {

	public static JPanel contentPane;
	public static JScrollPane panel_1;
	private JTextField textField;
	public static JTable table_1;
	public static DefaultTableModel defaultTableModel;
	public static String[][] contents = new String[300][3];
	public static int[] con_valid = new int[300];
	public static int total;
	public static int check = 0;
	public static String s = "검색";
	public static MyFrame frame;
	int exe = 0;
	String[][] Arr = new String[300][3];
	int cnt = 0;
	Thread t;
	Thread t2;
	public static void main(String[] args) {
		getFile file = new getFile();
		frame = new MyFrame();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MyFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("연락처 검색 시스템");
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 674, 47);
		contentPane.add(panel);

		JButton btnNewButton = new JButton("추가");
		btnNewButton.setBounds(12, 7, 106, 34);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFrame frame1 = new AddFrame(frame);
				frame1.setVisible(true);
			}
		});
		panel.setLayout(null);
		panel.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(130, 4, 420, 34);
		if (check == 1)
			textField.setEnabled(false);
		panel.add(textField);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		JButton btnNewButton_1 = new JButton(s);
		btnNewButton_1.setBounds(562, 7, 108, 31);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText(); exe = 0;

				try {
					if (input.equals("")) {
						exe = 1;
						throw new MyException6("검색어를 입력하세요");

					}
				} catch (MyException6 e6) {
					JOptionPane.showMessageDialog(contentPane, e6.getMessage());
				}
				if (check == 0 && exe == 0) {
					check = 1; 
					s = "검색중";
					for (int i = 0; i < table_1.getRowCount(); i++) {
						String num = table_1.getValueAt(i, 1).toString().replace("-", "");
						if (String.valueOf(table_1.getValueAt(i, 0)).contains(input)) {
							Arr[cnt][0] = table_1.getModel().getValueAt(i, 0).toString();
							Arr[cnt][1] = table_1.getModel().getValueAt(i, 1).toString();
							Arr[cnt][2] = table_1.getModel().getValueAt(i, 2).toString();
							cnt++;
						} else if (num.contains(input)) {
							Arr[cnt][0] = table_1.getModel().getValueAt(i, 0).toString();
							Arr[cnt][1] = table_1.getModel().getValueAt(i, 1).toString();
							Arr[cnt][2] = table_1.getModel().getValueAt(i, 2).toString();
							cnt++;
						} else if (String.valueOf(table_1.getValueAt(i, 1)).contains(input)) {
							Arr[cnt][0] = table_1.getModel().getValueAt(i, 0).toString();
							Arr[cnt][1] = table_1.getModel().getValueAt(i, 1).toString();
							Arr[cnt][2] = table_1.getModel().getValueAt(i, 2).toString();
							cnt++;
						} else if (String.valueOf(table_1.getValueAt(i, 2)).equals(input)) {
							Arr[cnt][0] = table_1.getModel().getValueAt(i, 0).toString();
							Arr[cnt][1] = table_1.getModel().getValueAt(i, 1).toString();
							Arr[cnt][2] = table_1.getModel().getValueAt(i, 2).toString();
							cnt++;
						}
					}
					defaultTableModel.setNumRows(0);

					t = new Thread() {
						public void run() {
							try {
								for (int i = 0; i < cnt; i++) {
									defaultTableModel.addRow(Arr[i]);
									Thread.sleep(500);
								}
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					};
					t.start();
					textField.setEnabled(false);
					btnNewButton_1.setText(s);
				} else if (check == 1 && exe == 0) {
					check = 0;
					s = "검색";
					t.stop();
					cnt = 0;		
					defaultTableModel.setNumRows(0);

					defaultTableModel.setColumnIdentifiers(contents[0]);

					for (int i = 1; i < total; i++)
						defaultTableModel.addRow(contents[i]);
					textField.setEnabled(true);
					btnNewButton_1.setText(s);
				}
			}
		});
		panel.add(btnNewButton_1);
		table_1 = new JTable();
		defaultTableModel = (DefaultTableModel) table_1.getModel();

		defaultTableModel.setColumnIdentifiers(contents[0]);

		for (int i = 1; i < total; i++)
			defaultTableModel.addRow(contents[i]);
			
		panel_1 = new JScrollPane(table_1);
		panel_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_1.setBounds(5, 62, 674, 324);
		contentPane.add(panel_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(5, 396, 674, 59);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JButton btnNewButton_2 = new JButton("SMS 전송");
		btnNewButton_2.setBounds(12, 5, 313, 49);
		panel_2.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				t2 = new Thread() {
					public void run() {
						try {
							for (int i = 0; i < table_1.getRowCount(); i++) {
								String s = table_1.getModel().getValueAt(i, 0).toString();
								SMSFrame frame = new SMSFrame(s);
								frame.setVisible(true);
								Thread.sleep(1000);
							}
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				t2.start();
				
			}
		});

		JButton btnNewButton_3 = new JButton("연락처 삭제");
		btnNewButton_3.setBounds(349, 5, 313, 49);
		btnNewButton_3.addActionListener(new ActionListener() {
			@SuppressWarnings("null")
			public void actionPerformed(ActionEvent e) {
				cnt = 0;
				for (int i = 0; i < table_1.getRowCount(); i++) {
					Arr[cnt][0] = table_1.getModel().getValueAt(i, 0).toString();
					Arr[cnt][1] = table_1.getModel().getValueAt(i, 1).toString();
					Arr[cnt][2] = table_1.getModel().getValueAt(i, 2).toString();
					cnt++;
				}
				
				for(int i=0;i<total;i++) {
					for(int j=0;j<cnt;j++) {
						if(contents[i][0].equals(Arr[j][0]) && contents[i][1].equals(Arr[j][1]) && contents[i][2].equals(Arr[j][2])) {
							con_valid[i] = 0;
						}
					}
				}
				try {
					String[][] copy  = new String[300][3];
					int co = 0;
					int []copy_valid = new int[300];
					File file = new File("data.txt");
					FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bufWriter = new BufferedWriter(fileWriter);
					for (int i = 0; i < total; i++) {
						if(con_valid[i] == 1) {
							
							for (int j = 0; j < 3; j++) {
								copy[co][j] = contents[i][j];
								bufWriter.write(contents[i][j] + " ");
							}
							copy_valid[co] = 1;
							co++;
							bufWriter.write("\r\n");
						}
					}
	
					bufWriter.close();
					fileWriter.close();
					
					total = co;
					for(int i=0;i<co;i++) {
						for (int j = 0; j < 3; j++) {
							contents[i][j] = copy[i][j];
						}
						con_valid[i] = 1;
					}
					cnt = 0;
					check = 0;
					s = "검색";
					defaultTableModel.setNumRows(0);

					defaultTableModel.setColumnIdentifiers(contents[0]);

					for (int i = 1; i < total; i++)
						defaultTableModel.addRow(contents[i]);
					textField.setText("");
					textField.setEnabled(true);
					btnNewButton_1.setText(s);
				} catch (IOException x) {
					x.printStackTrace();
				}
			}
		});
		panel_2.add(btnNewButton_3);
	}

	public void AddTable(String name, String phone, String group) throws IOException {
		contents[total][0] = name;
		contents[total][1] = phone;
		contents[total][2] = group;
		con_valid[total] = 1;
		defaultTableModel.addRow(contents[total]);

		total += 1;

		File file = new File("data.txt");
		FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bufWriter = new BufferedWriter(fileWriter);
		for (int i = 0; i < total; i++) {
			for (int j = 0; j < 3; j++) {
				bufWriter.write(contents[i][j] + " ");
			}
			bufWriter.write("\r\n");
		}

		bufWriter.close();
		fileWriter.close();

	}
}

class getFile extends MyFrame {
	public getFile() {
		String str = ("data.txt");

		String s;
		try {
			BufferedReader br = new BufferedReader(new FileReader(str));

			int p = 0;
			while ((s = br.readLine()) != null) {
				if (s.equals("") != true) {
					StringTokenizer c = new StringTokenizer(s);
					int i = 0;
					while (c.hasMoreTokens()) {
						contents[p][i] = c.nextToken();
						
						i++;
					}
					if (contents[p].equals(""))
						continue;
					con_valid[p] = 1;
					p++;
				}
			}

			total = p;
			br.close();
		} catch (FileNotFoundException fn) {
			System.out.println("파일을 찾을 수 없습니다");
		} catch (IOException e) {
			System.out.println("파일을 읽어 올 수 없습니다");
		} finally {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class MyException6 extends Exception {
	public MyException6(String e) {
		super(e);
	}
}