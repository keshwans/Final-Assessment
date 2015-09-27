package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class LibraryActivity extends Activity {

    private LibraryDatabaseHelper dbHelper;

    public EditText inputParameter;
    public TextView textDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        inputParameter = (EditText) findViewById(R.id.input_parameter);
        textDisplay = (TextView) findViewById(R.id.text_display);

        dbHelper = new LibraryDatabaseHelper(getApplicationContext(), getResources(), getPackageName());
        if (!dbHelper.hasData()) {
            dbHelper.populateData();
        }
    }

    public void checkOut(int memberId, int bookId) {
        // TODO This method is called when the member with the given ID checks
        //      out the book with the given ID. Update the system accordingly.
        //      The due date for the book is two weeks from today.

        dbHelper.checkOut(memberId, bookId);

    }

    public boolean checkIn(int memberId, int bookId) {
        // TODO This method is called when the member with the given ID returns
        //      the book with the given ID. Update the system accordingly. If
        //      the member is returning the book on time, return true. If it's
        //      late, return false.

        return false;
    }

    public void button_getMember_onClick(View view) {
        String name = inputParameter.getText().toString();

        // TODO Display member information for the member with the given name.
        String memberInfo = dbHelper.getMember(name);
        textDisplay.setText(memberInfo);

    }

    public void button_getBook_onClick(View view) {
        String isbn = inputParameter.getText().toString();

        // TODO Display book information for the book with the given ISBN.
        String bookInfo = dbHelper.getBook(isbn);
        textDisplay.setText(bookInfo);

    }

    public void button_getCheckedOut_onClick(View view) {
        String name = inputParameter.getText().toString();

        // TODO Display a list of books that the member with the given name
        //      currently has checked out, ordered by due date, with the
        //      earliest due first.
    }

}
