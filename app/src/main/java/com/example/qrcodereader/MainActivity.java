package com.example.qrcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
{
    private Button buttonScan;
    private EditText etName, etAddress; // in global section

    // ZXing lib linker
    private IntentIntegrator qrScan = new IntentIntegrator(this);
    // note the imports should be added by AS automatically.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link code
        buttonScan = findViewById(R.id.scanButton);
        etName = findViewById(R.id.textTitle);
        etAddress = findViewById(R.id.textAddress);
    }

    //In the doScan method for the button, start the integrator
    /*
     * Do the scanning
     */
    public void doScan(View view) {
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                    etName.setText(obj.getString("title"));
                    etAddress.setText(obj.getString("website"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //Event handler for the click
    public void doLoadSite(View view)
    {
        //Get the url from the edit text
        String url = String.valueOf(etAddress.getText());
        //Create a new intent
        Intent webViewPage = new Intent(this, WebViewer.class);
        //Pass the site's url to be loaded by the other view
        webViewPage.putExtra("siteUrl", url);
        startActivity(webViewPage);
    }
}