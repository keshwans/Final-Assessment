
package nyc.c4q.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "members")
public class Member {

    @SerializedName("id")
    @Expose
    @DatabaseField(id = true)
    private Long id;

    @SerializedName("name")
    @Expose
    @DatabaseField
    private String name;

    @SerializedName("dob_month")
    @Expose
    @DatabaseField
    private Long dobMonth;

    @SerializedName("dob_day")
    @Expose
    private Long dobDay;

    @SerializedName("dob_year")
    @Expose
    @DatabaseField
    private Long dobYear;

    @SerializedName("city")
    @Expose
    @DatabaseField
    private String city;

    @SerializedName("state")
    @Expose
    @DatabaseField
    private String state;

    /**
     * 
     * @return
     *     The id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Long id) {
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
    public Long getDobMonth() {
        return dobMonth;
    }

    /**
     * 
     * @param dobMonth
     *     The dob_month
     */
    public void setDobMonth(Long dobMonth) {
        this.dobMonth = dobMonth;
    }

    /**
     * 
     * @return
     *     The dobDay
     */
    public Long getDobDay() {
        return dobDay;
    }

    /**
     * 
     * @param dobDay
     *     The dob_day
     */
    public void setDobDay(Long dobDay) {
        this.dobDay = dobDay;
    }

    /**
     * 
     * @return
     *     The dobYear
     */
    public Long getDobYear() {
        return dobYear;
    }

    /**
     * 
     * @param dobYear
     *     The dob_year
     */
    public void setDobYear(Long dobYear) {
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

}
