import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Problem13 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		long [][] outcome  = consume();
		print(outcome);
		add(outcome);
	}
	
	private static void print(long[][] outcome) {
		for(int i = 0 ; i < 100; i ++){
			for (int j = 0; j < 50; j++){
				System.out.print(outcome[i][j]);
			}
			System.out.println("");
		}
	}

	private static void add(long[][] outcome) {
		StringBuilder result = new StringBuilder();
		long[] carryOver = new long [60]; 
		for (int i = 0; i < 60; i ++){
			carryOver[i] = 0;
		}
		int end = 48;
		for (int k = 49; k >=end; k --){
			long columnAdd = 0L;
			for (int j = 0; j < 100; j++){
				columnAdd = columnAdd + outcome[j][k];
				System.out.println("columnAddValue : " + columnAdd + " j value : " + outcome[j][k]);
			}
			columnAdd = columnAdd + carryOver[k+2];
			String ver = columnAdd+"";
			System.out.println("ver : " + ver);
			if (ver.length() > 1){
				char [] n = ver.toCharArray();
				String num = n[n.length-1]+"";
				result.append(num);
				int inc = 1;

				//
				System.out.print("carryOver State  before:  ");
				for (int i = 0; i <=  51 ; i++){
					System.out.print( carryOver[i] );
				}
				System.out.println("");
				//
				for (int s = n.length -2; s >= 0L; s-- ){
					String other = n[s]+"";
					carryOver[(k+2) - inc] = carryOver[(k+2) - inc] + Long.parseLong(other);
					inc = inc + 1;
				}
				
				//
				System.out.print("carryOver State  After :  ");
				for (int i = 0; i <=  51 ; i++){
					System.out.print( carryOver[i] );
				}
				System.out.println("");
				//
			}
			if (k == end){
				result.append(carryOver[end - 2]);
				if (carryOver[end - 1] != 0){
					result.append(carryOver[end - 1]);
				}
			}
		}

		System.out.println ("The sum : " + (new StringBuffer(result.toString()).reverse().toString()));
	}

	public static long [][] consume() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("/Users/shamsher/Documents/workspace-Git/EulerProject/src/Data-Problem13.txt"));
		String st = br.readLine();
		//20x20 handle only square for now
		long length = 50L;
		long width = 100L;
		//System.out.println(length);

		long [][] result = new long [(int) width][(int) length];
		for (int j = 0; j < width; j ++){
			//System.out.println(st);			
			char [] c = st.toCharArray();
			String [] line = new String[c.length];
			for (int m = 0; m < line.length;m ++){
				line[m] = c[m]+"";
			}
			for (int i = 0; i < length; i ++){
				result [j][i] = Long.parseLong(line[i]);
				//System.out.print(result [j][i]);
			}
			//System.out.println();
			st = br.readLine();
			if (st == null){
				break;
			}
		}
		return result;
	}
}
