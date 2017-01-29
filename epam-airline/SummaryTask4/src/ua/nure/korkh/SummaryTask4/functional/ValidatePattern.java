package ua.nure.korkh.SummaryTask4.functional;

/**
 * Enum includes patterns for validates
 * 
 * @author Korkh
 * 
 */
public enum ValidatePattern {
	NAME() {
		@Override
		public String pattern() {
			return "^[À-ßA-Z][à-ÿa-z]+$";
		}
	},
	LOGIN_PASS() {
		@Override
		public String pattern() {
			return "^[a-zA-Z0-9]{2,15}+$";
		}
	},
	TELEPHONE() {
		@Override
		public String pattern() {
			return "^((0|\\+3)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{10,13}$";
		}
	},
	EMAIL() {
		@Override
		public String pattern() {
			return "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		}
	};

	public abstract String pattern();

}
