package com.ltnc.be.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertRoomRequest extends BaseRequest{
    private Integer roomCapacity;
    private String roomNumber;
}
