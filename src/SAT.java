import java.awt.Color;
import java.util.*;
class Putnam {
	Clause[] expression;
	int[] literals;
	Putnam(int exprLength, int numLiterals) {
		expression = new Clause[exprLength];
		literals = new int[numLiterals*2];
		Arrays.fill(literals, 2);
		for (int i=0; i<expression.length; i++)
			expression[i]=new Clause(literals.length);
	}
	void reset() {
		Arrays.fill(literals, 2);
		for (int i=0; i<expression.length; i++)
			expression[i]=new Clause(literals.length);
	}
	class Clause {
		int[] index;
		Clause(int n) {
			index = new int[3];
			Random r = new Random();
			index[0] = r.nextInt(n);
			do {
				index[1] = r.nextInt(n);
			} while (index[1]%(literals.length/2) == index[0]%(literals.length/2));
			do {
				index[2] = r.nextInt(n);
			} while (index[2]%(literals.length/2) == index[0]%(literals.length/2) || index[2]%(literals.length/2) == index[1]%(literals.length/2));
		}
		//manually set chk
		/*
		Clause(int a, int b, int c) {
			index = new int[3];
			index[0]=a;
			index[1]=b;
			index[2]=c;
		}*/
		int getValue() {
			if (literals[index[0]]==1 || literals[index[1]]==1 || literals[index[2]]==1)
				return 1;
			else if (literals[index[0]]==0 && literals[index[1]]==0 && literals[index[2]]==0)
				return 0;
			else
				return 2;
		}
		public String toString() {
			String clauseStr="";
			clauseStr+="(";
			if (index[0]>=literals.length/2)
				clauseStr+="~";
			clauseStr+=(index[0]%(literals.length/2))+"<"+literals[index[0]]+">"+" + ";
			if (index[1]>=literals.length/2)
				clauseStr+="~";
			clauseStr+=(index[1]%(literals.length/2))+"<"+literals[index[1]]+">"+" + ";
			if (index[2]>=literals.length/2)
				clauseStr+="~";
			clauseStr+=(index[2]%(literals.length/2))+"<"+literals[index[2]]+">";
			clauseStr+=")";
			return clauseStr;
		}
	}
	void printExpression() {
		for (int i=0; i<expression.length; i++)
			System.out.println("."+expression[i]);
		System.out.println();
	}
	int getValue() {
		int count=0;
		for (int i=0; i<expression.length; i++) {
			if (expression[i].getValue() == 0)
				return 0;
			else if (expression[i].getValue() == 1)
				count++;
		}
		if (count == expression.length)
			return 1;
		else
			return 2;
	}
	int checkSatisfiable(int literalIndex) {
		int presentValue = getValue();
		if (presentValue != 2)
			return presentValue;
		else {
			literals[literalIndex] = 0;
			literals[literals.length/2 + literalIndex] = 1;
			if (checkSatisfiable(literalIndex+1) == 1)
				return 1;
			literals[literalIndex] = 1;
			literals[literals.length/2 + literalIndex] = 0;
			return checkSatisfiable(literalIndex+1);
		}
	}
}
public class SAT {
	
	public static void main (String args[]) {
		double sum = 0;
		int count1 = 0;
		for (int n=10; n<=1000; n+=10)
		{
			ArrayList<Double> plotalpha=new ArrayList<Double>();
			ArrayList<Double> plotunsat=new ArrayList<Double>();
			//for(double alpha = 0.1; alpha <7 ; alpha+=0.1){
				int count = 0;
				double alpha = 5.19;
				int k = (int)(n*alpha);
				Putnam p = new Putnam(k,n);
				for (int i=0; i<1000; i++) {
					if (p.checkSatisfiable(0)==0){
						count++;
					}
					p.reset();
				}
				double unsat = count /1000.0;
				double sat = 1000-count;
				count1++;
				sum = sum + sat;
				//double lsat = Math.log(sat);
				//int average = 2^(k*(7/8))^n;
				//if((int)lsat == average)
				//System.out.println(" sat=:"+sat+"\taverage=:"+average);
				//System.out.println(" k=:"+k+"\tn=:"+n);
				System.out.println(" alpha:"+alpha+"\tSAT : "+sat);
				//plotalpha.add((double)alpha);
				//plotunsat.add(unsat);
					
			//LineChart.generateChart(plotunsat, plotalpha, n+"", Color.RED);
		}
			double average1 = sum /count1;
			System.out.println(" sum:"+sum+"\taverage of sum : "+average1);
	}
}

