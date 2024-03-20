package ChemicalEquations;

import java.util.ArrayList;
import java.util.Collections;

public class ChemicalReaction {
	
	public String equation;
	public ArrayList<String> compounds = new ArrayList<String>();
	public ArrayList<Integer> coefficients = new ArrayList<Integer>();
	public ArrayList<Element> elements = new ArrayList<Element>();
	public ArrayList<Integer> elementAmounts = new ArrayList<Integer>();
	public ArrayList<ArrayList> elementsByCompound = new ArrayList<ArrayList>();
	public ArrayList<ArrayList> elementAmountsByCompound = new ArrayList<ArrayList>();
	ArrayList<String> numbers = new ArrayList<String>();
	ArrayList<Character> uppercaseLetters = new ArrayList<Character>();
	
	ChemicalReaction() {
		
		for (Integer i = 0; i < 1000; i++) {
			numbers.add(i.toString());
		}
		
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (char letter : letters.toCharArray()) {
			uppercaseLetters.add(letter);
		}
		
	}
	
	public ArrayList<String> getTerms(String sequence) {
		
		ArrayList<String> terms = new ArrayList<String>();
		
		while (sequence.length() > 0) {
			
			if (sequence.contains("+")) {
				int plusIndex = sequence.indexOf("+");
				terms.add(sequence.substring(0, plusIndex));
				sequence = sequence.substring(plusIndex + 1);
			} else {
				terms.add(sequence);
				sequence = "";
			}
	
		}
		
		return terms;
		
	}
	
	public ArrayList<ArrayList> getCompoundsAndCoefficients(ArrayList<String> terms) {
		
		ArrayList<String> compounds = new ArrayList<String>();
		ArrayList<Integer> coefficients = new ArrayList<Integer>();
		
		for (String term : terms) {
			
			while (term.length() > 0) {
				
				int i = 0;
				for (; i < term.length() && term.charAt(i) != '('; i++) {
				}
				int j = i;
				for (; j < term.length() && term.charAt(j) != ')'; j++) {
				}
				if (term.substring(i + 1, j).length() > 0) {
				
					if (!compounds.contains(term.substring(i + 1, j))) {
					
						compounds.add(term.substring(i + 1, j));
					
						if (i > 0)
							coefficients.add(numbers.indexOf(term.substring(0, i)));
						else
							coefficients.add(1);
					
					} else {
					
						int compoundIndex = compounds.indexOf(term.substring(i + 1, j));
					
						if (i > 0)
							coefficients.set(compoundIndex, coefficients.get(compoundIndex) + numbers.indexOf(term.substring(0, i)));
						else
							coefficients.set(compoundIndex, coefficients.get(compoundIndex) + 1);
					
					}
				
				}
				
				term = "";
				
			}
			
		}
		
		ArrayList<ArrayList> compoundsAndCoefficients = new ArrayList<ArrayList>();
		compoundsAndCoefficients.add(compounds);
		compoundsAndCoefficients.add(coefficients);
		return compoundsAndCoefficients;

	}
	
