import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;


public class Main extends JFrame {

    public class Question {
        private String questionText;
        private String[] options;
        private String correctAnswer;
    
        public Question(String questionText, String[] options, String correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    
        public String getQuestionText() {
            return questionText;
        }
    
        public String[] getOptions() {
            return options;
        }
    
        public String getCorrectAnswer() {
            return correctAnswer;
        }
    }  

    private JRadioButton politicsRadioButton;
    private JRadioButton geographyRadioButton;
    private JRadioButton musicRadioButton;
    private JRadioButton cultureRadioButton;

    private JPanel titlePanel;
    private JPanel categoryPanel;
    private JPanel buttonPanel;

    // New fields for quiz functionality
   // private int currentQuestionIndex = 0;
    private JFrame quizFrame;
    private JPanel quizPanel;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
   // private String[] currentQuestions, currentAnswers;
    private ArrayList<Boolean> userAnswers;
    private ArrayList<String> userSelectedAnswers;

    private Timer questionTimer;
    private JPanel timerPanel;
    private JLabel timerLabel;
    private int timeLeft;

    // changed things here
    private Question[] currentQuestions;
    private int currentQuestionIndex = 0;
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
            createFrame("Politics", createPoliticsQuestions());
        } else if (geographyRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Geography", createGeographyQuestions());
        } else if (musicRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Music", createMusicQuestions());
        } else if (cultureRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Culture", createCultureQuestions());
        } else {
            JOptionPane.showMessageDialog(this, "Please select a category.");
        }
    }

    public void hideMainWindow() {
        this.setVisible(false);
    }

    public void createFrame(String categoryName, Question[] questions ) {
        currentQuestions = questions;
        //currentAnswers = answers;
        //currentOptions = options;
        currentQuestionIndex = 0;
        userAnswers = new ArrayList<>();
        userSelectedAnswers = new ArrayList<>();

        quizFrame = new JFrame();
        quizFrame.setTitle(categoryName);
        quizFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quizFrame.setSize(800, 400);
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
    
    int padding = 20;
    quizPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
    quizPanel.setLayout(new BorderLayout()); // Changed to BorderLayout
    
    if (currentQuestionIndex < currentQuestions.length) {
        // Create timer panel
        timerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTimer(g);
            }
        };
        timerPanel.setPreferredSize(new Dimension(60, 60));
        timerLabel = new JLabel("15");
        timerLabel.setForeground(Color.RED);
        timerLabel.setFont(new Font(timerLabel.getFont().getName(), Font.BOLD, 16));
        timerPanel.setLayout(new GridBagLayout());
        timerPanel.add(timerLabel);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        
        Question currentQuestion = currentQuestions[currentQuestionIndex];
        JLabel questionLabel = new JLabel(currentQuestion.getQuestionText());
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(questionLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        for (String option : currentQuestion.getOptions()) {
            JRadioButton optionButton = new JRadioButton(option);
            optionButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            optionsGroup.add(optionButton);
            contentPanel.add(optionButton);
            contentPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        nextButton.setEnabled(true);
        
        quizPanel.add(timerPanel, BorderLayout.NORTH);
        quizPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Start the timer
        startQuestionTimer();
    } else {
        showResults();
    }
    quizPanel.revalidate();
    quizPanel.repaint();
}

private void drawTimer(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    int width = timerPanel.getWidth();
    int height = timerPanel.getHeight();
    int diameter = Math.min(width, height) - 10;
    int x = (width - diameter) / 2;
    int y = (height - diameter) / 2;
    
    // Draw background circle
    g2.setColor(Color.LIGHT_GRAY);
    g2.fillOval(x, y, diameter, diameter);
    
    // Draw progress arc
    g2.setColor(Color.RED);
    double angle = (15 - timeLeft) * 360 / 15.0;
    g2.fill(new Arc2D.Double(x, y, diameter, diameter, 90, -angle, Arc2D.PIE));
    
    // Draw foreground circle
    g2.setColor(Color.WHITE);
    g2.fillOval(x + 5, y + 5, diameter - 10, diameter - 10);
}

