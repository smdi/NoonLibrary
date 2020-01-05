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

import com.sdsmdg.tastytoast.TastyToast;

public class bookidpopup extends DialogFragment {
    private EditText  bid;
    private Button find;
    private String borrowData = "BorrowData";
    private TextView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.boodidpopup, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);



        bid = (EditText) customView.findViewById(R.id.bookidbip);
        find = (Button) customView.findViewById(R.id.findstudents);
        list = (TextView) customView.findViewById(R.id.liststudents);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String lbid = bid.getText().toString();

                if(lbid.length()>0){
//                    Toast.makeText(getActivity(),""+Integer.parseInt(lbid),Toast.LENGTH_SHORT).show();
                    getAllData(Integer.parseInt(lbid));
                }

            }
        });


    }
    private void getAllData( int bid) {

        try{
            SharedPreferences prefs1 = getActivity().getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE);
            String csid = prefs1.getString("sid", "No list available");//"No name defined" is the default value.
            if(csid.length() >1){
                list.setText(csid);
            }
            else{
                TastyToast.makeText(getActivity(), "No list available", TastyToast.LENGTH_LONG, TastyToast.CONFUSING).show();
                list.setText("No list available");
            }
        }catch (Exception e){}



    }
}