package phansa.phaiboon.showtun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import phansa.phaiboon.showtun.R;
import phansa.phaiboon.showtun.manager.GetAllData;
import phansa.phaiboon.showtun.manager.Myconstant;

/**
 * Created by Samsung on 25/8/2560.
 */

public class ReadEbookFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read_ebook, container, false);
        return view;
    }// OnCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Cerate ListView
        cerateListView();

    } //onActivityCreated

    private void cerateListView() {
        ListView listView = getView().findViewById(R.id.livEbook);
        String tag = "28AugV1";
        Myconstant myconstant = new Myconstant();

        try {

            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(myconstant.getUrlEbookString());
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==>" + strJSON);

        } catch (Exception e) {
            Log.d(tag, "e crateListView ==> " + e.toString());

        }

    } // ceateListView

} //Main Class
