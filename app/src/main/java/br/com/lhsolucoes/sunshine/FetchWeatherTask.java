package br.com.lhsolucoes.sunshine;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import br.com.lhsolucoes.sunshine.util.WeatherDataParser;

/**
 * Created by henrique on 01/12/16.
 */

public class FetchWeatherTask extends AsyncTask<String, Void, String[]> {

    private final String LOG_TAG = FetchWeatherTask.class.getSimpleName();
    private ArrayAdapter<String> mForecastAdapter;

    public FetchWeatherTask(ArrayAdapter<String> mForecastAdapter) {
        this.mForecastAdapter = mForecastAdapter;
    }

    @Override
    protected String[] doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        if (params.length == 0) {
            return null;
        }

        String postalCode = params[0];

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            //URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7&APPID="+ BuildConfig.OPEN_WEATHER_MAP_API_KEY);

            int DAYS = 7;

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http");
            builder.authority("api.openweathermap.org");
            builder.appendEncodedPath("data/2.5/forecast/daily");
            builder.appendQueryParameter("q", postalCode);
            builder.appendQueryParameter("mode", "json");
            builder.appendQueryParameter("units", "metric");
            builder.appendQueryParameter("cnt", String.valueOf(DAYS));
            builder.appendQueryParameter("APPID", BuildConfig.OPEN_WEATHER_MAP_API_KEY);

            Uri uri = builder.build();
            URL url = new URL(uri.toString());

            Log.i(LOG_TAG, "URI: " + uri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = null;
            }
            forecastJsonStr = buffer.toString();

            //Log.i(LOG_TAG, "Returned JSON: "+ forecastJsonStr);

            return WeatherDataParser.getWeatherDataFromJson(forecastJsonStr, DAYS);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            forecastJsonStr = null;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String[] result) {
        mForecastAdapter.clear();

        if (result != null) {
            mForecastAdapter.addAll(Arrays.asList(result));
        }
    }
}
