package com.example.yanxia.phonefeaturetest.testactivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.caverock.androidsvg.RenderOptions;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.yanxia.phonefeaturetest.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class GpsTestActivity extends AppCompatActivity {
    private static final String TAG = "GpsTestActivity";

    private static final String COLOR_ENABLE = "#f55b70";
    private static final String COLOR_DISABLE = "#641D1D";

    private SVGImageView puzzleImageView;

    private RenderOptions renderOptions = new RenderOptions();

    private SVG localeSvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps_test);

        puzzleImageView = findViewById(R.id.puzzle_svg_image_view);

        long[] dataArray = new long[9];
        dataArray[3] = 2;
        dataArray[5] = 2;
        dataArray[8] = 2;
        String css = getCssStringFromDataArray(dataArray);
        renderOptions.css(css);

        initLocaleSvg();
    }

    public void updateSvg(View view) {
        localeSvg.renderToPicture(renderOptions);
        puzzleImageView.setSVG(localeSvg);

        // long[] dataArray = new long[9];
        // dataArray[3] = 2;
        // dataArray[5] = 2;
        // dataArray[8] = 2;
        // String css = getCssStringFromDataArray(dataArray);
        //
        // puzzleImageView.setCSS(css);
    }

    private String getCssStringFromDataArray(long[] dataArray) {
        StringBuilder cssString = new StringBuilder();
        for (int i = 0; i < dataArray.length; i++) {
            if (dataArray[i] > 0) {
                String temp = String.format(Locale.getDefault(), ".brick%d { fill:%s; } ", i, COLOR_ENABLE);
                cssString.append(temp);
            } else {
                String temp = String.format(Locale.getDefault(), ".brick%d { fill:%s; } ", i, COLOR_DISABLE);
                cssString.append(temp);
            }
        }
        return cssString.toString();
    }

    private void initLocaleSvg() {
        // Check whether svg attribute is a string.
        // Could be a URL/filename or an SVG itself
        String url = "puzzle_nine.svg";
        if (internalSetImageAsset(url))
            return;
        // Last chance, maybe there is an actual SVG in the string
        // If the SVG is in the string, then we will assume it is not very large, and thus doesn't need to be parsed in the background.
        setFromString(url);
    }

    private boolean internalSetImageAsset(String filename) {
        try {
            InputStream is = getAssets().open(filename);
            new LoadURITask().execute(is);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    private class LoadURITask extends AsyncTask<InputStream, Integer, SVG> {
        protected SVG doInBackground(InputStream... is) {
            try {
                return SVG.getFromInputStream(is[0]);
            } catch (SVGParseException e) {
                Log.e("SVGImageView", "Parse error loading URI: " + e.getMessage());
            } finally {
                try {
                    is[0].close();
                } catch (IOException e) { /* do nothing */ }
            }
            return null;
        }

        protected void onPostExecute(SVG svg) {
            localeSvg = svg;
        }
    }

    private void setFromString(String url) {
        try {
            localeSvg = SVG.getFromString(url);
        } catch (SVGParseException e) {
            // Failed to interpret url as a resource, a filename, or an actual SVG...
            Log.e("SVGImageView", "Could not find SVG at: " + url);
        }
    }
}
