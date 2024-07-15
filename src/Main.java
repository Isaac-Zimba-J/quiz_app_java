import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main extends JFrame {

    // Questions and answers arrays
    String[] politicsQuestions = {
        "Who was the first President of Zambia after its independence in 1964?",
        "Which political party did Kenneth Kaunda lead?",
        "What is the term limit for the President of Zambia as per the current constitution?",
        "In which year did Zambia transition to a multi-party democracy?"
    };
    String[] politicsAnswers = {
        "C) Kenneth Kaunda",
        "D) United National Independence Party (UNIP)",
        "B) Two terms of five years each",
        "C) 1991"
    };
    String[][] politicsOptions = {
        {
            "A) Frederick Chiluba",
            "B) Hakainde Hichilema",
            "C) Kenneth Kaunda",
            "D) Rupiah Banda"
        },
        {
            "A) Movement for Multi-Party Democracy (MMD)",
            "B) Patriotic Front (PF)",
            "C) United Party for National Development (UPND)",
            "D) United National Independence Party (UNIP)"
        },
        {
            "A) One term of seven years",
            "B) Two terms of five years each",
            "C) Two terms of four years each",
            "D) Three terms of four years each"
        },
        {
            "A) 1964",
            "B) 1980",
            "C) 1991",
            "D) 2001"
        }
    };

    // Questions and answers arrays
    String[] geographyQuestions = {
        "What is the largest waterfall in Zambia, and one of the largest in the world?",
        "Which major African lake forms part of Zambia's border with the Democratic Republic of the Congo?",
        "What is the highest point in Zambia?",
        "What is the predominant vegetation type found in most of Zambia?"
    };
    
    String[] geographyAnswers = {
        "A) Victoria Falls",
        "C) Lake Tanganyika",
        "D) Mafinga Hills",
        "B) Savanna"
    };
    
    String[][] geographyOptions = {
        {
        "A) Victoria Falls",
        "B) Kafue River",
        "C) Kariba Dam",
        "D) Lake Bangweulu"
        },
        {
        "A) Lake Victoria",
        "B) Lake Malawi",
        "C) Lake Tanganyika",
        "D) Lake Kariba"
        },
        {
        "A) Muchinga Escarpment",
        "B) Livingstone Mountains",
        "C) Kafue Flats",
        "D) Mafinga Hills"
        },
        {
        "A) Rainforest",
        "B) Savanna",
        "C) Desert",
        "D) Tundra"
        }
    };
    
    String[] musicAndCultureQuestions = {
        "What is a traditional Zambian drumming ceremony called?",
        "Which Zambian mask is known for its vibrant colors and elaborate decorations?",
        "What is a popular Zambian dish made with cornmeal?",
        "What is Zambia's national holiday celebrating independence?"
    };
    
    String[] musicAndCultureAnswers = {
        "C) Likasa",
        "B) Makishi Mask",
        "A) Nshima",
        "D) Independence Day (October 24th)"
    };
    
    String[][] musicAndCultureOptions = {
        {
        "A) Ingoma",
        "B) Ngoma",
        "C) Likasa",
        "D) Chimba"
        },
        {
        "A) Nyau Mask",
        "B) Makishi Mask",
        "C) Kfwila Mask",
        "D) Mbuya Mask"
        },
        {
        "A) Nshima",
        "B) Ifisashi",
        "C) Kapenta",
        "D) Millionaire Stew"
        },
        {
        "A) Heroes' Day (March 12th)",
        "B) Labour Day (May 1st)",
        "C) Republic Day (July 1st)",
        "D) Independence Day (October 24th)"
        }
    };
  

    private JRadioButton politicsRadioButton;
    private JRadioButton geographyRadioButton;
    private JRadioButton musicRadioButton;
    private JRadioButton cultureRadioButton;

    private JPanel titlePanel;
    private JPanel categoryPanel;
    private JPanel buttonPanel;

    // New fields for quiz functionality
    private int currentQuestionIndex = 0;
    private JFrame quizFrame;
    private JPanel quizPanel;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private String[] currentQuestions, currentAnswers;
    private String[][] currentOptions;
    private ArrayList<Boolean> userAnswers;
    private ArrayList<String> userSelectedAnswers;

    // Main constructor
    public Main() {
        setSize(800, 600);
        setTitle("Zed Trivia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());

        topSection();
        middleSection();
        bottomSection();

        add(titlePanel, BorderLayout.NORTH);
        add(categoryPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void topSection() {
        titlePanel = new JPanel(new GridLayout(2, 1, 0, 10));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("WELCOME TO ZED TRIVIA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));

        JLabel explanationText = new JLabel("Choose a category to start the quiz", SwingConstants.CENTER);
        explanationText.setFont(new Font("Roboto", Font.PLAIN, 18));

        titlePanel.add(titleLabel);
        titlePanel.add(explanationText);
    }

    private void middleSection() {
        categoryPanel = new JPanel(new GridBagLayout());
        categoryPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(5, 0, 5, 0);

        politicsRadioButton = createStyledRadioButton("Politics");
        geographyRadioButton = createStyledRadioButton("Geography");
        musicRadioButton = createStyledRadioButton("Music");
        cultureRadioButton = createStyledRadioButton("Culture");

        ButtonGroup group = new ButtonGroup();
        group.add(politicsRadioButton);
        group.add(geographyRadioButton);
        group.add(musicRadioButton);
        group.add(cultureRadioButton);

        categoryPanel.add(politicsRadioButton, gbc);
        categoryPanel.add(geographyRadioButton, gbc);
        categoryPanel.add(musicRadioButton, gbc);
        categoryPanel.add(cultureRadioButton, gbc);
    }

    private JRadioButton createStyledRadioButton(String text) {
        JRadioButton button = new JRadioButton(text);
        button.setFont(new Font("Roboto", Font.PLAIN, 16));
        button.setPreferredSize(new Dimension(150, 30));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        return button;
    }

    private void bottomSection() {
        buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Roboto", Font.BOLD, 16));
        enterButton.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(enterButton);

        enterButton.addActionListener(e -> enterButtonClicked());
    }

    private void enterButtonClicked() {
        if (politicsRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Politics", politicsQuestions, politicsAnswers, politicsOptions);
        } else if (geographyRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Geography", geographyQuestions, geographyAnswers, geographyOptions);
        } else if (musicRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Music", musicAndCultureQuestions, musicAndCultureAnswers, musicAndCultureOptions);
        } else if (cultureRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Culture", musicAndCultureQuestions, musicAndCultureAnswers, musicAndCultureOptions);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a category.");
        }
    }

    public void hideMainWindow() {
        this.setVisible(false);
    }

    public void createFrame(String categoryName, String[] questions, String[] answers, String[][] options) {
        currentQuestions = questions;
        currentAnswers = answers;
        currentOptions = options;
        currentQuestionIndex = 0;
        userAnswers = new ArrayList<>();
        userSelectedAnswers = new ArrayList<>();

        quizFrame = new JFrame();
        quizFrame.setTitle(categoryName);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.setSize(800, 600);
        quizFrame.setLocationRelativeTo(null);

        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        quizFrame.add(quizPanel, BorderLayout.CENTER);
        quizFrame.add(nextButton, BorderLayout.SOUTH);

        displayQuestion();
        quizFrame.setVisible(true);
    }

    private void displayQuestion() {
        quizPanel.removeAll();
        optionsGroup = new ButtonGroup();

        if (currentQuestionIndex < currentQuestions.length) {
            JLabel questionLabel = new JLabel(currentQuestions[currentQuestionIndex]);
            questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            quizPanel.add(questionLabel);
            quizPanel.add(Box.createRigidArea(new Dimension(0, 10)));

            for (String option : currentOptions[currentQuestionIndex]) {
                JRadioButton optionButton = new JRadioButton(option);
                optionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                optionsGroup.add(optionButton);
                quizPanel.add(optionButton);
            }

            nextButton.setEnabled(true);
        } else {
            showResults();
        }

        quizPanel.revalidate();
        quizPanel.repaint();
    }

    private void checkAnswer() {
        String correctAnswer = currentAnswers[currentQuestionIndex];
        String selectedAnswer = null;

        for (Enumeration<AbstractButton> buttons = optionsGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                selectedAnswer = button.getText();
                break;
            }
        }

        if (selectedAnswer == null) {
            JOptionPane.showMessageDialog(quizFrame, "Please select an answer.");
        } else {
            boolean isCorrect = selectedAnswer.equals(correctAnswer);
            userAnswers.add(isCorrect);
            userSelectedAnswers.add(selectedAnswer);
            nextQuestion();
        }
    }

    private void nextQuestion() {
        currentQuestionIndex++;
        displayQuestion();
    }

    private void showResults() {
        quizPanel.removeAll();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

        JLabel resultLabel = new JLabel("Quiz Results");
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        quizPanel.add(resultLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        int correctCount = 0;
        for (int i = 0; i < currentQuestions.length; i++) {
            JLabel questionLabel = new JLabel((i + 1) + ". " + currentQuestions[i]);
            questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            quizPanel.add(questionLabel);

            JLabel answerLabel = new JLabel("Your answer: " + userSelectedAnswers.get(i));
            answerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            quizPanel.add(answerLabel);

            JLabel correctLabel = new JLabel("Correct answer: " + currentAnswers[i]);
            correctLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            quizPanel.add(correctLabel);

            if (userAnswers.get(i)) {
                correctCount++;
                JLabel resultIcon = new JLabel("✓");
                resultIcon.setForeground(Color.GREEN);
                resultIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
                quizPanel.add(resultIcon);
            } else {
                JLabel resultIcon = new JLabel("✗");
                resultIcon.setForeground(Color.RED);
                resultIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
                quizPanel.add(resultIcon);
            }

            quizPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JLabel scoreLabel = new JLabel("Your score: " + correctCount + " out of " + currentQuestions.length);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        quizPanel.add(scoreLabel);

        nextButton.setText("Finish");
        nextButton.removeActionListener(nextButton.getActionListeners()[0]);
        nextButton.addActionListener(e -> quizFrame.dispose());

        quizPanel.revalidate();
        quizPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}