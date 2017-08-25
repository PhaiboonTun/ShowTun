package phansa.phaiboon.showtun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import phansa.phaiboon.showtun.R;
import phansa.phaiboon.showtun.ServiceActivity;
import phansa.phaiboon.showtun.manager.GetAllData;
import phansa.phaiboon.showtun.manager.MyAlert;

/**
 * Created by Samsung on 23/8/2560.
 */

public class MainFragment extends Fragment{

    //Explicit
    private  String userString, passwordString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    } // onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //NewRegister Controller
        newRegisterController();

        //Login Controller
        loginController();


    }  // onActivityCreated

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText userEditText = getView().findViewById(R.id.edtUser);
                EditText passwordEditText = getView().findViewById(R.id.edtPassword);

                //Get Value From EditText
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.myDialog(getResources().getString(R.string.title_have_space),
                            getResources().getString(R.string.massage_have_space));

                } else {
                    //No Space
                    checkUserAndPass();

                }


            }// On click
        });
    }

    private void checkUserAndPass() {

        String tag = "25AugV1";
        String urlJSON ="http://androidthai.in.th/Tun/getAllUserTun.php";
        String[] columnStrings = new String[]{"id","Name","User","Password","Gender",};
        String[] userStrings = new String[columnStrings.length];
        boolean b = true; // True ==> User False

        try {
            GetAllData getAllData = new GetAllData(getActivity());
            getAllData.execute(urlJSON);
            String strJSON = getAllData.get();
            Log.d(tag, "JSON ==>" + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString(columnStrings[2]))) {
                    b = false;
                    for (int i1=0; i1<columnStrings.length; i1+=1) {
                        userStrings[i1] = jsonObject.getString(columnStrings[i1]);
                        Log.d(tag, "userString[" + i1 + "] ==>" + userStrings[i1]);
                    }
                }// if

            } //for

            if (b) {
                //User False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("User False", "Please Try Again User False");
            } else if (passwordString.equals(userStrings[3])) {
                //Password True
                Toast.makeText(getActivity(), "Welcome " + userStrings[1],
                        Toast.LENGTH_SHORT).show();

                //Intent To Service
                Intent intent = new Intent(getActivity(), ServiceActivity.class);
                intent.putExtra("User", userStrings);
                getActivity().startActivity(intent);
                getActivity().finish();

            } else {
                //Password False
                MyAlert myAlert = new MyAlert(getActivity());
                myAlert.myDialog("Password False","Please Try Again Password False");
            }

        } catch (Exception e) {
            Log.d(tag, "e checkUser ==> " + e.toString());
        }

    } //CheckUserAndPass

    private void newRegisterController() {
        TextView textView = getView().findViewById(R.id.txtNeRegister);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentFragmentMain, new SignUpFragment())
                        .addToBackStack(null)
                        .commit();


            } // onClick
        });
    }
}   //Main Class
