package mschneglberger.htlgkr.tictactoe;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class GameThread implements Runnable {
    MainActivity mainActivity;
    boolean botX;
    boolean botO;


    public GameThread(MainActivity mainActivity, boolean botX, boolean botO){
        this.mainActivity = mainActivity;
        this.botX = botX;
        this.botO = botO;
    }


    @Override
    public void run() {
        Looper.prepare();

        final String winner;
        Player playerX;
        Player playerO;

        if(!botX){
            playerX = new Human("X", mainActivity);
        }
        else{
            playerX = new ComputerPlayer();
        }



        if(!botO){
            playerO = new Human("O", mainActivity);
        }
        else{
            playerO = new ComputerPlayer();
        }



        while(true){

            if(hasWinner(mainActivity.getBoard()) != '*'){
                winner = String.valueOf(hasWinner(mainActivity.getBoard()));
                break;
            }

            playerX.makeMove();


            if(hasWinner(mainActivity.getBoard()) != '*'){
                winner = String.valueOf(hasWinner(mainActivity.getBoard()));
                break;
            }
            playerO.makeMove();

        }

        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(mainActivity, "Gewonnen hat: " + winner, Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("WINNER", "run: WINNER IS LOCATED " + winner);



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

        if(noteverythingFull){
            winner = '-';
        }





        return winner;
    }
}
