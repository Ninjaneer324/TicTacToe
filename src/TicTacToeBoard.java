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

public class TicTacToeBoard {
    private String[] board;
    public TicTacToeBoard() {
        board = new String[9];
        resetBoard();
    }
    public String get(int pos) {
        return board[pos];
    }
    public void drawXO(String symbol, int pos) {
        board[pos] = symbol;
    }
    public void erase(int pos) {
        board[pos] = " ";
    }
    public void resetBoard() {
        for(int i = 0; i < 9; i++)
            erase(i);
    }
    
    public ArrayList<Integer> emptySpots() {
        ArrayList<Integer> empty = new ArrayList<Integer>();
        for(int i = 0; i < 9; i++) {
            if(board[i] == " ")
                empty.add(i);
        }
        return empty;
    }
    
    public boolean isFull() {
        return emptySpots().size() == 0;
    }
    
    public boolean win(String symbol) {
        if((board[0].equals(symbol) && board[1].equals(symbol) && board[2].equals(symbol)) ||
            (board[3].equals(symbol) && board[4].equals(symbol) && board[5].equals(symbol)) ||
            (board[6].equals(symbol) && board[7].equals(symbol) && board[8].equals(symbol)) )
            return true;
        else if((board[0].equals(symbol) && board[3].equals(symbol) && board[6].equals(symbol)) ||
            (board[1].equals(symbol) && board[4].equals(symbol) && board[7].equals(symbol)) ||
            (board[2].equals(symbol) && board[5].equals(symbol) && board[8].equals(symbol)) )
            return true;
        else if((board[0].equals(symbol) && board[4].equals(symbol) && board[8].equals(symbol)) ||
            (board[6].equals(symbol) && board[4].equals(symbol) && board[2].equals(symbol)) )
            return true;           
        return false;
    }
    
    public String toString() {
        String output = "";
        int index = 0;
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                output += board[index];
                if(c < 2)
                    output += "|";
                ++index;
            }
            if(r < 2)
                output += "\n-----";
            output += "\n";
        }
        return output;
    }
    
}
