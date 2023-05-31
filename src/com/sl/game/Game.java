package com.sl.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	private int[][] board;
	private int score;
	
	public Game() {
		board = new int[4][4];
		score = 0;
		initBoard();
	}
	
	public void displayMenuOption() {
		System.out.println("Enter 0 to move Left");
		System.out.println("Enter 1 to move Right");
		System.out.println("Enter 2 to move Up");
		System.out.println("Enter 3 to move Down");
		System.out.println("Enter 4 to Restart");
		System.out.println("Enter 5 to Quit");
	}
	
	public void showMenu() {
		int input = 7;
		while(input !=5) {
			System.out.println("Game menu");
			displayMenuOption();
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your input");
			input = sc.nextInt();
			if(input<0 || input>5) {
				System.out.println("Please enter a valid input from the given options");
				displayMenuOption();
			}else {
				if(input==0) {
					moveLeft();
				}else if(input==1) {
					moveRight();
				}else if(input==2) {
					moveUp();
				}else if(input==3) {
					moveDown();
				}else if(input==4) {
					reStart();
					displayBoard();
				}else if(input==5) {
					break;
				}
				addRandomTile();
				displayBoard();
				System.out.println("Score: "+getScore());
				
				if(isGameOver()) {
					System.out.println("Game over");
					break;
				}
			}
		}
	}
	
	public void initBoard() {
		addRandomTile();
		addRandomTile();
	}
	
	private void addRandomTile() {
		//find all empty cells
		List<int[]> emptyCells = new ArrayList<>();
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(board[i][j]==0) {
					emptyCells.add(new int[] {i,j});
				}
			}
		}
		//if there are no empty cells, return
		if(emptyCells.isEmpty()) {
			return;
		}
		//print a random empty cell and add a tile with 2 or 4
		int[] cell = emptyCells.get((int)(Math.random()*emptyCells.size()));
		board[cell[0]][cell[1]] = (Math.random()<0.9) ? 2:4;
	}
	
	public boolean isGameOver() {
		//any cells are empty or not
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(board[i][j]==0) {
					return false;
				}
			}
		}
		//checking for adjecent cells are same or not
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(i<3 && board[i][j] == board[i+1][j]) {
					return false;
				}
				if(j<3 && board[i][j] == board[i][j+1]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void displayBoard() {
		System.out.println("-----------------------------");
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				System.out.print("|");
				
				if(board[i][j]==0) {
					System.out.print(" ");
				}else {
					System.out.printf("%4d", board[i][j]);
				}
				System.out.println("|");
				
			}
			System.out.println("------------------");
		}
	}
	
	public int getScore() {
		return score;
	}
	
	public void reStart() {
		score=0;
		board = new int[4][4];
		initBoard();
	}
	
	public void quit() {
		
	}
	
	public void addScore(int increment) {
		score += increment;
	}
	
	public boolean moveUp() {
		boolean moved = false;
		for(int j=0;j<4;j++) {
			int lastMergedRow = -1;
			for(int i=0;i<4;i++) {
				if(board[i][j] !=0) {
					int k=i;
					while(k>0 && board[k-1][j]==0) {
						board[k-1][j]=board[k][j];
						board[k][j]=0;
						k--;
						moved = true;
					}
					if(k>0 && board[k-1][j]==board[k][j] && lastMergedRow !=k-1) {
						board[k-1][j] *= 2;
						addScore(board[k-1][j]);
						board[k][j] = 0;
						lastMergedRow = k-1;
						moved=true;
					}
				}
			}
		}
		return moved;
	}
	public boolean moveDown() {
		boolean moved = false;
		for(int j=0;j<4;j++) {
			int lastMergedRow = -1;
			for(int i=3;i>=0;i--) {
				if(board[i][j] !=0) {
					int k=i;
					while(k<3 && board[k+1][j]==0) {
						board[k-1][j]=board[k][j];
						board[k][j]=0;
						k++;
						moved = true;
					}
					if(k<3 && board[k+1][j]==board[k][j] && lastMergedRow !=k-1) {
						board[k+1][j] *= 2;
						addScore(board[k+1][j]);
						board[k][j] = 0;
						lastMergedRow = k+1;
						moved=true;
					}
				}
			}
		}
		return moved;
	}
	
	public boolean moveLeft() {
		boolean moved = false;
		for(int i=0;i<4;i++) {
			int lastMergedColumn = -1;
			for(int j=0;j<4;j++) {
				if(board[i][j] !=0) {
					int k=j;
					while(k>0 && board[i][k-1]==0) {
						board[i][k-1]=board[i][k];
						board[i][k]=0;
						k--;
						moved = true;
					}
					if(k>0 && board[i][k-1]==board[i][k] && lastMergedColumn !=k-1) {
						board[i][k-1] *= 2;
						addScore(board[i][k-1]);
						board[i][k] = 0;
						lastMergedColumn = k-1;
						moved=true;
					}
				}
			}
		}
		return moved;
	}
	public boolean moveRight() {
		boolean moved = false;
		for(int i=0;i<4;i++) {
			int lastMergedColumn = -1;
			for(int j=3;j>=0;j--) {
				if(board[i][j] !=0) {
					int k=j;
					while(k<3 && board[i][k-1]==0) {
						board[i][k+1]=board[i][k];
						board[i][k]=0;
						k++;
						moved = true;
					}
					if(k>0 && board[i][k+1]==board[i][k] && lastMergedColumn !=k-1) {
						board[i][k+1] *= 2;
						addScore(board[i][k+1]);
						board[i][k] = 0;
						lastMergedColumn = k+1;
						moved=true;
					}
				}
			}
		}
		return moved;
	}


}
