//package com.art.jeanyvesart.controller;
//
//import com.art.jeanyvesart.dto.MyCurrentUserImpl;
//import com.art.jeanyvesart.dto.ReviewDto;
//import com.art.jeanyvesart.model.MyCustomer;
//import com.art.jeanyvesart.model.MyProduct;
//import com.art.jeanyvesart.model.MyReview;
//import com.art.jeanyvesart.repository.CustomerRepository;
//import com.art.jeanyvesart.repository.ProductRepository;
//import com.art.jeanyvesart.repository.ReviewRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//@Slf4j
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:8080/", "https://jeanyveshector.com/"})
//
//@RequestMapping(path = "/product/review", produces = "application/json")
//public class ReviewController {
//
//    private final ReviewRepository reviewRepository;
//    private final ProductRepository productRepository;
//    private final CustomerRepository customerRepository;
//
//
//    public ReviewController(ReviewRepository reviewRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
//        this.reviewRepository = reviewRepository;
//        this.productRepository = productRepository;
//        this.customerRepository = customerRepository;
//    }
//
//    @GetMapping("/all/{productId}")
//    public ResponseEntity<List<ReviewDto>> getAllReviews(@PathVariable Long productId) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "date");
//        List<MyReview> reviewList = reviewRepository.findAllByProductId(sort, productId);
//        if(!reviewList.isEmpty()){
//        List<ReviewDto> reviewDtoList = new ArrayList<>();
//        reviewList.forEach(review ->
//            reviewDtoList.add(
//                    new ReviewDto(
//                            new MyCurrentUserImpl(
//                                    review.getMyCustomer().getFullName(),
//                                    review.getMyCustomer().getId()),
//                            review.getHeadline(),
//                            review.getReviewText(),
//                            review.getProduct().getId(),
//                            review.getRating(),
//                            review.getDate(), review.getImageData(), review.getImageName()))
//        );
//        return new ResponseEntity<>(reviewDtoList, HttpStatus.OK);
//    }
//        else{
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//
//        }
//
//    }
//
//    @PostMapping(value = "/save", consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    @Transactional
//    public ReviewDto saveReview(@RequestBody ReviewDto reviewDto) {
//       // System.out.println(reviewDto);
//        try {
//            Optional<MyProduct> optionalMyProduct = productRepository.findById(reviewDto.getProductId());
//            if (optionalMyProduct.isPresent()) {
//                MyReview review = new MyReview();
//                review.setReviewText(reviewDto.getReviewText());
//                review.setDate(new Date());
//                review.setRating(reviewDto.getRating());
//                review.setHeadline(reviewDto.getHeadline());
//                review.setImageName(reviewDto.getImageName());
//                review.setImageData(reviewDto.getImageData());
//                Optional<MyCustomer> optionalMyCustomer = customerRepository.findById(reviewDto.getReviewer().getId());
//              //  System.out.println(Arrays.toString(reviewDto.getImageData()));
//              //  System.out.println(reviewDto.getImageName());
//
//                if (optionalMyCustomer.isPresent()) {
//                    review.setMyCustomer(optionalMyCustomer.get());
//                } else {
//
//                    review.setMyCustomer(new MyCustomer(reviewDto.getReviewer().getId()));
//                }
//                review.setProduct(optionalMyProduct.get());
//                //reviewRepository.save(review);
//                MyReview r = reviewRepository.save(review);
//                reviewDto.setReviewer(new MyCurrentUserImpl(
//                        r.getMyCustomer().getFullName(), r.getMyCustomer().getId()));
//                reviewDto.setRating(r.getRating());
//                reviewDto.setDate(r.getDate());
//                //System.out.println("reviewDto data here");
//
//
//                return reviewDto;
//
//            }
//            return null;
//
//        } catch (Exception e) {
//            System.out.println(e);
//            return null;
//        }
//
//    }
//}
