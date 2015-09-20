package nyc.c4q;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Comparator;

/**
 * Created by robert on 8/30/15.
 */
public class RosterAdapter extends ArrayAdapter<Person> {

    public enum NameDisplay {
        FirstLast, LastFirst
    }

    public NameDisplay nameDisplay;
    public boolean showColor;

    private Comparator<Person> compFirst;
    private Comparator<Person> compLast;

    public RosterAdapter(Context context, int resource, Person[] objects, NameDisplay nameDisplay,
                         boolean showColor) {
        super(context, resource, objects);

        this.nameDisplay = nameDisplay;
        this.showColor = showColor;

        this.compFirst = new FirstNameComparator();
        this.compLast = new LastNameComparator();

        switch (nameDisplay) {
            case FirstLast:
                sort(compFirst);
                break;

            case LastFirst:
                sort(compLast);
                break;

            default:
                throw new RuntimeException("nameDisplay is invalid, " + nameDisplay);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        }
        else {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.listitem_member, null);
        }
        Person person = getItem(position);

        TextView textName  = (TextView) view.findViewById(R.id.text_name);
        TextView textHouse = (TextView) view.findViewById(R.id.text_house);

        textName.setText(getName(person));
        textHouse.setText(getHouse(person));

        if (showColor) {
            view.setBackgroundResource(getColorRes(person));
        }
        else {
            view.setBackgroundResource(0);
        }

        return view;
    }

    public void setColor(boolean showColor) {
        this.showColor = showColor;
        notifyDataSetChanged();
    }

    public void sortByFirstName() {
        this.nameDisplay = NameDisplay.FirstLast;
        sort(compFirst);
        notifyDataSetChanged();
    }

    public void sortByLastName() {
        this.nameDisplay = NameDisplay.LastFirst;
        sort(compLast);
        notifyDataSetChanged();
    }

    private String getName(Person person) {
        switch (nameDisplay) {
            case FirstLast:
                return person.firstName + " " + person.lastName;

            case LastFirst:
                return person.lastName + ", " + person.firstName;

            default:
                throw new RuntimeException("nameDisplay is invalid, " + nameDisplay);
        }
    }

    private String getHouse(Person person) {
        switch (person.house) {
            case Gryffindor:
                return "Gryffindor";

            case Ravenclaw:
                return "Ravenclaw";

            case Hufflepuff:
                return "Hufflepuff";

            case Slytherin:
                return "Slytherin";

            default:
                throw new RuntimeException("House is invalid, " + person.house);
        }
    }

    private int getColorRes(Person person) {
        switch (person.house) {
            case Gryffindor:
                return R.color.gryffindor_red;

            case Ravenclaw:
                return R.color.ravenclaw_blue;

            case Hufflepuff:
                return R.color.hufflepuff_yellow;

            case Slytherin:
                return R.color.slytherin_green;

            default:
                throw new RuntimeException("House is invalid, " + person.house);
        }
    }
}
