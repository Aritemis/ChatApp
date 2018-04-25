/**
 *	@author Ariana Fairbanks
 */

package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ChatClientPanel extends JPanel 
{
	private static final long serialVersionUID = -8341376012711000351L;
	private ClientConnection base;
	private JTextField inputField;
	private JTextArea displayLog;
	private DefaultCaret displayCaret;
	private SpringLayout springLayout;
	private JScrollPane scroll;
	private Color outline;
	private JScrollBar scrollBar;
	private JLabel usersLabel;
	private JLabel lblTop;
	private JButton sendButton;
	private JButton exitButton;
	
	public ChatClientPanel(ClientConnection base)
	{
		setBorder(null);
		this.base = base;
		springLayout = new SpringLayout();
		displayLog = new JTextArea();
		displayCaret = (DefaultCaret)displayLog.getCaret();
		inputField = new JTextField();
		scroll = new JScrollPane(displayLog);
		outline = new Color(0, 255, 0);
		scrollBar = scroll.getVerticalScrollBar();
		usersLabel = new JLabel("");
		lblTop = new JLabel("WTDP Chat Client");
		sendButton = new JButton(" Send ");
		exitButton = new JButton(" Exit ");
		
		setUpPanel();
		setUpLayout();
		setUpListeners();
	}

	private void setUpPanel() 
	{
		setLayout(springLayout);
		add(inputField);
		add(scroll);
		add(usersLabel);
		add(lblTop);
		add(sendButton);
		add(exitButton);
		
		displayLog.setText("\tWelcome. Enter a username with fewer than 20 characters.\n");
		inputField.requestFocusInWindow();
		displayCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		UIManager.put("ScrollBarUI", "view.ScrollBarUI");
		scrollBar.setUI(new NewScrollBarUI());
		inputField.requestFocusInWindow();
	}

	private void setUpLayout() 
	{
		setBackground(new Color(0, 0, 0));
		displayLog.setEditable(false);
		displayLog.setWrapStyleWord(true);
		displayLog.setTabSize(4);
		displayLog.setLineWrap(true);
		inputField.setColumns(20);
		inputField.setText("");
		sendButton.setFocusPainted(false);
		sendButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.setContentAreaFilled(false);
		lblTop.setForeground(Color.GREEN);
		usersLabel.setForeground(Color.GREEN);
		displayLog.setForeground(Color.GREEN);
		displayLog.setBackground(Color.BLACK);
		sendButton.setBackground(Color.BLACK);
		sendButton.setForeground(Color.GREEN);
		exitButton.setBackground(Color.BLACK);
		exitButton.setForeground(Color.GREEN);
		inputField.setForeground(Color.GREEN);
		inputField.setBackground(Color.BLACK);
		lblTop.setFont(new Font("Monospaced", Font.PLAIN, 20));
		usersLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		sendButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
		exitButton.setFont(new Font("Monospaced", Font.PLAIN, 12));
		displayLog.setFont(new Font("DialogInput", Font.PLAIN, 15));
		inputField.setFont(new Font("DialogInput", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.WEST, scroll, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, scroll, -25, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.NORTH, lblTop, 15, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblTop, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.WEST, lblTop, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblTop, -230, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, inputField, -15, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, usersLabel, -25, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, usersLabel, 135, SpringLayout.EAST, lblTop);
		springLayout.putConstraint(SpringLayout.SOUTH, usersLabel, -10, SpringLayout.NORTH, scroll);
		springLayout.putConstraint(SpringLayout.NORTH, scroll, 45, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, inputField, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.EAST, inputField, 0, SpringLayout.EAST, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, scroll, -60, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.NORTH, sendButton, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, sendButton, -15, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, sendButton, 25, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, inputField, 10, SpringLayout.EAST, exitButton);
		springLayout.putConstraint(SpringLayout.NORTH, exitButton, 15, SpringLayout.SOUTH, scroll);
		springLayout.putConstraint(SpringLayout.SOUTH, exitButton, -15, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, exitButton, 10, SpringLayout.EAST, sendButton);
		displayLog.setBorder(new EmptyBorder(5, 15, 5, 15));
		scroll.setBorder(new LineBorder(new Color(0, 255, 0)));
		sendButton.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		exitButton.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		inputField.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(outline),
                BorderFactory.createEmptyBorder(0, 55, 0, 0)));
		inputField.requestFocusInWindow();
	}
	
	private void setUpListeners() 
	{
		sendButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent click) 
			{
				//TODO send
				//TODO character limits
				if(!base.sendMessage(inputField.getText()))
				{
					//TODO connection failed
				}
				displayLog.setText("");
				inputField.setText("");
				inputField.requestFocusInWindow();
			}
		});
		
		exitButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent click) 
			{
				//TODO exit
			}
		});
		
		inputField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(inputField.getText().length() > 0)
				{
					
				}
					
			}
		});
	}
	
	public void updateDisplayLog()
	{
		displayCaret = (DefaultCaret)displayLog.getCaret();
		displayLog.setCaretPosition(displayLog.getDocument().getLength());
		scroll.setViewportView(displayLog);
	}
	
	public void updateUsersLabel()
	{
		//TODO
	}
	
}
