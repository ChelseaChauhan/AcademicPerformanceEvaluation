package com.example.myproject;
import android.os.Parcel;
import android.os.Parcelable;

public class StudentModal implements Parcelable {
    private String studentEmail;
    private String studentPassword;
    private String studentPhone;
    private String studentName;
    private String studentImage;
    private String studentEnroll;
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    // creating an empty constructor.
    public StudentModal() {

    }

    protected StudentModal(Parcel in) {
        studentEmail = in.readString();
        studentPassword = in.readString();
        studentName = in.readString();
        studentId = in.readString();
        studentEnroll = in.readString();
        studentImage = in.readString();
        studentPhone = in.readString();

    }

    public static final Creator<StudentModal> CREATOR = new Creator<StudentModal>() {
        @Override
        public StudentModal createFromParcel(Parcel in) {
            return new StudentModal(in);
        }

        @Override
        public StudentModal[] newArray(int size) {
            return new StudentModal[size];
        }
    };

    // creating getter and setter methods.
    public String getStudentEmail() {
        return studentEmail;
    }
    public void setStudentEmail(String studentEmail) {
        this.studentEmail= studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }
    public void setStudentPassword(String studentPassword) { this.studentPassword= studentPassword; }

    public String getStudentPhone() {
        return studentPhone;
    }
    public void setStudentPhone(String studentPhone) { this.studentPhone= studentPhone; }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentEnroll() {
        return studentEnroll;
    }
    public void setStudentEnroll(String studentEnroll) { this.studentEnroll = studentEnroll; }

    public String getStudentImage() {
        return studentImage;
    }
    public void setStudentImage(String studentImage) { this.studentImage= studentImage; }



    public StudentModal( String studentEmail, String studentPassword, String studentName, String studentId, String studentEnroll, String studentImage, String studentPhone ) {
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;
        this.studentName = studentName;
        this.studentId = studentId;
        this.studentEnroll = studentEnroll;
        this.studentImage = studentImage;
        this.studentPhone = studentPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentEmail);
        dest.writeString(studentPassword);
        dest.writeString(studentName);
        dest.writeString(studentId);
        dest.writeString(studentEnroll);
        dest.writeString(studentImage);
        dest.writeString(studentPhone);


    }
}

