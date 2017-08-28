package phansa.phaiboon.showtun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phansa.phaiboon.showtun.R;

/**
 * Created by Samsung on 28/8/2560.
 */

public class DetailFragment extends Fragment {

    private String urlPDFString;

    public static DetailFragment detailInstance(String strURLpdf) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("PDF", strURLpdf);
        detailFragment.setArguments(bundle);
        return detailFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    } //onCreateView

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Read From Argument
        urlPDFString = getArguments().getString("PDF");
        Log.d("28AubV2", "urlPDF ==> " + urlPDFString);

    }


}//Main Class
