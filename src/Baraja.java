import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Baraja extends JPanel implements ActionListener{
	private Naipe[] baraja;
	private int posicion; //la carta de la baraja que se esta dando 
	
	private int noJugador;
	private Image maletin; 
	private Font font;
	
	protected JButton guardar,
					  lastMatch;
	
	private Jugador[] jugadores;
	private Dealer dealer;
	private JLabel[] nombres,
					 saldos,
					 apuestas;
	private JLabel nombreDealer,
				   saldoDealer,
				   apuestaDealer;
	
	private BlackJack juego;
	
	public Baraja(BlackJack juego){
		super();
		this.setPreferredSize(new Dimension(1000,600));
		this.setLayout(null);
		this.setBackground(new Color(210,105,30));
		this.noJugador=Integer.parseInt(JOptionPane.showInputDialog("Cuantos jugadores jugarán? "));
		this.jugadores=new Jugador[this.noJugador];
		this.dealer= new Dealer();
		this.nombreDealer= new JLabel(this.dealer.getNombre());
		this.saldoDealer= new JLabel("Saldo: "+this.dealer.getSaldo()+"");
		this.apuestaDealer= new JLabel("Apuesta: "+0);
		
		this.crearJugador();
		this.maletin= new ImageIcon("rsz_chips.png").getImage();
		this.font = new Font("Verdana", Font.BOLD, 14);
		this.guardar= new JButton("Guardar");
		//this.guardar.setBounds(getWidth()/2+770, getHeight()/2, 100, 30);
		this.guardar.addActionListener(this);
		this.add(this.guardar);
		this.lastMatch= new JButton("Cargar partida");
		//this.lastMatch.setBounds(getWidth()/2+870, getHeight()/2, 130, 30);
		this.lastMatch.addActionListener(this);
		this.add(this.lastMatch);
		this.juego=juego;
	}
	
	public Jugador[] crearJugador(){
		this.nombres = new JLabel[this.noJugador];
		this.saldos = new JLabel[this.noJugador];
		this.apuestas = new JLabel[this.noJugador];
		
		for(int i=0; i<this.noJugador;i++){
			this.jugadores[i]=new Jugador();
			this.nombres[i]=new JLabel(this.jugadores[i].getNombre());
			this.saldos[i]=new JLabel("Saldo: "+this.jugadores[i].getSaldo()+"");
			this.apuestas[i]= new JLabel("Apuesta: "+0);
			this.add(this.nombres[i]);
			this.add(this.saldos[i]);
			this.add(this.apuestas[i]);
		}
		return this.jugadores;
	}

	public Graphics paintJugador(Graphics g){
		g.setColor(Color.YELLOW);
		switch(this.noJugador){
			case 1:
				g.drawRect(getWidth()/2-42, getHeight()/3+220, 85, 100);
				g.setColor(Color.BLACK);
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3), getHeight()/3+175,100,30);
				this.apuestas[0].setBounds(getWidth()/2-(((this.apuestas[0].getText()).length())*3), getHeight()/3+190,100,30);
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3), getHeight()/3+320,100,30);
				break;
			case 2:
				g.drawRect(getWidth()/2-157, getHeight()/2+100, 85, 100);
				g.drawRect(getWidth()/2+72, getHeight()/2+100, 85, 100);
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3)-115, getHeight()/3+150,100,30);
				this.apuestas[0].setBounds(getWidth()/2-(((this.apuestas[0].getText()).length())*3)-115, getHeight()/3+165,100,30);
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3)-115, getHeight()/3+300,100,30);
				this.nombres[1].setBounds(getWidth()/2-(((this.nombres[1].getText()).length())*3)+115, getHeight()/3+150,100,30);
				this.apuestas[1].setBounds(getWidth()/2-(((this.apuestas[1].getText()).length())*3)+115, getHeight()/3+165,100,30);
				this.saldos[1].setBounds(getWidth()/2-(((this.saldos[1].getText()).length())*3)+115, getHeight()/3+300,100,30);
				break;
			case 3:
				g.drawRect(getWidth()/2-247, getHeight()/3+180, 85, 100);
				g.drawRect(getWidth()/2-42, getHeight()/3+220, 85, 100);
				g.drawRect(getWidth()/2+165, getHeight()/3+180, 85, 100);
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3)-205, getHeight()/3+130,100,30);
				this.apuestas[0].setBounds(getWidth()/2-(((this.apuestas[0].getText()).length())*3)-205, getHeight()/3+145,100,30);
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3)-205, getHeight()/3+275,100,30);
				this.nombres[1].setBounds(getWidth()/2-(((this.nombres[1].getText()).length())*3), getHeight()/3+175,100,30);
				this.apuestas[1].setBounds(getWidth()/2-(((this.apuestas[1].getText()).length())*3), getHeight()/3+190,100,30);
				this.saldos[1].setBounds(getWidth()/2-(((this.saldos[1].getText()).length())*3), getHeight()/3+320,100,30);
				this.nombres[2].setBounds(getWidth()/2-(((this.nombres[2].getText()).length())*3)+205, getHeight()/3+130,100,30);
				this.apuestas[2].setBounds(getWidth()/2-(((this.apuestas[2].getText()).length())*3)+205, getHeight()/3+145,100,30);
				this.saldos[2].setBounds(getWidth()/2-(((this.saldos[2].getText()).length())*3)+205, getHeight()/3+275,100,30);
				break;
			case 4:
				g.drawRect(getWidth()/2-345, getHeight()/2, 85, 100);
				g.drawRect(getWidth()/2-157, getHeight()/2+100, 85, 100);
				g.drawRect(getWidth()/2+72, getHeight()/2+100, 85, 100);
				g.drawRect(getWidth()/2+250, getHeight()/2, 85, 100);
				
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3)-305, getHeight()/3+50,100,30);
				this.apuestas[0].setBounds(getWidth()/2-(((this.apuestas[0].getText()).length())*3)-305, getHeight()/3+65,100,30);
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3)-305, getHeight()/3+200,100,30);
				this.nombres[1].setBounds(getWidth()/2-(((this.nombres[1].getText()).length())*3)-115, getHeight()/3+150,100,30);
				this.apuestas[1].setBounds(getWidth()/2-(((this.apuestas[1].getText()).length())*3)-115, getHeight()/3+165,100,30);
				this.saldos[1].setBounds(getWidth()/2-(((this.saldos[1].getText()).length())*3)-115, getHeight()/3+300,100,30);
				this.nombres[2].setBounds(getWidth()/2-(((this.nombres[2].getText()).length())*3)+115, getHeight()/3+150,100,30);
				this.apuestas[2].setBounds(getWidth()/2-(((this.apuestas[2].getText()).length())*3)+115, getHeight()/3+165,100,30);
				this.saldos[2].setBounds(getWidth()/2-(((this.saldos[2].getText()).length())*3)+115, getHeight()/3+300,100,30);
				this.nombres[3].setBounds(getWidth()/2-(((this.nombres[3].getText()).length())*3)+295, getHeight()/3+50,100,30);
				this.apuestas[3].setBounds(getWidth()/2-(((this.apuestas[3].getText()).length())*3)+295, getHeight()/3+65,100,30);
				this.saldos[3].setBounds(getWidth()/2-(((this.saldos[3].getText()).length())*3)+295, getHeight()/3+200,100,30);
				
				break;
			default:
				break;
		}
		return g;
	}
	public Graphics paintFondo(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(139,69,19));
		g.fillOval(getWidth()/2-600, getHeight()/2-925, 1200, 1200);
		g.setColor(new Color(0,153,0));
		g.fillOval(getWidth()/2-550, getHeight()/2-850, 1100 , 1100);
		g.drawImage(this.maletin, getWidth()/2-110, -40, 220, 183, this);
		g.setColor(Color.YELLOW);
		g.drawRect(getWidth()/2-42, getHeight()/3-40, 85, 100);
		this.guardar.setBounds(getWidth()-230, getHeight()/2-300, 100, 30);
		this.lastMatch.setBounds(getWidth()-130, getHeight()/2-300, 130, 30);
		
		this.nombreDealer.setBounds(getWidth()/2-(((this.nombreDealer.getText()).length())*3), getHeight()/3-65,100,30);
		this.saldoDealer.setBounds(getWidth()/2-(((this.saldoDealer.getText()).length())*3), getHeight()/3+60,100,30);
		this.apuestaDealer.setBounds(getWidth()/2-(((this.apuestaDealer.getText()).length())*3), getHeight()/3+75,100,30);
		this.add(this.nombreDealer);
		this.add(this.saldoDealer);
		this.add(this.apuestaDealer);
		g.setFont(this.font);
		g.drawString("BLACKJACK PAYS 3 to 2", getWidth()/2-90, getHeight()/2+25);
		g.setColor(Color.WHITE);
		g.drawString("INSURANCE", getWidth()/2-45, getHeight()/2+50);
		

		return g;
	}

	public void paintComponent(Graphics g){
	//	super.paintComponent(g);
		this.paintFondo(g);
		this.paintJugador(g);
	}
	public void mezclar(){
		
	}
	
	public Naipe next(){
		return this.baraja[0]; //duda
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(this.guardar==evt.getSource()){
			this.juego.guardar();
			
		}else if(this.lastMatch==evt.getSource()){
			this.juego.abrir();
		}
	}
}
