import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;

//This drives the game
//Some of this code I found from the examples in class and some hints online

public class Play extends JFrame{
	
	GameBoard gameboard;
	int seed;
	
	public boolean tournamentMode;
	
	JMenuItem tournamentItem;
	JMenuItem undo;
	JMenuItem reset;
	JMenuItem load;
	JMenuItem removed;
	JMenuItem redo;
	Timer timer;
	
	public Play() {
		tournamentMode = false;
		seed = (int) System.currentTimeMillis() % 100000;
        setSize(Config.WINDOW_WIDTH,Config.WINDOW_HEIGHT);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Mah Jong (Game #: " + seed + ")");
        
        gameboard = new GameBoard(this, seed, tournamentMode);
        add(gameboard, BorderLayout.CENTER);
        
        //create a game Menu
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);

        //Main item
        JMenu mahjong = new JMenu("Game");
        menu.add(mahjong);
        
        //New Game item
        JMenuItem newGame = new JMenuItem("New Game");
        
        newGame.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
        	  
            int confirm = JOptionPane.showConfirmDialog(null,
                "Start a new game?",
                "Message",
                JOptionPane.YES_NO_OPTION);
            if(confirm == 0){
                newGame();
            }
          }
        });
        
        mahjong.add(newGame);
        

        //Load Game
        load = new JMenuItem("Load Game");
        
        load.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
        	  
            String value = JOptionPane.showInputDialog(null, "Please enter a game number: ");
            
            if (!value.isEmpty() && checkInt(value)){
                loadGame(value);
            }
          }
        });
        
        mahjong.add(load);
        
        
        //Reset Item
        reset = new JMenuItem("Reset Game");
        
        reset.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
        	  
            int confirm = JOptionPane.showConfirmDialog(null,
                "Reset game (begin current game over)?",
                "Message",
                JOptionPane.YES_NO_OPTION);
            if(confirm == 0){
                //OK
                undoAll();
            }
          }
        });
        
        mahjong.add(reset);
        
        mahjong.addSeparator();
        
        //Exit item
        JMenuItem exit = new JMenuItem("Exit");
        
        exit.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
        	  
            int confirm = JOptionPane.showConfirmDialog(null,
                "Exit (quit current game)?",
                "Message",
                JOptionPane.YES_NO_OPTION);
            if(confirm == 0){
                //OK
                System.exit(0);
            }
          }
        });
        
        mahjong.add(exit);
        
        //Play
        JMenu playgame = new JMenu("Play");
        menu.add(playgame);

        //Undo Item
        undo = new JMenuItem("Undo");
        
        undo.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
        	  
            int confirm = JOptionPane.showConfirmDialog(null,
                "Undo previous action?",
                "Message",
                JOptionPane.YES_NO_OPTION);
            if(confirm == 0){
                //OK
                undo();
            }
          }
        });
        
        playgame.add(undo);
        
        //Redo Item
        redo = new JMenuItem("Redo");
        
        redo.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
        	  
            int confirm = JOptionPane.showConfirmDialog(null,
                "Redo previous action?",
                "Message",
                JOptionPane.YES_NO_OPTION);
            if(confirm == 0){
                //OK
                redo();
            }
          }
        });
        
        playgame.add(redo);

        //Display removed tiles
        removed = new JMenuItem("Removed Tiles");
        
        removed.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
            showRemovedTiles();
          }
        });
        
        playgame.add(removed);
        
      //Tournament
        JMenu tournamentMenu = new JMenu("Tournament");
        menu.add(tournamentMenu);

        tournamentItem = new JMenuItem("Start Tourament");
        
        tournamentItem.addActionListener(new ActionListener() {
        	
          public void actionPerformed(ActionEvent evt) {
            toggleTournamentMode();
          }
        });
        
        tournamentMenu.add(tournamentItem);
        
        //Play Win Screen
        if(Config.DEBUG){
            //Play
            JMenu debug = new JMenu("Debug");
            menu.add(debug);

            JMenuItem win = new JMenuItem("Win");
            
            win.addActionListener(new ActionListener() {
            	
              public void actionPerformed(ActionEvent evt) {
                gameboard.gameWon();
              }
            });
            
            debug.add(win);
        }

        setVisible(true);
	}//end Main
	
	//this snippet of code allows me to run a tournament
	protected void toggleTournamentMode() {
		tournamentMode = !tournamentMode;
		
        if(tournamentMode){
            tournamentItem.setText("End Tournament");
            newGame();
            setTitle("Mah Jong - Tournament Mode");
            undo.setEnabled(false);
            reset.setEnabled(false);
            load.setEnabled(false);
            removed.setEnabled(false);
            redo.setEnabled(false);
            timer = new Timer();
            add(timer, BorderLayout.NORTH);
        } else {
            tournamentItem.setText("Start Tournament");
            newGame();
            undo.setEnabled(true);
            reset.setEnabled(true);
            load.setEnabled(true);
            removed.setEnabled(true);
            redo.setEnabled(true);
            remove(timer);
        }
	}

	
	private boolean checkInt(String value) {
		try{
	           Integer.parseInt(value);
	           return true;
        }
        catch( Exception e ) {
           return false;
        }
	}

	protected void loadGame(String gameSeed) {
		remove(gameboard);
		
        gameboard = new GameBoard(this, Integer.parseInt(gameSeed), tournamentMode);
        add(gameboard);
        repaint();
        
        seed = Integer.parseInt(gameSeed);
        setTitle("Mah Jong (Game #: " + seed + ")");
	}

	protected void undoAll() {
		 while(gameboard.getUndo()){
	            undo();
		 }	
	}

	protected void undo() {
		if(gameboard.getUndo()){
            gameboard.undo();
		}		
	}

	protected void redo() {
		if(gameboard.getRedo()){
            gameboard.redo();
		}
	}
	
	 public void gameTitle(String title){
	        setTitle(title);
	}

	private void showRemovedTiles() {
		JPanel innerContainer = new JPanel();
        innerContainer.setLayout(new GridLayout(0,4,2,2));

        ArrayList<Tile> tiles = gameboard.getTilesRemoved();
        for(Tile t : tiles){
            t.setShadow(new ArrayList());
            innerContainer.add(t);
        }

        int height = ((tiles.size() / 4) + 1) * 100;
        int width = 380;
        innerContainer.setSize(width,height);
        innerContainer.setPreferredSize(new Dimension(width,height));
        
        System.out.println("Inner size: " + innerContainer.getSize());
        
        JScrollPane scroll = new JScrollPane(innerContainer);
        scroll.setLayout( new ScrollPaneLayout());
        
        //this sets up the size for the bin 
        scroll.setPreferredSize(new Dimension(400,410));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        JDialog popUp = new JDialog(this, true);
        popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        popUp.setPreferredSize(new Dimension(400,410));
        popUp.setBounds(400,100,410,400);
        popUp.setResizable(false);
        popUp.setLayout(new BorderLayout());
        
        popUp.add(scroll, BorderLayout.CENTER);

        popUp.setVisible(true);
		
	}

	private void newGame() {
		remove(gameboard);
        seed = (int) System.currentTimeMillis() % 100000;
        gameboard = new GameBoard(this, seed, tournamentMode);
        add(gameboard, BorderLayout.CENTER);
        setTitle("Mah Jong (Game #: " + seed + ")");
        repaint();
	}

	public static void main(String[] args) {
		new Play();
	}
}
