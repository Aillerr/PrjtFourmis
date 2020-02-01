import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
 
public class Fenetre extends JFrame implements ActionListener{
	private int largeur, hauteur;
	private JButton bt_jouer = new JButton("Commencer");
	private JButton bt_param = new JButton("Paramètres");
	private JButton bt_lancer = new JButton("Lancer");
	private Timer timer;
	private Monde m;
	private int dim;
	private JPanel content;
	private Panneau[][] Grid;
	private JFormattedTextField map = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField nb_n = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField nb_h = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField nb = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField time = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField nb_actions = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField nb_manche = new JFormattedTextField(NumberFormat.getIntegerInstance());
	private JRadioButton check1 = new JRadioButton("Voir la simulation");
	private JRadioButton check2 = new JRadioButton("Voir eulement les réultats");
	
	
	public Fenetre(int l, int h){  
		this.largeur=l;
		this.hauteur=h;
		this.setTitle("Simulation Fourmis");
		this.setSize(largeur, hauteur);
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public Fenetre(Monde m){  
		this.largeur=800;
		this.hauteur=800;
		this.m=m;
		this.setTitle("Simulation Fourmis");
		this.setSize(largeur, hauteur);
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void setFrameMenu() {

		
		Panneau cell1 = new Panneau();
		cell1.setPanneau(1,0);
	    cell1.setBackground(Color.red);
	    cell1.setPreferredSize(new Dimension(400, 225));		
	    
	    JPanel cell2 = new JPanel();
	    cell2.setBackground(Color.red);
	    cell2.setPreferredSize(new Dimension(400, 50));
	    cell2.add(bt_jouer);
	    bt_jouer.addActionListener(this);
	    
	    JPanel cell3 = new JPanel();
	    cell3.setBackground(Color.red);
	    cell3.setPreferredSize(new Dimension(400, 50));
	    cell3.add(bt_param);
	    
	    content = new JPanel();
	    content.setPreferredSize(new Dimension(500, 500));
	    content.setBackground(Color.red);

	    content.setLayout(new GridBagLayout());
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    content.add(cell1, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER; 
	    
	    gbc.gridy = 1;	
	    content.add(cell2, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    
	    gbc.gridy = 2;	
	    content.add(cell3, gbc);
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
    
	    this.setContentPane(content);
	}
	
	public void setFrameParam() {		
		JLabel map_lb = new JLabel("Taille de la carte : ");
		map_lb.setPreferredSize(new Dimension(250, 30));
		map.setPreferredSize(new Dimension(50, 30));
		
		JLabel nb_lb = new JLabel("Nombre de fourmis (au départ) : ");
		nb_lb.setPreferredSize(new Dimension(250, 30));
		nb.setPreferredSize(new Dimension(50, 30));
		
		JLabel lb_nourriture = new JLabel("Nombre de nourriture sur la carte : ");
		lb_nourriture.setPreferredSize(new Dimension(250, 30));
		nb_n.setPreferredSize(new Dimension(50, 30));
		
		JLabel lb_home = new JLabel("Nombre de fourimillière sur la carte : ");
		lb_home.setPreferredSize(new Dimension(250, 30));
		nb_h.setPreferredSize(new Dimension(50, 30));
				
		JLabel nb_actions_lb = new JLabel("Nombre d'actions par fourmis : ");
		nb_actions_lb.setPreferredSize(new Dimension(250, 30));
		nb_actions.setPreferredSize(new Dimension(50, 30));
		
		
		JLabel lb_nb_m = new JLabel("Nombre de tours : ");
		lb_nb_m.setPreferredSize(new Dimension(250, 30));
		nb_manche.setPreferredSize(new Dimension(50, 30));
		
		JLabel lb_time = new JLabel("Temps entre chaque action (en ms): ");
		lb_time.setPreferredSize(new Dimension(250, 30));
		time.setPreferredSize(new Dimension(50, 30));
		
		ButtonGroup bg = new ButtonGroup();
		check1.setPreferredSize(new Dimension(150, 30));
		check2.setPreferredSize(new Dimension(150, 30));
		bg.add(check1);
		bg.add(check2);
		
		bt_lancer.addActionListener(this);
		
		JPanel content = new JPanel();
	    content.setPreferredSize(new Dimension(500, 500));
	    GridLayout g = new GridLayout(9,2,10,10);
	    content.setLayout(g);
	    content.add(map_lb);
	    content.add(map);
	    content.add(nb_lb);
	    content.add(nb);
	    content.add(lb_nourriture);
	    content.add(nb_n);
	    content.add(lb_home);
	    content.add(nb_h);
	    content.add(nb_actions_lb);
	    content.add(nb_actions);
	    content.add(lb_nb_m);
	    content.add(nb_manche);
	    content.add(lb_time);
	    content.add(time);
	    content.add(check1);
	    content.add(check2);
	    content.add(bt_lancer);
	    
	    this.setContentPane(content);
	}
	
	public void setFrameMap(int taille) {	
		JPanel content = new JPanel();
		GridLayout g = new GridLayout(taille,taille,1,1);
		content.setLayout(g);
	    content.setPreferredSize(new Dimension(800, 800));
		
		this.dim=(this.largeur)/taille;
		this.Grid=new Panneau[taille][taille];
	    for(int i=0;i<taille;i++) {
		   for(int j=0;j<taille;j++) {
			   Grid[i][j]=new Panneau();
			   Grid[i][j].setPanneau(2,dim);
			   Grid[i][j].setPreferredSize(new Dimension(dim,dim));
		   }
	   }
	 
	    for(int i=0;i<taille;i++) {
			for(int j=0;j<taille;j++) {
				if((m.getTable(i,j)).getValue()=='N') {
					Grid[i][j].setPanneau(3,dim);
					content.add(Grid[i][j]);
				}
				if((m.getTable(i,j)).getValue()=='F') {
					Grid[i][j].setPanneau(4,dim);
					content.add(Grid[i][j]);
				}
				else {
					content.add(Grid[i][j]);
				}
			}
	    }
	    content.getIgnoreRepaint();
	    this.setContentPane(content);
	}
	
	public void update(int x2, int y2,int x, int y) {
		
		Grid[x2][y2].setPanneau(5,dim);
		if((m.getTable(x,y)).getValue()=='A' + ' ' + '+' + ' ' + 'N') {
			Grid[x][y].setPanneau(3,dim);
		}
		if((m.getTable(x,y)).getValue()=='A' + ' ' +  '+' + ' ' + 'F') {
			Grid[x][y].setPanneau(4,dim);
		}
		else {
			Grid[x][y].setPanneau(2,dim);
		}
		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		if(src==bt_jouer) {
			this.dispose();
			Fenetre f=new Fenetre(500,300);
			f.setFrameParam();
		}
		if(src==bt_lancer) {
			int taille=Integer.parseInt(map.getText());
			int nb_a=Integer.parseInt(nb_actions.getText());
			int nf=Integer.parseInt(nb.getText());
			int t=Integer.parseInt(time.getText());
			int nb_food=Integer.parseInt(nb_n.getText());
			int nb_home=Integer.parseInt(nb_h.getText());
			int nb_gen=Integer.parseInt(nb_manche.getText());
			int nb_fourmilliere= Integer.parseInt(nb_h.getText());
			int nb_nourriture =Integer.parseInt(nb_n.getText());
			boolean aff=check1.isSelected();
			this.dispose();
			Moteur simul=new Moteur(nf,taille,t,nb_a,nb_gen,nb_fourmilliere,nb_nourriture,aff);
			simul.boucleJeu();
		}
		if(src==timer) {
			
		}
	}     
}