	public ArrayList<ArrayList> getElementsAndAmounts(ArrayList<String> compounds) {
		
		ArrayList<ArrayList<Element>> elementsByCompound = new ArrayList<ArrayList<Element>>();
		ArrayList<ArrayList<Integer>> amountsByCompound = new ArrayList<ArrayList<Integer>>();
		ArrayList<Element> allElements = new ArrayList<Element>();
		ArrayList<Integer> allAmounts = new ArrayList<Integer>();
		
		for (String compound : compounds) {
			
			ArrayList<Element> elements = new ArrayList<Element>();
			ArrayList<Integer> amounts = new ArrayList<Integer>();
			
			while (compound.length() > 0) {
				
				int i = 0;
				for (; i < compound.length() && !numbers.contains("" + compound.charAt(i)) && (i == 0 || !(uppercaseLetters.contains(compound.charAt(i)) && uppercaseLetters.contains(compound.charAt(i - 1)))) && i < 2; i++) {
				}
				int j = i;
				for (; j < compound.length() && numbers.contains("" + compound.charAt(j)); j++) {
				}
				
				if (compound.substring(0, i).length() > 0) {
					
					if (!elements.contains(Element.valueOf(compound.substring(0, i)))) {
						elements.add(Element.valueOf(compound.substring(0, i)));
						int elementIndex = elements.indexOf(Element.valueOf(compound.substring(0, i)));
						if (i < j)
							amounts.add(elementIndex, numbers.indexOf(compound.substring(i, j)));
						else
							amounts.add(elementIndex, 1);
					} else {
						int elementIndex = elements.indexOf(Element.valueOf(compound.substring(0, i)));
						if (i < j)
							amounts.set(elementIndex, amounts.get(elementIndex) + numbers.indexOf(compound.substring(i, j)));
						else
							amounts.set(elementIndex, amounts.get(elementIndex) + 1);
					}
					
					if (!allElements.contains(Element.valueOf(compound.substring(0, i)))) {
						allElements.add(Element.valueOf(compound.substring(0, i)));
						Collections.sort(allElements);
						int elementIndex = allElements.indexOf(Element.valueOf(compound.substring(0, i)));
						if (i < j)
							allAmounts.add(elementIndex, numbers.indexOf(compound.substring(i, j)));
						else
							allAmounts.add(elementIndex, 1);
					} else {
						int elementIndex = allElements.indexOf(Element.valueOf(compound.substring(0, i)));
						if (i < j)
							allAmounts.set(elementIndex, allAmounts.get(elementIndex) + numbers.indexOf(compound.substring(i, j)));
						else
							allAmounts.set(elementIndex, allAmounts.get(elementIndex) + 1);
					}
					
				}
				
				compound = compound.substring(j);
				
			}
			
			elementsByCompound.add(elements);
			amountsByCompound.add(amounts);
			
		}
		
		ArrayList<ArrayList> elementsAndAmounts = new ArrayList<ArrayList>();
		elementsAndAmounts.add(allElements);
		elementsAndAmounts.add(allAmounts);
		elementsAndAmounts.add(elementsByCompound);
		elementsAndAmounts.add(amountsByCompound);
		return elementsAndAmounts;
		
	}
	
	public void initialize(String equ) {
		
		equation = equ.replaceAll(" ", "");
		equation = equation.replaceAll("=", "+");
		
		ArrayList<String> terms = getTerms(equation);
		
		ArrayList<ArrayList> components = getCompoundsAndCoefficients(terms);
		
		compounds = components.get(0);
		coefficients = components.get(1);
		
		ArrayList<ArrayList> factors = getElementsAndAmounts(compounds);
		
		elements = factors.get(0);
		
		elementAmounts = factors.get(1);
		
		elementsByCompound = factors.get(2);
		
		elementAmountsByCompound = factors.get(3);
		
	}
	
	public ArrayList<ArrayList> getBalancedReaction(String compound, double measurement, String unit) {
		
		ArrayList<Double> amountsInMoles = new ArrayList<Double>(compounds.size());
		ArrayList<Double> amountsInGrams = new ArrayList<Double>(compounds.size());
		
		double inputCompoundMoles;
		int inputCompoundIndex = compounds.indexOf(compound);
		if (unit == "grams") {		
			inputCompoundMoles = gramsToMoles(compound, measurement);
		} else if (unit == "moles") {
			inputCompoundMoles = measurement;
		} else {
			return null;
		}
		
		for (String unbalancedCompound : compounds) {
				
			int compoundIndex = compounds.indexOf(unbalancedCompound);
			double unbalancedCompoundCoefficient = coefficients.get(compoundIndex);
			double givenCompoundCoefficient = coefficients.get(inputCompoundIndex);
			double molesOfCompound = inputCompoundMoles * (unbalancedCompoundCoefficient / givenCompoundCoefficient);
			amountsInMoles.add(compoundIndex, molesOfCompound);
			amountsInGrams.add(compoundIndex, molesToGrams(unbalancedCompound, molesOfCompound));
			
		}
		
		ArrayList<ArrayList> balancedTerms = new ArrayList<ArrayList>(3);
		balancedTerms.add(new ArrayList<String>(compounds));
		balancedTerms.add(amountsInMoles);
		balancedTerms.add(amountsInGrams);
		return balancedTerms;
		
	}
	
	public double gramsToMoles(String compound, double grams) {
		
		return grams / getMolarMass(compound);
		
	}
	
	public double molesToGrams(String compound, double moles) {
		
		return getMolarMass(compound) * moles;
		
	}
	
	public double getMolarMass(String compound) {
		
		double molarMass = 0;
		int compoundIndex = compounds.indexOf(compound);
		ArrayList<Element> elements = this.elementsByCompound.get(compounds.indexOf(compound));
		ArrayList<Integer> amounts = this.elementAmountsByCompound.get(compounds.indexOf(compound));
		for (Element e : elements) {
			molarMass += e.atomicWeight * amounts.get(elements.indexOf(e));
		}
		return molarMass;
		
	}
	
}
