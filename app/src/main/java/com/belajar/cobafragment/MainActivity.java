package com.belajar.cobafragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements BlankFragment.OnFragmentInteractionListener {
    private Button mButton;
    private boolean isFragmentDisplayed = false;

    static final String STATE_FRAGMENT = "state_of_fragment";
    static final String STATE_CHOICE = "user_choice";

    private int mRadioButtonChoice = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = findViewById(R.id.btn_mulai);
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean((STATE_FRAGMENT));
            mRadioButtonChoice = savedInstanceState.getInt(STATE_CHOICE);
            if (isFragmentDisplayed) {
                mButton.setText(R.string.close);
            }
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFragmentDisplayed) {
                    displayFragment();
                } else {
                    closeFragment();
                }
            }
        });
    }

    public void displayFragment() {
        BlankFragment blankFragment =
                BlankFragment.newInstance(mRadioButtonChoice);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.add(R.id.fragment,
                blankFragment).addToBackStack(null).commit();

        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        BlankFragment simpleFragment = (BlankFragment) fragmentManager
                .findFragmentById(R.id.fragment);
        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        mButton.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        mRadioButtonChoice = choice;
        Toast.makeText(this, "choice is" + Integer.toString(choice),
                Toast.LENGTH_SHORT).show();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        savedInstanceState.putInt(STATE_CHOICE, mRadioButtonChoice);
        super.onSaveInstanceState(savedInstanceState);
    }
}