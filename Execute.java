package ChemicalEquations;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Execute {
	
	static JPanel balancedMeasurementsPanel = new JPanel();
	
	public static void main(String[] args) {
		
		ChemicalReaction reaction = new ChemicalReaction();
		
		JFrame equationInputFrame = new JFrame();
		JPanel equationInputPanel = new JPanel(new BorderLayout());
		JTextField equationInputLabel = new JTextField("Please input a balanced chemical equation. Ex: 4(NH3) + 5(O2) = 4(NO) + 6(H2O)");
		equationInputLabel.setEditable(false);
		JTextField equationTextField = new JTextField();
		JButton equationEnterButton = new JButton("Enter");
		equationInputPanel.add(equationInputLabel, BorderLayout.NORTH);
		equationInputPanel.add(equationTextField, BorderLayout.CENTER);
		equationInputPanel.add(equationEnterButton, BorderLayout.SOUTH);
		equationInputFrame.add(equationInputPanel);
		equationInputFrame.setSize(500, 200);
		equationInputPanel.setSize(500, 200);
		equationInputFrame.setVisible(true);
		
		JFrame parametersInputFrame = new JFrame();
		JPanel parametersInputPanel = new JPanel(new BorderLayout());
		JTextField parametersInputLabel = new JTextField("Please input an amount of a compound to get balanced results.");
		parametersInputLabel.setEditable(false);
		JComboBox compoundSelections = new JComboBox();
		JTextField measurementInput = new JTextField();
		String[] gramsMoles = {"grams", "moles"}; 
		JComboBox measurementUnit = new JComboBox(gramsMoles);
		JButton parametersEnterButton = new JButton("Enter");
		parametersInputPanel.add(parametersInputLabel, BorderLayout.NORTH);
		parametersInputPanel.add(compoundSelections, BorderLayout.WEST);
		parametersInputPanel.add(measurementInput, BorderLayout.CENTER);
		parametersInputPanel.add(measurementUnit, BorderLayout.EAST);
		parametersInputPanel.add(parametersEnterButton, BorderLayout.SOUTH);
		parametersInputFrame.add(parametersInputPanel);
		parametersInputFrame.setSize(500, 200);
		parametersInputPanel.setSize(500, 200);
		
		JFrame ouputFrame = new JFrame();
		JPanel outputPanel = new JPanel(new BorderLayout());
		JTextField outputLabel = new JTextField("These are all the measurements of compounds in your equation.");
		outputLabel.setEditable(false);
		JButton outputNewEquationButton = new JButton("New Equation");
		JButton outputChangeBalanceValuesButton = new JButton("Input Compound Amounts");
		outputPanel.add(outputLabel, BorderLayout.NORTH);
		outputPanel.add(balancedMeasurementsPanel, BorderLayout.CENTER);
		outputPanel.add(outputNewEquationButton, BorderLayout.EAST);
		outputPanel.add(outputChangeBalanceValuesButton, BorderLayout.WEST);
		ouputFrame.add(outputPanel);
		ouputFrame.setSize(1950, 200);
		outputPanel.setSize(1950, 200);
		
		class ButtonListener implements ActionListener {
			
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource() == equationEnterButton) {
					
					reaction.initialize(equationTextField.getText());
					equationInputFrame.setVisible(false);
					
					compoundSelections.removeAllItems();
					for (String compound : reaction.compounds) {
						compoundSelections.addItem(compound);
					}
					
					parametersInputFrame.setVisible(true);
					equationTextField.setText("");
						
				} else if (e.getSource() == parametersEnterButton) {
					
					parametersInputFrame.setVisible(false);
					
					double measurement;
					try {
						measurement = Double.parseDouble(measurementInput.getText());
					} catch (Exception exc) {
						measurement = 0.0;
					}
					
					buildOutputScreen(reaction.getBalancedReaction(compoundSelections.getSelectedItem().toString(), measurement, measurementUnit.getSelectedItem().toString()));
					ouputFrame.setVisible(true);
					
					compoundSelections.setSelectedIndex(0);
					measurementInput.setText("");
					measurementUnit.setSelectedIndex(0);
					
				} else if (e.getSource() == outputNewEquationButton) {
					
					ouputFrame.setVisible(false);
					equationInputFrame.setVisible(true);
					
				} else if (e.getSource() == outputChangeBalanceValuesButton) {
					
					ouputFrame.setVisible(false);
					parametersInputFrame.setVisible(true);
					
				}
				
			}
			
		}
		
		equationEnterButton.addActionListener(new ButtonListener());
		parametersEnterButton.addActionListener(new ButtonListener());
		outputChangeBalanceValuesButton.addActionListener(new ButtonListener());
		outputNewEquationButton.addActionListener(new ButtonListener());
		
	}
	
	static void buildOutputScreen(ArrayList<ArrayList> rows) {
		
		balancedMeasurementsPanel.removeAll();
		balancedMeasurementsPanel.setLayout(new GridLayout(0, rows.get(1).size()));
		
		for (Object compound : rows.get(0)) {
			balancedMeasurementsPanel.add(new JTextField(compound.toString()));
		}
		for (Object measurement : rows.get(1)) {
			balancedMeasurementsPanel.add(new JTextField(measurement.toString() + " moles"));
		}
		for (Object measurement : rows.get(2)) {
			balancedMeasurementsPanel.add(new JTextField(measurement.toString() + " grams"));
		}
		
	}
	
}
