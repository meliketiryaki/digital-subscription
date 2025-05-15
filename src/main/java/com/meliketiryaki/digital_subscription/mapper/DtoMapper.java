package com.meliketiryaki.digital_subscription.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.meliketiryaki.digital_subscription.dto.SubscriptionDto;
import com.meliketiryaki.digital_subscription.dto.UserDto;
import com.meliketiryaki.digital_subscription.model.Subscription;
import com.meliketiryaki.digital_subscription.model.User;

public class DtoMapper {

    public static SubscriptionDto toSubscriptionDto(Subscription subscription) {
        if (subscription == null) return null;
        return SubscriptionDto.builder()
                .id(subscription.getId())
                .serviceName(subscription.getServiceName())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .build();
    }

    public static UserDto toUserDto(User user) {
        if (user == null) return null;
        List<SubscriptionDto> subs = null;
        if (user.getSubscriptions() != null) {
            subs = user.getSubscriptions().stream()
                    .map(DtoMapper::toSubscriptionDto)
                    .collect(Collectors.toList());
        }
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole() != null ? user.getRole().name() : null)
                .subscriptions(subs)
                .build();
    }
}
