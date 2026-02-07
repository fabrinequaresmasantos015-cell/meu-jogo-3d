import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main extends JPanel implements Runnable {
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    private BufferedImage screen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

    public Main() {
        JFrame frame = new JFrame("Voxel Clash 3D - Retro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.setSize(1280, 720);
        frame.setVisible(true);
        new Thread(this).start();
    }

    public void run() {
        while (true) {
            render();
            repaint();
            try { Thread.sleep(16); } catch (Exception e) {}
        }
    }

    private void render() {
        Graphics2D g = (Graphics2D) screen.getGraphics();
        // Nuvens Frufru Magenta
        g.setColor(new Color(255, 0, 255)); 
        g.fillRect(0, 0, WIDTH, HEIGHT / 2);
        // Chão Amarelo
        g.setColor(new Color(255, 255, 0));
        g.fillRect(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
        // Árvores Ciano
        g.setColor(new Color(0, 255, 255));
        for(int i = 0; i < 4; i++) g.fillRect(80 + (i * 150), 140, 40, 60);
        // Lutador (Ciano) e Monstro Inflável (Magenta)
        g.setColor(Color.CYAN); g.fillOval(100, 250, 40, 40);
        g.setColor(Color.MAGENTA); g.fillOval(450, 220, 90, 90);
        g.dispose();
    }

    protected void paintComponent(Graphics g) {
        g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) { new Main(); }
}