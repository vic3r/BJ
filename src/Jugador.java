import javax.swing.JOptionPane;


public class Jugador {

	protected int saldo,
				  apuesta,
				  mano;
	protected String nombre; 
	protected boolean perdio;
	protected Naipe[] juego; //las cartas que va teniendo el jugador
	private int temporal,
				noPlayer;
	
	public Jugador(){
		this.juego= new Naipe[30];
		this.setNombre();
		this.setSaldo();
		this.mano=0;
		this.apuesta=0;
	}
	public Jugador(int num){
		this.juego= new Naipe[30];
		this.setNombre(num);
		this.setSaldo(num);
		this.noPlayer=num;
		this.mano=0;
		this.apuesta=0;
	}
	public void setSaldo(int num){
		do{
			this.saldo=Integer.parseInt(JOptionPane.showInputDialog("Escriba su saldo jugador "+num));
		}while(saldo<=10);
	}
	public void setSaldo(){
		do{
			this.saldo=Integer.parseInt(JOptionPane.showInputDialog("Escriba su saldo jugador"));
		}while(saldo<=10);
	}
	public int getSaldo(){
		return this.saldo;
	}
	public void setNombre(int num){
		this.nombre=JOptionPane.showInputDialog("Escriba su nombre jugador "+num);
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
		}else if(this.temporal>=10){
			this.temporal=10;
		}else{
			this.temporal+=1;
		}
		this.mano+=this.temporal;
		if(this.mano==21){
			if(this.isBlackJack()){
				this.ganoPartida();
			}
		}
		else if(this.mano>21){
			if(this.isBlackJack()){
				this.perdioPartida();
			}
		}
		
		System.out.println("La mano es "+this.mano);
	}
	public int getMano(){
		return this.mano;
	}
	public void ganoPartida(){
		if(this.noPlayer==0){
			JOptionPane.showMessageDialog(null, "Fin del juego, gano el Dealer");
		}else{
			JOptionPane.showMessageDialog(null, "Fin del juego, gano el jugador "+this.noPlayer);
		}
		this.saldo+=(apuesta*2);
		//Este método lo mandan a llamar para indicarle al jugador que ganó esta partida y modifica la cantidad de dinero disponible 
	}
	public void perdioPartida( ){
		//(Este método lo mandan a llamar para indicarle al jugador que perdió esta partida y modifica la cantidad de dinero disponible).
		JOptionPane.showMessageDialog(null, "Perdió el jugador "+this.noPlayer+" la partida");
	}
	public void empatoParitda(){
		//(Este método lo mandan a llamar para indicarle al jugador que empató esta partida y modifica la cantidad de dinero disponible).
	}
	public boolean otraCarta(){
		 //(Regresa si el jugador quiere otra carta o no) 
		if(JOptionPane.showConfirmDialog(null, "Quieres otra carta jugador "+this.noPlayer)==0){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean isBlackJack(){
		return true;// regresa si el jugador tiene un BlackJack
	}
	
}
