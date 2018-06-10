package com.lumbralessoftware.voterussia2018.rating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.lumbralessoftware.voterussia2018.R;
import com.lumbralessoftware.voterussia2018.Vote;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lumbralessoftware.voterussia2018.Constants.FIREBASE_ID;
import static com.lumbralessoftware.voterussia2018.Constants.ID_PLAYER;
import static com.lumbralessoftware.voterussia2018.Constants.NAME;
import static com.lumbralessoftware.voterussia2018.Constants.VOTE;

/**
 * Created by javiergonzalezcabezas on 9/6/18.
 */

public class RatingDialogFragment extends DialogFragment implements RatingContract.View {

    @BindView(R.id.rating_dialog_ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.rating_dialog_name_textView)
    TextView nameTextView;

    View view;
    String namePlayer;
    int idPlayer;
    DatabaseReference databaseReference;
    DatabaseReference vote;
    Vote voteData;
    int sumOne, sumTwo, sumThree, sumFour, sumFive;

    public RatingDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static RatingDialogFragment newInstance(int id, String name) {
        RatingDialogFragment frag = new RatingDialogFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putInt(ID_PLAYER, id);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rating_dialog, container);
        idPlayer = getArguments().getInt(ID_PLAYER);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        setTitle();
        getVotes();
    }

    @Override
    public void setTitle() {

        namePlayer = getArguments().getString(NAME, "Enter Name");
        nameTextView.setText(namePlayer);
    }

    @Override
    public void setMessageRating() {
        addValueVote(Math.round(ratingBar.getRating()));
        //textView.setText(getString(R.string.rating_dialog_rating) + ratingBar.getRating());
    }

    @OnClick(R.id.rating_dialog_submit_button)
    public void sumitRating() {
        setMessageRating();
    }

    @Override
    public void showError() {
        Snackbar.make(view, getString(R.string.error), Snackbar.LENGTH_LONG)
                .show();
    }

    private void getVotes() {

        databaseReference = FirebaseDatabase.getInstance().getReference();
        vote = databaseReference.child(VOTE);
        vote.orderByChild(FIREBASE_ID).equalTo(idPlayer).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    voteData = children.getValue(Vote.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showError();
            }
        });
    }

    @Override
    public void setPresenter(RatingContract.Presenter presenter) {

    }

    public void addValueVote(int vote) {
        switch (vote) {
            case 1:
                voteData.setOne(voteData.getOne() + 1);
                voteData.setSum(voteData.getSum() + 1);
                break;
            case 2:
                voteData.setTwo(voteData.getTwo() + 1);
                voteData.setSum(voteData.getSum() + 2);
                break;
            case 3:
                voteData.setThree(voteData.getThree() + 1);
                voteData.setSum(voteData.getSum() + 3);
                break;
            case 4:
                voteData.setFour(voteData.getFour() + 1);
                voteData.setSum(voteData.getSum() + 4);
                break;
            case 5:
                voteData.setFive(voteData.getFive() + 1);
                voteData.setSum(voteData.getSum() + 5);
                break;
        }
    }
}