import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Naipe {
	
	private static final String[] valores= {"As",
											"Dos",
											"Tres",
											"Cuatro",
											"Cinco", 
											"Seis",
											"Siete",
											"Ocho",
											"Nueve",
											"Diez",
											"Joto",
											"Queen", 
											"Rey"},
						  		  figuras= {"Espadas",
											"Corazones",
											"Trebol",
											"Diamantes"}; 
	private static final Image[] naipesImg= {new ImageIcon("Cartas\\Picas\\1.png").getImage(),
											new ImageIcon("Cartas\\Picas\\2.png").getImage(),
											new ImageIcon("Cartas\\Picas\\3.png").getImage(),
											new ImageIcon("Cartas\\Picas\\4.png").getImage(),
											new ImageIcon("Cartas\\Picas\\5.png").getImage(),
											new ImageIcon("Cartas\\Picas\\6.png").getImage(),
											new ImageIcon("Cartas\\Picas\\7.png").getImage(),
											new ImageIcon("Cartas\\Picas\\8.png").getImage(),
											new ImageIcon("Cartas\\Picas\\9.png").getImage(),
											new ImageIcon("Cartas\\Picas\\10.png").getImage(),
											new ImageIcon("Cartas\\Picas\\11.png").getImage(),
											new ImageIcon("Cartas\\Picas\\12.png").getImage(),
											new ImageIcon("Cartas\\Picas\\13.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\1.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\2.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\3.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\4.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\5.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\6.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\7.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\8.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\9.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\10.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\11.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\12.png").getImage(),
											new ImageIcon("Cartas\\Corazones\\13.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\1.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\2.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\3.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\4.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\5.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\6.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\7.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\8.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\9.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\10.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\11.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\12.png").getImage(),
											new ImageIcon("Cartas\\Trebol\\13.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\1.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\2.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\3.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\4.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\5.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\6.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\7.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\8.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\9.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\10.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\11.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\12.png").getImage(),
											new ImageIcon("Cartas\\Diamante\\13.png").getImage()};																	
											
	private static final Image dorsoImagen= new ImageIcon("Cartas\\Fondo_carta.png").getImage();
	private int figura, //del 0 al 3 
				valor;  //del 0 al 12
	private boolean[][] validacion;
	private Random random;
	
	public Naipe(){
		this.random= new Random();
		this.validacion= new boolean[4][13];
		this.valor=this.random.nextInt(13);
		this.figura=this.random.nextInt(4);
		this.validacion[this.figura][this.valor]=true;
	}
	
	public String toString(){//Valores[valor]+"de"+figuras[Figura];
		return this.valores[valor]+" de "+this.figuras[figura];//duda
	}
	
	public Image getImage(){
		System.out.println(this);
		return this.naipesImg[(this.valor)+(this.figura*13)];//duda 
	}
	public Image getDorsoImage(){
		return this.dorsoImagen;
	}
	public void setValor(){
		do{
			this.valor=this.random.nextInt(13);
			this.figura=this.random.nextInt(4);
			//this.validacion[this.figura][this.valor]=true;
			
		}
		while(this.validacion[this.figura][this.valor]==true);
		this.validacion[this.figura][this.valor]=true;
	}
	public void resetearBaraja(){
		for(int f=0; f<this.validacion.length;f++){
			for(int c=0; c<this.validacion[f].length;c++){
				this.validacion[f][c]=false;
			}
		}
	}
	public int getValor(){
		return this.valor;//duda
	}

}
