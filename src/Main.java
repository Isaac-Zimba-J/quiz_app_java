import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    // Questions and answers arrays
    String[] politicsQuestions = {
        "Who was the first President of Zambia after its independence in 1964?\n",
        "Which political party did Kenneth Kaunda lead?\n",
        "What is the term limit for the President of Zambia as per the current constitution?\n",
        "In which year did Zambia transition to a multi-party democracy?\n" 
    };
    String[] politicsAnswers = {
        "C) Kenneth Kaunda",
        "D) United National Independence Party (UNIP)",
        "B) Two terms of five years each",
        "C) 1991"
    };
    String[][] politicsOptions = {
        {
            "A) Frederick Chiluba\n",
            "B) Hakainde Hichilema\n",
            "C) Kenneth Kaunda\n",
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

    private JRadioButton politicsRadioButton;
    private JRadioButton geographyRadioButton;
    private JRadioButton musicRadioButton;
    private JRadioButton cultureRadioButton;

    private JPanel titlePanel;
    private JPanel categoryPanel;
    private JPanel buttonPanel;

    // Main constructor
    public Main() {
        // Setup of main properties
        setSize(800, 600);
        setTitle("Zed Trivia");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Initialize section components
        topSection();
        middleSection();
        bottomSection();

        // Layout manager
        setLayout(new BorderLayout());

        // Main panel with BoxLayout to organize components vertically
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));

        // Add components to the window
        mainPanel.add(titlePanel);
        mainPanel.add(categoryPanel);
        mainPanel.add(buttonPanel);

        // Add main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the window visible
        setVisible(true);
    }

    // Method to initialize the title and explanation of the game
    public void topSection() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridLayout(2,1));

        // Create and setup the title as a label
        JLabel titleLabel = new JLabel("WELCOME TO ZED TRIVIA", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        // Create and setup the explanation as a label
        JLabel explanationText = new JLabel("Choose a category to start the quiz", SwingConstants.CENTER);
        explanationText.setFont(new Font("Roboto", Font.PLAIN, 18));
        titlePanel.add(explanationText, BorderLayout.CENTER);
    }

    // Method to initialize the category selection radio buttons
    private void middleSection() {
        categoryPanel = new JPanel();
        categoryPanel.setLayout(new GridLayout(4, 1));

        // Radio buttons for the options
        politicsRadioButton = new JRadioButton("Politics");
        geographyRadioButton = new JRadioButton("Geography");
        musicRadioButton = new JRadioButton("Music");
        cultureRadioButton = new JRadioButton("Culture");

        // Group the radio buttons to ensure only one is selected
        ButtonGroup group = new ButtonGroup();
        group.add(politicsRadioButton);
        group.add(geographyRadioButton);
        group.add(musicRadioButton);
        group.add(cultureRadioButton);

        // Add radio buttons to the category panel
        categoryPanel.add(politicsRadioButton, BorderLayout.CENTER);
        categoryPanel.add(geographyRadioButton);
        categoryPanel.add(musicRadioButton);
        categoryPanel.add(cultureRadioButton);
    }

    // Method to initialize the Enter button
    private void bottomSection() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));

        // Create and setup the Enter button
        JButton enterButton = new JButton("Enter");
        buttonPanel.add(enterButton);

        // Add ActionListener to the Enter button
        enterButton.addActionListener(e -> enterButtonClicked());
    }

    // ActionListener method for Enter button click
    private void enterButtonClicked() {
        // Check which radio button is selected
        if (politicsRadioButton.isSelected()) {
            hideMainWindow();
            createFrame("Politics", politicsQuestions, politicsAnswers, politicsOptions);
        } else if (geographyRadioButton.isSelected()) {
            hideMainWindow();
            // createFrame("Geography", geographyQuestions, geographyAnswers, geographyOptions);
        } else if (musicRadioButton.isSelected()) {
            hideMainWindow();
            // createFrame("Music", musicQuestions, musicAnswers, musicOptions);
        } else if (cultureRadioButton.isSelected()) {
            hideMainWindow();
            // createFrame("Culture", cultureQuestions, cultureAnswers, cultureOptions);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a category.");
        }
    }

    public void hideMainWindow() {
        this.setVisible(false);
    }

    public void createFrame(String categoryName, String[] questions, String[] answers, String[][] options) {
        JFrame frame = new JFrame();
        frame.setTitle(categoryName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Iterate through the questions
        for (int i = 0; i < questions.length; i++) {
            JLabel questionLabel = new JLabel(questions[i]);
            panel.add(questionLabel);

            // Create and add the options for each question
            for (int j = 0; j < options[i].length; j++) {
                JRadioButton optionButton = new JRadioButton(options[i][j]);
                panel.add(optionButton);
            }
        }

        frame.add(new JScrollPane(panel), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new Main();
    }
}
