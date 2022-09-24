public class Polynomial {
	
	double[] poly;
	
	public Polynomial() {
		poly = new double[] {0};
	}

	public Polynomial(double[] arr) {
		int len = arr.length;
		poly = new double[len];
		for(int i = 0; i < len; i++) {
            	poly[i] = arr[i];
		}
	}

	public Polynomial add(Polynomial other) {
		int len = poly.length;
		int other_len = other.poly.length;
		double hold;
		if(len >= other_len) {
			for(int i = 0; i < other_len; i++) {
				poly[i] = poly[i] + other.poly[i];
			}
		return this;
		}
		else {
			for(int i = 0; i < len; i++) {
				other.poly[i] = other.poly[i] + poly[i];
			}
		return other;
		}
	}

	public double evaluate(double x) {
		double total = 0.0;
		int len = poly.length;
		double val;
		for(int i = 0; i < len; i++) {
			val = Math.pow(x, i);
			total = (poly[i] * val) + total;
		}
		return total;
	}

	public boolean hasRoot(double r) {
		return evaluate(r) == 0.0;
	}
}
			
	
			