private void startQuestionTimer() {
    if (questionTimer != null) {
        questionTimer.stop();
    }
    
    timeLeft = 15;
    questionTimer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            timeLeft--;
            timerLabel.setText(String.valueOf(timeLeft));
            timerPanel.repaint();
            if (timeLeft <= 0) {
                ((Timer)e.getSource()).stop();
                moveToNextQuestion();
            }
        }
    });
    questionTimer.start();
}

private void moveToNextQuestion() {
    if (questionTimer != null) {
        questionTimer.stop();
    }
    
    currentQuestionIndex++;
    displayQuestion();
}


private void checkAnswer() {
    String correctAnswer = currentQuestions[currentQuestionIndex].getCorrectAnswer();
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

        // panel to scroll through
        JPanel resultsJPanel = new JPanel();
        resultsJPanel.setLayout(new BoxLayout(resultsJPanel, BoxLayout.Y_AXIS));

        quizPanel.removeAll();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));

        JLabel resultLabel = new JLabel("Quiz Results");
        resultLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultLabel.setFont(new Font("Roboto", Font.BOLD, 20));
        quizPanel.add(resultLabel);
        quizPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        int correctCount = 0;
        for (int i = 0; i < currentQuestions.length; i++) {
            Question question = currentQuestions[i];
            JLabel questionLabel = new JLabel((i + 1) + ". " + question.getQuestionText());
            questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            quizPanel.add(questionLabel);

            JLabel answerLabel = new JLabel("Your answer: " + userSelectedAnswers.get(i));
            answerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            quizPanel.add(answerLabel);

            JLabel correctLabel = new JLabel("Correct answer: " + question.getCorrectAnswer());
            correctLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            quizPanel.add(correctLabel);

            if (userAnswers.get(i)) {
                correctCount++;
                JLabel resultIcon = new JLabel("✓");
                resultIcon.setForeground(Color.GREEN);
                resultIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
                quizPanel.add(resultIcon);
            } else {
                JLabel resultIcon = new JLabel("✗");
                resultIcon.setForeground(Color.RED);
                resultIcon.setAlignmentX(Component.LEFT_ALIGNMENT);
                quizPanel.add(resultIcon);
            }

            quizPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JLabel scoreLabel = new JLabel("Your score: " + correctCount + " out of " + currentQuestions.length);
        scoreLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreLabel.setFont(new Font("Roboto", Font.BOLD, 18));
        quizPanel.add(scoreLabel);

        nextButton.setText("Finish");
        nextButton.removeActionListener(nextButton.getActionListeners()[0]);
        nextButton.addActionListener(e -> returnToMainMenu());

        JScrollPane scrollPane = new JScrollPane(quizPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        // Remove all components from the quizFrame and add the scroll pane and button separately
        quizFrame.getContentPane().removeAll();
        quizFrame.setLayout(new BorderLayout());
        quizFrame.add(scrollPane, BorderLayout.CENTER);
        quizFrame.add(nextButton, BorderLayout.SOUTH);

        nextButton.setText("Finish");
        nextButton.removeActionListener(nextButton.getActionListeners()[0]);
        nextButton.addActionListener(e -> returnToMainMenu());

        quizFrame.revalidate();
        quizFrame.repaint();
    }

    private void returnToMainMenu() {
        quizFrame.dispose();
        resetMainMenu();
        this.setVisible(true);
    }

    private void resetMainMenu() {
        ButtonGroup categoryGroup = new ButtonGroup();
        categoryGroup.add(politicsRadioButton);
        categoryGroup.add(geographyRadioButton);
        categoryGroup.add(musicRadioButton);
        categoryGroup.add(cultureRadioButton);
        categoryGroup.clearSelection();
    }

    private Question[] createPoliticsQuestions() {
        String[] politicsQuestions = {
            "Who was the first President of Zambia after its independence in 1964?",
            "Which political party did Kenneth Kaunda lead?",
            "What is the term limit for the President of Zambia as per the current constitution?",
            "In which year did Zambia transition to a multi-party democracy?",
            "Who is the current President of Zambia?",
            "Which Zambian President introduced the Structural Adjustment Program?",
            "What is the capital city of Zambia?",
            "Which river forms the border between Zambia and Zimbabwe?",
            "Who was the second President of Zambia?",
            "In which year did Zambia gain independence?",
            "What is the official language of Zambia?",
            "Which party won the Zambian general elections in 2011?",
            "Who was the President of Zambia from 2008 to 2011?",
            "What is the currency of Zambia?",
            "Which Zambian province is famous for the Victoria Falls?",
            "Who was the first female Vice President of Zambia?",
            "Which political party was founded by Michael Sata?",
            "In which year was the Patriotic Front (PF) founded?",
            "Who was the President of Zambia before Hakainde Hichilema?",
            "Which Zambian President died in office in 2014?",
            "Which party did Frederick Chiluba belong to?",
            "What is the highest court in Zambia?",
            "Who was the first Speaker of the National Assembly of Zambia?",
            "Which country borders Zambia to the west?",
            "What is the motto of Zambia?"
        };
        
        String[] politicsAnswers = {
            "C) Kenneth Kaunda",
            "D) United National Independence Party (UNIP)",
            "B) Two terms of five years each",
            "C) 1991",
            "A) Hakainde Hichilema",
            "B) Frederick Chiluba",
            "A) Lusaka",
            "D) Zambezi River",
            "A) Frederick Chiluba",
            "C) 1964",
            "B) English",
            "C) Patriotic Front (PF)",
            "A) Rupiah Banda",
            "C) Zambian Kwacha",
            "D) Southern Province",
            "A) Inonge Wina",
            "C) Patriotic Front (PF)",
            "B) 2001",
            "D) Edgar Lungu",
            "B) Michael Sata",
            "A) Movement for Multi-Party Democracy (MMD)",
            "C) Supreme Court",
            "B) Wesley Nyirenda",
            "D) Angola",
            "A) One Zambia, One Nation"
        };
        
        String[][] politicsOptions = {
            { "A) Frederick Chiluba", "B) Hakainde Hichilema", "C) Kenneth Kaunda", "D) Rupiah Banda" },
            { "A) Movement for Multi-Party Democracy (MMD)", "B) Patriotic Front (PF)", "C) United Party for National Development (UPND)", "D) United National Independence Party (UNIP)" },
            { "A) One term of seven years", "B) Two terms of five years each", "C) Two terms of four years each", "D) Three terms of four years each" },
            { "A) 1964", "B) 1980", "C) 1991", "D) 2001" },
            { "A) Hakainde Hichilema", "B) Edgar Lungu", "C) Rupiah Banda", "D) Michael Sata" },
            { "A) Kenneth Kaunda", "B) Frederick Chiluba", "C) Levy Mwanawasa", "D) Rupiah Banda" },
            { "A) Lusaka", "B) Ndola", "C) Kitwe", "D) Livingstone" },
            { "A) Congo River", "B) Nile River", "C) Limpopo River", "D) Zambezi River" },
            { "A) Frederick Chiluba", "B) Michael Sata", "C) Rupiah Banda", "D) Edgar Lungu" },
            { "A) 1953", "B) 1958", "C) 1964", "D) 1969" },
            { "A) Bemba", "B) English", "C) Nyanja", "D) Tonga" },
            { "A) Movement for Multi-Party Democracy (MMD)", "B) United National Independence Party (UNIP)", "C) Patriotic Front (PF)", "D) United Party for National Development (UPND)" },
            { "A) Rupiah Banda", "B) Frederick Chiluba", "C) Michael Sata", "D) Hakainde Hichilema" },
            { "A) Zambian Dollar", "B) Zambian Pound", "C) Zambian Kwacha", "D) Zambian Shilling" },
            { "A) Copperbelt Province", "B) Eastern Province", "C) Western Province", "D) Southern Province" },
            { "A) Inonge Wina", "B) Edith Nawakwi", "C) Mutale Nalumango", "D) Margaret Mwanakatwe" },
            { "A) Movement for Multi-Party Democracy (MMD)", "B) United National Independence Party (UNIP)", "C) Patriotic Front (PF)", "D) United Party for National Development (UPND)" },
            { "A) 1990", "B) 2001", "C) 2011", "D) 2015" },
            { "A) Michael Sata", "B) Rupiah Banda", "C) Levy Mwanawasa", "D) Edgar Lungu" },
            { "A) Levy Mwanawasa", "B) Michael Sata", "C) Frederick Chiluba", "D) Kenneth Kaunda" },
            { "A) Movement for Multi-Party Democracy (MMD)", "B) United National Independence Party (UNIP)", "C) Patriotic Front (PF)", "D) United Party for National Development (UPND)" },
            { "A) Constitutional Court", "B) Court of Appeal", "C) Supreme Court", "D) High Court" },
            { "A) Robinson Nabulyato", "B) Wesley Nyirenda", "C) Amusaa Mwanamwambwa", "D) Patrick Matibini" },
            { "A) Tanzania", "B) Botswana", "C) Malawi", "D) Angola" },
            { "A) One Zambia, One Nation", "B) Unity and Freedom", "C) Forward with Zambia", "D) Prosperity for All"
            }
        };
    
        Question[] questions = new Question[politicsQuestions.length];
        for (int i = 0; i < politicsQuestions.length; i++) {
            questions[i] = new Question(politicsQuestions[i], politicsOptions[i], politicsAnswers[i]);
        }
        return questions;
    }
    
    private Question[] createGeographyQuestions() {
        String[] geographyQuestions = {
            "What is the capital city of Zambia?",
            "Which of these countries does NOT border Zambia?",
            "What is the largest lake in Zambia?",
            "Which river forms part of Zambia's southern border with Zimbabwe?",
            "What is the highest point in Zambia?",
            "In which province is the Victoria Falls located?",
            "What is the main language spoken in Zambia?",
            "Which city is known as the 'Copperbelt Capital'?",
            "What is the name of the major river that runs through the Kafue National Park?",
            "Which Zambian city is located on the border with the Democratic Republic of Congo?",
            "What is the total area of Zambia in square kilometers?",
            "Which Zambian province is known for its large reserves of copper?",
            "What is the main agricultural product of the Southern Province?",
            "Which town in Zambia is famous for emerald mining?",
            "What is the name of the second largest city in Zambia?",
            "Which Zambian river flows into the Indian Ocean?",
            "What is the main economic activity in the Copperbelt Province?",
            "Which province is the Barotse Floodplain located in?",
            "What is the population of Zambia as of the latest census?",
            "Which Zambian province shares a border with eight other provinces?",
            "What is the name of the famous swamp in Northern Zambia?",
            "Which national park in Zambia is known for its walking safaris?",
            "What is the major export of Zambia?",
            "Which province is home to the Luangwa Valley?",
            "What is the name of the lake formed by the Kariba Dam?"
        };
    
        String[] geographyAnswers = {
            "C) Lusaka",
            "D) Kenya",
            "B) Lake Kariba",
            "A) Zambezi River",
            "D) Mafinga Hills",
            "B) Southern Province",
            "C) Bemba",
            "B) Ndola",
            "A) Kafue River",
            "D) Ndola",
            "C) 752,618 sq km",
            "A) Copperbelt Province",
            "B) Maize",
            "C) Kagem",
            "D) Kitwe",
            "A) Zambezi River",
            "B) Mining",
            "D) Western Province",
            "A) 18 million",
            "C) Central Province",
            "B) Bangweulu Swamps",
            "A) South Luangwa National Park",
            "C) Copper",
            "B) Eastern Province",
            "D) Lake Kariba"
        };
    
        String[][] geographyOptions = {
            { "A) Kitwe", "B) Ndola", "C) Lusaka", "D) Livingstone" },
            { "A) Tanzania", "B) Malawi", "C) Mozambique", "D) Kenya" },
            { "A) Lake Tanganyika", "B) Lake Kariba", "C) Lake Bangweulu", "D) Lake Mweru" },
            { "A) Zambezi River", "B) Congo River", "C) Kafue River", "D) Luangwa River" },
            { "A) Muchinga Mountains", "B) Nyika Plateau", "C) Mulanje Mountain", "D) Mafinga Hills" },
            { "A) Western Province", "B) Southern Province", "C) Eastern Province", "D) Northern Province" },
            { "A) Tonga", "B) Lozi", "C) Bemba", "D) Nyanja" },
            { "A) Lusaka", "B) Ndola", "C) Kitwe", "D) Livingstone" },
            { "A) Kafue River", "B) Zambezi River", "C) Luangwa River", "D) Chambeshi River" },
            { "A) Lusaka", "B) Kitwe", "C) Livingstone", "D) Ndola" },
            { "A) 500,000 sq km", "B) 600,000 sq km", "C) 752,618 sq km", "D) 800,000 sq km" },
            { "A) Copperbelt Province", "B) Eastern Province", "C) Northern Province", "D) Southern Province" },
            { "A) Coffee", "B) Maize", "C) Tobacco", "D) Tea" },
            { "A) Ndola", "B) Kitwe", "C) Kagem", "D) Chingola" },
            { "A) Lusaka", "B) Ndola", "C) Livingstone", "D) Kitwe" },
            { "A) Zambezi River", "B) Kafue River", "C) Luangwa River", "D) Congo River" },
            { "A) Agriculture", "B) Mining", "C) Fishing", "D) Tourism" },
            { "A) Northern Province", "B) Eastern Province", "C) Southern Province", "D) Western Province" },
            { "A) 18 million", "B) 20 million", "C) 16 million", "D) 14 million" },
            { "A) Eastern Province", "B) Northern Province", "C) Central Province", "D) Western Province" },
            { "A) Okavango Delta", "B) Bangweulu Swamps", "C) Barotse Floodplain", "D) Kafue Flats" },
            { "A) South Luangwa National Park", "B) Lower Zambezi National Park", "C) Kafue National Park", "D) Liuwa Plain National Park" },
            { "A) Agriculture", "B) Tourism", "C) Copper", "D) Textiles" },
            { "A) Northern Province", "B) Eastern Province", "C) Southern Province", "D) Central Province" },
            { "A) Lake Tanganyika", "B) Lake Bangweulu", "C) Lake Mweru", "D) Lake Kariba" }
        };
    
        Question[] questions = new Question[geographyQuestions.length];
        for (int i = 0; i < geographyQuestions.length; i++) {
            questions[i] = new Question(geographyQuestions[i], geographyOptions[i], geographyAnswers[i]);
        }
        return questions;
    }
    
    private Question[] createMusicQuestions() {
        String[] musicQuestions = {
            "Which Zambian artist is known as the 'Princess of Peace'?",
            "What genre of music is Zamrock?",
            "Which Zambian musician released the hit song 'Amalume'?",
            "Who is considered the pioneer of Zamrock music in Zambia?",
            "Which Zambian artist is famous for the song 'Toliwe'?",
            "What is the name of the Zambian music award show?",
            "Which artist is known as the 'King of Zambian Hip-Hop'?",
            "Which female artist released the album 'Queen Diva'?",
            "Which Zambian band was prominent in the 1970s for Zamrock music?",
            "Who sang the hit song 'Chikondi'?",
            "Which artist is known for the song 'Vichani'?",
            "What is the name of Macky 2's debut album?",
            "Who is the lead vocalist of the band Witch?",
            "Which artist is known for blending Zambian traditional music with modern genres?",
            "Which Zambian gospel artist released the album 'Mwamba Lesa'?",
            "Who is the artist behind the song 'Fwelela'?",
            "Which Zambian artist is known for the song 'Ba Mai'?",
            "What is the popular music festival held annually in Lusaka?",
            "Which Zambian musician is associated with the hit 'Bwetu Bwetu'?",
            "Who is the artist behind the song 'Kopala Swag'?",
            "Which Zambian female artist is known for the song 'Mutinta'?",
            "What genre is the song 'Wilalila' by James Chamanyazi?",
            "Which Zambian musician is known as the 'Dancehall Magician'?",
            "Who released the song 'Banono'?",
            "What is the stage name of the Zambian artist Kondwani Kaira?"
        };
    
        String[] musicAnswers = {
            "B) Mampi",
            "C) A fusion of traditional Zambian music and psychedelic rock",
            "A) Slap Dee",
            "D) Emmanuel 'Jagari' Chanda",
            "A) Mampi",
            "C) Zambian Music Awards",
            "D) Macky 2",
            "B) Mampi",
            "A) Ngozi Family",
            "C) B1",
            "B) Chef 187",
            "D) Ndimupondo",
            "A) Emmanuel 'Jagari' Chanda",
            "B) Pompi",
            "D) Christine",
            "C) Salma Sky",
            "A) Afunika",
            "B) Stanbic Music Festival",
            "C) Petersen Zagaze",
            "D) Chef 187",
            "A) Wezi",
            "C) Kalindula",
            "B) Dalisoul",
            "A) Bobby East",
            "D) Chef 187"
        };
    
        String[][] musicOptions = {
            { "A) Theresa Ng'ambi", "B) Mampi", "C) Honey Bee", "D) Wezi" },
            { "A) Traditional folk music", "B) Contemporary gospel", "C) A fusion of traditional Zambian music and psychedelic rock", "D) Hip-hop" },
            { "A) Slap Dee", "B) Chef 187", "C) Bobby East", "D) Macky 2" },
            { "A) PK Chishala", "B) Paul Ngozi", "C) Keith Mlevhu", "D) Emmanuel 'Jagari' Chanda" },
            { "A) Mampi", "B) Wezi", "C) Kay Figo", "D) Judy Yo" },
            { "A) Zambia Music Awards", "B) Zambian Annual Music Awards", "C) Zambian Music Awards", "D) National Music Awards" },
            { "A) Slap Dee", "B) Chef 187", "C) Bobby East", "D) Macky 2" },
            { "A) Cleo Ice Queen", "B) Mampi", "C) Bombshell Grenade", "D) Wezi" },
            { "A) Ngozi Family", "B) Witch", "C) Musi-O-Tunya", "D) The Blackfoot" },
            { "A) JK", "B) Dalisoul", "C) B1", "D) Petersen Zagaze" },
            { "A) Slap Dee", "B) Chef 187", "C) Bobby East", "D) Macky 2" },
            { "A) Legendary", "B) Pempelo", "C) Chakolwa Mu Shanty", "D) Ndimupondo" },
            { "A) Emmanuel 'Jagari' Chanda", "B) Paul Ngozi", "C) Rikki Ililonga", "D) Chrissy Zebby Tembo" },
            { "A) Wezi", "B) Pompi", "C) Abel Chungu", "D) Tio" },
            { "A) Ephraim", "B) Kings Malembe", "C) Suwilanji", "D) Christine" },
            { "A) Macky 2", "B) Slap Dee", "C) Salma Sky", "D) Chef 187" },
            { "A) Afunika", "B) B1", "C) Shenky Shugah", "D) Rich Bizzy" },
            { "A) Zambian Music Festival", "B) Stanbic Music Festival", "C) Zambia International Music Festival", "D) Lusaka Music Festival" },
            { "A) Yo Maps", "B) T-Sean", "C) Petersen Zagaze", "D) Cleo Ice Queen" },
            { "A) Slap Dee", "B) Bobby East", "C) Pompi", "D) Chef 187" },
            { "A) Wezi", "B) Cleo Ice Queen", "C) Salma Sky", "D) Bombshell Grenade" },
            { "A) Rumba", "B) Dancehall", "C) Kalindula", "D) Hip-hop" },
            { "A) Afunika", "B) Dalisoul", "C) Shenky Shugah", "D) Rich Bizzy" },
            { "A) Bobby East", "B) Slap Dee", "C) Pompi", "D) Yo Maps" },
            { "A) Bobby East", "B) Slap Dee", "C) T-Sean", "D) Chef 187" }
        };
    
        Question[] questions = new Question[musicQuestions.length];
        for (int i = 0; i < musicQuestions.length; i++) {
            questions[i] = new Question(musicQuestions[i], musicOptions[i], musicAnswers[i]);
        }
        return questions;
    }
    
    private Question[] createCultureQuestions() {
        String[] cultureQuestions = {
            "What is the main staple food in Zambia?",
            "Which traditional ceremony celebrates the Lozi people's move to higher ground?",
            "What is the name of the traditional cloth worn by Zambian women?",
            "Which animal is considered sacred by the Bemba people?",
            "What is the primary language spoken by the Tonga people?",
            "Which festival is celebrated by the Ngoni people in Eastern Zambia?",
            "What is the traditional Zambian dance performed during harvest time?",
            "Which Zambian tribe is known for the Makishi masquerade?",
            "What is the traditional drink made from maize or millet in Zambia?",
            "Which province is the Likumbi Lya Mize festival celebrated?",
            "What is the name of the ceremonial leader of the Bemba people?",
            "Which Zambian ethnic group is famous for their fishing skills?",
            "What is the traditional game played with seeds or stones in Zambia?",
            "Which Zambian ceremony involves the crossing of the Zambezi River?",
            "What is the traditional dish made from groundnuts and vegetables?",
            "Which Zambian tribe is known for their intricate basket weaving?",
            "What is the main purpose of the Ncwala ceremony?",
            "Which Zambian ethnic group predominantly inhabits the Western Province?",
            "What is the traditional music instrument made from animal skin and wood?",
            "Which festival marks the end of the planting season for the Bemba people?",
            "What is the traditional Zambian house made from mud and thatch called?",
            "Which ethnic group celebrates the Kulamba ceremony?",
            "What is the name of the traditional healer in Zambian culture?",
            "Which Zambian tribe celebrates the Mutomboko ceremony?",
            "What is the traditional attire worn by Zambian men during ceremonies?"
        };
    
        String[] cultureAnswers = {
            "A) Nshima",
            "C) Kuomboka",
            "B) Chitenge",
            "D) Crocodile",
            "B) Tonga",
            "A) Ncwala",
            "D) Malipenga",
            "B) Luvale",
            "A) Chibuku",
            "C) North-Western Province",
            "D) Chitimukulu",
            "A) Lozi",
            "C) Mancala",
            "D) Kuomboka",
            "B) Ifisashi",
            "C) Tonga",
            "A) To celebrate the first fruits of the harvest",
            "B) Lozi",
            "D) Drum",
            "A) Chibwela Kumushi",
            "C) Mud and thatch house",
            "D) Chewa",
            "A) Ng'anga",
            "C) Lunda",
            "B) Chitenge"
        };
    
        String[][] cultureOptions = {
            { "A) Nshima", "B) Rice", "C) Cassava", "D) Potatoes" },
            { "A) Ncwala", "B) Kulamba", "C) Kuomboka", "D) Mutomboko" },
            { "A) Kanga", "B) Chitenge", "C) Kitenge", "D) Wrapper" },
            { "A) Lion", "B) Elephant", "C) Python", "D) Crocodile" },
            { "A) Bemba", "B) Tonga", "C) Lozi", "D) Lunda" },
            { "A) Ncwala", "B) Likumbi Lya Mize", "C) Kuomboka", "D) Umutomboko" },
            { "A) Ingoma", "B) Mutomboko", "C) Kalela", "D) Malipenga" },
            { "A) Bemba", "B) Luvale", "C) Tonga", "D) Chewa" },
            { "A) Chibuku", "B) Munkoyo", "C) Maheu", "D) Kachasu" },
            { "A) Eastern Province", "B) Southern Province", "C) North-Western Province", "D) Western Province" },
            { "A) Litunga", "B) Paramount Chief Mpezeni", "C) Chief Mukuni", "D) Chitimukulu" },
            { "A) Lozi", "B) Bemba", "C) Tonga", "D) Lunda" },
            { "A) Igisoro", "B) Mucuba", "C) Mancala", "D) Shisima" },
            { "A) Likumbi Lya Mize", "B) Kulamba", "C) Ncwala", "D) Kuomboka" },
            { "A) Nshima", "B) Ifisashi", "C) Chikanda", "D) Kapenta" },
            { "A) Bemba", "B) Lozi", "C) Tonga", "D) Lunda" },
            { "A) To celebrate the first fruits of the harvest", "B) To honor the ancestors", "C) To mark the end of the hunting season", "D) To commemorate a historical event" },
            { "A) Tonga", "B) Lozi", "C) Bemba", "D) Lunda" },
            { "A) Mbira", "B) Kalimba", "C) Marimba", "D) Drum" },
            { "A) Chibwela Kumushi", "B) Kuomboka", "C) Likumbi Lya Mize", "D) Mutomboko" },
            { "A) Brick house", "B) Wooden house", "C) Mud and thatch house", "D) Stone house" },
            { "A) Lunda", "B) Lozi", "C) Tonga", "D) Chewa" },
            { "A) Ng'anga", "B) Sangoma", "C) Babalawo", "D) Muti" },
            { "A) Bemba", "B) Chewa", "C) Lunda", "D) Lozi" },
            { "A) Suit", "B) Chitenge", "C) Kilt", "D) Dashiki" }
        };
    
        Question[] questions = new Question[cultureQuestions.length];
        for (int i = 0; i < cultureQuestions.length; i++) {
            questions[i] = new Question(cultureQuestions[i], cultureOptions[i], cultureAnswers[i]);
        }
        return questions;
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