package phansa.phaiboon.showtun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import phansa.phaiboon.showtun.R;
import phansa.phaiboon.showtun.manager.Myconstant;

/**
 * Created by Samsung on 28/8/2560.
 */

public class DetailFragment extends Fragment implements OnPageChangeListener, OnLoadCompleteListener{

    private int myIndex;

    public static DetailFragment detailInstance(int index) {

        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Index", index);
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
        myIndex = getArguments().getInt("Index",0);
        Log.d("28AubV2", "myIndex ==> " + myIndex);

    }//on create

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //ShowPDF
        Myconstant myConstant = new Myconstant();
        String[] titlePDFStrings = myConstant.getTitlePDFStrings();

        PDFView pdfView = getView().findViewById(R.id.myPDFViewer);
        pdfView.fromAsset(titlePDFStrings[myIndex])
                .defaultPage(0)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .onPageChange(this)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(getActivity()))
                .load();

    }//on activity create


    @Override
    public void onPageChanged(int page, int pageCount) {

    }

    @Override
    public void loadComplete(int nbPages) {

    }
}//Main Class
