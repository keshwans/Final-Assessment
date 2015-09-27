package nyc.c4q;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

import nyc.c4q.models.Book;
import nyc.c4q.models.Member;


public class LibraryActivity extends Activity {

    public EditText inputParameter;
    public TextView textDisplay;

    LibrarySQLiteOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        inputParameter = (EditText) findViewById(R.id.input_parameter);
        textDisplay = (TextView) findViewById(R.id.text_display);

        dbHelper = LibrarySQLiteOpenHelper.getInstance(getApplicationContext());

        if (!dbHelper.hasData()) {
            dbHelper.populateData();
        }

    }

    public void checkOut(int memberId, int bookId) {
        // TODO This method is called when the member with the given ID checks
        //      out the book with the given ID. Update the system accordingly.
        //      The due date for the book is two weeks from today.
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
        try {
            Member member = dbHelper.loadMemberData(name);

            String memberName = member.getName();
            int memberId = member.getId();
            String city = member.getCity();
            String state = member.getState();
            int dobDay = member.getDobDay();
            int dobMonth = member.getDobMonth();
            int dobYear = member.getDobYear();

            String result = "id: " + String.valueOf(memberId) + "\n" +
                    "name: " + memberName + "\n" +
                    "dob: " + String.valueOf(dobMonth) + "/" + String.valueOf(dobDay)+ "/" + String.valueOf(dobYear)+"\n" +
                    "location: " + city + ", " + state;

            textDisplay.setText(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void button_getBook_onClick(View view) {
        String isbn = inputParameter.getText().toString();

        // TODO Display book information for the book with the given ISBN.
        new BooksDataSeachTask().execute(isbn);

    }

    public void button_getCheckedOut_onClick(View view) {
        String name = inputParameter.getText().toString();

        // TODO Display a list of books that the member with the given name
        //      currently has checked out, ordered by due date, with the
        //      earliest due first.
    }


    private class BooksDataSeachTask extends AsyncTask<String, Void, Book> {
        String isbn;
        Book book;
        @Override
        protected Book doInBackground(String... isbns) {


            try {
                book = dbHelper.loadBookData(isbns[0]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return book;
        }

        @Override
        protected void onPostExecute(Book book) {
            int id = book.getId();
            String title = book.getTitle();
            String author =  book.getAuthor();
            String isbn = book.getIsbn();
            String isbn13 =  book.getIsbn13();
            String publisher = book.getPublisher();
            int publishyear = book.getPublishYear();

            String result = "id: " + String.valueOf(id) + "\n" +
                    "title: " + title + "\n" +
                    "author: " + author + "\n" +
                    "isbn: " + isbn + "\n" +
                    "isbn13: " + isbn13 + "\n" +
                    "publisher: " + publisher + "\n" +
                    "publication year: " + String.valueOf(publishyear);
            textDisplay.setText(result);

        }
    }
}
