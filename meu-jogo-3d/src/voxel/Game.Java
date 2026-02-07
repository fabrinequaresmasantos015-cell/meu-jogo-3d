package com.voxel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements Runnable, KeyListener {
    // Resolução Pixelada
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private boolean running = true;
    private double playerX = 0, playerZ = 0;

    public Game() {
        JFrame frame = new JFrame("Voxel Clash 3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setSize(1280, 720); // Janela maior, mas desenho em 360p
        frame.addKeyListener(this);
        frame.setVisible(true);
        new Thread(this).start();
    }

    public void run() {
        while (running) {
            update();
            render();
            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }
    }

    private void update() { /* Lógica de movimento aqui */ }

    private void render() {
        Graphics2D g = (Graphics2D) image.getGraphics();
        
        // Fundo: Nuvens Magenta "Frufru"
        g.setColor(new Color(255, 0, 255)); 
        g.fillRect(0, 0, WIDTH, HEIGHT / 2);
        
        // Chão: Blocos Amarelos (Perspectiva Simples)
        g.setColor(new Color(255, 255, 0));
        g.fillRect(0, HEIGHT / 2, WIDTH, HEIGHT / 2);

        // Árvores Ciano (Representadas por blocos)
        g.setColor(new Color(0, 255, 255));
        for(int i = 0; i < 5; i++) {
            g.fillRect(100 + (i * 120), 150, 40, 80); // Troncos/Folhas Pixeladas
        }

        // PERSONAGENS
        // Lutador (Azul Ciano)
        g.setColor(Color.CYAN);
        g.fillOval(WIDTH/2 - 20, HEIGHT - 100, 40, 60);
        
        // Monstro Inflável (Magenta/Amarelo - Redondo)
        g.setColor(Color.MAGENTA);
        g.fillOval(WIDTH/2 + 100, HEIGHT - 120, 80, 80); // Fisionomia inflável

        g.dispose();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public static void main(String[] args) { new Game(); }

    // Controles (Teclado)
    public void keyPressed(KeyEvent e) { if(e.getKeyCode() == KeyEvent.VK_W) playerZ += 0.1; }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}