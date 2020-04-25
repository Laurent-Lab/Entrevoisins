package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;

public class Details_Activity extends AppCompatActivity {
    ///
    private NeighbourApiService mApiService;
    List<Neighbour> fNeighbours ;
    //Référencement des mes items
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
    //Référencement d'une variable qui "contient" mes données
    private Neighbour       mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mApiService = DI.getNeighbourApiService();
        //Référencement de mes items
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
        //Récupération de mes données
        Intent intent = getIntent();
        mDetails = getIntent().getParcelableExtra("details");

        mDetailTitle.setText(mDetails.getName());
        mDetailTel.setText(mDetails.getPhoneNumber());
        mDetailAdress.setText(mDetails.getAddress());
        mDetailTitle1.setText(mDetails.getName());
        mDescription.setText(mDetails.getAboutMe());
        mDetailMail.setText(mDetails.getName()+"@gmail.com");
        //Récupération de l'image avec Glide
        Glide.with(this).load(mDetails.getAvatarUrl()).into(mDetailImage);

        mFavoriteButton = findViewById(R.id.favorite_button);
        changeStarGold();
        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mApiService.addFavorisNeighbour(mDetails);
                changeStarGold();
            }
        });
        //Utilisation du bouton back pour revenir sur l'activity précédente (terminer l'activité en cours)
        mButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void changeStarGold() {

        this.fNeighbours = mApiService.getFneighbours();

        if (fNeighbours.contains(mDetails)){
            mFavoriteButton.setImageResource(R.drawable.ic_star_pink_24dp);
        }else {
            changeStarWhite();
        }
    }

    private void changeStarWhite() {

            mFavoriteButton.setImageResource(R.drawable.ic_star_black_24dp);
    }
}
