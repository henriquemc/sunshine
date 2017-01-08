package br.com.lhsolucoes.sunshine.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.lhsolucoes.sunshine.R;
import br.com.lhsolucoes.sunshine.fragment.ForecastFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new ForecastFragment())
                    .commit();
        }

    }


}
