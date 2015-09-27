
package nyc.c4q.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class Book {

    public Book() {

    }

    public Book(Integer id, String title, String author, String isbn, String isbn13, String publisher, Integer publishYear, Boolean checkedOut, Integer checkedoutBy, Integer checkoutdateYear, Integer checkoutdateMonth, Integer checkoutdateDay, Integer duedateYear, Integer duedateMonth, Integer duedateDay) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.checkedOut = checkedOut;
        this.checkedoutBy = checkedoutBy;
        this.checkoutdateYear = checkoutdateYear;
        this.checkoutdateMonth = checkoutdateMonth;
        this.checkoutdateDay = checkoutdateDay;
        this.duedateYear = duedateYear;
        this.duedateMonth = duedateMonth;
        this.duedateDay = duedateDay;
    }

    public Book(Integer id, String title, String author, String isbn, String isbn13, String publisher, Integer publishYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isbn13 = isbn13;
        this.publisher = publisher;
        this.publishYear = publishYear;
    }

    @SerializedName("id")
    @Expose
    @DatabaseField(id = true)
    private Integer id;

    @SerializedName("title")
    @Expose
    @DatabaseField
    private String title;

    @SerializedName("author")
    @Expose
    @DatabaseField
    private String author;

    @SerializedName("isbn")
    @Expose
    @DatabaseField
    private String isbn;

    @SerializedName("isbn13")
    @Expose
    @DatabaseField
    private String isbn13;

    @SerializedName("publisher")
    @Expose
    @DatabaseField
    private String publisher;

    @SerializedName("publishyear")
    @Expose
    @DatabaseField
    private Integer publishYear;

    @SerializedName("checkedout")
    @Expose
    @DatabaseField
    private Boolean checkedOut;

    @SerializedName("checkedoutby")
    @Expose
    @DatabaseField
    private Integer checkedoutBy;

    @SerializedName("checkoutdateyear")
    @Expose
    @DatabaseField
    private Integer checkoutdateYear;

    @SerializedName("checkoutdatemonth")
    @Expose
    @DatabaseField
    private Integer checkoutdateMonth;

    @SerializedName("checkoutdateday")
    @Expose
    @DatabaseField
    private Integer checkoutdateDay;

    @SerializedName("duedateyear")
    @Expose
    @DatabaseField
    private Integer duedateYear;

    @SerializedName("duedatemonth")
    @Expose
    @DatabaseField
    private Integer duedateMonth;

    @SerializedName("duedateday")
    @Expose
    @DatabaseField
    private Integer duedateDay;

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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * 
     * @param isbn
     *     The isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 
     * @return
     *     The isbn13
     */
    public String getIsbn13() {
        return isbn13;
    }

    /**
     * 
     * @param isbn13
     *     The isbn13
     */
    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The publishYear
     */
    public Integer getPublishYear() {
        return publishYear;
    }

    /**
     * 
     * @param publishYear
     *     The publishYear
     */
    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * 
     * @return
     *     The checkedOut
     */
    public Boolean getCheckedOut() {
        return checkedOut;
    }

    /**
     * 
     * @param checkedOut
     *     The checkedOut
     */
    public void setCheckedOut(Boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    /**
     * 
     * @return
     *     The checkedoutBy
     */
    public Integer getCheckedoutBy() {
        return checkedoutBy;
    }

    /**
     * 
     * @param checkedoutBy
     *     The checkedoutBy
     */
    public void setCheckedoutBy(Integer checkedoutBy) {
        this.checkedoutBy = checkedoutBy;
    }

    /**
     * 
     * @return
     *     The checkoutdateYear
     */
    public Integer getCheckoutdateYear() {
        return checkoutdateYear;
    }

    /**
     * 
     * @param checkoutdateYear
     *     The checkoutdateYear
     */
    public void setCheckoutdateYear(Integer checkoutdateYear) {
        this.checkoutdateYear = checkoutdateYear;
    }

    /**
     * 
     * @return
     *     The checkoutdateMonth
     */
    public Integer getCheckoutdateMonth() {
        return checkoutdateMonth;
    }

    /**
     * 
     * @param checkoutdateMonth
     *     The checkoutdateMonth
     */
    public void setCheckoutdateMonth(Integer checkoutdateMonth) {
        this.checkoutdateMonth = checkoutdateMonth;
    }

    /**
     * 
     * @return
     *     The checkoutdateDay
     */
    public Integer getCheckoutdateDay() {
        return checkoutdateDay;
    }

    /**
     * 
     * @param checkoutdateDay
     *     The checkoutdateDay
     */
    public void setCheckoutdateDay(Integer checkoutdateDay) {
        this.checkoutdateDay = checkoutdateDay;
    }

    /**
     * 
     * @return
     *     The duedateYear
     */
    public Integer getDuedateYear() {
        return duedateYear;
    }

    /**
     * 
     * @param duedateYear
     *     The duedateYear
     */
    public void setDuedateYear(Integer duedateYear) {
        this.duedateYear = duedateYear;
    }

    /**
     * 
     * @return
     *     The duedateMonth
     */
    public Integer getDuedateMonth() {
        return duedateMonth;
    }

    /**
     * 
     * @param duedateMonth
     *     The duedateMonth
     */
    public void setDuedateMonth(Integer duedateMonth) {
        this.duedateMonth = duedateMonth;
    }

    /**
     * 
     * @return
     *     The duedateDay
     */
    public Integer getDuedateDay() {
        return duedateDay;
    }

    /**
     * 
     * @param duedateDay
     *     The duedateDay
     */
    public void setDuedateDay(Integer duedateDay) {
        this.duedateDay = duedateDay;
    }

}
