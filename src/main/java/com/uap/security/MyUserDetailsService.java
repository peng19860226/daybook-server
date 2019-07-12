package com.uap.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.uap.dao.RoleDao;
import com.uap.dao.UserDao;
import com.uap.model.UserEntity;
import com.uap.utils.MyStringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
	Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Autowired
    UserDao userDao;
	
	@Autowired
    RoleDao roleDao;

	private final static String DEFAULT_PASSWORD = "interest";
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		UserEntity userEntity = null;

		if(MyStringUtil.isInteger(id)) {
			userEntity = userDao.getUserEntityById(Integer.valueOf(id));
		}else {
			userEntity = userDao.getUserEntityByLoginName(id);
		}

		if(userEntity == null) {
			throw new UsernameNotFoundException("用户:"+ id + "不存在！");
		}

		String password = userEntity.getPassword();

		if(password == null) {
			password = DEFAULT_PASSWORD;
		}

		Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
		List<String> roleList = roleDao.getRolesByUserId(userEntity.getId());
		if(roleList!=null&&roleList.size()>0) {
		Iterator<String> iterator =  roleList.iterator();
		while (iterator.hasNext()){
			collection.add(new SimpleGrantedAuthority(iterator.next()));
		}
		}else {
			collection.add(new SimpleGrantedAuthority("ROLE_USER"));
		}

		User user = new User(String.valueOf(userEntity.getId()), password, collection);
		return user;
	}

}
