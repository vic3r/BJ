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
import javax.swing.JTextField;


public class Baraja extends JPanel implements ActionListener, Runnable{
	private Naipe[] baraja;//
	private int posicion; //la carta de la baraja que se esta dando 
	
	protected int noJugador,
				nvoNaipe,
				conteoCartas,
				conteoBotones,
				x,
				y,
				barajeada;
	private boolean selector,
					cartaFondo;
	private Image maletin,
				  equis; 
	private Font font;
	
	private JButton guardar,
					  lastMatch,
					  nextPartida;
	
	protected Jugador[] jugadores;
	protected boolean[] identificadores;
	private Dealer dealer;
	private PanelComportamiento ganador;
	private JLabel[] nombres,
					 saldos;
					 //apuestas;
	private JLabel nombreDealer,
				   saldoDealer,
				   apuestaDealer;
	private Thread hilo;
	protected JButton[] botones= {new JButton("1 Jugador"), 
						new JButton("2 Jugadores"), 
						new JButton("3 Jugadores"), 
						new JButton("4 Jugadores")};
	private JButton[] botonesApuesta;
	private JTextField[] apuestas;
	private BlackJack juego;
	
	public Baraja(BlackJack juego, PanelComportamiento ganador){
		super();
		this.setPreferredSize(new Dimension(1000,600));
		this.setLayout(null);
		this.setBackground(new Color(210,105,30));
		this.noJugador=0;
		this.dealer= new Dealer();
		this.nombreDealer= new JLabel(this.dealer.getNombre());
		this.saldoDealer= new JLabel("Saldo: "+this.dealer.getSaldo()+"");
		this.apuestaDealer= new JLabel("Apuesta: "+0);
		
		this.maletin= new ImageIcon("rsz_chips.png").getImage();
		this.equis= new ImageIcon("rsz_redx.png").getImage();
		this.font = new Font("Verdana", Font.BOLD, 14);
		this.guardar= new JButton("Guardar");
		this.guardar.addActionListener(this);
		this.add(this.guardar);
		this.lastMatch= new JButton("Cargar partida");
		this.lastMatch.addActionListener(this);
		this.add(this.lastMatch);
		this.nextPartida= new JButton("Siguiente partida");
		this.juego=juego;
		this.ganador=ganador;
		this.baraja=new Naipe[30];
		this.dealer.juego=this.baraja;
		this.nvoNaipe=0;
		this.conteoCartas=0;
		this.y=this.x=0;
		this.conteoBotones=0;
		this.barajeada=0;
		this.selector=true;
		this.hilo= new Thread();
		//this.hilo.start();
	}
	
