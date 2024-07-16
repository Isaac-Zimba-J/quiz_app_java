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

    // public class Question {
    //     private String questionText;
    //     private String[] options;
    //     private String correctAnswer;
    
    //     public Question(String questionText, String[] options, String correctAnswer) {
    //         this.questionText = questionText;
    //         this.options = options;
    //         this.correctAnswer = correctAnswer;
    //     }
    
    //     public String getQuestionText() {
    //         return questionText;
    //     }
    
    //     public String[] getOptions() {
    //         return options;
    //     }
    
    //     public String getCorrectAnswer() {
    //         return correctAnswer;
    //     }
    // }
    

    // Questions and answers arrays politics
    String[] politicsQuestions = {
        "Who was the first President of Zambia after its independence in 1964?",
        "Which political party did Kenneth Kaunda lead?",
        "What is the term limit for the President of Zambia as per the current constitution?",
        "In which year did Zambia transition to a multi-party democracy?",
        "Who succeeded Kenneth Kaunda as President of Zambia in 1991?",
        "Which party led Zambia into independence?",
        "Who is the current President of Zambia as of 2023?",
        "In which year was the Patriotic Front (PF) founded?",
        "Who was Zambia's first female Vice President?",
        "What is the name of Zambia's parliament?",
        "How many provinces does Zambia have?",
        "Who was the third President of Zambia?",
        "In which year was the Movement for Multi-Party Democracy (MMD) formed?",
        "Who was the leader of the United Party for National Development (UPND) before Hakainde Hichilema?",
        "What was Kenneth Kaunda's political philosophy called?",
        "Which Zambian President died while in office in 2008?",
        "Who was Zambia's Vice President under Frederick Chiluba?",
        "In which year was the Patriotic Front (PF) first elected to power?",
        "Who was Zambia's President from 2015 to 2021?",
        "What is the minimum age requirement to run for President in Zambia?",
        "Which Zambian President introduced the controversial 'Third Term' debate?",
        "Who was the first Zambian President to be elected after a predecessor died in office?",
        "In which year did Zambia return to a multi-party system after being a one-party state?",
        "Who was Zambia's Minister of Finance under President Hakainde Hichilema?",
        "What is the term length for members of the Zambian National Assembly?"
    };
    
    String[] politicsAnswers = {
        "C) Kenneth Kaunda",
        "D) United National Independence Party (UNIP)",
        "B) Two terms of five years each",
        "C) 1991",
        "A) Frederick Chiluba",
        "D) United National Independence Party (UNIP)",
        "D) Hakainde Hichilema",
        "B) 2001",
        "C) Inonge Wina",
        "A) National Assembly",
        "C) 10",
        "B) Levy Mwanawasa",
        "A) 1990",
        "C) Anderson Mazoka",
        "B) Humanism",
        "D) Levy Mwanawasa",
        "A) Christon Tembo",
        "C) 2011",
        "B) Edgar Lungu",
        "D) 35 years",
        "A) Frederick Chiluba",
        "C) Rupiah Banda",
        "B) 1991",
        "A) Situmbeko Musokotwane",
        "C) 5 years"
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
        },
        {
            "A) Frederick Chiluba",
            "B) Levy Mwanawasa",
            "C) Rupiah Banda",
            "D) Michael Sata"
        },
        {
            "A) Movement for Multi-Party Democracy (MMD)",
            "B) Patriotic Front (PF)",
            "C) United Party for National Development (UPND)",
            "D) United National Independence Party (UNIP)"
        },
        {
            "A) Edgar Lungu",
            "B) Michael Sata",
            "C) Rupiah Banda",
            "D) Hakainde Hichilema"
        },
        {
            "A) 1991",
            "B) 2001",
            "C) 2006",
            "D) 2011"
        },
        {
            "A) Edith Nawakwi",
            "B) Mutale Nalumango",
            "C) Inonge Wina",
            "D) Nkandu Luo"
        },
        {
            "A) National Assembly",
            "B) House of Representatives",
            "C) Congress",
            "D) Parliament House"
        },
        {
            "A) 8",
            "B) 9",
            "C) 10",
            "D) 11"
        },
        {
            "A) Frederick Chiluba",
            "B) Levy Mwanawasa",
            "C) Rupiah Banda",
            "D) Michael Sata"
        },
        {
            "A) 1990",
            "B) 1991",
            "C) 1992",
            "D) 1993"
        },
        {
            "A) Sakwiba Sikota",
            "B) Charles Milupi",
            "C) Anderson Mazoka",
            "D) Bob Sichinga"
        },
        {
            "A) African Socialism",
            "B) Humanism",
            "C) Zambian Nationalism",
            "D) One Zambia, One Nation"
        },
        {
            "A) Frederick Chiluba",
            "B) Michael Sata",
            "C) Rupiah Banda",
            "D) Levy Mwanawasa"
        },
        {
            "A) Christon Tembo",
            "B) Godfrey Miyanda",
            "C) Enoch Kavindele",
            "D) Nevers Mumba"
        },
        {
            "A) 2001",
            "B) 2006",
            "C) 2011",
            "D) 2016"
        },
        {
            "A) Michael Sata",
            "B) Edgar Lungu",
            "C) Rupiah Banda",
            "D) Guy Scott"
        },
        {
            "A) 18 years",
            "B) 21 years",
            "C) 30 years",
            "D) 35 years"
        },
        {
            "A) Frederick Chiluba",
            "B) Levy Mwanawasa",
            "C) Rupiah Banda",
            "D) Edgar Lungu"
        },
        {
            "A) Guy Scott",
            "B) Edgar Lungu",
            "C) Rupiah Banda",
            "D) Michael Sata"
        },
        {
            "A) 1990",
            "B) 1991",
            "C) 1992",
            "D) 1993"
        },
        {
            "A) Situmbeko Musokotwane",
            "B) Felix Mutati",
            "C) Margaret Mwanakatwe",
            "D) Bwalya Ng'andu"
        },
        {
            "A) 3 years",
            "B) 4 years",
            "C) 5 years",
            "D) 6 years"
        }
    };


    // Questions and answers arrays geography 
    String[] geographyQuestions ={
        "What is the largest waterfall in Zambia, and one of the largest in the world?",
        "Which major African lake forms part of Zambia's border with the Democratic Republic of the Congo?",
        "What is the highest point in Zambia?",
        "What is the predominant vegetation type found in most of Zambia?",
        "What is the capital city of Zambia?",
        "Which river is the longest in Zambia?",
        "What major wildlife reserve is located in eastern Zambia?",
        "What significant river flows through the Zambezi River basin?",
        "Which province is known for its rich copper deposits?",
        "What is the name of the large artificial lake on the Zambezi River?",
        "Which Zambian city is known for being a major mining center?",
        "What natural region covers much of the Zambian plateau?",
        "Which river forms a natural border between Zambia and Zimbabwe?",
        "What mountain range lies along the northeastern border of Zambia?",
        "What is the main agricultural product grown in the Southern Province of Zambia?",
        "What is the main river that flows through Lusaka, the capital city?",
        "Which national park is known for its population of white rhinos?",
        "What is the name of the waterfall located near the town of Mpika?",
        "What is the largest province in Zambia by area?",
        "Which Zambian lake is known for its fisheries and birdlife?",
        "What is the main source of hydroelectric power in Zambia?",
        "Which province is home to the Barotse Floodplain?",
        "What is the major export crop of the Central Province?",
        "What significant swamp area is located in northern Zambia?",
        "What is the name of the plateau that covers much of central and southern Zambia?"
    };
    
    String[] geographyAnswers = {
        "A) Victoria Falls",
        "C) Lake Tanganyika",
        "D) Mafinga Hills",
        "B) Savanna",
        "B) Lusaka",
        "A) Zambezi River",
        "D) South Luangwa National Park",
        "A) Zambezi River",
        "B) Copperbelt",
        "C) Lake Kariba",
        "D) Kitwe",
        "B) Miombo Woodland",
        "A) Zambezi River",
        "C) Muchinga Mountains",
        "D) Maize",
        "B) Chongwe River",
        "A) Mosi-oa-Tunya National Park",
        "C) Kundalila Falls",
        "B) Western Province",
        "D) Lake Bangweulu",
        "A) Kariba Dam",
        "C) Western Province",
        "B) Tobacco",
        "A) Bangweulu Swamps",
        "D) Central Plateau"
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
        },
        {
          "A) Ndola",
          "B) Lusaka",
          "C) Kitwe",
          "D) Livingstone"
        },
        {
          "A) Zambezi River",
          "B) Kafue River",
          "C) Luangwa River",
          "D) Chambeshi River"
        },
        {
          "A) Kafue National Park",
          "B) Liuwa Plain National Park",
          "C) Lower Zambezi National Park",
          "D) South Luangwa National Park"
        },
        {
          "A) Zambezi River",
          "B) Luangwa River",
          "C) Kafue River",
          "D) Chambeshi River"
        },
        {
          "A) Lusaka",
          "B) Copperbelt",
          "C) Northern",
          "D) Eastern"
        },
        {
          "A) Lake Tanganyika",
          "B) Lake Mweru",
          "C) Lake Kariba",
          "D) Lake Bangweulu"
        },
        {
          "A) Lusaka",
          "B) Ndola",
          "C) Livingstone",
          "D) Kitwe"
        },
        {
          "A) Rainforest",
          "B) Miombo Woodland",
          "C) Desert",
          "D) Montane Forest"
        },
        {
          "A) Zambezi River",
          "B) Kafue River",
          "C) Luangwa River",
          "D) Chambeshi River"
        },
        {
          "A) Muchinga Escarpment",
          "B) Mafinga Hills",
          "C) Muchinga Mountains",
          "D) Nyika Plateau"
        },
        {
          "A) Cotton",
          "B) Tobacco",
          "C) Sugar Cane",
          "D) Maize"
        },
        {
          "A) Kafue River",
          "B) Chongwe River",
          "C) Luangwa River",
          "D) Chambeshi River"
        },
        {
          "A) Mosi-oa-Tunya National Park",
          "B) Kafue National Park",
          "C) South Luangwa National Park",
          "D) Liuwa Plain National Park"
        },
        {
          "A) Victoria Falls",
          "B) Lumangwe Falls",
          "C) Kundalila Falls",
          "D) Chishimba Falls"
        },
        {
          "A) Northern Province",
          "B) Western Province",
          "C) Southern Province",
          "D) Eastern Province"
        },
        {
          "A) Lake Bangweulu",
          "B) Lake Mweru",
          "C) Lake Tanganyika",
          "D) Lake Kariba"
        },
        {
          "A) Kariba Dam",
          "B) Kafue Gorge Dam",
          "C) Itezhi-Tezhi Dam",
          "D) Victoria Falls"
        },
        {
          "A) Northern Province",
          "B) Eastern Province",
          "C) Western Province",
          "D) Southern Province"
        },
        {
          "A) Maize",
          "B) Tobacco",
          "C) Cotton",
          "D) Soybeans"
        },
        {
          "A) Bangweulu Swamps",
          "B) Kafue Flats",
          "C) Lukanga Swamps",
          "D) Busanga Swamps"
        },
        {
          "A) Muchinga Plateau",
          "B) Nyika Plateau",
          "C) Luangwa Plateau",
          "D) Central Plateau"
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

    private Timer questionTimer;
    private JPanel timerPanel;
    private JLabel timerLabel;
    private int timeLeft;

    // changed things here

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
            //createFrame("Music", musicQuestions, musicAnswers, musicOptions);
        } else if (cultureRadioButton.isSelected()) {
            hideMainWindow();
            //createFrame("Culture", cultureQuestions, cultureAnswers, cultureOptions);
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
        
        JLabel questionLabel = new JLabel(currentQuestions[currentQuestionIndex]);
        questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPanel.add(questionLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        for (String option : currentOptions[currentQuestionIndex]) {
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
            JLabel questionLabel = new JLabel((i + 1) + ". " + currentQuestions[i]);
            questionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            quizPanel.add(questionLabel);

            JLabel answerLabel = new JLabel("Your answer: " + userSelectedAnswers.get(i));
            answerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            quizPanel.add(answerLabel);

            JLabel correctLabel = new JLabel("Correct answer: " + currentAnswers[i]);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}