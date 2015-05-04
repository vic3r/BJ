import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class BlackJack extends JFrame {
	
	PrintWriter save; 
	BufferedReader load;
	Baraja juegoEnCurso;
	
	public BlackJack(){
		super("21 BlackJack");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		PanelComportamiento ganador= new PanelComportamiento();
		this.juegoEnCurso=new Baraja(this, ganador);
		this.add(juegoEnCurso);
		this.add(ganador, BorderLayout.WEST);
		this.pack();
		this.setVisible(true);
	}
	
	public void guardar(){
		//guarda la partida actual, es disable cuando el dealer empieza a repartir
		try {
			this.save=new PrintWriter(new FileWriter("guardar.txt"));
			this.save.println(this.juegoEnCurso.noJugador);
			for(int i=0; i<this.juegoEnCurso.jugadores.length;i++){
				this.save.println(this.juegoEnCurso.jugadores[i].nombre);
				this.save.println(this.juegoEnCurso.jugadores[i].saldo);
			}
			
			this.save.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se pudo guardar");
		}
		
		
	}
	public void abrir(){
		//permite abrir una paetida previamente guardada
		try {
			this.load=new BufferedReader(new FileReader("guardar.txt"));
			String line;
			String[] nombres;
			int[] saldos;
			int ll=1;
			int j=0,
				t=0;
			this.juegoEnCurso.noJugador=Integer.parseInt(this.load.readLine());
			nombres=new String[this.juegoEnCurso.noJugador];
			saldos=new int[this.juegoEnCurso.noJugador];
			while((line=this.load.readLine())!=null){
				//System.out.println(line);
				if(ll%2==0){
					saldos[t]=Integer.parseInt(line);
					t++;
				}else{
					nombres[j]=line;
					j++;
				}
				ll++;
			}
			this.juegoEnCurso.crearJugador(this.juegoEnCurso.noJugador, nombres, saldos);
			this.load.close();
			this.juegoEnCurso.repaint();

		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No hay ninguna partida guardada");
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Archivo guardado corrupto");
			
		}		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlackJack a=new BlackJack();
	}
}
