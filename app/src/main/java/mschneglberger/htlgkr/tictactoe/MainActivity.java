package mschneglberger.htlgkr.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Button> buttons = new ArrayList<>();

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



    private void game(){
        boolean playerXBot = findViewById(R.id.playerXComputer).isActivated();
        boolean playerOBot = findViewById(R.id.playerOComputer).isActivated();

        Thread t = new Thread(new GameThread(this, playerXBot, playerOBot));
        t.start();
    }
}
