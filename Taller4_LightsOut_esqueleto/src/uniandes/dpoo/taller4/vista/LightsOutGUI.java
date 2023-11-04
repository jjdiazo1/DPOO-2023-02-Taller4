package uniandes.dpoo.taller4.vista;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class LightsOutGUI {
    private JFrame frame;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private JLabel statusLabel;
    private Tablero tablero;
    private Top10 top10;
	private static int tamanoActual = 4;
	private static int dificultadActual = 3;
	static private boolean juegoGanado = false;

    public LightsOutGUI() {
        frame = new JFrame("Lights Out");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        tablero = new Tablero(tamanoActual);
        top10 = new Top10();
        
        //FlatLightLaf.install(); Si se quiere se puede usar para cambiar la apariencia

        /**
    	 * PANEL TAMANOS
    	 */
        
        JPanel topPanel = new JPanel();
        String[] tamanos = { "4x4", "5x5", "6x6", "7x7", "8x8", "9x9", "10x10" };
        JComboBox<String> sizeComboBox = new JComboBox<>(tamanos);
        JLabel sizeLabel = new JLabel("Tamaño del Tablero:");
        topPanel.add(sizeLabel);
        topPanel.add(sizeComboBox);
        sizeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedSize = (String) sizeComboBox.getSelectedItem();
                int newSize = 4; // Tamaño predeterminado
                if ("4x4".equals(selectedSize)) {
                    newSize = 4;
                } else if ("5x5".equals(selectedSize)) {
                    newSize = 5;
                } else if ("6x6".equals(selectedSize)) {
                    newSize = 6;
                } else if ("7x7".equals(selectedSize)) {
                    newSize = 7;
                } else if ("8x8".equals(selectedSize)) {
                    newSize = 8;
                } else if ("9x9".equals(selectedSize)) {
                    newSize = 9;
                } else if ("10x10".equals(selectedSize)) {
                    newSize = 10;
                }
                tamanoActual = newSize;
                setTamano(newSize); // Cambia el tamaño del tablero
                
                
            }
        });
        
        /**
    	 * PANEL DIFICULTADES
    	 */
        String[] dificultades = { "Fácil", "Medio", "Difícil" };
        JComboBox<String> difficultyComboBox = new JComboBox<>(dificultades);
        JLabel difficultyLabel = new JLabel("Dificultad:");
        topPanel.add(difficultyLabel);
        topPanel.add(difficultyComboBox);
        difficultyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                dificultadActual = 3;
                if ("Medio".equals(selectedDifficulty)) {
                	dificultadActual = 6;
                } else if ("Difícil".equals(selectedDifficulty)) {
                	dificultadActual = 30;
                }
                setDificultad(dificultadActual);
            }
        });
   
        frame.add(topPanel, BorderLayout.NORTH);
        
        /**
    	 * TABLERO
    	 */
        
        gamePanel = new TableroPanel(tablero);
        gamePanel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("repaint".equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                    // Realiza acciones cuando se repinta el panel
                    esJuegoGanado();
				}}});
				
      
        frame.add(gamePanel, BorderLayout.CENTER);
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        tablero.desordenar(dificultadActual);
        
        
        
        
        /**
    	 * BOTON TOP10
    	 */
        
        top10.cargarRecords(new File("top10.txt")); // Carga los registros existentes
        
        JButton top10Button = new JButton("Top 10");
        menuPanel.add(top10Button);

        top10Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> top10Data = obtenerTop10Data(); 

                // Crear un JDialog para mostrar el Top10
                JDialog top10Dialog = new JDialog(frame, "Top 10", true);
                top10Dialog.setSize(400, 400);

                DefaultListModel<String> listModel = new DefaultListModel<>();
                for (int i = 0; i < top10Data.size(); i++) {
                    String entry = top10Data.get(i);
                    listModel.addElement(entry);
                }

                JList<String> top10List = new JList<>(listModel);
                top10List.setCellRenderer(new ListCellRenderer<String>() {
                    @Override
                    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
                        Component c = new DefaultListCellRenderer().getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                        // Cambia el color del fondo según la posición en el Top10
                        if (index == 0) {
                            c.setBackground(new Color(0x1DA1F2)); 
                            c.setForeground(Color.WHITE);
                        } else if (index == 1) {
                            c.setBackground(new Color(0x1877F2));
                            c.setForeground(Color.WHITE);
                        } else if (index == 2) {
                            c.setBackground(new Color(0x006CBB)); 
                            c.setForeground(Color.WHITE);
                        } else {
                            c.setBackground(Color.WHITE);
                        }

                        
                        // Usa una fuente más grande y en negrita
                        c.setFont(c.getFont().deriveFont(16f).deriveFont(Font.BOLD));
                        return c;
                    }
                });
                // Agregar la lista a un JScrollPane para barras de desplazamiento
                JScrollPane scrollPane = new JScrollPane(top10List);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                top10Dialog.add(scrollPane);

                top10Dialog.setLocationRelativeTo(frame);
                top10Dialog.setVisible(true);
                

            }
        });
        
        /**
    	 * BOTON NUEVO JUEGO
    	 */
        JButton newGameButton = new JButton("Nuevo Juego");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nuevoJuego();
                
            }
        });
        
        /**
    	 * BOTON REINICIAR
    	 */
        JButton restartButton = new JButton("Reiniciar");
        
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego(tamanoActual); // Llama al método para reiniciar el tablero
            }
        });
        
        /**
    	 * BOTON REINICIAR TOP 10
    	 */
        JButton reiniciarTop10Button = new JButton("Reiniciar Top 10");
        reiniciarTop10Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarTop10();
                
            }
        });

        menuPanel.add(newGameButton);
        menuPanel.add(restartButton);
        menuPanel.add(top10Button);
        menuPanel.add(reiniciarTop10Button);
        frame.add(menuPanel, BorderLayout.EAST);

        statusLabel = new JLabel("Jugadas: " + tablero.darJugadas() + " - Jugador: Nombre");
        frame.add(statusLabel, BorderLayout.SOUTH);
    }

    private void addWindowListener(MouseAdapter mouseAdapter) {
		// TODO Auto-generated method stub
		//esto decia en el pdf que habia que ponerlo.
	}

    public void esJuegoGanado() {     
        // Verifica si el tablero está completamente iluminado para ver si gano
	    if (!juegoGanado && tablero.tableroIluminado()) {
	        String nombreGanador = JOptionPane.showInputDialog("¡Ganaste! Ingresa tu nombre:");
	        if (nombreGanador != null && !nombreGanador.isEmpty()) {
	            int puntajeGanador = tablero.calcularPuntaje();
	            if (puntajeGanador > 0 && top10.esTop10(puntajeGanador)) {
	            	juegoGanado = true;
	            	
	                top10.cargarRecords(new File("top10.txt")); // Carga los registros existentes
	                top10.agregarRegistro(nombreGanador, puntajeGanador);
	                try {
	                    top10.salvarRecords(new File("top10.txt")); // Guarda los registros actualizados
	                } catch (Exception e) {
	                    // Maneja cualquier excepción que pueda ocurrir al guardar
	                    e.printStackTrace();
	                }
	                reiniciarJuego(tamanoActual);
	             }
	          }
	        statusLabel.setText("Jugadas: " + tablero.darJugadas() + " - Jugador: Nombre");
	     }
	 }
    			
    

    private void setTamano(int size) {
    	    tablero.reiniciar();
    	    tablero = new Tablero(size); 
    	    gamePanel = new TableroPanel(tablero);
            gamePanel.addPropertyChangeListener(new PropertyChangeListener() {
    			@Override
    			public void propertyChange(PropertyChangeEvent evt) {
    				// TODO Auto-generated method stub
    				if ("repaint".equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                        // Realiza acciones cuando se repinta el panel
                        esJuegoGanado();
    				}}});   	  
            tablero.desordenar(dificultadActual);
            frame.add(gamePanel, BorderLayout.CENTER);
    	    frame.revalidate();
    	
    }
    
    private void setDificultad(int dificultad) {
    	tablero.reiniciar();
    	
        tablero = new Tablero(tamanoActual); 
        tablero.desordenar(dificultad);
        gamePanel = new TableroPanel(tablero);
        gamePanel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("repaint".equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                    // Realiza acciones cuando se repinta el panel
                    esJuegoGanado();
				}}});
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.revalidate();
    }
    
    private void reiniciarJuego(int size) {
    	
    	tablero.reiniciar();
        frame.remove(gamePanel);
        tablero = new Tablero(size);
        gamePanel = new TableroPanel(tablero);
        gamePanel.addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// TODO Auto-generated method stub
				if ("repaint".equals(evt.getPropertyName()) && (Boolean) evt.getNewValue()) {
                    // Realiza acciones cuando se repinta el panel
                    esJuegoGanado();
				}}});
        frame.add(gamePanel, BorderLayout.CENTER);
        tablero.desordenar(dificultadActual);
        frame.revalidate();
        statusLabel.setText("Jugadas: " + tablero.darJugadas() + " - Jugador: Nombre");
     
    }
    
    private void nuevoJuego() {
    	
    	tablero = new Tablero(4); 
    	tamanoActual = 4;
    	setDificultad(4);
    	tablero.desordenar(dificultadActual);
        frame.revalidate();
        juegoGanado = false;
        statusLabel.setText("Jugadas: " + tablero.darJugadas() + " - Jugador: Nombre");
    }
    
