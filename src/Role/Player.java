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
 * 角色类 
 * @author cxt 
 * 
 */  
public class Player extends JPanel implements Runnable{  
    
    static int x = 0;  
    static int y = 0;  
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

	//角色的步长  
    static int step = 10;  
    //角色是否移动  
    public static boolean up = false;  
    public static boolean down = false;  
    public static boolean left = false;  
    public static boolean right = false;  
    //人物的状态 默认为2
    static int status = 2 ;
    
    public int getSNH() {
		return SNH;
	}
	public void setSNH(int sNH) {
		SNH = sNH;
	}

	int SNH = 500 ; //人物的攻击力
       

   public void setStatus(int a)//设置状态
   {
       status=a;
   }
   
   //绘画方法
   @Override
protected void paintComponent(Graphics g) {
	// TODO Auto-generated method stub
	super.paintComponent(g);
	switch (status) {
	case 0:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/主角/走路/向右走.gif"),x,y,this);break;
	case 1:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/主角/走路/向左走.gif"),x,y,this);break;
	case 2:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/主角/站立/面朝右站.gif"),x,y-15,this);break;
	case 3:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/主角/站立/面朝左站.gif"),x,y-15,this);break;
	case 4:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/主角/攻击/面朝右攻击方式1.gif"),x-25,y-25,this);break;
	case 5:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/主角/攻击/面朝左攻击方式1.gif"),x-25,y-25,this);break;
	
	}

   }
    /** 
     * 角色移动的方法 
     */  
   @Override
public void run() {
	// TODO Auto-generated method stub
	   move(); //人物移动
	   this.repaint(); //重绘物体
       try {
        Thread.sleep(1000); //线程休眠
      } catch (InterruptedException e) {
        e.printStackTrace();
       }
}
   
    public void move(){ 
    	if(x>0 && x<1340 ) //边界判定
    	{
        if(up){  
            //改变角色在地图中的位置  
            y=y-step;  
        }  
        if(down){  
            y=y+step;  
        }  
        if(left){  
            x=x-step;  
        }  
        if(right){  
            x=x+step;  
        }  
       this.repaint();
    	}
    	else if(x<=0)//边界
        	x++;
        else if(x>=1340)//边界
        	x--;
    }  
    
}  