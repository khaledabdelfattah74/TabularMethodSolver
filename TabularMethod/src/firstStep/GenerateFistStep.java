package firstStep;

import java.util.LinkedList;

import interFaces.FirstStepInterface;

public class GenerateFistStep implements FirstStepInterface {
	private LinkedList<Column> columns = new LinkedList<Column>();
	private LinkedList<Implicant> primeImplicants = new LinkedList<Implicant>();

	/**
	 * merge possible groups in each column
	 * @param minterms
	 * @param dontcares
	 */
	@Override
	public void generateTabular(String minterms, String dontcares) {
		Inputs.setInputs(minterms, dontcares);
		columns.add(Column.generateFirstColumn(Inputs.implicants));
		for(int i = 0;i < Inputs.variblesNum;i++)
		{
			Column nextCol = columns.get(i).generateNextCol();
			columns.add(nextCol);
		}
	}
	/**
	 * 
	 * @return prime Implicants
	 */
	@Override
	public LinkedList<Implicant> getImplicants() {
		for (int i = 0; i < columns.size(); i++) {
			LinkedList<Implicant> tmp = null;
			for (int j = 0; j < columns.get(i).myColumn.size(); j++) {
				tmp = columns.get(i).myColumn.get(j).getPrimeImplicants();
				if (tmp != null) {
					for (int k = 0; k < tmp.size(); k++) {
						if (!Inputs.minTerms.contains(tmp.get(k).num) && tmp.get(k).difs.size() == 0) {
							continue;
						}
						primeImplicants.add(tmp.get(k));
					}
				}
			}
		}
		return primeImplicants;
	}
	
	/**
	 * 
	 * @return Minterms
	 */
	@Override
	public LinkedList<Integer> getMinterms() {
		return Inputs.minTerms;
	}
}
