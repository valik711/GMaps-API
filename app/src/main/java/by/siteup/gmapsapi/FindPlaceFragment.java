package by.siteup.gmapsapi;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;

import java.util.ArrayList;

/**
 * Created by valik on 1/23/17.
 */
public class FindPlaceFragment extends Fragment implements View.OnClickListener, View.OnTouchListener{

    Button searchButton;
    EditText requestTextEdit;
    ListView placesListView;
    TableLayout buttonsTable;
    ArrayAdapter<String> adapter;
    ArrayList<String> addresses = new ArrayList<>();

    TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);


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


            buttonsTable = (TableLayout) rootView.findViewById(R.id.butTable);

            ArrayList<Pair<Integer, String>> icons = new ArrayList<>();
            icons.add(new Pair<Integer, String>(R.drawable.transport, "Автомойки"));
            icons.add(new Pair<Integer, String>(R.drawable.animals,"Рыбалка"));
            icons.add(new Pair<Integer, String>(R.drawable.animals1,"Ветеринар"));
            icons.add(new Pair<Integer, String>(R.drawable.carmechanic,"Автосервис"));
            icons.add(new Pair<Integer, String>(R.drawable.dumbbellfortraining, "Фитнесс"));
            icons.add(new Pair<Integer, String>(R.drawable.frontalbussilhouette,"Транспорт"));
            icons.add(new Pair<Integer, String>(R.drawable.game,"Досуг"));
            icons.add(new Pair<Integer, String>(R.drawable.hairdresserscissorsandcomb,"Парикмахер"));
            icons.add(new Pair<Integer, String>(R.drawable.hamburguerdrinkwithstraw,"Фастфуд"));
            icons.add(new Pair<Integer, String>(R.drawable.letter,"Паркинг"));
            icons.add(new Pair<Integer, String>(R.drawable.sleepingbedsilhouette,"Гостиница"));
            icons.add(new Pair<Integer, String>(R.drawable.shopperwithbags,"Магазин"));
            icons.add(new Pair<Integer, String>(R.drawable.subwaysign,"Метро"));
            icons.add(new Pair<Integer, String>(R.drawable.swimmingman,"Бассейн"));
            icons.add(new Pair<Integer, String>(R.drawable.swinggame,"Для детей"));
            icons.add(new Pair<Integer, String>(R.drawable.teacherreading,"Библиотека"));
            icons.add(new Pair<Integer, String>(R.drawable.tooth,"Стоматолог"));
            icons.add(new Pair<Integer, String>(R.drawable.trainonrailroad,"Поезд"));
            icons.add(new Pair<Integer, String>(R.drawable.trialhammer,"Суд"));
            icons.add(new Pair<Integer, String>(R.drawable.tshirtsilhouette,"Одежда"));
            icons.add(new Pair<Integer, String>(R.drawable.vehicle,"Авто"));
            icons.add(new Pair<Integer, String>(R.drawable.frontalbussilhouette,"Еще"));
            icons.add(new Pair<Integer, String>(R.drawable.transport,"и еще"));
            icons.add(new Pair<Integer, String>(R.drawable.animals,"и еще..."));

            int n = 0;
            for (int r = 0; r < 6; ++r)
            {
                TableRow row = new TableRow(getActivity());
                row.setGravity(Gravity.CENTER);
                for (int c = 0; c < 4; ++c)
                {

                    View v = inflater.inflate(R.layout.icon_button, null, false);
                    ImageView placeIcon = (ImageView)v.findViewById(R.id.placeImage);
                    placeIcon.setImageResource(icons.get(n).first);
                    placeIcon.setOnTouchListener(this);
                    TextView placeText = (TextView)v.findViewById(R.id.placeText);
                    placeText.setText(icons.get(n).second);
                    row.addView(v, cellLp);
                    n++;
                }
                buttonsTable.addView(row, rowLp);
            }



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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // executeble code onClick
        return false;
    }
}

