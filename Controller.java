/**
 * This is an example of the basic "Bouncing Ball" animation, making
 * use of the Model-View-Controller design pattern and the Timer and
 * Observer/Observable classes.
 */
package kaleidoscope;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Timer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The Controller sets up the GUI and handles all the controls (buttons,
 * menu items, etc.)
 * 
 * @author David Matuszek
 * @author Josh Taylor
 * @author Ted Fujimoto
 */
@SuppressWarnings("serial")
public class Controller extends JFrame {
    JPanel buttonPanel = new JPanel();
    JButton runButton = new JButton("Run");
    JButton stopButton = new JButton("Stop");
    JLabel ballColorLabel = new JLabel("Ball color: ");
    JComboBox<String> ballColor = new JComboBox<String>(new String[] {"Green", "Red", "Blue"});
    JLabel rectColorLabel = new JLabel("Rectangle color: ");
    JComboBox<String> rectColor = new JComboBox<String>(new String[] {"Green", "Red", "Blue"});
    JLabel triColorLabel = new JLabel("Triangle color: ");
    JComboBox<String> triColor = new JComboBox<String>(new String[] {"Green", "Red", "Blue"});
    JLabel bgColorLabel = new JLabel("Background color: ");
    JComboBox<String> bgColor = new JComboBox<String>(new String[] {"White", "Black", "Gray"});
    JLabel speedLabel = new JLabel("Speed: ");
    JSlider speedSlider = new JSlider(5, 125);
    Timer timer;

    /** The Model is the object that does all the computations. It is
     * completely independent of the Controller and View objects. */
    Model model;
    
    /** The View object displays what is happening in the Model. */
    View view;
    
    public Controller() {
    	super("Kaleidoscope");
    }
    
    /**
     * Runs the bouncing ball program.
     * @param args Ignored.
     */
    public static void main(String[] args) {
        Controller c = new Controller();
        c.init();
        c.display();
    }

    /**
     * Sets up communication between the components.
     */
    private void init() {
        model = new Model();     // The model is independent from the other classes
        view = new View(model);  // The view needs to know what model to look at
        model.addObserver(view); // The model needs to give permission to be observed
    }

    /**
     * Displays the GUI.
     */
    private void display() {
        layOutComponents();
        attachListenersToComponents();
        setSize(600, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Arranges the various components in the GUI.
     */
    private void layOutComponents() {
        setLayout(new BorderLayout());
        buttonPanel.setPreferredSize(new Dimension(100, 100));
        this.add(BorderLayout.SOUTH, buttonPanel);
        buttonPanel.add(runButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(ballColorLabel);
        buttonPanel.add(ballColor);
        buttonPanel.add(rectColorLabel);
        buttonPanel.add(rectColor);
        buttonPanel.add(triColorLabel);
        buttonPanel.add(triColor);
        buttonPanel.add(bgColorLabel);
        buttonPanel.add(bgColor);
        buttonPanel.add(speedLabel);
        speedSlider.setValue(25);
        speedSlider.setMajorTickSpacing(25);
        speedSlider.setMinorTickSpacing(5);
        speedSlider.setPaintTicks(true);
        buttonPanel.add(speedSlider);
        stopButton.setEnabled(false);
        this.add(BorderLayout.CENTER, view);
    }
    
    /**
     * Attaches listeners to the components, and schedules a Timer.
     */
    private void attachListenersToComponents() {
        // The Run button tells the Model to start
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(false);
                stopButton.setEnabled(true);
                model.start();
            }
        });
        // The Stop button tells the Model to pause
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                runButton.setEnabled(true);
                stopButton.setEnabled(false);
                model.pause();
            }
        });
        // When the window is resized, the Model is given the new limits
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent arg0) {
                model.setLimits(view.getWidth(), view.getHeight());
            }
        });
        // When the ball color combobox is changed, update the color
        ballColor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		view.setBallColor(getColor((String) ballColor.getSelectedItem()));
        		view.repaint();
        	}
        });
        // When the rectangle color combobox is changed, update the color
        rectColor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		view.setRectColor(getColor((String) rectColor.getSelectedItem()));
        		view.repaint();
        	}
        });
        // When the triangle color combobox is changed, update the color
        triColor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		view.setTriColor(getColor((String) triColor.getSelectedItem()));
        		view.repaint();
        	}
        });
        // When the background color combobox is changed, update the color
        bgColor.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		view.setBgColor(getColor((String) bgColor.getSelectedItem()));
        		view.repaint();
        	}
        });
        // When the speed slider is changed, update the velocities of the objects
        speedSlider.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		model.changeVelocity(((float) speedSlider.getValue()) / 25);
        	}
        });
    }

    private Color getColor(String colorString) {
    	if (colorString.toLowerCase().equals("green")) {
    		return Color.green;
    	}
    	else if (colorString.toLowerCase().equals("blue")) {
    		return Color.blue;
    	}
    	else if (colorString.toLowerCase().equals("red")) {
    		return Color.red;
    	}
    	else if (colorString.toLowerCase().equals("black")) {
    		return Color.BLACK;
    	}
    	else if (colorString.toLowerCase().equals("gray")) {
    		return Color.gray;
    	}
    	else {
    		return Color.WHITE;
    	}
    }

}