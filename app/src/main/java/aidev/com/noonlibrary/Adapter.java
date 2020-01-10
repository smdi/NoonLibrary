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



    @Override
    public int getItemCount() {
        return listitem.size();
    }
}