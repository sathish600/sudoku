package com.zero.sudoku;

import java.util.Scanner;

import com.zero.sudoku.model.Cell;
import com.zero.sudoku.model.Color;

public class GameApp {

	public static void main(String[] args) {
		
		Game game = new Game();
		game.initialize();
		
		game.printMatrix(true);

        Scanner scanner = new Scanner(System.in);
        
        while (true) {

        	System.out.print("Enter Choices \n 1 - Insert \n 2 - Search \n 3 - Quit \n : ");
        	
        	String input = scanner.nextLine();
        	
            if("1".equals(input)) {
            	System.out.print("Enter input of the format R:C:V for insert, where R = row, C = column, V = value : ");
            	input = scanner.nextLine();

	            String[] values = input.split(":");
	            
	    		game.tryAndInsert(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Color.WHITE);
	
	    		game.printMatrix(true);
	            System.out.println("-----------\n");
	            System.out.println("-----------\n");
	            
            }else if("2".equals(input)) {
            	System.out.print("Enter input of the format R:C to search, where R = row, C = column : ");
            	input = scanner.nextLine();

	            String[] values = input.split(":");
	            
	    		Cell cell = game.getCell(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
	            System.out.println("-----------\n");
	            System.out.printf("Cell at %s, %s is %s", values[0], values[1], cell.toString());
	            System.out.println("\n-----------\n");
	            
            } else if ("3".equals(input)) {
                System.out.println("Exit!");
                break;
            }
        }

        scanner.close();
	}
}
