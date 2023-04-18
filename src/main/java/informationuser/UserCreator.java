package informationuser;

import org.apache.commons.lang3.RandomStringUtils;

public class UserCreator {
    static int pwdCharacters=10;

    public static UserInformation getRandom() {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(pwdCharacters);
        String name = RandomStringUtils.randomAlphabetic(7);
        return new UserInformation(email + "@yandex.ru", password, name);
    }

    public static UserInformation getRandom(int pwdCharacters) {
        String email = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(pwdCharacters);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new UserInformation(email + "@yandex.ru", password, name);
    }
}
