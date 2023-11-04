package uniandes.dpoo.taller4.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import uniandes.dpoo.taller4.modelo.Tablero;

public class TableroPanel extends JPanel {
	
	
    private static final long serialVersionUID = 1L;
	private static Tablero tablero;
	private static int tamanoActual;

    public TableroPanel(Tablero tablero) {
        TableroPanel.tablero = tablero;
        boolean[][] tamano = tablero.darTablero();
        int anchoAltoCasilla = tamano.length;
        int anchoCasilla = 50; // Ajusta el ancho de las casillas según tus necesidades
        setPreferredSize(new Dimension(anchoCasilla * anchoAltoCasilla, anchoCasilla * anchoAltoCasilla));
        
        boolean[][] board = tablero.darTablero();
        int size = board.length; // Obtén el tamaño del tablero
        TableroPanel.tamanoActual = size;       
            
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                hallarPosicionMouse(x, y);
                notificarRepaint();
            }
        });
    }
       

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boolean[][] board = tablero.darTablero();
        int size = board.length; // Obtén el tamaño del tablero
        TableroPanel.tamanoActual = size;

        int anchoCasilla = getWidth() / size; // Calcula el ancho de cada casilla
        int altoCasilla = getHeight() / size; // Calcula el alto de cada casilla

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int buttonSize = anchoCasilla;
        int padding = 4;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean isLit = board[i][j];

                int x = i * anchoCasilla;
                int y = j * altoCasilla;

                // Establecer el color de fondo del botón (amarillo claro para encendido, gris para apagado)
                Color buttonColor = isLit ? new Color(255, 255, 0) : new Color(150, 150, 150);

                // Dibujar el botón con bordes suaves y sombra
                g2d.setColor(buttonColor);
                g2d.fillRoundRect(x, y, buttonSize, buttonSize, 10, 10);

                // Agregar sombra difuminada al botón
                for (int k = 0; k < padding; k++) {
                    int alpha = 100 - (k * 20);
                    g2d.setColor(new Color(0, 0, 0, alpha));
                    g2d.drawRoundRect(x + k, y + k, buttonSize - (k * 2), buttonSize - (k * 2), 10, 10);
                }

            }
        }
    }



	private void hallarPosicionMouse(int x, int y) {
		int rows = TableroPanel.tamanoActual;
		int cols = TableroPanel.tamanoActual;

		int buttonWidth;
	
			buttonWidth = getWidth() / cols;
			int buttonHeight = getHeight() / rows;

			int col = x / buttonWidth;
			int row = y / buttonHeight;

	
			    System.out.println("Fila: " + row + ", Columna: " + col);
			 TableroPanel.tablero.jugar(col, row);
			 repaint();
	}
	
	public void notificarRepaint() {
        // Este método notificará a la clase principal cuando se repinte
        firePropertyChange("repaint", false, true);
    }



	


	







//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        boolean[][] board = tablero.darTablero();
//        int size = board.length; // Obtén el tamaño del tablero
//
//        int anchoCasilla = getWidth() / size; // Calcula el ancho de cada casilla
//        int altoCasilla = getHeight() / size; // Calcula el alto de cada casilla
//
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//                if (board[i][j]) {
//                    g.setColor(Color.WHITE); // Color cuando la casilla está encendida
//                } else {
//                    g.setColor(Color.YELLOW); // Color cuando la casilla está apagada
//                }
//                g.fillRect(i * anchoCasilla, j * altoCasilla, anchoCasilla, altoCasilla);
//                
//             // Dibuja el borde de la casilla
//                g.setColor(Color.BLACK); // Color del borde
//                g.drawRect(i * anchoCasilla, j * altoCasilla, anchoCasilla, altoCasilla);
//            }
//        }
//    }
    
    

}
