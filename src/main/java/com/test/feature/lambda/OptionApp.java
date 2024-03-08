package com.test.feature.lambda;

import com.test.domain.entity.User;

import java.util.Optional;

/**
 * @author trangle
 */
public class OptionApp {

    public static void main(String[] args) {
        User user = null;
        user = Optional.of(user)
                .ofNullable(new User())
                .filter(o -> "zz".equals(o.getUserName()))
                .get();


    }
}
