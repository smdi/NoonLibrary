package aidev.com.noonlibrary;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Initialiser> listitem;
    private Context context;
    private PopupWindow libraryPopup;


    public Adapter(Context context, List<Initialiser> listitem) {
        this.listitem = listitem;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{



        private TextView bookName, sem, bookId, availability;
        private ImageView bookImage;
        private Button borrow;

        public ViewHolder(View itemView) {
            super(itemView);

            bookName = (TextView) itemView.findViewById(R.id.bookname);
            sem = (TextView) itemView.findViewById(R.id.sem);
            bookId = (TextView) itemView.findViewById(R.id.bookid);
            availability = (TextView) itemView.findViewById(R.id.availabilityno);
            bookImage = (ImageView) itemView.findViewById(R.id.bookimage);
            borrow = (Button) itemView.findViewById(R.id.borrow);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Initialiser homeInitialiser = listitem.get(position);
        holder.bookName.setText(" "+homeInitialiser.getBookName());
        holder.bookId.setText(" "+homeInitialiser.getBookId());
        holder.availability.setText(" "+homeInitialiser.getBookAvalability());
        holder.sem.setText(" "+homeInitialiser.getSem());
        Glide.with(context).load(homeInitialiser.getBookImage()).into(holder.bookImage);

        holder.borrow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                LibraryPopup dialogFragment = new LibraryPopup();

                dialogFragment.show(((FragmentActivity)context).getSupportFragmentManager(), "OpenPopup");

            }
        });

    }

//    private void getAllData(int sid, int bid, int no_od_days) {
//
//
//
//        SharedPreferences prefs = context.getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE);
//        String cbid = prefs.getString("bid", "No");//"No name defined" is the default value.
//
//        SharedPreferences prefs1 = context.getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE);
//        String csid = prefs1.getString("sid", "No");//"No name defined" is the default value.
//
//        SharedPreferences prefs2 = context.getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE);
//        String cdays = prefs2.getString("days", "No");//"No name defined" is the default value.
//
//
//        if(cbid.equals("No")&&csid.equals("No")&&cdays.equals("No")){
//            SharedPreferences.Editor editor = context.getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE).edit();
//            editor.putString("bid", sid+"-");
//            editor.apply();
//
//            SharedPreferences.Editor editor1 = context.getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE).edit();
//            editor1.putString("sid", bid+"-");
//            editor.apply();
//
//            SharedPreferences.Editor editor2 = context.getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE).edit();
//            editor2.putString("days", no_od_days+"-");
//            editor.apply();
//        }
//        else{
//            SharedPreferences.Editor editor = context.getSharedPreferences(sid+borrowData, Context.MODE_PRIVATE).edit();
//            editor.putString("bid", cbid+sid+"-");
//            editor.apply();
//
//            SharedPreferences.Editor editor1 = context.getSharedPreferences(bid+borrowData, Context.MODE_PRIVATE).edit();
//            editor1.putString("sid", csid+bid+"-");
//            editor.apply();
//
//            SharedPreferences.Editor editor2 = context.getSharedPreferences(bid+""+sid, Context.MODE_PRIVATE).edit();
//            editor2.putString("days", cdays+no_od_days+"-");
//            editor.apply();
//        }
//
//        SharedPreferences bookcount = context.getSharedPreferences("BookStore", Context.MODE_PRIVATE);
//        int count = bookcount.getInt(""+bid, 0);
//        count = count-1;
//
//        SharedPreferences.Editor bookcount1 = context.getSharedPreferences("BookStore", Context.MODE_PRIVATE).edit();
//        bookcount1.putInt(""+bid,count);
//        bookcount1.apply();
//
//    }


//    private View inflatePopup() {
//
//        View customView = LayoutInflater.from(context).inflate(R.layout.librarypopup, null);
//
//        libraryPopup = new PopupWindow(
//                customView,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//        );
//
//
//        if(Build.VERSION.SDK_INT>=21){
//            libraryPopup.setElevation(5.0f);
//        }
////        libraryPopup.setOutsideTouchable(true);
//        return customView;
//    }


    @Override
    public int getItemCount() {
        return listitem.size();
    }
}