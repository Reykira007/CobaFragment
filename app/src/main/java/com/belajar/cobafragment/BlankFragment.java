package com.belajar.cobafragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final int DUA = 0;
    private static final int TIGA = 1;
    private static final int NONE = 2;

    private int pilihan = NONE;

    private static final String PILIH = "choice";

    OnFragmentInteractionListener mListener;

    interface OnFragmentInteractionListener {
        void onRadioButtonChoice(int choice);
    }

    private String mParam1;
    private String mParam2;

    public BlankFragment() {
    }

    public static BlankFragment newInstance(int choice) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putInt(PILIH, choice);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_blank,
                container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int chekedId) {
                        View radioButton = radioGroup.findViewById(chekedId);
                        int index = radioGroup.indexOfChild(radioButton);
                        TextView textView =
                                rootView.findViewById(R.id.fragment_header);
                        switch (index) {
                            case DUA:
                                textView.setText(R.string.benar);
                                pilihan = DUA;
                                mListener.onRadioButtonChoice(DUA);
                                break;
                            case TIGA:
                                textView.setText(R.string.salah);
                                pilihan = TIGA;
                                mListener.onRadioButtonChoice(TIGA);
                                break;
                            default:
                                break;
                        }
                    }
                }
        );
        return rootView;
    }
}