package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class Details_Activity extends AppCompatActivity {

    private NeighbourApiService mApiService;

    ImageView               mDetailImage;
    ImageView               mImgAdress;
    ImageView               mImgTel;
    ImageView               mImgMail;
    ImageButton             mButtonBack;
    TextView                mDetailMail;
    TextView                mDetailTel;
    TextView                mDetailAdress;
    TextView                mDetailTitle;
    TextView                mDetailTitle1;
    TextView                mDescriptionTitle;
    TextView                mDescription;
    FloatingActionButton    mFavoriteButton;

    Neighbour               mDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar2);




        mDetailImage = findViewById(R.id.detail_img);
        mImgAdress = findViewById(R.id.img_adress);
        mImgTel = findViewById(R.id.img_tel);
        mImgMail = findViewById(R.id.img_mail);
        mButtonBack = findViewById(R.id.button_back);
        mDetailMail = findViewById(R.id.detail_mail);
        mDetailTel = findViewById(R.id.detail_tel);
        mDetailAdress = findViewById(R.id.detail_adress);
        mDetailTitle = findViewById(R.id.detail_title);
        mDetailTitle1 = findViewById(R.id.detail_title_1);
        mDescriptionTitle = findViewById(R.id.description_title);
        mDescription = findViewById(R.id.description);
        mFavoriteButton = findViewById(R.id.favorite_button);


        Intent intent = getIntent();
        mDetails = getIntent().getParcelableExtra("details");

        mDetailTitle.setText(mDetails.getName());
        mDetailTel.setText(mDetails.getPhoneNumber());
        mDetailAdress.setText(mDetails.getAddress());
        mDetailTitle1.setText(mDetails.getName());
        mDescription.setText(mDetails.getAboutMe());

        Glide.with(this).load(mDetails.getAvatarUrl()).into(mDetailImage);





        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
