package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FirstJFrame extends JFrame{
	private JButton human1, human2;
	private JButton RandomAI1, RandomAI2;
	private JButton DumbAI1,DumbAI2;
	private JButton SmartAI1, SmartAI2;
	private JButton Go;
	private String player1;
	private String player2;
	private boolean GoClicked;
	
	private class player1human implements ActionListener{
		public void actionPerformed(ActionEvent e){
			RandomAI1.setEnabled(false);
			DumbAI1.setEnabled(false);
			SmartAI1.setEnabled(false);
			player1 = "human";
		}
	}

	private class player2human implements ActionListener{
		public void actionPerformed(ActionEvent e){
			RandomAI2.setEnabled(false);
			DumbAI2.setEnabled(false);
			SmartAI2.setEnabled(false);
			player2 = "human";
		}
	}
	
	private class player1Dumb implements ActionListener{
		public void actionPerformed(ActionEvent e){
			RandomAI1.setEnabled(false);
			human1.setEnabled(false);
			SmartAI1.setEnabled(false);
			player1 = "DumbAI";
		}
	}
	
	private class player2Dumb implements ActionListener{
		public void actionPerformed(ActionEvent e){
			RandomAI2.setEnabled(false);
			human2.setEnabled(false);
			SmartAI2.setEnabled(false);
			player2 = "DumbAI";
		}
	}
	
	private class player1Smart implements ActionListener{
		public void actionPerformed(ActionEvent e){
			RandomAI1.setEnabled(false);
			human1.setEnabled(false);
			DumbAI1.setEnabled(false);
			player1 = "SmartAI";
		}
	}
	
	private class player2Smart implements ActionListener{
		public void actionPerformed(ActionEvent e){
			RandomAI2.setEnabled(false);
			human2.setEnabled(false);
			DumbAI2.setEnabled(false);
			player2 = "SmartAI";
		}
	}
	
	private class player1Random implements ActionListener{
		public void actionPerformed(ActionEvent e){
			DumbAI1.setEnabled(false);
			human1.setEnabled(false);
			SmartAI1.setEnabled(false);
			player1 = "RandomAI";
		}
	}
	
	private class player2Random implements ActionListener{
		public void actionPerformed(ActionEvent e){
			DumbAI2.setEnabled(false);
			human2.setEnabled(false);
			SmartAI2.setEnabled(false);
			player2 = "RandomAI";
		}
	}
	
	public String getPlayer1() {
		return player1;
	}
	
	public String getPlayer2() {
		return player2;
	}
	
	public boolean getGoClicked() {
		return GoClicked;
	}
	
	private class go implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (player1 != null && player2 != null) {
				GoClicked = true;
				setVisible(false);
			}
		}
	}
	
	public FirstJFrame(){
		
		super("Tic-Tac-Toe");
		this.human1 = new JButton ("Human");
		this.human2 = new JButton ("Human");
		this.DumbAI1 = new JButton("Dumb AI");
		this.DumbAI2 = new JButton("Dumb AI");
		this.RandomAI1 = new JButton("Random AI");
		this.RandomAI2 = new JButton("Random AI");
		this.SmartAI1 = new JButton("Smart AI");
		this.SmartAI2 = new JButton("Smart AI");
		this.Go = new JButton("Go");
		
		Box players = new Box(BoxLayout.X_AXIS);
		add(players, BorderLayout.CENTER);
		Box player1 = new Box(BoxLayout.Y_AXIS);
		Box player2 = new Box(BoxLayout.Y_AXIS);
		players.add(player1);
		players.add(player2);
		
		JLabel p1 = new JLabel("Player1 plays as:");
		player1.add(p1);
		player1.add(this.human1);
		player1.add(this.DumbAI1);
		player1.add(this.RandomAI1);
		player1.add(this.SmartAI1);
		
		JLabel p2 = new JLabel("Player2 plays as:");
		player2.add(p2);
		player2.add(this.human2);
		player2.add(this.DumbAI2);
		player2.add(this.RandomAI2);
		player2.add(this.SmartAI2);
		add(this.Go, BorderLayout.SOUTH);
		
		human1.addActionListener(new player1human());
		human2.addActionListener(new player2human());
		DumbAI2.addActionListener(new player2Dumb());
		DumbAI1.addActionListener(new player1Dumb());
		SmartAI1.addActionListener(new player1Smart());
		SmartAI2.addActionListener(new player2Smart());
		RandomAI1.addActionListener(new player1Random());
		RandomAI2.addActionListener(new player2Random());
		Go.addActionListener(new go());
		
		pack();
	}

}