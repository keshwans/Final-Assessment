package nyc.c4q;

import java.util.Comparator;

/**
 * Created by robert on 9/20/15.
 */
public class FirstNameComparator implements Comparator<Person> {
    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public int compare(Person l, Person r) {
        if (l.firstName != r.firstName) {
            return l.firstName.compareTo(r.firstName);
        }
        return l.lastName.compareTo(r.lastName);
    }
}
