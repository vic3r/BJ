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
	public BlackJack(){
		super("21 BlackJack");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(new Baraja(this));
		this.pack();
		this.setVisible(true);
	}
	
	public void guardar(){
		//guarda la partida actual, es disable cuando el dealer empieza a repartir
		try {
			this.save=new PrintWriter(new FileWriter("guardar.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se pudo guardar");
		}
		this.save.println("el jugador tiene: ");
		this.save.close();
		
	}
	public void abrir(){
		//permite abrir una paetida previamente guardada
		try {
			this.load=new BufferedReader(new FileReader("guardar.txt"));
			String line;
			while((line=this.load.readLine())!=null){
				System.out.println(line);
			}

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
