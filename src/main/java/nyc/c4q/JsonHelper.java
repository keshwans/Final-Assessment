package nyc.c4q;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import nyc.c4q.models.Book;
import nyc.c4q.models.Member;

/**
 * Created by keshwans on 9/27/15.
 */
public class JsonHelper {

    public static ArrayList<Book> loadBookJsonRawUsingGson(Context context, int jsonResource) {

        InputStream raw = context.getResources().openRawResource(jsonResource);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();
        ArrayList<Book> bookResponse = gson.fromJson(rd, new TypeToken<ArrayList<Book>>() {}.getType());
        return bookResponse;
    }

    public static ArrayList<Member> loadMemberJsonRawUsingGson(Context context, int jsonResource) {

        InputStream raw = context.getResources().openRawResource(jsonResource);
        Reader rd = new BufferedReader(new InputStreamReader(raw));
        Gson gson = new Gson();

        ArrayList<Member> memberResponse = gson.fromJson(rd, new TypeToken<ArrayList<Member>>() {}.getType());

        return memberResponse;
    }
}