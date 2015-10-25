import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class WordGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Статично задаване на масива
		char arr[][]= new char[][]{
				  { 'i', 'i', 'v', 'a', 'n'},
				  { 'n', 'v', 'h', 'n', 'h'},
				  { 'a', 'h', 'a', 'h', 'h'},
				  { 'v', 'v', 'h', 'n', 'h'},
				  { 'i', 'v', 'a', 'n', 'n'}
				};
		//Резултат от масива
		try{
		System.out.println(check(arr,"ivan",5,5));
		//Генерира масив който съдържа думата поне веднъж
		Generate();
		}catch(ArrayIndexOutOfBoundsException e){
			System.err.println("Too big array! Check the number of rows and columns as it exeeds the maximum!");
		}
	}
	
	
    //метода приема като аргументи: масив от символи, думата която искаме да прожерим,брой редове и бройколони
	//връща колко пъти е намерена думата
	public static int check(char[][] ch, String word, int rowsNum, int colsNum) {
		String[] rows = new String[rowsNum * 2];
		String[] cols = new String[colsNum * 2];
		String diagonal = "";
		String diagonal2 = "";
		int counter = 0;
		String revWord = "";
		for (int i = word.length() - 1; i >= 0; i--) {
			revWord += word.charAt(i);
		}
		// Проверява в редовете за съвпадение
		boolean flagRow=true;
		boolean flagCol=true;
		boolean flagDiag1=true;
		boolean flagDiag2=true;
		for (int i = 0; i < rowsNum; i++) {
			flagRow=true;
			flagCol=true;
			for (int j = 0; j < colsNum; j++) {
				if(flagRow){
				rows[i] += ch[i][j];
				if (rows[i].contains(word) || rows[i].contains(revWord)) {
					counter++;
					flagRow=false;
				}
				}
				if(flagCol){
				cols[i] += ch[j][i];
				if (cols[i].contains(word) || cols[i].contains(revWord)) {
					counter++;
					flagCol=false;
				}
				}
				
				if(flagDiag1==true){
				if (i == j) {
					diagonal += ch[i][j];
				}
				if (diagonal.contains(word) || diagonal.contains(revWord)) {
					counter++;
					flagDiag1= false;
					
				}
				}
					if(flagDiag2){			
				if (i + j + 1 == colsNum) {
					diagonal2 += ch[i][j];
				}
				if (diagonal2.contains(word) || diagonal2.contains(revWord)) {
					counter++;
					flagDiag2 = false;
					}
					}
			}
			}
						
		return counter;
	}

	
	public static void Generate() {
		char[][] arr = new char[100][100];
		int rows = 6, cols = 5;
		char ch = 0;
		boolean flag = true;
		String word = null;
		try{
			boolean flagWord=true;
			while(flagWord){
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter a valid word");
		word = in.nextLine();
		for(int i=0;i<word.length();i++){
			if(word.charAt(i)< 'a' ||word.charAt(i) > 'z'){
				flagWord=true;
				break;
			}
			else flagWord=false;
				
		}
			}
				// Генерира матрици от букви до намиране на поне едно съвпадение с
		// желаната дума
		while (flag) {
			Random rand = new Random();
			if (flag) {
				for (int i = 0; i < rows; i++) {
					for (int j = 0; j < cols; j++) {
						while (ch < 'a' || ch > 'z') {
							ch = (char) (96 + rand.nextInt(26));
						}
						arr[i][j] = ch;
						ch = 0;
					}

				}

				int count = check(arr, word, rows, cols);
				if (count > 0) {
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < cols; j++) {
							System.out.print(arr[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println(count);
					flag = false;
				}
			}
		}
	}catch(InputMismatchException e){
		System.err.println("you havent entered a valid char");
	}

	}
}