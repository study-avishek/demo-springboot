package com.increff.springboot.demo.api;

import com.increff.commons.springboot.common.ApiException;
import com.increff.commons.springboot.common.ApiStatus;
import com.increff.commons.springboot.common.ConvertUtil;
import com.increff.commons.springboot.common.ValidationUtil;
import com.increff.springboot.demo.dao.UserDao;
import com.increff.springboot.demo.model.UserData;
import com.increff.springboot.demo.model.UserForm;
import com.increff.springboot.demo.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

@Service
public class UserApi {

    @Autowired
    private UserDao dao;

    @Transactional(rollbackFor = ApiException.class)
    public UserData addUser(UserForm userForm) throws IllegalAccessException, ApiException {
        ValidationUtil.validate(userForm);
        normalize(userForm);
        UserPojo pojo = ConvertUtil.convert(userForm, UserPojo.class);
        UserPojo existing = dao.select("username", pojo.getUsername());
        pojo.setEnabled(true);
        dao.persist(pojo);
        return ConvertUtil.convert(pojo, UserData.class);
    }

    private static void normalize(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                String value = (String) field.get(obj);
                if (value != null) {
                    field.set(obj, value.toLowerCase().trim());
                }
            }
        }
    }
}
