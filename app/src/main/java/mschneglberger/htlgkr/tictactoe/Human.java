package mschneglberger.htlgkr.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Human implements Player, View.OnClickListener {
    String marker;
    MainActivity mainActivity;
    volatile Button clicked;


    public Human(String marker, MainActivity mainActivity){
        this.marker = marker;
        this.mainActivity = mainActivity;
        clicked = null;
    }



    @Override
    public void makeMove() {
        clicked = null;
        TextView temp =(TextView) mainActivity.findViewById(R.id.textView_currentPlayer);
        temp.setText("Spieler " + marker + " ist drann");


        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        setupButtons();

        while(clicked == null){}//WHAIT until button is clicked;

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
            b.setOnClickListener(this);
            b.setClickable(false);
        }


        for(Button b : mainActivity.buttons){
            if(b.getText().equals("")){
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
        clicked = (Button) view;
    }
}
