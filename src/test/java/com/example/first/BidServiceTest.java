package com.example.first;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BidServiceTest {

    @Autowired
    private BidService bidService;

    @Autowired
    private AuctionRepository auctionRepo;

    @Autowired
    private BidRepository bidRepo;

    @Test
    void testPlaceBidRollback() {
        Auction auction = new Auction();
        auction.setItemTitle("Test Item");
        auction = auctionRepo.save(auction);
        Long auctionId = auction.getId();

        long initialBidCount = bidRepo.count();

        assertThrows(Exception.class, () -> {
            bidService.placeBid(auctionId, -10.0);
        });

        assertEquals(initialBidCount, bidRepo.count());
    }
}