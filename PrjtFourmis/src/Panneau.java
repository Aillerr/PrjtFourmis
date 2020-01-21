import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	public void paintComponent(Graphics g){
		try {
			Font font = new Font("Courier", Font.BOLD, 30);
		    g.setFont(font);
			g.drawString("Simulation Fourmis", 70, 30);
			Image img = ImageIO.read(new File("fermite.png"));
			g.drawImage(img, 80, -50,300, 300, this);

		} catch (IOException e) {
			e.printStackTrace();
		}                
	}               
}