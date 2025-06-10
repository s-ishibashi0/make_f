package bean;

public class User {
    private boolean isAuthenticated;
    private School school; // ← 追加

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public School getSchool() { // ← 追加
        return school;
    }

    public void setSchool(School school) { // ← 追加
        this.school = school;
    }
}
