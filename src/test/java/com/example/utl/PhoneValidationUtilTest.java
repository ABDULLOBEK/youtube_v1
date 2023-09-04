package com.example.utl;

import com.example.util.PhoneValidationUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PhoneValidationUtilTest {
    private final PhoneValidationUtil phoneValidationUtil = new PhoneValidationUtil();

//    @BeforeEach
//    public void init() {
//        PhoneValidationUtil PhoneValidationUtil = new PhoneValidationUtil();
//    }

    //    @BeforeAll
//    public static void setUp() {
//        PhoneValidationUtil PhoneValidationUtil = new PhoneValidationUtil();
//    }
    @Test
    public void itShouldValidateWrongPhoneNumber() {
        // given
        String phone = "+99891572";
        // when
        boolean result = phoneValidationUtil.test(phone);
        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void itShouldValidatePhoneNumber_withoutPlus() {
        // given
        String phone = "998915721213";
        // when
        boolean result = phoneValidationUtil.test(phone);
        // then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    public void itShouldValidateNull() {
        // given
        // when
        boolean result = phoneValidationUtil.test(null);
        // then
        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("it should be false!!!")
    @Test
    public void itShouldValidatePhoneNumber_withoutPlus2() {
        // given
        String phone = "998915721213";
        // when
        boolean result = phoneValidationUtil.test(phone);
        // then
        Assertions.assertThat(result).isTrue().as("it should be false!!!");
    }


}
