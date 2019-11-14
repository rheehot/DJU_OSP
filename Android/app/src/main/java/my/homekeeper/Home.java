package my.homekeeper;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;

public class Home extends Fragment {
    final String TAG = "TAG+HomeFragment";
    Button lampButton;
    TextView tempText, humText, dustText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "Create Home Fragment onCreateView");

        lampButton = (Button) view.findViewById(R.id.lampButton);
        tempText = (TextView) view.findViewById(R.id.tempText);
        humText = (TextView) view.findViewById(R.id.humText);
        dustText = (TextView) view.findViewById(R.id.dustText);

        tempText.setText(((MainActivity)MainActivity.context).sensorData[0] + "℃");
        humText.setText(((MainActivity)MainActivity.context).sensorData[1] + "%");
        dustText.setText(((MainActivity)MainActivity.context).sensorData[2] + "㎍/㎥");

        lampButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("전등 ON / OFF");
                builder.setMessage("전등을 키시겠습니까? 끄시겠습니까?");
                builder.setPositiveButton("On", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ((MainActivity)MainActivity.context).turnOn();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                builder.setNegativeButton("Off", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ((MainActivity)MainActivity.context).turnOff();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }
}
