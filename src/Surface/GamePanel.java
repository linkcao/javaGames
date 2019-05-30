package Surface;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.stream.IIOByteBuffer;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0, y = 0;
    Image image = new ImageIcon("img/background1.jpg").getImage();

    @Override
    public void paint(Graphics g) {
    	// TODO Auto-generated method stub
    	super.paint(g);
    	g.drawImage(image, x, y, null);
    	
    }
	
}
