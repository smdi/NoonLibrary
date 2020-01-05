package aidev.com.noonlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class finepopup extends DialogFragment {
    private EditText  delays;
    private Button find;
    private TextView listv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.finepopup, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);



        delays = (EditText) customView.findViewById(R.id.delay);
        find = (Button) customView.findViewById(R.id.findfine);
        listv = (TextView) customView.findViewById(R.id.totalfine);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String ldelay = delays.getText().toString();

                if(ldelay.length()>0){
                    try {
                        int days = Integer.parseInt(ldelay);
                        days = days *10;
                        listv.setText(""+days);
                    }
                    catch (Exception e){}

                }

            }
        });


    }

}