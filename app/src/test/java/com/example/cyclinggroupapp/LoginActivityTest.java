package com.example.cyclinggroupapp;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginActivityTest {

    @Test
    public void testValidateData_InvalidEmail() {
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.binding.emailET.setText("invalidEmail");
        loginActivity.binding.passwordEt.setText("validPassword");

        assertFalse(loginActivity.validateData());
    }












    @Test
    public void testValidateData_EmptyPassword() {
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.binding.emailET.setText("validEmail@example.com");
        loginActivity.binding.passwordEt.setText("");

        assertFalse(loginActivity.validateData());
    }

    @Test
    public void testValidateData_PasswordTooShort() {
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.binding.emailET.setText("validEmail@example.com");
        loginActivity.binding.passwordEt.setText("short");

        assertFalse(loginActivity.validateData());
    }

    @Test
    public void testValidateData_ValidInput() {
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.binding.emailET.setText("validEmail@example.com");
        loginActivity.binding.passwordEt.setText("validPassword");

        assertTrue(loginActivity.validateData());
    }

    @Test
    public void testValidateData_AdminLogin() {
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.binding.emailET.setText("admin");
        loginActivity.binding.passwordEt.setText("admin");

        // Assuming firebaseAdminLogin() sets a flag for admin login
        assertTrue(loginActivity.validateData());
        // Validate that admin login flag is set or check other expected behavior
    }

    @Test
    public void testValidateData_GccAdminLogin() {
        LoginActivity loginActivity = new LoginActivity();
        loginActivity.binding.emailET.setText("gccadmin");
        loginActivity.binding.passwordEt.setText("GCCRocks!");

        // Assuming firebaseGccLogin() sets a flag for GCC admin login
        assertTrue(loginActivity.validateData());
        // Validate that GCC admin login flag is set or check other expected behavior
    }

}
