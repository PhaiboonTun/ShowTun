package phansa.phaiboon.showtun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import phansa.phaiboon.showtun.fragment.ReadEbookFragment;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Add Fragment
        if (savedInstanceState==null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentFragmentService, new ReadEbookFragment())
                    .commit();
        }

    } // onCreate

}// Main Class