private void reiniciarTop10() {
	
        File archivoTop10 = new File("top10.txt");
        if (archivoTop10.exists()) {
            archivoTop10.delete();
        }
        this.top10 = new Top10();
        frame.revalidate();
        tablero.desordenar(dificultadActual);
        juegoGanado = false;
        statusLabel.setText("Jugadas: " + tablero.darJugadas() + " - Jugador: Nombre");
        
    }
       //todo no se ve la vaina del top 10

    
    private ArrayList<String> obtenerTop10Data() {
    	
    	ArrayList<String> top10Data = new ArrayList<>();
        Collection<RegistroTop10> registros = null;
		try {
			registros = top10.darRegistros();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int position = 1;
        for (RegistroTop10 registro : registros) {
            String nombre = registro.darNombre();
            int puntos = registro.darPuntos();
            String positionStr = String.format("%d. ", position);

            // Establecer el color de fondo y la fuente de acuerdo a la posición
            String formattedEntry;
            if (position <= 3) {
                formattedEntry = "<html><b><font color='green'>" + positionStr + nombre + ": " + puntos + "</font></b></html>";
            } else {
                formattedEntry = "<html>" + positionStr + nombre + ": " + puntos + "</html>";
            }
            top10Data.add(formattedEntry);
            
            position++;
        }

        return top10Data;
    }
    
    public void start() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LightsOutGUI lightsOutApp = new LightsOutGUI();
                lightsOutApp.start();
            }
        });
    }
}
