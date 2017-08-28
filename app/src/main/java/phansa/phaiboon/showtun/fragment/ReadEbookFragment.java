package phansa.phaiboon.showtun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import phansa.phaiboon.showtun.R;
import phansa.phaiboon.showtun.manager.EbookAdapter;
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

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] nameStings = new String[jsonArray.length()];
            String[] detailStrings = new String[jsonArray.length()];
            String[] imageStrings = new String[jsonArray.length()];
            final String[] pdfStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStings[i] = jsonObject.getString("Name");
                detailStrings[i] = jsonObject.getString("Detail");
                imageStrings[i] = jsonObject.getString("Cover");
                pdfStrings[i] = jsonObject.getString("pdf");

            } //for

            EbookAdapter ebookAdapter = new EbookAdapter(getActivity(),
                    imageStrings, nameStings, detailStrings);
            listView.setAdapter(ebookAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.contentFragmentService, DetailFragment.detailInstance(pdfStrings[i]))
                            .addToBackStack(null)
                            .commit();

                }
            });


        } catch (Exception e) {
            Log.d(tag, "e crateListView ==> " + e.toString());

        }

    } // ceateListView

} //Main Class
