package enums;

/**
 * @author vipul pachauri
 */
public enum UserRole {

    USER("USER"),ADMIN("ADMIN");

    private String role;

    UserRole(String role){
        this.role=role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
