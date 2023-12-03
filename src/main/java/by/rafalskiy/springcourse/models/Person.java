package by.rafalskiy.springcourse.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Pavel Rafalskiy
 */
public class Person {
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 10, max = 100, message = "ФИО должно быть от 10 до 100 символов")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+\\s+[a-zA-Zа-яА-Я]+\\s+[a-zA-Zа-яА-Я]+$",
            message = "Please provide a valid full name (First Name, Patronymic, Last Name)")
    private String fullName;

    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int yearOfBirth;

    public Person() {
    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
