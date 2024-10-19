package com.telcom.dto;

import com.telcom.entity.FriendFamily;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FriendFamilyDTO {
	
	@NotNull
	@Max(value = 9999999999L, message = "{customer.phoneNo.invalid}")
	@Min(value = 6000000000L, message = "{customer.phoneNo.invalid}")
    private long phoneNo;
	
	@NotNull
	@Max(value = 9999999999L, message = "{friendAndFamily.phoneNo.invalid}")
	@Min(value = 6000000000L, message = "{friendAndFamily.phoneNo.invalid}")
	private long friendAndFamily;
	
	
    public static FriendFamily prepareEntity(FriendFamilyDTO fdto) {
    	
    	FriendFamily friend = new FriendFamily();
    	friend.setPhoneNo(fdto.getPhoneNo());
    	friend.setFriendAndFamily(fdto.getFriendAndFamily());
		return friend;
    	
    }


	public FriendFamily createFriend() {
		FriendFamily friend = new FriendFamily();
    	friend.setPhoneNo(this.getPhoneNo());
    	friend.setFriendAndFamily(this.getFriendAndFamily());
		return friend;
	}

}
