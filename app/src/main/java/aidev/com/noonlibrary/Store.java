package aidev.com.noonlibrary;

public class Store {
    public static final String TABLE_NAME = "Books";

    public static final String ID = "bookid";
    public static final String NAME = "bookname";
    public static final String IMAGE = "bookimage";
    public static final String AVAILABILITY = "availability";
    public static final String SEM = "sem";

    private int bookId, availability,image;
    private String bookName,sem;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + ID + " INTEGER PRIMARY KEY ,"
                    + IMAGE + " INTEGER ,"
                    + NAME + " TEXT ,"
                    + SEM + " TEXT ,"
                    + AVAILABILITY + " INTEGER"
                    + ")";

    public Store() {
    }

    public Store(int bookId, int image, String bookName, int availability,String sem) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.image = image;
        this.availability =  availability;
        this.sem = sem;
    }

    public int getBookId() {
        return bookId;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setBookId(int bookId) {

        this.bookId = bookId;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}