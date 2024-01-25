//package com.art.jeanyvesart.model;
//
//import lombok.Data;
//
//import java.io.Serializable;
//import java.util.Date;
//import java.util.List;
//
//@Data
//
//public class Checkout implements Serializable {
//    private static final long serialVersionUID = 1L;
//
//    private Long id;
//    private Date placedAt = new Date();
//
//  //  @Column(length = 500)
//    private Address billingAddress;
//  //  @Column(length = 1000)
//
//    private List<MyProduct>artworks;
//   // @Column(length = 500)
//    private MyCustomer buyer;
////    @NotEmpty(message = "Card holder name is required")
////    public String ccName;
//////    @NotEmpty
////    @CreditCardNumber(message = "Not a valid credit card number")
////    private String ccNumber;
////    @NotBlank(message = "Expiration month is required")
////    private String ccExpirationMonth;
////    @NotBlank(message = "Expiration year is required")
////    private String ccExpirationYear;
////    //@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
////    @NotEmpty
////   // @Size(min = 3, max = 4)
////    private String ccCVV;
//
//
//}
