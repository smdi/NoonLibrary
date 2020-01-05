package aidev.com.noonlibrary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;

public class StudentsId extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.studentid,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        studentidpopup dialogFragment = new studentidpopup();
        dialogFragment.show(((FragmentActivity)getActivity()).getSupportFragmentManager(), "OpenPopup");

    }
}
