import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Fenetre extends JFrame {
	private int largeur, hauteur;
	private Panneau pan = new Panneau();
	private JButton bt_jouer = new JButton("Jouer");
	
	public Fenetre(int l, int h){  
		this.largeur=l;
		this.hauteur=h;
		this.setTitle("Simulation Fourmis");
		this.setSize(largeur, hauteur);
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(pan);
		pan.add(bt_jouer);
		this.setVisible(true);
  }     
}