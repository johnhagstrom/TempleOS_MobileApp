package org.templeos.templeos7;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GodWords extends Fragment {

    private ArrayList<String> dictionary;
    private ArrayList<String> words = new ArrayList<String>();
    private String result;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public GodWords() {
        // Required empty public constructor
    }

    public static GodWords newInstance(String param1, String param2) {
        GodWords fragment = new GodWords();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_god_words, container, false);

        textView = (TextView) view.findViewById(R.id.textView_gw);

        dictionary = createDictionary();
        words = getWords();
        result = arrayToString();
        textView.setText("God says...\n\n" + result);

        Button b = (Button) view.findViewById(R.id.button);
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                words = getWords();
                result = arrayToString();
                textView.setText("God says...\n\n" + result);
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // ---------------------------------------------------------------------------------------------
    // Process GodWords stuff
    // ---------------------------------------------------------------------------------------------
    public ArrayList<String> createDictionary(){
        dictionary = new ArrayList<String>();
        BufferedReader dict = null;
        AssetManager am = this.getResources().getAssets();
        try {
            dict = new BufferedReader(new InputStreamReader(am.open("dict.txt")));
            String word;
            while((word = dict.readLine()) != null){
                dictionary.add(word);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dict.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public ArrayList<String> getWords() {
        words.clear();
        int numWordsToGenerate = 32;
        int numWordsInDict = 99171;
        for(int i = 0; i <= numWordsToGenerate; i++) {
            words.add(dictionary.get(1 + (int) (Math.random() * numWordsInDict)));
        }
        return words;
    }

    public String arrayToString() {
        StringBuilder strWords = new StringBuilder();
        for (String value : words) {
            strWords.append(value + " ");
        }
        String text = strWords.toString();
        return text;
    }
}
