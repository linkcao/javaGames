package Surface;

import javax.swing.ImageIcon;

/** 
 * 游戏配置接口 
 * @author yy 
 * 
 */  
public interface gameConfig {  
    //游戏主窗体名字  
    String title = "冒险岛";  
    //游戏主窗体的大小  
    int frameX = 700;  
    int frameY = 700;  
    //游戏面板大小  
    int panelX = 650;  
    int panelY = 650;  
    //游戏素材大小  
    int elesize = 50;  
    //人物大小  
    int playersize = 50;  
    //------------[游戏素材]----------  
    //-----第一层  
    ImageIcon icon0 = new ImageIcon("000空白.png");  
    ImageIcon icon1 = new ImageIcon("001草地.png");  
    ImageIcon icon2 = new ImageIcon("002地砖.png");  
    ImageIcon icon3 = new ImageIcon("003召泽地板副本.png");  
    ImageIcon icon100 = new ImageIcon("100红树.png");  
    ImageIcon icon101 = new ImageIcon("101绿树.png");  
    ImageIcon icon102 = new ImageIcon("102绿竹.png");  
    ImageIcon icon103 = new ImageIcon("103高绿树.png");  
    ImageIcon icon150 = new ImageIcon("150岩浆.png");  
      
      
    //镜头  
//  ImageIcon shadow = new ImageIcon("镜头阴影.png");  
    ImageIcon shadow2 = new ImageIcon("镜头阴影2.png");  
      
      
      
}  
