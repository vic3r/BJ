import javax.swing.JOptionPane;


public class Jugador {

	protected int saldo;
	protected String nombre; 
	protected boolean perdio;
	protected Naipe[] juego; //las cartas que va teniendo el jugador
	
	public Jugador(){
		this.setNombre();
		this.setSaldo();
	}
	public void setSaldo(){
		do{
			this.saldo=Integer.parseInt(JOptionPane.showInputDialog("Escriba su saldo jugador"));
		}while(saldo<=10);
	}
	public int getSaldo(){
		return this.saldo;
	}
	public void setNombre(){
		this.nombre=JOptionPane.showInputDialog("Escriba su nombre jugador");
	}
	public String getNombre(){
		return this.nombre;
	}
	public boolean getPerdio(){
		return this.perdio;
	}
	public int getTotal(){
		int total=0;
		return total; //Regresa la suma de puntos que lleva en ese momento el jugador en función de las cartas que tiene)
	}
	public void ganoPartida(){
		//Este método lo mandan a llamar para indicarle al jugador que ganó esta partida y modifica la cantidad de dinero disponible 
	}
	public void perdioPartida( ){
		//(Este método lo mandan a llamar para indicarle al jugador que perdió esta partida y modifica la cantidad de dinero disponible).
	}
	public void empatoParitda(){
		//(Este método lo mandan a llamar para indicarle al jugador que empató esta partida y modifica la cantidad de dinero disponible).
	}
	public boolean otraCarta(){
		 //(Regresa si el jugador quiere otra carta o no) 
		return false;
	}
	public boolean isBlackJack(){
		return false;// regresa si el jugador tiene un BlackJack
	}
	
}
