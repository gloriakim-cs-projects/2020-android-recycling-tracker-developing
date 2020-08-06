package com.codepath.wmgf8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Random;

public class top_nav_goal extends AppCompatActivity {

    //navigation
    ImageButton ic_camera_button, ic_search_button, ic_record_button, ic_daily_goal1, ic_daily_goal2, ic_daily_goal3, ic_daily_goal4, ic_weekly_goal1, ic_weekly_goal2, ic_weekly_goal3, ic_weekly_goal4;
    Button oic_background, ic_today_button, ic_goal_button, ic_collection_button;

    //goals
    String TAG = "";
    ImageView IOD, daily_goal1, daily_goal2, daily_goal3, daily_goal4, weekly_goal1, weekly_goal2, weekly_goal3, weekly_goal4;
    TextView daily_goal1_text, daily_goal2_text, daily_goal3_text, daily_goal4_text, weekly_goal1_text, weekly_goal2_text, weekly_goal3_text, weekly_goal4_text;

    //FIXME: Please note that if the below oic_array changes, the oic_array in bottom_nav_search.java & bottom_nav_camera.java & bottom_nav_search_details.javaalso needs to change
    String[] oic_array = {"Backpack", "Ball", "Bicycle", "Binder", "Blanket", "Bulletin Board", "Can", "Candy Wrapper", "Car", "Cardboard", "Cellphone", "Charger", "Chips", "Clothing", "Coffee Machine", "Comb", "Computer", "Detergent", "Earphone", "Eraser", "Extension Cord", "Fan", "Food", "Frying Pan", "Furniture", "Glass Cup", "Glasses", "Hair Dryer", "Hairbrush", "Hanger", "Jam", "Ketchup Bottle", "Kettle", "Lamp", "Laptop", "Lightbulb", "Medicine", "Milk Carton", "Milk Jug", "Mug", "Notebook", "Paper", "Paper Cup", "Paper Plate", "Paper Towel", "Pen", "Pencil", "Pillow", "Pizza Box", "Plastic Bag", "Plastic Bottle", "Plastic Cup", "Plastic Jug", "Plastic Utensil", "Pot", "Refrigerator", "Rug", "Scissors", "Shoes", "Silverware", "Straw", "Styrofoam", "Styrofoam Box", "TV", "Tissue", "To Go Box", "Toothbrush", "Toothpaste", "Towel", "Trash Bag", "USB Cable", "USB Flash Drive", "Umbrella", "Water Bottle", "Whiteboard"};
    int[] daily_counter = new int[75];
    int[] weekly_counter = new int[75];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_nav_goal);


        IOD = (ImageView) findViewById(R.id.oic);
        oic_background = (Button) findViewById(R.id.oic_background);
        ic_daily_goal1 = (ImageButton) findViewById(R.id.ic_daily_goal1);
        ic_daily_goal2 = (ImageButton) findViewById(R.id.ic_daily_goal2);
        ic_daily_goal3 = (ImageButton) findViewById(R.id.ic_daily_goal3);
        ic_daily_goal4 = (ImageButton) findViewById(R.id.ic_daily_goal4);
        ic_weekly_goal1 = (ImageButton) findViewById(R.id.ic_weekly_goal1);
        ic_weekly_goal2 = (ImageButton) findViewById(R.id.ic_weekly_goal2);
        ic_weekly_goal3 = (ImageButton) findViewById(R.id.ic_weekly_goal3);
        ic_weekly_goal4 = (ImageButton) findViewById(R.id.ic_weekly_goal4);

        daily_goal1 = (ImageView) findViewById(R.id.oic_daily_goal1);
        daily_goal2 = (ImageView) findViewById(R.id.oic_daily_goal2);
        daily_goal3 = (ImageView) findViewById(R.id.oic_daily_goal3);
        daily_goal4 = (ImageView) findViewById(R.id.oic_daily_goal4);
        daily_goal1_text = (TextView) findViewById(R.id.ic_daily_goal1_text);
        daily_goal2_text = (TextView) findViewById(R.id.ic_daily_goal2_text);
        daily_goal3_text = (TextView) findViewById(R.id.ic_daily_goal3_text);
        daily_goal4_text = (TextView) findViewById(R.id.ic_daily_goal4_text);

        weekly_goal1 = (ImageView) findViewById(R.id.oic_weekly_goal1);
        weekly_goal2 = (ImageView) findViewById(R.id.oic_weekly_goal2);
        weekly_goal3 = (ImageView) findViewById(R.id.oic_weekly_goal3);
        weekly_goal4 = (ImageView) findViewById(R.id.oic_weekly_goal4);
        weekly_goal1_text = (TextView) findViewById(R.id.ic_weekly_goal1_text);
        weekly_goal2_text = (TextView) findViewById(R.id.ic_weekly_goal2_text);
        weekly_goal3_text = (TextView) findViewById(R.id.ic_weekly_goal3_text);
        weekly_goal4_text = (TextView) findViewById(R.id.ic_weekly_goal4_text);


        ////////////
        /* goals */
        ///////////
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        int lastDay = settings.getInt("today",0);
        int lastWeek = settings.getInt("week", 0);

        //////////////////////////////////
        /* Goal assignment based on DAY */
        //////////////////////////////////
        //region assign goals based on DAY
        if (lastDay != currentDay) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("today", currentDay);

            Random random = new Random();

            String str0 = IOD();
            editor.putString("IOD", str0);

            String[] str = new String[8];
            str = daily_goal();
            editor.putString("daily_goal1", str[0]);
            editor.putString("daily_goal2", str[1]);
            editor.putString("daily_goal3", str[2]);
            editor.putString("daily_goal4", str[3]);
            editor.putString("daily_goal1_text", str[4]);
            editor.putString("daily_goal2_text", str[5]);
            editor.putString("daily_goal3_text", str[6]);
            editor.putString("daily_goal4_text", str[7]);

            editor.commit();
        }

        if (lastWeek != currentWeek) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("week", currentWeek);

            Random random = new Random();

            String[] str = new String[8];
            str = weekly_goal();
            editor.putString("weekly_goal1", str[0]);
            editor.putString("weekly_goal2", str[1]);
            editor.putString("weekly_goal3", str[2]);
            editor.putString("weekly_goal4", str[3]);
            editor.putString("weekly_goal1_text", str[4]);
            editor.putString("weekly_goal2_text", str[5]);
            editor.putString("weekly_goal3_text", str[6]);
            editor.putString("weekly_goal4_text", str[7]);

            //commit
            editor.commit();
        }
        //region assign goals based on DAY

        //call goal counters
        for (int i = 0; i < oic_array.length; i++) {
            StringBuilder s = new StringBuilder("counter_");
            s.append(oic_array[i]);
            String s2 = s.toString();
            daily_counter[i] = Integer.valueOf(settings.getString(s2, "0"));
        }

        //////////////////////////////
        /* IOD, daily, weekly goals */
        //////////////////////////////
        //region IOD, daily, weekly goal
        String str0 = settings.getString("IOD", "0");
        IOD2(str0, daily_counter);

        String[] returned_daily = new String[8];
        returned_daily[0] = settings.getString("daily_goal1", "0");
        returned_daily[1] = settings.getString("daily_goal2", "0");
        returned_daily[2] = settings.getString("daily_goal3", "0");
        returned_daily[3] = settings.getString("daily_goal4", "0");
        returned_daily[4] = settings.getString("daily_goal1_text", "0");
        returned_daily[5] = settings.getString("daily_goal2_text", "0");
        returned_daily[6] = settings.getString("daily_goal3_text", "0");
        returned_daily[7] = settings.getString("daily_goal4_text", "0");

        returned_daily_goal(returned_daily);

        String[] returned_weekly = new String[8];
        returned_weekly[0] = settings.getString("weekly_goal1", "0");
        returned_weekly[1] = settings.getString("weekly_goal2", "0");
        returned_weekly[2] = settings.getString("weekly_goal3", "0");
        returned_weekly[3] = settings.getString("weekly_goal4", "0");
        returned_weekly[4] = settings.getString("weekly_goal1_text", "0");
        returned_weekly[5] = settings.getString("weekly_goal2_text", "0");
        returned_weekly[6] = settings.getString("weekly_goal3_text", "0");
        returned_weekly[7] = settings.getString("weekly_goal4_text", "0");

        returned_weekly_goal(returned_weekly);
        //endregion IOD, daily, weekly goal

        ///////////////////////
        /* bottom nav camera */
        ///////////////////////
        //region bottom nav

        ic_camera_button = (ImageButton) findViewById(R.id.ic_main_camera);
        ic_search_button = (ImageButton) findViewById(R.id.image_search);
        ic_record_button = (ImageButton) findViewById(R.id.image_record);

        ic_camera_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(top_nav_goal.this, bottom_nav_camera.class);
                startActivity(int1);
            }
        });

        ic_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(top_nav_goal.this, bottom_nav_search.class);
                startActivity(int2);
            }
        });

        ic_record_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            }
        });
        //endregion bottom nav

        ////////////////////
        /* top navigation */
        ///////////////////
        //region top nav

        ic_today_button = (Button) findViewById(R.id.ic_today_button);
        ic_collection_button = (Button) findViewById(R.id.ic_collection_button);

        ic_today_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(top_nav_goal.this, MainActivity.class);
                startActivity(int4);
            }
        });

        ic_collection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int6 = new Intent(top_nav_goal.this, top_nav_collect.class);
                startActivity(int6);
            }
        });
        //endregion top nav
    }

    public String IOD(){
        Random random = new Random();

        String str0 = "oic_" + random.nextInt(76); //number of classes/folders
        int id = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str0, null, null);
        IOD.setBackgroundResource(id);

        return str0;
    }

    public void IOD2(String str, int[] counter) {
        int id = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str, null, null);
        IOD.setBackgroundResource(id);

        String str2 = str.replaceAll("[^0-9]", "");
        int strNum = Integer.valueOf(str2);
        if (strNum < 1) {
            strNum = 1;
        }
        str = oic_array[strNum - 1];
        SharedPreferences settings = getSharedPreferences("PREFS", 0);


        StringBuilder s = new StringBuilder("counter_");
        s.append(str);
        String s2 = s.toString();
        daily_counter[strNum - 1] = Integer.valueOf(settings.getString(s2, "0"));

        if (daily_counter[strNum - 1] >= 1) {
            //chyange the color(background) of button
            oic_background.setBackgroundResource(R.drawable.ioc_rectangle_dark);
            //make button clickable to receive the award
            oic_background.setEnabled(true);
            oic_background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //set the "double" point part
                    SharedPreferences settings = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("doublePoint", "True");
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "You can now have double points!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public String[] daily_goal(){
        Random random = new Random();
        String[] str = new String[8];

        str[0] = "oic_" + random.nextInt(75);
        str[1] = "oic_" + random.nextInt(75);
        str[2] = "oic_" + random.nextInt(75);
        str[3] = "oic_" + random.nextInt(75);

        int id1 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[0], null, null);
        int id2 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[1], null, null);
        int id3 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[2], null, null);
        int id4 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[3], null, null);

        daily_goal1.setBackgroundResource(id1);
        daily_goal2.setBackgroundResource(id2);
        daily_goal3.setBackgroundResource(id3);
        daily_goal4.setBackgroundResource(id4);

        //textView
        str[4] = "Collect " + (random.nextInt(10 - 5) + 5); // assign 5~10 //FIXME: need to assign 30% more/less than last week
        daily_goal1_text.setText(str[4]);
        str[5] = "Collect " + (random.nextInt(10 - 5) + 5); // assign 5~10 //FIXME: need to assign 30% more/less than last week
        daily_goal2_text.setText(str[5]);
        str[6] = "Collect " + (random.nextInt(10 - 5) + 5); // assign 5~10 //FIXME: need to assign 30% more/less than last week
        daily_goal3_text.setText(str[6]);
        str[7] = "Collect " + (random.nextInt(10 - 5) + 5); // assign 5~10 //FIXME: need to assign 30% more/less than last week
        daily_goal4_text.setText(str[7]);

        return str;
    }

    public void returned_daily_goal(String[] str) {
        int id1 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[0], null, null);
        int id2 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[1], null, null);
        int id3 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[2], null, null);
        int id4 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[3], null, null);

        daily_goal1.setBackgroundResource(id1);
        daily_goal2.setBackgroundResource(id2);
        daily_goal3.setBackgroundResource(id3);
        daily_goal4.setBackgroundResource(id4);

        daily_goal1_text.setText(str[4]);
        daily_goal2_text.setText(str[5]);
        daily_goal3_text.setText(str[6]);
        daily_goal4_text.setText(str[7]);

        //assign goals
        String new_str1 = str[0].replaceAll("[^0-9]", "");
        String new_str2 = str[1].replaceAll("[^0-9]", "");
        String new_str3 = str[2].replaceAll("[^0-9]", "");
        String new_str4 = str[3].replaceAll("[^0-9]", "");

        int strNum1 = Integer.parseInt(new_str1);
        int strNum2 = Integer.parseInt(new_str2);
        int strNum3 = Integer.parseInt(new_str3);
        int strNum4 = Integer.parseInt(new_str4);

        if (strNum1 < 1) {
            strNum1 = 1;
        }
        if (strNum2 < 1) {
            strNum2 = 1;
        }
        if (strNum3 < 1) {
            strNum3 = 1;
        }
        if (strNum4 < 1) {
            strNum4 = 1;
        }

        String new_strNum1 = oic_array[strNum1 - 1];
        String new_strNum2 = oic_array[strNum2 - 1];
        String new_strNum3 = oic_array[strNum3 - 1];
        String new_strNum4 = oic_array[strNum4 - 1];

        SharedPreferences settings = getSharedPreferences("PREFS", 0);

        StringBuilder s1 = new StringBuilder("counter_");
        StringBuilder s2 = new StringBuilder("counter_");
        StringBuilder s3 = new StringBuilder("counter_");
        StringBuilder s4 = new StringBuilder("counter_");

        s1.append(new_strNum1);
        s2.append(new_strNum2);
        s3.append(new_strNum3);
        s4.append(new_strNum4);

        String daily1 = s1.toString();
        String daily2 = s2.toString();
        String daily3 = s3.toString();
        String daily4 = s4.toString();
        //Toast.makeText(getApplicationContext(), daily1, Toast.LENGTH_LONG).show();

        daily_counter[strNum1 - 1] = Integer.parseInt(settings.getString(daily1, "0"));
        daily_counter[strNum2 - 1] = Integer.parseInt(settings.getString(daily2, "0"));
        daily_counter[strNum3 - 1] = Integer.parseInt(settings.getString(daily3, "0"));
        daily_counter[strNum4 - 1] = Integer.parseInt(settings.getString(daily4, "0"));
        //Toast.makeText(getApplicationContext(), Integer.toString(daily_counter[strNum1 - 1]), Toast.LENGTH_LONG).show();

        String new_str5 = str[4].replaceAll("[^0-9]", "");
        String new_str6 = str[5].replaceAll("[^0-9]", "");
        String new_str7 = str[6].replaceAll("[^0-9]", "");
        String new_str8 = str[7].replaceAll("[^0-9]", "");

        int strNum5 = Integer.parseInt(new_str5);
        int strNum6 = Integer.parseInt(new_str6);
        int strNum7 = Integer.parseInt(new_str7);
        int strNum8 = Integer.parseInt(new_str8);

        if (daily_counter[strNum1 - 1] == strNum5) {
            //chyange the color(background) of button
            ic_daily_goal1.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_daily_goal1.setEnabled(true);
            ic_daily_goal1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   //FIXME: edit the following code if more tasks need to be assigned!
//                    SharedPreferences settings = getSharedPreferences("PREFS", 0);
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.putString("doublePoint", "TRUE");
//                    String doublePoint = settings.getString("doublePoint", "0");
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (daily_counter[strNum2 - 1] == strNum6) {
            //chyange the color(background) of button
            ic_daily_goal2.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_daily_goal2.setEnabled(true);
            ic_daily_goal2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (daily_counter[strNum3 - 1] == strNum7) {
            //chyange the color(background) of button
            ic_daily_goal3.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_daily_goal3.setEnabled(true);
            ic_daily_goal3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (daily_counter[strNum4 - 1] == strNum8) {
            //chyange the color(background) of button
            ic_daily_goal4.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_daily_goal4.setEnabled(true);
            ic_daily_goal4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public String[] weekly_goal() {
        Random random = new Random();
        String[] str = new String[8];

        //imageView
        str[0] = "oic_" + random.nextInt(76);
        str[1] = "oic_" + random.nextInt(76);
        str[2] = "oic_" + random.nextInt(76);
        str[3] = "oic_" + random.nextInt(76);

        int id5 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[0], null, null);
        int id6 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[1], null, null);
        int id7 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[2], null, null);
        int id8 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[3], null, null);

        weekly_goal1.setBackgroundResource(id5);
        weekly_goal2.setBackgroundResource(id6);
        weekly_goal3.setBackgroundResource(id7);
        weekly_goal4.setBackgroundResource(id8);

        //textView
        str[4] = "Collect " + (random.nextInt(40 - 20) + 20); // assign 40~100 //FIXME: need to assign 30% more/less than last week
        weekly_goal1_text.setText(str[4]);
        str[5] = "Collect " + (random.nextInt(40 - 20) + 20); // assign 40~100 //FIXME: need to assign 30% more/less than last week
        weekly_goal2_text.setText(str[5]);
        str[6] = "Collect " + (random.nextInt(40 - 20) + 20); // assign 40~100 //FIXME: need to assign 30% more/less than last week
        weekly_goal3_text.setText(str[6]);
        str[7] = "Collect " + (random.nextInt(40 - 20) + 20); // assign 40~100 //FIXME: need to assign 30% more/less than last week
        weekly_goal4_text.setText(str[7]);

        return str;
    }

    public void returned_weekly_goal(String[] str) {
        int id1 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[0], null, null);
        int id2 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[1], null, null);
        int id3 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[2], null, null);
        int id4 = getResources().getIdentifier("com.codepath.wmgf8:drawable/" + str[3], null, null);

        weekly_goal1.setBackgroundResource(id1);
        weekly_goal2.setBackgroundResource(id2);
        weekly_goal3.setBackgroundResource(id3);
        weekly_goal4.setBackgroundResource(id4);

        weekly_goal1_text.setText(str[4]);
        weekly_goal2_text.setText(str[5]);
        weekly_goal3_text.setText(str[6]);
        weekly_goal4_text.setText(str[7]);

        //assign goals
        String new_str1 = str[0].replaceAll("[^0-9]", "");
        String new_str2 = str[1].replaceAll("[^0-9]", "");
        String new_str3 = str[2].replaceAll("[^0-9]", "");
        String new_str4 = str[3].replaceAll("[^0-9]", "");

        int strNum1 = Integer.parseInt(new_str1);
        int strNum2 = Integer.parseInt(new_str2);
        int strNum3 = Integer.parseInt(new_str3);
        int strNum4 = Integer.parseInt(new_str4);

        if (strNum1 < 1) {
            strNum1 = 1;
        }
        if (strNum2 < 1) {
            strNum2 = 1;
        }
        if (strNum3 < 1) {
            strNum3 = 1;
        }
        if (strNum4 < 1) {
            strNum4 = 1;
        }

        String new_strNum1 = oic_array[strNum1 - 1];
        String new_strNum2 = oic_array[strNum2 - 1];
        String new_strNum3 = oic_array[strNum3 - 1];
        String new_strNum4 = oic_array[strNum4 - 1];

        SharedPreferences settings = getSharedPreferences("PREFS", 0);

        StringBuilder s1 = new StringBuilder("counter_");
        StringBuilder s2 = new StringBuilder("counter_");
        StringBuilder s3 = new StringBuilder("counter_");
        StringBuilder s4 = new StringBuilder("counter_");

        s1.append(new_strNum1);
        s2.append(new_strNum2);
        s3.append(new_strNum3);
        s4.append(new_strNum4);

        String daily1 = s1.toString();
        String daily2 = s2.toString();
        String daily3 = s3.toString();
        String daily4 = s4.toString();
        //Toast.makeText(getApplicationContext(), daily1, Toast.LENGTH_LONG).show();

        weekly_counter[strNum1 - 1] = Integer.parseInt(settings.getString(daily1, "0"));
        weekly_counter[strNum2 - 1] = Integer.parseInt(settings.getString(daily2, "0"));
        weekly_counter[strNum3 - 1] = Integer.parseInt(settings.getString(daily3, "0"));
        weekly_counter[strNum4 - 1] = Integer.parseInt(settings.getString(daily4, "0"));
        //Toast.makeText(getApplicationContext(), Integer.toString(daily_counter[strNum1 - 1]), Toast.LENGTH_LONG).show();

        String new_str5 = str[4].replaceAll("[^0-9]", "");
        String new_str6 = str[5].replaceAll("[^0-9]", "");
        String new_str7 = str[6].replaceAll("[^0-9]", "");
        String new_str8 = str[7].replaceAll("[^0-9]", "");

        int strNum5 = Integer.parseInt(new_str5);
        int strNum6 = Integer.parseInt(new_str6);
        int strNum7 = Integer.parseInt(new_str7);
        int strNum8 = Integer.parseInt(new_str8);

        if (weekly_counter[strNum1 - 1] == strNum5) {
            //chyange the color(background) of button
            ic_weekly_goal1.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_weekly_goal1.setEnabled(true);
            ic_weekly_goal1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //FIXME: edit the following code if more tasks need to be assigned!
//                    SharedPreferences settings = getSharedPreferences("PREFS", 0);
//                    SharedPreferences.Editor editor = settings.edit();
//                    editor.putString("doublePoint", "TRUE");
//                    String doublePoint = settings.getString("doublePoint", "0");
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (weekly_counter[strNum2 - 1] == strNum6) {
            //chyange the color(background) of button
            ic_weekly_goal2.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_weekly_goal2.setEnabled(true);
            ic_weekly_goal2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (weekly_counter[strNum3 - 1] == strNum7) {
            //chyange the color(background) of button
            ic_weekly_goal3.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_weekly_goal3.setEnabled(true);
            ic_weekly_goal3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
        if (weekly_counter[strNum4 - 1] == strNum8) {
            //chyange the color(background) of button
            ic_weekly_goal4.setBackgroundResource(R.drawable.ic_oval_dark);
            //make button clickable to receive the award
            ic_weekly_goal4.setEnabled(true);
            ic_weekly_goal4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(), "You reached today's goal!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
