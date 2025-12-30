package com.back.boundedContext.payout.out;

import com.back.boundedContext.payout.app.PayoutCandidateItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayoutCandidateItemRepository extends JpaRepository<PayoutCandidateItem, Integer> {
}
