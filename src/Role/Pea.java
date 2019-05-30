package Role;


import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/** 
 * 豌豆类 
 * @author cxt 
 * 
 */  
public class Pea extends JPanel implements Runnable{  
	
    
	 //豌豆射手在画板中的位置
    static int x = 130;  
    static int y = 100;  
    //子弹的位置
    public static int mx = 150;
    public static int my = y;
	//角色的步长  
    static int step = 10;  
    //角色是否移动  
    public static boolean up = false;  
    public static boolean down = false;  
    public static boolean left = false;  
    public static boolean right = false;  
	int SNH = 500 ; // 豌豆的攻击力
	
    public int getMx() {
		return mx;
	}
	public int getMy() {
		return my;
	}
	public int getX() {
		return x;
	}
	public static void setX(int x) {
		Player.x = x;
	}
	public int getY() {
		return y;
	}
	public static void setY(int y) {
		Player.y = y;
	}


    public int getSNH() {
		return SNH;
	}
	public void setSNH(int sNH) {
		SNH = sNH;
	}

	//绘画方法 绘制植物以及子弹的位置
   @Override
protected void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	super.paintComponent(g);
	g.drawImage(Toolkit.getDefaultToolkit().getImage("img/sheshou.gif"),x,y,this);
	g.drawImage(Toolkit.getDefaultToolkit().getImage("img/zidan.png"),mx,y,this);
	
   }
    /** 
     * 角色移动的方法 
     */  
   @Override
public void run() {
	// TODO Auto-generated method stub
	   while(true) {
		   mx = mx + 6;		//子弹前进
		   this.repaint(); //重绘物体
       try {
        Thread.sleep(80); //线程休眠
      } catch (InterruptedException e) {
        e.printStackTrace();
       }
	  }
}
   
    public void move(){ 
    	if(x>0 && x<1340 ) //边界判定
    	{
        if(up){  
            y=y-step; //向上移动  
        }  
        if(down){  		
            y=y+step;  //向下移动
        }
        this.repaint();
    	}
    	else if(x<=0)
        	x++;
        else if(x>=1340)
        	x--;
    }  
    
}  