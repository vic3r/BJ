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
	
	private int noJugador,
				nvoNaipe,
				conteoCartas,
				conteoBotones,
				x;
	private boolean selector;
	private Image maletin; 
	private Font font;
	
	protected JButton guardar,
					  lastMatch,
					  nextPartida;
	
	private Jugador[] jugadores;
	private Dealer dealer;
	private JLabel[] nombres,
					 saldos;
					 //apuestas;
	private JLabel nombreDealer,
				   saldoDealer,
				   apuestaDealer;
	private Thread hilo;
	private JButton[] botones= {new JButton("1 Jugador"), 
						new JButton("2 Jugadores"), 
						new JButton("3 Jugadores"), 
						new JButton("4 Jugadores")};
	private JButton[] botonesApuesta;
	private JTextField[] apuestas;
	private BlackJack juego;
	
	public Baraja(BlackJack juego){
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
		this.font = new Font("Verdana", Font.BOLD, 14);
		this.guardar= new JButton("Guardar");
		this.guardar.addActionListener(this);
		this.add(this.guardar);
		this.lastMatch= new JButton("Cargar partida");
		this.lastMatch.addActionListener(this);
		this.add(this.lastMatch);
		this.nextPartida= new JButton("Siguiente partida");
		this.juego=juego;
		this.baraja=new Naipe[30];
		this.dealer.juego=this.baraja;
		this.nvoNaipe=0;
		this.conteoCartas=0;
		this.x=0;
		this.conteoBotones=0;
		this.selector=true;
		this.hilo= new Thread();
		//this.hilo.start();
	}
	
	public Jugador[] crearJugador(){
		this.nombres = new JLabel[this.noJugador];
		this.saldos = new JLabel[this.noJugador];
		this.apuestas = new JTextField[this.noJugador];
		this.botonesApuesta= new JButton[this.noJugador];
		if(this.noJugador<=4){
			for(int i=0; i<this.noJugador;i++){
				this.jugadores[i]=new Jugador(i+1);
				this.jugadores[i].juego=this.baraja;
				this.nombres[i]=new JLabel(this.jugadores[i].getNombre());
				this.saldos[i]=new JLabel("Saldo: "+this.jugadores[i].getSaldo()+"");
				this.apuestas[i]= new JTextField("");
				this.botonesApuesta[i]= new JButton("Apostar");
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
				this.next();//
			//this.mezclar();
				this.baraja[this.nvoNaipe].setValor();
			}
			g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3-40, this);
			this.dealer.setMano();
		}
		switch(this.noJugador){
			case 1:
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
				this.jugadores[0].setMano();
				this.posicion=this.jugadores[0].getMano();
				break;
			case 2:
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
				this.jugadores[0].setMano();
				this.posicion=this.jugadores[0].getMano();
				
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
				this.jugadores[1].setMano();
				break;
			case 3:
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-247+this.x, getHeight()/3+180, this);
				this.jugadores[0].setMano();
				this.posicion=this.jugadores[0].getMano();
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
				this.jugadores[1].setMano();
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+165+this.x, getHeight()/3+180, this);
				this.jugadores[2].setMano();
				break;
			case 4:
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-345+this.x, getHeight()/2, this);
				this.jugadores[0].setMano();
				this.posicion=this.jugadores[0].getMano();
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
				this.jugadores[1].setMano();
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
				this.jugadores[2].setMano();
				this.baraja[this.nvoNaipe].setValor();
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+250+this.x, getHeight()/2, this);
				this.jugadores[3].setMano();
				break;
		}
		return g;
	}
	public Graphics paintExtraCartas(Graphics g, int referencia){
		switch(referencia){
		case 0:
			this.x+=10;
			this.baraja[this.nvoNaipe].setValor();
			g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3-40, this);
			this.dealer.setMano();
			break;
		case 1:
			this.x+=10;
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
			break;
		case 2:
			this.x+=10;
			this.baraja[this.nvoNaipe].setValor();
			if(this.noJugador==2){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
			}else if(this.noJugador==3){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-42+this.x, getHeight()/3+220, this);
			}else {
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2-157+this.x, getHeight()/2+100, this);
			}
			this.jugadores[1].setMano();
			break;
		case 3:
			this.x+=10;
			this.baraja[this.nvoNaipe].setValor();
			if(this.noJugador==3){
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+165+this.x, getHeight()/3+180, this);
			}else{
				g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+72+this.x, getHeight()/2+100, this);
			}
			this.jugadores[2].setMano();
			break;
		case 4:
			this.x+=10;
			this.baraja[this.nvoNaipe].setValor();
			g.drawImage(this.baraja[nvoNaipe].getImage(),getWidth()/2+250+this.x, getHeight()/2, this);
			this.jugadores[3].setMano();
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
					this.jugadores[i].apuesta=Integer.parseInt(this.apuestas[i].getText());
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
							while(this.jugadores[r].mano<21){
								if(this.jugadores[r].otraCarta()){
									this.paintExtraCartas(getGraphics(), r+1);
								}else{
									break;
								}
							}
						}
						while(this.dealer.mano<17){
							this.paintExtraCartas(getGraphics(), 0);
						}
						this.add(this.nextPartida);
						this.nextPartida.setBounds(getWidth()-100, getHeight()-30, 100, 30);
						this.nextPartida.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent ett) {
								// TODO Auto-generated method stub
								Baraja.super.paint(getGraphics());
								for(int i=0; i<Baraja.this.jugadores.length;i++){
									Baraja.this.botonesApuesta[i].setEnabled(true);;
								}
								remove(Baraja.this.nextPartida);
								
							}
						});
					}	
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*if(this.jugadores[0].otraCarta()==true){
			try{
				repaint();
				Thread.sleep(3000);
			}
			catch(Exception e){
				System.out.println();
			}
		}*/
		
	}
}