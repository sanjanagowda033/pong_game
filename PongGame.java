package mini;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PongGame extends JPanel implements KeyListener, Runnable {
    private int ballX = 250, ballY = 150, ballXSpeed = 2, ballYSpeed = 2;
    private int paddle1Y = 100, paddle2Y = 100;
    private int paddleWidth = 10, paddleHeight = 60;
    private int player1Score = 0, player2Score = 0;

    public PongGame() {
        setFocusable(true);
        addKeyListener(this);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, 10, 10);

        g.fillRect(10, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(getWidth() - 20, paddle2Y, paddleWidth, paddleHeight);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player 1: " + player1Score, 50, 20);
        g.drawString("Player 2: " + player2Score, getWidth() - 150, 20);
    }

    public void move() {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        if (ballY <= 0 || ballY >= getHeight() - 10) {
            ballYSpeed = -ballYSpeed;
        }

        if (ballX <= 20 && ballY >= paddle1Y && ballY <= paddle1Y + paddleHeight) {
            ballXSpeed = -ballXSpeed;
        } else if (ballX >= getWidth() - 30 && ballY >= paddle2Y && ballY <= paddle2Y + paddleHeight) {
            ballXSpeed = -ballXSpeed;
        }

        if (ballX < 0) {
            player2Score++;
            resetGame();
        } else if (ballX > getWidth()) {
            player1Score++;
            resetGame();
        }
    }

    public void resetGame() {
        ballX = getWidth() / 2;
        ballY = getHeight() / 2;
        ballXSpeed = -ballXSpeed;
    }

    @Override
    public void run() {
        while (true) {
            move();
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_S && paddle1Y < getHeight() - paddleHeight) {
            paddle1Y += 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= 10;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && paddle2Y < getHeight() - paddleHeight) {
            paddle2Y += 10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        PongGame game = new PongGame();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setVisible(true);
    }
}

public class PongGame {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
