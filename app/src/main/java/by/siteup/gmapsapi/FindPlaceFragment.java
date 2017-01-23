package by.siteup.gmapsapi;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;

/**
 * Created by valik on 1/23/17.
 */
public class FindPlaceFragment extends Fragment implements View.OnClickListener{

    Button searchButton;
    EditText requestTextEdit;
    ListView placesListView;
    TableLayout buttonsTable;
    ArrayAdapter<String> adapter;
    ArrayList<String> addresses = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, addresses);

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.find_places, container, false);

        ArrayList<Button> buts = new ArrayList<>();

        if( isAdded()) {
            searchButton = (Button) rootView.findViewById(R.id.button);
            searchButton.setOnClickListener(this);
        }
        return rootView;

    }

    @Override
    public void onClick(View v) {
        requestTextEdit = (EditText) getView().findViewById(R.id.editText);
        placesListView = (ListView) getView().findViewById(R.id.listView);
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA-SaUHNdfLPrNTvgIhijWV09OIXmthvI4");
        PlacesSearchResponse apiResponse = new PlacesSearchResponse();

        try {
            apiResponse = PlacesApi.textSearchQuery(context, String.valueOf(requestTextEdit.getText() + " Минск")).language("ru").await();
            for(PlacesSearchResult r: apiResponse.results){
                addresses.add(r.formattedAddress);
            }

            placesListView.setAdapter(adapter);
            InputMethodManager inputManager = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

