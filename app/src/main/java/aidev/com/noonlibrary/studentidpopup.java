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

public class studentidpopup extends DialogFragment {
    private EditText  sid;
    private Button find;
    private String borrowData = "BorrowData";
    private TextView list;
    NamesStore namesStore =  new NamesStore();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.studentidpopup, container,
                false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull final View customView, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(customView, savedInstanceState);



        sid = (EditText) customView.findViewById(R.id.studentidsip);
        find = (Button) customView.findViewById(R.id.findbooks);
        list = (TextView) customView.findViewById(R.id.listbooks);

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String lsid = sid.getText().toString();

                if(lsid.length()>0){
//                    Toast.makeText(getActivity(),""+Integer.parseInt(lsid),Toast.LENGTH_SHORT).show();
                    getAllData(Integer.parseInt(lsid));
                }

            }
        });


    }
    private void getAllData( int sid) {

        try{
            SharedPreferences prefs1 = getActivity().getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE);
            String csid = prefs1.getString("bid", "No list available");//"No name defined" is the default value.
            String s =  new String(csid);
            String spl[] = s.split("-");
            String books = "";
            for(String w :spl){

                books = books + getBookName(w) + ",";
            }
            if(csid.length() >1){
                list.setText(books);
            }
            else{
                TastyToast.makeText(getActivity(), "No list available", TastyToast.LENGTH_LONG, TastyToast.CONFUSING).show();
                list.setText("No list available");
            }


        }
        catch (Exception e){

        }

    }

    private String getBookName(String w) {

        if(w.equals(""+namesStore.getBid1())){
            return namesStore.getBook1();
        }
        else if(w.equals(""+namesStore.getBid2())){
            return namesStore.getBook2();
        }
        else if(w.equals(""+namesStore.getBid3())){
            return namesStore.getBook3();
        }
        else if(w.equals(""+namesStore.getBid4())){
            return namesStore.getBook4();
        }
        else if(w.equals(""+namesStore.getBid5())){
            return namesStore.getBook5();
        }
        else if(w.equals(""+namesStore.getBid6())){
            return namesStore.getBook6();
        }
        else if(w.equals(""+namesStore.getBid7())){
            return namesStore.getBook7();
        }
        else if(w.equals(""+namesStore.getBid8())){
            return namesStore.getBook8();
        }

        return  "No list available";

    }
}