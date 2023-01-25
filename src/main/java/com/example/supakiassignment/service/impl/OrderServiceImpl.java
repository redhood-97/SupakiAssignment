package com.example.supakiassignment.service.impl;

import com.example.supakiassignment.dto.OrderPlaceResultDto;
import com.example.supakiassignment.entity.Item;
import com.example.supakiassignment.entity.Order;
import com.example.supakiassignment.entity.User;
import com.example.supakiassignment.entity.Wallet;
import com.example.supakiassignment.enums.ItemStatus;
import com.example.supakiassignment.repository.OrderRepository;
import com.example.supakiassignment.repository.StoreRepository;
import com.example.supakiassignment.repository.UserRepository;
import com.example.supakiassignment.repository.WalletRepository;
import com.example.supakiassignment.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;

    @Override
    public OrderPlaceResultDto placeOrder(String userId, String itemId) throws RuntimeException{
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty()) return OrderPlaceResultDto.builder()
                    .success(false)
                    .msg("No such user exists. Please register.")
                    .build();

            Optional<Item> item = storeRepository.findById(itemId);
            if (item.isEmpty() || Objects.equals(item.get().getStatus(), ItemStatus.SOLD))
                return OrderPlaceResultDto.builder()
                        .msg("Item has been sold. Not available.")
                        .success(false)
                        .build();

            List<Wallet> wallet = walletRepository.findWalletByUserId(user.get().getId());
            // No validation for wallet check as it will be created during user creation
            if (wallet.get(0).getBalance() < item.get().getPrice())
                return OrderPlaceResultDto.builder()
                        .msg("Sorry, balance is insufficient to complete this order. Please recharge to proceed.")
                        .success(false)
                        .build();

            Order order = Order.builder()
                    .itemId(itemId)
                    .userId(userId)
                    .cost(item.get().getPrice())
                    .build();

            Order purchase = orderRepository.save(order);

            // Update the balance after the item has been purchased
            Float newBalance = wallet.get(0).getBalance() - item.get().getPrice();
            wallet.get(0).setBalance(newBalance);
            walletRepository.save(wallet.get(0));
            item.get().setStatus(ItemStatus.SOLD);
            storeRepository.save(item.get());

            return OrderPlaceResultDto.builder()
                    .id(purchase.getId())
                    .success(true)
                    .msg("Order placed successfully")
                    .build();
        } catch (Exception e) {
            log.error("Exception encountered while placing the order = {}", e);
            throw new RuntimeException(e);
        }
    }

    public List<Order> findOrdersOfAUser(String userId) {
        return new ArrayList<Order>();
    }
}
