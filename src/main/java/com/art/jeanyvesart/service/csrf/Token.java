package com.art.jeanyvesart.service.csrf;


import lombok.Data;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Service;

import java.io.Serializable;


@Service
public class Token implements CsrfToken, Serializable {


  private String parameterName;

  private String headerName;
  private String token;
//
//  public Token(String parameterName, String headerName, String token){
//    this.parameterName = parameterName;
//    this.headerName =headerName;
//    this.token = token;
//  }

  @Override
  public String getHeaderName() {
    return headerName;
  }

  @Override
  public String getParameterName() {
    return parameterName;
  }

  @Override
  public String getToken() {
    return token;
  }


  // Omitted code

}