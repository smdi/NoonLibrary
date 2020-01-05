package aidev.com.noonlibrary;

import android.app.Fragment;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;





public class Library extends AppCompatActivity {

    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        initialiser();


    }



    private void initialiser() {

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.library);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            final Fragment[] fragment = {null};

            switch (item.getItemId()) {


                case R.id.library :

                    fragment[0] = new LibraryBooks();
                    break;

                case R.id.returnbook :

                    fragment[0] = new ReturnBooks();
                    break;

                case R.id.fine :

                    fragment[0] = new Fine();
                    break;

                case R.id.booksborrowed :

                    fragment[0] = new BooksId();
                    break;

                case R.id.studentborrowedbooks :

                    fragment[0] = new StudentsId();
                    break;

            }
            return loadFragment(fragment[0]);

        }
    };

    public boolean loadFragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame, fragment).addToBackStack("tag");
            ft.commit();
            return  true;
        }
        return false;

    }

}