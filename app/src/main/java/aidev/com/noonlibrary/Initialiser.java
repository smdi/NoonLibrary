package aidev.com.noonlibrary;

public class Initialiser {

    private String bookName , sem ;
    private int bookId, bookImage, bookAvalability;


    public Initialiser(String bookName, String sem, int bookId, int bookImage, int bookAvalability) {
        this.bookName = bookName;
        this.sem = sem;
        this.bookId = bookId;
        this.bookImage = bookImage;
        this.bookAvalability = bookAvalability;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }

    public int getBookAvalability() {
        return bookAvalability;
    }

    public void setBookAvalability(int bookAvalability) {
        this.bookAvalability = bookAvalability;
    }
}
