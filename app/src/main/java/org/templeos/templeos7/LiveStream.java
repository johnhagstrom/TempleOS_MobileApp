package org.templeos.templeos7;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class LiveStream extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public LiveStream() {
        // Required empty public constructor
    }

    public static LiveStream newInstance(String param1, String param2) {
        LiveStream fragment = new LiveStream();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_live_stream, container, true);

        VideoView videoView = (VideoView)view.findViewById(R.id.videoView1);

        // String testurl = "https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8";
        String url = "http://www.templeos.org/hls/templeos.m3u8";
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);

        MediaController vidControl = new MediaController(getActivity());
        vidControl.setAnchorView(videoView);
        vidControl.setMediaPlayer(videoView);
        vidControl.setEnabled(true);

        videoView.setMediaController(vidControl);
        videoView.requestFocus();
        videoView.start();

        return inflater.inflate(R.layout.fragment_live_stream, container, false);
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
        } else {
            // pass
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
}
