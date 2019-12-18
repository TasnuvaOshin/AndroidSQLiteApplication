package khudrosoft.com.renataapplication.Quiz;

public class QuizData {
    public String question;
    public String opt_one;
    public String opt_two;
    public String opt_three;
    public String opt_four;
    String answer;

    public QuizData() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOpt_one() {
        return opt_one;
    }

    public void setOpt_one(String opt_one) {
        this.opt_one = opt_one;
    }

    public String getOpt_two() {
        return opt_two;
    }

    public void setOpt_two(String opt_two) {
        this.opt_two = opt_two;
    }

    public String getOpt_three() {
        return opt_three;
    }

    public void setOpt_three(String opt_three) {
        this.opt_three = opt_three;
    }

    public String getOpt_four() {
        return opt_four;
    }

    public void setOpt_four(String opt_four) {
        this.opt_four = opt_four;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuizData(String question, String opt_one, String opt_two, String opt_three, String opt_four, String answer) {
        this.question = question;
        this.opt_one = opt_one;
        this.opt_two = opt_two;
        this.opt_three = opt_three;
        this.opt_four = opt_four;
        this.answer = answer;
    }
}
