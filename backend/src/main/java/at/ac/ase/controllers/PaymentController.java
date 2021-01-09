package at.ac.ase.controllers;

import at.ac.ase.dto.PaymentIntentDTO;
import at.ac.ase.dto.translator.PaymentIntentToPaymentIntentResponseTranslator;
import at.ac.ase.entities.AuctionPost;
import at.ac.ase.service.auction.IAuctionService;
import at.ac.ase.service.payment.IPaymentService;
import at.ac.ase.util.exceptions.AuctionNotPayableException;
import at.ac.ase.util.exceptions.ObjectNotFoundException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private IAuctionService auctionService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private PaymentIntentToPaymentIntentResponseTranslator piToPirTranslator;

    @PostMapping
    public ResponseEntity<Object> postPaymentIntent(
        @RequestBody PaymentIntentDTO paymentIntentDTO) {

        AuctionPost auctionPost =  auctionService
            .getAuctionPost(paymentIntentDTO.getAuctionId())
            .orElseThrow(ObjectNotFoundException::new);

//        if (!auctionService.isAuctionPayable(auctionPost)) {
//            throw new AuctionNotPayableException();
//        }
        return ResponseEntity.ok(
            piToPirTranslator.paymentIntentToPaymentIntentResponse(
                paymentService.createPaymentIntent(auctionPost)));
    }

    @PostMapping("/store/{auctionId}")
    public ResponseEntity storePayment(@PathVariable Long auctionId) {
        AuctionPost auctionPost =  auctionService
            .getAuctionPost(auctionId)
            .orElseThrow(ObjectNotFoundException::new);
        if (!auctionService.isAuctionPayable(auctionPost)) {
            throw new AuctionNotPayableException();
        }
        auctionPost.setPaymentDate(LocalDateTime.now());
        auctionService.saveAuction(auctionPost);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
