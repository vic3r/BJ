import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Naipe {
	
	private static final String[] valores= new String[13],
						  		  figuras= new String[4]; 
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
											new ImageIcon("Cartas\\Diamantes\\1.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\2.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\3.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\4.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\5.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\6.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\7.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\8.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\9.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\10.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\11.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\12.png").getImage(),
											new ImageIcon("Cartas\\Diamantes\\13.png").getImage()};																	
											
	private static final Image dorsoImagen= new ImageIcon("Cartas\\Fondo_carta.png").getImage();
	private int figura, 
				valor; 
	
	
	public Naipe(){
		
	}
	public String toString(){//Valores[valor]+"de"+figuras[Figura];
		
		return "";//duda
	}
	
	public Image getImage(){
		
		return this.naipesImg[0];//duda 
	}
	public int getValor(){
		return this.valor;//duda
	}

}
