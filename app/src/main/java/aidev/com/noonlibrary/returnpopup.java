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

import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class returnpopup extends DialogFragment {

    private EditText sid, bid, no_od_days;
    private Button borrow;
    private String borrowData = "BorrowData";
    private TextView fine;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.returnpopup, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);


        sid = (EditText) customView.findViewById(R.id.studentidrp);
        bid = (EditText) customView.findViewById(R.id.bookidentryrp);
        no_od_days = (EditText) customView.findViewById(R.id.noofdaysrp);
        borrow = (Button) customView.findViewById(R.id.returnrp);
        fine = (TextView) customView.findViewById(R.id.totalfinerp);

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lsid = sid.getText().toString();
                String lbid = bid.getText().toString();
                String lno_od_days = no_od_days.getText().toString();


                if(lsid.length()>0&&lbid.length()>0&&lno_od_days.length()>0){
                    getAllData(Integer.parseInt(lsid),Integer.parseInt(lbid),lno_od_days);
                }
            }
        });


    }
    private void getAllData(int sid, int bid, String d1) {

        try{
            SharedPreferences prefs = getActivity().getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE);
            String cbid = prefs.getString("bid", "No");//"No name defined" is the default value.

            SharedPreferences prefs1 = getActivity().getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE);
            String csid = prefs1.getString("sid", "No");//"No name defined" is the default value.

            SharedPreferences prefs2 = getActivity().getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE);
            String cdays = prefs2.getString("days", "No");//"No name defined" is the default value.



            if(cbid.equals("No")&&csid.equals("No")&&cdays.equals("No")){
                TastyToast.makeText(getActivity(), "No record found", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
            }
            else{

                int val = -1;
                String str =  new String(cbid);
                String str2 =  new String(csid);
                String spl[] = str.split("-");
                for(int i = 0 ;i<spl.length;i++){
                    if(spl[i].equals(""+bid)){

                        val = i;
                    }
                }

                if(val>-1){
                    String str1 = new String(cdays);
                    String spl1[] = str1.split("-");
                    String d2 = spl1[val];

                    int days = getDays(d1,d2);

//                    Toast.makeText(getActivity(),""+days,Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE).edit();
                    editor.putString("bid", str.replace(""+bid+"-",""));
                    editor.apply();

                    SharedPreferences.Editor editor1 = getActivity().getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE).edit();
                    editor1.putString("sid", str2.replace(""+sid+"-",""));
                    editor1.apply();

                    SharedPreferences.Editor editor2 = getActivity().getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE).edit();
                    editor2.putString("days", str1.replace(""+d2+"-",""));
                    editor2.apply();

                    SharedPreferences bookcount = getActivity().getSharedPreferences("BookStore", Context.MODE_PRIVATE);
                    int count = bookcount.getInt(""+bid, 0);
                    count = count+1;

                    SharedPreferences.Editor bookcount1 = getActivity().getSharedPreferences("BookStore", Context.MODE_PRIVATE).edit();
                    bookcount1.putInt(""+bid,count);
                    bookcount1.apply();

                    if(days>30){
                        days = days *10;
                        fine.setText(""+days);
                    }
                    else {
                        fine.setText("No fine");
                    }

//                    Toast.makeText(getActivity(),"Return successful",Toast.LENGTH_SHORT).show();
                    TastyToast.makeText(getActivity(), "Return successful", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
                }
                else {
                    TastyToast.makeText(getActivity(), "No record found", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
                }


            }

        }catch (Exception e){}

    }

    private int getDays(String d1, String d2) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

        int days = 0;

        try {
            Date date1 = myFormat.parse(d1);
            Date date2 = myFormat.parse(d2);
            long diff = date2.getTime() - date1.getTime();
            days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
}