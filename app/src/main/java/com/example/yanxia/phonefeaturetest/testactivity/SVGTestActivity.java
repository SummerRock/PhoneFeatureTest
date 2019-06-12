package com.example.yanxia.phonefeaturetest.testactivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Picture;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.caverock.androidsvg.RenderOptions;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.caverock.androidsvg.SVGParseException;
import com.example.yanxia.phonefeaturetest.R;
import com.example.yanxia.phonefeaturetest.utils.GraphicUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class SVGTestActivity extends AppCompatActivity {
    private static final String TAG = "test_log";

    private static final String COLOR_ENABLE = GraphicUtils.convertColorIntToHexString(Color.CYAN);
    private static final String COLOR_DISABLE = GraphicUtils.convertColorIntToHexString(Color.RED);

    private SVGImageView puzzleImageView;
    private ImageView imageView2;

    private SVG localeSvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg_test);

        puzzleImageView = findViewById(R.id.puzzle_svg_image_view);
        imageView2 = findViewById(R.id.svg_test_image_2);

        initLocaleSvg();
    }

    public void updateWithCss(View view) {
        puzzleImageView.setCSS(getCssString());
    }

    public void updateSvg(View view) {
        RenderOptions renderOptions = new RenderOptions();
        renderOptions.css(getCssString());
        Picture picture = localeSvg.renderToPicture(renderOptions);

        Bitmap bitmap = GraphicUtils.createBitmapFromPicture(picture, picture.getWidth(), picture.getHeight());
        imageView2.setImageBitmap(bitmap);
    }

    private String getCssString() {
        long[] dataArray = new long[9];
        dataArray[3] = 2;
        dataArray[5] = 2;
        dataArray[8] = 2;
        String css = getCssStringFromDataArray(dataArray);
        return css;
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
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            Log.e(TAG, "Could not find SVG at: " + url);
        }
    }
}
