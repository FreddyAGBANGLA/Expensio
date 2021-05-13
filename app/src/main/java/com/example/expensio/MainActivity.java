package com.example.expensio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.Duration;

public class MainActivity extends AppCompatActivity {
//    Animation anim;
    ImageView logo_expensio;
    TextView text1,text2,text3,text4,text5,text6,text7,text8;

    //------------------
    ScaleAnimation scaleInAnimation, scaleOutAnimation;
    TranslateAnimation translateLogo;
    AnimationSet setLogo;

    //TextAnimation
    AnimationSet animSetText1,animSetText2,animSetText3,animSetText4,animSetText5,animSetText6,animSetText7,animSetText8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo_expensio=findViewById(R.id.logo_expensio); // Declare an imageView to show the animation.
        /*anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade); // Create the animation.
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(new Intent(intent));
                finish();
                return;
                // HomeActivity.class is the activity to go after showing the splash screen.
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        logo_expensio.startAnimation(anim);
*/

        text1= findViewById(R.id.text1);
        text2= findViewById(R.id.text2);
        text3= findViewById(R.id.text3);
        text4= findViewById(R.id.text4);
        text5= findViewById(R.id.text5);
        text6= findViewById(R.id.text6);
        text7= findViewById(R.id.text7);
        text8= findViewById(R.id.text8);


        //Animation de logo
        scaleInAnimation = new ScaleAnimation(1,1.5f,1,1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleInAnimation.setDuration(800);
        scaleInAnimation.setFillAfter(true);

        scaleOutAnimation = new ScaleAnimation(1.5f,1,1.5f,1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleOutAnimation.setDuration(800);

        translateLogo = new TranslateAnimation(0, -200, 0, 0);
        translateLogo.setDuration(800);

        setLogo = new AnimationSet(true);
        setLogo.addAnimation(scaleOutAnimation);
        setLogo.addAnimation(translateLogo);
        setLogo.setFillAfter(true);


        //Animation de texte

        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        alphaAnimation.setDuration(800);

        TranslateAnimation trans1= new TranslateAnimation(0, 120, 0, 0);
        trans1.setDuration(800);

        TranslateAnimation trans2= new TranslateAnimation(0, 160, 0, 0);
        trans2.setDuration(800);

        TranslateAnimation trans3= new TranslateAnimation(0, 200, 0, 0);
        trans3.setDuration(800);

        TranslateAnimation trans4= new TranslateAnimation(0, 240, 0, 0);
        trans4.setDuration(800);

        TranslateAnimation trans5= new TranslateAnimation(0, 282, 0, 0);
        trans5.setDuration(800);

        TranslateAnimation trans6= new TranslateAnimation(0, 322, 0, 0);
        trans6.setDuration(800);

        TranslateAnimation trans7= new TranslateAnimation(0, 362, 0, 0);
        trans7.setDuration(800);

        TranslateAnimation trans8= new TranslateAnimation(0, 380, 0, 0);
        trans8.setDuration(800);

        animSetText1 = new AnimationSet(true);
        animSetText1.addAnimation(alphaAnimation);
        animSetText1.addAnimation(trans1);
        animSetText1.setFillAfter(true);

        animSetText2 = new AnimationSet(true);
        animSetText2.addAnimation(alphaAnimation);
        animSetText2.addAnimation(trans2);
        animSetText2.setFillAfter(true);

        animSetText3 = new AnimationSet(true);
        animSetText3.addAnimation(alphaAnimation);
        animSetText3.addAnimation(trans3);
        animSetText3.setFillAfter(true);

        animSetText4 = new AnimationSet(true);
        animSetText4.addAnimation(alphaAnimation);
        animSetText4.addAnimation(trans4);
        animSetText4.setFillAfter(true);

        animSetText5 = new AnimationSet(true);
        animSetText5.addAnimation(alphaAnimation);
        animSetText5.addAnimation(trans5);
        animSetText5.setFillAfter(true);

        animSetText6 = new AnimationSet(true);
        animSetText6.addAnimation(alphaAnimation);
        animSetText6.addAnimation(trans6);
        animSetText6.setFillAfter(true);

        animSetText7 = new AnimationSet(true);
        animSetText7.addAnimation(alphaAnimation);
        animSetText7.addAnimation(trans7);
        animSetText7.setFillAfter(true);

        animSetText8 = new AnimationSet(true);
        animSetText8.addAnimation(alphaAnimation);
        animSetText8.addAnimation(trans8);
        animSetText8.setFillAfter(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo_expensio.startAnimation(scaleInAnimation);

            }
        }, 2000);

        scaleInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo_expensio.startAnimation(setLogo);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        setLogo.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                text1.startAnimation(animSetText1);
                text2.startAnimation(animSetText2);
                text3.startAnimation(animSetText3);
                text4.startAnimation(animSetText4);
                text5.startAnimation(animSetText5);
                text6.startAnimation(animSetText6);
                text7.startAnimation(animSetText7);
                text8.startAnimation(animSetText8);

                text1.setVisibility(View.VISIBLE);
                text2.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);
                text5.setVisibility(View.VISIBLE);
                text6.setVisibility(View.VISIBLE);
                text7.setVisibility(View.VISIBLE);
                text8.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(new Intent(intent));
                finish();
            }
        },5000);
    }
    }