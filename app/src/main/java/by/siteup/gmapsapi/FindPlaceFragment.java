package by.siteup.gmapsapi;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;

/**
 * Created by valik on 1/23/17.
 */
public class FindPlaceFragment extends Fragment implements View.OnClickListener{

    Button button;
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
        View rootView = inflater.inflate(R.layout.find_places, container, false);


        if( isAdded()) {
            button = (Button) rootView.findViewById(R.id.button);
            button.setOnClickListener(this);
        }


        return rootView;

    }



    @Override
    public void onClick(View v) {


        EditText ed = (EditText) getView().findViewById(R.id.editText);
        ListView places = (ListView) getView().findViewById(R.id.listView);
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA-SaUHNdfLPrNTvgIhijWV09OIXmthvI4");
        PlacesSearchResponse results = new PlacesSearchResponse();

        try {
            results = PlacesApi.textSearchQuery(context, String.valueOf(ed.getText() + " Минск")).language("ru").await();
            for(PlacesSearchResult r: results.results){
                a.add(r.formattedAddress);
            }


            places.setAdapter(ad);
            InputMethodManager inputManager = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

