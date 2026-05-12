import java.util.InputMismatchException;
import java.io.Serializable;

public abstract class SpootifyContent implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String title;
    protected double duration;

    public SpootifyContent(String title, double duration){

        if(title.isBlank())
            throw new InputMismatchException("Invalid input");

        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getDuration() {
        return this.duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String toString(){

        return String.format("%s - %.2f minutes", title, duration);
    }
}