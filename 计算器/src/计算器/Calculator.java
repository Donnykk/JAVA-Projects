package 计算器;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
	private String result = ""; // 文本框的输出
	JTextField text = new JTextField();
	JPanel textPanel = new JPanel();
	boolean quit = false;

	public void Init() {
		text.setHorizontalAlignment(SwingConstants.RIGHT); // 右对齐
		text.setEditable(false);// 文本框不可编辑
		this.getContentPane().add(text, BorderLayout.NORTH);

		textPanel.setLayout(new GridLayout(6, 4));
		this.setPreferredSize(new Dimension(400, 500));
		this.pack();
		this.getContentPane().add(textPanel, BorderLayout.CENTER);

		JButton keys[] = new JButton[24];
		keys[0] = new JButton("%");
		keys[1] = new JButton("1/x");
		keys[2] = new JButton("C");
		keys[3] = new JButton("DELETE");
		keys[4] = new JButton("x^0.5");
		keys[5] = new JButton("x^2");
		keys[6] = new JButton("x^3");
		keys[7] = new JButton("/");
		keys[8] = new JButton("7");
		keys[9] = new JButton("8");
		keys[10] = new JButton("9");
		keys[11] = new JButton("*");
		keys[12] = new JButton("4");
		keys[13] = new JButton("5");
		keys[14] = new JButton("6");
		keys[15] = new JButton("-");
		keys[16] = new JButton("1");
		keys[17] = new JButton("2");
		keys[18] = new JButton("3");
		keys[19] = new JButton("+");
		keys[20] = new JButton("pai");
		keys[21] = new JButton("0");
		keys[22] = new JButton(".");
		keys[23] = new JButton("=");
		textPanel.add(keys[0]);
		textPanel.add(keys[1]);
		textPanel.add(keys[2]);
		textPanel.add(keys[3]);
		textPanel.add(keys[4]);
		textPanel.add(keys[5]);
		textPanel.add(keys[6]);
		textPanel.add(keys[7]);
		textPanel.add(keys[8]);
		textPanel.add(keys[9]);
		textPanel.add(keys[10]);
		textPanel.add(keys[11]);
		textPanel.add(keys[12]);
		textPanel.add(keys[13]);
		textPanel.add(keys[14]);
		textPanel.add(keys[15]);
		textPanel.add(keys[16]);
		textPanel.add(keys[17]);
		textPanel.add(keys[18]);
		textPanel.add(keys[19]);
		textPanel.add(keys[20]);
		textPanel.add(keys[21]);
		textPanel.add(keys[22]);
		textPanel.add(keys[23]);
		for (int i = 0; i < 24; i++)// 每个按钮都注册事件监听器
		{
			keys[i].addActionListener(this);
		}

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String label = e.getActionCommand();
		if (label == "=") {
			text.setText(this.result);
			String[] s = transform(this.result);
			String str = figure(s);
			this.result = str;
			text.setText(this.result);
		} else if (label == "CLOSE") {
			quit = true;
			return;
		} else if (label == "C") {
			this.result = "";
			text.setText(result);
		} else if (label == "DELETE") {
			this.result = this.result.substring(0, this.result.length() - 1);
			text.setText(this.result);
		} else if (label == "1/x") {
			this.result += "^-1";
			text.setText(this.result);
		} else if (label == "x^0.5") {
			this.result += "^0.5";
			text.setText(this.result);
		} else if (label == "x^2") {
			this.result += "^2";
			text.setText(this.result);
		} else if (label == "x^3") {
			this.result += "^3";
			text.setText(this.result);
		} else {
			this.result += label;
			text.setText(this.result);
		}
	}

	// 开方运算
	public String deSquare(String str) {
		String result = "";
		double a = Double.parseDouble(str), b = 0;
		b = Math.sqrt(a);
		result = String.valueOf(b);// 将运算结果转换为string类型并赋给string类型的变量result
		return result;
	}

	// 平方运算
	public String Square(String str) {
		String result = "";
		double a = Double.parseDouble(str), b = 0;
		b = Math.pow(a, 2);
		result = String.valueOf(b);
		return result;
	}

	// 三次方运算
	public String multiSquare(String str) {
		String result = "";
		double a = Double.parseDouble(str), b = 0;
		b = Math.pow(a, 2) * a;
		result = String.valueOf(b);
		return result;
	}

	private String[] transform(String str) {
		String s = "";
		char opStack[] = new char[100];
		String postQueue[] = new String[100];// 后缀表达式字符串数组，为了将多位数存储为独立的字符串
		int top = -1, j = 0;
		for (int i = 0; i < str.length(); i++) {
			if ("0123456789.".indexOf(str.charAt(i)) >= 0) {
				s = "";// 作为承接字符，每次开始时都要清空
				for (; i < str.length() && "0123456789.".indexOf(str.charAt(i)) >= 0; i++) {
					s += str.charAt(i);
				}
				i--;
				postQueue[j] = s;
				j++;
			} 
			if ("*%/".indexOf(str.charAt(i)) >= 0) {
				if (top == -1) {// 若栈为空则直接入栈
					top++;
					opStack[top] = str.charAt(i);
				} else {
					if ("*%/".indexOf(opStack[top]) >= 0) {
						postQueue[j] = opStack[top] + "";
						j++;
						opStack[top] = str.charAt(i);// 当前运算符入栈
					} else if ("+-".indexOf(str.charAt(i)) >= 0) {
						postQueue[j] = opStack[top] + "";
						j++;
						opStack[top] = str.charAt(i);// 当前元素入栈
					}
				}
			} else if ("+-".indexOf(str.charAt(i)) >= 0) {
				if (top == -1) {
					top++;
					opStack[top] = str.charAt(i);
				} else {
					if ("*%/".indexOf(opStack[top]) >= 0) {
						postQueue[j] = opStack[top] + "";
						j++;
						opStack[top] = str.charAt(i);
					} else if ("+-".indexOf(str.charAt(i)) >= 0) {
						postQueue[j] = opStack[top] + "";
						j++;
						opStack[top] = str.charAt(i);
					}
				}
			}
		}
		for (; top != -1;) {// 遍历结束后将栈中剩余元素依次出栈进入后缀表达式
			postQueue[j] = opStack[top] + "";
			j++;
			top--;
		}
		return postQueue;
	}

	private String figure(String[] str) {
		String Result[] = new String[100];// 顺序存储的栈，数据类型为字符串
		int Top = -1;
		for (int i = 0; str[i] != null; i++) {
			if ("+-*%/".indexOf(str[i]) < 0) {
				Top++;
				Result[Top] = str[i];
			}
			if ("+-*%/".indexOf(str[i]) >= 0) {
				double x, y, n;
				x = Double.parseDouble(Result[Top]);
				Top--;
				y = Double.parseDouble(Result[Top]);
				Top--;
				if ("-".indexOf(str[i]) >= 0) {
					n = y - x;
					Top++;
					Result[Top] = String.valueOf(n);
				}
				if ("+".indexOf(str[i]) >= 0) {
					n = y + x;
					Top++;
					Result[Top] = String.valueOf(n);
				}
				if ("*".indexOf(str[i]) >= 0) {
					n = y * x;
					Top++;
					Result[Top] = String.valueOf(n);
				}
				if ("/".indexOf(str[i]) >= 0) {
					if (x == 0) {
						String s = "error!";
						return s;
					} else {
						n = y / x;
						Top++;
						Result[Top] = String.valueOf(n);
					}
				}
				if ("%".indexOf(str[i]) >= 0) {
					if (x == 0) {
						String s = "error!";
						return s;
					} else {
						n = y % x;
						Top++;
						Result[Top] = String.valueOf(n);
					}
				}
			}
		}
		return Result[Top];
	}
}
