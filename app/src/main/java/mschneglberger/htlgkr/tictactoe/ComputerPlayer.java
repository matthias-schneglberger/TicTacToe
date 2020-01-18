package mschneglberger.htlgkr.tictactoe;

import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ComputerPlayer implements Player{
    char marker;
    char gegenMarker;
    MainActivity mainActivity;


    public ComputerPlayer(char marker, char gegenMarker, MainActivity mainActivity){
        this.marker = marker;
        this.gegenMarker = gegenMarker;
        this.mainActivity = mainActivity;
    }

    @Override
    public void move() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        char[][] board = mainActivity.getBoard();

        char[][] bestMove = null;
        int bestValue = -1;

//        int counter = 0;
        List<char[][]> possibleMoves = getAllPossibleMoves(deepCopyMatrix(board), marker);

//        for(char[][] tempBoard : possibleMoves){
//            mainActivity.setBoard(tempBoard);
//        }


        for(char[][] tempBoard : possibleMoves){
            int tempV = minMax(deepCopyMatrix(tempBoard), 0, false);
            if(tempV > bestValue){
                bestMove = deepCopyMatrix(tempBoard);
                bestValue = tempV;

            }
//            counter++;
        }

        mainActivity.setBoard(bestMove);
    }

    public int minMax(char[][] board, int depth, boolean isMaximizingPlayer){
        if(hasWinner(board) != '*'){
            char returnVal = hasWinner(board);
            if(returnVal == '-'){
                return 0;
            }
            if(returnVal == marker){
                return 1;
            }

            return -1;
        }

        int bestValue;
        if(isMaximizingPlayer){
            bestValue = Integer.MIN_VALUE;
            for(char[][] tempBoard : getAllPossibleMoves(deepCopyMatrix(board), marker)){
                int value = minMax(deepCopyMatrix(tempBoard), depth+1, false);
                bestValue = max(bestValue, value);
            }
            return bestValue;
        }
        else{
            bestValue = Integer.MAX_VALUE;
            for(char[][] tempBoard : getAllPossibleMoves(deepCopyMatrix(board), gegenMarker)){
                int value = minMax(deepCopyMatrix(tempBoard), depth+1, true);
                bestValue = min(bestValue, value);
            }
            return bestValue;
        }

    }

    public List<char[][]> getAllPossibleMoves(char[][] board, char markerToSet){
        List<char[][]> possibleMoves = new ArrayList<>();
        char[][] tempArr;
        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                if(board[i][x] == '#'){
                    tempArr = deepCopyMatrix(board);
                    tempArr[i][x] = markerToSet;
                    possibleMoves.add(tempArr);
                }
            }
        }

        return possibleMoves;
    }

    public int max(int a, int b){
        return Math.max(a,b);
    }
    public int min(int a, int b){
        return Math.min(a,b);
    }


    private char hasWinner(char[][] board){
        char winner = '*';

        //ROW CHECKER
        for(int i = 0; i <= 2; i++){
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] != '#'){
                winner = board[i][0];
            }
        }

        //COL CHECKER
        for(int i = 0; i <= 2; i++){
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] != '#'){
                winner = board[0][i];
            }
        }

        //DIAGONAL CHECKER
        if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] != '#'){
            winner = board[0][0];
        }

        //DIAGONAL CHECKER
        if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[0][2] != '#'){
            winner = board[2][0];
        }

        //Everything full
        boolean noteverythingFull = true;

        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                if(board[i][x] == '#'){
                    noteverythingFull = false;
                }

            }
        }

        if(noteverythingFull && winner == '*'){
            winner = '-';
        }



        return winner;
    }


    public static char[][] deepCopyMatrix(char[][] input) {
        if (input == null)
            return null;
        char[][] result = new char[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
}
