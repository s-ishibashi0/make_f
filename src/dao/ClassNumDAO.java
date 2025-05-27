package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDAO extends DAO {

    // get (学校コードとクラス番号に基づいて ClassNum を取得)
    public ClassNum get(String classNum, School school) throws Exception {
        ClassNum classNumObj = null;
        String sql = "SELECT SCHOOL_CD, CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ? AND CLASS_NUM = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());  // School クラスの cd を取得
            ps.setString(2, classNum);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                classNumObj = new ClassNum();
                classNumObj.setClass_num(rs.getString("CLASS_NUM"));

                // School オブジェクトを作成し、ClassNum に設定
                School schoolObj = new School();
                schoolObj.setCd(rs.getString("SCHOOL_CD"));
                classNumObj.setSchool(schoolObj);
            }
        }

        return classNumObj;
    }

    // filter (学校コードに関連するすべてのクラス番号を取得)
    public List<String> filter(School school) throws Exception {
        List<String> classNums = new ArrayList<>();
        String sql = "SELECT CLASS_NUM FROM CLASS_NUM WHERE SCHOOL_CD = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, school.getCd());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                classNums.add(rs.getString("CLASS_NUM"));
            }
        }

        return classNums;
    }

    // save (新しい ClassNum を保存)
    public boolean save(ClassNum classNum) throws Exception {
        String sql = "INSERT INTO CLASS_NUM (SCHOOL_CD, CLASS_NUM) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, classNum.getSchool().getCd());  // School オブジェクトから学校コードを取得
            ps.setString(2, classNum.getClass_num());  // ClassNum オブジェクトからクラス番号を取得

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // 1件以上保存できた場合は true
        }
    }

    // save (既存のクラス番号を新しいクラス番号で更新)
    public boolean save(ClassNum classNum, String newClassNum) throws Exception {
        String sql = "UPDATE CLASS_NUM SET CLASS_NUM = ? WHERE SCHOOL_CD = ? AND CLASS_NUM = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newClassNum);
            ps.setString(2, classNum.getSchool().getCd());  // School オブジェクトから学校コードを取得
            ps.setString(3, classNum.getClass_num());  // 既存のクラス番号を取得

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // 1件以上更新できた場合は true
        }
    }
}
