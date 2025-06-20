package bean;

public class TestListStudent {
    private String subjectName;
    private String subjectCd;  // 修正
    private int num;
    private int point;
    private int studentNo;

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCd() {   // 修正
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {  // 修正
        this.subjectCd = subjectCd;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    private int entYear;
    private String classNum;
    private String studentName;

    public int getEntYear() { return entYear; }
    public void setEntYear(int entYear) { this.entYear = entYear; }

    public String getClassNum() { return classNum; }
    public void setClassNum(String classNum) { this.classNum = classNum; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

}