	public Jugador[] crearJugador(){
		this.nombres = new JLabel[this.noJugador];
		this.saldos = new JLabel[this.noJugador];
		this.apuestas = new JTextField[this.noJugador];
		this.botonesApuesta= new JButton[this.noJugador];
		this.identificadores=new boolean[this.noJugador];
		if(this.noJugador<=4){
			for(int i=0; i<this.noJugador;i++){
				this.jugadores[i]=new Jugador(i+1);
				this.jugadores[i].juego=this.baraja;
				this.nombres[i]=new JLabel(this.jugadores[i].getNombre());
				this.saldos[i]=new JLabel("Saldo: "+this.jugadores[i].getSaldo()+"");
				this.apuestas[i]= new JTextField("Pon tu apuesta");
				this.botonesApuesta[i]= new JButton("Apostar");
				this.identificadores[i]=true;
				this.add(this.nombres[i]);
				this.add(this.saldos[i]);
				this.add(this.apuestas[i]);
				this.add(this.botonesApuesta[i]);
				this.botonesApuesta[i].addActionListener(this);
				}
		}else{
			try{
				throw new Exception("Excediste el numero de jugadores posibles");
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error en el numero limite de jugadores");
			}
		}
		return this.jugadores;
	}
	public Jugador[] crearJugador(int guardado, String[] nombres,int[] saldos ){
		this.noJugador=guardado;
		remove(this.botones[0]);
		remove(this.botones[1]);
		remove(this.botones[2]);
		remove(this.botones[3]);
		this.jugadores= new Jugador[this.noJugador];
		this.nombres = new JLabel[this.noJugador];
		this.saldos = new JLabel[this.noJugador];
		this.apuestas = new JTextField[this.noJugador];
		this.botonesApuesta= new JButton[this.noJugador];
		this.identificadores=new boolean[this.noJugador];
		if(this.noJugador<=4){
			for(int i=0; i<this.noJugador;i++){
				this.jugadores[i]=new Jugador(i+1, nombres[i], saldos[i]);
				this.jugadores[i].juego=this.baraja;
				this.nombres[i]=new JLabel(this.jugadores[i].getNombre());
				this.saldos[i]=new JLabel("Saldo: "+this.jugadores[i].getSaldo()+"");
				this.apuestas[i]= new JTextField("Pon tu apuesta");
				this.botonesApuesta[i]= new JButton("Apostar");
				this.identificadores[i]=true;
				this.add(this.nombres[i]);
				this.add(this.saldos[i]);
				this.add(this.apuestas[i]);
				this.add(this.botonesApuesta[i]);
				this.botonesApuesta[i].addActionListener(this);
				}
		}else{
			try{
				throw new Exception("Excediste el numero de jugadores posibles");
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Error en el numero limite de jugadores");
			}
		}
		return this.jugadores;
	}
	public void dormir(){
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	public Graphics paintJugador(Graphics g){
		g.setColor(Color.YELLOW);
		switch(this.noJugador){
			case 1:
				g.drawRect(getWidth()/2-42, getHeight()/3+220, 85, 100);
				g.setColor(Color.BLACK);
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3), getHeight()/3+150,100,30);
				this.apuestas[0].setBounds(getWidth()/2-50, getHeight()/3+180,100,30);
				this.saldos[0].setText("Saldo: "+this.jugadores[0].getSaldo());
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3), getHeight()/3+320,100,30);
				this.botonesApuesta[0].setBounds(getWidth()/2-50, getHeight()/3+350,100,30);
				break;
			case 2:
				g.drawRect(getWidth()/2-157, getHeight()/2+100, 85, 100);
				g.drawRect(getWidth()/2+72, getHeight()/2+100, 85, 100);
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3)-115, getHeight()/3+140,100,30);
				this.apuestas[0].setBounds(getWidth()/2-165, getHeight()/3+165,100,30);
				this.saldos[0].setText("Saldo: "+this.jugadores[0].getSaldo());
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3)-115, getHeight()/3+300,100,30);
				this.botonesApuesta[0].setBounds(getWidth()/2-165, getHeight()/3+330,100,30);
				this.nombres[1].setBounds(getWidth()/2-(((this.nombres[1].getText()).length())*3)+115, getHeight()/3+140,100,30);
				this.apuestas[1].setBounds(getWidth()/2+65, getHeight()/3+165,100,30);
				this.saldos[1].setText("Saldo: "+this.jugadores[1].getSaldo());
				this.saldos[1].setBounds(getWidth()/2-(((this.saldos[1].getText()).length())*3)+115, getHeight()/3+300,100,30);
				this.botonesApuesta[1].setBounds(getWidth()/2+65, getHeight()/3+330,100,30);
				break;
			case 3:
				g.drawRect(getWidth()/2-247, getHeight()/3+180, 85, 100);
				g.drawRect(getWidth()/2-42, getHeight()/3+220, 85, 100);
				g.drawRect(getWidth()/2+165, getHeight()/3+180, 85, 100);
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3)-205, getHeight()/3+110,100,30);
				this.apuestas[0].setBounds(getWidth()/2-255, getHeight()/3+145,100,30);
				this.saldos[0].setText("Saldo: "+this.jugadores[0].getSaldo());
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3)-205, getHeight()/3+275,100,30);
				this.botonesApuesta[0].setBounds(getWidth()/2-255, getHeight()/3+305,100,30);
				this.nombres[1].setBounds(getWidth()/2-(((this.nombres[1].getText()).length())*3), getHeight()/3+150,100,30);
				this.apuestas[1].setBounds(getWidth()/2-50, getHeight()/3+180,100,30);
				this.saldos[1].setText("Saldo: "+this.jugadores[1].getSaldo());
				this.saldos[1].setBounds(getWidth()/2-(((this.saldos[1].getText()).length())*3), getHeight()/3+320,100,30);
				this.botonesApuesta[1].setBounds(getWidth()/2-50, getHeight()/3+350,100,30);
				this.nombres[2].setBounds(getWidth()/2-(((this.nombres[2].getText()).length())*3)+205, getHeight()/3+110,100,30);
				this.apuestas[2].setBounds(getWidth()/2+155, getHeight()/3+145,100,30);
				this.saldos[2].setText("Saldo: "+this.jugadores[2].getSaldo());
				this.saldos[2].setBounds(getWidth()/2-(((this.saldos[2].getText()).length())*3)+205, getHeight()/3+275,100,30);
				this.botonesApuesta[2].setBounds(getWidth()/2+155, getHeight()/3+305,100,30);
				break;
			case 4:
				g.drawRect(getWidth()/2-345, getHeight()/2, 85, 100);
				g.drawRect(getWidth()/2-157, getHeight()/2+100, 85, 100);
				g.drawRect(getWidth()/2+72, getHeight()/2+100, 85, 100);
				g.drawRect(getWidth()/2+250, getHeight()/2, 85, 100);
				
				this.nombres[0].setBounds(getWidth()/2-(((this.nombres[0].getText()).length())*3)-305, getHeight()/3+40,100,30);
				this.apuestas[0].setBounds(getWidth()/2-355, getHeight()/3+65,100,30); 
				this.saldos[0].setText("Saldo: "+this.jugadores[0].getSaldo());
				this.saldos[0].setBounds(getWidth()/2-(((this.saldos[0].getText()).length())*3)-305, getHeight()/3+200,100,30);
				this.botonesApuesta[0].setBounds(getWidth()/2-355, getHeight()/3+230,100,30);
				this.nombres[1].setBounds(getWidth()/2-(((this.nombres[1].getText()).length())*3)-115, getHeight()/3+140,100,30);
				this.apuestas[1].setBounds(getWidth()/2-165, getHeight()/3+165,100,30);
				this.saldos[1].setText("Saldo: "+this.jugadores[1].getSaldo());
				this.saldos[1].setBounds(getWidth()/2-(((this.saldos[1].getText()).length())*3)-115, getHeight()/3+300,100,30);
				this.botonesApuesta[1].setBounds(getWidth()/2-165, getHeight()/3+330,100,30);
				this.nombres[2].setBounds(getWidth()/2-(((this.nombres[2].getText()).length())*3)+115, getHeight()/3+140,100,30);
				this.apuestas[2].setBounds(getWidth()/2+65, getHeight()/3+165,100,30);
				this.saldos[2].setText("Saldo: "+this.jugadores[2].getSaldo());
				this.saldos[2].setBounds(getWidth()/2-(((this.saldos[2].getText()).length())*3)+115, getHeight()/3+300,100,30);
				this.botonesApuesta[2].setBounds(getWidth()/2+65, getHeight()/3+330,100,30);
				this.nombres[3].setBounds(getWidth()/2-(((this.nombres[3].getText()).length())*3)+295, getHeight()/3+40,100,30);
				this.apuestas[3].setBounds(getWidth()/2+240, getHeight()/3+65,100,30);
				this.saldos[3].setText("Saldo: "+this.jugadores[3].getSaldo());
				this.saldos[3].setBounds(getWidth()/2-(((this.saldos[3].getText()).length())*3)+295, getHeight()/3+200,100,30);
				this.botonesApuesta[3].setBounds(getWidth()/2+245, getHeight()/3+230,100,30);
				
				break;
			default:
				break;
		}
		return g;
	}
	
	public Graphics paintFondo(Graphics g){
		//super.paintComponent(g);
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
	public Graphics paintMenu(Graphics g){
		for(int i=0;i<this.botones.length;i++){
			this.botones[i].setBounds(getWidth()/2-65, getHeight()/3+((i+6)*30), 130, 30 );
			this.add(this.botones[i]);
		}
		this.botones[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Baraja.this.noJugador=1;
				Baraja.this.jugadores=new Jugador[Baraja.this.noJugador];
				Baraja.this.crearJugador();
				remove(Baraja.this.botones[0]);
				remove(Baraja.this.botones[1]);
				remove(Baraja.this.botones[2]);
				remove(Baraja.this.botones[3]);
				Baraja.this.repaint();
			}
		} );
		this.botones[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Baraja.this.noJugador=2;
				Baraja.this.jugadores=new Jugador[Baraja.this.noJugador];
				Baraja.this.crearJugador();
				remove(Baraja.this.botones[0]);
				remove(Baraja.this.botones[1]);
				remove(Baraja.this.botones[2]);
				remove(Baraja.this.botones[3]);
				Baraja.this.repaint();
			}
		} );
		this.botones[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Baraja.this.noJugador=3;
				Baraja.this.jugadores=new Jugador[Baraja.this.noJugador];
				Baraja.this.crearJugador();
				remove(Baraja.this.botones[0]);
				remove(Baraja.this.botones[1]);
				remove(Baraja.this.botones[2]);
				remove(Baraja.this.botones[3]);
				Baraja.this.repaint();
			}
		} );
		this.botones[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Baraja.this.noJugador=4;
				Baraja.this.jugadores=new Jugador[Baraja.this.noJugador];
				Baraja.this.crearJugador();
				remove(Baraja.this.botones[0]);
				remove(Baraja.this.botones[1]);
				remove(Baraja.this.botones[2]);
				remove(Baraja.this.botones[3]);
				Baraja.this.repaint();
			}
		} );
		return g;
	}

	public Graphics paintCartas(Graphics g){
		if(this.x==0){
			super.paint(g);
		}
		if(this.noJugador>0){
			if(this.selector){
				this.baraja[nvoNaipe]= new Naipe();
				this.selector=false;
			}else{
				if(this.barajeada>5){
					this.next();
					this.selector=true;
					this.barajeada=0;
				}
				else{//this.next();
					this.mezclar();//
				}
				this.baraja[this.nvoNaipe].setValor();
				this.barajeada++;
			}
			g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3-40, this);
			if(this.x==20){
				//g.drawImage(this.baraja[nvoNaipe].getDorsoImage(),getWidth()/2-42+this.x, (getHeight()/3-40)+this.y, this);
				//this.hilo.start();
			}
			this.dealer.setMano();
			this.dormir();
		}
		switch(this.noJugador){
			case 1:
				if(this.identificadores[0]){
					//super.paintComponents(g);//
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
					this.jugadores[0].setMano();
					this.posicion=this.jugadores[0].getMano();
					this.dormir();
				}
				else{
					g.drawImage(this.equis,getWidth()/2-42, getHeight()/3+220, this);
				}
				break;
			case 2:
				if(this.identificadores[0]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
					this.jugadores[0].setMano();
					this.posicion=this.jugadores[0].getMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2-157, getHeight()/2+100, this);
				}
				if(this.identificadores[1]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
					this.jugadores[1].setMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2+72, getHeight()/2+100, this);
				}
				break;
			case 3:
				if(this.identificadores[0]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-247+this.x, getHeight()/3+180, this);
					this.jugadores[0].setMano();
					this.posicion=this.jugadores[0].getMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2-247, getHeight()/3+180, this);
				}
				if(this.identificadores[1]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
					this.jugadores[1].setMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2-42, getHeight()/3+220, this);
				}
				if(this.identificadores[2]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+165+this.x, getHeight()/3+180, this);
					this.jugadores[2].setMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2+165, getHeight()/3+180, this);
				}
				break;
			case 4:
				if(this.identificadores[0]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-345+this.x, getHeight()/2, this);
					this.jugadores[0].setMano();
					this.posicion=this.jugadores[0].getMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2-345, getHeight()/2, this);
				}
				if(this.identificadores[1]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
					this.jugadores[1].setMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2-157, getHeight()/2+100, this);
				}
				if(this.identificadores[2]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
					this.jugadores[2].setMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2+72, getHeight()/2+100, this);
				}
				if(this.identificadores[3]){
					this.baraja[this.nvoNaipe].setValor();
					g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+250+this.x, getHeight()/2, this);
					this.jugadores[3].setMano();
					this.dormir();
				}else{
					g.drawImage(this.equis,getWidth()/2+250, getHeight()/2, this);
				}
				break;
		}
		return g;
	}

	public Graphics paintExtraCartas(Graphics g, int referencia){
		switch(referencia){
		case 0:
			this.baraja[this.nvoNaipe].setValor();
			g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3-40, this);
			this.dealer.setMano();
			this.x+=20;
			break;
		case 1:
			this.baraja[this.nvoNaipe].setValor();
			if(this.noJugador==1){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
			}else if(this.noJugador==2){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
			}else if(this.noJugador==3){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-247+this.x, getHeight()/3+180, this);
			}else{
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-345+this.x, getHeight()/2, this);
			}
			this.jugadores[0].setMano();
			this.posicion=this.jugadores[0].getMano();
			this.x+=20;
			break;
		case 2:
			this.baraja[this.nvoNaipe].setValor();
			if(this.noJugador==2){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
			}else if(this.noJugador==3){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
			}else {
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
			}
			this.jugadores[1].setMano();
			this.x+=20;
			break;
		case 3:
			this.baraja[this.nvoNaipe].setValor();
			if(this.noJugador==3){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+165+this.x, getHeight()/3+180, this);
			}else{
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
			}
			this.jugadores[2].setMano();
			this.x+=20;
			break;
		case 4:
			this.baraja[this.nvoNaipe].setValor();
			g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+250+this.x, getHeight()/2, this);
			this.jugadores[3].setMano();
			this.x+=20;
			break;
		}
		return g;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.paintFondo(g);
		if(this.noJugador==0){
			this.paintMenu(g);
		}else{
			this.paintJugador(g);
		}
	}
	public void mezclar(){
		//this.baraja[this.nvoNaipe].setValor();
		this.baraja[this.nvoNaipe].resetearBaraja();
	}
	
	public Naipe next(){
		if((((this.noJugador+1)*2)+this.conteoCartas)>42){
			nvoNaipe++;
		}
		return this.baraja[nvoNaipe]; //duda regresa una nueva baraja
	}
	public void comparacion(){
		int max=0;
		int posG=0;
		for(int i=0; i<(this.noJugador);i++){
			if(this.jugadores[i].mano>max && this.jugadores[i].mano<=21){
				max=this.jugadores[i].mano;
				posG=i+1;
			}
		}
		if(this.dealer.mano<21){
			if(max>this.dealer.mano && max<21){
				this.jugadores[posG-1].ganoPartida();
			}else if(max==this.dealer.mano && max<=21){
				this.jugadores[posG-1].empatoParitda();
			}else{
				this.dealer.ganoPartida();
			}
		}else{
			if(max==0){
				//this.dealer.ganoPartida();
				JOptionPane.showMessageDialog(null, "Nadie gano");
			}else {
				for(int i=0; i<this.noJugador; i++){
					if(this.jugadores[i].mano<21){
						this.jugadores[i].ganoPartida();
					}
				}	
			}
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(this.guardar==evt.getSource()){
			this.juego.guardar();
			
		}else if(this.lastMatch==evt.getSource()){
			this.juego.abrir();
		}else{
			for(int i=0; i<this.botonesApuesta.length; i++){
				if(this.botonesApuesta[i]==evt.getSource()){
					this.guardar.setEnabled(false);
					this.lastMatch.setEnabled(false);
					try{
						this.jugadores[i].apuesta=Integer.parseInt(this.apuestas[i].getText());
						if(this.jugadores[i].apuesta<15){
							JOptionPane.showMessageDialog(null, "15 es la apuesta minima");
							break;
						}
						else{
							this.jugadores[i].getTotal();
							this.saldos[i].setText("Saldo: "+this.jugadores[i].getSaldo());
							//this.hilo.start();
							this.botonesApuesta[i].setEnabled(false);
							if(!this.botonesApuesta[i].isEnabled()){
								this.conteoBotones+=1;
							}
							if(this.conteoBotones==this.noJugador){
								for(int r=0; r<=1; r++){
									this.paintCartas(getGraphics());
									this.x+=20;
								}
		
								for(int r=0; r<this.jugadores.length; r++){
									this.x=40;
									while(this.jugadores[r].mano<21 &&(this.identificadores[r]==true)){
										if(this.jugadores[r].otraCarta()){
											this.paintExtraCartas(getGraphics(), r+1);
										}else{
											//this.x=40;
											break;
										}
									}
								}
								//this.cartaFondo=true;
								//this.hilo.interrupt();
								this.x=40;
								while(this.dealer.mano<17){
									this.paintExtraCartas(getGraphics(), 0);
									this.dormir();
								}
								this.comparacion();
								if(this.nextPartida.isDisplayable()){
									this.nextPartida.setEnabled(true);
								}else{
									this.add(this.nextPartida);
								}
								this.nextPartida.setBounds(getWidth()-100, getHeight()-30, 100, 30);
								//repaint();
								
								this.nextPartida.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent ett) {
										// TODO Auto-generated method stub
										Baraja.this.conteoBotones=0;
										for(int i=0; i<Baraja.this.jugadores.length;i++){
											Baraja.this.botonesApuesta[i].setEnabled(true);
											Baraja.this.jugadores[i].mano=0;
											if(Baraja.this.jugadores[i].saldo<15){
												Baraja.this.identificadores[i]=false;
												Baraja.this.botonesApuesta[i].setEnabled(false);
												Baraja.this.conteoBotones++;
											}
										}
										//remove(Baraja.this.nextPartida);
										Baraja.this.guardar.setEnabled(true);
										Baraja.this.lastMatch.setEnabled(true);
										Baraja.this.nextPartida.setEnabled(false);
										Baraja.super.paint(getGraphics());
										Baraja.this.dealer.mano=0;
										Baraja.this.x=0;
										//Baraja.this.ganador.pase=true;
										
										}
									});
								}
							}
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(null, "Por favor introduzca un valor válido");;
					}
				}
			}
		}
	}

	public Graphics paintCartaFondo(Graphics g){
		g.drawImage(this.baraja[nvoNaipe].getDorsoImage(),getWidth()/2-22, getHeight()/3-40, this);
		return g;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			this.paintCartaFondo(getGraphics());
		}
	}
}