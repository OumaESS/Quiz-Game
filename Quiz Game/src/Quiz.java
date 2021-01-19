package com.quiz;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener {

	String[] questions = {
			"<html>JAVA est un langage</html>",
			"<html>Toutes les classes héritent de la classe</html>",
			"<html>Par convention une classe</html>",
			"<html>Est-ce que on peut avoir constructeurs pour la même classe</html>",
			"<html>Dans la ligne public class A implements B, B est :</html>",
			"",
			"<html>Après la compilation, un programme écrit en JAVA, il se transforme en programme en bytecode Quelle est l’extension du programme en bytecode ?</html>",
			"<html>Class Test{Public Test () {System.out.println(”Bonjour”);} public Test (int i) {this(); System.out.println(”Nous sommes en ”+i+ ” !”);};} qu’affichera l’instruction suivante? Test t1=new Test (2018);</html>",
			"<html>Voici un constructeur de la classe Employee, y-a t'il un problème ? Public void Employe(String n){Nom=n;}</html>",
			"<html>Pour spécifier que la variable ne pourra plus être modifiée et doit être initialisée dès sa déclaration, on la déclare comme une constante avec le mot réservé</html>",
			"<html>Dans une classe, on accède à ses variables grâce au mot clé</html>",
			"",
			"<html>calculerSalaire(int) / calculerSalaire(int, double) \n La méthode calculerSalaire est : </html>",
			"<html>Une classe qui contient au moins une méthode abstraite doit être déclarée abstraite.</html>",
			"<html>Est-ce qu’une classe peut implémenter plusieurs interfaces ?</html>",
			"<html>La déclaration d'une méthode suivante :public void traitement() throws IOException précise que la méthode propage une exception contrôlée</html>",
			"<html>class Test{public static void main (String[] args) {try {int a =10; System.out.println (a = + a );int b = 0 / a;System.out.println (b = +b);}catch(ArithmeticException e){System.out.println (diviser par 0!); }finally {System.out.println (je suis à l’intérieur de finally);}}</html>"
			
	};
	String[][] options = { {"Compilé","Interprété", "Compilé et interpreté"},
			{"Main","Object", "AWT"},
			{"est en minuscule","commence par une majuscule", "est en majuscules"},
			{"Oui","Non","aucun des choix"},
			{"Interfacce","Classe parent", "Package"},
			{},
			{"aucun des choix", ".JAVA", ".Class"},
			{"aucun des choix", "Boujour Nous sommes en 2018", "Nous sommes en 2018"},
			{"vrai", "fuax","aucun des choix"},
			{"aucun des choix", "final", "const"},
			{"aucun des choix", "this", "super"},
			{},
			{"aucun des choix", "surchargée", "redéfinie"},
			{"vrai", "faux","aucun des choix"},
			{"vrai", "faux","aucun des choix"},
			{"vrai", "faux","aucun des choix"},
			{"aucun des choix","a=10 b=0 je suis à l’intérieur de finally" ,"a=10 b=0 diviser par 0! je suis à l’intérieur de finally"}
		};
	char[] answers = { 
			'A',
			'C',
			'B',
			'A',
			'A',
			' ',
			'A',
			'C',
			'A',
			'C',
			'B',
			' ',
			'A',
			'A',
			'B',
			'A',
			'B'};

	char answer;
	int ques;
	int index;
	int correct_guesses = 0;
	int total_questions = questions.length;
	int result;
	int seconds = 10;

	JFrame frame = new JFrame();
	JTextField textfield = new JTextField();
	JLabel jlabel = new JLabel();

	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();

	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();

	JTextField niveau = new JTextField();

	//JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();
	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();

	Timer timer = new Timer(1000, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			if (seconds <= 0) {
				displayAnswer();
			}
		}
	});

	public Quiz() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(750, 750);
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		// TEXTFIALD NQ
		textfield.setBounds(0, 0, 750, 50);
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);

		// QUESTIONS
		jlabel.setBounds(10,30,610,140);
		jlabel.setForeground(Color.white);
		jlabel.setFont(new Font("MV Boli", Font.BOLD, 20));
		jlabel.setHorizontalAlignment(JTextField.CENTER);

		// BUTTONS
		buttonA.setBounds(0, 180, 100, 100);
		buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonA.setFocusable(false);
		buttonA.addActionListener(this);
		buttonA.setText("A");

		buttonB.setBounds(0, 280, 100, 100);
		buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonB.setFocusable(false);
		buttonB.addActionListener(this);
		buttonB.setText("B");

		buttonC.setBounds(0, 380, 100, 100);
		buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
		buttonC.setFocusable(false);
		buttonC.addActionListener(this);
		buttonC.setText("C");

		// ANSWERS
		answer_labelA.setBounds(125, 180, 500, 100);
		answer_labelA.setBackground(new Color(50, 50, 50));
		answer_labelA.setForeground(new Color(25, 255, 0));
		answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelB.setBounds(125, 280, 500, 100);
		answer_labelB.setBackground(new Color(50, 50, 50));
		answer_labelB.setForeground(new Color(25, 255, 0));
		answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 35));

		answer_labelC.setBounds(125, 380, 500, 100);
		answer_labelC.setBackground(new Color(50, 50, 50));
		answer_labelC.setForeground(new Color(25, 255, 0));
		answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 35));

		// niveau
		niveau.setBounds(255, 500, 130, 50);
		niveau.setBackground(null);
		niveau.setForeground(Color.green);
		niveau.setFont(new Font("MV Boli", Font.BOLD, 50));
		// niveau.setEditable(false);
		niveau.setHorizontalAlignment(JTextField.CENTER);

		// Time
		seconds_left.setBounds(535, 510, 100, 100);
		seconds_left.setBackground(new Color(25, 25, 25));
		seconds_left.setForeground(new Color(255, 0, 0));
		seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));

