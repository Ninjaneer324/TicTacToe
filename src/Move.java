/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kakas
 */
public class Move {
    public int index;
    public int score;
    public int functioncalls;
    public Move() {
        index = -1;
        score = -11;
        functioncalls = -1;
    }
    
    public Move(int i, int s, int f) {
        index = i;
        score = s;
        functioncalls = f;
    }
    
}
