package nyc.c4q;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

        try {
            dbHelper.checkout(memberId, bookId);
        } catch (SQLException e) {
            Toast.makeText(this, "Could not checkout: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public boolean checkIn(int memberId, int bookId) {
        // TODO This method is called when the member with the given ID returns
        //      the book with the given ID. Update the system accordingly. If
        //      the member is returning the book on time, return true. If it's
        //      late, return false.


        try {
            dbHelper.checkin(memberId, bookId);
        } catch (SQLException e) {
            Toast.makeText(this, "Could not checking: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return true;
    }

    public void button_getMember_onClick(View view) {
        String name = inputParameter.getText().toString();
        if (name.trim().equals("")) {
            return;
        }

        // TODO Display member information for the member with the given name.
        try {
            Member member = dbHelper.loadMemberData(name);

            if (member != null) {
                String memberName = member.getName();
                int memberId = member.getId();
                String city = member.getCity();
                String state = member.getState();
                int dobDay = member.getDobDay();
                int dobMonth = member.getDobMonth();
                int dobYear = member.getDobYear();

                String result = "id: " + String.valueOf(memberId) + "\n" +
                        "name: " + memberName + "\n" +
                        "dob: " + String.valueOf(dobMonth) + "/" + String.valueOf(dobDay) + "/" + String.valueOf(dobYear) + "\n" +
                        "location: " + city + ", " + state;

                textDisplay.setText(result);
            } else {
                textDisplay.setText("Member name: " + name + " not found");
            }
        } catch (SQLException e) {
            textDisplay.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void button_getBook_onClick(View view) {
        String isbn = inputParameter.getText().toString();
        if (isbn.trim().equals("")) {
            return;
        }

        // TODO Display book information for the book with the given ISBN.
        new BooksDataSeachTask().execute(isbn);

    }

    public void button_getCheckedOut_onClick(View view) {
        String name = inputParameter.getText().toString();
        if (name.trim().equals("")) {
            return;
        }

        // TODO Display a list of books that the member with the given name
        //      currently has checked out, ordered by due date, with the
        //      earliest due first.


        new BookCheckOutTask().execute(name);

    }


    private class BooksDataSeachTask extends AsyncTask<String, Void, Book> {
        String isbn;
        Book book;

        @Override
        protected Book doInBackground(String... isbns) {
            isbn = isbns[0];

            try {
                book = dbHelper.loadBookData(isbn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return book;
        }

        @Override
        protected void onPostExecute(Book book) {
            if (book != null) {
                int id = book.getId();
                String title = book.getTitle();
                String author = book.getAuthor();
                String isbn = book.getIsbn();
                String isbn13 = book.getIsbn13();
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
            } else {
                textDisplay.setText("Book with isbn=" + isbn + " Not found");
            }
        }
    }

    private class BookCheckOutTask extends AsyncTask<String, Void, ArrayList<Book>> {
        ArrayList<Book> books;
        String memberName;


        @Override
        protected ArrayList<Book> doInBackground(String... memberNames) {
            try {
                memberName = memberNames[0];
                books = (ArrayList<Book>) dbHelper.loadCheckedOutBooks(memberName);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return books;
        }

        private String getFormattedDueDate(Book book) {

            int dueMonth = Integer.valueOf(String.valueOf(book.getDuedateMonth()));
            int dueDate = Integer.valueOf(String.valueOf(book.getDuedateDay()));
            int dueYear = Integer.valueOf(String.valueOf(book.getDuedateYear()));

            return String.format("%04d%02d%02d", dueYear, dueMonth, dueDate);
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            if (books != null && books.size() > 0) {
                String bookResult = "";
                Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(Book lhs, Book rhs) {

                        String lhsDueDate = getFormattedDueDate(lhs);
                        String rhsDueDate = getFormattedDueDate(rhs);
                        return lhsDueDate.compareTo(rhsDueDate);
                    }
                });

                for (int i = 0; i < books.size(); i++) {

                    Book book = books.get(i);

                    int id = book.getId();
                    String title = book.getTitle();
                    String author = book.getAuthor();
                    int checkedoutDateYear = book.getCheckoutdateYear();
                    int checkedoutDateMonth = book.getCheckoutdateMonth();
                    int checkedoutDateDay = book.getCheckoutdateDay();
                    int duedateYear = book.getDuedateYear();
                    int duedateMonth = book.getDuedateMonth();
                    int duedateDay = book.getDuedateDay();

                    String checkeoutDate = String.valueOf(checkedoutDateMonth) + "/" +
                            String.valueOf(checkedoutDateDay) + "/" + String.valueOf(checkedoutDateYear);

                    String dueDate = String.valueOf(duedateMonth) + "/" +
                            String.valueOf(duedateDay) + "/" + String.valueOf(duedateYear);

                    bookResult = bookResult +
                            "\n-----\n" +
                            "title: " + title + "\n" +
                            "author: " + author + "\n" +
                            "checkout date: " + checkeoutDate + "\n" +
                            "due date: " + dueDate;

                }

                String result = "name: " + String.valueOf(memberName) + bookResult;

                textDisplay.setText(result);
            }
            else {
                textDisplay.setText("name: " + memberName + " has no checkouts");
            }
        }
    }
}