//		time_label.setBounds(535, 475, 100, 25);
//		time_label.setBackground(new Color(50, 50, 50));
//		time_label.setForeground(new Color(255, 0, 0));
//		time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
//		time_label.setHorizontalAlignment(JTextField.CENTER);

		number_right.setBounds(225, 225, 200, 100);
		number_right.setBackground(new Color(25, 25, 25));
		number_right.setForeground(new Color(25, 255, 0));
		number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);

		percentage.setBounds(225, 325, 200, 100);
		percentage.setBackground(new Color(25, 25, 25));
		percentage.setForeground(new Color(25, 255, 0));
		percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);

		//frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(jlabel);
		frame.add(textfield);
		frame.setVisible(true);
		frame.add(niveau);

		nextQuestion();
	}

	public void nextQuestion() {

		if(index==5 || index==11 || index==17){
			results();
		}
		else{
				{
					textfield.setText("Question " + (ques + 1)+ "/" + 15 );
					jlabel.setText(questions[index]);
					answer_labelA.setText(options[index][0]);
					answer_labelB.setText(options[index][1]);
					answer_labelC.setText(options[index][2]);
					timer.start();
				}
				if (index <= 5) {
					niveau.setText("N1");
				} else if (index <=11) {
					niveau.setText("N2");
				} else if (index <= 17) {
					niveau.setText("N3");
				}
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);

		if (e.getSource() == buttonA) {
			answer = 'A';
			if (answer == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonB) {
			answer = 'B';
			if (answer == answers[index]) {
				correct_guesses++;
			}
		}
		if (e.getSource() == buttonC) {
			answer = 'C';
			if (answer == answers[index]) {
				correct_guesses++;
			}
		}
		displayAnswer();
	}

	public void displayAnswer() {

		timer.stop();

		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);

		if (answers[index] != 'A')
			answer_labelA.setForeground(new Color(255, 0, 0));
		if (answers[index] != 'B')
			answer_labelB.setForeground(new Color(255, 0, 0));
		if (answers[index] != 'C')
			answer_labelC.setForeground(new Color(255, 0, 0));
		ques++;

		Timer pause = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				answer_labelA.setForeground(new Color(25, 255, 0));
				answer_labelB.setForeground(new Color(25, 255, 0));
				answer_labelC.setForeground(new Color(25, 255, 0));

				answer = ' ';
				seconds = 10;
				seconds_left.setText(String.valueOf(seconds));
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				index++;
				nextQuestion();
			}
		});
		pause.setRepeats(false);
		pause.start();
	}


	public void results() {
		
		
		
		
		
		
		if(index==5 && correct_guesses<3){
			buttonA.setVisible(false);
			buttonB.setVisible(false);
			buttonC.setVisible(false);
			niveau.setVisible(false);
			percentage.setVisible(false);
			
			answer_labelA.setText("");
			answer_labelB.setText("");
			answer_labelC.setText("");
			
			jlabel.setText("");
			seconds_left.setVisible(false);
			number_right.setText("LOSER");
			frame.add(number_right);
			
		}else if(index==11 && correct_guesses<6){
			buttonA.setVisible(false);
			buttonB.setVisible(false);
			buttonC.setVisible(false);
			niveau.setVisible(false);
			percentage.setVisible(false);
			
			jlabel.setText("");
			seconds_left.setVisible(false);
			answer_labelA.setText("");
			answer_labelB.setText("");
			answer_labelC.setText("");			
			number_right.setText("LOSER");
			frame.add(number_right);
		}
		else if(index==17){
			if(correct_guesses<10){
				buttonA.setVisible(false);
				buttonB.setVisible(false);
				buttonC.setVisible(false);
				niveau.setVisible(false);
				percentage.setVisible(false);
				
				jlabel.setText("");
				seconds_left.setVisible(false);
				answer_labelA.setText("");
				answer_labelB.setText("");
				answer_labelC.setText("");				
				number_right.setText("LOSER");
				frame.add(number_right);
			}
			else{
				System.out.println(correct_guesses);
				buttonA.setVisible(false);
				buttonB.setVisible(false);
				buttonC.setVisible(false);
				niveau.setVisible(false);
				percentage.setVisible(false);
				
				jlabel.setText("");
				seconds_left.setVisible(false);
				answer_labelA.setText("");
				answer_labelB.setText("");
				answer_labelC.setText("");				
				number_right.setText("WINER!!");
				frame.add(number_right);
			}
		}
		else {
			
			correct_guesses=0;
			index++;
			nextQuestion();
		}

}
}
