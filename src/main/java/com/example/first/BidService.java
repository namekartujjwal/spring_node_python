package com.example.first;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BidService {

    private final AuctionRepository auctionRepo;
    private final BidRepository bidRepo;

    public BidService(AuctionRepository auctionRepo, BidRepository bidRepo) {
        this.auctionRepo = auctionRepo;
        this.bidRepo = bidRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public void placeBid(Long auctionId, Double amount) throws Exception {
        Auction auction = auctionRepo.findById(auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found"));

        Bid bid = new Bid();
        bid.setAmount(amount);

        auction.addBid(bid);

        if (amount <= 0) {
            throw new Exception("Invalid bid. Transaction rolling back.");
        }

        bidRepo.save(bid);
    }
}