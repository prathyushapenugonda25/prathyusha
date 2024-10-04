import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class QuizApp extends JFrame implements ActionListener {
    private ArrayList<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton;

    public QuizApp() {
        setTitle("Java Quiz");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Java-related quiz questions
        questions = new ArrayList<>();
        questions.add(new QuizQuestion("Which of the following is not a Java feature?", new String[]{
                "Object-Oriented", "Use of Pointers", "Platform Independent", "Portable"}, 1));

        questions.add(new QuizQuestion("What is the default value of a local variable?", new String[]{
                "null", "0", "garbage value", "not assigned"}, 3));

        questions.add(new QuizQuestion("Which package contains the Random class?", new String[]{
                "java.util", "java.lang", "java.awt", "java.io"}, 0));

        questions.add(new QuizQuestion("Which of the following is a marker interface?", new String[]{
                "Runnable", "Serializable", "Comparable", "EventListener"}, 1));

        questions.add(new QuizQuestion("Which of these is a wrapper class?", new String[]{
                "int", "void", "Integer", "float"}, 2));

        questions.add(new QuizQuestion("What is the size of an int data type in Java?", new String[]{
                "16-bit", "32-bit", "64-bit", "8-bit"}, 1));

        questions.add(new QuizQuestion("Which keyword is used to inherit a class in Java?", new String[]{
                "implements", "inherit", "extends", "super"}, 2));

        questions.add(new QuizQuestion("Which method is used to start a thread execution?", new String[]{
                "init()", "start()", "run()", "resume()"}, 1));

        questions.add(new QuizQuestion("Which of these cannot be used for a switch statement?", new String[]{
                "int", "char", "float", "enum"}, 2));

        questions.add(new QuizQuestion("Which of the following is true about JVM?", new String[]{
                "It is a physical machine", "It converts Java bytecode into machine language", "It is a Java compiler", "None of the above"}, 1));

        // Question label
        questionLabel = new JLabel();
        questionLabel.setBounds(30, 30, 400, 20);
        add(questionLabel);

        // Option buttons
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setBounds(30, 60 + (i * 30), 400, 30);
            optionGroup.add(optionButtons[i]);
            add(optionButtons[i]);
        }

        // Next button
        nextButton = new JButton("Next");
        nextButton.setBounds(180, 230, 100, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        displayQuestion();
    }

    private void displayQuestion() {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getQuestion());

        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
        }
        optionGroup.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

            // Check which option is selected
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    if (currentQuestion.isCorrect(i)) {
                        score++;
                    }
                    break;
                }
            }

            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayQuestion();
            } else {
                showResult();
            }
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Your Score: " + score + "/" + questions.size());
        System.exit(0);
    }

    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();
        quizApp.setVisible(true);
    }
    public class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, String[] options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctAnswerIndex;
    }
}

}
