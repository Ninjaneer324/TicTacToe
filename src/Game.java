/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kakas
 */
import java.util.*;

public class Game {
    private static TicTacToeBoard board;
    private static String aiSymbol;
    private static String playerSymbol;
    private static String playerName;
    private static int fc = 0;
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        board = new TicTacToeBoard();
        System.out.println("Welcome to Tic Tac Toe!!!");
        System.out.println("To start off, what's your first name?");
        playerName = scanner.next();
        System.out.println("Well Hello " + playerName + "!");
        System.out.println("This is just like regular Tic Tac Toe. X goes first and it will be"
                + "randomly decided who goes first");
        System.out.println("Layout of board is below. Type number of space you want to mark in.");
        System.out.println("\nHave fun!!!\n\n");
        String game = "";
        do {
            System.out.println("LayOut: ");
            System.out.println(layout()+"\n\n");
            board.resetBoard();
            int rand = (((int) Math.random()) * 2) + 0;
            playerSymbol = (rand == 0) ? "X":"O";
            aiSymbol = (playerSymbol.equals("X")) ? "O":"X";
            int turn = (playerSymbol.equals("X")) ? 1:0;
            System.out.println(playerName + " is " + playerSymbol + " and Computer is " + aiSymbol);
            do {
               if(turn == 1) {
                   System.out.println(playerName + ", enter valid index on TicTacToe board to mark: ");
                   String i = scanner.next();
                   String nums = "123456789";
                   while(nums.indexOf(i) == -1 ||Integer.parseInt(i) - 1 < 0 || Integer.parseInt(i) - 1 >= 9 || !(board.get(Integer.parseInt(i) -1).equals(" "))) {
                       System.out.println(playerName +", enter valid index on TicTacToe board to mark: ");
                       i = scanner.next();
                   }
                   int index = Integer.parseInt(i) - 1;
                   System.out.println(playerName +" marked " + (index + 1));
                   board.drawXO(playerSymbol, index);
                   System.out.println(board);
                   --turn; 
                   /*
                     //Let's computer play itself
                     int index = miniMax(board, playerSymbol).index;
                     System.out.println(playerName +" marked " + (index + 1));
                     board.drawXO(playerSymbol, index);
                     System.out.println(board);
                   --turn;
                   */
                   
               }
               else {
                   int index = miniMax(board, aiSymbol).index;
                   System.out.println("Computer marked " + (index + 1));
                   board.drawXO(aiSymbol, index);
                   System.out.println(board);
                   ++turn;
               } 
            }while(terminalScore(board) == -11);
            switch(terminalScore(board)) {
                case -10:
                    System.out.println("You Win! Congrats! Play Again? [y/n]");
                    game = scanner.next();
                    break;
                case 10:
                    System.out.println("Awww. You Lost. Play Again? [y/n]");
                    game = scanner.next();
                    break;
                case 0:
                    System.out.println("Draw Play Again? [y/n]");
                    game = scanner.next();
                    break;
            }
            System.out.println("\n\n");
        }while(game.toLowerCase().equals("y"));
        System.out.println("\n\nThanks For Playing!!!");
    }
    
    public static String layout() {
        String output = "";
        int i = 0;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                output += (i+1);
                if(c < 2)
                    output += "|";
                ++i;
            }
            if(r < 2)
                output += "\n-----";
            output+= "\n";
        }
        return output;
    }
    
    public static int terminalScore(TicTacToeBoard newBoard) {
        if(newBoard.win(aiSymbol))
            return 10;
        else if(newBoard.win(playerSymbol))
            return -10;
        else if(newBoard.isFull())
            return 0;
        return -11;
    }
    
    public static Move miniMax(TicTacToeBoard newBoard, String player) {
        //++fc
        if(newBoard.win(aiSymbol)) {
            int temp = fc;
            fc = 0;
            return new Move(-1, 10, temp);
        }
        else if(newBoard.win(playerSymbol)) {
            int temp = fc;
            fc = 0;
            return new Move(-1, -10, temp);
        }
        else if(newBoard.isFull()) {
            int temp = fc;
            fc = 0;
            return new Move(-1, 0, fc);
        }
        ++fc;
        ArrayList<Move> moves = new ArrayList<Move>();
        ArrayList<Integer> emptySpots = newBoard.emptySpots();
        for(int i:emptySpots) {
            Move m = new Move();
            m.index = i;
            newBoard.drawXO(player, i);
            if (player.equals(aiSymbol)){
                Move result = miniMax(newBoard, playerSymbol);
                m.score = result.score;
                m.functioncalls = result.functioncalls;
            }
            else{
                Move result = miniMax(newBoard, aiSymbol);
                m.score = result.score;
                m.functioncalls = result.functioncalls;
            }
            newBoard.erase(i);
            moves.add(m);
        }
        Move bestMove = moves.get(0);
        if(player.equals(aiSymbol)) {
            for(Move m: moves){
              if(m.score > bestMove.score || 
                      (m.score == bestMove.score && m.functioncalls < bestMove.functioncalls)){
                bestMove = m;
              }
            }
        }
        else {
           for(Move m: moves){
              if(m.score < bestMove.score || 
                      (m.score == bestMove.score && m.functioncalls < bestMove.functioncalls)){
                bestMove = m;
              }
            } 
        }   
    return bestMove;
  }
}