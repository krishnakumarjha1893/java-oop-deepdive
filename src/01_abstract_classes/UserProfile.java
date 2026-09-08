public class Main {

    public static void main(String[] args) {
        // Child class testing logic here
    }

}

// Top-level class default (package-private) rahegi
// Constructor protected hai taaki sirf child ya same package instantiate kare
class UserProfile {

    private double walletBalance;
    private String email;

    protected UserProfile(String e, double d) {
        this.email = e;
        this.walletBalance = d;
    }

    public String getEmail() {
        return email;
    }

    public double getWalletBalance() {
        return walletBalance;
    }
}
