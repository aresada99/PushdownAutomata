// IMPORTANT NOTE: I used 'E' for Epsilon symbol.

package package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


public class main {


	public static void main(String[] args) throws IOException {
		
		
		String fp = new String(getFileName());
		File file = new File(fp);	
		Scanner scan1 = new Scanner(file);
		Scanner scan = new Scanner(file);
		Scanner scan2 = new Scanner(file);
		
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--Input File--------------------------------------------------------");
		while(scan1.hasNextLine()) {
			System.out.println(scan1.nextLine());
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println("--------------------------------------------------------------------\r");
		

		
		
		int variableNumInput = Integer.parseInt(scan.nextLine());
		int variableNumStack = Integer.parseInt(scan.nextLine());
		int finalStateNum = Integer.parseInt(scan.nextLine());
		int stateNum = Integer.parseInt(scan.nextLine());

		
		String statesStr = scan.nextLine();
		String[] states = new String[stateNum];
		states = statesStr.split(" ",stateNum);

		
		String startState = scan.nextLine();

		
		String finalStatesStr = scan.nextLine();
		String[] finalStatesAry = new String[finalStateNum];
		ArrayList<String> finalStates = new ArrayList<String>();
		finalStatesAry = finalStatesStr.split(" ",finalStateNum);
		for(int i =0;i<finalStateNum;i++) {
			finalStates.add(finalStatesAry[i]);
			finalStates.get(i);
		}
		
		String stackStr = scan.nextLine();
		String[] stackAlphabet = new String[variableNumStack];
		stackAlphabet = stackStr.split(" ",variableNumStack);

		
		String initialStackSymbol = scan.nextLine();

		
		String varStr = scan.nextLine();
		String[] variablesStr = new String[variableNumInput];
		variablesStr = varStr.split(" ",variableNumInput);
		ArrayList<String> variables = new ArrayList<String>();
		for(int i =0;i<variableNumInput;i++) {
			variables.add(variablesStr[i]);
			variables.get(i);
		}
		

		int inputSize = 0;
		
		ArrayList<String> paths1d = new ArrayList<String>();
		
		///////
		for(int i=0;i<10;i++) {scan2.nextLine();}
		String str = "";
		char charA;
		String strA;
		while(scan2.hasNextLine()) {
			str = scan2.nextLine();
			charA = str.charAt(0);
			strA = String.valueOf(charA);

			if(variables.contains(strA)) {
				break;
			}
			inputSize++;

		}
		///////
		
		for(int i=0;i<inputSize;i++) {
			paths1d.add(scan.nextLine());
		}
		
		String[][] paths2d = new String[inputSize][5];
		for(int i=0;i<inputSize;i++) {
			for(int j=0;j<5;j++) {
				String[] string_parts = paths1d.get(i).split(" ");
				paths2d[i][j] = string_parts[j];
				
			}
		}
		
		ArrayList<String> inputsList = new ArrayList<String>();
		while(scan.hasNextLine()) {
			inputsList.add(scan.nextLine());
		}


		
		Stack<String> pda_stack = new Stack<String>();
		
		//PUSHDOWN AUTOMATA
				for(int i =0;i<inputsList.size();i++) {
					String[] current = new String[inputsList.get(i).length()];
					current = inputsList.get(i).split("",current.length);
					String currentState =  startState;
					String pathsToOutput = "";
					int counter = 0;
					
					for(int j=0;j<current.length;j++) {


						String token = current[j];
						
					
							
						for(int k= 0;k<inputSize;k++) {
							if(paths2d[k][0].equals(currentState)) {
								if(paths2d[k][1].equals("E") || (paths2d[k][1].equals(token)) && (paths2d[k][2].equals("E") || pda_stack.peek().equals(paths2d[k][2]))) {
									if(!paths2d[k][2].equals("E")) {
										if(pda_stack.peek().equals(paths2d[k][2])) {
										pda_stack.pop();
										}
									}
									if(!paths2d[k][3].equals("E")) {
										pda_stack.push(paths2d[k][3]);
									}
								
									
									currentState = paths2d[k][4];
									pathsToOutput = pathsToOutput + currentState + " ";
									if(!paths2d[k][1].equals("E")) {
										counter++;	
										break;
										
									}

								}

							}
						}
					
					
					}
					while(!pda_stack.empty()) {
					String token = "E";
						
					for(int k= 0;k<inputSize;k++) {
						if(paths2d[k][0].equals(currentState)) {
							if(paths2d[k][1].equals("E") || (paths2d[k][1].equals(token)) && (paths2d[k][2].equals("E") || pda_stack.peek().equals(paths2d[k][2]))) {
								if(!paths2d[k][2].equals("E")) {
									if(pda_stack.peek().equals(paths2d[k][2])) {
									pda_stack.pop();
									}
								}
								if(!paths2d[k][3].equals("E")) {
									pda_stack.push(paths2d[k][3]);
								}
							
								
								currentState = paths2d[k][4];
								pathsToOutput = pathsToOutput + currentState + " ";
								if(!paths2d[k][1].equals("E")) {
									break;
								}

							}

						}
					}
				}
					

					if(finalStates.contains(currentState) && (counter == current.length || counter > current.length)) {
						String q = "String: "+inputsList.get(i)+" ------->    Accepted\rRoute Taken: "+startState+" "+pathsToOutput;
						System.out.println(q +"\r");
						
					}
					else {
						String p = "String: "+inputsList.get(i)+" ------->    Rejected\rRoute Taken: "+startState+" "+pathsToOutput;
						System.out.println(p+"\r");
					}
			
				}
	
	}
	

	

	
	
	
	
	
	public static String getFileName(){
		 JFileChooser chooser = new JFileChooser();
		 FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt file", "txt");
		 chooser.setFileFilter(filter);
		 int returnVal = chooser.showOpenDialog(null);
		 if(returnVal != JFileChooser.APPROVE_OPTION) {
		 throw new RuntimeException("Failed to choose file");
		 }
		 return chooser.getSelectedFile().getAbsolutePath();
		}
	
	
}
