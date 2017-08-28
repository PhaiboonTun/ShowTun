package phansa.phaiboon.showtun.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import phansa.phaiboon.showtun.R;

/**
 * Created by Samsung on 28/8/2560.
 */

public class EbookAdapter extends BaseAdapter{

    private Context context;
    private String[] iconStrins, titleStrins, detailStrings;

    public EbookAdapter(Context context,
                        String[] iconStrins,
                        String[] titleStrins,
                        String[] detailStrings) {
        this.context = context;
        this.iconStrins = iconStrins;
        this.titleStrins = titleStrins;
        this.detailStrings = detailStrings;
    }

    @Override
    public int getCount() {
        return titleStrins.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view1 = layoutInflater.inflate(R.layout.listview_ebook_layout, viewGroup, false);

        //Show Text
        TextView titleTextView = view1.findViewById(R.id.txtTitle);
        TextView detailTextView = view1.findViewById(R.id.txtDetail);

        titleTextView.setText(titleStrins[i]);
        detailTextView.setText(detailStrings[i]);

        //Show Image
        ImageView imageView = view1.findViewById(R.id.imvIcon);
        Picasso.with(context).load(iconStrins[i]).into(imageView);


        return view1;



    }
} //MainClass
