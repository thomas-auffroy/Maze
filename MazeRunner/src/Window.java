import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	public Window(JPanel pan) {
		// TODO Auto-generated constructor stub
		this.setTitle("Maze Runner");
		this.setSize(1000,1000);		
		this.setContentPane(pan);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}

	public static void main(String[] args) {
		new	Window(new Panel());
		
	}

}
