
package nyc.c4q.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "members")
public class Member {

    public static final String COLUMN_NAME_NAME      = "name";
    public static final String COLUMN_NAME_DOB_MONTH = "dob_month";
    public static final String COLUMN_NAME_DOB_DAY   = "dob_day";
    public static final String COLUMN_NAME_DOB_YEAR  = "dob_year";
    public static final String COLUMN_NAME_CITY      = "city";
    public static final String COLUMN_NAME_STATE     = "state";

    @SerializedName("id")
    @Expose
    @DatabaseField(id = true)
    private Integer id;

    @SerializedName("name")
    @Expose
    @DatabaseField (columnName = COLUMN_NAME_NAME)
    private String name;

    @SerializedName("dob_month")
    @Expose
    @DatabaseField (columnName = COLUMN_NAME_DOB_MONTH)
    private Integer dobMonth;

    @SerializedName("dob_day")
    @Expose
    @DatabaseField (columnName = COLUMN_NAME_DOB_DAY)
    private Integer dobDay;

    @SerializedName("dob_year")
    @Expose
    @DatabaseField (columnName = COLUMN_NAME_DOB_YEAR)
    private Integer dobYear;

    @SerializedName("city")
    @Expose
    @DatabaseField (columnName = COLUMN_NAME_CITY)
    private String city;

    @SerializedName("state")
    @Expose
    @DatabaseField (columnName = COLUMN_NAME_STATE)
    private String state;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The dobMonth
     */
    public Integer getDobMonth() {
        return dobMonth;
    }

    /**
     * 
     * @param dobMonth
     *     The dob_month
     */
    public void setDobMonth(Integer dobMonth) {
        this.dobMonth = dobMonth;
    }

    /**
     * 
     * @return
     *     The dobDay
     */
    public Integer getDobDay() {
        return dobDay;
    }

    /**
     * 
     * @param dobDay
     *     The dob_day
     */
    public void setDobDay(Integer dobDay) {
        this.dobDay = dobDay;
    }

    /**
     * 
     * @return
     *     The dobYear
     */
    public Integer getDobYear() {
        return dobYear;
    }

    /**
     * 
     * @param dobYear
     *     The dob_year
     */
    public void setDobYear(Integer dobYear) {
        this.dobYear = dobYear;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }


    public Member() {
    }

    public Member(Integer id, String name, Integer dobMonth, Integer dobDay, Integer dobYear, String city, String state) {
        this.id = id;
        this.name = name;
        this.dobMonth = dobMonth;
        this.dobDay = dobDay;
        this.dobYear = dobYear;
        this.city = city;
        this.state = state;
    }
}
