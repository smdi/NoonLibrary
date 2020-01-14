package aidev.com.noonlibrary;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.RandomAccessFile;
import java.util.ArrayList;



public class LibraryBooks extends Fragment {


    NamesStore namesStore= new NamesStore();
    private int instance = 0;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Store> al;
    private SharedPreferences prefs;
    private DatabaseHelper db;
    SQLiteOpenHelper sql;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.librarybooks,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = getActivity().getSharedPreferences(namesStore.getNameInstance(), Context.MODE_PRIVATE);
        instance = prefs.getInt("instance", 0);
        initialiser(view);

        if(instance == 0){dataSource();}
        dataSource1();
    }

    private void dataSource1() {

//        SharedPreferences prefs = getActivity().getSharedPreferences(namesStore.getBookAvailabilitysp(), Context.MODE_PRIVATE);
//
//        al.add( new Initialiser(namesStore.getBook1(),"Semester I",namesStore.getBid1(),R.drawable.c,prefs.getInt(""+namesStore.getBid1(),10)));
//        al.add( new Initialiser(namesStore.getBook2(),"Semester II",namesStore.getBid2(),R.drawable.arduino,prefs.getInt(""+namesStore.getBid2(),10)));
//        al.add( new Initialiser(namesStore.getBook3(),"Semester III",namesStore.getBid3(),R.drawable.cpp,prefs.getInt(""+namesStore.getBid3(),10)));
//        al.add( new Initialiser(namesStore.getBook4(),"Semester IV",namesStore.getBid4(),R.drawable.ds,prefs.getInt(""+namesStore.getBid4(),10)));
//        al.add( new Initialiser(namesStore.getBook5(),"Semester V",namesStore.getBid5(),R.drawable.java,prefs.getInt(""+namesStore.getBid5(),10)));
//        al.add( new Initialiser(namesStore.getBook6(),"Semester VI",namesStore.getBid6(),R.drawable.os,prefs.getInt(""+namesStore.getBid6(),10)));
//        al.add( new Initialiser(namesStore.getBook7(),"Semester VII",namesStore.getBid7(),R.drawable.python,prefs.getInt(""+namesStore.getBid7(),10)));
//        al.add( new Initialiser(namesStore.getBook8(),"Semester VIII",namesStore.getBid8(),R.drawable.algo,prefs.getInt(""+namesStore.getBid8(),10)));

        al = db.getAllNotes();
        Adapter adapter = new Adapter(getActivity(),al);
        recyclerView.setAdapter(adapter);
    }


    private void dataSource() {


        SharedPreferences.Editor editor = getActivity().getSharedPreferences(namesStore.getNameInstance(), Context.MODE_PRIVATE).edit();
        editor.putInt("instance", 1);
        editor.apply();

//        SharedPreferences.Editor editor1 = getActivity().getSharedPreferences(namesStore.getBookAvailabilitysp(), Context.MODE_PRIVATE).edit();
//        editor1.putInt(""+namesStore.getBid1(),10);
//        editor1.putInt(""+namesStore.getBid2(),10);
//        editor1.putInt(""+namesStore.getBid3(),10);
//        editor1.putInt(""+namesStore.getBid4(),10);
//        editor1.putInt(""+namesStore.getBid5(),10);
//        editor1.putInt(""+namesStore.getBid6(),10);
//        editor1.putInt(""+namesStore.getBid7(),10);
//        editor1.putInt(""+namesStore.getBid8(),10);
//        editor1.apply();


        db.insertBook(namesStore.getBid1(),R.drawable.c,namesStore.getBook1(),10,"Semester I");
        db.insertBook(namesStore.getBid2(),R.drawable.arduino,namesStore.getBook2(),10,"Semester II");
        db.insertBook(namesStore.getBid3(),R.drawable.cpp,namesStore.getBook3(),10,"Semester III");
        db.insertBook(namesStore.getBid4(),R.drawable.ds,namesStore.getBook4(),10,"Semester IV");
        db.insertBook(namesStore.getBid5(),R.drawable.java,namesStore.getBook5(),10,"Semester V");
        db.insertBook(namesStore.getBid6(),R.drawable.os,namesStore.getBook6(),10,"Semester VI");
        db.insertBook(namesStore.getBid7(),R.drawable.python,namesStore.getBook7(),10,"Semester VII");
        db.insertBook(namesStore.getBid8(),R.drawable.algo,namesStore.getBook8(),10,"Semester VIII");





    }

    private void initialiser(View view) {

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(false);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        al = new ArrayList<Store>();
        db = new DatabaseHelper(getActivity());


    }
}
