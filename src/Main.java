import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame implements ActionListener
{
	public static final int WIDTH = 1280, HEIGHT = 720;
	
	JPanel mainPanel = new JPanel(new CardLayout());
	CardLayout mainLayout = (CardLayout) mainPanel.getLayout();
	Connection dbConnect = DBConnection.dbConnector();
	
	static int phase = 0;
	
	private JFrame frame, popupFrame;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem1, menuItem2;
	
	private JPanel titlePanel, loginPanel, dashboardPanel;
	private JPanel displayPanel = new JPanel(new CardLayout()), display1, display2, display3, display4;
	private CardLayout displayCards = (CardLayout) displayPanel.getLayout();
	
	private JButton loginBtn, enterBtn, regPropBtn, regUserBtn, regAdminsBtn, addPropBtn, addUsrBtn;
	private JLabel credit;
	private JTextField nameField;
	private JPasswordField passField;
	
	public Main(int width, int height)
	{
		Init();
		this.setResizable(false);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.getContentPane().setLayout(null);
		
		mainPanel.setBounds(0, 0, 1280, 720);
		mainPanel.add(titlePanel, "titlePage");
		mainPanel.add(loginPanel, "loginPage");
		mainPanel.add(dashboardPanel, "dashboardPage");
		this.getContentPane().add(mainPanel);
		
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuItem1.addActionListener(this);
		menuItem2.addActionListener(this);
		menu.add(menuItem1);
		menu.add(menuItem2);
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);
		
		
		credit = new JLabel();
		credit.setText("Powered by Abdulrahman Bucheeri");
		credit.setBounds(860, 620, 300, 50);
		credit.setFont(new Font("Calibri", Font.ITALIC, 18));
		
		TitlePage();
	}
	
	public static void main(String args[])
	{
		Main window = new Main(WIDTH, HEIGHT);
		window.setTitle("My Realtor 1.0");
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void Init()
	{
		frame = new JFrame();
		popupFrame = new JFrame();
		frame = this;
		menuBar = new JMenuBar();
		menu = new JMenu("Help");
		menuItem1 = new JMenuItem("About");
		menuItem2 = new JMenuItem("DB Connection");
		titlePanel = new JPanel();
		loginPanel = new JPanel();
		dashboardPanel = new JPanel();
		display1 = new JPanel();
		display2 = new JPanel();
		display3 = new JPanel();
		display4 = new JPanel();
		loginBtn = new JButton();
		enterBtn = new JButton();
		regPropBtn = new JButton();
		regUserBtn = new JButton();
		regAdminsBtn = new JButton();
		addPropBtn = new JButton();
		addUsrBtn = new JButton();
		nameField = new JTextField();
		passField = new JPasswordField();
		
		loginBtn.addActionListener(this);
		enterBtn.addActionListener(this);
		regPropBtn.addActionListener(this);
		regUserBtn.addActionListener(this);
		addPropBtn.addActionListener(this);
		addUsrBtn.addActionListener(this);
		regAdminsBtn.addActionListener(this);
	}
	
	public void TitlePage()
	{
		try
		{
			phase = 1;
			titlePanel.setLayout(null);
			titlePanel.setBounds(250, 50, 780, 560);
			titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
			JLabel title = new JLabel();
			//title.setText("<html><p style='text-align: center;'>WELCOME</p>"+"<p style='text-align: center;'>"+"TO"+"</p>"+"<p style='text-align: center;'>MY REALTOR</p></html");
			title.setIcon(new ImageIcon("Images\\Bucheeri_Enterprise_Logo.png"));
			Dimension size = title.getPreferredSize();
			title.setBounds(380, 50, size.width, size.height);
			title.setFont(new Font("Times New Roman", Font.BOLD, 54));
			title.setVisible(true);
			titlePanel.add(title, BorderLayout.CENTER);
			
			loginBtn.setText("Log");
			loginBtn.setBounds(500, 450, 250, 50);
			loginBtn.setFont(new Font("Calibri", Font.PLAIN, 24));
			loginBtn.setBackground(new Color(0x2dce98));
			loginBtn.setForeground(Color.white);
			loginBtn.setUI(new StyledButtonUI());
			loginBtn.setVisible(true);
			titlePanel.add(loginBtn, BorderLayout.CENTER);
		
			titlePanel.add(credit);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void LoginPage()
	{
		try
		{
			phase = 2;
			loginPanel.setLayout(null);
			loginPanel.setBounds(250, 50, 780, 560);
			loginPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			JLabel title = new JLabel();
			title.setText("<html><p style='text-align: center; text-transform: uppercase;'>LOGIN</p></html>");
			title.setBounds(520, 70, 250, 250);
			title.setFont(new Font("Calibri", Font.BOLD, 54));
			title.setVisible(true);
			loginPanel.add(title, BorderLayout.CENTER);
			
			JLabel nameLbl = new JLabel();
			nameLbl.setText("Access Name:");
			nameLbl.setBounds(525, 270, 200, 50);
			nameLbl.setFont(new Font("Calibri", Font.PLAIN, 22));
			loginPanel.add(nameLbl, BorderLayout.CENTER);
			
			nameField.setBounds(425, 320, 350, 40);
			nameField.setFont(new Font("Calibri", Font.PLAIN, 18));
			loginPanel.add(nameField);
			
			JLabel passLbl = new JLabel();
			passLbl.setText("Access Password:");
			passLbl.setBounds(525, 370, 200, 50);
			passLbl.setFont(new Font("Calibri", Font.PLAIN, 22));
			loginPanel.add(passLbl, BorderLayout.CENTER);
			
			passField.setBounds(425, 420, 350, 40);
			passField.setFont(new Font("Calibri", Font.PLAIN, 18));
			passField.setEchoChar('*');
			loginPanel.add(passField);
			
			enterBtn.setText("ENTER");
			enterBtn.setBounds(525, 530, 150, 50);
			enterBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			enterBtn.setUI(new StyledButtonUI());
			enterBtn.setBackground(new Color(45,227,163));
			loginPanel.add(enterBtn);
			
			loginPanel.add(credit);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void DashboardPage()
	{
		try
		{
			phase = 3;
			
			dashboardPanel.setLayout(null);
			dashboardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			regPropBtn.setText("Registered Properties");
			regPropBtn.setBounds(20, 20, 300, 50);
			regPropBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			regPropBtn.setUI(new StyledButtonUI());
			regPropBtn.setBackground(new Color(255, 179, 0));
			dashboardPanel.add(regPropBtn);
			
			regUserBtn.setText("Registered Users");
			regUserBtn.setBounds(340, 20, 300, 50);
			regUserBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			regUserBtn.setUI(new StyledButtonUI());
			regUserBtn.setBackground(new Color(255, 179, 0));
			dashboardPanel.add(regUserBtn);
			
			addPropBtn.setText("Add New Property");
			addPropBtn.setBounds(20, 80, 300, 50);
			addPropBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			addPropBtn.setUI(new StyledButtonUI());
			addPropBtn.setBackground(new Color(0, 247, 136));
			dashboardPanel.add(addPropBtn);
			
			addUsrBtn.setText("Add New User");
			addUsrBtn.setBounds(340, 80, 300, 50);
			addUsrBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			addUsrBtn.setUI(new StyledButtonUI());
			addUsrBtn.setBackground(new Color(0, 247, 136));
			dashboardPanel.add(addUsrBtn);
			
			/*regBuyersBtn = new JButton();
			regBuyersBtn.setText("Registered Buyers");
			regBuyersBtn.setBounds(660, 20, 300, 50);
			regBuyersBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			regBuyersBtn.setUI(new StyledButtonUI());
			regBuyersBtn.setBackground(new Color(255, 179, 0));
			regBuyersBtn.addActionListener(this);
			dashboardPanel.add(regBuyersBtn);*/
			
			regAdminsBtn.setText("Admins");
			regAdminsBtn.setBounds(980, 20, 250, 50);
			regAdminsBtn.setFont(new Font("Calibri", Font.BOLD, 24));
			regAdminsBtn.setUI(new StyledButtonUI());
			regAdminsBtn.setBackground(new Color(255, 18, 18));
			dashboardPanel.add(regAdminsBtn);
			
			displayPanel.setBounds(20, 150, 960, 450);
			displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			displayPanel.setBackground(new Color(247, 236, 210));
			displayPanel.add(display1, "displayOne");
			displayPanel.add(display2, "displayTwo");
			displayPanel.add(display3, "displayThree");
			displayPanel.add(display4, "displayFour");
			dashboardPanel.add(displayPanel);
			
			dashboardPanel.add(credit);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void RegPropShow()
	{
		display1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		display1.setBackground(new Color(247, 236, 210));
		display1.setLayout(new BorderLayout());
		String[] column1 = {"Property Name", "Property Type", "Property Price", "Property Owner", "Building No", "Street No", "Block No", "City", "Governance"};
		String[][] rowData = {{"r1", "r1", "r1", "r1", "r1", "r1", "r1", "r1", "r1"}};
		DefaultTableModel model1 = new DefaultTableModel(rowData, column1);
		JTable table1 = new JTable(model1);
		table1.setShowGrid(true);
		table1.setGridColor(Color.BLACK);
		//model1.addColumn("Header");
		display1.add(new JScrollPane(table1));
	}
	
	public void RegUser()
	{
		try
		{
			display2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			display2.setBackground(new Color(117, 193, 255));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void AddNewProp()
	{
		try
		{
			display3.setLayout(null);
			display3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			
			JLabel title = new JLabel("Add New Property:");
			title.setBounds(10, 10, 250, 20);
			title.setFont(new Font("Calibri", Font.BOLD, 20));
			title.setVisible(true);
			display3.add(title);
			
			JLabel propNameLbl = new JLabel("Property Name");
			propNameLbl.setBounds(10, 100, 150, 20);
			propNameLbl.setFont(new Font("Calibri", Font.PLAIN, 18));
			propNameLbl.setVisible(true);
			display3.add(propNameLbl);
			
			JTextField propNameTxt = new JTextField("Type Property Name");
			propNameTxt.setBounds(170, 90, 200, 30);
			propNameTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propNameTxt.setForeground(Color.GRAY);
			propNameTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propNameTxt.getText().equals("Type Property Name"))
					{
						propNameTxt.setText("");
						propNameTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propNameTxt.getText().isEmpty())
					{
						propNameTxt.setForeground(Color.GRAY);
						propNameTxt.setText("Type Property Name");
					}
				}
			});
			display3.add(propNameTxt);
			
			JLabel propTypeLbl = new JLabel("Property Type");
			propTypeLbl.setBounds(10, 135, 150, 20);
			propTypeLbl.setFont(new Font("Calibri", Font.PLAIN, 18));
			propTypeLbl.setVisible(true);
			display3.add(propTypeLbl);
			
			JTextField propTypeTxt = new JTextField("Select Property Type");
			propTypeTxt.setBounds(170, 125, 200, 30);
			propTypeTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propTypeTxt.setForeground(Color.GRAY);
			propTypeTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propTypeTxt.getText().equals("Select Property Type"))
					{
						propTypeTxt.setText("");
						propTypeTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propTypeTxt.getText().isEmpty())
					{
						propTypeTxt.setForeground(Color.GRAY);
						propTypeTxt.setText("Select Property Type");
					}
				}
			});
			display3.add(propTypeTxt);
			
			JLabel propPriceLbl = new JLabel("Property Price");
			propPriceLbl.setBounds(10, 170, 150, 20);
			propPriceLbl.setFont(new Font("Calibri", Font.PLAIN, 18));
			propPriceLbl.setVisible(true);
			display3.add(propPriceLbl);
			
			JTextField propPriceTxt = new JTextField("Set Price");
			propPriceTxt.setBounds(170, 160, 200, 30);
			propPriceTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propPriceTxt.setForeground(Color.GRAY);
			propPriceTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propPriceTxt.getText().equals("Set Price"))
					{
						propPriceTxt.setText("");
						propPriceTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propPriceTxt.getText().isEmpty())
					{
						propPriceTxt.setForeground(Color.GRAY);
						propPriceTxt.setText("Set Price");
					}
				}
			});
			display3.add(propPriceTxt);
			
			JLabel propOwnerLbl = new JLabel("Property Owner");
			propOwnerLbl.setBounds(10, 205, 150, 20);
			propOwnerLbl.setFont(new Font("Calibri", Font.PLAIN, 18));
			propOwnerLbl.setVisible(true);
			display3.add(propOwnerLbl);
			
			JTextField propOwnerTxt = new JTextField("Type Owner Name");
			propOwnerTxt.setBounds(170, 195, 200, 30);
			propOwnerTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propOwnerTxt.setForeground(Color.GRAY);
			propOwnerTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propOwnerTxt.getText().equals("Type Owner Name"))
					{
						propOwnerTxt.setText("");
						propOwnerTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propOwnerTxt.getText().isEmpty())
					{
						propOwnerTxt.setForeground(Color.GRAY);
						propOwnerTxt.setText("Type Owner Name");
					}
				}
			});
			display3.add(propOwnerTxt);
			
			JLabel propAddressLbl = new JLabel("Property Address");
			propAddressLbl.setBounds(10, 240, 150, 20);
			propAddressLbl.setFont(new Font("Calibri", Font.PLAIN, 18));
			propAddressLbl.setVisible(true);
			display3.add(propAddressLbl);
			
			JTextField propBuildingTxt = new JTextField("Type Building No");
			propBuildingTxt.setBounds(170, 230, 200, 30);
			propBuildingTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propBuildingTxt.setForeground(Color.GRAY);
			propBuildingTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propBuildingTxt.getText().equals("Type Building No"))
					{
						propBuildingTxt.setText("");
						propBuildingTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propBuildingTxt.getText().isEmpty())
					{
						propBuildingTxt.setForeground(Color.GRAY);
						propBuildingTxt.setText("Type Building No");
					}
				}
			});
			display3.add(propBuildingTxt);
			
			JTextField propFlatTxt = new JTextField("Type Flat No");
			propFlatTxt.setBounds(170, 265, 200, 30);
			propFlatTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propFlatTxt.setForeground(Color.GRAY);
			propFlatTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propFlatTxt.getText().equals("Type Flat No"))
					{
						propFlatTxt.setText("");
						propFlatTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propFlatTxt.getText().isEmpty())
					{
						propFlatTxt.setForeground(Color.GRAY);
						propFlatTxt.setText("Type Flat No");
					}
				}
			});
			display3.add(propFlatTxt);
			
			JTextField propRoadTxt = new JTextField("Type Road No");
			propRoadTxt.setBounds(170, 300, 200, 30);
			propRoadTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propRoadTxt.setForeground(Color.GRAY);
			propRoadTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propRoadTxt.getText().equals("Type Road No"))
					{
						propRoadTxt.setText("");
						propRoadTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propRoadTxt.getText().isEmpty())
					{
						propRoadTxt.setForeground(Color.GRAY);
						propRoadTxt.setText("Type Road No");
					}
				}
			});
			display3.add(propRoadTxt);
			
			JTextField propBlockTxt = new JTextField("Type Block No");
			propBlockTxt.setBounds(170, 335, 200, 30);
			propBlockTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propBlockTxt.setForeground(Color.GRAY);
			propBlockTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propBlockTxt.getText().equals("Type Block No"))
					{
						propBlockTxt.setText("");
						propBlockTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propBlockTxt.getText().isEmpty())
					{
						propBlockTxt.setForeground(Color.GRAY);
						propBlockTxt.setText("Type Block No");
					}
				}
			});
			display3.add(propBlockTxt);
			
			JTextField propCityTxt = new JTextField("Select City");
			propCityTxt.setBounds(170, 370, 200, 30);
			propCityTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propCityTxt.setForeground(Color.GRAY);
			propCityTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propCityTxt.getText().equals("Select City"))
					{
						propCityTxt.setText("");
						propCityTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propCityTxt.getText().isEmpty())
					{
						propCityTxt.setForeground(Color.GRAY);
						propCityTxt.setText("Select City");
					}
				}
			});
			display3.add(propCityTxt);
			
			JTextField propGoverTxt = new JTextField("Select Governance");
			propGoverTxt.setBounds(170, 405, 200, 30);
			propGoverTxt.setFont(new Font("Calibri", Font.ITALIC, 18));
			propGoverTxt.setForeground(Color.GRAY);
			propGoverTxt.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent e)
				{
					if (propGoverTxt.getText().equals("Select Governance"))
					{
						propGoverTxt.setText("");
						propGoverTxt.setForeground(Color.BLACK);
					}
				}
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (propGoverTxt.getText().isEmpty())
					{
						propGoverTxt.setForeground(Color.GRAY);
						propGoverTxt.setText("Select Governance");
					}
				}
			});
			display3.add(propGoverTxt);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void AdminsShow()
	{
		AdminPage sp = new AdminPage(WIDTH,HEIGHT);
		sp.setTitle("My Realtor 1.0");
		sp.setVisible(true);
		sp.setLocationRelativeTo(null);
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == loginBtn)
		{
			mainLayout.show(mainPanel, "dashboardPage");
			DashboardPage();
			/*mainLayout.show(mainPanel, "loginPage");
			LoginPage();*/
		}
		
		if (e.getSource() == menuItem1)
		{
			JOptionPane.showMessageDialog(popupFrame, "Realtor 1.0 Software.\nPowered by Abdulrahman Bucheeri.\nEmail: jack99blue2008@gmail.com.\nTwitter: @jackblue2009.");
		}
		
		if (e.getSource() == menuItem2)
		{
			if (dbConnect != null)
			{
				JOptionPane.showMessageDialog(popupFrame, "Connection Successful!");
			}
			else
			{
				JOptionPane.showMessageDialog(popupFrame, "Connection Denied!");
			}
		}
		
		if (e.getSource() == enterBtn)
		{
			String pass = String.valueOf(passField.getPassword());
			mainLayout.show(mainPanel, "dashboardPage");
			DashboardPage();
			//JOptionPane.showMessageDialog(null, nameField.getText() + " || " + pass);
			/*if (nameField.getText().equals("admin") && pass.equals("admin"))
			{
				mainLayout.show(mainPanel, "dashboardPage");
				DashboardPage();
				nameField.setText("");
				passField.setText("");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Incorrect Access Name and Password. Please try again!");
				nameField.setText("");
				passField.setText("");
			}*/
		}
		
		if (e.getSource() == regPropBtn)
		{
			displayCards.show(displayPanel, "displayOne");
			RegPropShow();
		}
		
		if (e.getSource() == regUserBtn)
		{
			displayCards.show(displayPanel, "displayTwo");
			RegUser();
		}
		
		if (e.getSource() == addPropBtn)
		{
			displayCards.show(displayPanel, "displayThree");
			AddNewProp();
		}
		
		if (e.getSource() == regAdminsBtn)
		{
			displayCards.show(displayPanel, "displayFour");
			AdminsShow();
		}
	}
}
