/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import java.awt.Graphics2D;
import java.awt.Rectangle;


/**
 *
 * @author Aldrin
 */
public class Ball {
    
        private static final int DIAMETER = 30;
        int x = 225;
	int y = 250;
	int xa = 0;
	int ya = 1;
        int score1 = 0;
        int score2 = 0;
	private Pong pong;

	public Ball(Pong pong) {
		this.pong = pong;
	}
        
        public Ball(Pong pong, int ya, int y, int score1, int score2) {
		this.pong = pong;
                this.ya = ya;
                this.y = y;
                this.score1 = score1;
                this.score2 = score2;
	}
        

	void move() {
		if (x + xa < 0)
                { 
			xa = pong.speed;
                }
                else if (x + xa > pong.getWidth() - DIAMETER)
                {
			xa = -pong.speed;
                }
                else if (y + ya < 0)
                {
                        score1++;
			pong.racquet1.setScore(score1);
                        if( pong.racquet1.getScore() == 3)
                        {
                            pong.repaint();
                            pong.gameOver();
                            pong.racquet1.setScore(0);
                            pong.racquet2.setScore(0);
                            pong.racquet1.resetPos();
                            pong.racquet2.resetPos();
                            
                        }
                        
                        pong.ball = new Ball(pong, -pong.speed, 450, score1, score2);
                        pong.speed = 1;
                        
                        
                }
                else if (y + ya > pong.getHeight() - DIAMETER)
                {
                        score2++;
			pong.racquet2.setScore(score2);
                        if( pong.racquet2.getScore() == 3)
                        {
                           pong.repaint();
                           pong.gameOver();
                           pong.racquet1.setScore(0);
                           pong.racquet2.setScore(0);
                           
                            pong.racquet1.resetPos();
                            pong.racquet2.resetPos();
                        }
                         pong.ball = new Ball(pong, pong.speed, 30, score1, score2);
                         pong.speed = 1;
                }
                else if(collision1())
                {
                        xa = - pong.speed;
			ya = -pong.speed;
			y = pong.racquet1.getTopY() - DIAMETER;
                        if(pong.speed < 5){
                            pong.speed++;
                        }
                     
		}
                else if(collision2()){
                    
                    xa = pong.speed;
                    ya = pong.speed;
                    y = pong.racquet2.getTopY() + DIAMETER;
                     if(pong.speed < 5){
                            pong.speed++;
                        }
                }
                
           

		x = x + xa;
		y = y + ya;
	}

	private boolean collision1() {
		return pong.racquet1.getBounds().intersects(getBounds());
	}
        
        private boolean collision2() {
		return pong.racquet2.getBounds().intersects(getBounds());
	}
        
        

	public void paint(Graphics2D g) {
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
    
}
