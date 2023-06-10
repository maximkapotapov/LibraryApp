package library.models;

import javax.validation.constraints.*;


public class Person {
    private int personId;

    @NotEmpty(message = "Name should not be empty")
    @Pattern(regexp = "[А-Я][a-я]+\\s[А-Я][а-я]+\\s[А-Я][а-я]+",
            message = "The name is incorrect. Correct name format: Фамилия, Имя, Отчество")
    private String fullName;

    @Min(value = 1920, message = "The age is too low!")
    @Max(value = 2023, message = "The age is too high!")
    private int ageOfBirth;

    public Person() {

    }

    public Person(int personId, String fullName, int ageOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.ageOfBirth = ageOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAgeOfBirth() {
        return ageOfBirth;
    }

    public void setAgeOfBirth(int ageOfBirth) {
        this.ageOfBirth = ageOfBirth;
    }
}
