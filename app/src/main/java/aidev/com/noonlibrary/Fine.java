package aidev.com.noonlibrary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;

public class Fine extends Fragment {

    private EditText  delays;
    private Button find;
    private TextView listv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fine,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);

//        finepopup dialogFragment = new finepopup();
//
//        dialogFragment.show(((FragmentActivity)getActivity()).getSupportFragmentManager(), "OpenPopup");


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
