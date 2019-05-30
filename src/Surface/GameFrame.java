package Surface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;

import Role.Monster;
import Role.Pea;
import Role.Player;

import javax.crypto.NullCipher;
import javax.security.auth.x500.X500Principal;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.swing.JLabel;
import java.awt.Font;

//窗口
public class GameFrame {
	/*
	 * 1、人物线程
	 * 2、攻击线程
	 * 3、被攻击线程
	 * 4、子弹发射线程
	 */
	private boolean over = false; //游戏结束标志
	private JFrame frame;
	private Random random = new Random();
	private int attacking=0;
	// 创建一个容量为12的线程池
	ExecutorService executorService = Executors.newFixedThreadPool(12);  
	/**人物方向状态
	 * 0=左
	 * 1=右
	 * */
	private int Direction; 
	//当前控制人物 1为战士 2为射手
	private int role = 2;
	//记住数组在JAVA中是对象！！！
	//怪兽数组
	Monster[] monster = new Monster[10];
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {  //刷新图像
			public void run() {
				try {
					GameFrame window = new GameFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameFrame() {
		initialize();
	}

	Player player = new Player();	
	Pea pea = new Pea();
	JLabel label = new JLabel("\u6E38\u620F\u7ED3\u675F");
	
	class Attack implements Runnable	//攻击的线程
	 {
		 @Override
		public void run() {
			 if(attacking == 0)//判定人物朝向
			 {
				 attacking = 1; //保证只有一个攻击线程
				 for (int i = 0; i < monster.length; i++) {  //所有怪兽判断是否被攻击
					 if(Direction == 1)
					{
						 if(monster[i].getY() <= player.getY()+10 &&
								 monster[i].getY() >= player.getY()-10 &&	 
								 (monster[i].getX() -8) <= (player.getX()+100) && 
								 monster[i].getX() > player.getX() )   //通过怪兽的位置判断是否被攻击
						 	{
								if(monster[i].aLive)  //被攻击
						 		{
							 		monster[i].setBeAtt(true); //掉血
							 		monster[i].setzhuangtai(2); //新的状态
							 		monster[i].beAtt(player.getSNH()); 
						 		}
								else {
									monster[i].setzhuangtai(3);
									boolean notOver = true; 
									for(int j = 0 ; j<monster.length ;j++)
										{
										if(monster[j].aLive )
											notOver = false; 
										}
										if(notOver)
											player.add(label);  //全部死亡游戏结束 
								}
								}
					}
				}
				 try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 	// TODO Auto-generated method stub
				 if(Direction == 1)
					 player.setStatus(2); //向右
					else 
					 player.setStatus(3); //向右
				 attacking = 0;
			 }
			 
		 }
	 }
	class PeaAtt implements Runnable		//豌豆攻击的线程
	 {
		 @Override
		public synchronized void run() {
			 while (true) {
				 for (int i = 0; i < monster.length; i++) { //每个怪兽判断是否被攻击
					 if(monster[i].getY() <= pea.getY() + 10 &&
								 monster[i].getY() >= pea.getY()-10 &&	 
								 (monster[i].getX() -8) <= (pea.getMx() - monster[i].getX() + 100) )
						 	{
						 		
								if(monster[i].aLive)
						 		{
							 		monster[i].setBeAtt(true);
							 		monster[i].setzhuangtai(2);
							 		monster[i].beAtt(pea.getSNH());
							 		pea.mx = 130;
						 		}
								else 
									monster[i].setzhuangtai(3);
						 	}
					}
				 if (pea.getMx() >= 1000)
					 pea.mx = 130;
				 try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }
		// TODO Auto-generated method stub
			
	 }	
	 }
	
	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() { //设置监听器
			public void keyPressed(KeyEvent e) {
						
			            int code = e.getKeyCode();  
			            switch (code) {  
			            case KeyEvent.VK_UP:  
			                if(role == 1)
			            	Player.up = true;
			                else 
							Pea.up = true;	
			                break;  
			            case KeyEvent.VK_DOWN:  
			                if(role == 1)
			            	Player.down = true;
			                else 
			                Pea.down = true;
			                break;  
			            case KeyEvent.VK_LEFT:  
			            		Player.left = true;  
			            		player.setStatus(1);  //向左
			            		Direction = 0;
			                break;  
			            case KeyEvent.VK_RIGHT:
			            	Player.right = true;  
			                player.setStatus(0); //向右
			                Direction = 1;
			                break;  
			            case KeyEvent.VK_X:  //人物攻击
			            	executorService.execute(new Thread(new Attack())); //添加攻击线程
							if(Direction == 1)
							 player.setStatus(4); //向右
							else 
							 player.setStatus(5); //向右
			            	break;
			            case KeyEvent.VK_Z:
			            	if(role == 1)
			            		role = 2;
			            	else 
								role = 1;
			            default:  
			                break;  
			            }  
			            if(role == 1)
			            player.move();
			            else {
			            	 pea.move();
						}
			        }  
			        //当按键释放  
			        public void keyReleased(KeyEvent e){  
			            int code = e.getKeyCode();  
			            switch (code) {  
			            case KeyEvent.VK_UP:  
			            	Player.up = false;  
			            	Pea.up = false;  
			            	break;  
			            case KeyEvent.VK_DOWN:  
			                Player.down = false;  
			                Pea.down = false;
			                break;  
			            case KeyEvent.VK_LEFT:  
			                Player.left = false; 
			                player.setStatus(3); //向右
			                break;  
			            case KeyEvent.VK_RIGHT:  
			                Player.right = false;  
			                player.setStatus(2); //向右
			                break;  
			            default:  
			                break;  
			            }
			            player.move();
			        }  
		});
		frame.setBounds(400, 100, 1200, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		player.setBounds(50, 320, 1500, 900);
		frame.getContentPane().add(player);
		player.setBackground(null);
		player.setLayout(null);
		player.setOpaque(false); //背景透明 和paint() 有关 不能重写paint() 
		
		
			
		label.setBounds(513, 88, 412, 193);
		label.setFont(new Font("宋体", Font.PLAIN, 96));

		//加入豌豆
		pea.setBounds(400, 100, 1200, 650);
		frame.getContentPane().add(pea);
		pea.setBackground(null);
		pea.setLayout(null);
		pea.setOpaque(false); //背景透明 和paint() 有关 不能重写paint() 
		executorService.execute(new Thread(new Pea()));
	
		//加入怪兽
		for (int i = 0; i < 10; i++) {
			monster[i] = new Monster(random.nextInt(2));
			monster[i].setBounds(monster[i].getX(), monster[i].getY(), 1500, 900);
			frame.getContentPane().add(monster[i]);
			monster[i].setBackground(null);
			monster[i].setLayout(null);
			monster[i].setOpaque(false); //背景透明 和paint() 有关 不能重写paint() 
			executorService.execute(new Thread(monster[i]));
		}
		
		//豌豆射击
		new Thread(new PeaAtt()).start();
		GamePanel panel = new GamePanel();
		panel.setBounds(0, 0, 1500, 900);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		 new Thread(player).start();
		
	}
}
