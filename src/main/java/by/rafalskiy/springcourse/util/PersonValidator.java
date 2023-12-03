package by.rafalskiy.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import by.rafalskiy.springcourse.dao.PersonDAO;
import by.rafalskiy.springcourse.models.Person;
import org.springframework.validation.ValidationUtils;
import java.util.regex.Pattern;

/**
 * @author Pavel Rafalskiy
 */
@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;
    private static final String NAME_PATTERN = "^[a-zA-Zа-яА-Я]+\\s+[a-zA-Zа-яА-Я]+\\s+[a-zA-Zа-яА-Я]+$";
    private static final Pattern pattern = Pattern.compile(NAME_PATTERN);

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "NotEmpty");


        if (personDAO.getPersonByFullName(person.getFullName()).isPresent()) {
            // поле, код ошибки, сообщение ошибки
            errors.rejectValue("FullName", "", "Человек с таким ФИО уже существует");
        }

        if (!pattern.matcher(person.getFullName()).matches()) {
            errors.rejectValue("fullName", "person.fullName.invalidFormat",
                    "Please provide a valid full name (First Name, Patronymic, Last Name)");
        }

    }
}
