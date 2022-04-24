import java.awt.Dimension;

import javax.swing.JFrame;

public class AdminPage extends JFrame
{
	private JFrame frame;
	
	public AdminPage(int width, int height)
	{
		Init();
		setResizable(false);
		this.setPreferredSize(new Dimension(width,height));
		this.setMaximumSize(new Dimension(width,height));
		this.setMinimumSize(new Dimension(width,height));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}
	
	private void Init()
	{
		frame = new JFrame();
		frame = this;
	}
}
