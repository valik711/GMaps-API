package by.siteup.gmapsapi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by valik on 1/26/17.
 */
public class SettingsFragment extends Fragment{

    ArrayAdapter<String> ad;
    ArrayList<String> a = new ArrayList<>();
    Spinner city;



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
        return rootView;

    }


    public void revome(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }



}

