package com.serviceinterface;

import java.util.List;


import com.entity.UserRoleEntity;
import com.payload.UserRoleRequest;

public interface UserRoleServiceInterface
{
	void add(UserRoleRequest userRoleRequest);
	List<UserRoleEntity> getAll();
	void updateuserrole (UserRoleRequest userrolerequest);
	UserRoleEntity deleteuserroles(UserRoleRequest userrolerequest);
}
