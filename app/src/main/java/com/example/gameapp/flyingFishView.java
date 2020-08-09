package com.example.gameapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class flyingFishView extends View {

    int count=0;

    private Bitmap fish[] = new Bitmap[2];
    private int fishX=10;
    private int fishY;
    private int fishSpeed;
    private boolean touch = false;

    private int canvasWidth,canvasHeight;

    private  int yellowX,yellowY,yellowSpeed=50;
    private Paint yellowPaint= new Paint();

    private int greenX,greenY,greenSpeed=56;
    private Paint greenPaint = new Paint();

    private int redX,redY,redSpeed=60;
    private Paint redPaint = new Paint();

    private int score,lifeCounter;

    private Bitmap backgroundImage;
    private Paint scorePaint = new Paint ();
    private Bitmap life[] = new Bitmap[2];


    public flyingFishView(Context context) {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(),R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(),R.drawable.fish2);

        backgroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.backg);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT);
        scorePaint.setAntiAlias(true);

        life[0]= BitmapFactory.decodeResource(getResources(),R.drawable.hearts);
        life[1]= BitmapFactory.decodeResource(getResources(),R.drawable.heart_grey);

        fishY= 350;
        score = 0;
        lifeCounter=3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        canvas.drawBitmap(backgroundImage,0,0,null);

        int minFishY = fish[0].getHeight();
        int maxFishY = canvasHeight - fish[0].getHeight() * 3;
        fishY+=fishSpeed;

        if (fishY<minFishY)
            fishY=minFishY;
        if (fishY>maxFishY)
            fishY=maxFishY;

        fishSpeed+=2;
        if (touch){
            count++;
            canvas.drawBitmap(fish[1],fishX,fishY,null);
            touch=false;
        }
        else{
            canvas.drawBitmap(fish[0],fishX,fishY,null);
        }

        yellowX-=yellowSpeed;

        if (hitBallChecker(yellowX,yellowY)){
            score+=10;
            yellowX = - 100;
        }
        if (yellowX<0){
            yellowX=canvasWidth+21;
            yellowY= (int) Math.floor(Math.random() * (maxFishY-minFishY)+minFishY);

        }
        canvas.drawCircle(yellowX,yellowY,40,yellowPaint);

        greenX-=greenSpeed;

        if (hitBallChecker(greenX,greenY)){
            score+=20;
            greenX = - 100;
        }
        if (greenX<0){
            if (count%4==0) {
                greenX = canvasWidth + 21;
                greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY) + minFishY);
            }

        }
        canvas.drawCircle(greenX,greenY,50,greenPaint);

        redX-=redSpeed;
        if (hitBallChecker(redX,redY)){
            lifeCounter--;
            redX = - 100;
            if (lifeCounter==0){
                Toast.makeText(getContext(), "Game Over *_*", Toast.LENGTH_SHORT).show();

                Intent gameoverIntent = new Intent(getContext(),GameOverActivity.class);
                gameoverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                gameoverIntent.putExtra("Score",score);
                getContext().startActivity(gameoverIntent);

            }
        }
        if (redX<0){
            redX=canvasWidth+28;
            redY= (int) Math.floor(Math.random() * (maxFishY-minFishY)+minFishY);

        }
        canvas.drawCircle(redX,redY,50,redPaint);

        canvas.drawText("Score: "+score,20,60,scorePaint);

        for (int i=0;i<3;i++){
            int x = (int) (700 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i<lifeCounter){
                canvas.drawBitmap(life[0],x,y,null);
            }
            else{
                canvas.drawBitmap(life[1],x,y,null);
            }

        }


    }

    public boolean hitBallChecker(int x, int y){
        if (fishX<x && x< (fishX + fish[0].getWidth()) && fishY< y && y< (fishY + fish[0].getHeight()) )
            return true;
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            touch=true;
            fishSpeed=-30;
        }
        return true;
    }
}
