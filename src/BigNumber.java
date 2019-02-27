
public class BigNumber {
	//private String number;
	private int[] val = new int[100];
	private boolean sign = false;
	
	public BigNumber() {
		sign = false;
	}
	
	public BigNumber(String number) {
		if(number.charAt(0) == '-') {
			sign = true;
			number = number.substring(1);
		}
		int len = number.length();
		for(int i=len-1, j=0; i>=0; i--, j++) {
			val[j] = number.charAt(i) - '0';
		}
	}

	// BigN + BigN
	public BigNumber add(BigNumber num2) {
		BigNumber result = new BigNumber();
		for (int i=0; i<100; i++) {
			result.val[i] = val[i] + num2.val[i];
		}
		// 進位
		for (int i=0; i<99; i++) {
			result.val[i+1] += result.val[i] / 10;
			result.val[i] %= 10;
		}
		return result;
	}
	
	// BigN + INT
	public BigNumber add(int num2) {
		BigNumber result = new BigNumber(this.toString());
		result.val[0] = val[0] + num2;
		// 進位
		for (int i=0; i<99; i++) {
			result.val[i+1] += result.val[i] / 10;
			result.val[i] %= 10;
		}
		return result;
	}
	
	// BigN - BigN
	public BigNumber sub(BigNumber num2) {
		BigNumber result = new BigNumber();
		// Avoid Underflow
		if(!largerthan(num2)) {
			for (int i=0; i<100; i++) {
				result.sign = true;
				result.val[i] = num2.val[i] - val[i];
			}
		}
		else {
			for (int i=0; i<100; i++) {
				result.val[i] = val[i] - num2.val[i];
			}
		}
		// 借位
		for (int i=0; i<99; i++) {
			if(result.val[i] < 0) {
				result.val[i+1]--;
				result.val[i] += 10;
			}
		}
		return result;
	}
	
	// BigN * BigN
	public BigNumber mul(BigNumber num2) {
		BigNumber result = new BigNumber();
		for (int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(i+j < 100) {
					result.val[i+j] += val[i] * num2.val[j];
				}
			}
		}
		// 進位
		for (int i=0; i<99; i++) {
			result.val[i+1] += result.val[i] / 10;
			result.val[i] %= 10;
		}
		return result;
	}
	
	// BigN * INT
	public BigNumber mul(int num2) {
		BigNumber result = new BigNumber();
		for (int i=0; i<100; i++) {
			result.val[i] = val[i] * num2;
		}
		// 進位
		for (int i=0; i<99; i++) {
			result.val[i+1] += result.val[i] / 10;
			result.val[i] %= 10;
		}
		return result;
	}
	
	// BigN / BigN
	public BigNumber div(BigNumber num2) {
		int[] ori_val = this.val;
		BigNumber num1 = new BigNumber();
		StringBuilder qt = new StringBuilder();
		
		boolean sign1 = this.sign;
		boolean sign2 = num2.sign;
		if(sign1 != sign2) {
			qt.append('-');
		}
		// 長除法
		for(int i=100-1; i>=0; i--) {
			num1 = num1.mul(10);
			num1 = num1.add(ori_val[i]);
			for(int k=9; k>0; k--) {
				BigNumber temp = num2.mul(k);
				if(num1.largerthan(temp)) {
					qt.append(k);
					num1 = num1.sub(temp);
					break;
				}
				else if(k == 1) {
					qt.append(0);
				}
			}
		}
		return new BigNumber(qt.toString());
	}
	
	// Compare
	private boolean largerthan(BigNumber num2) {
		if(sign == false && num2.sign == true)
			return true;
		for (int i=100-1; i>=0; i--)
			if (val[i] != num2.val[i])
				return val[i] >= num2.val[i];
		return true; // 完全相等
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		// 負數
		if(sign)
			builder.append("-");
		int i = 100 - 1;
		// 前面的0不要print
		while(i >= 0 && val[i] == 0)
			i--;
		while(i >= 0){
		    builder.append(val[i]);
		    i--;
		}
		return builder.length() == 0? "0" : builder.toString();
	}
}
