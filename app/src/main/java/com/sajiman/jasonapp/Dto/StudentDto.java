package com.sajiman.jasonapp.Dto;


import android.os.Parcel;
import android.os.Parcelable;

public class StudentDto implements Parcelable {

    private String imagePath;
    private int id;
    private String name;
    private String organization;
    private String faculty;
    private int roll;
    private String studentId;

    public StudentDto(int id, String studentID, String imagePath, String name, String organization, String faculty, int roll) {
        this.studentId = studentID;
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.organization = organization;
        this.faculty = faculty;
        this.roll = roll;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected StudentDto(Parcel in) {
        id = in.readInt();
        imagePath = in.readString();
        name = in.readString();
        organization = in.readString();
        faculty = in.readString();
        roll = in.readInt();
        studentId = in.readString();
    }

    public static final Creator<StudentDto> CREATOR = new Creator<StudentDto>() {
        @Override
        public StudentDto createFromParcel(Parcel in) {
            return new StudentDto(in);
        }

        @Override
        public StudentDto[] newArray(int size) {
            return new StudentDto[size];
        }
    };

    public StudentDto() {

    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imagePath);
        dest.writeString(name);
        dest.writeString(organization);
        dest.writeString(faculty);
        dest.writeInt(roll);
        dest.writeString(studentId);
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "id='" + id + '\'' +
                "imagePath='" + imagePath + '\'' +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", faculty='" + faculty + '\'' +
                ", roll=" + roll +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}
