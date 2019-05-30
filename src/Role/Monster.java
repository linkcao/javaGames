package Role;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

/** 
 * 怪兽类 
 * @author cxt 
 */
public class Monster extends JPanel implements Runnable
{
	private int MonsterID;//怪兽的ID
	private int zhuangtai=0;//怪兽的状态 决定怪物的贴图
	private int HP;//怪物血量
	public boolean beAtt = false; //是否被攻击
	public boolean aLive = true;	//是否存活
	//怪物在画板中的位置
	int x= 50;
	int y= 500; 
	Random r=new Random();//随机对象用来随机生成怪兽的出生点
	//构造函数
	public Monster(int id) {
		MonsterID = id;
		HP = 2000;
		x = 500 ;
		y = r.nextInt(200); //随机生成
	}
	
	public boolean isBeAtt() {
		return beAtt;
	}
	public void setBeAtt(boolean beAtt) {
		this.beAtt = beAtt;
	}
	public int getID()
	{
		return MonsterID;
	}
	public Monster(int id,int x,int y)
	{
		MonsterID=id;
		switch(MonsterID)
		{
		case 0:
		{
			HP=2000;
			break;
		}
		case 1:
		{
			HP=5000;
			break;
		}
		}
	}
	public int getHP()
	{
		return HP;
	}
	public void setzhuangtai(int m)
	{
		zhuangtai=m;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	//怪兽被攻击 血量减少
	public void beAtt(int SNH)
	{
		HP -= SNH;
		if( HP <= 0 )
			{
			setzhuangtai(3);
			aLive = false;
			repaint();
			}
	}
	
	//怪兽的绘画线程
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if(MonsterID == 0)	//不同ID的怪兽贴图不同
		switch(zhuangtai)  //通过怪兽的状态来改变怪兽的贴图
		{
		case 0:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/痩刺客/站立/左.gif"),x,y,this);break;
		case 1:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/痩刺客/移动/左.gif"),x,y,this);break;
		case 2:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/痩刺客/被攻击/左.png"),x,y,this);break;
		case 3:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/Night_grave_graphic.png"),x,y,this);break;
		}else {
			switch (zhuangtai) {
			case 0:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/刻印石头人/站立/左.gif"),x,y,this);break;
			case 1:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/刻印石头人/移动/左.gif"),x,y,this);break;
			case 2:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/刻印石头人/被攻击/左.png"),x,y,this);break;
			case 3:g.drawImage(Toolkit.getDefaultToolkit().getImage("img/Night_grave_graphic.png"),x,y,this);break;
			}
		
		}
	
	}
	//怪兽被攻击，修改状态
	public synchronized void BeAttack() {
		setzhuangtai(2); //收到攻击状态
			repaint();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			setBeAtt(false);
			setzhuangtai(1);//正常状态
		}
	
	@Override
	public synchronized void run() {
		while(x > 0 && aLive) {
			setzhuangtai(1);
			repaint();
			if(beAtt)  //被攻击停止前进
				BeAttack();
			x = x - 1;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(x <= 300)
			System.out.println("游戏结束");
		// TODO Auto-generated method stub
	}	
}

