package by.siteup.gmapsapi;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by valik on 1/26/17.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener{

    ArrayAdapter<String> ad;
    ArrayList<String> a = new ArrayList<>();
    Spinner city;
    Button saveBtn;
    TextView t;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        a.add("Минск");
        a.add("Брест");
        a.add("Гродно");
        a.add("Гомель");
        a.add("Могилев");
        a.add("Витебск");

        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, a);
        ad = arrayAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.settings, container, false);

        city = (Spinner) rootView.findViewById(R.id.spinner);
        city.setAdapter(ad);

        SharedPreferences sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();



        ArrayAdapter adapter = (ArrayAdapter) city.getAdapter();
        city.setSelection(adapter.getPosition(sPref.getString("city", "")));
        saveBtn = (Button) rootView.findViewById(R.id.saveBtn);
        t = (TextView) rootView.findViewById(R.id.textView2);
        saveBtn.setOnClickListener(this);

        return rootView;

    }


    public void revome(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }


    @Override
    public void onClick(View v) {
        SharedPreferences sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString("city", city.getSelectedItem().toString());
        ed.commit();

        t.setText(sPref.getString("city", ""));


    }
}

