import java.util.InputMismatchException;

public class SpootifyPodcast extends SpootifyContent {

    private static final long serialVersionUID = 1L;

    private String presenter;
    private String review;
    
    public SpootifyPodcast(
        String title,
        double duration,
        String presenter,
        String review
    ){

        super(title, duration);

        if(
            presenter == null || presenter.isBlank() ||
            review == null || review.isBlank()
        ){
            throw new InputMismatchException("Invalid input");
        }

        this.presenter = presenter;
        this.review = review;
    }

    public String getPresenter() {
        return this.presenter;
    }

    public void setPresenter(String presenter) {

        if(presenter == null || presenter.isBlank())
            throw new InputMismatchException("Invalid input");

        this.presenter = presenter;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {

        if(review == null || review.isBlank())
            throw new InputMismatchException("Invalid input");

        this.review = review;
    }

    public String toString(){

        return String.format(
            "Podcast | Title: %s | Duration: %.2f minutes | Presenter: %s | Description: %s |",
            title,
            duration,
            presenter,
            review
        );
    }
}