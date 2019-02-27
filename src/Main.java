
public class Main {
	public static void main(String args[]){
		// ADD
		Input input = new Input("123 + 456 = ");
		input.eval();
		input = new Input("1234567890 + 987654321 = ");
		input.eval();
		
		// SUB	
		input = new Input("123 - 456 = ");
		input.eval();
		input = new Input("456 - 123 = ");
		input.eval();
		input = new Input("1234567890 - 987654321 = ");
		input.eval();
		
		// MUL
		input = new Input("123 * 456 = ");
		input.eval();
		input = new Input("1234567890 * 987654321 = ");
		input.eval();
		
		// DIV
		input = new Input("4352 / 17 = ");
		input.eval();
		input = new Input("185063895 / 234555 = ");
		input.eval();
		
		// Multiple Input
		input = new Input("1 + 2 * 3 + 4 * 2 - 5 = ");
		input.eval();
		input = new Input("1001 - 20 * 50 + 5 - 10 / 2 = ");
		input.eval();
	}
}