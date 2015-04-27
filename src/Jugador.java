import javax.swing.JOptionPane;


public class Jugador {

	protected int saldo,
				  apuesta,
				  mano;
	protected String nombre; 
	protected boolean perdio;
	protected Naipe[] juego; //las cartas que va teniendo el jugador
	private int temporal;
	
	public Jugador(){
		this.juego= new Naipe[30];
		this.setNombre();
		this.setSaldo();
		this.mano=0;
		this.apuesta=0;
		
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
		this.saldo-=this.apuesta;
		return apuesta; //Regresa la suma de puntos que lleva en ese momento el jugador en función de las cartas que tiene)
	}
	public void setMano(){
		this.temporal=this.juego[0].getValor();
		if(this.temporal==0){
			this.temporal=11;
		}else if(this.temporal>10){
			this.temporal=10;
		}else{
			this.temporal+=1;
		}
		this.mano+=this.temporal;
		System.out.println("La mano es "+this.mano);
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
		if(JOptionPane.showConfirmDialog(null, "Quieres otra carta")>0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isBlackJack(){
		return false;// regresa si el jugador tiene un BlackJack
	}
	
}
