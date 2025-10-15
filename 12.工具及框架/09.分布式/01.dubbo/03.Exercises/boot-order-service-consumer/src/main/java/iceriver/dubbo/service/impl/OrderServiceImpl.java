package iceriver.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;

import iceriver.dubbo.bean.UserAddress;
import iceriver.dubbo.service.OrderService;
import iceriver.dubbo.service.UserService;
@Service
public class OrderServiceImpl implements OrderService{
//	@Autowired
	@Reference
	UserService userService;
	
	@Override
	public List<UserAddress> initOrder(String userId) {
		// TODO 自动生成的方法存根
		System.out.println("用户id" + userId);
		
		List<UserAddress> list = userService.getUserAddressList(userId);
		for(UserAddress userAddress : list) {
			System.out.println(userAddress.getUserAddress());
		}
		return list;
	}
}
