package by.siteup.gmapsapi;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by valik on 1/23/17.
 */
public class AboutFragment extends Fragment{

    ArrayAdapter<String> ad;
    ArrayList<String> a = new ArrayList<>();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, a);
        ad = arrayAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.about, container, false);
        return rootView;

    }


    public void revome(){
        getActivity().getFragmentManager().beginTransaction().remove(this).commit();
    }



}

