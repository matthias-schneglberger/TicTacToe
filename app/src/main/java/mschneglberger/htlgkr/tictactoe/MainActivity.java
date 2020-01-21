package mschneglberger.htlgkr.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Button> buttons = new ArrayList<>();
    boolean playerXBot = false;
    boolean playerOBot = false;
    Thread currentGamingThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.button_startGame);


        buttons.add((Button) findViewById(R.id.field1));
        buttons.add((Button) findViewById(R.id.field2));
        buttons.add((Button) findViewById(R.id.field3));

        buttons.add((Button) findViewById(R.id.field4));
        buttons.add((Button) findViewById(R.id.field5));
        buttons.add((Button) findViewById(R.id.field6));

        buttons.add((Button) findViewById(R.id.field7));
        buttons.add((Button) findViewById(R.id.field8));
        buttons.add((Button) findViewById(R.id.field9));

        Switch playerXBotToggle = findViewById(R.id.playerXComputer);
        Switch playerOBotToggle = findViewById(R.id.playerOComputer);

        playerXBotToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    playerXBot = true;
                } else {
                    playerXBot = false;
                }
            }
        });

        playerOBotToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    playerOBot = true;
                } else {
                    playerOBot = false;
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearButtons();

                game();
            }
        });

        for(Button b : buttons){
            b.setClickable(false);
        }

    }

    public void clearButtons(){
        for(Button b : buttons){
            b.setText("");
        }
    }


    public char[][] getBoard(){
        char[][] board = new char[3][3];


        List<Character> buttonsTEXT = new ArrayList<>();


        for(Button b : buttons){


            try{
                buttonsTEXT.add(b.getText().charAt(0));
            }
            catch(Exception e){
                buttonsTEXT.add('#');
            }


        }


        board[0][0] = buttonsTEXT.get(0);
        board[0][1] = buttonsTEXT.get(1);
        board[0][2] = buttonsTEXT.get(2);

        board[1][0] = buttonsTEXT.get(3);
        board[1][1] = buttonsTEXT.get(4);
        board[1][2] = buttonsTEXT.get(5);

        board[2][0] = buttonsTEXT.get(6);
        board[2][1] = buttonsTEXT.get(7);
        board[2][2] = buttonsTEXT.get(8);

        return board;
    }

    public void setBoard(char[][] board){

//        for(Button b : buttons){
//            b.setText("");
//        }


        int currentNum = 0;
        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){

                    try{
                        if(!String.valueOf(board[i][x]).equals("#")){
                            buttons.get(currentNum).setText(String.valueOf(board[i][x]));
                        }

                    }
                    catch (Exception e){
                        Log.e("MainActivity", "setBoard: failed to set button#" + currentNum + "to: " + board[i][x]);
                    }

                    currentNum++;

            }
        }


//        buttons.get(0).setText(String.valueOf(board[0][0]));
//        buttons.get(1).setText(String.valueOf(board[0][1]));
//        buttons.get(2).setText(String.valueOf(board[0][2]));
//
//        buttons.get(3).setText(String.valueOf(board[1][0]));
//        buttons.get(4).setText(String.valueOf(board[1][1]));
//        buttons.get(5).setText(String.valueOf(board[1][2]));
//
//        buttons.get(6).setText(String.valueOf(board[2][0]));
//        buttons.get(7).setText(String.valueOf(board[2][1]));
//        buttons.get(8).setText(String.valueOf(board[2][2]));
    }


    private void game(){
        if(currentGamingThread != null){

        }
//            currentGamingThread.stop();
        currentGamingThread = new Thread(new GameThread(this, playerXBot, playerOBot));
        currentGamingThread.start();

        Log.d("NEW THREAD", "game: " + playerOBot + playerXBot);


    }
}
