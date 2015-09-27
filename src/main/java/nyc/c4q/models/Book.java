
package nyc.c4q.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "books")
public class Book {

    @SerializedName("id")
    @Expose
    @DatabaseField(id = true)
    private Long id;

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
    private Long publishyear;

    @SerializedName("checkedout")
    @Expose
    @DatabaseField
    private Boolean checkedout;

    @SerializedName("checkedoutby")
    @Expose
    @DatabaseField
    private Long checkedoutby;

    @SerializedName("checkoutdateyear")
    @Expose
    @DatabaseField
    private Long checkoutdateyear;

    @SerializedName("checkoutdatemonth")
    @Expose
    private Long checkoutdatemonth;

    @SerializedName("checkoutdateday")
    @Expose
    @DatabaseField
    private Long checkoutdateday;

    @SerializedName("duedateyear")
    @Expose
    @DatabaseField
    private Long duedateyear;

    @SerializedName("duedatemonth")
    @Expose
    @DatabaseField
    private Long duedatemonth;

    @SerializedName("duedateday")
    @Expose
    @DatabaseField
    private Long duedateday;

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
     *     The publishyear
     */
    public Long getPublishyear() {
        return publishyear;
    }

    /**
     * 
     * @param publishyear
     *     The publishyear
     */
    public void setPublishyear(Long publishyear) {
        this.publishyear = publishyear;
    }

    /**
     * 
     * @return
     *     The checkedout
     */
    public Boolean getCheckedout() {
        return checkedout;
    }

    /**
     * 
     * @param checkedout
     *     The checkedout
     */
    public void setCheckedout(Boolean checkedout) {
        this.checkedout = checkedout;
    }

    /**
     * 
     * @return
     *     The checkedoutby
     */
    public Long getCheckedoutby() {
        return checkedoutby;
    }

    /**
     * 
     * @param checkedoutby
     *     The checkedoutby
     */
    public void setCheckedoutby(Long checkedoutby) {
        this.checkedoutby = checkedoutby;
    }

    /**
     * 
     * @return
     *     The checkoutdateyear
     */
    public Long getCheckoutdateyear() {
        return checkoutdateyear;
    }

    /**
     * 
     * @param checkoutdateyear
     *     The checkoutdateyear
     */
    public void setCheckoutdateyear(Long checkoutdateyear) {
        this.checkoutdateyear = checkoutdateyear;
    }

    /**
     * 
     * @return
     *     The checkoutdatemonth
     */
    public Long getCheckoutdatemonth() {
        return checkoutdatemonth;
    }

    /**
     * 
     * @param checkoutdatemonth
     *     The checkoutdatemonth
     */
    public void setCheckoutdatemonth(Long checkoutdatemonth) {
        this.checkoutdatemonth = checkoutdatemonth;
    }

    /**
     * 
     * @return
     *     The checkoutdateday
     */
    public Long getCheckoutdateday() {
        return checkoutdateday;
    }

    /**
     * 
     * @param checkoutdateday
     *     The checkoutdateday
     */
    public void setCheckoutdateday(Long checkoutdateday) {
        this.checkoutdateday = checkoutdateday;
    }

    /**
     * 
     * @return
     *     The duedateyear
     */
    public Long getDuedateyear() {
        return duedateyear;
    }

    /**
     * 
     * @param duedateyear
     *     The duedateyear
     */
    public void setDuedateyear(Long duedateyear) {
        this.duedateyear = duedateyear;
    }

    /**
     * 
     * @return
     *     The duedatemonth
     */
    public Long getDuedatemonth() {
        return duedatemonth;
    }

    /**
     * 
     * @param duedatemonth
     *     The duedatemonth
     */
    public void setDuedatemonth(Long duedatemonth) {
        this.duedatemonth = duedatemonth;
    }

    /**
     * 
     * @return
     *     The duedateday
     */
    public Long getDuedateday() {
        return duedateday;
    }

    /**
     * 
     * @param duedateday
     *     The duedateday
     */
    public void setDuedateday(Long duedateday) {
        this.duedateday = duedateday;
    }

}
