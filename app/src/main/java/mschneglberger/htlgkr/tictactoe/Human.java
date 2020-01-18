package mschneglberger.htlgkr.tictactoe;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Human implements Player, View.OnClickListener {
    String marker;
    MainActivity mainActivity;
    Button clicked;
    boolean whait = true;


    public Human(String marker, MainActivity mainActivity){
        this.marker = marker;
        this.mainActivity = mainActivity;

    }



    @Override
    public void move() {
        clicked = null;
        TextView temp =(TextView) mainActivity.findViewById(R.id.textView_currentPlayer);
        temp.setText("Spieler " + marker + " ist drann");



        shutDownButtons();
        setupButtons();



        while(whait){
            Log.i("INFO", "makeMove: inWhile");
        }//WHAIT until button is clicked;
        whait = true;

        shutDownButtons();


        try{
            clicked.setText(marker);
        }
        catch(Exception e){}


        clicked = null;

        temp.setText("Spieler " + marker + " als letztes drann");
    }

    private void setupButtons(){
        for(Button b : mainActivity.buttons){
            b.setOnClickListener(null);
            b.setOnClickListener(this);
            b.setClickable(false);
        }


        for(Button b : mainActivity.buttons){
            if(b.getText().equals("") || b.getText().equals(" ")){
                b.setClickable(true);
            }

        }
    }
    private void shutDownButtons(){
        for(Button b : mainActivity.buttons){
            b.setOnClickListener(null);
            b.setClickable(false);
        }
    }

    @Override
    public void onClick(View view) {
        this.clicked = (Button) view;
        whait = false;
        Log.d("test", "onClick: clicked" + this.marker);
    }
}
