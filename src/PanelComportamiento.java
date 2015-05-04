import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.InputStream;


public class PanelComportamiento extends JPanel {

	private Baraja juegoEnCurso;
	private JLabel ganador;
	private JButton musica;
	private Image corona,
		  		  bj,
		  		  bJ;
	private InputStream explotar;
	private AudioStream estallido;
	protected boolean pase;
	
	public PanelComportamiento(){
		super();
		this.setPreferredSize(new Dimension(200,600));
		setLayout(null);
		this.setBackground(new Color(109,69,45));
		this.corona= new ImageIcon("cor.png").getImage();
		this.bj=new ImageIcon("rsz_21.png").getImage();
		this.bJ=new ImageIcon("rsz_black.png").getImage();
		try{
			this.explotar = new FileInputStream("shot.au");
			this.estallido = new AudioStream(this.explotar);
		}catch(Exception e){
			e.printStackTrace();
		}
		this.musica= new JButton("Musica");
		this.musica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					AudioPlayer.player.start(PanelComportamiento.this.estallido); 
					try {
						PanelComportamiento.this.explotar = new FileInputStream("shot.au");
						PanelComportamiento.this.estallido = new AudioStream(PanelComportamiento.this.explotar);
					} catch (Exception p) {
						p.printStackTrace();
					
				}
			}
		});
		this.add(this.musica);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(new Color(139,69,19));
		g.fillRect(getWidth()-195, getHeight()-595, getWidth()-10, getHeight()-10);
		g.setColor(new Color(218,165,32));
		g.fillRect(getWidth()-190, getHeight()-590, getWidth()-20, getHeight()-20);
		g.drawImage(this.corona, getWidth()-150, getHeight()-580, this);
		g.drawImage(this.bj, getWidth()-140, getHeight()-70, this);
		g.drawImage(this.bJ, getWidth()-165, getHeight()-150, this);
		this.musica.setBounds(getWidth()-160, getHeight()-470, 100, 30);
		try{
			if(this.pase){
				try{
					Thread.sleep(200);
					repaint();
				}catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			}
		}catch(NullPointerException e){
			
		}
	}
	public void ponerEtiqueta(){
			//this.ganador=
	}
		
}
