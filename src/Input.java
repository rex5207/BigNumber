import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Input {
	private String ori_str;
	private List<String> parse_str = new ArrayList<String>();

	public Input(String input) {
		ori_str = input;
		input = input.replaceAll(" ", ""); // 去掉空格
		input = input.replaceAll("=", "");
		add_parentheses(input);
	}
	
	// 將輸入字串根據優先順序加上括號
	private void add_parentheses(String input) {
		String symbols[] = {"+", "-", "*", "/"};
		for(String symbol : symbols) {
			if(input.indexOf(symbol) != -1) {
				String token[] = input.split("\\"+symbol, 2);
				parse_str.add("(");
				add_parentheses(token[0]);
				parse_str.add(symbol);
				add_parentheses(token[1]);
				parse_str.add(")");
				return;
			}
		}
		parse_str.add(input);
	}
	
	public void eval() {
		System.out.print(ori_str);
		Stack<BigNumber> nums = new Stack<BigNumber>();
		Stack<String> symbols = new Stack<String>();
		for (String str : parse_str) {
			// 非右括號的運算符號，存起來
			if(str == "+" || str == "-" || str == "*" || str == "/" || str == "(") {
				symbols.add(str);
			}
			// 開始運算至左括號
			else if( str == ")") {
				String symbol = symbols.peek();
				symbols.pop(); // remove +-*/
				symbols.pop(); // remove (
				BigNumber num1 = nums.peek();
				nums.pop(); // remove num1
				BigNumber num2 = nums.peek();
				nums.pop(); // remove num2
				switch (symbol){
				    case "+":
				    	nums.add(num2.add(num1));
				    	break;
				    case "-":
				    	nums.add(num2.sub(num1));
				    	break;
				    case "*":
				    	nums.add(num2.mul(num1));
				    	break;
				    case "/":
				    	nums.add(num2.div(num1));
				    	break;
				}
			}
			// 數字，轉成BigNumber並存起來
			else {
				nums.add(new BigNumber(str));
				for(BigNumber num : nums) {
					num.toString();
				}
			}
		}
		// Final Output
		BigNumber ans = nums.peek();
		nums.pop();
		System.out.println(ans.toString());
	}
}
