package com.coder520.jxc.service.impl;

import com.coder520.jxc.entity.User;
import com.coder520.jxc.repository.UserRepository;
import com.coder520.jxc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 用户Service的实现类
 * @author liugang
 * @create 2018/12/5 23:13
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> list(User user, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        Page<User> pageUser = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (user!=null){
                    if (StringUtils.isNotEmpty(user.getUserName())){
                        predicate.getExpressions().add(cb.like(root.get("userName"),"%"+user.getUserName()+"%"));
                    }
                    predicate.getExpressions().add(cb.notEqual(root.get("id"),1));
                }
                return predicate;
            }
        },pageable);
        return pageUser.getContent();
    }

    @Override
    public Long getCount(User user) {
        Long count = userRepository.count(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (user!=null){
                    if (StringUtils.isNotEmpty(user.getUserName())){
                        predicate.getExpressions().add(cb.like(root.get("userName"),"%"+user.getUserName()+"%"));
                    }
                    predicate.getExpressions().add(cb.notEqual(root.get("id"),1));
                }
                return predicate;
            }
        });
        return count;
    }
}
