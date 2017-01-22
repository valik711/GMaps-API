package by.siteup.gmapsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //empty comment

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed = (EditText) findViewById(R.id.editText);
                GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA-SaUHNdfLPrNTvgIhijWV09OIXmthvI4");
                GeocodingResult[] results = new GeocodingResult[0];
                try {
                    results = GeocodingApi.geocode(context, String.valueOf(ed.getText()+" Минск")).language("ru").await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                TextView myTextView = (TextView) findViewById(R.id.tv);
                myTextView.setText(results[0].formattedAddress);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
