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
import android.widget.Toast;

import com.sdsmdg.tastytoast.TastyToast;

public class LibraryPopup extends DialogFragment {
    private EditText sid, bid, no_od_days;
    private Button borrow;
    private String borrowData = "BorrowData";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.librarypopup, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);

        sid = (EditText) customView.findViewById(R.id.studentid);
        bid = (EditText) customView.findViewById(R.id.bookidentry);
        no_od_days = (EditText) customView.findViewById(R.id.noofdays);
        borrow = (Button) customView.findViewById(R.id.buttonborrow);

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String lsid = sid.getText().toString();
                String lbid = bid.getText().toString();
                String lno_od_days = no_od_days.getText().toString();
                if(lsid.length()>0&&lbid.length()>0&&lno_od_days.length()>0){

                    getAllData(Integer.parseInt(lsid),Integer.parseInt(lbid),lno_od_days);
                    dismiss();
                }

            }
        });


    }
    private void getAllData(int sid, int bid, String no_od_days) {

        try{
            SharedPreferences prefs = getActivity().getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE);
            String cbid = prefs.getString("bid", "No");//"No name defined" is the default value.

            SharedPreferences prefs1 = getActivity().getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE);
            String csid = prefs1.getString("sid", "No");//"No name defined" is the default value.

            SharedPreferences prefs2 = getActivity().getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE);
            String cdays = prefs2.getString("days", "No");//"No name defined" is the default value.

            int check =  checkBookTaken(cbid , bid);

            if(check == 0){

                if(cbid.equals("No")&&csid.equals("No")&&cdays.equals("No")){
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE).edit();
                    editor.putString("bid", bid+"-");
                    editor.apply();

                    SharedPreferences.Editor editor1 = getActivity().getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE).edit();
                    editor1.putString("sid", sid+"-");
                    editor1.apply();

                    SharedPreferences.Editor editor2 = getActivity().getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE).edit();
                    editor2.putString("days", no_od_days+"-");
                    editor2.apply();
                }
                else{
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE).edit();
                    editor.putString("bid", cbid+bid+"-");
                    editor.apply();

                    SharedPreferences.Editor editor1 = getActivity().getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE).edit();
                    editor1.putString("sid", csid+sid+"-");
                    editor1.apply();

                    SharedPreferences.Editor editor2 = getActivity().getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE).edit();
                    editor2.putString("days", cdays+no_od_days+"-");
                    editor2.apply();
                }

                SharedPreferences bookcount = getActivity().getSharedPreferences("BookStore", Context.MODE_PRIVATE);
                int count = bookcount.getInt(""+bid, 0);
                count = count-1;

                SharedPreferences.Editor bookcount1 = getActivity().getSharedPreferences("BookStore", Context.MODE_PRIVATE).edit();
                bookcount1.putInt(""+bid,count);
                bookcount1.apply();

                TastyToast.makeText(getActivity(), "Book borrowed", TastyToast.LENGTH_LONG, TastyToast.SUCCESS).show();
//                Toast.makeText(getActivity(),"Book borrowed",Toast.LENGTH_SHORT).show();
            }
            else {
                TastyToast.makeText(getActivity(), "Book can't borrow twice", TastyToast.LENGTH_LONG, TastyToast.CONFUSING).show();
//                Toast.makeText(getActivity(),"Book can't borrow twice",Toast.LENGTH_SHORT).show();
            }


        }catch (Exception e){}


    }

    private int checkBookTaken(String cbid, int bid) {
        int val = 0;
        String str =  new String(cbid);
        String spl[] = str.split("-");
        for(int i = 0 ;i<spl.length;i++){
            if(spl[i].equals(""+bid)){
                val = 1;
            }
        }
        return  val;
    }
}