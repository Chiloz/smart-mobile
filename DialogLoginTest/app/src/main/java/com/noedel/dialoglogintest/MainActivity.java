package com.noedel.dialoglogintest;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String poiContent = "Nunc molestie dui nibh, in tempus tellus pharetra sed. Maecenas sodales, mauris ac ullamcorper laoreet, sapien nulla auctor tellus, mattis sodales nulla orci non velit. Fusce gravida libero purus, non hendrerit eros condimentum ac. Proin ultricies sodales mi sed vestibulum. Nullam eget nisi eget ante maximus dignissim.";
    String poiContent2 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin imperdiet tincidunt justo, ac convallis mi posuere ut. Pellentesque luctus massa molestie lorem porta finibus. Donec magna neque, eleifend a fringilla at, egestas quis mi. Etiam eu tristique est. Curabitur volutpat diam dui, quis efficitur nibh sagittis ut. Praesent cursus est faucibus condimentum dignissim. In hac habitasse platea dictumst. Aliquam vehicula ex metus, a porttitor sapien lobortis non. Sed mollis, sapien vitae condimentum fermentum, mauris diam auctor nisi, a gravida turpis diam id libero.";
    String poiTitle = "Mestreechter merret";
    String poiDate = "26-9-2018";
    String poiAchievement = "Maastricht Pathfinder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonOpenDialog = findViewById(R.id.buttonOpenDialog);

        buttonOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_layout);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView textViewName = dialog.findViewById(R.id.textViewName);
                textViewName.setText(poiTitle);

                TextView textViewContent = dialog.findViewById(R.id.textViewContent);
                textViewContent.setText(poiContent2);

                TextView textViewDate = dialog.findViewById(R.id.textViewDate);
                textViewDate.setText("Visited on " + poiDate);

                TextView textViewAchievement = dialog.findViewById(R.id.textViewAchievement);
                textViewAchievement.setText("Part of " + poiAchievement);


                dialog.show();

                ImageButton imageButtonClose = dialog.findViewById(R.id.imageButtonClose);

                imageButtonClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }
}

// Om fonts overal te laden:
// https://stackoverflow.com/questions/2888508/how-to-change-the-font-on-the-textview
