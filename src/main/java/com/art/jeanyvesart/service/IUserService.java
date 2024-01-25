package com.art.jeanyvesart.service;


import com.art.jeanyvesart.dto.CustomerDto;
import com.art.jeanyvesart.exceptionHandler.UserAlreadyExistException;

public interface IUserService<E> {
    E registerNewCustomerAccount(CustomerDto userDto) throws UserAlreadyExistException;
}